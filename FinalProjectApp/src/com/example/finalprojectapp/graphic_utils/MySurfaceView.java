package com.example.finalprojectapp.graphic_utils;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;

	private int fps;
	private boolean isAnimating;

	public MySurfaceView(Context context, int fps, boolean isAnimating) {
		super(context);

		this.isAnimating = isAnimating;
		this.fps = fps;

		holder = getHolder();
		holder.addCallback(this);

		gameLoopThread = new GameLoopThread(this);
	}

	public void setFps(int fps) {
		this.fps = fps;
	}

	public void setAnimating(boolean isAnimating) {
		this.isAnimating = isAnimating;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		if (gameLoopThread.getState() == Thread.State.TERMINATED) 
			gameLoopThread = new GameLoopThread(this);

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

	private int sleepInMS(){
		return Math.round(1000/fps);
	}

	// logical reset function
	public abstract void reset();

	// graphic, positions and mathematics function
	public abstract void preCalculation();

	// logical update for  animated mode function
	public abstract void updateAnimated();

	// logical update for non animated mode function
	public abstract void updateNonAnimated();

	// graphic rendering of images
	public abstract void render();

	public abstract void loadSnapshot(GameSnapshot gameSnapshot);

	// running thread
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

			mySurfaceView.preCalculation();

			while (running) {

				long StartTimeInMS= System.currentTimeMillis();

				if(!holder.getSurface().isValid())
					continue;

				Canvas c = null;

				try {
					synchronized (holder){

						if(isAnimating)
							mySurfaceView.updateAnimated();
						else
							mySurfaceView.updateNonAnimated();

						c = holder.lockCanvas();
						if(c!=null)
							mySurfaceView.draw(c);
					}
				} 
				finally {

					if (c != null)
						holder.unlockCanvasAndPost(c);
				}

				long EndTimeInMS= System.currentTimeMillis();

				long sleepTime = sleepInMS() - (EndTimeInMS - StartTimeInMS);

				if(sleepTime>0){
					try {
						sleep(sleepTime);
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public abstract boolean isStillAnimating();
}
