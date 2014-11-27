package logics.game;

public class Board {

	private Tile[][] tiles;

	public Board(int boardRows, int boardCols/*, int heroRow, int heroCol, int goalRow, int goalCol*/) {

		tiles = new Tile[boardRows][boardCols];

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j]= new Regular_Tile(i,j);
			}
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}
}
