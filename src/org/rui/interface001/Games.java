package org.rui.interface001;

interface Game {
	boolean move();
}

interface GameFactory {
	Game getGame();
}

// 111111111111111111111111111111
class Checkers implements Game {
	private int moves = 0;
	private static final int MOVES = 3;

	public boolean move() {
		System.out.println("Checkers moves :" + moves);
		return ++moves != MOVES;
	}

}

class ChecksFatory implements GameFactory {
	public Game getGame() {
		return new Checkers();
	}

}

// 22222222222
class Chess implements Game {
	private int moves = 0;
	private static final int MOVES = 4;

	public boolean move() {
		System.out.println("Chess moves :" + moves);
		return ++moves != MOVES;
	}

}

class ChessFatory implements GameFactory {
	public Game getGame() {
		return new Chess();
	}

}

public class Games {

	static void playGame(GameFactory fac) {
		Game game = fac.getGame();
		while (game.move())
			;
	}

	public static void main(String[] args) {
		playGame(new ChecksFatory());
		playGame(new ChessFatory());
	}

}
