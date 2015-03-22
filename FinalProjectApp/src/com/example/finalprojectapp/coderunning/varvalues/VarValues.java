package com.example.finalprojectapp.coderunning.varvalues;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

}
