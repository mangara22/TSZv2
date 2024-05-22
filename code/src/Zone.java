import java.util.*;

public class Zone {

    private final String zoneName;
    private ArrayList<Pokemon> zonePokemon;

    public Zone(String name) {
        zoneName = name;
        zonePokemon = new ArrayList<>();
    }

    public String getZoneName() {
        return zoneName;
    }

    public ArrayList<Pokemon> getZonePokemon() {
        return zonePokemon;
    }
}
