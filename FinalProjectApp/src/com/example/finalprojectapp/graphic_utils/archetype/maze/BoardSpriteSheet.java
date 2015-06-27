package com.example.finalprojectapp.graphic_utils.archetype.maze;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import com.example.finalprojectapp.graphic_utils.SpriteSheet;
import com.example.finalprojectapp.scenario.archetype.MazeScenarioArchetype.BoardTilesTypesEnum;

/**
 * SpriteSheet that handles the MazeScenarioArchetype board graphic visualization.
 * @author daniel portnoy
 *
 */
public class BoardSpriteSheet extends SpriteSheet{

	private final int TILE_WIDTH = 64, TILE_HEIGHT = 64;
	private int boardWidth, boardHeight;
	private int rows, columns;

	private Bitmap rockys_stones = getBitmapByCoords(0, 352, TILE_WIDTH, TILE_HEIGHT);
	private Bitmap grass = getBitmapByCoords(384, 32, TILE_WIDTH, TILE_HEIGHT);
	private Bitmap round_bricks_gray = getBitmapByCoords(256, 32, TILE_WIDTH, TILE_HEIGHT);

	private List<List<Bitmap>> boardMatrix;

	/**
	 * 
	 * @param rows - Number of rows in the sprite.
	 * @param columns - Number of columns in the sprite.
	 * @param boardMap - Bitmap of the SpriteSheet image.
	 * @param boardTiles - Logical representation of the board.
	 */
	public BoardSpriteSheet(int rows, int columns, Bitmap boardMap, List<List<BoardTilesTypesEnum>> boardTiles) {
		super(boardMap);

		this.rows = rows;
		this.columns = columns;

		boardWidth = TILE_WIDTH*columns;
		boardHeight = TILE_HEIGHT*rows;

		boardMatrix = new ArrayList<List<Bitmap>>();

		// Load the bitmaps to the boardMatrix.
		
		for (int i = 0; i < rows; i++) {
			
			List<Bitmap> bitmapRow = new ArrayList<Bitmap>();

			for (int j = 0; j < columns; j++) {
				switch (boardTiles.get(i).get(j)) {
				case Rockys_stones:
					bitmapRow.add(j, rockys_stones);
					break;
				case Grass:
					bitmapRow.add(j, grass);
					break;
				case Round_bricks_gray:
					bitmapRow.add(j, round_bricks_gray);
					break;

				default:
					break;
				}
			}
			
			boardMatrix.add(i, bitmapRow);		
		}
	}

	/**
	 * Merge the boardMatrix of bitmaps to a single image.
	 * @return Bitmap.
	 */
	public Bitmap getBitmap() {

		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap board = Bitmap.createBitmap(boardWidth, boardHeight, conf);

		Canvas canvas = new Canvas(board);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				canvas.drawBitmap(boardMatrix.get(i).get(j), j*TILE_WIDTH, i*TILE_HEIGHT, null);
			}
		}

		return board;
	}
}