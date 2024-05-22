import java.util.*;

public class Inventory {

    private LinkedList<Pokemon> caughtPokemon;
    private int pokeballs;
    private int berries;
    private int bait;
    private int mud;
    private int displayRates;

    public enum ItemType {
        POKEBALL, BERRY, BAIT, MUD, DISPLAYRATES
    }

    public Inventory() {
        caughtPokemon = new LinkedList<>();
        pokeballs = 30;
        berries = 5;
        bait = 20;
        mud = 20;
        displayRates = 10;
    }

    public int getItemCount(ItemType itemType) {
        switch (itemType) {
            case POKEBALL:
                return pokeballs;
            case BERRY:
                return berries;
            case BAIT:
                return bait;
            case MUD:
                return mud;
            case DISPLAYRATES:
                return displayRates;
            default:
                return -1;
        }
    }

    public void incrementItem(ItemType itemType) {
        switch (itemType) {
            case POKEBALL:
                pokeballs++;
                break;
            case BERRY:
                berries++;
                break;
            case BAIT:
                bait++;
                break;
            case MUD:
                mud++;
                break;
            case DISPLAYRATES:
                displayRates++;
                break;
        }
    }

    public void decrementItem(ItemType itemType) {
        switch (itemType) {
            case POKEBALL:
                pokeballs--;
                break;
            case BERRY:
                berries--;
                break;
            case BAIT:
                bait--;
                break;
            case MUD:
                mud--;
                break;
            case DISPLAYRATES:
                displayRates--;
                break;
        }
    }
}
