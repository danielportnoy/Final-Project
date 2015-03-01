package com.example.finalprojectapp.codescreenlogic.optionmenu.optionsDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.finalprojectapp.nodetree.Type;

public class OptionsDB {

	private static Map<NodeType, List<Type>> staticOptions;
	static {
		staticOptions =  new HashMap<NodeType, List<Type>>();

		Type[] typesStatement = { Type.Statement };	

		staticOptions.put(NodeType.Block, new ArrayList<Type>(Arrays.asList(typesStatement)));
		staticOptions.put(NodeType.IfThen, new ArrayList<Type>(Arrays.asList(typesStatement)));
		staticOptions.put(NodeType.BoolVarDec, new ArrayList<Type>(Arrays.asList(typesStatement)));
		staticOptions.put(NodeType.IntVarDec, new ArrayList<Type>(Arrays.asList(typesStatement)));
		staticOptions.put(NodeType.For, new ArrayList<Type>(Arrays.asList(typesStatement)));

		Type[] typesLiteral = { Type.Bool, Type.Int};	

		staticOptions.put(NodeType.Literal, new ArrayList<Type>(Arrays.asList(typesLiteral)));
		
		Type[] typesInt = {Type.Int};	

		staticOptions.put(NodeType.Plus, new ArrayList<Type>(Arrays.asList(typesInt)));


	}

	public static List<Type> getByNodeType(NodeType type){
		return staticOptions.get(type);
	}

}
