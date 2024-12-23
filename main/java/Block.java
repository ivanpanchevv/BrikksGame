public class Block {
    private String color; // The color of the block
    private int[][] shape; // The shape of the block

    public Block(String color, int[][] shape) {
        this.color = color;
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public int[][] getShape() {
        return shape;
    }

    public void printShape() {
        String colorCode = getColorCode();
        for (int[] row : shape) {
            for (int cell : row) {
                if (cell == 1) {
                    System.out.print(colorCode + " ■ " + ANSI.RESET);
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    public String getColorCode() {
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

    public void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = shape[i][j];
            }
        }

        shape = rotated;
        System.out.println("Block rotated! Updated shape:");
        printShape();
    }
}
