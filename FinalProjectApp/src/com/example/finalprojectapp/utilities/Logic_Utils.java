package com.example.finalprojectapp.utilities;

public class Logic_Utils {

	public static boolean isValidIdentifier(String text){

		if(text == null || text.length() == 0)
			return false;
		else{
			if(!Character.isJavaIdentifierStart((text.charAt(0))))
				return false;

			for (int i = 1; i < text.length() ; i++) {
				if(!Character.isJavaIdentifierPart((text.charAt(i))))
					return false;
			}
		}

		return true;
	}
}
