import javax.swing.*;
import java.awt.*;

/**
 * Class to create a GUI for the user's inventory.
 * @author Michael Angara
 */
public class InventoryGUI extends JFrame {

    /**
     * Constructor for the InventoryGUI class.
     * @param userInventory inventory object to display
     */
    public InventoryGUI(Inventory userInventory) {
        setTitle("INVENTORY");
        setSize(725, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Item", "Quantity", "Description"};
        Object[][] data = {
                {"Pokéballs", userInventory.getItemCount(Inventory.ItemType.POKEBALL),
                        userInventory.getItemDescription(Inventory.ItemType.POKEBALL)},
                {"Berries", userInventory.getItemCount(Inventory.ItemType.BERRY),
                        userInventory.getItemDescription(Inventory.ItemType.BERRY)},
                {"Bait", userInventory.getItemCount(Inventory.ItemType.BAIT),
                        userInventory.getItemDescription(Inventory.ItemType.BAIT)},
                {"Mud", userInventory.getItemCount(Inventory.ItemType.MUD),
                        userInventory.getItemDescription(Inventory.ItemType.MUD)}
        };

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(127, 124, 175));
        table.setGridColor(new Color(127, 124, 175));
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
