package com.example.finalprojectapp.scenario.concrete.inventory_wing;

import java.util.ArrayList;

import android.util.Pair;

import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.scenario.archetype.InventoryScenarioArchetype;

public class Inventory_Wing_Level_1 extends InventoryScenarioArchetype {

	public static final String INVENTORY_LEVEL1_TEXT = "";

	public Inventory_Wing_Level_1() {

		// TODO temporary
		/*
		InitialBlockNode root = LevelManager.getInstance().getRootNode();

		Node swap1 = new swapNode();
		swap1.setOrder(0);
		swap1.setParent(LevelManager.getInstance().getRootNode());
		swap1.setErasable(false);

		Node iValue = new IntLiteralNode(2);
		iValue.setOrder(0);
		iValue.setParent(swap1);
		iValue.setErasable(false);

		swap1.addChild(iValue, 0);

		Node iValue2 = new IntLiteralNode(0);
		iValue.setOrder(1);
		iValue.setParent(swap1);
		iValue.setErasable(false);

		swap1.addChild(iValue2, 1);

		root.addChild(swap1 , 0);

		Node swap2 = new swapNode();
		swap2.setOrder(0);
		swap2.setParent(LevelManager.getInstance().getRootNode());
		swap2.setErasable(false);

		Node iValue3 = new IntLiteralNode(1);
		iValue3.setOrder(0);
		iValue3.setParent(swap2);
		iValue3.setErasable(false);

		swap2.addChild(iValue3, 0);

		Node iValue4 = new IntLiteralNode(3);
		iValue4.setOrder(1);
		iValue4.setParent(swap2);
		iValue4.setErasable(false);

		swap2.addChild(iValue4, 1);

		root.addChild(swap2 , 1);
		 */
	}

	@SuppressWarnings("serial")
	public static final ArrayList<Pair<InventoryItemsEnum,Integer>> INVENTORY_LEVEL1 = new ArrayList<Pair<InventoryItemsEnum,Integer>>(){
		{
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Shild, 3));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Sword, 1));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Helmet, 2));
		}
	};

	@SuppressWarnings("serial")
	public static final ArrayList<Pair<InventoryItemsEnum,Integer>> WIN_INVENTORY_LEVEL1 = new ArrayList<Pair<InventoryItemsEnum,Integer>>(){
		{
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Sword, 1));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Helmet, 2));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(InventoryItemsEnum.Shild, 3));
		}
	};

	@Override
	public void initiateConfigurations() {

		// TODO
		/*
		List<Pair<InventoryItemsEnum, Integer>> items = randomizeInventoryItems(4);
		setDefaultConfig(new MyConfiguration(items, items, items.size()));
		 */

		// TODO
		setDefaultConfig(new MyConfiguration(INVENTORY_LEVEL1, WIN_INVENTORY_LEVEL1, INVENTORY_LEVEL1.size()));

		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());	
	}

	@Override
	public void initiateAvailableOptions() {

		addToAvailableOptions(new NumberOption());
		addToAvailableOptions(new SwapOption());

	}

	@Override
	public void initiateLevelText() {
		setLevelText(INVENTORY_LEVEL1_TEXT);
	}

}