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
            case POKEBALL -> "A device for catching wild Pokémon. It’s thrown like a ball at a Pokémon, " +
                    "comfortably encapsulating its target.";
            case BERRY -> "A berry will increase a Pokémon's catch rate and decrease a Pokémon's flee rate.";
            case BAIT -> "Bait will decrease a Pokémon's catch rate and flee rate.";
            case MUD -> "Mud will increase a Pokémon's catch rate and flee rate.";
        };

    }

    public LinkedList<Pokemon> getCaughtPokemon() {
        return caughtPokemon;
    }
}
