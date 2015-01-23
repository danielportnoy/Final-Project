package com.example.codescreenapp;

import com.example.codescreenapp.R;
import com.example.logic.Manager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class CodeScreenActivity extends Activity {

	private ListView options = null;
	private OptionsAdapter optionsAdapter = null;

	private ListView codeLines = null;
	private CodeLinesAdapter codeLinesAdapter = null;

	private Manager manager = Manager.getInstance();
	private GraphicsUnit gu = GraphicsUnit.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_code_screen);

		initOptionsMenu();
		initCodeLines();

		gu.setOptionsMenuAdapter(optionsAdapter);
		gu.setCodeLinesAdapter(codeLinesAdapter);
	}

	private void initCodeLines() {
		codeLines = (ListView)findViewById(R.id.listView_Code);
		codeLinesAdapter = new CodeLinesAdapter(this, android.R.layout.simple_list_item_1, manager.getCodeScreen().getStatements());
		codeLines.setAdapter(codeLinesAdapter);	
	}

	private void initOptionsMenu() {
		options = (ListView)findViewById(R.id.listView_Options);
		optionsAdapter = new OptionsAdapter(this, android.R.layout.simple_list_item_1, manager.getOptionMenu().getOptions());
		options.setAdapter(optionsAdapter);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.code_screen, menu);
		return true;
	}

}
