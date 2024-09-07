package tictactoe;

public class Main {
	
	public static void main(String[] args) {
		GameRunner game = new GameRunner(); 
		game.initializeGame(); 
		System.out.println("Game Winner: " + game.startGame());
	}
}
