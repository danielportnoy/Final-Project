package com.example.logic.grammar;

import java.util.ArrayList;

import com.example.logic.optionmenu.OptionFilter;


public abstract class Pattern {
		
	private ArrayList<PlaceHolder> placeHolders;
		
	public Pattern(int numberOfElements ) {
		
		this.placeHolders = new ArrayList<PlaceHolder>(numberOfElements);
		
		for (int i = 0; i < numberOfElements ; i++) {
			placeHolders.add(new PlaceHolder(new OptionFilter(null),null , this));
		}
	}

	public ArrayList<PlaceHolder> getPlaceHolders() {
		return placeHolders;
	}

	public void setPlaceHolders(ArrayList<PlaceHolder> placeHolders) {
		this.placeHolders = placeHolders;
	}

	@Override
	public abstract String toString();
}
