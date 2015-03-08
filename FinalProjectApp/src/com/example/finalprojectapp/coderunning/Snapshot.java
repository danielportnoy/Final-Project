package com.example.finalprojectapp.coderunning;

import java.util.List;

import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.Scope;

public class Snapshot {

	public Node currentNode;

	public VarValues values;

	public GameSnapshot gameSnapshot;

	public Snapshot(Node currentNode, VarValues values, GameSnapshot gameSnapshot) {
		this.currentNode = currentNode;
		this.values = new VarValues(values);
		this.gameSnapshot = gameSnapshot;
		
		List<String> ids = Scope.getIdentifiersRecursive(currentNode.getParent(), currentNode.getOrder());
		values.removeExtra(ids);
		
	}



}
