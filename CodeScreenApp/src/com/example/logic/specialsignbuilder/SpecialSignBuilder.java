package com.example.logic.specialsignbuilder;

import com.example.logic.codescreen.InputMethod;
import com.example.logic.pattern.EqualsSign_Ptrn;
import com.example.logic.pattern.Semicolon_Ptrn;
import com.example.logic.placeholder.PlaceHolder;
import com.example.logic.placeholder.PlaceHolderType;

public class SpecialSignBuilder {

	public static void setSemicolonSign(PlaceHolder semicolon) {
		semicolon.setPlaceholderType(PlaceHolderType.Semicolon_sign);
		semicolon.setOptionFilter(null);
		semicolon.setInputMethod(InputMethod.Disabled);
		semicolon.setPattern(new Semicolon_Ptrn());
	}

	public static void setEqualsSign(PlaceHolder equals_sign) {
		equals_sign.setPlaceholderType(PlaceHolderType.Equals_sign);
		equals_sign.setOptionFilter(null);
		equals_sign.setInputMethod(InputMethod.Disabled);
		equals_sign.setPattern(new EqualsSign_Ptrn());
	}

}
