package com.example.finalprojectapp.gamescreenlogic.level1;

import java.util.List;

import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolder;
import com.example.finalprojectapp.gamescreenlogic.Scenario;
import com.example.finalprojectapp.gamescreenlogic.level1.infrastructure.Board;
import com.example.finalprojectapp.gamescreenlogic.level1.infrastructure.Coordinate;
import com.example.finalprojectapp.gamescreenlogic.level1.infrastructure.Goal_Tile;
import com.example.finalprojectapp.gamescreenlogic.level1.infrastructure.Hero;
import com.example.finalprojectapp.gamescreenlogic.level1.infrastructure.Wall_Tile;
import com.example.finalprojectapp.gamescreenlogic.level1.infrastructure.infoBundle_Level1;

public class Level1 extends Scenario {
	
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
	public void RunCode(List<PlaceHolder> codeLines) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkWin() {
		// TODO Auto-generated method stub
		//return hero.getLocation().equals(goalTile.getLocation());
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		/*board = new Board(getInfoBundle().getBoardHeight(), getInfoBundle().getBoardLength());
		hero = new Hero(getInfoBundle().getHeroLook(), getInfoBundle().getHeroLocation());
		goalTile = new Goal_Tile(getInfoBundle().getGoalLocation());

		initialize();
		
		ScopeTable.reset();*/
	}

	/*@Override
	public List<Pattern> getSpecialPatterns() {
		List<Pattern> patterns = new ArrayList<Pattern>();
		
		patterns.add(new GoRight_Ptrn(this));
		
		return patterns;
	}
*/
}
