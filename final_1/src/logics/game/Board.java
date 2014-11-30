package logics.game;

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

		setBoardHeight(boardRows);
		setBoardLength(boardCols);
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

	public void setTileAtLoaction(Tile tile , Coordinate location){
		tiles[location.getRowCoord()][location.getColCoord()] = tile;
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
