package com.example.finalprojectapp.coderunning;

import com.example.finalprojectapp.Constants;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.activities.ScenraioDisplyActivity;
import com.example.finalprojectapp.activities.SettingsActivity;
import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.managment.CodeRunningGraphicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningLogicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class CodeRunningActivity extends Activity implements OnClickListener {

	private MySurfaceView gameView;
	private CodePlayer player;
	
	private SharedPreferences SP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code_running);

		CodeRunningLogicUnit logics = new CodeRunningLogicUnit();

		ListView codeLines = (ListView)findViewById(R.id.listView_Running_Code);
		CodeRunningLinesAdapter codeRunningLinesAdapter = new CodeRunningLinesAdapter(this, android.R.layout.simple_list_item_1, logics.getRunningCodeLines());
		codeLines.setAdapter(codeRunningLinesAdapter);	

		SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);

		gameView = LevelManager.getInstance().getScenario().generateGameView(this, fps, animation);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		CodeRunningGraphicUnit graphics = new CodeRunningGraphicUnit(codeRunningLinesAdapter , gameView);
		LevelManager.getInstance().registerCodeRunningManager(new CodeRunningManager(logics, graphics));

		LinearLayout gameViewLayout = (LinearLayout) findViewById(R.id.LinearLayout_Running_Game);
		gameViewLayout.addView(gameView);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		int fps = SP.getInt(Constants.FPS_KEY,Constants.DEFAULT_FPS);
		int cps = SP.getInt(Constants.CPS_KEY,Constants.DEFAULT_CPS);
		boolean animation = SP.getBoolean(Constants.ANIMATION_KEY, Constants.DEFAULT_ANIMATION);
		
		gameView.setFps(fps);
		gameView.setAnimating(animation);
		
		LevelManager.getInstance().runCode();
		
		player = new CodePlayer(LevelManager.getInstance().getNumOfSnapshots() , this, ((Button)findViewById(R.id.button_PlayPause)),cps);

		player.start();
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
	        // Settings option clicked.
	    	Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, ScenraioDisplyActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish(); 
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_startSnapshot:
			player.startSnapshot();
			break;
		case R.id.button_prevSnapshot:
			player.prevSnapshot();
			break;
		case R.id.button_PlayPause:
			player.togglePlay();
			break;
		case R.id.button_nextSnapshot:
			player.nextSnapshot();
			break;
		case R.id.button_endSnapshot:
			player.endSnapshot();
			break;

		default:
			break;
		}

	}

}
