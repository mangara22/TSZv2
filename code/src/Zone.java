import java.util.*;

public class Zone {

    private String zoneName;

    private final ColorfulConsolePrinter printer = new ColorfulConsolePrinter();

    private ArrayList<Pokemon> zonePokemon;

    private String zoneColor;

    private String zoneDesc;

    public Zone(String name) {
        zoneName = name;
        zonePokemon = new ArrayList<>();
        zoneSetUp();
    }

    public String getZoneName() { return zoneName; }

    public ArrayList<Pokemon> getZonePokemon() { return zonePokemon; }

    public String getZoneColor() { return zoneColor; }

    public String getZoneDesc() {return zoneDesc; }

    public void zoneSetUp() {
        switch (zoneName) {
            case "Desert":
                zoneName = printer.colorMessage(zoneName, "yellow_bold");
                zoneColor = "yellow";
                zoneDesc = "All you see are hills of sand...lots of sand...";
                break;
            case "Forest":
                zoneName = printer.colorMessage(zoneName, "purple_bold");
                zoneColor = "purple";
                zoneDesc = "You enter a lush forest area...";
                break;
            case "Grasslands":
                zoneName = printer.colorMessage(zoneName, "green_bold");
                zoneColor = "green";
                zoneDesc = "You see a big grassy open area, with towering mountains in the distance...";
                break;
            case "Tundra":
                zoneName = printer.colorMessage(zoneName, "cyan_bold");
                zoneColor = "cyan";
                zoneDesc = "It's..really..cold..here...";
                break;
            case "Aquatic":
                zoneName = printer.colorMessage(zoneName, "blue_bold");
                zoneColor = "blue";
                zoneDesc = "You see nothing but a few beaches, deserted islands, and a lot of water...";
                break;
        }
    }
}
