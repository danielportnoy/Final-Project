package com.example.finalprojectapp.codescreenlogic.optionmenu;
import java.util.LinkedList;
import java.util.List;

public class OptionMenu {

	private LinkedList<Option> options;

	public OptionMenu() {
		options = new LinkedList<Option>();
	}

	public LinkedList<Option> getOptions() {
		return options;
	}

	public void clearMenu(){
		options.clear();
	}
	
	private void loadOption(Option op){
		options.add(op);
	}
	
	public void loadNewOptions(List<Option> ops){
		clearMenu();
		
		for (Option op : ops) {
			loadOption(op);
		}
	}

	public void displayOptions(){
		for (Option option : options) {
			System.out.println(option);
		}
	}
}
