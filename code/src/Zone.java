import java.util.*;

/**
 * Class to represent a zone.
 * @author Michael Angara
 */
public class Zone {

    private final String zoneName;

    private final ArrayList<Pokemon> zonePokemon;

    private String zoneDesc;

    /**
     * Constructor for the Zone class, also sets up the zone's description.
     * @param name string representing the name of the zone
     */
    public Zone(String name) {
        zoneName = name;
        zonePokemon = new ArrayList<>(10);
        zoneSetUp();
    }

    /**
     * Returns the name of the zone.
     * @return string containing the name
     */
    public String getZoneName() { return zoneName; }

    /**
     * Returns the list of Pokémon in the zone.
     * @return ArrayList of Pokemon
     */
    public ArrayList<Pokemon> getZonePokemon() { return zonePokemon; }

    /**
     * Returns the description of the zone.
     * @return string containing the description of the zone
     */
    public String getZoneDesc() { return zoneDesc; }

    /**
     * Sets the zone description based off of the name of the zone.
     */
    private void zoneSetUp() {
        switch (zoneName) {
            case "Desert" -> zoneDesc = "All you see are hills of sand...lots of sand...";
            case "Forest" -> zoneDesc = "You enter a lush forest area...";
            case "Grasslands" -> zoneDesc = "You see a big grassy open area, with towering mountains in the distance...";
            case "Tundra" -> zoneDesc = "It's..really..cold..here...";
            case "Aquatic" -> zoneDesc = "You see nothing but a few beaches, deserted islands, and a lot of water...";
        }
    }
}
