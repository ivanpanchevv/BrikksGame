public class Player {
    private String name;
    private Sheet sheet;
    private int bombs;
    private int energy;

    public Player(String name) {
        this.name = name;
        this.sheet = new Sheet();
        this.bombs = 3;
        this.energy = 0;
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

