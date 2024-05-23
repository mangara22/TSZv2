import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private long startTime;

    public GUI() {
        setTitle("Pokémon Safari Zone");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        moveButton = new JButton("Move");
        statsButton = new JButton("Stats");
        inventoryButton = new JButton("Inventory");

        buttonPanel.add(moveButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(inventoryButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        moveButton.addActionListener(e -> handleMoveOrLook());
        statsButton.addActionListener(e -> displayStats());
        inventoryButton.addActionListener(e -> displayInventory());

        startTime = System.currentTimeMillis();
        welcome();
    }

    private void appendToTextArea(String message) {
        textArea.append(message + "\n");
    }

    private void displayTime(long minutes, long seconds) {
        appendToTextArea("Total time spent adventuring: " + minutes + " minute(s), " + seconds + " second(s)");
    }

    private void displaySafariPokemon() {
        appendToTextArea("+========YOUR SAFARI POKÉMON========+");
        if (userInventory.getCaughtPokemon().size() == 0) {
            appendToTextArea("No Pokémon caught yet.");
        } else {
            for (Pokemon p : userInventory.getCaughtPokemon()) {
                appendToTextArea("" + p);
            }
        }
    }

    private void displayStats() {
        appendToTextArea("+========STATS========+");
        appendToTextArea("Chosen region: " + userData.getSelectedRegion());
        appendToTextArea("Current zone: " + userData.getCurrentZone().getZoneName());
        appendToTextArea("Current steps: " + userData.getTotalSteps());
        appendToTextArea("Total Pokémon caught: " + userInventory.getCaughtPokemon().size());
    }

    private void displayInventory() {
        String pbDesc = userInventory.getItemDescription(Inventory.ItemType.POKEBALL);
        String baitDesc = userInventory.getItemDescription(Inventory.ItemType.BAIT);
        String mudDesc = userInventory.getItemDescription(Inventory.ItemType.MUD);
        String berryDesc = userInventory.getItemDescription(Inventory.ItemType.BERRY);
        appendToTextArea("Number of pokéballs: " + userInventory.getItemCount(Inventory.ItemType.POKEBALL));
        appendToTextArea(pbDesc);
        appendToTextArea("Number of bait: " + userInventory.getItemCount(Inventory.ItemType.BAIT));
        appendToTextArea(baitDesc);
        appendToTextArea("Number of mud: " + userInventory.getItemCount(Inventory.ItemType.MUD));
        appendToTextArea(mudDesc);
        appendToTextArea("Number of berries: " + userInventory.getItemCount(Inventory.ItemType.BERRY));
        appendToTextArea(berryDesc);
        displaySafariPokemon();
    }

    private void handleMoveOrLook() {
        appendToTextArea("You take a step...");
        int val = logic.movement();
        switch (val) {
            case 0 -> appendToTextArea("Nothing there.");
            case 1 -> appendToTextArea("You found a pokéball!");
            case 3 -> appendToTextArea("You found a piece of bait!");
            case 5 -> appendToTextArea("You found a ball of mud!");
            case 6 -> appendToTextArea("You found a berry!");
            case 10 -> {
                appendToTextArea("It's a wild encounter!");
                Pokemon opponent = userData.getCurrentZone().getZonePokemon().get(random.nextInt(10));
                appendToTextArea("A wild " + opponent.getName() + " has appeared!");
                Encounter encounter = new Encounter(userInventory, opponent);
                encounter.wildEncounter();
            }
        }
        steps++;
        checkZone();
        int status = logic.isGameOver(userData.getTotalSteps());
        if (status != 0) {
            endGame(status);
        }
    }

    public void welcome() {
        appendToTextArea("+========Welcome to the SAFARI ZONE!========+");
        appendToTextArea("You will enter the zone with 30 pokéballs along with some bait, mud, and berries. \nYour adventure will end when you run out of pokéballs or when you exceed 100 steps.");
        String choice = JOptionPane.showInputDialog(this, "Would you like to enter? (Y/N): ").toUpperCase();
        if (!choice.equals("Y")) {
            appendToTextArea("Goodbye!");
            System.exit(0);
        }
        userInventory = new Inventory();
        userData = new Data();
        DataLoader loader = new DataLoader(userData);
        int genChoice = Integer.parseInt(JOptionPane.showInputDialog(this, "Which Pokémon Generation would you want to play? (1-9): "));
        while (genChoice < 1 || genChoice > 9) {
            appendToTextArea("Invalid Generation number, enter again!");
            genChoice = Integer.parseInt(JOptionPane.showInputDialog(this, "Which Pokémon Generation do you want to play? (1-9): "));
        }
        loader.readFile(genChoice);
        loader.loadZonePokemon();
        userData.setSelectedRegion(genChoice);
        appendToTextArea("Welcome to the " + userData.getSelectedRegion() + " region!");
        gameplay();
    }

    public void checkZone() {
        int randomTarget = random.nextInt(10, 21);
        if (zoneChange) {
            appendToTextArea("Loading a new zone...");
            Zone currentZone = userData.getCurrentZone();
            appendToTextArea("Entering the " + currentZone.getZoneName() + " zone!");
            appendToTextArea(currentZone.getZoneDesc());
            appendToTextArea("Pokémon in this zone: ");
            for (Pokemon p : currentZone.getZonePokemon()) {
                appendToTextArea(p.toString());
            }
            zoneChange = false;
        }
        if (steps == randomTarget) {
            steps = 0;
            logic.chooseRandomZone();
        }
    }

    public void gameplay() {
        logic = new GameLogic(userData, userInventory);
        logic.chooseRandomZone();
    }

    private void endGame(int status) {
        if (status == -1) {
            appendToTextArea("You ran out of pokéballs, try again next time!");
        } else if (status == 1) {
            appendToTextArea("Limit of 100 steps has been reached!");
        }
        appendToTextArea("Your adventure has ended.");
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
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}


