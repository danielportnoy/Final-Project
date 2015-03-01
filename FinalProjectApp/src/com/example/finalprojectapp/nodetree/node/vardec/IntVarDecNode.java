package com.example.finalprojectapp.nodetree.node.vardec;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.Node;

public class IntVarDecNode extends Node {

	private String identifier;
	private Node initialValue;

	public IntVarDecNode(String identifier) {
		this.identifier = identifier;
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

}
