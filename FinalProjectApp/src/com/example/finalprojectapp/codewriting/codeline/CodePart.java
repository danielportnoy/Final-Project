package com.example.finalprojectapp.codewriting.codeline;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.node.Setter;

public class CodePart {

	private boolean tab = false;
	private boolean newline = false;

	private String text = null;
	private Setter setter = null;

	public CodePart(boolean tab , boolean newline , String text , Setter setter) {
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
	
	public static List<CodePart> tabber(List<CodePart> codeParts){
				
		List<CodePart> tabbedCodeParts = new ArrayList<CodePart>();
		
		tabbedCodeParts.add(0,  new CodePart(true, false, null, null));

		for (int i = 0; i < codeParts.size(); i++) {
			tabbedCodeParts.add(codeParts.get(i));
			
			if(codeParts.get(i).isNewline())
				tabbedCodeParts.add(new CodePart(true, false, null, null));
		}
		
		return tabbedCodeParts;
		
	}
}
