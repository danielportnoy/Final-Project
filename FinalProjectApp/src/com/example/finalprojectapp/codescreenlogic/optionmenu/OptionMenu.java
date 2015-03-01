package com.example.finalprojectapp.codescreenlogic.optionmenu;
import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.codescreenlogic.Scope;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.identifier.BoolIdentifierOption;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.identifier.IntIdentifierOption;
import com.example.finalprojectapp.nodetree.Setter;
import com.example.finalprojectapp.nodetree.Type;

public class OptionMenu {

	private List<Option> availableCommands;
	private List<Option> currentCommands;


	public OptionMenu() {
		availableCommands = new ArrayList<Option>();
		currentCommands = new ArrayList<Option>();
	}

	public void clearMenu(){
		currentCommands.clear();
	}

	private void loadOption(Option op){
		currentCommands.add(op);
	}

	private void loadNewOptions(List<Option> ops){
		for (Option op : ops) {
			loadOption(op);
		}
	}

	public List<Option> getAvailableCommands() {
		return availableCommands;
	}

	public void setAvailableCommands(List<Option> availableCommands) {
		this.availableCommands = availableCommands;
	}

	public List<Option> getCurrentCommands() {
		return currentCommands;
	}

	public void setCurrentCommands(List<Option> currentCommands) {
		this.currentCommands = currentCommands;
	}
	
	public void loadOptionsForSetter(Setter setter) {
		
		clearMenu();
		
		loadStaticOptions(setter);
		loadIdentifierOptions(setter);
	}

	private void loadStaticOptions(Setter setter) {

		List<Option> newOptions = new ArrayList<Option>();

		for (Option option : availableCommands) {
			for (Type type : setter.possibleTypes()) {
				if(option.isType(type))
					newOptions.add(option);
			}		
		}

		loadNewOptions(newOptions);

	}

	private void loadIdentifierOptions(Setter setter) {

		List<String> identifiers;
		List<Option> newOptions = new ArrayList<Option>();

		if(setter.possibleTypes().contains(Type.Bool)){
			identifiers = Scope.getBooleanIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				newOptions.add(new BoolIdentifierOption(id));
		}
		if(setter.possibleTypes().contains(Type.Int)){
			identifiers = Scope.getIntegerIdentifiersRecursive(setter.getParent(), setter.getOrder());
			for (String id : identifiers)
				newOptions.add(new IntIdentifierOption(id));
		}

		loadNewOptions(newOptions);
	}

}
