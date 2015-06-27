package com.example.finalprojectapp.graphic_utils;

import android.graphics.Bitmap;

/**
 * Hold the data and functions needed to handle a sprite image. 
 * @author daniel portnoy
 *
 */
public abstract class Sprite extends SpriteSheet{

	protected int sourceRows; 
	protected int sourceCols;

	protected int currentRow;
	protected int currentCol;

	private int width;
	private int height;

	/**
	 * @param sourceBitmap - Bitmap of the original image.
	 * @param sourceRows - Number of rows in the sprite.
	 * @param sourceCols - Number of columns in the sprite.
	 * @param currentRow - Current Y position to show.
	 * @param currentCol - Current X position to show.
	 */
	public Sprite(Bitmap sourceBitmap, int sourceRows, int sourceCols, int currentRow, int currentCol) {
		super(sourceBitmap);

		this.sourceRows = sourceRows;
		this.sourceCols = sourceCols;

		this.currentRow = currentRow;
		this.currentCol = currentCol;

		width = sourceBitmap.getWidth()/sourceCols;
		height = sourceBitmap.getHeight()/sourceRows;
	}

	/**
	 * Update the current row \ column.
	 */
	public abstract void update();

	public int getFrameNumber() {
		return currentCol + 1;
	}

	public boolean isLooped(){
		return getFrameNumber() >= sourceCols;
	}

	/**
	 * Get a bitmap representing the (column , row) part of the sprite image.
	 * @param col
	 * @param row
	 * @return Bitmap
	 */
	public Bitmap getBitmapByCoords(int col, int row){

		int srcX = col * width;
		int srcY = row * height;

		return Bitmap.createBitmap(getSourceBitmap(), srcX, srcY, width, height);
	}

	public Bitmap getCurrentBitmap(){
		return getBitmapByCoords(currentCol, currentRow);
	}

}
