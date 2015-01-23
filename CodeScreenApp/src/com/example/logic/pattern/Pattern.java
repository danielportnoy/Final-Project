package com.example.logic.pattern;

import java.util.ArrayList;

import com.example.logic.placeholder.PlaceHolder;

public abstract class Pattern {
		
	private ArrayList<PlaceHolder> placeHolders;
		
	public Pattern(int numberOfElements ) {
		
		this.placeHolders = new ArrayList<PlaceHolder>(numberOfElements);
		
		for (int i = 0; i < numberOfElements ; i++) {
			placeHolders.add(new PlaceHolder(this,null,null,null,null));
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
	
	public boolean validate(){
		
		for (PlaceHolder PlaceHolder : placeHolders) {
			if(!PlaceHolder.validate())
				return false;
		}

		return true;
	}
}
