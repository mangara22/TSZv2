import java.util.*;

/**
 * Class to store user data, such as region, total steps, and current zone.
 * @author Michael Angara
 */
public class Data {

    private final ArrayList<Pokemon> allPokemon;

    private final ArrayList<Zone> allZones;

    private String selectedRegion;

    private int totalSteps;

    private Zone currentZone;

    /**
     * Constructor for the Data class, also initializes the zones with names.
     */
    public Data() {
        allPokemon = new ArrayList<>(50);
        allZones = new ArrayList<>(5);
        String[] zoneNames = {"Desert", "Forest", "Grasslands", "Tundra", "Aquatic"};
        for(int i = 0; i < 5; i++) {
            allZones.add(new Zone(zoneNames[i]));
        }
        selectedRegion = "";
        totalSteps = 0;
    }

    /**
     * Gets the list of 50 Pokémon from the respective generation.
     * @return ArrayList of Pokemon
     */
    public ArrayList<Pokemon> getAllPokemon() { return allPokemon; }

    /**
     * Gets the ArrayList of 5 Zones.
     * @return ArrayList of type Zone
     */
    public ArrayList<Zone> getAllZones() { return allZones; }

    /**
     * Gets the String representing the user's selected region.
     * @return string with the name of the region
     */
    public String getSelectedRegion() { return selectedRegion; }

    /**
     * Sets the selected region based off of the generation number.
     * @param genNum integer representing the generation number (1-9)
     */
    public void setSelectedRegion(int genNum) {
        switch (genNum) {
            case 1 -> selectedRegion = "Kanto";
            case 2 -> selectedRegion = "Johto";
            case 3 -> selectedRegion = "Hoenn";
            case 4 -> selectedRegion = "Sinnoh";
            case 5 -> selectedRegion = "Unova";
            case 6 -> selectedRegion = "Kalos";
            case 7 -> selectedRegion = "Alola";
            case 8 -> selectedRegion = "Galar";
            case 9 -> selectedRegion = "Paldea";
            default -> selectedRegion = "Unknown";
        }
    }

    /**
     * Gets the total number of steps the user has taken.
     * @return integer representing the total number of steps
     */
    public int getTotalSteps() { return totalSteps; }

    /**
     * Increments the total amount of user steps by one.
     */
    public void incrementTotalSteps() { totalSteps++; }

    /**
     * Gets the current zone the user is in.
     * @return zone object representing the current zone
     */
    public Zone getCurrentZone() { return currentZone; }

    /**
     * Sets the current zone to a new zone.
     * @param newZone zone object that will replace the current zone
     */
    public void setCurrentZone(Zone newZone) { currentZone = newZone; }
}
