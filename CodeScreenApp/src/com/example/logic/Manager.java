package com.example.logic;

import android.util.Log;

import com.example.codescreenapp.GraphicsUnit;
import com.example.logic.codescreen.CodeScreen;
import com.example.logic.codescreen.InputMethod;
import com.example.logic.grammar.IntIdnt_Ptrn;
import com.example.logic.grammar.PlaceHolder;
import com.example.logic.grammar.ReturnDataType_Ptrn;
import com.example.logic.grammar.VarDecNoAsgn_Ptrn;
import com.example.logic.optionmenu.OptionFilter;
import com.example.logic.optionmenu.Option;
import com.example.logic.optionmenu.OptionMenu;
import com.example.logic.optionmenu.OptionEnum;
import com.example.logic.optionmenu.OptionsDB;

public class Manager {

	private CodeScreen codeScreen;
	private OptionMenu optionMenu;

	private PlaceHolder ph = null;

	private static Manager instance = new Manager( );

	private Manager() {
		codeScreen = new CodeScreen();
		optionMenu = new OptionMenu();
	}

	public static Manager getInstance( ) {
		return instance;
	}

	public void pickedOption(Option option) {

		if(option.getOption().equals(OptionEnum.VarDec)){

			optionMenu.loadNewOptions(OptionsDB.filter(new OptionFilter(OptionEnum.VarDec)));
			GraphicsUnit.getInstance().updateOptionsMenu();

		}
		else if(option.getOption().equals(OptionEnum.VarDecNoAsgn)){

			ph.setPattern(new VarDecNoAsgn_Ptrn());

			optionMenu.clearMenu();
			GraphicsUnit.getInstance().updateOptionsMenu();

			codeScreen.addNewLine();
			GraphicsUnit.getInstance().updateCodeLines();

		}
		else if(ph.getOptionFilter().getOption().equals(OptionEnum.ReturnType)){

			ph.setPattern(new ReturnDataType_Ptrn(option.toString()));
			ph.setInputMethod(InputMethod.Disabled);

			GraphicsUnit.getInstance().updateCodeLines();

			if(ph.getParent() instanceof VarDecNoAsgn_Ptrn){

				PlaceHolder idntPH = ((VarDecNoAsgn_Ptrn)ph.getParent()).getIdntPlaceHolder();

				if(option.getOption().equals(OptionEnum.Int))
					idntPH.setOptionFilter(new OptionFilter(OptionEnum.IntIdnt));
				else if(option.getOption().equals(OptionEnum.Boolean))
					idntPH.setOptionFilter(new OptionFilter(OptionEnum.BooleanIdnt));
			}

			optionMenu.clearMenu();
			GraphicsUnit.getInstance().updateOptionsMenu();			

		}	

		Log.d("aaa", option.toString());
	}

	public void pickedCode(PlaceHolder ph) {

		this.ph = ph;

		optionMenu.loadNewOptions(OptionsDB.filter(ph.getOptionFilter()));
		GraphicsUnit.getInstance().updateOptionsMenu();
	}

	public void setCode(String text, PlaceHolder ph) {

		if(ph.getOptionFilter().getOption().equals(OptionEnum.IntIdnt))
			ph.setPattern(new IntIdnt_Ptrn(text));
		else if(ph.getOptionFilter().getOption().equals(OptionEnum.BooleanIdnt))
			ph.setPattern(new IntIdnt_Ptrn(text));

		ph.setInputMethod(InputMethod.Disabled);
		GraphicsUnit.getInstance().updateCodeLines();

		optionMenu.clearMenu();
		GraphicsUnit.getInstance().updateOptionsMenu();

	}

	public CodeScreen getCodeScreen() {
		return codeScreen;
	}

	public OptionMenu getOptionMenu() {
		return optionMenu;
	}

}
