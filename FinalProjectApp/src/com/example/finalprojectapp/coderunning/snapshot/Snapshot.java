package com.example.finalprojectapp.coderunning.snapshot;

import java.util.List;

import com.example.finalprojectapp.coderunning.varvalues.VarValues;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.Scope;

/**
 * Holds the data of the entire system.
 * @author daniel portnoy
 *
 */
public class Snapshot {

	public Node currentNode;

	public VarValues values;

	public GameSnapshot gameSnapshot;
	
	/**
	 * Copies the other Snapshot.
	 * @param other
	 */
	public Snapshot(Snapshot other) {
		
		this.currentNode = other.currentNode;
		this.values = new VarValues(other.values);
		this.gameSnapshot = other.gameSnapshot;

		// remove extra identifiers from the VarValues.
		List<String> ids = Scope.getPrevIdentifiers(currentNode.getParent(), currentNode.getOrder());
		values.removeExtra(ids);
	}

	/**
	 * @param currentNode - Node that took the Snapshot.
	 * @param values - Current system variables and their values.
	 * @param gameSnapshot -  Current game system status. 
	 */
	public Snapshot(Node currentNode, VarValues values, GameSnapshot gameSnapshot) {
		this.currentNode = currentNode;
		this.values = new VarValues(values);
		this.gameSnapshot = gameSnapshot;

		// remove extra identifiers from the VarValues.
		List<String> ids = Scope.getPrevIdentifiers(currentNode.getParent(), currentNode.getOrder());
		this.values.removeExtra(ids);
	}

	/**
	 * Comparing to another Snapshot object. 
	 * @param other - Snapshot for comparison.
	 * @return if they are equal.
	 */
	public boolean equals(Snapshot other) {
		return currentNode.equals(other.currentNode) && values.equals(other.values) && gameSnapshot.equals(other.gameSnapshot);
	}

}
