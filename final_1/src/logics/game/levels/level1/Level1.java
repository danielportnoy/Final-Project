package logics.game.levels.level1;

import graphics.Graphics_Control;
import logics.game.Coordinate;
import logics.game.Scenario;
import logics.game.Tile;
import logics.game.Wall_Tile;
import logics.grammar.statements.Block;
import logics.grammar.statements.Statement;
import logics.visitor.CodeVisitor;
import logics.visitor.CodeVisitorElement;

public class Level1 extends Scenario {

	public Level1(int boardHeight, int boardLength, String heroLook,Coordinate heroLocation,String goalMark, Coordinate goalLocation) {
		super(boardHeight, boardLength, heroLook, heroLocation);

		goalTile = board.getTileAtLoaction(goalLocation);
		goalTile.setMark(goalMark);

		Coordinate wallLocation = new Coordinate(0, 0);
		board.setTileAtLoaction(new Wall_Tile(wallLocation),wallLocation);
	}

	public Tile goalTile;


	/*@Override
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
	}*/

	@Override
	public boolean isWin() {
		return hero.getLocation().equals(goalTile.getLocation());
	}

	class GoRight_Statement extends Statement {

		public GoRight_Statement() {
			super("Go right");
		}

		@Override
		public Void accept(CodeVisitor v) {
			((Level1CodeVisitorElement)v).visit(this);
			return null;
		}

		/*@Override
		public Type Run() {
			hero.setColCoord(hero.getColCoord()+1);
			return null;
		}*/

	}


	class Level1CodeVisitorElement extends CodeVisitorElement {

		void visit(GoRight_Statement s){

			int cuurCol = hero.getLocation().getColCoord();

			Coordinate targetCoord = new Coordinate(hero.getLocation());
			targetCoord.setColCoord(cuurCol+1);
			
			Tile targetTile = board.getTileAtLoaction(targetCoord);

			if(targetTile==null)
				return;
			else if(!targetTile.isHabitable())
				return;

			hero.getLocation().setColCoord(cuurCol+1);	
		}

		@Override
		public void visit(Block block) {
			Graphics_Control.drawBoard(board, hero);

			for (Statement statement : block.getStatements()) {
				statement.accept(this);

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
	}

	public Statement GoRightStatement() {
		return new GoRight_Statement();
	}

	@Override
	public void RunCode(Block codeBlock) {
		codeBlock.accept(new Level1CodeVisitorElement());
	}


}
