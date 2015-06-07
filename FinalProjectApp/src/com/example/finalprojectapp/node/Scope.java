package com.example.finalprojectapp.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Scope {

	private Map<String, Integer> IntegerIdentifiers;
	private Map<String, Integer> BooleanIdentifiers;

	public Scope() {
		IntegerIdentifiers = new HashMap<String, Integer>();
		BooleanIdentifiers = new HashMap<String, Integer>();
	}

	public void removeIdentifier(int order){

		for(Iterator<Entry<String, Integer>> it = IntegerIdentifiers.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, Integer> entry = it.next();
			if(entry.getValue().equals(order)) {
				it.remove();
			}
		}

		for(Iterator<Entry<String, Integer>> it = BooleanIdentifiers.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, Integer> entry = it.next();
			if(entry.getValue().equals(order)) {
				it.remove();
			}
		}

	}

	public Map<String, Integer> getIntegerIdentifiers() {
		return IntegerIdentifiers;
	}

	public Map<String, Integer> getBooleanIdentifiers() {
		return BooleanIdentifiers;
	}

	private List<String> getIntegerIdentifiersUntil(int until) {
		return getIdentifiersUntil(IntegerIdentifiers, until);
	}

	private List<String> getBooleanIdentifiersUntil(int until) {
		return getIdentifiersUntil(BooleanIdentifiers, until);
	}

	private List<String> getIdentifiersUntil(Map<String, Integer> map , int until){

		List<String> ids = new ArrayList<String>();

		for (Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() < until)
				ids.add(entry.getKey());
		}

		return ids;
	}

	public static List<String> getPrevIdentifiers(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		identifiers.addAll(getBooleanIdentifiersRecursive(n,order));
		identifiers.addAll(getIntegerIdentifiersRecursive(n,order));

		return identifiers;
	}

	public static List<String> getNextIdentifiers(Node perent , int order){

		List<String> ids = new ArrayList<String>();

		for (Node n : perent.getChildNodes())
			if(n != null && n.getOrder() >= order){
				for (Entry<String, Integer> entry : n.getScope().IntegerIdentifiers.entrySet())
					ids.add(entry.getKey());
				for (Entry<String, Integer> entry : n.getScope().BooleanIdentifiers.entrySet())
					ids.add(entry.getKey());
			}

		for (Entry<String, Integer> entry : perent.getScope().IntegerIdentifiers.entrySet())
			if(entry.getValue() >= order)
				ids.add(entry.getKey());

		for (Entry<String, Integer> entry : perent.getScope().BooleanIdentifiers.entrySet())
			if(entry.getValue() >= order)
				ids.add(entry.getKey());

		return ids;	
	}

	public static List<String> getBooleanIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		identifiers.addAll(getBooleanIdentifiersRecursive(n.getParent(),n.getOrder()));
		identifiers.addAll(n.getScope().getBooleanIdentifiersUntil(order));

		return identifiers;
	}

	public static List<String> getIntegerIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		identifiers.addAll(getIntegerIdentifiersRecursive(n.getParent(),n.getOrder()));
		identifiers.addAll(n.getScope().getIntegerIdentifiersUntil(order));

		return identifiers;
	}

	public static Type getTypeByIdentifier(Node n, int order, String identifier){

		if(getBooleanIdentifiersRecursive(n, order).contains(identifier))
			return Type.Bool;
		else if(getIntegerIdentifiersRecursive(n, order).contains(identifier))
			return Type.Int;

		return null;	// TODO

	}
}
