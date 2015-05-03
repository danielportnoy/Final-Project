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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenario_disply);

		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		boolean levelInstructions = SP.getBoolean(Constants.LI_KEY, Constants.DEFAULT_LI);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		gameView = LevelManager.getInstance().getScenario().generateGameView(this, fps, animation);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		LinearLayout gameViewLayout = (LinearLayout) findViewById(R.id.LinearLayout_GameScreen);
		gameViewLayout.addView(gameView);

		if(levelInstructions){
			AlertDialog.Builder builder = Android_Utils.getStartGameDialog(this,
					Constants.LEVEL_START_TEXT, LevelManager.getInstance().getScenario().getLevelText());

			builder.create().show();
		}
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
	        // Settings option clicked.
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

		Button b = (Button) findViewById(R.id.buttonRun);

		if(!LevelManager.getInstance().isCodeLinesValid())
			b.setEnabled(false);
		else
			b.setEnabled(true);

		gameView.reset();
	}

	@Override
	public void onClick(View v) {

		Intent intent = null;

		switch (v.getId()) {

		case R.id.buttonRun:
			intent = new Intent(this, CodeRunningActivity.class);
			startActivity(intent);
			//finish(); TODO
			break;

		case R.id.buttonCode:
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
		
		Intent intent = new Intent(this, LevelPickingActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}
}
