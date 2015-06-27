package com.example.finalprojectapp.coderunning.snapshot;

/**
 * Holds the data of the game system.
 * @author daniel portnoy
 *
 */
public abstract class GameSnapshot {

	/**
	 * Comparing to another GameSnapshot object. 
	 * @param other - Snapshot for comparison.
	 * @return if they are equal.
	 */
	public abstract boolean equals(GameSnapshot other);
	
	/**
	 * Copies the other GameSnapshot.
	 * @param other
	 */
	public abstract GameSnapshot copy(GameSnapshot other);
}
