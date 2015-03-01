package com.example.finalprojectapp.gamescreenlogic;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;

public abstract class Scenario {

	protected List<Option> availableOptions;

	public Scenario() {
		availableOptions = new ArrayList<Option>();
		initiateAvailableOptions();
	}

	public abstract void initiateAvailableOptions();

	public List<Option> getAvailableOptions() {
		return availableOptions;
	}

}
