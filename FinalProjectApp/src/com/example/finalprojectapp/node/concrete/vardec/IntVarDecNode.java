package com.example.finalprojectapp.node.concrete.vardec;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
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
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, "int "+ identifier, null));

		if(initialValue == null){
			res.add(new CodeWritingPart(false, false, ";", null));
			res.add(new CodeWritingPart(false, false, null, new InitialValueSetter(this)));
		}
		else{
			res.add(new CodeWritingPart(false, false, "=", null));
			res.addAll(initialValue.getCodeWritingParts());
			res.add(new CodeWritingPart(false, false, ";", null));
		}

		return res;
	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, "int "+ identifier));

		if(initialValue != null){
			res.add(new CodeRunningPart(false, false,isHighlighted, "="));
			res.addAll(initialValue.getCodeRunningParts(target,isHighlighted));
		}

		res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		return res;
	}


	@Override
	public ReturnObject run() {

		LevelManager.getInstance().takeSnapshot(this);

		if(initialValue == null)
			LevelManager.getInstance().putIntegerValueToIdentifier(identifier, 0);
		else
			LevelManager.getInstance().putIntegerValueToIdentifier(identifier, initialValue.run().getIntValue());

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
