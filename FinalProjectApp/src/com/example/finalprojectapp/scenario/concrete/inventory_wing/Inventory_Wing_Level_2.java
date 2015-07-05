package com.example.finalprojectapp.scenario.concrete.inventory_wing;

import java.util.List;

import android.util.Pair;

import com.example.finalprojectapp.codewriting.option.concrete.block.BlockOption;
import com.example.finalprojectapp.codewriting.option.concrete.literal.NumberOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.AdditionOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.DivisionOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.IncrementOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.arithmetic.SubtractionOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.assignment.SimpleAssignmentOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.GreaterThanOption;
import com.example.finalprojectapp.codewriting.option.concrete.operation.relational.LessThanOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.ForOption;
import com.example.finalprojectapp.codewriting.option.concrete.statement.IfThenOption;
import com.example.finalprojectapp.codewriting.option.concrete.vardec.IntVarDecOption;
import com.example.finalprojectapp.scenario.archetype.InventoryScenarioArchetype;

/**
 * Instance of the Inventory game scenario - Level 1.
 * @author daniel portnoy
 *
 */
public class Inventory_Wing_Level_2 extends InventoryScenarioArchetype {

	// hint text.
	public static final String INVENTORY_LEVEL2_TEXT = "Hello, travler! you'r goal is to sort the inventory from the lowest to the highest. go ahead and try.... \n\n" +
			"Hint: try the \"Swap( X , Y );\" option...\n\n" +
			"Warning: be careful not to exceed the limits of th inventory.";

	// Specific definitions.

	public static final int INVENTORY_LEVEL2_INVENTORY_SIZE_1 = 5;
	public static final int INVENTORY_LEVEL2_INVENTORY_SIZE_2 = 7;
	public static final int INVENTORY_LEVEL2_INVENTORY_SIZE_3 = 11;

	@Override
	public void initiateConfigurations() {

		List<Pair<InventoryItemsEnum, Integer>> items_1 = randomizeInventoryItems(INVENTORY_LEVEL2_INVENTORY_SIZE_1);
		List<Pair<InventoryItemsEnum, Integer>> items_2 = randomizeInventoryItems(INVENTORY_LEVEL2_INVENTORY_SIZE_2);
		List<Pair<InventoryItemsEnum, Integer>> items_3 = randomizeInventoryItems(INVENTORY_LEVEL2_INVENTORY_SIZE_3);

		setDefaultConfig(new MyConfiguration(items_1, sortInventory(items_1), items_1.size()));

		setCurrentConfig(getDefaultConfig());

		addToConfigs(getDefaultConfig());	

		addToConfigs(new MyConfiguration(items_2, sortInventory(items_2), items_2.size()));	
		addToConfigs(new MyConfiguration(items_3, sortInventory(items_3), items_3.size()));	

	}

	@Override
	public void initiateAvailableOptions() {

		addToAvailableOptions(new SwapOption());
		addToAvailableOptions(new InventorySizeOption());
		addToAvailableOptions(new AmountAtOption());
		
		addToAvailableOptions(new SimpleAssignmentOption());

		addToAvailableOptions(new ForOption());
		addToAvailableOptions(new IntVarDecOption());
		addToAvailableOptions(new IncrementOption());

		addToAvailableOptions(new AdditionOption());
		addToAvailableOptions(new IfThenOption());

		addToAvailableOptions(new GreaterThanOption());
		addToAvailableOptions(new LessThanOption());

		addToAvailableOptions(new BlockOption());

		addToAvailableOptions(new SubtractionOption());
		addToAvailableOptions(new DivisionOption());

		addToAvailableOptions(new NumberOption());
	}

	@Override
	public void initiateLevelText() {
		setLevelText(INVENTORY_LEVEL2_TEXT);
	}

}