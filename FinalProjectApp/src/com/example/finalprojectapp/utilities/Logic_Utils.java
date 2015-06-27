package com.example.finalprojectapp.utilities;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Supplies the needed utility functions of the java logical code.
 * @author daniel portnoy
 *
 */
public class Logic_Utils {

	/**
	 * Check if string is in a valid Identifier format.
	 * @param text
	 * @return boolean value.
	 */
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
	 * Get the max font size for a String inside a rectangle.
	 * @param str
	 * @param maxWidth
	 * @param maxHeight
	 * @return integer value.
	 */
	public static int determineMaxTextSize(String str, float maxWidth, float maxHeight)
	{
		int size = 0; 

		Paint paint = new Paint();
		paint.setTextSize(size);

		Rect bounds = new Rect();

		paint.getTextBounds(str, 0, str.length(), bounds);		        

		while(bounds.width() < maxWidth && bounds.height() < maxHeight){
			paint.setTextSize(++ size);
			paint.getTextBounds(str, 0, str.length(), bounds);
		} 

		return size;
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
		//Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int scope = max - min;

		//int randomNum = rand.nextInt((max - min) + 1) + min; 
		int randomNum = (int) (Math.random() * (scope + 1)) + min;

		return randomNum;
	}
}
