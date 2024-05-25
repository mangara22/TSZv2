import java.util.*;
import java.io.*;

/**
 * Class to load in Pokémon and populate zones.
 * @author Michael Angara
 */
public class DataLoader {

    private final Data data;

    /**
     * Constructor for the DataLoader class.
     * @param userData data object to populate
     */
    public DataLoader(Data userData) { data = userData; }

    /**
     * Opens and reads 50 Pokémon from a file with a specific generation number.
     * @param gen integer representing the generation number (1-9)
     */
    public void readFile(int gen) {
        try {
            File f = new File("../code/files/gen" + gen + ".txt");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" - ");
                data.getAllPokemon().add(new Pokemon(line));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Loads 10 random Pokémon into each of the 5 zones.
     */
    public void loadZonePokemon() {
        Random random = new Random();
        ArrayList<Zone> zones = data.getAllZones();
        ArrayList<Pokemon> allPokemon = data.getAllPokemon();
        for (Zone z : zones) { // populate each zone with 10 random Pokémon
            for (int i = 0; i < 10; i++) {
                int randIdx = random.nextInt(allPokemon.size());
                z.getZonePokemon().add(allPokemon.get(randIdx));
                allPokemon.remove(randIdx);
            }
        }
    }
}
