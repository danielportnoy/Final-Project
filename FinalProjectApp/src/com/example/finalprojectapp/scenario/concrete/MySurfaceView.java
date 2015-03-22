package com.example.finalprojectapp.scenario.concrete;

import com.example.finalprojectapp.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private int x=0 , y=0;
	private int currentFrame = 0;

	private Bitmap board;
	private Bitmap[] hero;

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;

	public MySurfaceView(Context context) {
		super(context);

		board = BitmapFactory.decodeResource(getResources(),R.drawable.level1_background);

		hero = new Bitmap[] {
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_1),
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_2),
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_3),
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_4),
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_5),
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_6),
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_7),
				BitmapFactory.decodeResource(getResources(),R.drawable.level1_hero_8)
		};

		holder = getHolder();
		holder.addCallback(this);

		gameLoopThread = new GameLoopThread(this);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		Bitmap scaledBoard = Bitmap.createScaledBitmap(board, getWidth(), getHeight(), true);

		Bitmap scaledHero[] = new Bitmap[]{
				Bitmap.createScaledBitmap(hero[0], getWidth()/6, getHeight()/6, true),
				Bitmap.createScaledBitmap(hero[1], getWidth()/6, getHeight()/6, true),
				Bitmap.createScaledBitmap(hero[2], getWidth()/6, getHeight()/6, true),
				Bitmap.createScaledBitmap(hero[3], getWidth()/6, getHeight()/6, true),
				Bitmap.createScaledBitmap(hero[4], getWidth()/6, getHeight()/6, true),
				Bitmap.createScaledBitmap(hero[5], getWidth()/6, getHeight()/6, true),
				Bitmap.createScaledBitmap(hero[6], getWidth()/6, getHeight()/6, true),
				Bitmap.createScaledBitmap(hero[7], getWidth()/6, getHeight()/6, true),
		};

		canvas.drawBitmap(scaledBoard, 0, 0, null);

		if (currentFrame < scaledHero.length)		
			canvas.drawBitmap(scaledHero[currentFrame], (x-1)*(getWidth()/6) + currentFrame*(getWidth()/6)/scaledHero.length, y*(getHeight()/6), null);
		else
			canvas.drawBitmap(scaledHero[0], x*(getWidth()/6), y*(getHeight()/6), null);

		currentFrame++;

	}

	public void a(int x , int y){
		this.x=x;
		this.y=y;

		currentFrame = 0;

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameLoopThread.setRunning(true);
		gameLoopThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		boolean retry = true;

		gameLoopThread.setRunning(false);

		while (retry) {

			try {

				gameLoopThread.join();
				retry = false;
			} 
			catch (InterruptedException e) {
			}
		}
	}

	class GameLoopThread extends Thread{

		private MySurfaceView mySurfaceView = null;

		private boolean running = false;

		public GameLoopThread(MySurfaceView mySurfaceView) {
			this.mySurfaceView = mySurfaceView;
		}

		public void setRunning(boolean run) {
			running = run;
		}

		@Override
		public void run() {

			while (running) {

				if(!holder.getSurface().isValid())
					continue;

				Canvas c = null;

				try {
					synchronized (holder){
						c = holder.lockCanvas();

						if(c!=null)
							mySurfaceView.draw(c);
					}
				} 
				finally {

					if (c != null)
						holder.unlockCanvasAndPost(c);
				}

				try {
					sleep(80);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
