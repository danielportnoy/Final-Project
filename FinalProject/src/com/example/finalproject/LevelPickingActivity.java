package com.example.finalproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class LevelPickingActivity extends Activity implements OnClickListener{

	private SharedPreferences.Editor editor;
	private SharedPreferences settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_picking);

		settings = 	getSharedPreferences(Constants.SHARED_PREFERANCES, MODE_PRIVATE);
		editor = settings.edit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_picking, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent(this, GameScreenActivity.class);

		switch (v.getId()) {
		case R.id.buttonLEVEL1:
			intent.putExtra(Constants.LEVEL_NUMBER_EXTRA, 1);
			editor.putInt(Constants.LEVEL_NUMBER_EXTRA, 1);
			editor.commit();
			break;
		case R.id.buttonLEVEL2:
			intent.putExtra(Constants.LEVEL_NUMBER_EXTRA, 2);
			editor.putInt(Constants.LEVEL_NUMBER_EXTRA, 2);
			editor.commit();
			break;
		case R.id.buttonLEVEL3:
			intent.putExtra(Constants.LEVEL_NUMBER_EXTRA, 3);
			editor.putInt(Constants.LEVEL_NUMBER_EXTRA, 3);
			editor.commit();
			break;

		default:
			break;
		}

		startActivity(intent);
	}

}
