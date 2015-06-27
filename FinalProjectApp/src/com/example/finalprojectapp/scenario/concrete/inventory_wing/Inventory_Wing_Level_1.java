package com.example.finalprojectapp.scenario.concrete.inventory_wing;

import java.util.ArrayList;

import android.util.Pair;

import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.scenario.archetype.InventoryScenarioArchetype;

/**
 * Instance of the Inventory game scenario - Level 1.
 * @author daniel portnoy
 *
 */
public class Inventory_Wing_Level_1 extends InventoryScenarioArchetype {

	// hint text.
	public static final String INVENTORY_LEVEL1_TEXT = "Hello, travler! you'r goal is to sort the inventory from the lowest to the highest. go ahead and try.... \n\n" +
			"Hint: try the \"Swap( X , Y );\" option...\n\n" +
			"Warning: be careful not to exceed the limits of th inventory.";

	public Inventory_Wing_Level_1() {

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

	// Specific definitions.

	public static final int INVENTORY_LEVEL1_INVENTORY_SIZE = 3;

	
	/***** Inventory *****/
	public static final InventoryItemsEnum INVENTORY_LEVEL1_INVENTORY_ITEM_1 = InventoryItemsEnum.Shild;
	public static final int INVENTORY_LEVEL1_INVENTORY_ITEM_1_AMOUNT = 3;
	public static final InventoryItemsEnum INVENTORY_LEVEL1_INVENTORY_ITEM_2 = InventoryItemsEnum.Sword;
	public static final int INVENTORY_LEVEL1_INVENTORY_ITEM_2_AMOUNT = 1;
	public static final InventoryItemsEnum INVENTORY_LEVEL1_INVENTORY_ITEM_3 = InventoryItemsEnum.Helmet;
	public static final int INVENTORY_LEVEL1_INVENTORY_ITEM_3_AMOUNT = 2;
	
	@SuppressWarnings("serial")
	public static final ArrayList<Pair<InventoryItemsEnum,Integer>> INVENTORY_LEVEL1 = new ArrayList<Pair<InventoryItemsEnum,Integer>>(){
		{
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(
					INVENTORY_LEVEL1_INVENTORY_ITEM_1, INVENTORY_LEVEL1_INVENTORY_ITEM_1_AMOUNT));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(
					INVENTORY_LEVEL1_INVENTORY_ITEM_1, INVENTORY_LEVEL1_INVENTORY_ITEM_2_AMOUNT));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(
					INVENTORY_LEVEL1_INVENTORY_ITEM_1, INVENTORY_LEVEL1_INVENTORY_ITEM_3_AMOUNT));

		}
	};
	/***** Inventory *****/

	/***** Win Inventory *****/
	public static final InventoryItemsEnum INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_1 = InventoryItemsEnum.Shild;
	public static final int INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_1_AMOUNT = 3;
	public static final InventoryItemsEnum INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_2 = InventoryItemsEnum.Sword;
	public static final int INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_2_AMOUNT = 1;
	public static final InventoryItemsEnum INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_3 = InventoryItemsEnum.Helmet;
	public static final int INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_3_AMOUNT = 2;
	@SuppressWarnings("serial")
	public static final ArrayList<Pair<InventoryItemsEnum,Integer>> WIN_INVENTORY_LEVEL1 = new ArrayList<Pair<InventoryItemsEnum,Integer>>(){
		{
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(
					INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_1, INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_1_AMOUNT));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(
					INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_2, INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_2_AMOUNT));
			add(new Pair<InventoryScenarioArchetype.InventoryItemsEnum, Integer>(
					INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_3, INVENTORY_LEVEL1_INVENTORY_WIN_ITEM_3_AMOUNT));
		}
	};
	/***** Win Inventory *****/

	@Override
	public void initiateConfigurations() {
		/*
		List<Pair<InventoryItemsEnum, Integer>> items = randomizeInventoryItems(4);
		setDefaultConfig(new MyConfiguration(items, items, items.size()));
		 */
		setDefaultConfig(new MyConfiguration(INVENTORY_LEVEL1, WIN_INVENTORY_LEVEL1, INVENTORY_LEVEL1_INVENTORY_SIZE));

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