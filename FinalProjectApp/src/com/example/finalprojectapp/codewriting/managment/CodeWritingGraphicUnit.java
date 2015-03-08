package com.example.finalprojectapp.codewriting.managment;

import com.example.finalprojectapp.codewriting.adapter.OptionsAdapter;
import com.example.finalprojectapp.codewriting.adapter.CodeLinesAdapter;

public class CodeWritingGraphicUnit {

	private CodeLinesAdapter codeLinesAdapter;
	private OptionsAdapter optionsAdapter;

	public CodeWritingGraphicUnit(CodeLinesAdapter codeLinesAdapter, OptionsAdapter optionsAdapter) {
		this.codeLinesAdapter = codeLinesAdapter;
		this.optionsAdapter = optionsAdapter;
	}

	public void updateOptions() {
		optionsAdapter.notifyDataSetChanged();
	}

	public void updateCodeLines() {
		codeLinesAdapter.notifyDataSetChanged();
	}

	public CodeLinesAdapter getCodeLinesAdapter() {
		return codeLinesAdapter;
	}

	public OptionsAdapter getOptionsAdapter() {
		return optionsAdapter;
	}
	
	

}
