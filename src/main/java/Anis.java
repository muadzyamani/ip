public class Anis {
    private static final String BORDER = "____________________________________________________________";
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
        System.out.println(" Hi! My name is Anis, and I'm here to help.");
        System.out.println(" What's on your mind?");
        printBorder();
    }

    public static void displayGoodbye() {
        System.out.println(" Glad I could assist! Have a wonderful day.");
        System.out.println(" Feel free to reach out anytime.");
        printBorder();
    }

    public static void main(String[] args) {
        displayWelcome();
        displayGoodbye();
    }
}