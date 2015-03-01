package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codescreenlogic.CodeScreenManager;
import com.example.finalprojectapp.constants.Constants;
import com.example.finalprojectapp.gamescreenlogic.GameScreenManager;
import com.example.finalprojectapp.gamescreenlogic.Level1;
import com.example.finalprojectapp.gamescreenlogic.Scenario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameScreenActivity extends Activity implements OnClickListener {

	private int levelNumber;

	private TextView levelTextView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_screen);

		levelNumber = getIntent().getIntExtra(Constants.LEVEL_NUMBER_EXTRA, 1);

		levelTextView = (TextView)findViewById(R.id.TextView_LevelNumber);
		levelTextView.setText(levelNumber+"");

		GameScreenManager.reset();
		CodeScreenManager.reset();

		initScenario(levelNumber);

	}

	@Override
	protected void onResume() {
		super.onResume(); //TODO

		Button b = (Button) findViewById(R.id.buttonRun);
		
		if(!CodeScreenManager.getInstance().getCodeScreen().isCodeScreenValid())
			b.setEnabled(false);
		else
			b.setEnabled(true);
	}

	private void initScenario(int levelNumber) {

		switch (levelNumber) {
		case 1:
			Scenario level1 = new Level1();
			GameScreenManager.getInstance().setScenario(level1);
			CodeScreenManager.getInstance().setOptions(level1.getAvailableOptions());
			break;
		case 2:

			break;
		case 3:

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_screen, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.buttonRun:
			Toast.makeText(this, "run", Toast.LENGTH_SHORT).show();
			break;
		case R.id.buttonCode:
			//Toast.makeText(this, "code", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, CodeScreenActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonReset:
			//Toast.makeText(this, "reset", Toast.LENGTH_SHORT).show();
			GameScreenManager.reset();
			break;
		default:
			break;
		}
	}
}
