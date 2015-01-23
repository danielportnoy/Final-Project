package com.example.logic.grammar;

import com.example.logic.codescreen.InputMethod;
import com.example.logic.optionmenu.OptionFilter;

public class PlaceHolder {
	
	private Pattern parent;

	private Pattern pattern;
	
	private OptionFilter optionFilter;
	private InputMethod inputMethod;

	public PlaceHolder(OptionFilter optionFilter ,  InputMethod inputMethod , Pattern parent) {
		
		this.parent = parent;

		this.pattern = null;
		
		this.optionFilter = optionFilter;
		this.inputMethod = inputMethod;
	}
	
	public Pattern getParent() {
		return parent;
	}

	public void setParent(Pattern parent) {
		this.parent = parent;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public OptionFilter getOptionFilter() {
		return optionFilter;
	}

	public void setOptionFilter(OptionFilter optionsFilter) {
		this.optionFilter = optionsFilter;
	}
	
	public InputMethod getInputMethod() {
		return inputMethod;
	}

	public void setInputMethod(InputMethod inputMethod) {
		this.inputMethod = inputMethod;
	}

	public boolean validate() {
		return true;//TODO
	}

	@Override
	public String toString() {
		if(pattern == null)
			return "< " + optionFilter.toString() + " >";
		else
			return pattern.toString();
	}

}
