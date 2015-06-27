package com.example.finalprojectapp.coderunning;

import java.util.List;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.activities.ScenraioDisplyActivity;
import com.example.finalprojectapp.activities.SettingsActivity;
import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.adapter.VarValuesAdapter;
import com.example.finalprojectapp.coderunning.codeplayer.CodePlayer;
import com.example.finalprojectapp.coderunning.managment.CodeRunningGraphicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningLogicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;
import com.example.finalprojectapp.coderunning.testcase.TestCase;
import com.example.finalprojectapp.graphic_utils.MySurfaceView;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class CodeRunningActivity extends Activity implements OnClickListener {

	private CodePlayer player;

	private SharedPreferences SP;

	private LinearLayout gameViewLayout;
	private MySurfaceView gameView;

	private CodeRunningGraphicUnit graphics;
	private CodeRunningLogicUnit logics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code_running);

		/*
		 * Initialize the logicUnit.
		 * Adding the codeLine and VarValues adapters to the logicUnit.
		 */
		logics = new CodeRunningLogicUnit();

		ListView codeLines = (ListView)findViewById(R.id.listView_Running_Code);
		CodeRunningLinesAdapter codeRunningLinesAdapter = new CodeRunningLinesAdapter(this, android.R.layout.simple_list_item_1, logics.getRunningCodeLines());
		codeLines.setAdapter(codeRunningLinesAdapter);	

		ListView listView_VarValues= (ListView)findViewById(R.id.listView_Running_VarValues);
		VarValuesAdapter varValuesLinesAdapter = new VarValuesAdapter(this, android.R.layout.simple_list_item_1, logics.getValuesList());
		listView_VarValues.setAdapter(varValuesLinesAdapter);	

		//Retrieve all preferences.
		SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		/*
		 * Initialize the graphicUnit.
		 * Adding the gameView to the graphicUnit.
		 */
		graphics = new CodeRunningGraphicUnit(codeRunningLinesAdapter, varValuesLinesAdapter, gameView);

		// registering the managers to the levelManager instance.
		LevelManager.getInstance().registerCodeRunningManager(new CodeRunningManager(logics, graphics));

		gameViewLayout = (LinearLayout) findViewById(R.id.LinearLayout_Game);
	}

	@Override
	protected void onResume() {
		super.onResume();

		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		int cps = SP.getInt(Constants.CPS_KEY,Constants.DEFAULT_CPS);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		TestCase testCaseToShow = null;

		/*
		 * Conduct the testing of the code for all of the Configurations.
		 * Choosing a proper TestCase to show in the CodeRunning display.
		 * If there is no Failing tests, the default (first) is chosen.
		 */

		List<TestCase> tests = LevelManager.getInstance().runCodeTests();

		for (TestCase testCase : tests) 
			if(!LevelManager.getInstance().checkWin(testCase)){

				// pick the failing test.
				testCaseToShow = testCase;
				break;
			}

		// default (first).
		if(testCaseToShow == null)
			testCaseToShow = tests.get(0);

		LevelManager.getInstance().getScenario().setCurrentConfig(testCaseToShow.getConfig());

		/*
		 * Creating the gameView and adding it to the activity.
		 * using FPS and CPS in the creation process. 
		 */

		gameView = LevelManager.getInstance().getScenario().generateGameView(this, fps, animation);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		gameViewLayout.removeAllViews();
		gameViewLayout.addView(gameView);

		graphics.setGameView(gameView);

		/*
		 * Creating the CodePLayer.
		 * using the chosen TestCase and the gameView.
		 */

		player = new CodePlayer(this, /*((ImageButton)findViewById(R.id.button_PlayPause))*/ cps, testCaseToShow, gameView);

		player.display();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.running_screen_menu, menu);
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
	public void onBackPressed() {
		
		//Back button was clicked - launch the level display activity.

		Intent intent = new Intent(this, ScenraioDisplyActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish(); 
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		//Start button was clicked.
		case R.id.button_startSnapshot:
			player.startSnapshot();
			break;

			//Prev button was clicked.
		case R.id.button_prevSnapshot:
			player.prevSnapshot();
			break;

			//Play\Pause button was clicked.
		case R.id.button_PlayPause:
			player.togglePlay();
			break;

			//Next button was clicked.
		case R.id.button_nextSnapshot:
			player.nextSnapshot();
			break;

			//GotoEnd button was clicked.
		case R.id.button_endSnapshot:
			player.endSnapshot();
			break;

		default:
			break;
		}

	}

}
