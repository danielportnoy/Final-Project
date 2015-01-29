package com.example.finalprojectapp.codescreenlogic.optionmenu;

public class Option {
	
	private OptionEnum option;
	
	public Option(OptionEnum option) {
		this.option = option;
	}

	public OptionEnum getOption() {
		return option;
	}

	public void setOption(OptionEnum option) {
		this.option = option;
	}
	
	@Override
	public String toString(){
		return option.toString();
	}

}
