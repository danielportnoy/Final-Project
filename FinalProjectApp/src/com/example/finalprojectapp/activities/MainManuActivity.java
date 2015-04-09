package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainManuActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_manu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_manu, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if( v.getId() == R.id.buttonPLAY){
			Intent intent = new Intent(this, LevelPickingActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.buttonSETTINGS){
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.buttonEXIT){
			System.exit(0);
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
	}

}
