import java.util.*;

/**
 * Class to handle main gameplay logic, such as movement or checking game over status.
 * @author Michael Angara
 */
public class GameLogic {

    private final Data userData;

    private final Inventory userInventory;

    private final Random random = new Random();

    /**
     * Constructor for the GameLogic class.
     * @param userData data object to access
     * @param userInventory inventory object to access
     */
    public GameLogic(Data userData, Inventory userInventory) {
        this.userData = userData;
        this.userInventory = userInventory;
    }

    /**
     * Checks for the two game over conditions: running out of pokéballs or reaching 100 steps.
     * @param steps the current number of steps the user has taken
     * @return integer value representing the game over status
     */
    public int isGameOver(int steps) {
        if (userInventory.getItemCount(Inventory.ItemType.POKEBALL) <= 0) {
            return -1;
        }
        else if (steps >= 100) {
            return 1;
        }
        return 0;
    }

    /**
     * Chooses a random zone and sets it to be the current zone.
     */
    public void chooseRandomZone() {
        ArrayList<Zone> zones = userData.getAllZones();
        Zone currentZone = zones.get(random.nextInt(zones.size()));
        userData.setCurrentZone(currentZone);
    }

    /**
     * Handles all movement by choosing random values, resulting in an encounter, item picked up, or nothing.
     * @return integer value representing an encounter, item, or none
     */
    public int movement() {
        int encounter = random.nextInt(2); // 0 for no encounter, 1 for an encounter
        int item = random.nextInt(8); // 0-1 for pokéball, 2-3 for bait, 4-5 for mud, 6 for berry, 7 no item
        if (encounter == 1) {
            userData.incrementTotalSteps();
            return 10;
        }
        if (item == 0 || item == 1) {
            userInventory.incrementItem(Inventory.ItemType.POKEBALL);
            return 1;
        }
        else if (item == 2 || item == 3) {
            userInventory.incrementItem(Inventory.ItemType.BAIT);
            return 3;
        }
        else if (item == 4 || item == 5) {
            userInventory.incrementItem(Inventory.ItemType.MUD);
            return 5;
        }
        else if (item == 6) {
            userInventory.incrementItem(Inventory.ItemType.BERRY);
            return 6;
        }
        else {
            userData.incrementTotalSteps();
            return 0;
        }
    }
}
