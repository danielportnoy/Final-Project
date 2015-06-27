package com.example.finalprojectapp.codewriting.managment;

import com.example.finalprojectapp.codewriting.adapter.OptionsAdapter;
import com.example.finalprojectapp.codewriting.adapter.CodeWritingLinesAdapter;

/**
 * Manages all code writing graphic visualization and display.
 * @author daniel portnoy
 *
 */
public class CodeWritingGraphicUnit {

	private CodeWritingLinesAdapter codeWritingLinesAdapter;
	private OptionsAdapter optionsAdapter;

	public CodeWritingGraphicUnit(CodeWritingLinesAdapter codeWritingLinesAdapter, OptionsAdapter optionsAdapter) {
		this.codeWritingLinesAdapter = codeWritingLinesAdapter;
		this.optionsAdapter = optionsAdapter;
	}

	/**
	 * Displays the code options on the screen.
	 */
	public void updateOptions() {
		optionsAdapter.notifyDataSetChanged();
	}

	/**
	 * Displays the code lines on the screen.
	 */
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
