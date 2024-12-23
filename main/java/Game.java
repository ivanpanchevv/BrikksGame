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

            Block block = generateBlock();
            System.out.println("Generated Block (Color: " + block.getColor() + "):");
            block.printShape();

            System.out.println("\nCurrent Board:");
            player.getSheet().printSheet();

            System.out.println("Enter the column (0-9) to place the block, 'r' to rotate (cost: 1 energy), or 'b' to use a bomb:");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("b")) {
                if (player.hasBombs()) {
                    player.useBomb();
                    System.out.println("You used a bomb to skip this block!");
                    continue;
                } else {
                    System.out.println("You have no bombs left!");
                }
            } else if (input.equalsIgnoreCase("r")) {
                if (player.hasEnoughEnergy(1)) {
                    block.rotate();
                    player.useEnergy(1);
                } else {
                    System.out.println("Not enough energy to rotate the block!");
                }
            } else {
                try {
                    int column = Integer.parseInt(input);
                    boolean placed = player.getSheet().placeBlock(block, column);
                    if (placed) {
                        player.addEnergy(1);
                    } else {
                        System.out.println("Block placement failed. Game over.");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Try again.");
                }
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
