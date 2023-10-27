package GoFish;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoFish_ClientCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGoFishGame(scanner);
    }

    private static void playGoFishGame(Scanner scanner) {
        // Ask the user for their name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        Player player1 = new HumanPlayer(playerName);
        Player player2 = new ComputerPlayer("Computer Player");

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        // Create and initialize the game
        GoFishGameClass game = new GoFishGameClass(players);
        System.out.println("Your game has begun! ");
     // Game loop
        while (!game.isGameOver()) {
            for (Player player : players) {
                player.play(); // This is where a player's turn is initiated
                game.playTurn(player); // This is where the turn logic within the game is managed
                displayGameState(player, players); // You can display the game state after each turn
            }
        }

        // Game is over, display the winner
        displayWinner(players);
    }
    // Display the current state of the game
    private static void displayGameState(Player currentPlayer, List<Player> players) {
        System.out.println("--------------------------------------------------");
        System.out.println("Current Player: " + currentPlayer.getName());
        for (Player player : players) {
            System.out.println(player.getName() + "'s Hand: " + player.getHand());
        }
        System.out.println("--------------------------------------------------");
    }

    // Display the winner of the game
    private static void displayWinner(List<Player> players) {
        Player winner = null;
        int maxBooks = 0;

        for (Player player : players) {
            int books = player.countBooks();
            System.out.println(player.getName() + " has " + books + " books.");
            if (books > maxBooks) {
                maxBooks = books;
                winner = player;
            }
        }

        if (winner != null) {
            System.out.println("Winner: " + winner.getName() + " with " + maxBooks + " books!");
        } else {
            System.out.println("It's a tie! No winner.");
        }
    }
}
