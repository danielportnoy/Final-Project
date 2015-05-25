package com.example.finalprojectapp.coderunning.snapshot;

import com.example.finalprojectapp.scenario.Configuration;

public abstract class GameSnapshot {

	public abstract boolean checkWin(Configuration config);

	public abstract boolean equals(GameSnapshot other);
	
	public abstract GameSnapshot copy(GameSnapshot other);
}
