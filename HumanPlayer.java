package GoFish;
import java.util.*;

// Anastasiia

public class HumanPlayer implements Player {
    private String name;
    private List<Card> hand;
    private Deck deck;  // Added deck
    private List<Player> players;  // Added players

    public HumanPlayer(String name) {  // Modified constructor
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
        if (hand.isEmpty()) {
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                addToHand(drawnCard);
                System.out.println(name + " drew a card.");
            }
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println(name + ", it's your turn.");
            System.out.println("Your hand: " + hand);
            System.out.print("Enter the rank you want to ask for: ");
            String rankToAskFor = scanner.nextLine().trim();

            Player targetPlayer = players.get(0);
            if (targetPlayer != this) {
                for (Card card : targetPlayer.getHand()) {
                    if (card.getRank().equals(rankToAskFor)) {
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
                        return;
                    }
                }

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
