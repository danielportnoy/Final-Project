package com.example.finalprojectapp.graphic_utils.archetype.maze;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import com.example.finalprojectapp.graphic_utils.SpriteSheet;
import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype.BoardTilesTypesEnum;

public class BoardSpriteSheet extends SpriteSheet{

	private final int TileWidth = 64, TileHeight = 64;
	private int boardWidth, boardHeight;
	private int rows, columns;

	private Bitmap rockys_stones = getBitmapByCoords(0, 352, TileWidth, TileHeight);
	private Bitmap grass = getBitmapByCoords(384, 32, TileWidth, TileHeight);
	private Bitmap round_bricks_gray = getBitmapByCoords(256, 32, TileWidth, TileHeight);

	private Bitmap[][] boardMatrix;


	public BoardSpriteSheet(int rows, int columns, Bitmap boardMap, BoardTilesTypesEnum[][] boardTiles) {
		super(boardMap);

		this.rows = rows;
		this.columns = columns;

		boardWidth = TileWidth*columns;
		boardHeight = TileHeight*rows;

		boardMatrix = new Bitmap[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				switch (boardTiles[i][j]) {
				case Rockys_stones:
					boardMatrix[i][j] = rockys_stones;
					break;
				case Grass:
					boardMatrix[i][j] = grass;
					break;
				case Round_bricks_gray:
					boardMatrix[i][j] = round_bricks_gray;
					break;

				default:
					break;
				}
			}
		}
	}

	public Bitmap getBitmap() {

		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap board = Bitmap.createBitmap(boardWidth, boardHeight, conf);

		Canvas canvas = new Canvas(board);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				canvas.drawBitmap(boardMatrix[i][j], j*TileWidth, i*TileHeight, null);
			}
		}

		return board;
	}
}