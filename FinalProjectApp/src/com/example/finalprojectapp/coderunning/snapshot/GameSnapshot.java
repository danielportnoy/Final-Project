package com.example.finalprojectapp.coderunning.snapshot;

public abstract class GameSnapshot {

	public abstract boolean checkWin();

	public abstract boolean checkLoss();

	public abstract boolean equals(GameSnapshot other);
}
