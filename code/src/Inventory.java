import java.util.*;

public class Inventory {

    private final LinkedList<Pokemon> caughtPokemon;

    private int pokeballs;

    private int berries;

    private int bait;

    private int mud;

    public enum ItemType {
        POKEBALL, BERRY, BAIT, MUD
    }

    public Inventory() {
        caughtPokemon = new LinkedList<>();
        pokeballs = 30;
        berries = 5;
        bait = 20;
        mud = 20;
    }

    public int getItemCount(ItemType itemType) {
        return switch (itemType) {
            case POKEBALL -> pokeballs;
            case BERRY -> berries;
            case BAIT -> bait;
            case MUD -> mud;
        };
    }

    public void incrementItem(ItemType itemType) {
        switch (itemType) {
            case POKEBALL -> pokeballs++;
            case BERRY -> berries++;
            case BAIT -> bait++;
            case MUD -> mud++;
        }
    }

    public void decrementItem(ItemType itemType) {
        switch (itemType) {
            case POKEBALL -> pokeballs--;
            case BERRY -> berries--;
            case BAIT -> bait--;
            case MUD -> mud--;
        }
    }

    public String getItemDescription(ItemType itemType) {
        return switch (itemType) {
            case POKEBALL -> "A device for catching wild Pokémon.\n";
            case BERRY -> "Increases catch rate, lowers flee rate.\n";
            case BAIT -> "Decrease catch rate and flee rate.\n";
            case MUD -> "Increases catch rate and flee rate.\n";
        };
    }

    public LinkedList<Pokemon> getCaughtPokemon() { return caughtPokemon; }
}
