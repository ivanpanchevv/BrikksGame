
public class Board {
    private Block[][] grid;

    public Board() {
        grid = new Block[5][5]; // Example grid size for blocks
        initializeBlocks();
    }

    public void initializeBlocks() {
        // Initialize blocks with different colors and shapes
        grid[0][0] = new Block("Red", new int[][] { { 1, 1 }, { 1, 0 } });
        grid[0][1] = new Block("Blue", new int[][] { { 1, 1, 1 }, { 0, 1, 0 } });
        // Add other blocks as per game rules
    }

    public Block getBlockByDice(int column, int row) {
        if (column >= 0 && column < grid.length && row >= 0 && row < grid[0].length) {
            return grid[column][row];
        }
        return null;
    }

    public void printBoard() {
        System.out.println("Blocks on the board:");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getColor() + " ");
                } else {
                    System.out.print("Empty ");
                }
            }
            System.out.println();
        }
    }
}
