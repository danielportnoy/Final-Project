package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.gamescreen.GameScreenActivity;
import com.example.finalprojectapp.scenario.Scenario;
import com.example.finalprojectapp.scenario.concrete.tutorial.*;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class TutorialActivity extends Activity implements OnClickListener{

	private LevelManager levelManager = LevelManager.getInstance();
	private SharedPreferences SP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);

		// Retrieve all preferences.
		SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tutorial_menu, menu);
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
	public void onClick(View v) {

		Intent intent = null;

		Scenario scenario = null;

		levelManager.reset();

		/*
		 *  If swipe mode option is:
		 *  	ON - use fragments in GameScreenActivity.
		 *  	OFF - use the regular display. 
		 */

		boolean isSwipeMode = SP.getBoolean(Constants.SWIPE_KEY,Constants.DEFAULT_SWIPE);

		if(isSwipeMode)
			intent = new Intent(this, GameScreenActivity.class);
		else
			intent = new Intent(this, ScenraioDisplyActivity.class);

		//Set the scenario in according to the button that was clicked.

		if(v.getId() ==  R.id.button_tutorial_LEVEL1)
			scenario = new Tutorial_Level_1();
		else if(v.getId() ==  R.id.button_tutorial_LEVEL2)
			scenario = new Tutorial_Level_2();
		else if(v.getId() ==  R.id.button_tutorial_LEVEL3)
			scenario = new Tutorial_Level_3();
		/*else if(v.getId() ==  R.id.button_tutorial_LEVEL4)
			scenario = new Tutorial_Level_4();*/

		if(scenario != null){
			levelManager.loadScenario(scenario);
			startActivity(intent);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		//Back button was clicked - launch wingPicking activity.

		Intent intent = new Intent(this, WingPickingActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}
