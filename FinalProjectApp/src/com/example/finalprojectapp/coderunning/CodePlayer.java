package com.example.finalprojectapp.coderunning;

import com.example.finalprojectapp.LevelManager;

public class CodePlayer {
	
	private CodeRunningActivity cra; 

	private int numberOfSnapshots;
	private int currentSnapshotNumber;

	private boolean isPlaying;

	private PlayerThread playerTheard;
	
	public CodePlayer(int numberOfSnapshots, CodeRunningActivity cra) {
		this.numberOfSnapshots = numberOfSnapshots;
		this.cra = cra;
		
		currentSnapshotNumber = 0;
		
		isPlaying = false;
		
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
		cra.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				LevelManager.getInstance().getCodeRunningManager().refresh(currentSnapshotNumber);
			}
		});
	}

	public void startSnapshot() {
		currentSnapshotNumber = 0;
		display();
	}

	public void prevSnapshot() {
		if(currentSnapshotNumber > 0){
			currentSnapshotNumber--;
			display();
		}
	}

	public void nextSnapshot() {
		if(currentSnapshotNumber < numberOfSnapshots - 1){
			currentSnapshotNumber++;
			display();
		}
	}
	
	public void endSnapshot() {
		currentSnapshotNumber = numberOfSnapshots - 1;
		display();
	}	

	public void togglePlay() {
		isPlaying = !isPlaying;
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

				try {
					sleep(500);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
