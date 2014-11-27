package logics.game;

import logics.grammar.statements.Block;
import characters.Hero;

public abstract class Scenario {

	public Board board;
	public Hero hero;

	/*public Tile goalTile;*/

	public Scenario(int boardRows, int boardCols , String heroLook ,  int heroRow , int heroCol/*, String goalMark, int goalRow, int goalCol*/) {
		board=new Board(boardRows,boardCols);
		hero = new Hero(heroLook, heroRow, heroCol);

		/*goalTile = board.getTiles()[goalRow][goalCol];
		goalTile.setMark(goalMark);
		
		board.getTiles()[0][0]=new Wall_Tile(0, 0);*/
	}

	public abstract void RunCode(Block codeBlock);/*{

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
	}*/

	public abstract boolean isWin();/*{
		return hero.getRowCoord()==goalTile.getRowCoord() & hero.getColCoord()==goalTile.getColCoord();
	}*/

	/*class GoRight_Statement extends Statement{

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
	}*/
}
