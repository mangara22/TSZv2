import java.util.*;

public class Encounter {

    private final Inventory userInventory;

    private final Pokemon opponent;

    private final Random random = new Random();

    private final ColorfulConsolePrinter printer = new ColorfulConsolePrinter();

    public Encounter(Inventory userInventory, Pokemon p) {
        this.userInventory = userInventory;
        opponent = p;
    }

    private void delay(int milliseconds) {
        try{
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private String encounterOptions() {
        String prompt = printer.colorMessage(">>", "yellow_bold");
        Scanner scanner = new Scanner(System.in);
        if (userInventory.getCaughtPokemon().contains(opponent)) {
            System.out.println(printer.colorMessage("*---*---*" + opponent + "*---*---*", "red"));
            System.out.println(printer.colorMessage("Already caught.", "white"));
        }
        else System.out.println(printer.colorMessage("---------" + opponent + "---------", "red"));
        System.out.print(prompt + "What would you like to do? Catch(C) Bait(T) Mud(M) Berry(B) Info(I) Run(R): ");
        return scanner.nextLine().toUpperCase();
    }

    public void wildEncounter() {
        int fleeRate = random.nextInt(6); // >= 4 flees
        int catchRate = random.nextInt(5); // >=3 catches
        label:
        while (true) {
            String choice = encounterOptions();
            String oppoName = printer.colorMessage(opponent.getName(), "red");
            switch (choice) {
                case "C":
                    if (userInventory.getItemCount(Inventory.ItemType.POKEBALL) > 0) {
                        System.out.println("You threw a pokéball...");
                        userInventory.decrementItem(Inventory.ItemType.POKEBALL);
                        delay(1000);
                        for (int i = 1; i <= 3; i++) {
                            System.out.print(i + "..");
                            delay(500);
                        }
                        if (fleeRate >= 4) {
                            System.out.println(oppoName + " is too quick and ran away!");
                            break label;
                        } else if (catchRate >= 3) {
                            System.out.println("you caught " + oppoName + "!");
                            userInventory.getCaughtPokemon().add(opponent);
                            break label;
                        }
                        System.out.println(oppoName + " broke out of the pokéball!");
                        catchRate--;
                        fleeRate++;
                    } else {
                        System.out.println(printer.colorMessage("You have no more pokéballs left!", "yellow"));
                        break label;
                    }
                    break;
                case "T":
                    if (userInventory.getItemCount(Inventory.ItemType.BAIT) > 0) {
                        System.out.println("You threw some bait...");
                        userInventory.decrementItem(Inventory.ItemType.BAIT);
                        delay(1000);
                        if (fleeRate >= 4) {
                            System.out.println(oppoName + " is too quick and ran away!");
                            break label;
                        }
                        fleeRate--;
                        catchRate--;
                    } else {
                        System.out.println(printer.colorMessage("You have no more bait left!", "yellow"));
                        break label;
                    }
                    break;
                case "M":
                    if (userInventory.getItemCount(Inventory.ItemType.MUD) > 0) {
                        System.out.println("You threw some mud...");
                        userInventory.decrementItem(Inventory.ItemType.MUD);
                        delay(1000);
                        if (fleeRate >= 4) {
                            System.out.println(oppoName + " is too quick and ran away!");
                            break label;
                        }
                        catchRate++;
                        fleeRate++;
                    } else {
                        System.out.println(printer.colorMessage("You have no more mud left!", "yellow"));
                        break label;
                    }
                    break;
                case "B":
                    if (userInventory.getItemCount(Inventory.ItemType.BERRY) > 0) {
                        System.out.println("You threw a berry...");
                        userInventory.decrementItem(Inventory.ItemType.BERRY);
                        delay(1000);
                        if (fleeRate >= 4) {
                            System.out.println(oppoName + " is too quick and ran away!");
                            break label;
                        }
                        catchRate++;
                        fleeRate--;
                    } else {
                        System.out.println(printer.colorMessage("You have no more berries left!", "yellow"));
                        break label;
                    }
                    break;
                case "I":
                    System.out.println(printer.colorMessage("Here is a description of the opposing Pokémon: ", "white"));
                    System.out.println(printer.colorMessage(opponent.getDesc(), "red"));
                    break;
                case "R":
                    System.out.println("You ran away from " + oppoName + ".");
                    break label;
            }
        }
    }
}
