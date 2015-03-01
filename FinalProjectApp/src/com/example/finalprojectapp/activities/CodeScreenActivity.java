package com.example.finalprojectapp.activities;

import com.example.finalprojectapp.R;
import com.example.finalprojectapp.codescreenlogic.CodeScreenGraphicsUnit;
import com.example.finalprojectapp.codescreenlogic.CodeScreenManager;
import com.example.finalprojectapp.codescreenlogic.adapters.CodeLinesAdapter;
import com.example.finalprojectapp.codescreenlogic.adapters.OptionsAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class CodeScreenActivity extends Activity {

	private ListView options = null;
	private OptionsAdapter optionsAdapter = null;

	private ListView codeLines = null;
	private CodeLinesAdapter codeLinesAdapter = null;

	private CodeScreenManager csmanager = CodeScreenManager.getInstance();
	private CodeScreenGraphicsUnit gu = CodeScreenGraphicsUnit.getInstance();

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
		codeLinesAdapter = new CodeLinesAdapter(this, android.R.layout.simple_list_item_1, csmanager.getCodeScreen().getCodeLines());
		codeLines.setAdapter(codeLinesAdapter);	
	}

	private void initOptionsMenu() {
		options = (ListView)findViewById(R.id.listView_Options);
		optionsAdapter = new OptionsAdapter(this, android.R.layout.simple_list_item_1, csmanager.getOptionMenu().getCurrentCommands());
		options.setAdapter(optionsAdapter);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.code_screen, menu);
		return true;
	}
}
