import java.util.*;

public class Data {

    private ArrayList<Pokemon> allPokemon;
    private final String[] zoneNames = {"Desert", "Forest", "Grasslands", "Tundra", "Aquatic"};
    private ArrayList<Zone> allZones;
    private LinkedList<Pokemon> caughtPokemon;

    public Data() {
        allPokemon = new ArrayList<>(50);
        caughtPokemon = new LinkedList<>();
        allZones = new ArrayList<>(5);
        int i = 0;
        for (Zone z : allZones) {
            z = new Zone(zoneNames[i]);
        }
    }

    public ArrayList<Pokemon> getAllPokemon() {
        return allPokemon;
    }

    public String[] getZoneNames() {
        return zoneNames;
    }

    public ArrayList<Zone> getAllZones() {
        return allZones;
    }
}
