package com.example.finalprojectapp.graphic_utils;

import com.example.finalprojectapp.coderunning.snapshot.GameSnapshot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * A view to display the game on.
 * @author daniel portnoy
 *
 */
public abstract class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;

	protected int fps;
	protected boolean isAnimating;

	/**
	 * 
	 * @param context - Context of the application.
	 * @param fps - Frame per second rate.
	 * @param isAnimating - Flag that tells if there need to be animation.
	 */
	public MySurfaceView(Context context, int fps, boolean isAnimating) {
		super(context);

		this.isAnimating = isAnimating;
		this.fps = fps;

		holder = getHolder();
		holder.addCallback(this);

		//make transtparant
		setZOrderOnTop(true);
		holder.setFormat(PixelFormat.TRANSPARENT);

		gameLoopThread = new GameLoopThread(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		// Re create the game loop thread.
		if (gameLoopThread.getState() == Thread.State.TERMINATED) 
			gameLoopThread = new GameLoopThread(this);

		// Start the game loop thread.
		gameLoopThread.setRunning(true);
		gameLoopThread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		boolean retry = true;

		gameLoopThread.setRunning(false);

		// Loop until the game loop is destroyed.
		while (retry) {

			try {
				gameLoopThread.join();
				retry = false;
			} 
			catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Get the actual sleep time.
	 * @return sleep time in milliseconds.
	 */
	private int sleepInMS(){
		return Math.round(1000/fps);
	}

	/**
	 * Logical reset function
	 */
	public abstract void reset();

	/**
	 * Graphic, positions and mathematics function
	 */
	public abstract void preCalculation();

	/**
	 * Logical update for  animated mode function
	 */
	public abstract void updateAnimated();

	/**
	 * Logical update for non animated mode function
	 */
	public abstract void updateNonAnimated();

	/**
	 * Graphic rendering of images
	 */
	public abstract void render();

	/**
	 * Loads the current GameSnapshot object to the system.
	 * @param gameSnapshot - GameSnapshot object.
	 */
	public abstract void loadSnapshot(GameSnapshot gameSnapshot);

	/**
	 * A thread class that updates and re-draws the screen.
	 * @author daniel portnoy
	 *
	 */
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
						
						// Update the logical side.

						if(isAnimating)
							mySurfaceView.updateAnimated();
						else
							mySurfaceView.updateNonAnimated();

						c = holder.lockCanvas();
						
						// Update the graphical side.
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
