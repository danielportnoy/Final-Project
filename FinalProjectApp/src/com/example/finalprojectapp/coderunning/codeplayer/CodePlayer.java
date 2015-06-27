package com.example.finalprojectapp.coderunning.codeplayer;

import android.app.Activity;
import android.app.AlertDialog;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.testcase.TestCase;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.utilities.Android_Utils;

/**
 * Manages the playing of Snapshots, refreshing the game view, etc...
 * @author daniel portnoy
 *
 */
public class CodePlayer {

	private Activity activity; 
	//private ImageButton playPauseButton;

	private int numberOfSnapshots;
	private int currentSnapshotNumber;

	private static boolean isPlaying;
	private static boolean lastIsPlaying;

	private PlayerThread playerTheard;

	private int cps;

	private TestCase testCase;

	private MySurfaceView gameView;

	/**
	 * @param activity - Current showing activity.
	 * @param cps - command per second rate.
	 * @param testCase - current test case to show.
	 * @param gameView - the game view.
	 */
	public CodePlayer(Activity activity, /*ImageButton imageButton*/ int cps, TestCase testCase, MySurfaceView gameView) {

		numberOfSnapshots = testCase.getSnapshots().size();

		this.activity = activity;
		//this.playPauseButton = imageButton;

		this.cps = cps;

		this.testCase = testCase;

		this.gameView = gameView;

		currentSnapshotNumber = 0;

		isPlaying = false;
		//imageButton.setText("Play");
	}

	/**
	 * Get the actual sleep time.
	 * @return sleep time in milliseconds.
	 */
	private int sleepInMS() {
		return Math.round(1000/cps);
	}

	/**
	 * Start the player thread.
	 */
	public void start() {
		playerTheard = new PlayerThread();
		playerTheard.setRunning(true);
		playerTheard.start();
	}

	/**
	 * Destroy the player thread.
	 */
	public void destroy(){
		
		if(playerTheard == null)
			return;

		boolean retry = true;

		playerTheard.setRunning(false);

		while (retry) {

			try {
				playerTheard.join();
				retry = false;
			} 
			catch (InterruptedException e) {
			}
		}
	}

	/**
	 * 
	 * @return whether the player is playing.
	 */
	public boolean isPlaying() {
		return isPlaying;
	}

	/**
	 * Display the current Snapshot on the screen.
	 */
	public void display() {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {

				long StartTimeInMS = System.currentTimeMillis();

				LevelManager.getInstance().refrashRunningScreen(testCase.getSnapshots().get(currentSnapshotNumber));

				//if last snapshot
				if(currentSnapshotNumber == numberOfSnapshots - 1){
					isPlaying = false;
					//playPauseButton.setText("Play");
					destroy();
					EndGame();
				}

				long EndTimeInMS = System.currentTimeMillis();

				// calculate the needed sleep time and set the player to sleep.
				
				long sleepTime = sleepInMS() - (EndTimeInMS - StartTimeInMS);

				if(playerTheard != null){
					playerTheard.setSleepTime(sleepTime);
					playerTheard.setSleep(true);
				}
			}
		});			

	}

	/**
	 * Set current Snapshot to be the first one.
	 */
	public void startSnapshot() {
		currentSnapshotNumber = 0;
		display();
	}

	/**
	 * Set current Snapshot to be the previous one.
	 */
	public void prevSnapshot() {
		if(currentSnapshotNumber > 0)
			currentSnapshotNumber--;
		display();
	}

	/**
	 * Set current Snapshot to be the next one.
	 */
	public void nextSnapshot() {
		if(currentSnapshotNumber < numberOfSnapshots - 1)
			currentSnapshotNumber++;
		display();
	}

	/**
	 * Set current Snapshot to be the last one.
	 */
	public void endSnapshot() {
		currentSnapshotNumber = numberOfSnapshots - 1;
		display();
	}	

	/**
	 * Toggles between playing and not playing.
	 */
	public void togglePlay() {
		isPlaying = !isPlaying;

		if(isPlaying()){
			//playPauseButton.setText("Play");
			start();
		}
		else{
			//playPauseButton.setText("Pause");
			destroy();
		}
	}

	/**
	 * Handles the presentation of the end game.
	 */
	public void EndGame(){

		AlertDialog.Builder builder = null;

		if(testCase.getException() != null)		
			
			// Display the exception text in a dialog. 
			builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_LOSS_TITLE_TEXT, Constants.LEVEL_END_LOSS_HEADER_TEXT,
					testCase.getException().getMessage(), Constants.LEVEL_END_LOSS_POSITIVE_TEXT , false);
		else{
			if(LevelManager.getInstance().checkWin(testCase)){
				
				// Display win text in a dialog.
				builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_WIN_TITLE_TEXT, Constants.LEVEL_END_WIN_HEADER_TEXT,
						Constants.LEVEL_END_WIN_TEXT, Constants.LEVEL_END_WIN_POSITIVE_TEXT , true);
			}
			else{
				
				// Display loss text in a dialog.
				builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_LOSS_TITLE_TEXT, Constants.LEVEL_END_LOSS_HEADER_TEXT,
						Constants.LEVEL_END_LOSS_TEXT, Constants.LEVEL_END_LOSS_POSITIVE_TEXT , false);
			}
		}

		builder.create().show();
	}

	/**
	 * A thread class that 'plays' the Screenshots one after the other.
	 * @author daniel portnoy
	 *
	 */
	class PlayerThread extends Thread{

		private boolean sleep = false;

		private long sleepTime = 0;

		private boolean isRunning = false;

		public void setSleep(boolean sleep) {
			this.sleep = sleep;
		}

		public void setSleepTime(long sleepTime) {
			this.sleepTime = sleepTime;
		}

		public void setRunning(boolean isRunning) {
			this.isRunning = isRunning;
		}

		@Override
		public void run() {

			while (isRunning) {

				if(isPlaying && !gameView.isStillAnimating()){
					lastIsPlaying = isPlaying;
					isPlaying = false;
					nextSnapshot();
				}

				if(sleep){
					sleep = false;

					try {
						sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					isPlaying = lastIsPlaying;
				}
			}
		}
	}
}
