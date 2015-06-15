package com.example.finalprojectapp.activities;

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
		if( v.getId() == R.id.button_Tutorial){
			Intent intent = new Intent(this, TutorialActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.button_Maze_Wing){
			Intent intent = new Intent(this, MazeWingActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.button_TBD_Wing){
			//TODO
		}
	}

}
