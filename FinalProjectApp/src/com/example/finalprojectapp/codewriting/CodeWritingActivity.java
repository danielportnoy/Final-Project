package com.example.finalprojectapp.codewriting;

import com.example.finalprojectapp.LevelManager;
import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codewriting.adapter.OptionsAdapter;
import com.example.finalprojectapp.codewriting.adapter.CodeWritingLinesAdapter;
import com.example.finalprojectapp.codewriting.managment.CodeWritingGraphicUnit;
import com.example.finalprojectapp.codewriting.managment.CodeWritingLogicUnit;
import com.example.finalprojectapp.codewriting.managment.CodeWritingManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class CodeWritingActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code_writing);

		CodeWritingLogicUnit logics = new CodeWritingLogicUnit();

		ListView codeLines = (ListView)findViewById(R.id.listView_Writing_Code);
		CodeWritingLinesAdapter codeLinesAdapter = new CodeWritingLinesAdapter(this, android.R.layout.simple_list_item_1, logics.getWritingCodeLines());
		codeLines.setAdapter(codeLinesAdapter);	

		ListView options = (ListView)findViewById(R.id.listView_Options);
		OptionsAdapter optionsAdapter = new OptionsAdapter(this, android.R.layout.simple_list_item_1, logics.getCurrentOptions());
		options.setAdapter(optionsAdapter);	

		CodeWritingGraphicUnit graphics = new CodeWritingGraphicUnit(codeLinesAdapter, optionsAdapter);

		CodeWritingManager manager = new CodeWritingManager(logics, graphics);

		LevelManager.getInstance().registerCodeWritingManager(manager);
		
		manager.refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.code_screen, menu);
		return true;
	}
}
