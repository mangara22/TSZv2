import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame {
    private Inventory userInventory;
    private GameLogic logic;
    private final Random random = new Random();
    private Data userData = null;
    private boolean zoneChange = true;
    private int steps = 0;
    private JTextArea textArea;
    private JButton moveButton;
    private JButton statsButton;
    private JButton inventoryButton;
    private JButton pokemonButton;
    private long startTime;

    public GUI() {
        setTitle("SAFARI ZONE");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        moveButton = new JButton("Move Around");
        statsButton = new JButton("Show Stats");
        inventoryButton = new JButton("Look at Inventory");
        pokemonButton = new JButton("See caught Pokémon");

        buttonPanel.add(moveButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(pokemonButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        moveButton.addActionListener(e -> handleMoveOrLook());
        statsButton.addActionListener(e -> displayStats());
        inventoryButton.addActionListener(e -> displayInventory());
        pokemonButton.addActionListener(e -> displaySafariPokemon());

        welcome();
    }

    private void appendToTextArea(String message) {
        textArea.append(message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.revalidate();
        textArea.repaint();
    }

    private void displayTime(long minutes, long seconds) {
        appendToTextArea("Total time spent adventuring: " + minutes + " minute(s), " + seconds + " second(s)");
    }

    private void displaySafariPokemon() {
        appendToTextArea("\n+========YOUR SAFARI POKÉMON========+");
        if (userInventory.getCaughtPokemon().isEmpty()) {
            appendToTextArea("No Pokémon caught yet.");
        } else {
            for (Pokemon p : userInventory.getCaughtPokemon()) {
                appendToTextArea(p.toString());
            }
        }
        appendToTextArea("+========YOUR SAFARI POKÉMON========+");
    }

    private void displayStats() {
        appendToTextArea("\n+========STATS========+");
        appendToTextArea("Chosen region: " + userData.getSelectedRegion());
        appendToTextArea("Current zone: " + userData.getCurrentZone().getZoneName());
        appendToTextArea("Current steps: " + userData.getTotalSteps());
        appendToTextArea("Total Pokémon caught: " + userInventory.getCaughtPokemon().size());
        appendToTextArea("+========STATS========+");
    }

    private void displayInventory() {
        String pbDesc = userInventory.getItemDescription(Inventory.ItemType.POKEBALL);
        String baitDesc = userInventory.getItemDescription(Inventory.ItemType.BAIT);
        String mudDesc = userInventory.getItemDescription(Inventory.ItemType.MUD);
        String berryDesc = userInventory.getItemDescription(Inventory.ItemType.BERRY);
        appendToTextArea("\n+========INVENTORY========+");
        appendToTextArea("Pokéballs - " + userInventory.getItemCount(Inventory.ItemType.POKEBALL));
        appendToTextArea(pbDesc);
        appendToTextArea("Bait - " + userInventory.getItemCount(Inventory.ItemType.BAIT));
        appendToTextArea(baitDesc);
        appendToTextArea("Mud - " + userInventory.getItemCount(Inventory.ItemType.MUD));
        appendToTextArea(mudDesc);
        appendToTextArea("Berries - " + userInventory.getItemCount(Inventory.ItemType.BERRY));
        appendToTextArea(berryDesc);
        appendToTextArea("+========INVENTORY========+");
    }

    private void handleMoveOrLook() {
        int val = logic.movement();
        switch (val) {
            case 0 -> appendToTextArea(">> Nothing there.");
            case 1 -> appendToTextArea(">> You found a pokéball!");
            case 3 -> appendToTextArea(">> You found a piece of bait!");
            case 5 -> appendToTextArea(">> You found a ball of mud!");
            case 6 -> appendToTextArea(">> You found a berry!");
            case 10 -> {
                appendToTextArea(">> It's a wild encounter!");
                Pokemon opponent = userData.getCurrentZone().getZonePokemon().get(random.nextInt(10));
                new Encounter(userInventory, opponent);
            }
        }
        steps++;
        checkZone();
        int status = logic.isGameOver(userData.getTotalSteps());
        if (status != 0) {
            endGame(status);
        }
    }

    private void welcome() {
        appendToTextArea("You will enter the Safari Zone with 30 pokéballs along with some bait, mud, and berries.");
        appendToTextArea("Your adventure will end when you run out of pokéballs or when you exceed 100 steps.");
        userInventory = new Inventory();
        userData = new Data();
        DataLoader loader = new DataLoader(userData);
        int genChoice = Integer.parseInt(JOptionPane.showInputDialog(this, "Which Pokémon Generation do you want to play? (1-9): "));
        while (genChoice < 1 || genChoice > 9) {
            genChoice = Integer.parseInt(JOptionPane.showInputDialog(this, "Which Pokémon Generation do you want to play? (1-9): "));
        }
        loader.readFile(genChoice);
        loader.loadZonePokemon();
        userData.setSelectedRegion(genChoice);
        appendToTextArea("Click the buttons to start playing!");
        setTitle("SAFARI ZONE - " + userData.getSelectedRegion() + " Region");
        gameplay();
    }

    private void checkZone() {
        int randomTarget = random.nextInt(10, 21);
        if (zoneChange) {
            appendToTextArea("\n+========NEW ZONE ALERT========+");
            appendToTextArea("Loading a new zone...");
            Zone currentZone = userData.getCurrentZone();
            appendToTextArea("Entering the " + currentZone.getZoneName() + " zone!");
            appendToTextArea(currentZone.getZoneDesc());
            appendToTextArea("Pokémon in this zone: ");
            for (Pokemon p : currentZone.getZonePokemon()) {
                appendToTextArea(p.nameAndType());
            }
            zoneChange = false;
            appendToTextArea("+========NEW ZONE ALERT========+");
        }
        if (steps == randomTarget) {
            steps = 0;
            logic.chooseRandomZone();
        }
    }

    private void gameplay() {
        startTime = System.currentTimeMillis();
        logic = new GameLogic(userData, userInventory);
        logic.chooseRandomZone();
    }

    private void endGame(int status) {
        if (status == -1) {
            appendToTextArea("You ran out of pokéballs, try again next time!");
        } else if (status == 1) {
            appendToTextArea("Limit of 100 steps has been reached!");
        }
        appendToTextArea("Your time in the Safari Zone has ended.");
        displayStats();
        displayInventory();
        long end = System.currentTimeMillis();
        long timeSpent = end - startTime;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeSpent);
        long seconds = (TimeUnit.MILLISECONDS.toSeconds(timeSpent)) % 60;
        displayTime(minutes, seconds);
        moveButton.setEnabled(false);
        statsButton.setEnabled(false);
        inventoryButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI display = new GUI();
            display.setVisible(true);
        });
    }
}
