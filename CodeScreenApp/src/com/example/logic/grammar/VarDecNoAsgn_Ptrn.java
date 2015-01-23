package com.example.logic.grammar;

import com.example.logic.codescreen.InputMethod;
import com.example.logic.optionmenu.OptionEnum;
import com.example.logic.optionmenu.OptionFilter;

public class VarDecNoAsgn_Ptrn extends Pattern {

	private PlaceHolder TypePlaceHolder;
	private PlaceHolder IdntPlaceHolder;

	public VarDecNoAsgn_Ptrn() {
		super(2);

		TypePlaceHolder = getPlaceHolders().get(0);
		IdntPlaceHolder = getPlaceHolders().get(1);	

		TypePlaceHolder.setOptionFilter(new OptionFilter(OptionEnum.ReturnType));
		TypePlaceHolder.setInputMethod(InputMethod.Option);
		
		//IdntPlaceHolder.setOptionFilter(new OptionFilter(null));
		IdntPlaceHolder.setOptionFilter(new OptionFilter(OptionEnum.Idnt));
		IdntPlaceHolder.setInputMethod(InputMethod.Keyboard);
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
