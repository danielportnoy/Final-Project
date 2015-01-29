package com.example.finalprojectapp.codescreenlogic.specialsignbuilder;

import com.example.finalprojectapp.codescreenlogic.codescreen.InputMethod;
import com.example.finalprojectapp.codescreenlogic.pattern.Text_Ptrn;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolder;
import com.example.finalprojectapp.codescreenlogic.placeholder.PlaceHolderType;

public class SpecialSignBuilder {

	public static void setSemicolonSign(PlaceHolder semicolon) {
		semicolon.setPlaceholderType(PlaceHolderType.Semicolon_sign);
		semicolon.setOptionFilter(null);
		semicolon.setInputMethod(InputMethod.Disabled);
		semicolon.setPattern(new Text_Ptrn(";"));
	}

	public static void setEqualsSign(PlaceHolder equals_sign) {
		equals_sign.setPlaceholderType(PlaceHolderType.Equals_sign);
		equals_sign.setOptionFilter(null);
		equals_sign.setInputMethod(InputMethod.Disabled);
		equals_sign.setPattern(new Text_Ptrn("="));
	}

}
