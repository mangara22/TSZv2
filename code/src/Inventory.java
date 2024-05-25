import java.util.*;

/**
 * Class to store game specific items, such as pokéballs.
 * @author Michael Angara
 */
public class Inventory {

    private final LinkedList<Pokemon> caughtPokemon;

    private int pokeballs;

    private int berries;

    private int bait;

    private int mud;

    /**
     * Custom type to represent items used.
     */
    public enum ItemType {
        POKEBALL, BERRY, BAIT, MUD
    }

    /**
     * Constructor for the Inventory class, initializes all items with a set amount.
     */
    public Inventory() {
        caughtPokemon = new LinkedList<>();
        pokeballs = 30;
        berries = 5;
        bait = 20;
        mud = 20;
    }

    /**
     * Gets the current number of items.
     * @param itemType a special datatype that represents a POKEBALL, BERRY, BAIT, or MUD
     * @return integer representing the quantity of the item
     */
    public int getItemCount(ItemType itemType) {
        return switch (itemType) {
            case POKEBALL -> pokeballs;
            case BERRY -> berries;
            case BAIT -> bait;
            case MUD -> mud;
        };
    }

    /**
     * Increments the current number of an item by one.
     * @param itemType a special datatype that represents a POKEBALL, BERRY, BAIT, or MUD
     */
    public void incrementItem(ItemType itemType) {
        switch (itemType) {
            case POKEBALL -> pokeballs++;
            case BERRY -> berries++;
            case BAIT -> bait++;
            case MUD -> mud++;
        }
    }

    /**
     * Decrements the current number of an item by one.
     * @param itemType a special datatype that represents a POKEBALL, BERRY, BAIT, or MUD
     */
    public void decrementItem(ItemType itemType) {
        switch (itemType) {
            case POKEBALL -> pokeballs--;
            case BERRY -> berries--;
            case BAIT -> bait--;
            case MUD -> mud--;
        }
    }

    /**
     * Gets the item's description.
     * @param itemType a special datatype that represents a POKEBALL, BERRY, BAIT, or MUD
     * @return a string containing the given item's description
     */
    public String getItemDescription(ItemType itemType) {
        return switch (itemType) {
            case POKEBALL -> "A device for catching wild Pokémon.\n";
            case BERRY -> "Increases catch rate, lowers flee rate.\n";
            case BAIT -> "Decrease catch rate and flee rate.\n";
            case MUD -> "Increases catch rate and flee rate.\n";
        };
    }

    /**
     * Gets the list of Pokémon the user caught.
     * @return a linked list of Pokémon
     */
    public LinkedList<Pokemon> getCaughtPokemon() { return caughtPokemon; }
}
