package com.example.finalprojectapp.coderunning;

import com.example.finalprojectapp.DrawGameView;
import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.coderunning.adapter.CodeRunningLinesAdapter;
import com.example.finalprojectapp.coderunning.managment.CodeRunningGraphicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningLogicUnit;
import com.example.finalprojectapp.coderunning.managment.CodeRunningManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class CodeRunningActivity extends Activity implements OnClickListener {

	private int snapshotNum;
	private CodeRunningManager manager;
	private DrawGameView dgv;
	
	private LevelManager levelManager = LevelManager.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code_running);	

		CodeRunningLogicUnit logics = new CodeRunningLogicUnit();

		ListView codeLines = (ListView)findViewById(R.id.listView_Running_Code);
		CodeRunningLinesAdapter codeRunningLinesAdapter = new CodeRunningLinesAdapter(this, android.R.layout.simple_list_item_1, logics.getRunningCodeLines());
		codeLines.setAdapter(codeRunningLinesAdapter);	

		CodeRunningGraphicUnit graphics = new CodeRunningGraphicUnit(codeRunningLinesAdapter);

		manager = new CodeRunningManager(logics, graphics);

		levelManager.registerCodeRunningManager(manager);

		levelManager.getRootNode().run();

		snapshotNum = 0;

		LinearLayout l = (LinearLayout) findViewById(R.id.LinearLayout_Running_Game);
		dgv = levelManager.getScenario().getDrawGameViewInstance(this, null);
		dgv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		l.addView(dgv);

		manager.refresh(snapshotNum , dgv);

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
		case R.id.button_prevSnapshot:
			if(snapshotNum > 0)
				snapshotNum--;
			manager.refresh(snapshotNum , dgv);
			break;
		case R.id.button_nextSnapshot:
			if(snapshotNum < manager.getLogics().getSnapshots().size() - 1)
				snapshotNum++;
			manager.refresh(snapshotNum , dgv);
			break;

		default:
			break;
		}

	}

}
