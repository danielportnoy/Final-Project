package com.example.finalprojectapp.utilities;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
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

/**
 * Supplies the needed utility functions of the android system.
 * @author daniel portnoy
 *
 */
public class Android_Utils {

	/**
	 * Calculates the density pixels for the device.
	 * @param dpValue
	 * @param context
	 * @return integer pixels.
	 */
	public static int calcDP(int dpValue , Context context){
		return (int)(dpValue * context.getResources().getDisplayMetrics().density); 
	}

	/**
	 * Sets up the view.
	 * @param v - View to set up.
	 * @param context
	 */
	public static void setLayoutParams(View v ,  Context context){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER;
		v.setPadding(Android_Utils.calcDP(3 , context), 0, 0, Android_Utils.calcDP(3 , context));
		v.setLayoutParams(params);
	}

	/**
	 * Sets up the setter view.
	 * @param v - View to set up.
	 * @param context
	 */
	public static void setSetterLayoutParams(View v ,  Context context){
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, calcDP(28, context));
		params.gravity = Gravity.CENTER;
		params.setMargins(Android_Utils.calcDP(2 , context), 0, Android_Utils.calcDP(2 , context), 0);
		v.setLayoutParams(params);
	}

	/**
	 * Set up BitmapFactory.Options object.
	 * @return BitmapFactory.Options object.
	 */
	public static BitmapFactory.Options BitmapFactoryOptionsInScaled(){
		BitmapFactory.Options opts = new Options();
		opts.inScaled = false;
		return opts;
	}

	/**
	 * Set up and display the endGame dialog.
	 * @param ACTIVITY
	 * @param title
	 * @param headerText
	 * @param text
	 * @param textPositive
	 * @param IS_WIN
	 * @return AlertDialog.Builder object.
	 */
	public static AlertDialog.Builder getEndGameDialog(final Activity ACTIVITY,
			String title, String headerText, String text , String textPositive, final boolean IS_WIN){

		// Creating and Building the Dialog 
		AlertDialog.Builder builder = new AlertDialog.Builder(ACTIVITY);

		builder.setTitle(title);

		LayoutInflater inflater = (LayoutInflater) ACTIVITY.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View view = inflater.inflate(R.layout.end_level_dialog, null);

		TextView headerTV = (TextView)view.findViewById(R.id.textView_gameEndTextHeader);
		headerTV.setText(headerText);

		TextView textTV = (TextView)view.findViewById(R.id.textView_gameEndText);
		textTV.setText(text);

		builder.setView(view);

		builder.setPositiveButton(textPositive, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(ACTIVITY);
				boolean isSwipeMode = SP.getBoolean(Constants.SWIPE_KEY,Constants.DEFAULT_SWIPE);


				Intent intent = null;
				if(IS_WIN || !isSwipeMode)
				{
					if(IS_WIN)
						intent = new Intent(ACTIVITY, LevelManager.lastWingActivityClass);
					else if(!isSwipeMode)
						intent = new Intent(ACTIVITY, ScenraioDisplyActivity.class);

					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					ACTIVITY.startActivity(intent);
					ACTIVITY.finish(); 
				}
				else{
					((GameScreenActivity)ACTIVITY).setTab(0);
				}
			}
		});

		builder.setNegativeButton(Constants.END_GAME_NEGATIVE_TEXT, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		return builder;
	}

	/**
	 * Set up and display the startGame dialog.
	 * @param ACTIVITY
	 * @param title
	 * @param headerText
	 * @param text
	 * @param textPositive
	 * @param IS_WIN
	 * @return AlertDialog.Builder object.
	 */
	public static AlertDialog.Builder getStartGameDialog(final Activity ACTIVITY, String title, String text){

		// Creating and Building the Dialog 
		AlertDialog.Builder builder = new AlertDialog.Builder(ACTIVITY);

		builder.setTitle(title);

		LayoutInflater inflater = (LayoutInflater) ACTIVITY.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View view = inflater.inflate(R.layout.start_level_dialog, null);

		TextView t = (TextView)view.findViewById(R.id.textView_gameStartText);
		t.setText(text);

		CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox_ShowNessageAgain);

		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(ACTIVITY);
				Editor editor = SP.edit();

				if ( isChecked )
					editor.putBoolean(Constants.LI_KEY, false);
				else
					editor.putBoolean(Constants.LI_KEY, true);

				editor.commit();				
			}
		});

		builder.setView(view);

		builder.setPositiveButton(Constants.END_GAME_POSITIVE_TEXT, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		});

		return builder;
	}

	/**
	 * Creates a white background Bitmap.
	 * @param width
	 * @param height
	 * @return Bitmap object.
	 */
	public static Bitmap GetWhiteBitmap(int width, int height){

		Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		Bitmap map = Bitmap.createBitmap(width, height, conf);

		Canvas canvas = new Canvas(map);
		canvas.setDensity(DisplayMetrics.DENSITY_DEFAULT);

		canvas.drawColor(Color.WHITE);

		return map;
	}

}
