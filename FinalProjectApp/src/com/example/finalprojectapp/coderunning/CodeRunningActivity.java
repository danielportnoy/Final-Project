package com.example.finalprojectapp.coderunning;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CodeRunningActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code_running);
		
		CodeRunningManager manager = new CodeRunningManager();
		
		LevelManager.getInstance().registerCodeRunningManager(manager);
		//TODO
		LevelManager.getInstance().getRootNode().run();
		manager.getSnapshots();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.running_screen, menu);
		return true;
	}

}
