package com.example.finalprojecttestapp;

import com.example.finalprojecttestapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

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
			//Toast.makeText(this, "play", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, LevelPickingActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.buttonOPTIONS){
			Toast.makeText(this, "options", Toast.LENGTH_SHORT).show();
		}
		else if(v.getId() == R.id.buttonEXIT){
			//Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
			System.exit(0);
		}
	}

}
