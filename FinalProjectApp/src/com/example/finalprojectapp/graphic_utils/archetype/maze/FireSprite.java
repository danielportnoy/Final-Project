package com.example.finalprojectapp.graphic_utils.archetype.maze;

import android.graphics.Bitmap;

import com.example.finalprojectapp.graphic_utils.Sprite;

public class FireSprite extends Sprite{

	public FireSprite(Bitmap sourceBitmap, int sourceRows, int sourceCols,int currentRow, int currentCol) {
		super(sourceBitmap, sourceRows, sourceCols, currentRow, currentCol);
		reset();
	}

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