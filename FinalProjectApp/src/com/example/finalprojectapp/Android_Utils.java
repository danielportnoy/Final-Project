package com.example.finalprojectapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class Android_Utils {
	
	public static int calcMargin(int dpValue , Context context){
		return (int)(dpValue * context.getResources().getDisplayMetrics().density); 
	}

	public static void setLayoutParams(View v ,  Context context){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(Android_Utils.calcMargin(5 , context), 0, 0, 0);
		v.setLayoutParams(params);
	}

}
