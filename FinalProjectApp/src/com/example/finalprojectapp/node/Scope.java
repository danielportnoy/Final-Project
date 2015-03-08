package com.example.finalprojectapp.node;

import java.util.ArrayList;
import java.util.HashMap;
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

	public static List<String> getIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		identifiers.addAll(getBooleanIdentifiersRecursive(n,order));
		identifiers.addAll(getIntegerIdentifiersRecursive(n,order));

		return identifiers;
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
}
