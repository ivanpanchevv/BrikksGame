import java.util.Scanner;

public class Menu {
    private Scanner scanner; // Shared Scanner instance

    public Menu(Scanner scanner) {
        this.scanner = scanner; // Pass the shared Scanner
    }

    public void displayMenu() {
        System.out.println("\n=== Brikks Game Menu ===");
        System.out.println("1. Start New Game");
        System.out.println("2. View Instructions");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            return; // Exit since the game is only played once
        }

        switch (choice) {
            case 1:
                startNewGame();
                break;
            case 2:
                viewInstructions();
                break;
            case 3:
                System.out.println("Exiting the game. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private void startNewGame() {
        System.out.println("\nStarting a new game...");
        Player player = new Player("Player 1");
        Game game = new Game(player);
        game.start(); // Start the game
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
        System.out.println("\nPress Enter to exit.");
        scanner.nextLine(); // Wait for the user to press Enter
    }
}
