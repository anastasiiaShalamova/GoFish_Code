package GoFish;

import java.util.List;

public interface Player {
	String getName();
	List<Card> getHand();
	void addToHand(Card card);
	void play();
	int countBooks();

}