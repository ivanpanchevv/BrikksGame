public class Block {
    private String color;
    private int[][] shape;

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
                    System.out.print(colorCode + " ■ " + Colors.RESET);
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

