package GoFish;

// Bryna

public interface Game {
	void startGame();
	boolean isGameOver();
	void endGame();
	void playTurn(Player player);
}
