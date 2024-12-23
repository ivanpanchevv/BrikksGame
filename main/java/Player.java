public class Player {
    private String name;
    private Sheet sheet;
    private int bombsLeft;

    public Player(String name) {
        this.name = name;
        this.sheet = new Sheet();
        this.bombsLeft = 3; // Player starts with 3 bombs
    }

    public String getName() {
        return name;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public int getBombsLeft() {
        return bombsLeft;
    }

    public void useBomb() {
        if (bombsLeft > 0) {
            bombsLeft--;
            System.out.println("Bomb used! Remaining bombs: " + bombsLeft);
        } else {
            System.out.println("No bombs left!");
        }
    }

    public boolean isInGame() {
        return bombsLeft > 0 || sheet.hasSpace();
    }
}
