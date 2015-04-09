package com.example.finalprojectapp.utilities;

import java.util.Random;

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
	
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
}
