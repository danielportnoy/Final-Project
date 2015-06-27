package com.example.finalprojectapp.graphic_utils;

import android.graphics.Bitmap;

/**
 * Hold the data and functions needed to handle a sprite sheet image. 
 * @author daniel portnoy
 *
 */
public abstract class SpriteSheet {
	
	private Bitmap sourceBitmap;
	
	/**
	 * 
	 * @param sourceBitmap - Bitmap of the original image.
	 */
	public SpriteSheet(Bitmap sourceBitmap) {
		this.sourceBitmap = sourceBitmap;
	}

	/**
	 * Get a bitmap representing the (column , row) part of the sprite image.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return Bitmap
	 */
	public Bitmap getBitmapByCoords(int x, int y, int width, int height){
		return Bitmap.createBitmap(sourceBitmap, x, y, width, height);
	}
	
	public Bitmap getSourceBitmap() {
		return sourceBitmap;
	}


}
