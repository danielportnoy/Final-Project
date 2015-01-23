package com.example.logic.pattern;

import com.example.logic.codescreen.InputMethod;
import com.example.logic.optionmenu.OptionFilter;
import com.example.logic.placeholder.PlaceHolder;
import com.example.logic.placeholder.PlaceHolderType;
import com.example.logic.specialsignbuilder.SpecialSignBuilder;

public class VarDecWithAsgn_Ptrn extends Pattern {

	private PlaceHolder TypePlaceHolder;

	private PlaceHolder IdntPlaceHolder;

	private PlaceHolder Equals_sign;

	private PlaceHolder ExprPlaceHolder;

	private PlaceHolder Semicolon_sign;

	public VarDecWithAsgn_Ptrn() {
		super(5);

		TypePlaceHolder = getPlaceHolders().get(0);
		IdntPlaceHolder = getPlaceHolders().get(1);	
		Equals_sign = getPlaceHolders().get(2);
		ExprPlaceHolder = getPlaceHolders().get(3);
		Semicolon_sign = getPlaceHolders().get(4);

		TypePlaceHolder.setPlaceholderType(PlaceHolderType.ReturnType);
		TypePlaceHolder.setOptionFilter(OptionFilter.ReturnType);
		TypePlaceHolder.setInputMethod(InputMethod.Option);

		IdntPlaceHolder.setPlaceholderType(PlaceHolderType.Idnt);
		IdntPlaceHolder.setOptionFilter(null);
		IdntPlaceHolder.setInputMethod(InputMethod.Keyboard);

		SpecialSignBuilder.setEqualsSign(Equals_sign);

		ExprPlaceHolder.setPlaceholderType(PlaceHolderType.Expr);
		ExprPlaceHolder.setOptionFilter(null);
		ExprPlaceHolder.setInputMethod(InputMethod.Disabled);

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

	public PlaceHolder getExprPlaceHolder() {
		return ExprPlaceHolder;
	}

	public void setExprPlaceHolder(PlaceHolder exprPlaceHolder) {
		ExprPlaceHolder = exprPlaceHolder;
	}

	@Override
	public String toString() {
		return null;
	}

}
