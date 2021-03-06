package logics.game;

import logics.grammar.statements.Block_Statement;
import characters.Hero;

public abstract class Scenario {

	public Board board;
	public Hero hero;

	public Scenario(int boardHeight, int boardLength , String heroLook ,  Coordinate heroLocation/*, String goalMark, int goalRow, int goalCol*/) {
		board=new Board(boardHeight,boardLength);
		hero = new Hero(heroLook, heroLocation);
	}

	public abstract void RunCode(Block_Statement codeBlock);

	public abstract boolean checkWin();
}
