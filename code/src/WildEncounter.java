import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WildEncounter extends JFrame {

    private final Inventory userInventory;

    private final Pokemon opponent;

    private JTextArea textArea;

    private int fleeRate;

    private int catchRate;

    public WildEncounter(Inventory userInventory, Pokemon p, Runnable onCloseCallback) {
        Random random = new Random();
        this.userInventory = userInventory;
        this.opponent = p;
        initUI();
        fleeRate = random.nextInt(6); // >= 4 flees
        catchRate = random.nextInt(5); //>= 3 catches

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                onCloseCallback.run();
            }
        });
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
        JButton catchButton, baitButton, mudButton, berryButton, runButton;
        catchButton = new JButton("Catch");
        catchButton.setToolTipText("Use a pokéball.");
        catchButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        baitButton = new JButton("Bait");
        baitButton.setToolTipText("Distract with some bait.");
        baitButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        mudButton = new JButton("Mud");
        mudButton.setToolTipText("Throw some mud.");
        mudButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        berryButton = new JButton("Berry");
        berryButton.setToolTipText("Feed a berry.");
        berryButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        runButton = new JButton("Run");
        runButton.setToolTipText("Immediately run from this wild encounter.");
        runButton.setFont(new Font("Century Gothic", Font.BOLD, 12));

        buttonPanel.add(catchButton);
        buttonPanel.add(baitButton);
        buttonPanel.add(mudButton);
        buttonPanel.add(berryButton);
        buttonPanel.add(runButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(new Color(127, 124, 175));
        add(panel);

        catchButton.addActionListener(e -> usePokeball());
        baitButton.addActionListener(e -> useBait());
        mudButton.addActionListener(e -> useMud());
        berryButton.addActionListener(e -> useBerry());
        runButton.addActionListener(e -> dispose());

        setTitle("WILD ENCOUNTER - " + opponent.nameAndType());
        setVisible(true);
        appendToTextArea("A wild " + opponent.getName() + " has appeared!");
        appendToTextArea("+--------DESCRIPTION--------+");
        appendToTextArea(opponent.getDesc());

        if (userInventory.getItemCount(Inventory.ItemType.BAIT) <= 0) {
            baitButton.setEnabled(false);
        }
        if (userInventory.getItemCount(Inventory.ItemType.MUD) <= 0) {
            mudButton.setEnabled(false);
        }
        if (userInventory.getItemCount(Inventory.ItemType.BERRY) <= 0) {
            berryButton.setEnabled(false);
        }
    }

    private void appendToTextArea(String message) {
        textArea.append(message + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private void usePokeball() {
        if (userInventory.getItemCount(Inventory.ItemType.POKEBALL) > 0) {
            userInventory.decrementItem(Inventory.ItemType.POKEBALL);
            if (fleeRate >= 4) {
                JOptionPane.showMessageDialog(this, opponent.getName() + " is " +
                        "too quick and ran away!");
                dispose();
            } else if (catchRate >= 3) {
                JOptionPane.showMessageDialog(this, "You caught " + opponent.getName() + "!");
                userInventory.getCaughtPokemon().add(opponent);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, opponent.getName() + " broke " +
                        "out of the pokéball!");
                fleeRate++;
                catchRate--;
            }
        } else {
            JOptionPane.showMessageDialog(this, "You have no more pokéballs left!");
            dispose();
        }
    }

    private void useBait() {
        if (userInventory.getItemCount(Inventory.ItemType.BAIT) > 0) {
            userInventory.decrementItem(Inventory.ItemType.BAIT);
            if (fleeRate >= 4) {
                JOptionPane.showMessageDialog(this, opponent.getName() + " is " +
                        "too quick and ran away!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, opponent.getName() + " is " +
                        "distracted by the bait, intrigued by it.");
                catchRate--;
                fleeRate--;
            }
        } else {
            JOptionPane.showMessageDialog(this, "You have no more bait left!");
            dispose();
        }
    }

    private void useMud() {
        if (userInventory.getItemCount(Inventory.ItemType.MUD) > 0) {
            userInventory.decrementItem(Inventory.ItemType.MUD);
            if (fleeRate >= 4) {
                JOptionPane.showMessageDialog(this, opponent.getName() + " is " +
                        "too quick and ran away!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, opponent.getName() + " is annoyed " +
                        "by the mud and wants to flee!");
                fleeRate++;
                catchRate++;
            }
        } else {
            JOptionPane.showMessageDialog(this, "You have no more mud left!");
            dispose();
        }
    }

    private void useBerry() {
        if (userInventory.getItemCount(Inventory.ItemType.BERRY) > 0) {
            userInventory.decrementItem(Inventory.ItemType.BERRY);
            if (fleeRate >= 4) {
                JOptionPane.showMessageDialog(this, opponent.getName() + " is " +
                        "too quick and ran away!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, opponent.getName() + " enjoys the berries, " +
                        "munching on them happily!");
                catchRate++;
                fleeRate--;
            }
        } else {
            JOptionPane.showMessageDialog(this, "You have no more berries left!");
            dispose();
        }
    }
}
