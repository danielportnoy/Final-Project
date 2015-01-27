package final_2.logics.game.level1.infrastructure;

public class infoBundle_Level1 {

	private int boardHeight;
	private int boardLength;
	private String heroLook;
	private Coordinate heroLocation;
	private Coordinate goalLocation;

	public infoBundle_Level1(int boardHeight, int boardLength, String heroLook,Coordinate heroLocation, Coordinate goalLocation) {
		this.boardHeight=boardHeight;
		this.boardLength=boardLength;
		this.heroLook=heroLook;
		this.heroLocation = heroLocation.makeCopy();
		this.goalLocation= goalLocation.makeCopy();
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	public int getBoardLength() {
		return boardLength;
	}

	public void setBoardLength(int boardLength) {
		this.boardLength = boardLength;
	}

	public String getHeroLook() {
		return heroLook;
	}

	public void setHeroLook(String heroLook) {
		this.heroLook = heroLook;
	}

	public Coordinate getHeroLocation() {
		return heroLocation;
	}

	public void setHeroLocation(Coordinate heroLocation) {
		this.heroLocation = heroLocation;
	}

	public Coordinate getGoalLocation() {
		return goalLocation;
	}

	public void setGoalLocation(Coordinate goalLocation) {
		this.goalLocation = goalLocation;
	}

}
