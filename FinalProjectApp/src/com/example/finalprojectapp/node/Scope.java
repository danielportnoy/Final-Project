package com.example.finalprojectapp.node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Hold the identifier deceleration data. 
 * @author daniel portnoy
 *
 */
public class Scope {

	private Map<String, Integer> IntegerIdentifiers;
	private Map<String, Integer> BooleanIdentifiers;

	public Scope() {
		IntegerIdentifiers = new HashMap<String, Integer>();
		BooleanIdentifiers = new HashMap<String, Integer>();
	}

	/**
	 * Removes all identifiers from a given position.
	 * @param order
	 */
	public void removeIdentifier(int order){

		// remove integer identifiers.
		for(Iterator<Entry<String, Integer>> it = IntegerIdentifiers.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, Integer> entry = it.next();
			if(entry.getValue().equals(order)) {
				it.remove();
			}
		}

		// remove integer identifiers.
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

	/**
	 * Get all of the identifiers until a given position.
	 * @param map - Identifier map.
	 * @param until
	 * @return List of identifiers.
	 */
	private List<String> getIdentifiersUntil(Map<String, Integer> map , int until){

		List<String> ids = new ArrayList<String>();

		for (Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() < until)
				ids.add(entry.getKey());
		}

		return ids;
	}

	/**
	 * Get all of the identifiers from a given position.
	 * @param map - Identifier map.
	 * @param from
	 * @return List of identifiers.
	 */
	private List<String> getIdentifiersFrom(Map<String, Integer> map , int from){

		List<String> ids = new ArrayList<String>();

		for (Entry<String, Integer> entry : map.entrySet()) {
			if(entry.getValue() >= from)
				ids.add(entry.getKey());
		}

		return ids;
	}

	/**
	 * Get all of the declared before identifiers of a Node. 
	 * @param n - Node.
	 * @param order
	 * @return List of identifiers.
	 */
	public static List<String> getPrevIdentifiers(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		identifiers.addAll(getPrevBooleanIdentifiersRecursive(n,order));
		identifiers.addAll(getPrevIntegerIdentifiersRecursive(n,order));

		return identifiers;
	}

	/**
	 * Get all of the declared after identifiers of a Node. 
	 * @param n - Node.
	 * @param order
	 * @return List of identifiers.
	 */
	public static List<String> getNextIdentifiers(Node perent , int order){

		List<String> identifiers = new ArrayList<String>();

		identifiers.addAll(getNextBooleanIdentifiersRecursive(perent,order));
		identifiers.addAll(getNextIntegerIdentifiersRecursive(perent,order));

		return identifiers;
	}

	/**
	 * Get all of the declared after boolean identifiers of a Node. 
	 * Recursive.
	 * @param n - Node
	 * @param order
	 * @return List of identifiers.
	 */
	public static List<String> getNextBooleanIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n == null)
			return identifiers;

		List<Node> childNodes = n.getChildNodes();

		// get children declared after boolean identifiers recursively.
		if(childNodes != null){
			for (Node node : n.getChildNodes())
				if(n.getOrder() >= order)
					identifiers.addAll(getNextBooleanIdentifiersRecursive(node, 0));
		}

		// get siblings declared after boolean identifiers recursively.
		for (Entry<String, Integer> entry : n.getScope().BooleanIdentifiers.entrySet())
			if(entry.getValue() >= order)
				identifiers.add(entry.getKey());

		identifiers.addAll(n.getScope().getBooleanIdentifiersFrom(order));

		return identifiers;
	}

	/**
	 * Get all of the declared after integer identifiers of a Node. 
	 * Recursive.
	 * @param n - Node
	 * @param order
	 * @return List of identifiers.
	 */
	public static List<String> getNextIntegerIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		List<Node> childNodes = n.getChildNodes();

		// get children declared after boolean identifiers recursively.
		if(childNodes != null){
			for (Node node : n.getChildNodes())
				if(n.getOrder() >= order)
					identifiers.addAll(getNextIntegerIdentifiersRecursive(node, 0));
		}

		// get siblings declared after boolean identifiers recursively.
		for (Entry<String, Integer> entry : n.getScope().IntegerIdentifiers.entrySet())
			if(entry.getValue() >= order)
				identifiers.add(entry.getKey());

		identifiers.addAll(n.getScope().getIntegerIdentifiersFrom(order));

		return identifiers;
	}

	/**
	 * Get all of the declared before boolean identifiers of a Node. 
	 * Recursive.
	 * @param n - Node
	 * @param order
	 * @return List of identifiers.
	 */
	public static List<String> getPrevBooleanIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		// get parent declared before boolean identifiers recursively.
		identifiers.addAll(getPrevBooleanIdentifiersRecursive(n.getParent(),n.getOrder()));

		// get siblings declared before boolean identifiers recursively.
		identifiers.addAll(n.getScope().getBooleanIdentifiersUntil(order));

		return identifiers;
	}

	/**
	 * Get all of the declared before integer identifiers of a Node. 
	 * Recursive.
	 * @param n - Node
	 * @param order
	 * @return List of identifiers.
	 */
	public static List<String> getPrevIntegerIdentifiersRecursive(Node n , int order){

		List<String> identifiers = new ArrayList<String>();

		if(n==null)
			return identifiers;

		// get parent declared before integer identifiers recursively.
		identifiers.addAll(getPrevIntegerIdentifiersRecursive(n.getParent(),n.getOrder()));

		// get siblings declared before integer identifiers recursively.
		identifiers.addAll(n.getScope().getIntegerIdentifiersUntil(order));

		return identifiers;
	}

	/**
	 * 
	 * @param n
	 * @param order
	 * @param identifier
	 * @return
	 */
	public static Type getTypeByIdentifier(Node n, int order, String identifier){

		if(getPrevBooleanIdentifiersRecursive(n, order).contains(identifier))
			return Type.Bool;
		else if(getPrevIntegerIdentifiersRecursive(n, order).contains(identifier))
			return Type.Int;

		return null;

	}
}
