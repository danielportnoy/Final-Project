package com.example.finalprojectapp.codescreenlogic;

import com.example.finalprojectapp.codescreenlogic.adapters.CodeLinesAdapter;
import com.example.finalprojectapp.codescreenlogic.adapters.OptionsAdapter;


public class GraphicsUnit {

	private CodeLinesAdapter codeLinesAdapter;
	private OptionsAdapter optionsMenuAdapter;

	private static GraphicsUnit instance = new GraphicsUnit( );

	private GraphicsUnit() {}

	public static GraphicsUnit getInstance( ) {
		return instance;
	}

	public void updateOptionsMenu() {
		optionsMenuAdapter.notifyDataSetChanged();
	}

	public void updateCodeLines() {
		codeLinesAdapter.notifyDataSetChanged();
	}


	public CodeLinesAdapter getCodeLinesAdapter() {
		return codeLinesAdapter;
	}

	public void setCodeLinesAdapter(CodeLinesAdapter codeLinesAdapter) {
		this.codeLinesAdapter = codeLinesAdapter;
	}

	public OptionsAdapter getOptionsMenuAdapter() {
		return optionsMenuAdapter;
	}

	public void setOptionsMenuAdapter(OptionsAdapter optionsMenuAdapter) {
		this.optionsMenuAdapter = optionsMenuAdapter;
	}

}
