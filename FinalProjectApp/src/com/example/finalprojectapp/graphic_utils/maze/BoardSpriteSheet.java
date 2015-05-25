package com.example.finalprojectapp.graphic_utils.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import com.example.finalprojectapp.graphic_utils.SpriteSheet;

public class BoardSpriteSheet extends SpriteSheet{

	private final int TileWidth = 64, TileHeight = 64;
	private int boardWidth, boardHeight;
	private int rows, columns;

	private Bitmap rockys_stones = getBitmapByCoords(0, 352, TileWidth, TileHeight);
	private Bitmap grass = getBitmapByCoords(384, 32, TileWidth, TileHeight);
	private Bitmap round_bricks_gray = getBitmapByCoords(256, 32, TileWidth, TileHeight);
	
	private List<List<Bitmap>> boardMatrixRandom;

	private Bitmap[][] boardMatrixStatic = { 
			{rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones},
			{grass,grass,grass,grass,grass,grass,grass,grass,grass,grass},
			{round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray},
			{rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones,rockys_stones},
			{grass,grass,grass,grass,grass,grass,grass,grass,grass,grass},
			{round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray,round_bricks_gray},
	};


	public BoardSpriteSheet(int rows, int columns, Bitmap boardMap) {
		super(boardMap);

		this.rows = rows;
		this.columns = columns;

		boardWidth = TileWidth*columns;
		boardHeight = TileHeight*rows;

		boardMatrixRandom = new ArrayList<List<Bitmap>>();
		Random rand = new Random();

		for (int i = 0; i < rows; i++) {
			
			List<Bitmap> row = new ArrayList<Bitmap>();
			
			for (int j = 0; j < columns; j++) {
				int randomNum = rand.nextInt((3 - 1) + 1) + 1;
				
				switch (randomNum) {
				case 1:
					row.add(rockys_stones);
					break;
				case 2:
					row.add(grass);
					break;
				case 3:
					row.add(round_bricks_gray);
					break;

				default:
					break;
				}
			}
			
			boardMatrixRandom.add(row);
		}
	}

	public Bitmap getBitmap() {

		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap board = Bitmap.createBitmap(boardWidth, boardHeight, conf);

		Canvas canvas = new Canvas(board);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				canvas.drawBitmap(boardMatrixStatic[i][j], j*TileWidth, i*TileHeight, null);
			}
		}
		
		/*for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				canvas.drawBitmap(boardMatrixRandom.get(i).get(j), j*TileWidth, i*TileHeight, null);
			}
		}*/

		return board;
	}
}