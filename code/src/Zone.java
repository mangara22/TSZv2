import java.util.*;

public class Zone {

    private final String zoneName;

    private final ArrayList<Pokemon> zonePokemon;

    private String zoneDesc;

    public Zone(String name) {
        zoneName = name;
        zonePokemon = new ArrayList<>(10);
        zoneSetUp();
    }

    public String getZoneName() { return zoneName; }

    public ArrayList<Pokemon> getZonePokemon() { return zonePokemon; }

    public String getZoneDesc() {return zoneDesc; }

    public void zoneSetUp() {
        switch (zoneName) {
            case "Desert" -> zoneDesc = "All you see are hills of sand...lots of sand...";
            case "Forest" -> zoneDesc = "You enter a lush forest area...";
            case "Grasslands" -> zoneDesc = "You see a big grassy open area, with towering mountains in the distance...";
            case "Tundra" -> zoneDesc = "It's..really..cold..here...";
            case "Aquatic" -> zoneDesc = "You see nothing but a few beaches, deserted islands, and a lot of water...";
        }
    }
}
