package com.example.finalprojectapp.graphic_utils;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;

	private int sleepTime_mm;

	public MySurfaceView(Context context, int fps) {
		super(context);

		sleepTime_mm = Math.round(1000/fps);

		holder = getHolder();
		holder.addCallback(this);

		gameLoopThread = new GameLoopThread(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		if (gameLoopThread.getState() == Thread.State.TERMINATED) {
			gameLoopThread = new GameLoopThread(this);
			gameLoopThread.setRunning(true);
			gameLoopThread.start();
		}
		else {
			gameLoopThread.setRunning(true);
			gameLoopThread.start();
		}
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

	// logical reset function
	public abstract void reset();

	// logical update function
	public abstract void update();

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

			while (running) {

				if(!holder.getSurface().isValid())
					continue;

				Canvas c = null;

				try {
					synchronized (holder){

						mySurfaceView.update();

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
					sleep(sleepTime_mm);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
