package final_2.logics.game.level1;

import final_2.logics.game.level1.infrastructure.Board;
import final_2.logics.game.level1.infrastructure.Coordinate;
import final_2.logics.game.level1.infrastructure.Hero;
import final_2.logics.game.level1.infrastructure.Tile;
import final_2.logics.game.level1.infrastructure.Wall_Tile;
import final_2.logics.game.scenario.Scenario;
import final_2.logics.grammar.statement.Block_Stmt;
import final_2.logics.grammar.statement.Stmt;

public class Level1 implements Scenario {

	private Board board;
	private Hero hero;
	
	private Tile goalTile;

	public Level1(int boardHeight, int boardLength, String heroLook,Coordinate heroLocation,String goalMark, Coordinate goalLocation) {
		this.board=new Board(boardHeight, boardLength);
		this.hero=new Hero(heroLook, heroLocation);
		
		this.goalTile = this.board.getTileAtLoaction(goalLocation);
		
		Coordinate wallLocation = new Coordinate(0, 0);
		board.setTileAtLoaction(new Wall_Tile(wallLocation),wallLocation);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
	
	public Tile getGoalTile() {
		return goalTile;
	}

	public void setGoalTile(Tile goalTile) {
		this.goalTile = goalTile;
	}

	@Override
	public void RunCode(Block_Stmt codeBlock) {
		codeBlock.run();
	}

	@Override
	public boolean checkWin() {
		return hero.getLocation().equals(goalTile.getLocation());
	}
	
	class GoRightStmt extends Stmt {

		@Override
		public void run() {
			
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
		public String toString() {
			return "goRight();\n";
		}	
	}
	
	public GoRightStmt GoRightStmt(){
		return new GoRightStmt();
	}

}
