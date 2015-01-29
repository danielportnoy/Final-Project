package com.example.finalprojectapp.codescreenlogic;

import com.example.finalprojectapp.codescreenlogic.codescreen.CodeScreen;
import com.example.finalprojectapp.codescreenlogic.codescreen.InputMethod;
import com.example.finalprojectapp.codescreenlogic.optionmenu.Option;
import com.example.finalprojectapp.codescreenlogic.optionmenu.OptionEnum;
import com.example.finalprojectapp.codescreenlogic.optionmenu.OptionFilter;
import com.example.finalprojectapp.codescreenlogic.optionmenu.OptionMenu;
import com.example.finalprojectapp.codescreenlogic.optionmenu.OptionsDB;
import com.example.finalprojectapp.codescreenlogic.pattern.Idnt_Ptrn;
import com.example.finalprojectapp.codescreenlogic.pattern.Literal_Ptrn;
import com.example.finalprojectapp.codescreenlogic.pattern.ReturnDataType_Ptrn;
import com.example.finalprojectapp.codescreenlogic.pattern.VarDecNoAsgn_Ptrn;
import com.example.finalprojectapp.codescreenlogic.pattern.VarDecWithAsgn_Ptrn;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolder;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolderType;

public class CodeScreenManager {

	private CodeScreen codeScreen;
	private OptionMenu optionMenu;

	private PlaceHolder ph = null;

	private static CodeScreenManager instance = new CodeScreenManager();

	private CodeScreenManager() { 
		codeScreen = new CodeScreen();
		optionMenu = new OptionMenu();
	}

	public static CodeScreenManager getInstance( ) {
		return instance;
	}

	public void pickedOption(Option option) {

		if(option.getOption().equals(OptionEnum.VarDec)){

			optionMenu.loadNewOptions(OptionsDB.filterByOptionEnum(OptionEnum.VarDec));
			GraphicsUnit.getInstance().updateOptionsMenu();

		}
		else if(option.getOption().equals(OptionEnum.VarDecNoAsgn)){

			ph.setPattern(new VarDecNoAsgn_Ptrn());

			optionMenu.clearMenu();
			GraphicsUnit.getInstance().updateOptionsMenu();

			codeScreen.addNewLine();
			GraphicsUnit.getInstance().updateCodeLines();

		}else if(option.getOption().equals(OptionEnum.VarDecWithAsgn)){

			ph.setPattern(new VarDecWithAsgn_Ptrn());

			optionMenu.clearMenu();
			GraphicsUnit.getInstance().updateOptionsMenu();

			codeScreen.addNewLine();
			GraphicsUnit.getInstance().updateCodeLines();

		}
		else if(ph.getPlaceholderType().equals(PlaceHolderType.ReturnType)){

			ph.setPattern(new ReturnDataType_Ptrn(option.toString()));
			ph.setInputMethod(InputMethod.Disabled);

			GraphicsUnit.getInstance().updateCodeLines();

			if(ph.getParent() instanceof VarDecNoAsgn_Ptrn){

				PlaceHolder idntPH = ((VarDecNoAsgn_Ptrn)ph.getParent()).getIdntPlaceHolder();

				if(option.getOption().equals(OptionEnum.Int))
					idntPH.setPlaceholderType(PlaceHolderType.IntIdnt);
				else if(option.getOption().equals(OptionEnum.Boolean))
					idntPH.setPlaceholderType(PlaceHolderType.BoolIdnt);
			}
			else if(ph.getParent() instanceof VarDecWithAsgn_Ptrn){

				PlaceHolder idntPH = ((VarDecWithAsgn_Ptrn)ph.getParent()).getIdntPlaceHolder();

				if(option.getOption().equals(OptionEnum.Int))
					idntPH.setPlaceholderType(PlaceHolderType.IntIdnt);
				else if(option.getOption().equals(OptionEnum.Boolean))
					idntPH.setPlaceholderType(PlaceHolderType.BoolIdnt);

				PlaceHolder exprPH = ((VarDecWithAsgn_Ptrn)ph.getParent()).getExprPlaceHolder();

				if(option.getOption().equals(OptionEnum.Int)){
					exprPH.setPlaceholderType(PlaceHolderType.IntExpr);
					exprPH.setOptionFilter(OptionFilter.Int);
				}
				else if(option.getOption().equals(OptionEnum.Boolean)){
					exprPH.setPlaceholderType(PlaceHolderType.BoolExpr);
					exprPH.setOptionFilter(OptionFilter.Boolean);
				}

				exprPH.setInputMethod(InputMethod.Option);
			}

			optionMenu.clearMenu();
			GraphicsUnit.getInstance().updateOptionsMenu();			
		}	
		else if(option.getOption().equals(OptionEnum.Literal)){

			if(ph.getPlaceholderType().equals(PlaceHolderType.IntExpr)){
				ph.setPlaceholderType(PlaceHolderType.IntLiteral);
				ph.setInputMethod(InputMethod.Keyboard);
			}
			else if(ph.getPlaceholderType().equals(PlaceHolderType.BoolExpr)){
				ph.setPlaceholderType(PlaceHolderType.BoolLiteral);
				ph.setOptionFilter(OptionFilter.BooleanLiteral);
				ph.setInputMethod(InputMethod.Option);
			}

			GraphicsUnit.getInstance().updateCodeLines();

			optionMenu.clearMenu();
			GraphicsUnit.getInstance().updateOptionsMenu();		
		}
		else if(option.getOption().equals(OptionEnum.True) | option.getOption().equals(OptionEnum.False)){

			ph.setPattern(new Literal_Ptrn(option.toString()));
			ph.setInputMethod(InputMethod.Disabled);

			GraphicsUnit.getInstance().updateCodeLines();

			optionMenu.clearMenu();
			GraphicsUnit.getInstance().updateOptionsMenu();		
		}
	}

	public void pickedCode(PlaceHolder ph) {

		this.ph = ph;

		optionMenu.loadNewOptions(OptionsDB.filterByOptionFilter(ph.getOptionFilter()));
		GraphicsUnit.getInstance().updateOptionsMenu();
	}

	public void setCode(String text, PlaceHolder ph) {

		if(ph.getPlaceholderType().equals(PlaceHolderType.Idnt) | 
				ph.getPlaceholderType().equals(PlaceHolderType.IntIdnt) | 
				ph.getPlaceholderType().equals(PlaceHolderType.BoolIdnt))
			ph.setPattern(new Idnt_Ptrn(text));
		else if(ph.getPlaceholderType().equals(PlaceHolderType.IntLiteral) | 
				ph.getPlaceholderType().equals(PlaceHolderType.BoolLiteral))
			ph.setPattern(new Literal_Ptrn(text));

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

	public static void reset() {
		instance = new CodeScreenManager();
	}

}
