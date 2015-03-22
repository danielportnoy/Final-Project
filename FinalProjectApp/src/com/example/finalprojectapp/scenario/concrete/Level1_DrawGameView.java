package com.example.finalprojectapp.scenario.concrete;

import com.example.finalprojectapp.DrawGameView;
import com.example.finalprojectapp.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class Level1_DrawGameView extends DrawGameView {

	private int x=0 , y=0;

	Bitmap board;
	Bitmap hero;

	public Level1_DrawGameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		board = BitmapFactory.decodeResource(getResources(),R.drawable.level1_background);
		hero = BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_1);

		/*Bitmap temp = BitmapFactory.decodeResource(getResources(),R.drawable.level1_background);
		board=	Bitmap.createScaledBitmap(temp, getWidth(), getHeight(), true);*/
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (canvas != null) {

			Bitmap scaledBoard = Bitmap.createScaledBitmap(board, getWidth(), getHeight(), true);
			Bitmap scaledHero = Bitmap.createScaledBitmap(hero, getWidth()/6, getHeight()/6, true);
			
			canvas.drawBitmap(scaledBoard, 0, 0, null);
			canvas.drawBitmap(scaledHero, x*(getWidth()/6), y*(getHeight()/6), null);

		}

	}

	public void a(int x , int y){
		this.x=x;
		this.y=y;
	}

}
