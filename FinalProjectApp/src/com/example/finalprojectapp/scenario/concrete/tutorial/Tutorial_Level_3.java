package com.example.finalprojectapp.scenario.concrete.tutorial;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.concrete.block.InitialBlockNode;
import com.example.finalprojectapp.node.concrete.literal.IntLiteralNode;
import com.example.finalprojectapp.node.concrete.vardec.IntVarDecNode;
import com.example.finalprojectapp.scenario.archetype.PrintScenarioArchetype;

/**
 * Instance of the Print game scenario - Level 3.
 * @author daniel portnoy
 *
 */
public class Tutorial_Level_3 extends PrintScenarioArchetype {

	// hint text.
	public static final String TUTORIAL_LEVEL3_TEXT = "";

	public static final String TUTORIAL_LEVEL3_HEADER = "int i = 13;";
	public static final String TUTORIAL_LEVEL3_SUB_HEADER= "print i, would you?";
	public static final String TUTORIAL_LEVEL3_ANSWEAR = 13 + "";

	public Tutorial_Level_3() {

		InitialBlockNode root = LevelManager.getInstance().getRootNode();

		Node i = new IntVarDecNode("i");
		i.setOrder(0);
		i.setParent(LevelManager.getInstance().getRootNode());
		i.setErasable(false);

		root.getScope().getIntegerIdentifiers().put("i", i.getOrder());

		Node iValue = new IntLiteralNode(13);
		iValue.setOrder(0);
		iValue.setParent(i);
		iValue.setErasable(false);

		i.addChild(iValue, 0);

		root.addChild(i , 0);

		/*Node print = new PrintNode();
		print.setOrder(1);
		print.setParent(LevelManager.getInstance().getRootNode());
		print.setErasable(false);

		root.addChild(print , 1);*/

	}

	@Override
	public void initiateConfigurations() {

		setDefaultConfig(new MyConfiguration(TUTORIAL_LEVEL3_HEADER, TUTORIAL_LEVEL3_SUB_HEADER , TUTORIAL_LEVEL3_ANSWEAR));

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