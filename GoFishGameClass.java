package GoFish;
import java.util.*;

// Bryna

public class GoFishGameClass implements Game {
	
    private List<Player> players;
    private Deck deck;
    private Map<Player, List<Card>> playerSets;
    
    //constructor
    public GoFishGameClass(List<Player> players) {
        this.players = players;
        this.deck = new DeckClass();
        this.playerSets = new HashMap<>();
        
        for (Player player : players) {
            playerSets.put(player, new ArrayList<>());
        }
    }
 

    
    public void startGame() {
        //start the game by shuffling the deck
        deck.shuffleDeck();

        //give 7 cards to each player
        for (Player player : players) {
            //Populate the player's hand with 7 cards, 
        	//one card at a time since the addToHand() only takes one card at a time
            for (int j = 0; j < 7; j++) {
                Card card = deck.drawCard();
                player.addToHand(card); 
            }
        }
        //starts the game by allowing players to take turns
        while (!isGameOver()) {
            for (Player player : players) {
                //player takes his turn
                playTurn(player);
            }
        }

        //end the game when one of the 2 conditions for the game ending is fulfilled
        endGame();
    }

    

    public boolean isGameOver() {
        // Check if the deck is empty
        if (deck.isEmpty()) {
            return true;
        }

        // Check if any player's hand is empty
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }

        return false;
    }




    public void playTurn(Player player) {
        // Draw a card
        Card cardDrawn = deck.drawCard();
        player.play();

        // Add the drawn card to the player's hand
        player.addToHand(cardDrawn);

        // Check if the drawn card creates a set
        List<Card> currentHand = player.getHand();
        String drawnCardRank = cardDrawn.getRank();
        int count = 0;

        for (Card card : currentHand) {
            if (card.getRank().equals(drawnCardRank)) {
                count++;
            }
        }

        if (count == 4) {
            // If there's a set, then remove the cards of that set from the player's hand
            currentHand.removeIf(card -> card.getRank().equals(drawnCardRank));

            // Keep track of the sets that have been found by the player
            playerSets.get(player).add(cardDrawn);
        }
    }



    //handles game ending logic
    public void endGame() {
        //Create lists to store the sets collected by each player
        List<List<Card>> playerBooksLists = new ArrayList<>();

        //Check each player's hand for books (sets of 4 cards)
        for (Player player : players) {
            List<Card> hand = player.getHand();
            Map<String, Integer> rankCounts = new HashMap<>();

            //Count the number of cards for each rank in the player's hand
            for (Card card : hand) {
                String rank = card.getRank();
                rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
            }

            // Check for books and collect them
            List<Card> books = new ArrayList<>();
            for (String rank : rankCounts.keySet()) {
                if (rankCounts.get(rank) == 4) {
                    // This player has a book of rank
                    for (int i = hand.size() - 1; i >= 0; i--) {
                        if (hand.get(i).getRank().equals(rank)) {
                            books.add(hand.remove(i));
                        }
                    }
                }
            }

            if (!books.isEmpty()) {
                playerBooksLists.add(books);
            }
        }

        //Determine the winner based on the number of books collected
        Player winner = null;
        int maxBooks = 0;
        for (int i = 0; i < playerBooksLists.size(); i++) {
            if (playerBooksLists.get(i).size() > maxBooks) {
                maxBooks = playerBooksLists.get(i).size();
                winner = players.get(i);
            }
        }

        //Display the results
        if (winner != null) {
            System.out.println("Winner: " + winner.getName() + " with " + maxBooks + " books.");
        } else {
            System.out.println("It's a tie! No winner.");
        }

    }
}
