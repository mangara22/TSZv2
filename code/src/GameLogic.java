import java.util.*;

public class GameLogic {

    private final Data userData;

    private final Inventory userInventory;

    private final Random random = new Random();

    public GameLogic(Data userData, Inventory userInventory) {
        this.userData = userData;
        this.userInventory = userInventory;
    }

    public int isGameOver(int steps) {
        if (userInventory.getItemCount(Inventory.ItemType.POKEBALL) <= 0) {
            return -1;
        }
        else if (steps >= 100) {
            return 1;
        }
        return 0;
    }

    public void chooseRandomZone() {
        ArrayList<Zone> zones = userData.getAllZones();
        Zone currentZone = zones.get(random.nextInt(zones.size()));
        userData.setCurrentZone(currentZone);
    }

    public int movement() {
        int encounter = random.nextInt(2); //0 for no encounter, 1 for an encounter
        int item = random.nextInt(8); //0-1 for pokéball, 2-3 for bait, 4-5 for mud, 6 for berry, 7 no item
        if (encounter == 1) {
            userData.incrementTotalSteps();
            return 10; // a wild encounter!
        }
        if (item == 0 || item == 1) {
            userInventory.incrementItem(Inventory.ItemType.POKEBALL);
            return 1; // found a pokéball
        } else if (item == 2 || item == 3) {
            userInventory.incrementItem(Inventory.ItemType.BAIT);
            return 3; // found bait
        }
        else if (item == 4 || item == 5) {
            userInventory.incrementItem(Inventory.ItemType.MUD);
            return 5; // found mud
        }
        else if (item == 6) {
            userInventory.incrementItem(Inventory.ItemType.BERRY);
            return 6; // found berries
        }
        else {
            userData.incrementTotalSteps();
            return 0;
        }
    }
}
