package GoFish;

// Bryna

public class CardClass implements Card {
	public String rank;
	public String suit;
	
	public CardClass(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public String getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder(rank + " of " + suit);
		return str.toString();
	}

}
