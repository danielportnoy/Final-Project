package com.example.finalprojectapp.coderunning;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.managment.CodeRunningGraphicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningLogicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;
import com.example.finalprojectapp.scenario.MySurfaceView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class CodeRunningActivity extends Activity implements OnClickListener {

	private MySurfaceView gameView;
	private CodePlayer player;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code_running);

		CodeRunningLogicUnit logics = new CodeRunningLogicUnit();

		ListView codeLines = (ListView)findViewById(R.id.listView_Running_Code);
		CodeRunningLinesAdapter codeRunningLinesAdapter = new CodeRunningLinesAdapter(this, android.R.layout.simple_list_item_1, logics.getRunningCodeLines());
		codeLines.setAdapter(codeRunningLinesAdapter);	

		gameView = LevelManager.getInstance().getScenario().generateGameView(this);
		gameView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

		CodeRunningGraphicUnit graphics = new CodeRunningGraphicUnit(codeRunningLinesAdapter , gameView);
		LevelManager.getInstance().registerCodeRunningManager(new CodeRunningManager(logics, graphics));

		LinearLayout gameViewLayout = (LinearLayout) findViewById(R.id.LinearLayout_Running_Game);
		gameViewLayout.addView(gameView);

		LevelManager.getInstance().getScenario().reset();

		LevelManager.getInstance().getRootNode().run();

		player = new CodePlayer(LevelManager.getInstance().getCodeRunningManager().getLogics().getSnapshots().size() , this);
		player.display();
		
		((Button)findViewById(R.id.button_PlayPause)).setText("Play");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.running_screen, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_startSnapshot:
			player.startSnapshot();
			break;
		case R.id.button_prevSnapshot:
			player.prevSnapshot();
			break;
		case R.id.button_PlayPause:
			if(player.isPlaying())
				((Button)findViewById(R.id.button_PlayPause)).setText("Play");
			else
				((Button)findViewById(R.id.button_PlayPause)).setText("Pause");
			player.togglePlay();
			break;
		case R.id.button_nextSnapshot:
			player.nextSnapshot();
			break;
		case R.id.button_endSnapshot:
			player.endSnapshot();
			break;

		default:
			break;
		}

	}

}
