package com.example.finalprojectapp.coderunning.coderunning_components;

import java.util.ArrayList;
import java.util.List;

public class CodeRunningPart {

	private boolean tab = false;
	private boolean newline = false;
	private boolean highlighted = false;

	private String text = null;

	public CodeRunningPart(boolean tab , boolean newline , boolean highlighted ,String text) {
		this.tab = tab;
		this.newline = newline;
		this.highlighted = highlighted;
		this.text = text;
	}

	public boolean isTab() {
		return tab;
	}

	public boolean isNewline() {
		return newline;
	}

	public boolean isHighlighted() {
		return highlighted;
	}
	
	public String getText() {
		return text;
	}

	public static List<CodeRunningPart> tabber(List<CodeRunningPart> codeParts){
				
		List<CodeRunningPart> tabbedCodeParts = new ArrayList<CodeRunningPart>();
		
		tabbedCodeParts.add(0,  new CodeRunningPart(true, false, false, null));

		for (int i = 0; i < codeParts.size(); i++) {
			tabbedCodeParts.add(codeParts.get(i));
			
			if(codeParts.get(i).isNewline())
				tabbedCodeParts.add(new CodeRunningPart(true, false, false, null));
		}
		
		return tabbedCodeParts;
		
	}
}
