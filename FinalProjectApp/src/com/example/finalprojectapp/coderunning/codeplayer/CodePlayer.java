package com.example.finalprojectapp.coderunning.codeplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Button;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.testcase.TestCase;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.utilities.Android_Utils;

public class CodePlayer {

	private Activity activity; 
	private Button playPauseButton;

	private int numberOfSnapshots;
	private int currentSnapshotNumber;

	private static boolean isPlaying;
	private static boolean lastIsPlaying;

	private PlayerThread playerTheard;

	private int cps;

	private TestCase testCase;

	private MySurfaceView gameView;

	public CodePlayer(Activity activity, Button playPauseButton , int cps, TestCase testCase, MySurfaceView gameView) {

		numberOfSnapshots = testCase.getSnapshots().size();

		this.activity = activity;
		this.playPauseButton = playPauseButton;

		this.cps = cps;

		this.testCase = testCase;

		this.gameView = gameView;

		currentSnapshotNumber = 0;

		isPlaying = false;
		playPauseButton.setText("Play");

	}

	private int sleepInMS() {
		return Math.round(1000/cps);
	}

	public void start() {
		playerTheard = new PlayerThread();
		playerTheard.setRunning(true);
		playerTheard.start();
	}

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

	public boolean isPlaying() {
		return isPlaying;
	}

	public void display() {
		activity.runOnUiThread(new Runnable() {

			@Override
			public void run() {

				long StartTimeInMS = System.currentTimeMillis();

				LevelManager.getInstance().refrashRunningScreen(testCase.getSnapshots().get(currentSnapshotNumber));

				//if last snapshot
				if(currentSnapshotNumber == numberOfSnapshots - 1){
					isPlaying = false;
					playPauseButton.setText("Play");
					destroy();
					handleEndGame();
				}

				long EndTimeInMS = System.currentTimeMillis();

				long sleepTime = sleepInMS() - (EndTimeInMS - StartTimeInMS);

				if(playerTheard != null){
					playerTheard.setSleepTime(sleepTime);
					playerTheard.setSleep(true);
				}
			}
		});			

	}

	public void startSnapshot() {
		currentSnapshotNumber = 0;
		display();
	}

	public void prevSnapshot() {
		if(currentSnapshotNumber > 0)
			currentSnapshotNumber--;
		display();
	}

	public void nextSnapshot() {
		if(currentSnapshotNumber < numberOfSnapshots - 1)
			currentSnapshotNumber++;
		display();
	}

	public void endSnapshot() {
		currentSnapshotNumber = numberOfSnapshots - 1;
		display();
	}	

	public void togglePlay() {
		isPlaying = !isPlaying;

		if(isPlaying()){
			playPauseButton.setText("Play");
			start();
		}
		else{
			playPauseButton.setText("Pause");
			destroy();
		}
	}

	public void handleEndGame(){	// TODO

		AlertDialog.Builder builder = null;

		if(testCase.getException() != null)			
			builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_LOSS_TITLE_TEXT, Constants.LEVEL_END_LOSS_HEADER_TEXT,
					testCase.getException().getMessage(), Constants.LEVEL_END_LOSS_POSITIVE_TEXT , false);
		else{
			if(LevelManager.getInstance().checkWin(testCase))
				builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_WIN_TITLE_TEXT, Constants.LEVEL_END_WIN_HEADER_TEXT,
						Constants.LEVEL_END_WIN_TEXT, Constants.LEVEL_END_WIN_POSITIVE_TEXT , true);
			else
				builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_LOSS_TITLE_TEXT, Constants.LEVEL_END_LOSS_HEADER_TEXT,
						Constants.LEVEL_END_LOSS_TEXT, Constants.LEVEL_END_LOSS_POSITIVE_TEXT , false);
		}

		builder.create().show();
	}

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
