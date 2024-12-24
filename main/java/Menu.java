import java.util.Scanner;

public class Menu {
    private Scanner scanner; //

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Main Menu:");
            System.out.println("1. Start Game");
            System.out.println("2. View Instructions");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    startNewGame();
                    break;
                case "2":
                    viewInstructions();
                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine();
                    break;
                case "3":
                    System.out.println("Exiting the game. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }


    private void startNewGame() {
        System.out.println("\nStarting a new game...");
        Player player = new Player("Player");
        Game game = new Game(player);
        game.start();
    }

    private void viewInstructions() {
        System.out.println("\n=== Instructions ===");
        System.out.println("1. The game consists of a 10x10 grid.");
        System.out.println("2. Blocks will be generated randomly with different shapes and colors.");
        System.out.println("3. Place blocks by selecting the column where they should drop.");
        System.out.println("4. Blocks must fit within the grid. If they cannot fit in the rows, the game ends.");
        System.out.println("5. You can skip blocks by using bombs (up to 3 per game).");
        System.out.println("6. Points are scored for successfully placed blocks.");
        System.out.println("7. The goal is to place as many blocks as possible and score the highest points!");

    }
}

