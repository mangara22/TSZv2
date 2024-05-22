import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Display {

    private final String prompt = ColorfulConsolePrinter.colorMessage(">>", "yellow_bold");
    private Inventory inventory;
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private Data userData = null;

    private void delay(int milliseconds) {
        try{
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private int isGameOver(int steps) {
        if (inventory.getItemCount(Inventory.ItemType.POKEBALL) <= 0) {
            return -1;
        }
        else if (steps >= 100) {
            return 1;
        }
        return 0;
    }

    private void displayTime(long minutes, long seconds) {
        String minutesText = ColorfulConsolePrinter.colorMessage("" + minutes, "white_bold");
        System.out.print("Total time spent adventuring: " + minutesText + " minute(s)");
        String secondsText = ColorfulConsolePrinter.colorMessage("" + seconds, "white_bold");
        System.out.println(", " + secondsText + " second(s)");
    }

    private static void printSafariPokemon() {
        System.out.println(YELLOW + "+=====YOUR SAFARI POKÉMON=====+" + RESET);
        if(safariPokemon.size() == 0){
            System.out.println(WHITE + "No Pokémon caught yet." + RESET);
        }
        for(Pokemon p : safariPokemon){
            System.out.println(WHITE + p + RESET);
        }
    }

    private void stats(String zone, int totalSteps) {
        System.out.println(YELLOW_BOLD + "+=====STATS=====+" + RESET);
        System.out.println("Current region: " + region);
        System.out.println("Current zone: " + zone);
        System.out.println("Current steps: " + WHITE_BOLD + totalSteps + RESET);
        System.out.println("Total Pokémon caught: " + WHITE_BOLD + safariPokemon.size() + RESET);
        System.out.println("Total pokéballs: " + WHITE_BOLD + pokeballs + RESET);
        System.out.println("Total bait: " + WHITE_BOLD + bait + RESET);
        System.out.println("Total mud: " + WHITE_BOLD + mud + RESET);
        System.out.println("Total berries: " + WHITE_BOLD + berries + RESET);
        System.out.println("Total display rates uses left: " + WHITE_BOLD + displayRates + RESET);
        printSafariPokemon();
    }

    public void welcome() {
        inventory = new Inventory();
        System.out.println(ColorfulConsolePrinter.colorMessage("+========Welcome to the SAFARI ZONE!========+", "yellow_bold"));
        System.out.println("You will enter the zone with " + ColorfulConsolePrinter.colorMessage("" + inventory.getItemCount(Inventory.ItemType.POKEBALL), "white_bold")
                + " pokéballs along with some bait, mud, and berries. \nYour adventure will " +
                "end when you run out of pokéballs or you exceed " + ColorfulConsolePrinter.colorMessage("100", "white_bold") + " steps.");
        System.out.print(prompt + "Would you like to enter? (Y/N): ");
        String choice = scanner.nextLine().toUpperCase();
        if(!choice.equals("Y")){
            System.out.println(ColorfulConsolePrinter.colorMessage("Goodbye!", "red_bold"));
            System.exit(0);
        }
        userData = new Data();
        DataLoader loader = new DataLoader(userData);
        int genChoice;
        System.out.print(prompt + "Which Pokémon Generation would you want to play? (1-9): ");
        genChoice = scanner.nextInt();
        while(genChoice < 1 || genChoice > 9) {
            System.out.println(ColorfulConsolePrinter.colorMessage("Invalid Generation number, enter again!", "red_bold"));
            System.out.print(prompt + "Which Pokémon Generation do you want to play? (1-9): ");
            genChoice = scanner.nextInt();
        }
        loader.readFile(genChoice);
        loader.loadZonePokemon();
    }

    public void gameplay() {
        long start = System.currentTimeMillis();
        int steps = 0;
        Zone currentZone = userData.getAllZones().get(random.nextInt(userData.getAllZones().size()));
        boolean zoneChange = true;
        int totalSteps = 0;
        String zone = "";
        int status = isGameOver(totalSteps);
        delay(5000);
//        while(status == 0) {
//            status = isGameOver(totalSteps);
//        }
        if (status == -1) {
            System.out.println(ColorfulConsolePrinter.colorMessage("You have no more pokéballs left!", "red_bold"));
        }
        else if (status == 1) {
            System.out.println(ColorfulConsolePrinter.colorMessage("Limit of 100 steps has been reached!", "red_bold"));
        }
        System.out.println(ColorfulConsolePrinter.colorMessage("Your adventure has ended.", "yellow_bold"));
        long end = System.currentTimeMillis();
        long timeSpent = end - start;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeSpent);
        long seconds = (TimeUnit.MILLISECONDS.toSeconds(timeSpent)) % 60;
        displayTime(minutes, seconds);
    }

    //TODO: add descriptions for each item type?
}
