package com.example.finalprojectapp.codewriting.codewriting_components;

import java.util.ArrayList;
import java.util.List;

import com.example.finalprojectapp.node.Node;
import com.example.finalprojectapp.node.Setter;

public class CodeWritingPart {

	private boolean tab = false;
	private boolean newline = false;

	private String text = null;
	private Setter setter = null;

	private Node makerNode = null;
	private boolean editable = false;

	public CodeWritingPart(boolean tab , boolean newline , String text , Setter setter, Node makerNode) {
		this.tab = tab;
		this.newline = newline;
		this.text = text;
		this.setter = setter;
		this.makerNode = makerNode;
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

	public Node getMakerNode() {
		return makerNode;
	}
	
	public boolean isEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public static List<CodeWritingPart> tabber(List<CodeWritingPart> codeParts){

		List<CodeWritingPart> tabbedCodeParts = new ArrayList<CodeWritingPart>();

		tabbedCodeParts.add(0,  new CodeWritingPart(true, false, null, null,codeParts.get(0).getMakerNode()));

		for (int i = 0; i < codeParts.size(); i++) {
			tabbedCodeParts.add(codeParts.get(i));

			if(codeParts.get(i).isNewline())
				tabbedCodeParts.add(new CodeWritingPart(true, false, null, null,codeParts.get(i).getMakerNode()));
		}

		return tabbedCodeParts;

	}
}