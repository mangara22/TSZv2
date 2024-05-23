import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Display {

    private final ColorfulConsolePrinter printer = new ColorfulConsolePrinter();

    private final String prompt = printer.colorMessage(">>", "yellow_bold");

    private Inventory userInventory;

    private GameLogic logic;

    private Scanner scanner = new Scanner(System.in);

    private final Random random = new Random();

    private Data userData = null;

    private boolean zoneChange = true;

    private int steps = 0;

    public Display() {}

    private void delay(int milliseconds) {
        try{
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private void displayTime(long minutes, long seconds) {
        String minutesText = printer.colorMessage("" + minutes, "white_bold");
        System.out.print("Total time spent adventuring: " + minutesText + " minute(s)");
        String secondsText = printer.colorMessage("" + seconds, "white_bold");
        System.out.println(", " + secondsText + " second(s)");
    }

    private void displaySafariPokemon() {
        System.out.println(printer.colorMessage("+========YOUR SAFARI POKÉMON========+", "yellow_bold"));
        if (userInventory.getCaughtPokemon().size() == 0) {
            System.out.println(printer.colorMessage("No Pokémon caught yet.", "white"));
        }
        else {
            for (Pokemon p : userInventory.getCaughtPokemon()) {
                System.out.println(printer.colorMessage("" + p, "white"));
            }
        }
    }

    private void displayStats() {
        System.out.println(printer.colorMessage("+========STATS========+", "yellow_bold"));
        System.out.println("Chosen region: " + userData.getSelectedRegion());
        System.out.println("Current zone: " + userData.getCurrentZone().getZoneName());
        System.out.println("Current steps: " + printer.colorMessage("" +
                userData.getTotalSteps(), "white_bold"));
        System.out.println("Total Pokémon caught: " + printer.colorMessage("" +
                userInventory.getCaughtPokemon().size(), "white_bold"));
        System.out.println("Total pokéballs: " + printer.colorMessage("" +
                userInventory.getItemCount(Inventory.ItemType.POKEBALL), "white_bold"));
        System.out.println("Total bait: " + printer.colorMessage("" +
                userInventory.getItemCount(Inventory.ItemType.BAIT), "white_bold"));
        System.out.println("Total mud: " + printer.colorMessage("" +
                userInventory.getItemCount(Inventory.ItemType.MUD), "white_bold"));
        System.out.println("Total berries: " + printer.colorMessage("" +
                userInventory.getItemCount(Inventory.ItemType.BERRY), "white_bold"));
        displaySafariPokemon();
    }

    private void options() {
        scanner = new Scanner(System.in);
        System.out.println(printer.colorMessage("+========OPTIONS========+", "yellow_bold"));
        System.out.print(prompt + "Move(M) Look(L) Stats(S): ");
        String choice = scanner.nextLine().toUpperCase();
        if (choice.equals("M") || choice.equals("L")) {
            System.out.print("You take a step...");
            delay(500);
            switch (logic.movementOrLook()) {
                case 0:
                    System.out.println(printer.colorMessage("Nothing there.", "white"));
                case 1:
                    System.out.println(printer.colorMessage("You found a pokéball!", "red"));
                    break;
                case 3:
                    System.out.println(printer.colorMessage("You found a piece of bait!", "red"));
                    break;
                case 5:
                    System.out.println(printer.colorMessage("You found a ball of mud!", "red"));
                    break;
                case 6:
                    System.out.println(printer.colorMessage("You found a berry!", "red"));
                    break;
                case 10:
                    System.out.println(printer.colorMessage("It's a wild encounter!", "yellow"));
                    break;
            }
        }
        else if (choice.equals("S")) {
            displayStats();
        }
        else {
            System.out.println(printer.colorMessage("No valid option selected, defaulting to Stats.", "white"));
            displayStats();
        }
    }

    public void welcome() {
        System.out.println(printer.colorMessage("+========Welcome to the SAFARI ZONE!========+", "yellow_bold"));
        System.out.println("You will enter the zone with " + printer.colorMessage("" + 30, "white_bold")
                + " pokéballs along with some bait, mud, and berries. \nYour adventure will " +
                "end when you run out of pokéballs or when you exceed " +
                printer.colorMessage("100", "white_bold") + " steps.");
        System.out.print(prompt + "Would you like to enter? (Y/N): ");
        String choice = scanner.nextLine().toUpperCase();
        if (!choice.equals("Y")) {
            System.out.println(printer.colorMessage("Goodbye!", "red_bold"));
            System.exit(0);
        }
        userInventory = new Inventory();
        userData = new Data();
        DataLoader loader = new DataLoader(userData);
        int genChoice;
        System.out.print(prompt + "Which Pokémon Generation would you want to play? (1-9): ");
        genChoice = scanner.nextInt();
        while(genChoice < 1 || genChoice > 9) {
            System.out.println(printer.colorMessage("Invalid Generation number, enter again!", "red_bold"));
            System.out.print(prompt + "Which Pokémon Generation do you want to play? (1-9): ");
            genChoice = scanner.nextInt();
        }
        loader.readFile(genChoice);
        loader.loadZonePokemon();
        userData.setSelectedRegion(genChoice);
        System.out.println("Welcome to the " + printer.colorMessage("" +
                userData.getSelectedRegion(), "white_bold") + " region!");
    }

    public void checkZone() {
        int randomTarget = random.nextInt(10,21);
        if (zoneChange) {
            System.out.print(printer.colorMessage("Loading a new zone", "white"));
            delay(1000);
            for(int i = 0; i < 3; i++){
                System.out.print(printer.colorMessage(".", "white_bold"));
                delay(1000);
            }
            Zone currentZone = userData.getCurrentZone();
            System.out.println("Entering the " + currentZone.getZoneName() + " zone!");
            System.out.println(printer.colorMessage(currentZone.getZoneDesc(), currentZone.getZoneColor()));
            System.out.println(printer.colorMessage("Pokémon in this zone: ", "white"));
            for (Pokemon p : currentZone.getZonePokemon()) {
                System.out.println(printer.colorMessage(p.toString(), currentZone.getZoneColor()));
            }
            zoneChange = false;
        }
        if (steps == randomTarget) {
            steps = 0;
            logic.chooseRandomZone();
        }
    }

    public void gameplay() {
        logic = new GameLogic(userData, userInventory);
        long start = System.currentTimeMillis();
        logic.chooseRandomZone();
        int status = logic.isGameOver(userData.getTotalSteps());
        delay(2000);
        while (status == 0) {
            checkZone();
            options();
            steps++;
            status = logic.isGameOver(userData.getTotalSteps());
        }
        if (status == -1) {
            System.out.println(printer.colorMessage("You have no more pokéballs left!", "red_bold"));
        }
        else if (status == 1) {
            System.out.println(printer.colorMessage("Limit of 100 steps has been reached!", "red_bold"));
        }
        System.out.println(printer.colorMessage("Your adventure has ended.", "yellow_bold"));
        displayStats();
        long end = System.currentTimeMillis();
        long timeSpent = end - start;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeSpent);
        long seconds = (TimeUnit.MILLISECONDS.toSeconds(timeSpent)) % 60;
        displayTime(minutes, seconds);
    }
}