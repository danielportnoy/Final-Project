package com.example.finalprojectapp.coderunning.snapshot;

import java.util.List;

import com.example.finalprojectapp.coderunning.varvalues.VarValues;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.Scope;

public class Snapshot {

	public Node currentNode;

	public VarValues values;

	public GameSnapshot gameSnapshot;
	
	public Snapshot(Snapshot other) {
		
		this.currentNode = other.currentNode;
		this.values = new VarValues(other.values);
		this.gameSnapshot = other.gameSnapshot;

		List<String> ids = Scope.getPrevIdentifiers(currentNode.getParent(), currentNode.getOrder());
		values.removeExtra(ids);
	}

	public Snapshot(Node currentNode, VarValues values, GameSnapshot gameSnapshot) {
		this.currentNode = currentNode;
		this.values = new VarValues(values);
		this.gameSnapshot = gameSnapshot;

		List<String> ids = Scope.getPrevIdentifiers(currentNode.getParent(), currentNode.getOrder());
		values.removeExtra(ids);
	}

	public boolean equals(Snapshot other) {
		return currentNode.equals(other.currentNode) && values.equals(other.values) && gameSnapshot.equals(other.gameSnapshot);
	}

}
