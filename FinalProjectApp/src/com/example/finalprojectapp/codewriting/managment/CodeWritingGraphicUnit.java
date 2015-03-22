package com.example.finalprojectapp.codewriting.managment;

import com.example.finalprojectapp.codewriting.adapter.OptionsAdapter;
import com.example.finalprojectapp.codewriting.adapter.CodeWritingLinesAdapter;

public class CodeWritingGraphicUnit {

	private CodeWritingLinesAdapter codeWritingLinesAdapter;
	private OptionsAdapter optionsAdapter;

	public CodeWritingGraphicUnit(CodeWritingLinesAdapter codeWritingLinesAdapter, OptionsAdapter optionsAdapter) {
		this.codeWritingLinesAdapter = codeWritingLinesAdapter;
		this.optionsAdapter = optionsAdapter;
	}

	public void updateOptions() {
		optionsAdapter.notifyDataSetChanged();
	}

	public void updateCodeWritingLines() {
		codeWritingLinesAdapter.notifyDataSetChanged();
	}

	public CodeWritingLinesAdapter getCodeWritingLinesAdapter() {
		return codeWritingLinesAdapter;
	}

	public OptionsAdapter getOptionsAdapter() {
		return optionsAdapter;
	}
	
	

}
