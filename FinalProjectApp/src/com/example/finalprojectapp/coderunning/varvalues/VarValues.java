package com.example.finalprojectapp.coderunning.varvalues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Pair;

public class VarValues {

	private Map<String, Integer> intValues = new HashMap<String, Integer>();
	private Map<String, Boolean> boolValues = new HashMap<String, Boolean>();

	public VarValues(VarValues other){
		intValues.putAll(other.intValues);
		boolValues.putAll(other.boolValues);
	}

	public VarValues() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(VarValues other) {

		if (intValues.size() != other.intValues.size() || boolValues.size() != other.boolValues.size())
			return false;

		Iterator<Entry<String, Integer>> thisIt1 = intValues.entrySet().iterator();
		Iterator<Entry<String, Integer>> otherIt1 = other.intValues.entrySet().iterator();

		for (int i = 0; i < intValues.size(); i++) {
			Entry<String, Integer> thisEntry = thisIt1.next();
			Entry<String, Integer> otherEntry = otherIt1.next();

			if(thisEntry.getKey() != otherEntry.getKey() ||thisEntry.getValue() != otherEntry.getValue())
				return false;
		}

		Iterator<Entry<String, Boolean>> thisIt2 = boolValues.entrySet().iterator();
		Iterator<Entry<String, Boolean>> otherIt2 = other.boolValues.entrySet().iterator();

		for (int i = 0; i < boolValues.size(); i++) {
			Entry<String, Boolean> thisEntry = thisIt2.next();
			Entry<String, Boolean> otherEntry = otherIt2.next();

			if(thisEntry.getKey() != otherEntry.getKey() ||thisEntry.getValue() != otherEntry.getValue())
				return false;
		}

		return true;
	}

	public Map<String, Integer> getIntValues() {
		return intValues;
	}

	public Map<String, Boolean> getBoolValues() {
		return boolValues;
	}

	public void removeExtra(List<String> identifiers){

		for(Iterator<Map.Entry<String, Integer>> it = intValues.entrySet().iterator(); it.hasNext(); ) {
			Entry<String, Integer> entry = it.next();
			if(!identifiers.contains(entry.getKey()))
				it.remove();
		}

		for(Iterator<Map.Entry<String, Boolean>> it = boolValues.entrySet().iterator(); it.hasNext(); ) {
			Entry<String, Boolean> entry = it.next();
			if(!identifiers.contains(entry.getKey()))
				it.remove();
		}
	}
	
	public List<Pair<String, String>> toList(){
		
		List<Pair<String, String>> res = new ArrayList<Pair<String,String>>();
		
		for(Iterator<Map.Entry<String, Integer>> it = intValues.entrySet().iterator(); it.hasNext(); ) {
			Entry<String, Integer> entry = it.next();
			res.add(new Pair<String, String>(entry.getKey(), entry.getValue().toString()));
		}

		for(Iterator<Map.Entry<String, Boolean>> it = boolValues.entrySet().iterator(); it.hasNext(); ) {
			Entry<String, Boolean> entry = it.next();
			res.add(new Pair<String, String>(entry.getKey(), entry.getValue().toString()));
		}
		
		return res;		
	}

}
