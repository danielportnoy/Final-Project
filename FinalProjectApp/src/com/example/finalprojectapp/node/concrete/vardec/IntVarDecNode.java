package com.example.finalprojectapp.node.concrete.vardec;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;

public class IntVarDecNode extends Node {

	private String identifier;
	private Node initialValue;

	public IntVarDecNode(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public List<CodePart> getCodeParts() {

		List<CodePart> res = new ArrayList<CodePart>();

		res.add(new CodePart(false, false, "int "+ identifier, null));

		if(initialValue == null){
			res.add(new CodePart(false, false, ";", null));
			res.add(new CodePart(false, false, null, new InitialValueSetter(this)));
		}
		else{
			res.add(new CodePart(false, false, "=", null));
			res.addAll(initialValue.getCodeParts());
			res.add(new CodePart(false, false, ";", null));
		}

		return res;
	}

	@Override
	public ReturnObject run() {

		if(initialValue == null)
			LevelManager.getInstance().getCodeRunningManager().putInt(identifier, 0);
		else{
			LevelManager.getInstance().getCodeRunningManager().takeSnapshot(initialValue);	
			LevelManager.getInstance().getCodeRunningManager().putInt(identifier, initialValue.run().getIntValue());
		}
		return new ReturnObject();
	}


	class InitialValueSetter extends Setter{

		final static int order = 0;

		public InitialValueSetter(Node parent) {
			super("< = int expr >", false, parent, order);
		}

		@Override
		public void setChildNode(Node toSet) {
			initialValue = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Int);

			return possibilities;
		}

	}
}
