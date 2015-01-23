package com.example.logic.optionmenu;


public class OptionFilter {

	private boolean filterByOption;
	private OptionEnum option;
	
	public OptionFilter(OptionEnum option){
		
		filterByOption = option!=null;
		this.option = option;
		
	}

	public boolean isFilterByOption() {
		return filterByOption;
	}

	public OptionEnum getOption() {
		return option;
	}
	
	@Override
	public String toString() {
		
		if(isFilterByOption())
			return option.toString();
		
		return null;
	}
}
