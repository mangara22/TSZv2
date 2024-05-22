import java.util.*;
import java.io.*;

public class DataLoader {

    public void readFile(int gen, Data data, TextColor color) {
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
            color.displayMessage("Generation [" + gen + "] Pokémon loaded in!", "white_bold");
        }
    }

    public void loadZonePokemon(Data data) {
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
