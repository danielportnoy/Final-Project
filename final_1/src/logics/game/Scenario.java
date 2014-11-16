package logics.game;

import graphics.Graphics_Control;

import java.util.LinkedList;

import logics.grammar.statements.Statement;
import logics.grammar.types.Type;
import characters.Hero;

public class Scenario {

	public Board board;
	public Hero hero;

	public Tile goalTile;

	public Scenario(int boardRows, int boardCols , String heroLook ,  int heroRow , int heroCol, String goalMark, int goalRow, int goalCol) {
		board=new Board(boardRows,boardCols);
		hero = new Hero(heroLook, heroRow, heroCol);

		goalTile = board.getTiles()[goalRow][goalCol];
		goalTile.setMark(goalMark);
	}

	public void RunCode(LinkedList<Statement> code){

		Graphics_Control.drawBoard(board, hero);

		for (Statement statement : code) {
			statement.Run();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Graphics_Control.drawBoard(board, hero);
		}
		
		if(isWin())
			System.out.println("You Won!");
		else
			System.out.println("You Lost!");
	}

	public boolean isWin(){
		return hero.getRowCoord()==goalTile.getRowCoord() & hero.getColCoord()==goalTile.getColCoord();
	}

	class GoRight_Statement extends Statement{

		public GoRight_Statement() {
			super("Go right");
		}

		@Override
		public Type Run() {
			hero.setColCoord(hero.getColCoord()+1);
			return null;
		}

	}

	public Statement GoRightStatement() {
		return new GoRight_Statement();
	}
}
