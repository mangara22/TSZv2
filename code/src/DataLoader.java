import java.util.*;
import java.io.*;

public class DataLoader {

    private Data data;

    public DataLoader(Data userData) {
        data = userData;
    }

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
        finally {
            System.out.println("Generation [" + ColorfulConsolePrinter.colorMessage("" + gen, "white_bold") + "] Pokémon loaded in!");
        }
    }

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
