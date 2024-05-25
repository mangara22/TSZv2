import java.util.Random;

public class Pokemon {

    private final String name;

    private final String type;

    private final String desc;

    private final String characteristic;

    private final String heldItem;

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

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name + " [" + type + "]").append("\n");
        result.append("Personality: " + characteristic).append("\n");
        result.append("Held Item: " + heldItem).append("\n");
        return result.toString();
    }

    public String nameAndType() { return name + " [" + type + "]"; }

    public String getName() { return name; }

    public String getDesc() { return desc; }
}
