package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.scenario.concrete.Level1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LevelPickingActivity extends Activity implements OnClickListener{

	/*private SharedPreferences.Editor editor;
	private SharedPreferences settings;*/

	private LevelManager levelManager = LevelManager.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_picking);

		/*settings = 	getSharedPreferences(Constants.SHARED_PREFERANCES, MODE_PRIVATE);
		editor = settings.edit();*/
		
		// TODO temporary
		((Button)findViewById(R.id.buttonLEVEL2)).setEnabled(false);
		((Button)findViewById(R.id.buttonLEVEL3)).setEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_picking_menu, menu);
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
	public void onClick(View v) {

		Intent intent = new Intent(this, ScenraioDisplyActivity.class);

		switch (v.getId()) {
		case R.id.buttonLEVEL1:

			levelManager.reset();
			levelManager.loadScenario(new Level1());

			break;
		case R.id.buttonLEVEL2:
			
			levelManager.reset();
			
			break;
		case R.id.buttonLEVEL3:
		
			levelManager.reset();
			
			break;

		default:
			break;
		}

		startActivity(intent);
		//finish(); TODO 
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		
		Intent intent = new Intent(this, MainManuActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}
