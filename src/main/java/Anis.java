import java.util.Scanner;

public class Anis {
    private static final String BORDER = "    ____________________________________________________________";
    private static final String LOGO =
            """
                    ░███               ░██           
                   ░██░██                            
                  ░██  ░██  ░████████  ░██ ░███████  
                 ░█████████ ░██    ░██ ░██░██        
                 ░██    ░██ ░██    ░██ ░██ ░███████  
                 ░██    ░██ ░██    ░██ ░██       ░██ 
                 ░██    ░██ ░██    ░██ ░██ ░███████                                    
            """;

    public static void printBorder() {
        System.out.println(BORDER);
    }

    public static void printLogo() {
        System.out.print(LOGO);
    }

    public static void displayWelcome() {
        printLogo();
        printBorder();
        System.out.println("\t Hi! My name is Anis, and I'm here to help.");
        System.out.println("\t What's on your mind?");
        printBorder();
    }

    public static void displayGoodbye() {
        System.out.println("\t Glad I could assist! Have a wonderful day.");
        System.out.println("\t Feel free to reach out anytime.");
        printBorder();
    }

    public static void echoCommand(String command) {
        printBorder();
        System.out.println("\t " + command);
        printBorder();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayWelcome();

        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                displayGoodbye();
                break;
            }

            echoCommand(input);
        }

        scanner.close();
    }
}