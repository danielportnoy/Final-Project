package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.concrete.block.InitialBlockNode;
import com.example.finalprojectapp.node.concrete.vardec.IntVarDecNode;
import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

public class Tutorial_Level_3 extends PrintScenarioArchetype {

	public static final String TUTORIAL_LEVEL3_TEXT = "";
	
	public Tutorial_Level_3() {
		
		InitialBlockNode root = LevelManager.getInstance().getRootNode();
		
		/*
		Node i = new IntVarDecNode("i");
		i.setOrder(0);
		i.setParent(LevelManager.getInstance().getRootNode());
		i.setErasable(false);
		
		root.getScope().getIntegerIdentifiers().put("i", i.getOrder());
		root.innerNodes.add(i);
		*/
		
		/*
		Node print = new PrintNode();
		print.setOrder(1);
		print.setParent(LevelManager.getInstance().getRootNode());
		print.setErasable(false);
		
		root.innerNodes.add(print);
		*/
	}

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration("Print the var : i", 0+""));
		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());
	}

	@Override
	public void initiateAvailableOptions() {
		addToAvailableOptions(new PrintOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(TUTORIAL_LEVEL3_TEXT);
	}

}