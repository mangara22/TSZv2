import java.util.*;

public class Data {

    private ArrayList<Pokemon> allPokemon;

    private final String[] zoneNames = {"Desert", "Forest", "Grasslands", "Tundra", "Aquatic"};

    private ArrayList<Zone> allZones;

    private String selectedRegion;

    private int totalSteps;

    private Zone currentZone;

    public Data() {
        allPokemon = new ArrayList<>(50);
        allZones = new ArrayList<>(5);
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
            case 1:
                selectedRegion = "Kanto";
                break;
            case 2:
                selectedRegion = "Johto";
                break;
            case 3:
                selectedRegion = "Hoenn";
                break;
            case 4:
                selectedRegion = "Sinnoh";
                break;
            case 5:
                selectedRegion = "Unova";
                break;
            case 6:
                selectedRegion = "Kalos";
                break;
            case 7:
                selectedRegion = "Alola";
                break;
            case 8:
                selectedRegion = "Galar";
                break;
            case 9:
                selectedRegion = "Paldea";
                break;
            default:
                selectedRegion = "Unknown";
                break;
        }
    }

    public int getTotalSteps() { return totalSteps; }

    public void incrementTotalSteps() { totalSteps++; }

    public Zone getCurrentZone() { return currentZone; }

    public void setCurrentZone(Zone newZone) { currentZone = newZone; }
}
