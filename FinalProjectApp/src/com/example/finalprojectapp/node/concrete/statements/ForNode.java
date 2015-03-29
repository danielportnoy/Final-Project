package com.example.finalprojectapp.node.concrete.statements;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.coderunning.coderunning_components.CodeRunningPart;
import com.example.finalprojectapp.codewriting.codewriting_components.CodeWritingPart;
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

	public ForNode() {
		setType(Type.Statement);
	}

	@Override
	public List<CodeWritingPart> getCodeWritingParts() {

		List<CodeWritingPart> res = new ArrayList<CodeWritingPart>();

		res.add(new CodeWritingPart(false, false, "for (", null));

		if(init == null){
			res.add(new CodeWritingPart(false, false, null, new InitSetter(this)));
			res.add(new CodeWritingPart(false, false, ";", null));
		}
		else
			res.addAll(init.getCodeWritingParts());

		if(condition == null)
			res.add(new CodeWritingPart(false, false, null, new ConditionSetter(this)));
		else
			res.addAll(condition.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ";", null));

		if(update == null)
			res.add(new CodeWritingPart(false, false, null, new UpdateSetter(this)));
		else
			res.addAll(update.getCodeWritingParts());

		res.add(new CodeWritingPart(false, false, ")", null));


		if(body == null){
			res.add(new CodeWritingPart(false, true, null, null));
			res.add(new CodeWritingPart(true, false, null, null));

			res.add(new CodeWritingPart(false, false, null, new BodySetter(this)));
		}
		else{
			if(body instanceof BlockNode)	//TODO
				res.addAll(body.getCodeWritingParts());
			else{
				res.add(new CodeWritingPart(false, true, null, null));
				res.addAll(CodeWritingPart.tabber(body.getCodeWritingParts()));
			}

		}

		return res;

	}

	@Override
	public List<CodeRunningPart> getCodeRunningParts(Node target, boolean isHighlighted) {

		isHighlighted = target.equals(this) || isHighlighted;
		List<CodeRunningPart> res = new ArrayList<CodeRunningPart>();

		res.add(new CodeRunningPart(false, false,isHighlighted, "for ("));

		if(init != null)
			res.addAll(init.getCodeRunningParts(target,isHighlighted));
		res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		if(condition != null)
			res.addAll(condition.getCodeRunningParts(target,isHighlighted));
		res.add(new CodeRunningPart(false, false,isHighlighted, ";"));

		if(update != null)
			res.addAll(update.getCodeRunningParts(target,isHighlighted));	
		res.add(new CodeRunningPart(false, false,isHighlighted, ")"));


		if(body instanceof BlockNode)	//TODO
			res.addAll(body.getCodeRunningParts(target,isHighlighted));
		else{
			res.add(new CodeRunningPart(false, true,isHighlighted, null));
			res.addAll(CodeRunningPart.tabber(body.getCodeRunningParts(target,isHighlighted)));
		}

		return res;
	}

	@Override
	public ReturnObject run() {

		LevelManager.getInstance().takeSnapshot(this);

		if(init != null)
			init.run();

		if(condition!=null){
			
			while(condition.run().getBoolValue()){
				body.run();

				if(update != null)
					update.run();
			}
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
			possibilities.add(Type.ForInit);

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
			possibilities.add(Type.ForUpdate);

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
