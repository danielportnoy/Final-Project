package com.example.finalprojectapp.coderunning.snapshot;

public abstract class GameSnapshot {

	public abstract boolean equals(GameSnapshot other);
	
	public abstract GameSnapshot copy(GameSnapshot other);
}
