import java.util.*;

public class Inventory {

    private LinkedList<Pokemon> caughtPokemon;

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
        switch (itemType) {
            case POKEBALL:
                return pokeballs;
            case BERRY:
                return berries;
            case BAIT:
                return bait;
            case MUD:
                return mud;
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
        }
    }

    public String getItemDescription(ItemType itemType) {
        String desc = "";
        switch (itemType) {
            case POKEBALL:
                desc = "A device for catching wild Pokémon. It’s thrown like a ball at a Pokémon, " +
                        "comfortably encapsulating its target.";
                break;
            case BERRY:
                desc = "A berry will increase a Pokémon's catch rate and decrease a Pokémon's flee rate.";
                break;
            case BAIT:
                desc = "Bait will decrease a Pokémon's catch rate and flee rate.";
                break;
            case MUD:
                desc = "Mud will increase a Pokémon's catch rate and flee rate.";
                break;
        }
        return desc;

    }

    public LinkedList<Pokemon> getCaughtPokemon() {
        return caughtPokemon;
    }
}
