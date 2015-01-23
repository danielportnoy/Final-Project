package com.example.logic.placeholder;

import com.example.logic.codescreen.InputMethod;
import com.example.logic.optionmenu.OptionFilter;
import com.example.logic.pattern.Pattern;

public class PlaceHolder {

	private Pattern parent;

	private Pattern pattern;

	private PlaceHolderType placeholderType;
	private OptionFilter optionFilter;
	private InputMethod inputMethod;

	public PlaceHolder(Pattern parent , Pattern pattern, PlaceHolderType placeholderType , OptionFilter optionFilter ,  InputMethod inputMethod) {

		this.parent = parent;
		this.pattern = pattern;

		this.placeholderType = placeholderType;
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

	public PlaceHolderType getPlaceholderType() {
		return placeholderType;
	}

	public void setPlaceholderType(PlaceHolderType placeholderType) {
		this.placeholderType = placeholderType;
	}

	public OptionFilter getOptionFilter() {
		return optionFilter;
	}

	public void setOptionFilter(OptionFilter optionFilter) {
		this.optionFilter = optionFilter;
	}

	public InputMethod getInputMethod() {
		return inputMethod;
	}

	public void setInputMethod(InputMethod inputMethod) {
		this.inputMethod = inputMethod;
	}

	public boolean validate() {
		if(pattern != null)
			return pattern.validate();
		else
			return false;
	}

	@Override
	public String toString() {
		if(pattern == null)
			return "< " + placeholderType.toString() + " >";
		else
			return pattern.toString();
	}
}
