import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a shared Scanner instance
        Menu menu = new Menu(scanner); // Pass the Scanner to the Menu
        menu.displayMenu(); // Display the menu once
        scanner.close(); // Close the Scanner after the program finishes
    }
}
