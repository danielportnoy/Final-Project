package com.example.finalprojectapp.coderunning.coderunning_components;

import java.util.ArrayList;
import java.util.List;

/**
 * Hold data to display a code part.
 * @author daniel portnoy
 *
 */
public class CodeRunningPart {

	private boolean tab = false;
	private boolean newline = false;
	private boolean highlighted = false;

	private String text = null;

	/**
	 * Code part might be a tab, newLine, text.
	 * Also might be highlighted.
	 * @param tab
	 * @param newline
	 * @param highlighted
	 * @param text
	 */
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

	/**
	 * Adds 'tabs' in the code lines where needed. Only for displaying purposes.
	 * @param codeParts
	 * @return A list of Code parts that is tabbed correctly.
	 */
	public static List<CodeRunningPart> tabber(List<CodeRunningPart> codeParts){
				
		List<CodeRunningPart> tabbedCodeParts = new ArrayList<CodeRunningPart>();
		
		// add an initial tab at the start.
		tabbedCodeParts.add(0,  new CodeRunningPart(true, false, false, null));

		for (int i = 0; i < codeParts.size(); i++) {
			tabbedCodeParts.add(codeParts.get(i));
			
			// add tab after a newLine.
			if(codeParts.get(i).isNewline())
				tabbedCodeParts.add(new CodeRunningPart(true, false, false, null));
		}
		
		return tabbedCodeParts;
		
	}
}
