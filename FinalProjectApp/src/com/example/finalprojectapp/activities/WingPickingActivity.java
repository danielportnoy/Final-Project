package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class WingPickingActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wing_picking);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wing_picking, menu);
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
		if( v.getId() == R.id.button_Tutorial){

			//Tutorial button was clicked - launch tutorial activity.

			Intent intent = new Intent(this, TutorialActivity.class);
			startActivity(intent);

			LevelManager.lastWingActivityClass = TutorialActivity.class;
		}
		else if(v.getId() == R.id.button_Maze_Wing){

			//Maze button was clicked - launch maze activity.

			Intent intent = new Intent(this, MazeWingActivity.class);
			startActivity(intent);

			LevelManager.lastWingActivityClass = MazeWingActivity.class;
		}
		else if(v.getId() == R.id.button_Inventory_Wing){

			//Inventory button was clicked - launch inventory activity.

			Intent intent = new Intent(this, InventoryWingActivity.class);
			startActivity(intent);

			LevelManager.lastWingActivityClass = InventoryWingActivity.class;
		}
	}

}
