package logics.game.levels.level1;

import graphics.Graphics_Control;

import logics.game.Scenario;
import logics.game.Tile;
import logics.game.Wall_Tile;
import logics.grammar.statements.Block;
import logics.grammar.statements.Statement;
import logics.visitor.CodeVisitor;
import logics.visitor.CodeVisitorElement;

public class Level1 extends Scenario {

	public Tile goalTile;

	public Level1(int boardRows, int boardCols, String heroLook, int heroRow,int heroCol	, String goalMark, int goalRow, int goalCol) {
		super(boardRows, boardCols, heroLook, heroRow, heroCol);

		goalTile = board.getTiles()[goalRow][goalCol];
		goalTile.setMark(goalMark);

		board.getTiles()[0][0]=new Wall_Tile(0, 0);
	}

	@Override
	public void RunCode(Block codeBlock){

		Level1CodeVisitorElement l1v = new Level1CodeVisitorElement();
		
		Graphics_Control.drawBoard(board, hero);

		for (Statement statement : codeBlock.getStatements()) {
			statement.accept(l1v);

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

	@Override
	public boolean isWin() {
		return hero.getRowCoord()==goalTile.getRowCoord() & hero.getColCoord()==goalTile.getColCoord();
	}

	class GoRight_Statement extends Statement {

		public GoRight_Statement() {
			super("Go right");
		}

		@Override
		public void accept(CodeVisitor v) {
			((Level1CodeVisitorElement)v).visit(this);
		}

		/*@Override
		public Type Run() {
			hero.setColCoord(hero.getColCoord()+1);
			return null;
		}*/

	}

	
	class Level1CodeVisitorElement extends CodeVisitorElement {

		void visit(GoRight_Statement s){
			hero.setColCoord(hero.getColCoord()+1);
		}
	}

	public Statement GoRightStatement() {
		return new GoRight_Statement();
	}


}
