package com.example.finalprojectapp.codescreenlogic.pattern;

import com.example.finalprojectapp.codescreenlogic.codescreen.InputMethod;
import com.example.finalprojectapp.codescreenlogic.optionmenu.OptionFilter;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolder;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolderType;
import com.example.finalprojectapp.codescreenlogic.specialsignbuilder.SpecialSignBuilder;

public class VarDecNoAsgn_Ptrn extends Pattern {

	private PlaceHolder TypePlaceHolder;
	private PlaceHolder IdntPlaceHolder;
	
	private PlaceHolder Semicolon_sign;

	public VarDecNoAsgn_Ptrn() {
		super(3);

		TypePlaceHolder = getPlaceHolders().get(0);
		IdntPlaceHolder = getPlaceHolders().get(1);	
		Semicolon_sign = getPlaceHolders().get(2);

		TypePlaceHolder.setPlaceholderType(PlaceHolderType.ReturnType);
		TypePlaceHolder.setOptionFilter(OptionFilter.ReturnType);
		TypePlaceHolder.setInputMethod(InputMethod.Option);
		
		IdntPlaceHolder.setPlaceholderType(PlaceHolderType.Idnt);
		IdntPlaceHolder.setOptionFilter(null);
		IdntPlaceHolder.setInputMethod(InputMethod.Keyboard);
		
		SpecialSignBuilder.setSemicolonSign(Semicolon_sign);
		
	}
	
	public PlaceHolder getTypePlaceHolder() {
		return TypePlaceHolder;
	}

	public void setTypePlaceHolder(PlaceHolder typePlaceHolder) {
		TypePlaceHolder = typePlaceHolder;
	}

	public PlaceHolder getIdntPlaceHolder() {
		return IdntPlaceHolder;
	}

	public void setIdntPlaceHolder(PlaceHolder idntPlaceHolder) {
		IdntPlaceHolder = idntPlaceHolder;
	}

	@Override
	public String toString() {
		return null;
	}
}
