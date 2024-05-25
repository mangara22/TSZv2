import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Class to create a GUI for the user's statistics.
 * @author Michael Angara
 */
public class StatsGUI extends JFrame {

    private final long start, end;

    /**
     * Constructor for the StatsGUI class.
     * @param userInventory inventory object to display
     * @param userData data object to display
     * @param start start time of the game
     * @param end current time of the game
     */
    public StatsGUI(Inventory userInventory, Data userData, long start, long end) {
        this.start = start;
        this.end = end;

        setTitle("STATISTICS");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.append("Chosen region: " + userData.getSelectedRegion() + "\n");
        textArea.append("Current zone: " + userData.getCurrentZone().getZoneName() + "\n");
        textArea.append("Current steps: " + userData.getTotalSteps() + "\n");
        textArea.append("Total Pokémon caught: " + userInventory.getCaughtPokemon().size() + "\n\n");

        textArea.append("+--------POKÉMON CAUGHT--------+\n");
        if (userInventory.getCaughtPokemon().isEmpty()) {
            textArea.append("No Pokémon caught yet.\n\n");
        }
        else {
            for (Pokemon p : userInventory.getCaughtPokemon()) {
                textArea.append(p.toString() + "\n\n");
            }
        }

        textArea.append(calculateTime());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(new Color(127, 124, 175), 3));
        add(panel);

        setVisible(true);
    }

    /**
     * Calculates the amount of time elapsed between the start and the current time.
     * @return string containing the time spend adventuring in minutes and seconds
     */
    private String calculateTime() {
        long timeSpent = end - start;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeSpent);
        long seconds = (TimeUnit.MILLISECONDS.toSeconds(timeSpent)) % 60;
        return "Time spent adventuring: " + minutes + " minute(s), " + seconds + " second(s)";
    }
}
