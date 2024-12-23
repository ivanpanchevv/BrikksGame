import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private Random random;
    private static final String[] COLORS = { "Red", "Blue", "Green", "Yellow", "White" };
    private static final int[][][] SHAPES = {
            { { 1, 1, 1 }, { 1, 0, 0 } }, // L-shape
            { { 1, 1, 1, 1 } },           // Line
            { { 1, 1 }, { 1, 1 } },       // Square
            { { 0, 1, 1 }, { 1, 1, 0 } }, // Z-shape
            { { 1, 1, 0 }, { 0, 1, 1 } }  // S-shape
    };

    public Game(Player player) {
        this.player = player;
        this.random = new Random();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Game started!");

        while (player.getSheet().hasSpace()) {
            System.out.println("\n" + player.getName() + "'s Turn!");
            System.out.println("Bombs Remaining: " + player.getBombs());
            System.out.println("Energy: " + player.getEnergy());

            // Generate a random block
            Block block = generateBlock();
            System.out.println("Generated Block (Color: " + block.getColor() + "):");
            block.printShape();

            // Display the current board
            System.out.println("\nCurrent Board:");
            player.getSheet().printSheet();

            boolean blockPlaced = false; // Track whether the block is placed

            while (!blockPlaced) {
                System.out.println("Enter the column (0-9) to place the block, 'r' to rotate (cost: 1 energy), or 'b' to use a bomb:");

                String input = scanner.nextLine().trim(); // Read player input

                if (input.equalsIgnoreCase("b")) { // Use a bomb
                    if (player.hasBombs()) {
                        player.useBomb();
                        System.out.println("You used a bomb to skip this block!");
                        break; // Skip to the next block
                    } else {
                        System.out.println("You have no bombs left!");
                    }
                } else if (input.equalsIgnoreCase("r")) { // Rotate the block
                    if (player.hasEnoughEnergy(1)) {
                        player.useEnergy(1);
                        block.rotate();
                    } else {
                        System.out.println("Not enough energy to rotate the block!");
                    }
                } else { // Attempt to place the block
                    try {
                        int column = Integer.parseInt(input);
                        blockPlaced = player.getSheet().placeBlock(block, column);
                        if (blockPlaced) {
                            player.addEnergy(1); // Gain energy for placing the block
                        } else {
                            System.out.println("Invalid placement! Try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid column (0-9), 'r' to rotate, or 'b' to use a bomb.");
                    }
                }
            }

            // If the player skipped the block using a bomb, continue to the next turn
            if (!blockPlaced) {
                continue;
            }
        }

        System.out.println("\nGame Over! Final score: " + player.getSheet().calculateScore());
    }


    private Block generateBlock() {
        int shapeIndex = random.nextInt(SHAPES.length);
        String color = COLORS[random.nextInt(COLORS.length)];
        return new Block(color, SHAPES[shapeIndex]);
    }
}
