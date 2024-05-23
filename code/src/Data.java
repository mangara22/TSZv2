import java.util.*;

public class Data {

    private final ArrayList<Pokemon> allPokemon;

    private final ArrayList<Zone> allZones;

    private String selectedRegion;

    private int totalSteps;

    private Zone currentZone;

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

    public ArrayList<Pokemon> getAllPokemon() { return allPokemon; }

    public ArrayList<Zone> getAllZones() { return allZones; }

    public String getSelectedRegion() { return selectedRegion; }

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

    public int getTotalSteps() { return totalSteps; }

    public void incrementTotalSteps() { totalSteps++; }

    public Zone getCurrentZone() { return currentZone; }

    public void setCurrentZone(Zone newZone) { currentZone = newZone; }
}
