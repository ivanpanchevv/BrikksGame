public class Player {
    private String name;
    private Sheet sheet;  // The player's game board
    private int bombs;    // Bombs available to skip blocks
    private int energy;   // Energy available for rotating blocks

    public Player(String name) {
        this.name = name;
        this.sheet = new Sheet();
        this.bombs = 3; // Start with 3 bombs
        this.energy = 0; // Start with 0 energy
    }

    public String getName() {
        return name;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public int getBombs() {
        return bombs;
    }

    public void useBomb() {
        if (bombs > 0) {
            bombs--;
        }
    }

    public boolean hasBombs() {
        return bombs > 0;
    }

    public int getEnergy() {
        return energy;
    }

    public void addEnergy(int amount) {
        energy += amount;
    }

    public void useEnergy(int amount) {
        if (amount <= energy) {
            energy -= amount;
        }
    }

    public boolean hasEnoughEnergy(int amount) {
        return energy >= amount;
    }
}
