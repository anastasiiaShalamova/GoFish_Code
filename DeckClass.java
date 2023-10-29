package GoFish;
import java.util.*;

// Anastasiia

public class DeckClass implements Deck {
    private List<Card> cards = new ArrayList<>();

    public DeckClass() {
        initializeDeck();
        shuffleDeck();
    }
    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    @Override
    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Remove and return the top card
        }
        return null; // Handle cases where the deck is empty
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    private void initializeDeck() {
        // Create a standard deck of 52 cards with ranks and suits
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (String rank : ranks) {
                Card card = new CardClass(rank, suit); // Use CardClass to create cards
                cards.add(card);
            }
        }
    }
    public List<Card> getCards() {
        return cards;
    }


}
