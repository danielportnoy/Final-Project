package com.example.logic.optionmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class OptionsDB {

	private static Map<OptionFilter, List<Option>> mainOptions = new HashMap<OptionFilter, List<Option>>();
	static{
		mainOptions = new HashMap<OptionFilter, List<Option>>();

		Option[] voidOptions = { new Option(OptionEnum.VarDec)};

		mainOptions.put(OptionFilter.Void , new ArrayList<Option>(Arrays.asList(voidOptions)));
		
		Option[] intOptions = { new Option(OptionEnum.IntLiteral)};

		mainOptions.put(OptionFilter.Int , new ArrayList<Option>(Arrays.asList(intOptions)));
	}

	private static Map<OptionEnum,List<Option>> subOptions = new HashMap<OptionEnum, List<Option>>();
	static{
		subOptions = new HashMap<OptionEnum, List<Option>>();

		Option[] optionsVarDec = { new Option(OptionEnum.VarDecNoAsgn) , new Option(OptionEnum.VarDecWithAsgn)};

		subOptions.put(OptionEnum.VarDec , new ArrayList<Option>(Arrays.asList(optionsVarDec)));
	}

	private static Map<OptionFilter, List<Option>> typeOptions = new HashMap<OptionFilter, List<Option>>();
	static{
		typeOptions = new HashMap<OptionFilter, List<Option>>();

		Option[] optionTypes = { new Option(OptionEnum.Int) , new Option(OptionEnum.Boolean)};

		typeOptions.put(OptionFilter.ReturnType , new ArrayList<Option>(Arrays.asList(optionTypes)));
	}

	public static List<Option> filterByOptionFilter(OptionFilter optionFilter) {

		ArrayList<Option> res = new ArrayList<Option>();

		if(optionFilter == null){	

			for (Entry<OptionFilter, List<Option>> entry : mainOptions.entrySet()) {
				res.addAll(entry.getValue());
			}
		}
		else if(optionFilter.equals(OptionFilter.Void)){	

			for (Entry<OptionFilter, List<Option>> entry : mainOptions.entrySet()) {
				if(entry.getKey().equals(OptionFilter.Void))
					res.addAll(entry.getValue());
			}
		}
		else if(optionFilter.equals(OptionFilter.Int)){	

			for (Entry<OptionFilter, List<Option>> entry : mainOptions.entrySet()) {
				if(entry.getKey().equals(OptionFilter.Int))
					res.addAll(entry.getValue());
			}
		}
		else if(optionFilter.equals(OptionFilter.ReturnType)){	

			for (Entry<OptionFilter, List<Option>> entry : typeOptions.entrySet()) {
				if(entry.getKey().equals(OptionFilter.ReturnType))
					res.addAll(entry.getValue());
			}
		}		

		return res;
	}

	public static List<Option> filterByOptionEnum(OptionEnum optionEnum) {
		
		ArrayList<Option> res = new ArrayList<Option>();
		
		if(optionEnum == null){	

			for (Entry<OptionEnum, List<Option>> entry : subOptions.entrySet()) {
				res.addAll(entry.getValue());
			}
		}
		else if(optionEnum.equals(OptionEnum.VarDec)){	

			for (Entry<OptionEnum, List<Option>> entry : subOptions.entrySet()) {
				if(entry.getKey().equals(OptionEnum.VarDec))
					res.addAll(entry.getValue());
			}
		}		
		
		return res;
	}
}
