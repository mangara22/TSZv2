import java.util.Random;

/**
 * Class to represent a Pokémon.
 * @author Michael Angara
 */
public class Pokemon {

    private final String name;

    private final String type;

    private final String desc;

    private final String characteristic;

    private final String heldItem;

    /**
     * Constructor for the Pokemon class, also chooses a random characteristic and held item.
     * @param data string array containing the name, type, and description
     */
    public Pokemon(String[] data) {
        Random random = new Random();
        String[] characteristics =
                {"Likes to relax.", "A little quick tempered.", "Has great endurance.", "Can be highly curious.",
                "Hates to lose.", "Is cautious and alert to sounds.", "Can be stubborn at times."};
        String[] items =
                {"Amulet Coin", "Zinc", "Wide Lens", "Razor Claw", "Muscle Feather", "Pink Scarf",
                "Lagging Tail", "Grip Claw", "Expert Belt", "Covert Cloak", "Choice Specs", "Black Sludge"};
        name = data[0];
        type = data[1];
        desc = data[2];
        int randCIdx = random.nextInt(characteristics.length);
        int randIdx = random.nextInt(items.length);
        characteristic = characteristics[randCIdx];
        heldItem = items[randIdx];
    }

    /**
     * Returns the string representation of a Pokémon.
     * @return string containing the name, type, personality, and held item
     */
    public String toString() {
        return name + " [" + type + "]" + "\n" +
                "Personality: " + characteristic + "\n" +
                "Held Item: " + heldItem + "\n";
    }

    /**
     * Returns the name and the type of Pokémon.
     * @return string containing the name and type
     */
    public String nameAndType() { return name + " [" + type + "]"; }

    /**
     * Returns the name of a Pokémon.
     * @return string representing the name
     */
    public String getName() { return name; }

    /**
     * Returns the description of a Pokémon.
     * @return string containing the description of the Pokémon
     */
    public String getDesc() { return desc; }
}
