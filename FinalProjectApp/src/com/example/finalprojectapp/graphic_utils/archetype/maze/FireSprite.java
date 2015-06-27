package com.example.finalprojectapp.graphic_utils.archetype.maze;

import android.graphics.Bitmap;

import com.example.finalprojectapp.graphic_utils.Sprite;

/**
 * Hold the data and functions needed to handle a fire animation image. 
 * @author daniel portnoy
 *
 */
public class FireSprite extends Sprite{

	/**
	 * @param sourceBitmap - Bitmap of the fire animation sprite image.
	 * @param sourceRows - Number of rows in the sprite.
	 * @param sourceCols - Number of columns in the sprite.
	 * @param currentRow - Current Y position to show.
	 * @param currentCol - Current X position to show.
	 */
	public FireSprite(Bitmap sourceBitmap, int sourceRows, int sourceCols,int currentRow, int currentCol) {
		super(sourceBitmap, sourceRows, sourceCols, currentRow, currentCol);
		reset();
	}

	/**
	 * Resets the data.
	 */
	public void reset() {
		currentCol = 0;
		currentRow = 0;
	}

	@Override
	public void update() {

		currentCol++;

		if(currentCol == sourceCols)
		{
			currentCol = 0;
			currentRow++;

			if(currentRow == sourceRows)
				currentRow = 0;
		}
	}
};