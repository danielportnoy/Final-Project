package com.example.finalprojectapp.utilities;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.activities.LevelPickingActivity;
import com.example.finalprojectapp.activities.ScenraioDisplyActivity;
import com.example.finalprojectapp.gamescreen.GameScreenActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

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

	public static BitmapFactory.Options BitmapFactoryOptionsInScaled(){
		BitmapFactory.Options opts = new Options();
		opts.inScaled = false;
		return opts;
	}

	public static AlertDialog.Builder getEndGameDialog(final Activity activity,
			String title, String text , String textPositive, final boolean isWin){

		// Creating and Building the Dialog 
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);

		builder.setTitle(title);

		LayoutInflater inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View view = inflater.inflate(R.layout.end_level_dialog, null);

		TextView t = (TextView)view.findViewById(R.id.textView_gameEndText);
		t.setText(text);

		builder.setView(view);

		builder.setPositiveButton(textPositive, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(activity);
				boolean isSwipeMode = SP.getBoolean(Constants.SWIPE_KEY,Constants.DEFAULT_SWIPE);

				
				Intent intent = null;
				if(isWin || !isSwipeMode)
				{
					if(isWin)
						intent = new Intent(activity, LevelPickingActivity.class);
					else if(!isSwipeMode)
						intent = new Intent(activity, ScenraioDisplyActivity.class);
					
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					activity.startActivity(intent);
					activity.finish(); 
				}
				else{
					((GameScreenActivity)activity).setTab(0);
				}
			}
		});

		builder.setNegativeButton("Stay", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		return builder;
	}

	public static AlertDialog.Builder getStartGameDialog(final Activity activity, String title, String text){

		// Creating and Building the Dialog 
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);

		builder.setTitle(title);

		LayoutInflater inflater = (LayoutInflater) activity.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View view = inflater.inflate(R.layout.start_level_dialog, null);

		TextView t = (TextView)view.findViewById(R.id.textView_gameStartText);
		t.setText(text);

		CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox_ShowNessageAgain);

		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(activity);
				Editor editor = SP.edit();

				if ( isChecked )
					editor.putBoolean(Constants.LI_KEY, false);
				else
					editor.putBoolean(Constants.LI_KEY, true);

				editor.commit();				
			}
		});

		builder.setView(view);

		builder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		});

		return builder;
	}

	public static Bitmap GetWhiteBitmap(int width, int height){

		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap map = Bitmap.createBitmap(width, height, conf);

		Canvas canvas = new Canvas(map);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		canvas.drawColor(Color.WHITE);

		return map;
	}

}
