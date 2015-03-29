package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.scenario.concrete.Level1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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
		getMenuInflater().inflate(R.menu.level_picking, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent(this, ScenraioDisplyActivity.class);

		switch (v.getId()) {
		case R.id.buttonLEVEL1:
			/*intent.putExtra(Constants.LEVEL_NUMBER_EXTRA, 1);
			editor.putInt(Constants.LEVEL_NUMBER_EXTRA, 1);
			editor.commit();*/

			levelManager.reset();
			levelManager.loadScenario(new Level1());

			break;
		case R.id.buttonLEVEL2:
			/*intent.putExtra(Constants.LEVEL_NUMBER_EXTRA, 2);
			editor.putInt(Constants.LEVEL_NUMBER_EXTRA, 2);
			editor.commit();*/

			levelManager.reset();

			break;
		case R.id.buttonLEVEL3:
			/*intent.putExtra(Constants.LEVEL_NUMBER_EXTRA, 3);
			editor.putInt(Constants.LEVEL_NUMBER_EXTRA, 3);
			editor.commit();*/

			levelManager.reset();

			break;

		default:
			break;
		}

		startActivity(intent);
	}

}
