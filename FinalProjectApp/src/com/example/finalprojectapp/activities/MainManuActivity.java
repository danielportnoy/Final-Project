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
			//Play button was clicked - launch wingPicking activity.
			
			Intent intent = new Intent(this, WingPickingActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.buttonSETTINGS){
			//Settings button was clicked - launch settings activity.

			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.buttonEXIT){
			//Exit button was clicked - exit application.

			System.exit(0);
		}
	}
	
	@Override
	public void onBackPressed() {
		//Back button was clicked - exit application.

		super.onBackPressed();
		System.exit(0);
	}

}
