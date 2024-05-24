import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Encounter extends JFrame {

    private final Inventory userInventory;
    private final Pokemon opponent;
    private JTextArea textArea;
    private JButton catchButton, baitButton, mudButton, berryButton, infoButton, runButton;
    private int fleeRate;
    private int catchRate;

    public Encounter(Inventory userInventory, Pokemon p) {
        Random random = new Random();
        this.userInventory = userInventory;
        this.opponent = p;
        initUI();
        fleeRate = random.nextInt(6); // >= 4 flees
        catchRate = random.nextInt(5); //>= 3 catches
    }

    private void initUI() {
        setTitle("WILD ENCOUNTER");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        catchButton = new JButton("Catch");
        baitButton = new JButton("Bait");
        mudButton = new JButton("Mud");
        berryButton = new JButton("Berry");
        infoButton = new JButton("Info");
        runButton = new JButton("Run");

        buttonPanel.add(catchButton);
        buttonPanel.add(baitButton);
        buttonPanel.add(mudButton);
        buttonPanel.add(berryButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(runButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        catchButton.addActionListener(e -> handleCatch());
        baitButton.addActionListener(e -> handleBait());
        mudButton.addActionListener(e -> handleMud());
        berryButton.addActionListener(e -> handleBerry());
        infoButton.addActionListener(e -> displayInfo());
        runButton.addActionListener(e -> runAway());

        setTitle("WILD ENCOUNTER - " + opponent.nameAndType());
        setVisible(true);
        appendToTextArea("A wild " + opponent.getName() + " has appeared!");
    }

    private void appendToTextArea(String message) {
        textArea.append(message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.revalidate();
        textArea.repaint();
    }

    private void handleCatch() {
        if (userInventory.getItemCount(Inventory.ItemType.POKEBALL) > 0) {
            appendToTextArea("You threw a pokéball...");
            userInventory.decrementItem(Inventory.ItemType.POKEBALL);
            if (fleeRate >= 4) {
                appendToTextArea(opponent.getName() + " is too quick and ran away!");
                dispose();
            } else if (catchRate >= 3) {
                appendToTextArea("You caught " + opponent.getName() + "!");
                userInventory.getCaughtPokemon().add(opponent);
                dispose();
            } else {
                appendToTextArea(opponent.getName() + " broke out of the pokéball!");
                fleeRate++;
                catchRate--;
            }
        } else {
            appendToTextArea("You have no more pokéballs left!");
        }
    }

    private void handleBait() {
        if (userInventory.getItemCount(Inventory.ItemType.BAIT) > 0) {
            appendToTextArea("You threw some bait...");
            userInventory.decrementItem(Inventory.ItemType.BAIT);
            if (fleeRate >= 4) {
                appendToTextArea(opponent.getName() + " is too quick and ran away!");
                dispose();
            } else {
                appendToTextArea(opponent.getName() + " is distracted by the bait, intrigued by it.");
                catchRate--;
                fleeRate--;
            }
        } else {
            appendToTextArea("You have no more bait left!");
        }
    }

    private void handleMud() {
        if (userInventory.getItemCount(Inventory.ItemType.MUD) > 0) {
            appendToTextArea("You threw some mud...");
            userInventory.decrementItem(Inventory.ItemType.MUD);
            if (fleeRate >= 4) {
                appendToTextArea(opponent.getName() + " is too quick and ran away!");
                dispose();
            } else {
                appendToTextArea(opponent.getName() + " is angered by the mud and wants to flee!");
                fleeRate++;
                catchRate++;
            }
        } else {
            appendToTextArea("You have no more mud left!");
        }
    }

    private void handleBerry() {
        if (userInventory.getItemCount(Inventory.ItemType.BERRY) > 0) {
            appendToTextArea("You threw a berry...");
            userInventory.decrementItem(Inventory.ItemType.BERRY);
            if (fleeRate >= 4) {
                appendToTextArea(opponent.getName() + " is too quick and ran away!");
                dispose();
            } else {
                appendToTextArea(opponent.getName() + " enjoys the berries, munching on them happily!");
                catchRate++;
                fleeRate--;
            }
        } else {
            appendToTextArea("You have no more berries left!");
        }
    }

    private void displayInfo() {
        appendToTextArea(">> Here is a description of the opposing Pokémon: ");
        appendToTextArea(opponent.getDesc());
    }

    private void runAway() {
        appendToTextArea("You ran away from " + opponent.getName() + ".");
        dispose();
    }
}
