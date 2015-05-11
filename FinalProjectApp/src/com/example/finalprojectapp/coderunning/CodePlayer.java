package com.example.finalprojectapp.coderunning;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.Button;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.utilities.Android_Utils;

public class CodePlayer {

	private Activity activity; 
	private Button playPauseButton;

	private int numberOfSnapshots;
	private int currentSnapshotNumber;

	private boolean isPlaying;

	private PlayerThread playerTheard;

	private int cps;

	public CodePlayer(int numberOfSnapshots, Activity activity, Button playPauseButton , int cps) {
		this.numberOfSnapshots = numberOfSnapshots;
		this.activity = activity;
		this.playPauseButton = playPauseButton;

		this.cps = cps;

		currentSnapshotNumber = 0;

		isPlaying = false;
		playPauseButton.setText("Play");

	}
	
	public void setCurrentSnapshotNumber(int currentSnapshotNumber) {
		this.currentSnapshotNumber = currentSnapshotNumber;
	}
	
	public void setCps(int cps) {
		this.cps = cps;
	}
	
	public void setNumberOfSnapshots(int numberOfSnapshots) {
		this.numberOfSnapshots = numberOfSnapshots;
	}
	
	private int sleepInMM() {
		return Math.round(1000/cps);
	}

	public void start() {
		playerTheard = new PlayerThread();
		playerTheard.setRunning(true);
		playerTheard.start();
	}

	public void destroy(){

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
				LevelManager.getInstance().refrashRunningScreen(currentSnapshotNumber);

				//if last snapshot
				if(currentSnapshotNumber == numberOfSnapshots - 1){
					isPlaying = false;
					playPauseButton.setText("Play");
					handleEndGame();
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

		if(isPlaying())
			playPauseButton.setText("Play");
		else
			playPauseButton.setText("Pause");
	}

	public void handleEndGame(){	// TODO

		AlertDialog.Builder builder;

		if(LevelManager.getInstance().checkWin(currentSnapshotNumber))
			builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_WIN_TITLE_TEXT, Constants.LEVEL_END_WIN_TEXT, Constants.LEVEL_END_WIN_POSITIVE_TEXT , true);
		else
			builder = Android_Utils.getEndGameDialog(activity, Constants.LEVEL_END_LOSS_TITLE_TEXT, Constants.LEVEL_END_LOSS_TEXT, Constants.LEVEL_END_LOSS_POSITIVE_TEXT , false);

		builder.create().show();
	}

	class PlayerThread extends Thread{

		private boolean isRunning = false;

		public void setRunning(boolean isRunning) {
			this.isRunning = isRunning;
		}

		@Override
		public void run() {

			while (isRunning) {

				if(isPlaying)
					nextSnapshot();
				
				Log.d("aaa", isPlaying +"");

				try {
					sleep(sleepInMM());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
