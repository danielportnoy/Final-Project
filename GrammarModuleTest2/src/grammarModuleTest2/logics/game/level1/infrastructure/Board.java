package grammarModuleTest2.logics.game.level1.infrastructure;

public class Board {

	private Tile[][] tiles;

	private int boardLength;
	private int boardHeight;

	public Board(int boardRows, int boardCols) {

		tiles = new Tile[boardRows][boardCols];

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j]= new Regular_Tile(new Coordinate(i, j));
			}
		}

		this.boardHeight = boardRows;
		this.boardLength = boardCols;
	}

	public Board(Board other) {

		tiles = new Tile[other.getBoardHeight()][other.getBoardLength()];

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = other.getTileAtLoaction(new Coordinate(i,j)).makeCopy();
			}
		}

		this.boardHeight = other.getBoardHeight();
		this.boardLength = other.getBoardLength();

	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public Tile getTileAtLoaction(Coordinate location){

		if( -1 < location.getRowCoord() && location.getRowCoord() < getBoardHeight()
				&& -1 < location.getColCoord() && location.getColCoord() < getBoardLength())
			return tiles[location.getRowCoord()][location.getColCoord()];

		return null;
	}

	public void setTile(Tile tile){
		int rCoord = tile.getLocation().getRowCoord();
		int cCoord = tile.getLocation().getColCoord();
		tiles[rCoord][cCoord] = tile;
	}

	public int getBoardLength() {
		return boardLength;
	}

	public void setBoardLength(int boardLength) {
		this.boardLength = boardLength;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}
}
