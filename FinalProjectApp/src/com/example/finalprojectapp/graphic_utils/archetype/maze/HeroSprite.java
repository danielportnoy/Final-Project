package com.example.finalprojectapp.graphic_utils.archetype.maze;

import android.graphics.Bitmap;

import com.example.finalprojectapp.graphic_utils.Sprite;

/**
 * Hold the data and functions needed to handle a hero animation image. 
 * @author daniel portnoy
 *
 */
public class HeroSprite extends Sprite {

	/**
	 * Represents the hero direction.
	 * @author daniel portnoy
	 *
	 */
	public static enum heroDirection{	
		Stand,
		Up,
		Left,
		Down,
		Right
	}

	public static final double[] ARC_ARRAY_9_FRAMES = {0, 0.6, 0.8, 0.9, 1, 0.9, 0.8, 0.6, 0};

	private heroDirection direction;

	/**
	 * @param sourceBitmap - Bitmap of the hero animation sprite image.
	 * @param sourceRows - Number of rows in the sprite.
	 * @param sourceCols - Number of columns in the sprite.
	 * @param currentRow - Current Y position to show.
	 * @param currentCol - Current X position to show.
	 */
	public HeroSprite(Bitmap sourceBitmap, int sourceRows, int sourceCols,int currentRow, int currentCol) {
		super(sourceBitmap, sourceRows, sourceCols, currentRow, currentCol);
		reset();		
	}

	public int getNumOfFrames() {
		return sourceCols;
	}

	/**
	 * Resets the data.
	 */
	public void reset() {
		setDirection(heroDirection.Stand);
		currentCol = 0;
		currentRow = 3;
	}

	public heroDirection getDirection() {
		return direction;
	}

	public void setDirection(heroDirection direction) {
		this.direction = direction;
	}

	@Override
	public void update() {

		if(direction != heroDirection.Stand){

			currentCol++;

			switch (direction) {
			case Up:
				currentRow = 0;
				break;
			case Left:
				currentRow = 1;
				break;
			case Down:
				currentRow = 2;
				break;
			case Right:
				currentRow = 3;
				break;

			default:
				break;
			}
		}
	}

	public Bitmap getFirstFrameBitmap(){
		return getBitmapByCoords(0, currentRow);
	}

}
