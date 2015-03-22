package com.example.finalprojectapp.codewriting.codewriting_components;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.node.Setter;

public class CodeWritingPart {

	private boolean tab = false;
	private boolean newline = false;

	private String text = null;
	private Setter setter = null;

	public CodeWritingPart(boolean tab , boolean newline , String text , Setter setter) {
		this.tab = tab;
		this.newline = newline;
		this.text = text;
		this.setter = setter;
	}

	public boolean isTab() {
		return tab;
	}

	public boolean isNewline() {
		return newline;
	}

	public String getText() {
		return text;
	}

	public Setter getSetter() {
		return setter;
	}
	
	public static List<CodeWritingPart> tabber(List<CodeWritingPart> codeParts){
				
		List<CodeWritingPart> tabbedCodeParts = new ArrayList<CodeWritingPart>();
		
		tabbedCodeParts.add(0,  new CodeWritingPart(true, false, null, null));

		for (int i = 0; i < codeParts.size(); i++) {
			tabbedCodeParts.add(codeParts.get(i));
			
			if(codeParts.get(i).isNewline())
				tabbedCodeParts.add(new CodeWritingPart(true, false, null, null));
		}
		
		return tabbedCodeParts;
		
	}
}
