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

	private List<String> getIntegerIdentifiersFrom(int until) {
		return getIdentifiersFrom(IntegerIdentifiers, until);
	}

	private List<String> getBooleanIdentifiersFrom(int until) {
		return getIdentifiersFrom(BooleanIdentifiers, until);
	}

	private List<String> getIdentifiersUntil(Map<String, Integer> map , int until){

		List<String> ids = new ArrayList<String>();

		for (Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() < until)
				ids.add(entry.getKey());
		}

		return ids;
	}

	private List<String> getIdentifiersFrom(Map<String, Integer> map , int until){

		List<String> ids = new ArrayList<String>();

		for (Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() >= until)
				ids.add(entry.getKey());
		}

		return ids;
	}


	public static List<String> getPrevIdentifiers(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		identifiers.addAll(getPrevBooleanIdentifiersRecursive(n,order));
		identifiers.addAll(getPrevIntegerIdentifiersRecursive(n,order));

		return identifiers;
	}

	public static List<String> getNextIdentifiers(Node perent , int order){

		List<String> identifiers = new ArrayList<String>();

		identifiers.addAll(getNextBooleanIdentifiersRecursive(perent,order));
		identifiers.addAll(getNextIntegerIdentifiersRecursive(perent,order));

		return identifiers;
	}

	public static List<String> getNextBooleanIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n == null)
			return identifiers;

		List<Node> childNodes = n.getChildNodes();

		if(childNodes != null){
			for (Node node : n.getChildNodes())
				if(n.getOrder() >= order)
					identifiers.addAll(getNextBooleanIdentifiersRecursive(node, 0));
		}

		for (Entry<String, Integer> entry : n.getScope().BooleanIdentifiers.entrySet())
			if(entry.getValue() >= order)
				identifiers.add(entry.getKey());

		identifiers.addAll(n.getScope().getBooleanIdentifiersFrom(order));

		return identifiers;
	}

	public static List<String> getNextIntegerIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		List<Node> childNodes = n.getChildNodes();

		if(childNodes != null){
			for (Node node : n.getChildNodes())
				if(n.getOrder() >= order)
					identifiers.addAll(getNextIntegerIdentifiersRecursive(node, 0));
		}

		for (Entry<String, Integer> entry : n.getScope().IntegerIdentifiers.entrySet())
			if(entry.getValue() >= order)
				identifiers.add(entry.getKey());

		identifiers.addAll(n.getScope().getIntegerIdentifiersFrom(order));

		return identifiers;
	}


	public static List<String> getPrevBooleanIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		identifiers.addAll(getPrevBooleanIdentifiersRecursive(n.getParent(),n.getOrder()));
		identifiers.addAll(n.getScope().getBooleanIdentifiersUntil(order));

		return identifiers;
	}

	public static List<String> getPrevIntegerIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		identifiers.addAll(getPrevIntegerIdentifiersRecursive(n.getParent(),n.getOrder()));
		identifiers.addAll(n.getScope().getIntegerIdentifiersUntil(order));

		return identifiers;
	}

	public static Type getTypeByIdentifier(Node n, int order, String identifier){

		if(getPrevBooleanIdentifiersRecursive(n, order).contains(identifier))
			return Type.Bool;
		else if(getPrevIntegerIdentifiersRecursive(n, order).contains(identifier))
			return Type.Int;

		return null;	// TODO

	}
}
