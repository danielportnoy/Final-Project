package final_2.logics.game.level1;

import final_2.logics.game.level1.infrastructure.Board;
import final_2.logics.game.level1.infrastructure.Coordinate;
import final_2.logics.game.level1.infrastructure.Goal_Tile;
import final_2.logics.game.level1.infrastructure.Hero;
import final_2.logics.game.level1.infrastructure.Wall_Tile;
import final_2.logics.game.level1.infrastructure.infoBundle_Level1;
import final_2.logics.game.scenario.Scenario;
import final_2.logics.grammar.statement.Block_Stmt;
import final_2.main.ScopeTable;

public class Level1 implements Scenario {

	private Board board;
	private Hero hero;

	private Goal_Tile goalTile;

	private infoBundle_Level1 infoBundle;

	public Level1(Level1 other) {
		this.board=new Board(other.getBoard());
		this.hero=new Hero(other.getHero());
		this.goalTile = new Goal_Tile(other.getGoalTile());
	}

	public Level1(int boardHeight, int boardLength, String heroLook,Coordinate heroLocation, Coordinate goalLocation) {

		this.board=new Board(boardHeight, boardLength);
		this.hero=new Hero(heroLook, heroLocation);
		this.goalTile = new Goal_Tile(goalLocation);
		this.infoBundle = new infoBundle_Level1(boardHeight, boardLength, heroLook, heroLocation, goalLocation);

		initialize();
	}

	private void initialize() {
		board.setTile(goalTile);

		Wall_Tile wall = new Wall_Tile(new Coordinate(0,0));
		board.setTile(wall);
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

	public Goal_Tile getGoalTile() {
		return goalTile;
	}

	public void setGoalTile(Goal_Tile goalTile) {
		this.goalTile = goalTile;
	}

	public infoBundle_Level1 getInfoBundle() {
		return infoBundle;
	}

	public void setInfoBundle(infoBundle_Level1 infoBundle) {
		this.infoBundle = infoBundle;
	}

	@Override
	public void RunCode(Block_Stmt codeBlock) {
		codeBlock.run();
	}

	@Override
	public boolean checkWin() {
		return hero.getLocation().equals(goalTile.getLocation());
	}

	@Override
	public void reset() {

		board = new Board(getInfoBundle().getBoardHeight(), getInfoBundle().getBoardLength());
		hero = new Hero(getInfoBundle().getHeroLook(), getInfoBundle().getHeroLocation());
		goalTile = new Goal_Tile(getInfoBundle().getGoalLocation());

		initialize();
		
		ScopeTable.reset();
	}

	/*class GoRightStmt extends Stmt {

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
	}*/

}
