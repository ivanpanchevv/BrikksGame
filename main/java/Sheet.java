public class Sheet {
    private String[][] grid; // 10x10 grid to represent the board
    private int score;       // Player's score

    public Sheet() {
        grid = new String[10][10]; // Initialize the grid as empty
        score = 0;
    }

    public boolean placeBlock(Block block, int column) {
        int[][] shape = block.getShape();
        String color = block.getColor();

        // Check for column boundaries
        if (column < 0 || column + shape[0].length > grid[0].length) {
            System.out.println("Block is out of column boundaries! Try another column.");
            return false;
        }

        // Find the bottom-most row where the block can fit
        int bottomRow = findBottomRow(shape, column);
        if (bottomRow == -1) {
            System.out.println("Block is out of row boundaries! Game over.");
            return false;
        }

        // Place the block on the grid
        markBlock(shape, bottomRow, column, color);
        score += 5; // Add points for successfully placing the block

        // Clear full rows and update the score
        clearFullRows();

        return true;
    }

    private int findBottomRow(int[][] shape, int column) {
        for (int row = grid.length - shape.length; row >= 0; row--) {
            if (canPlace(shape, row, column)) {
                return row; // Return the first valid row
            }
        }
        return -1; // No valid row found
    }

    private boolean canPlace(int[][] shape, int row, int column) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    int r = row + i;
                    int c = column + j;
                    if (r >= grid.length || c >= grid[0].length || grid[r][c] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void markBlock(int[][] shape, int row, int column, String color) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] == 1) {
                    // Add colorful block with ANSI codes and proper alignment
                    grid[row + i][column + j] = getColorCode(color) + "■  " + ANSI.RESET;
                }
            }
        }
    }

    /**
     * Clears all rows that are full and shifts rows above down.
     */
    private void clearFullRows() {
        int rowsCleared = 0;

        for (int row = 0; row < grid.length; row++) {
            if (isRowFull(row)) {
                clearRow(row); // Clear the row
                shiftRowsDown(row); // Shift rows above down
                rowsCleared++;
            }
        }

        // Update score based on the number of rows cleared
        if (rowsCleared > 0) {
            score += rowsCleared * 10; // 10 points per cleared row
            System.out.println(rowsCleared + " row(s) cleared! Score: " + score);
        }
    }

    /**
     * Checks if a row is full.
     */
    private boolean isRowFull(int row) {
        for (int col = 0; col < grid[row].length; col++) {
            if (grid[row][col] == null) {
                return false; // Row is not full
            }
        }
        return true; // Row is full
    }

    /**
     * Clears a specific row by setting all its cells to null.
     */
    private void clearRow(int row) {
        for (int col = 0; col < grid[row].length; col++) {
            grid[row][col] = null;
        }
    }

    /**
     * Shifts all rows above the given row down by one position.
     */
    private void shiftRowsDown(int startRow) {
        for (int row = startRow; row > 0; row--) {
            grid[row] = grid[row - 1]; // Move the row above down
        }
        grid[0] = new String[grid[0].length]; // Clear the top row
    }

    public void printSheet() {
        System.out.println("Current Board:");

        // Iterate through the grid to display the board
        for (int row = 0; row < grid.length; row++) {
            // Print row number aligned to two digits
            System.out.printf("%2d ", row);

            // Print each cell in the row
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != null) {
                    // Print the colorful block (aligned with 3-character width)
                    System.out.printf("%-3s", grid[row][col]);
                } else {
                    // Print an empty cell with 3-character width
                    System.out.print(".  ");
                }
            }
            System.out.println(); // Newline after each row
        }

        // Print column numbers at the bottom, aligned with the grid
        System.out.print("   "); // Padding to align with row numbers
        for (int col = 0; col < grid[0].length; col++) {
            System.out.printf("%-3d", col); // Each column number takes 3-character width
        }
        System.out.println();
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
