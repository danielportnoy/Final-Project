package com.example.finalprojectapp.utilities;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class Android_Utils {

	public static int calcDP(int dpValue , Context context){
		return (int)(dpValue * context.getResources().getDisplayMetrics().density); 
	}

	public static void setLayoutParams(View v ,  Context context){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		//params.setMargins(Android_Utils.calcDP(4 , context), 0, 0, 0);	// TODO
		v.setPadding(Android_Utils.calcDP(3 , context), 0, 0, Android_Utils.calcDP(3 , context));
		v.setLayoutParams(params);
	}

	
	public static void setSetterLayoutParams(View v ,  Context context){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, calcDP(28, context)); //(calcDP(32, context), calcDP(24, context)
		params.gravity = Gravity.CENTER;
		params.setMargins(Android_Utils.calcDP(2 , context), 0, Android_Utils.calcDP(2 , context), 0);
		v.setLayoutParams(params);
	}
	 
}
