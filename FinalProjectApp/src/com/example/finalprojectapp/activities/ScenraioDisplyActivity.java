package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.CodeRunningActivity;
import com.example.finalprojectapp.codewriting.CodeWritingActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ScenraioDisplyActivity extends Activity implements OnClickListener {

	/*
	private int levelNumber;
	private TextView levelTextView = null;
	 */
	private LevelManager levelManager = LevelManager.getInstance();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenario_disply);

		/*
		levelNumber = getIntent().getIntExtra(Constants.LEVEL_NUMBER_EXTRA, 1);

		levelTextView = (TextView)findViewById(R.id.TextView_LevelNumber);
		levelTextView.setText(levelNumber+"");
		 */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_screen, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		Button b = (Button) findViewById(R.id.buttonRun);

		if(!levelManager.isCodeLinesValid())
			b.setEnabled(false);
		else
			b.setEnabled(true);
	}

	@Override
	public void onClick(View v) {

		Intent intent = null;

		switch (v.getId()) {

		case R.id.buttonRun:
			intent = new Intent(this, CodeRunningActivity.class);
			startActivity(intent);
			break;

		case R.id.buttonCode:
			intent = new Intent(this, CodeWritingActivity.class);
			startActivity(intent);
			break;

		case R.id.buttonReset:
			Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}
