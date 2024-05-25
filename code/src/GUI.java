import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Main GUI class that handles the start of the game and overall gameplay.
 * @author Michael Angara
 */
public class GUI extends JFrame {

    private Inventory userInventory;

    private GameLogic logic;

    private final Random random = new Random();

    private Data userData = null;

    private final JTextArea textArea;

    private final JButton moveButton, statsButton, inventoryButton, endButton;

    private boolean zoneChange = true;

    private int steps = 0;

    private long startTime;

    /**
     * Constructor for the GUI class, sets up main GUI window with buttons.
     */
    public GUI() {
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(true);

        moveButton = new JButton("Move Around");
        moveButton.setToolTipText("Take a step and see if something happens.");
        moveButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        statsButton = new JButton("Show Stats");
        statsButton.setToolTipText("Shows the statistics of this adventure.");
        statsButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        inventoryButton = new JButton("Look at Inventory");
        inventoryButton.setToolTipText("Look at the items in your inventory.");
        inventoryButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        endButton = new JButton("Quit");
        endButton.setToolTipText("End this adventure early.");
        endButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        buttonPanel.add(moveButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(endButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(new Color(127, 124, 175));
        add(panel);

        moveButton.addActionListener(e -> handleMovement());
        statsButton.addActionListener(e -> new StatsGUI(userInventory, userData,
                startTime, System.currentTimeMillis()));
        inventoryButton.addActionListener(e -> new InventoryGUI(userInventory));
        endButton.addActionListener(e -> endWindow());

        welcome();
    }

    /**
     * Helper function to append text to the JTextArea with newlines.
     * @param message text to append to the text area.
     */
    private void appendToTextArea(String message) {
        textArea.append(message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Sets the main buttons to be disabled or re-enabled.
     * @param set boolean value to set the buttons to
     */
    private void setButtons(boolean set) {
        moveButton.setEnabled(set);
        statsButton.setEnabled(set);
        inventoryButton.setEnabled(set);
        endButton.setEnabled(set);
    }

    /**
     * A Runnable that is called when a WildEncounter window is closed. This sets the main buttons to
     * be re-enabled.
     */
    private void enableButtons() { setButtons(true); }

    /**
     * Checks to see if the user can randomly enter a new zone and change the current zone. This will show an
     * alert notifying the user of the zone change along with a description of it and its pokémon.
     */
    private void checkZone() {
        int randomTarget = random.nextInt(10, 21);
        if (zoneChange) {
            Zone currentZone = userData.getCurrentZone();
            JOptionPane.showMessageDialog(this, "New Zone Alert!\n" +
                    "Entering the " + currentZone.getZoneName() + " zone!\n" + currentZone.getZoneDesc());
            appendToTextArea("Pokémon in this zone: ");
            for (Pokemon p : currentZone.getZonePokemon()) {
                appendToTextArea(p.nameAndType());
            }
            zoneChange = false;
        }
        if (steps == randomTarget) {
            steps = 0;
            logic.chooseRandomZone();
        }
    }

    /**
     * Handles all movement based on logic.movement()'s return value, handling encounters and items. This function
     * also keeps track of game over status.
     */
    private void handleMovement() {
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
                setButtons(false);
                new WildEncounter(userInventory, opponent, this::enableButtons);
            }
        }
        steps++;
        checkZone();
        int status = logic.isGameOver(userData.getTotalSteps());
        if (status != 0) {
            endGame(status);
        }
    }

    /**
     * Function that displays the main GUI along with the option to load in a specific Pokémon generation.
     */
    private void welcome() {
        appendToTextArea("You will enter the Safari Zone with 30 pokéballs along with some bait, mud, and berries.");
        appendToTextArea("Your adventure will end when you run out of pokéballs or when you exceed 100 steps.");
        userInventory = new Inventory();
        userData = new Data();
        DataLoader loader = new DataLoader(userData);
        int genChoice = Integer.parseInt(JOptionPane.showInputDialog(this,
                "Which Pokémon Generation do you want to play? (1-9): "));
        while (genChoice < 1 || genChoice > 9) {
            genChoice = Integer.parseInt(JOptionPane.showInputDialog(this,
                    "Which Pokémon Generation do you want to play? (1-9): "));
        }
        loader.readFile(genChoice);
        loader.loadZonePokemon();
        userData.setSelectedRegion(genChoice);
        appendToTextArea("Click the buttons to start playing!");
        setTitle("SAFARI ZONE - " + userData.getSelectedRegion() + " Region");
        gameplay();
    }

    /**
     * Function that is called after the user starts the Safari Zone, this function starts the time and
     * creates a new GameLogic instance to handle game logic.
     */
    private void gameplay() {
        startTime = System.currentTimeMillis();
        logic = new GameLogic(userData, userInventory);
        logic.chooseRandomZone();
    }

    /**
     * Function that handles the end of the Safari Zone. This function shows a message dialog of why the game ended.
     * @param status integer value that represents why the game ended
     */
    private void endGame(int status) {
        String reason = "";
        if (status == -1) {
            reason += "\nYou used up all of your pokéballs.";
        }
        else if (status == 1) {
            reason += "\nYou reached the limit of 100 steps.";
        }
        JOptionPane.showMessageDialog(this, "Your time in the Safari Zone has ended." + reason);
        endWindow();
    }

    /**
     * Function that closes the main window and shows the Stats window upon game over.
     */
    private void endWindow() {
        dispose();
        new StatsGUI(userInventory, userData, startTime, System.currentTimeMillis());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI display = new GUI();
            display.setVisible(true);
        });
    }
}
