import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private Random random;
    private static final String[] COLORS = { "Red", "Blue", "Green", "Yellow", "White" };
    private static final int[][][] SHAPES = {
            { { 1, 1, 1 }, { 1, 0, 0 } }, // L-shape
            { { 1, 1, 1, 1 } }, // Line
            { { 1, 1 }, { 1, 1 } }, // Square
            { { 0, 1, 1 }, { 1, 1, 0 } }, // Z-shape
            { { 1, 1, 0 }, { 0, 1, 1 } } // S-shape
    };

    public Game(Player player) {
        this.player = player;
        this.random = new Random();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Game started!");

        while (player.isInGame()) {
            System.out.println("\n" + player.getName() + "'s Turn!");

            // Generate random block
            Block block = generateBlock();
            System.out.println("Generated Block (Color: " + block.getColor() + "):");
            block.printShape();

            // Show the current board
            System.out.println("\nCurrent Board:");
            player.getSheet().printSheet();

            System.out.println("Enter the column (0-9) to place the block:");
            int column = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            boolean placed = player.getSheet().placeBlock(block, column);
            if (!placed) {
                if (blockGoesOutOfBounds(column, block)) {
                    System.out.println("Block placement failed due to column boundaries! Try again.");
                    continue;
                } else {
                    System.out.println("Block placement failed due to row boundaries! Game over.");
                    break;
                }
            }

            // Show the updated board
            System.out.println("\nUpdated Board:");
            player.getSheet().printSheet();
        }

        System.out.println("\nGame Over! Final score: " + player.getSheet().calculateScore());
        scanner.close();
    }

    private Block generateBlock() {
        int shapeIndex = random.nextInt(SHAPES.length);
        String color = COLORS[random.nextInt(COLORS.length)];
        int[][] shape = SHAPES[shapeIndex];
        return new Block(color, shape);
    }

    private boolean blockGoesOutOfBounds(int column, Block block) {
        int[][] shape = block.getShape();
        return column < 0 || column + shape[0].length > 10; // Check column boundaries
    }
}
