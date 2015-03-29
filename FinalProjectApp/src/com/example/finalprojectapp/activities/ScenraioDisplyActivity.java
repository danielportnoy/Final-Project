package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.CodeRunningActivity;
import com.example.finalprojectapp.codewriting.CodeWritingActivity;
import com.example.finalprojectapp.scenario.MySurfaceView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class ScenraioDisplyActivity extends Activity implements OnClickListener {

	private MySurfaceView gameView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenario_disply);
		
		gameView = LevelManager.getInstance().getScenario().generateGameView(this);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		LinearLayout gameViewLayout = (LinearLayout) findViewById(R.id.LinearLayout_GameScreen);
		gameViewLayout.addView(gameView);
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

		if(!LevelManager.getInstance().isCodeLinesValid())
			b.setEnabled(false);
		else
			b.setEnabled(true);
		
		gameView.reset();
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
