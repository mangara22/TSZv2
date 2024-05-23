public class ColorfulConsolePrinter {

    private final String RESET = "\033[0m";
    private final String RED = "\033[0;31m";
    private final String GREEN = "\033[0;32m";
    private final String YELLOW = "\033[0;33m";
    private final String BLUE = "\033[0;34m";
    private final String PURPLE = "\033[0;35m";
    private final String CYAN = "\033[0;36m";
    private final String WHITE = "\033[0;37m";
    private final String RED_BOLD = "\033[1;31m";
    private final String GREEN_BOLD = "\033[1;32m";
    private final String YELLOW_BOLD = "\033[1;33m";
    private final String BLUE_BOLD = "\033[1;34m";
    private final String PURPLE_BOLD = "\033[1;35m";
    private final String CYAN_BOLD = "\033[1;36m";
    private final String WHITE_BOLD = "\033[1;37m";

    public ColorfulConsolePrinter() {}

    public String colorMessage(String message, String color) {
        String text;
        switch (color.toLowerCase()) {
            case "red":
                text = RED + message + RESET;
                break;
            case "green":
                text = GREEN + message + RESET;
                break;
            case "yellow":
                text = YELLOW + message + RESET;
                break;
            case "blue":
                text = BLUE + message + RESET;
                break;
            case "purple":
                text = PURPLE + message + RESET;
                break;
            case "cyan":
                text = CYAN + message + RESET;
                break;
            case "white":
                text = WHITE + message + RESET;
                break;
            case "red_bold":
                text = RED_BOLD + message + RESET;
                break;
            case "green_bold":
                text = GREEN_BOLD + message + RESET;
                break;
            case "yellow_bold":
                text = YELLOW_BOLD + message + RESET;
                break;
            case "blue_bold":
                text = BLUE_BOLD + message + RESET;
                break;
            case "purple_bold":
                text = PURPLE_BOLD + message + RESET;
                break;
            case "cyan_bold":
                text = CYAN_BOLD + message + RESET;
                break;
            case "white_bold":
                text = WHITE_BOLD + message + RESET;
                break;
            default:
                text = message;
                break;
        }
        return text;
    }
}
