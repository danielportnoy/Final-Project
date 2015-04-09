package com.example.finalprojectapp.graphic_utils.maze;

import android.graphics.Bitmap;

import com.example.finalprojectapp.graphic_utils.Sprite;

public class HeroSprite extends Sprite {
	
	public static enum heroDirection{	
		Stand,
		Up,
		Left,
		Down,
		Right
	}
	
	private heroDirection direction;
	
	public HeroSprite(Bitmap sourceBitmap, int sourceRows, int sourceCols,int currentRow, int currentCol) {
		super(sourceBitmap, sourceRows, sourceCols, currentRow, currentCol);
		reset();
	}
	
	public int getNumOfFrames() {
		return sourceCols;
	}
	
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

}
