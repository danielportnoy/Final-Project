package com.example.finalprojectapp.gamescreenlogic;

import com.example.finalprojectapp.codescreenlogic.optionmenu.option.block.BlockOption;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.literal.LiteralOption;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.operation.arithmetic.PlusOption;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.statement.IfThenOption;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.vardec.BoolVarDecOption;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.vardec.IntVarDecOption;

public class Level1 extends Scenario {

	@Override
	public void initiateAvailableOptions() {
		availableOptions.add(new BlockOption());
		availableOptions.add(new IfThenOption());
		availableOptions.add(new LiteralOption());
		availableOptions.add(new BoolVarDecOption());
		availableOptions.add(new IntVarDecOption());
		//availableOptions.add(new ForOption());		// TODO
		
		availableOptions.add(new PlusOption());

	}
}
