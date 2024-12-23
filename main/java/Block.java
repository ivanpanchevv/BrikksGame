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
                    System.out.print(colorCode + "■ " + ANSI.RESET);
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private String getColorCode() {
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
