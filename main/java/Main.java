public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Single-Player Brikks Game!");

        Player player = new Player("Player 1");
        Game game = new Game(player);

        game.start();
    }
}
