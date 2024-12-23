public class Sheet {
    private String[][] grid; // Grid stores block colors as strings
    private int score;

    public Sheet() {
        grid = new String[10][10]; // 10x10 grid initialized with null (empty cells)
        score = 0;
    }

    public boolean placeBlock(Block block, int column) {
        int[][] shape = block.getShape();
        String colorCode = getColorCode(block.getColor());

        // Check for column boundaries
        if (column < 0 || column + shape[0].length > grid[0].length) {
            System.out.println("Block is out of column boundaries! Try another column.");
            return false;
        }

        // Try placing the block from the bottom-most row
        int bottomRow = grid.length - 1;
        for (int row = bottomRow; row >= 0; row--) {
            if (canPlace(shape, row, column)) {
                markBlock(shape, row, column, colorCode);
                score += 5; // Add points for successfully placing the block
                return true;
            }
        }

        // If the block cannot be placed in the rows, end the game
        System.out.println("Block is out of row boundaries! Game over.");
        return false;
    }

    private boolean canPlace(int[][] shape, int row, int column) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int r = row + i;
                    int c = column + j;
                    if (r >= grid.length || c >= grid[0].length || c < 0 || grid[r][c] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void markBlock(int[][] shape, int row, int column, String colorCode) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    grid[row + i][column + j] = colorCode + "■" + ANSI.RESET; // Use colored block
                }
            }
        }
    }

    public void printSheet() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != null) {
                    System.out.print(grid[row][col] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public int calculateScore() {
        return score;
    }

    public boolean hasSpace() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getColorCode(String color) {
        switch (color.toLowerCase()) {
            case "red":
                return ANSI.RED;
            case "blue":
                return ANSI.BLUE;
            case "green":
                return ANSI.GREEN;
            case "yellow":
                return ANSI.YELLOW;
            case "white":
                return ANSI.WHITE;
            default:
                return ANSI.RESET;
        }
    }
}
