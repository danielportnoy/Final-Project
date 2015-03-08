package com.example.finalprojectapp.node.concrete.statements;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.codewriting.codeline.CodePart;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.ReturnObject;
import com.example.finalprojectapp.node.Setter;
import com.example.finalprojectapp.node.Type;
import com.example.finalprojectapp.node.concrete.block.BlockNode;

public class ForNode extends Node{			// TODO

	private Node init;
	private Node condition;
	private Node update;
	private Node body;

	@Override
	public List<CodePart> getCodeParts() {

		List<CodePart> res = new ArrayList<CodePart>();

		res.add(new CodePart(false, false, "for (", null));

		if(init == null)
			res.add(new CodePart(false, false, null, new InitSetter(this)));
		else
			res.addAll(init.getCodeParts());

		res.add(new CodePart(false, false, ";", null));

		if(condition == null)
			res.add(new CodePart(false, false, null, new ConditionSetter(this)));
		else
			res.addAll(condition.getCodeParts());
		
		res.add(new CodePart(false, false, ";", null));
		
		if(update == null)
			res.add(new CodePart(false, false, null, new UpdateSetter(this)));
		else
			res.addAll(update.getCodeParts());
		
		res.add(new CodePart(false, false, ")", null));


		if(body == null){
			res.add(new CodePart(false, true, null, null));
			res.add(new CodePart(true, false, null, null));

			res.add(new CodePart(false, false, null, new BodySetter(this)));
		}
		else{
			if(body instanceof BlockNode)	//TODO
				res.addAll(body.getCodeParts());
			else{
				res.add(new CodePart(false, true, null, null));
				res.addAll(CodePart.tabber(body.getCodeParts()));
			}

		}

		return res;
	
	}
	
	@Override
	public ReturnObject run() {

		LevelManager.getInstance().getCodeRunningManager().takeSnapshot(init);
		init.run();

		while(condition.run().getBoolValue()){
			
			LevelManager.getInstance().getCodeRunningManager().takeSnapshot(body);
			body.run();
			
			LevelManager.getInstance().getCodeRunningManager().takeSnapshot(update);
			update.run();
		}

		return new ReturnObject();
	}
	
	class InitSetter extends Setter{

		static final int order = 0;

		public InitSetter(Node parent) {
			super("+", false, parent ,order);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			init = toSet;
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

	class ConditionSetter extends Setter{

		final static int order = 1;

		public ConditionSetter(Node parent) {
			super("< bool expr >", false, parent, order);	//TODO
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

	class UpdateSetter extends Setter{

		static final int order = 2;

		public UpdateSetter(Node parent) {
			super("+", false, parent ,order);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			update = toSet;
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

	class BodySetter extends Setter{

		static final int order = 4;

		public BodySetter(Node parent) {
			super("+", true, parent ,order);	//TODO
		}

		@Override
		public void setChildNode(Node toSet) {
			body = toSet;
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
