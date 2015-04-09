package com.example.finalprojectapp.graphic_utils;

import android.graphics.Bitmap;

public abstract class Sprite extends SpriteSheet{

	protected int sourceRows; 
	protected int sourceCols;

	protected int currentRow;
	protected int currentCol;

	private int width;
	private int height;

	public Sprite(Bitmap sourceBitmap, int sourceRows, int sourceCols, int currentRow, int currentCol) {
		super(sourceBitmap);

		this.sourceRows = sourceRows;
		this.sourceCols = sourceCols;

		this.currentRow = currentRow;
		this.currentCol = currentCol;

		width = sourceBitmap.getWidth()/sourceCols;
		height = sourceBitmap.getHeight()/sourceRows;
	}

	public abstract void update();

	public int getFrameNumber() {
		return currentCol + 1;
	}

	public boolean isLooped(){
		return getFrameNumber() >= sourceCols;
	}

	public Bitmap getBitmapByCoords(int col, int row){

		int srcX = col * width;
		int srcY = row * height;

		return Bitmap.createBitmap(getSourceBitmap(), srcX, srcY, width, height);
	}

	public Bitmap getCurrentBitmap(){
		return getBitmapByCoords(currentCol, currentRow);
	}

}
