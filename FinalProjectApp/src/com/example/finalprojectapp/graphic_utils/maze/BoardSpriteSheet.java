package com.example.finalprojectapp.graphic_utils.maze;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;

import com.example.finalprojectapp.graphic_utils.SpriteSheet;

public class BoardSpriteSheet extends SpriteSheet{

	private final int TileWidth = 64, TileHeight = 64;
	private int boardWidth, boardHeight;
	private int rows, columns;

	private Bitmap blockA = getBitmapByCoords(256, 192, TileWidth, TileHeight);
	private Bitmap blockB = getBitmapByCoords(320, 192, TileWidth, TileHeight);
	private Bitmap blockC = getBitmapByCoords(384, 192, TileWidth, TileHeight);
	private Bitmap blockD = getBitmapByCoords(448, 192, TileWidth, TileHeight);

	private Bitmap[][] boardMatrix = { 
			{blockA,blockA,blockA,blockA},
			{blockB,blockB,blockB,blockB},
			{blockC,blockC,blockC,blockC},
			{blockD,blockD,blockD,blockD}
	};


	public BoardSpriteSheet(int rows, int columns, Bitmap boardMap) {
		super(boardMap);

		this.rows = rows;
		this.columns = columns;

		boardWidth = TileWidth*columns;
		boardHeight = TileHeight*rows;
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