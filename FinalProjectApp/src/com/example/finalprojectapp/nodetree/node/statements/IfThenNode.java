package com.example.finalprojectapp.nodetree.node.statements;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodePart;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;
import com.example.finalprojectapp.nodetree.node.Node;
import com.example.finalprojectapp.nodetree.node.block.BlockNode;

public class IfThenNode extends Node{

	private Node condition;
	private Node thenBody;

	@Override
	public List<CodePart> getCodeParts() {

		List<CodePart> res = new ArrayList<CodePart>();

		res.add(new CodePart(false, false, "if (", null));

		if(condition == null)
			res.add(new CodePart(false, false, null, new ConditionSetter(this)));
		else
			res.addAll(condition.getCodeParts());

		res.add(new CodePart(false, false, ")", null));

		if(thenBody == null){
			res.add(new CodePart(false, true, null, null));
			res.add(new CodePart(true, false, null, null));

			res.add(new CodePart(false, false, null, new ThenBodySetter(this)));
		}
		else{
			if(thenBody instanceof BlockNode)	//TODO
				res.addAll(thenBody.getCodeParts());
			else{
				res.add(new CodePart(false, true, null, null));
				res.addAll(CodePart.tabber(thenBody.getCodeParts()));
			}

		}

		return res;

	}

	class ConditionSetter extends Setter{

		final static int order = 0;

		public ConditionSetter(Node parent) {
			super("< bool expr >", true, parent, order);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			condition = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Bool);

			return possibilities;
		}
	}

	class ThenBodySetter extends Setter{

		static final int order = 1;

		public ThenBodySetter(Node parent) {
			super("+", true, parent ,order);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			thenBody = toSet;
			toSet.setOrder(order);
			toSet.setParent(getParent());
		}

		@Override
		public List<Type> possibleTypes() {

			List<Type> possibilities = new ArrayList<Type>();
			possibilities.add(Type.Statement);

			return possibilities;
		}

	}

}
