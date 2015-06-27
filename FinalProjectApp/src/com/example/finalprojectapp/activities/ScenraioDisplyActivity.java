package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.CodeRunningActivity;
import com.example.finalprojectapp.codewriting.CodeWritingActivity;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;
import com.example.finalprojectapp.utilities.Android_Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ScenraioDisplyActivity extends Activity implements OnClickListener {

	private MySurfaceView gameView;

	private SharedPreferences SP;

	private LinearLayout gameViewLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenario_disply);

		//Retrieve all preferences.
		SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		/*
		 * Creating the gameView and adding it to the activity.
		 * using FPS and CPS in the creation process. 
		 */
		gameView = LevelManager.getInstance().getScenario().generateGameView(this, fps, animation);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		gameViewLayout = (LinearLayout) findViewById(R.id.LinearLayout_GameScreen);
		gameViewLayout.addView(gameView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_screen_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:

			//Settings option was clicked - launch settings activity.
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}


	@Override
	protected void onResume() {
		super.onResume();

		//Disable the run button if code isn't valid.

		Button b = (Button) findViewById(R.id.buttonRun);

		if(!LevelManager.getInstance().isCodeLinesValid())
			b.setEnabled(false);
		else
			b.setEnabled(true);

		//Set the scenario configuration to the default.

		LevelManager.getInstance().getScenario().setCurrentConfig(LevelManager.getInstance().getScenario().getDefaultConfig());

		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		boolean levelInstructions = SP.getBoolean(Constants.LI_KEY, Constants.DEFAULT_LI);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		/*
		 * Creating the gameView and adding it to the activity.
		 * using FPS and CPS in the creation process. 
		 */

		gameView = LevelManager.getInstance().getScenario().generateGameView(this, fps, animation);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		gameViewLayout.removeAllViews();
		gameViewLayout.addView(gameView);

		/*
		 * Show the level instructions dialog.
		 * using the levelInstructions preference.
		 */

		if(levelInstructions){
			AlertDialog.Builder builder = Android_Utils.getStartGameDialog(this,
					Constants.LEVEL_START_TEXT, LevelManager.getInstance().getScenario().getLevelText());

			builder.create().show();
		}	
	}

	@Override
	public void onClick(View v) {

		Intent intent = null;

		switch (v.getId()) {

		case R.id.buttonRun:
			
			//Run button was clicked - launch code running activity.

			intent = new Intent(this, CodeRunningActivity.class);
			startActivity(intent);
			//finish(); TODO
			break;

		case R.id.buttonCode:
			
			//Code button was clicked - launch code writing activity.

			intent = new Intent(this, CodeWritingActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		//Back button was clicked - launch the last open wing activity.

		Intent intent = new Intent(this, LevelManager.lastWingActivityClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
}
