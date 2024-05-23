public class ColorfulConsolePrinter {

    public ColorfulConsolePrinter() {}

    public String colorMessage(String message, String color) {
        String text;
        String RESET = "\033[0m";
        String RED = "\033[0;31m";
        String GREEN = "\033[0;32m";
        String YELLOW = "\033[0;33m";
        String BLUE = "\033[0;34m";
        String PURPLE = "\033[0;35m";
        String CYAN = "\033[0;36m";
        String WHITE = "\033[0;37m";
        String RED_BOLD = "\033[1;31m";
        String GREEN_BOLD = "\033[1;32m";
        String YELLOW_BOLD = "\033[1;33m";
        String BLUE_BOLD = "\033[1;34m";
        String PURPLE_BOLD = "\033[1;35m";
        String CYAN_BOLD = "\033[1;36m";
        String WHITE_BOLD = "\033[1;37m";
        text = switch (color.toLowerCase()) {
            case "red" -> RED + message + RESET;
            case "green" -> GREEN + message + RESET;
            case "yellow" -> YELLOW + message + RESET;
            case "blue" -> BLUE + message + RESET;
            case "purple" -> PURPLE + message + RESET;
            case "cyan" -> CYAN + message + RESET;
            case "white" -> WHITE + message + RESET;
            case "red_bold" -> RED_BOLD + message + RESET;
            case "green_bold" -> GREEN_BOLD + message + RESET;
            case "yellow_bold" -> YELLOW_BOLD + message + RESET;
            case "blue_bold" -> BLUE_BOLD + message + RESET;
            case "purple_bold" -> PURPLE_BOLD + message + RESET;
            case "cyan_bold" -> CYAN_BOLD + message + RESET;
            case "white_bold" -> WHITE_BOLD + message + RESET;
            default -> message;
        };
        return text;
    }
}
