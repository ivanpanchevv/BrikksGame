public class Sheet {
    private String[][] grid;
    private int score;

    public Sheet() {
        grid = new String[10][10]; // Initialize the grid as empty
        score = 0;
    }

    public boolean placeBlock(Block block, int column) {
        int[][] shape = block.getShape();
        String color = block.getColor();


        if (column < 0 || column + shape[0].length > grid[0].length) {
            System.out.println("Block is out of column boundaries! Try another column.");
            return false;
        }


        int bottomRow = findBottomRow(shape, column);
        if (bottomRow == -1) {
            System.out.println("Block is out of row boundaries! Game over.");
            System.exit(0);
            return false;
        }

        // Add points for successfully placing the block
        markBlock(shape, bottomRow, column, color);
        score += 5;


        clearFullRows();

        return true;
    }


    private int findBottomRow(int[][] shape, int column) {
        for (int row = grid.length - shape.length; row >= 0; row--) {
            if (canPlace(shape, row, column)) {
                return row;
            }
        }
        return -1;
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

                    grid[row + i][column + j] = getColorCode(color) + "■  " + Colors.RESET;
                }
            }
        }
    }


    private void clearFullRows() {
        int rowsCleared = 0;

        for (int row = 0; row < grid.length; row++) {
            if (isRowFull(row)) {
                clearRow(row);
                shiftRowsDown(row);
                rowsCleared++;
            }
        }


        if (rowsCleared > 0) {
            score += rowsCleared * 10;
            System.out.println(rowsCleared + " row(s) cleared! Score: " + score);
        }
    }


    private boolean isRowFull(int row) {
        for (int col = 0; col < grid[row].length; col++) {
            if (grid[row][col] == null) {
                return false;
            }
        }
        return true;
    }


    private void clearRow(int row) {
        for (int col = 0; col < grid[row].length; col++) {
            grid[row][col] = null;
        }
    }


    private void shiftRowsDown(int startRow) {
        for (int row = startRow; row > 0; row--) {
            grid[row] = grid[row - 1];
        }
        grid[0] = new String[grid[0].length];
    }

    public void printSheet() {
        System.out.println("Current Board:");


        for (int row = 0; row < grid.length; row++) {

            System.out.printf("%2d ", row);


            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != null) {

                    System.out.printf("%-3s", grid[row][col]);
                } else {

                    System.out.print(".  ");
                }
            }
            System.out.println();
        }

        // Print column numbers at the bottom, aligned with the grid
        System.out.print("   ");
        for (int col = 0; col < grid[0].length; col++) {
            System.out.printf("%-3d", col);
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
                return Colors.RED;
            case "blue":
                return Colors.BLUE;
            case "green":
                return Colors.GREEN;
            case "yellow":
                return Colors.YELLOW;
            case "white":
                return Colors.WHITE;
            default:
                return Colors.RESET;
        }
    }
}

