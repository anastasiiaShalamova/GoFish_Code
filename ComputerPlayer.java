package GoFish;
import java.util.*;

public class ComputerPlayer implements Player {
    private String name;
    private List<Card> hand;
    private Deck deck;  // Added deck
    private List<Player> players;  // Added players

    public ComputerPlayer(String name) {  // Modified constructor
        this.name = name;
        this.hand = new ArrayList<>();
    }

    @Override
    public int countBooks() {
        int books = 0;
        List<Card> handCopy = new ArrayList<>(hand);

        for (int i = 0; i < handCopy.size(); i++) {
            Card card = handCopy.get(i);
            int count = 0;

            for (int j = 0; j < handCopy.size(); j++) {
                if (handCopy.get(j).getRank().equals(card.getRank())) {
                    count++;
                }
            }

            if (count == 4) {
                books++;
                handCopy.removeIf(c -> c.getRank().equals(card.getRank()));
            }
        }

        return books;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public void addToHand(Card card) {
        hand.add(card);
    }

    @Override
    public void play() {
        // Implement the computer player's logic for taking a turn
        if (hand.isEmpty()) {
            // If the hand is empty, draw a card from the deck
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                addToHand(drawnCard);
                System.out.println(name + " drew a card.");
            }
        } else {
            Random random = new Random();
            String rankToAskFor = hand.get(random.nextInt(hand.size())).getRank();

            // Choose a player to ask for cards (next player in the list)
            Player targetPlayer = players.get(0);
            if (targetPlayer != this) {
                for (Card card : targetPlayer.getHand()) {
                    if (card.getRank().equals(rankToAskFor)) {
                        // Player has the card with the requested rank
                        List<Card> receivedCards = new ArrayList<>();
                        for (Card c : targetPlayer.getHand()) {
                            if (c.getRank().equals(rankToAskFor)) {
                                receivedCards.add(c);
                            }
                        }
                        targetPlayer.getHand().removeAll(receivedCards);
                        hand.addAll(receivedCards);

                        System.out.println(name + " asked " + targetPlayer.getName() + " for " + rankToAskFor + ".");
                        System.out.println(name + " received " + receivedCards.size() + " cards.");
                        return; // Exit the loop after a successful request
                    }
                }

                // No cards with the requested rank, so Go Fish
                System.out.println(name + " asked " + targetPlayer.getName() + " for " + rankToAskFor + " but Go Fish!");
                Card drawnCard = deck.drawCard();
                if (drawnCard != null) {
                    addToHand(drawnCard);
                    System.out.println(name + " drew a card.");
                }
            }
        }
    }

}
