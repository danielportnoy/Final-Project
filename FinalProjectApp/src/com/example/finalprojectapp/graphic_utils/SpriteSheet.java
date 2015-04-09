package com.example.finalprojectapp.graphic_utils;

import android.graphics.Bitmap;

public abstract class SpriteSheet {
	
	private Bitmap sourceBitmap;
	
	public SpriteSheet(Bitmap sourceBitmap) {
		this.sourceBitmap = sourceBitmap;
	}

	public Bitmap getBitmapByCoords(int x, int y, int width, int height){
		return Bitmap.createBitmap(sourceBitmap, x, y, width, height);
	}
	
	public Bitmap getSourceBitmap() {
		return sourceBitmap;
	}


}
