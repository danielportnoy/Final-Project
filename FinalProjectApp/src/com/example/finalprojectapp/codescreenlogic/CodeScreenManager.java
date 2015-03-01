package com.example.finalprojectapp.codescreenlogic;

import java.util.List;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodeScreen;
import com.example.finalprojectapp.codescreenlogic.optionmenu.OptionMenu;
import com.example.finalprojectapp.codescreenlogic.optionmenu.option.Option;
import com.example.finalprojectapp.nodetree.Setter;

public class CodeScreenManager {

	private CodeScreen codeScreen;
	private OptionMenu optionMenu;
	private Setter currentSetter;

	private static CodeScreenManager instance = new CodeScreenManager();

	private CodeScreenManager() { 
		codeScreen = new CodeScreen();
		optionMenu = new OptionMenu();
		
		currentSetter = null;
	}

	public static CodeScreenManager getInstance( ) {
		return instance;
	}
	
	public CodeScreen getCodeScreen() {
		return codeScreen;
	}

	public OptionMenu getOptionMenu() {
		return optionMenu;
	}

	public Setter getCurrentSetter() {
		return currentSetter;
	}

	public static void reset() {
		instance = new CodeScreenManager();
	}

	public void SetterClick(Setter setter) {
		
		this.currentSetter = setter;
		
		optionMenu.loadOptionsForSetter(setter);
		
		CodeScreenGraphicsUnit.getInstance().updateOptionsMenu();
	}

	public void setOptions(List<Option> availableCommands) {
		optionMenu.setAvailableCommands(availableCommands);	
	}
	
}
