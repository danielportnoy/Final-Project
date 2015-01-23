package com.example.logic.optionmenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class OptionsDB {

	private static Map<OptionEnum, List<Option>> mainOptions = new HashMap<OptionEnum, List<Option>>();
	static{
		mainOptions = new HashMap<OptionEnum, List<Option>>();

		Option[] initialOptions = { new Option(OptionEnum.VarDec)};

		mainOptions.put(OptionEnum.Void , new ArrayList<Option>(Arrays.asList(initialOptions)));
	}

	private static Map<OptionEnum,List<Option>> subOptions = new HashMap<OptionEnum, List<Option>>();
	static{
		subOptions = new HashMap<OptionEnum, List<Option>>();

		Option[] optionsVarDec = { new Option(OptionEnum.VarDecNoAsgn)};

		subOptions.put(OptionEnum.VarDec , new ArrayList<Option>(Arrays.asList(optionsVarDec)));
	}

	private static Map<OptionEnum, List<Option>> typeOptions = new HashMap<OptionEnum, List<Option>>();
	static{
		typeOptions = new HashMap<OptionEnum, List<Option>>();

		Option[] optionTypes = { new Option(OptionEnum.Int) , new Option(OptionEnum.Boolean)};

		typeOptions.put(OptionEnum.ReturnType , new ArrayList<Option>(Arrays.asList(optionTypes)));
	}

	private static List<Option> filterByOption (OptionEnum option) {

		ArrayList<Option> res = new ArrayList<Option>();

		if(option == null){	

			for (Entry<OptionEnum, List<Option>> entry : mainOptions.entrySet()) {
				res.addAll(entry.getValue());
			}
		}
		else if(option.equals(OptionEnum.Void)){	

			for (Entry<OptionEnum, List<Option>> entry : mainOptions.entrySet()) {
				if(entry.getKey().equals(OptionEnum.Void))
					res.addAll(entry.getValue());
			}
		}
		else if(option.equals(OptionEnum.Int)){	

			for (Entry<OptionEnum, List<Option>> entry : mainOptions.entrySet()) {
				if(entry.getKey().equals(OptionEnum.Int))
					res.addAll(entry.getValue());
			}
		}
		else if(option.equals(OptionEnum.VarDec)){	

			for (Entry<OptionEnum, List<Option>> entry : subOptions.entrySet()) {
				if(entry.getKey().equals(OptionEnum.VarDec))
					res.addAll(entry.getValue());
			}
		}		
		else if(option.equals(OptionEnum.ReturnType)){	

			for (Entry<OptionEnum, List<Option>> entry : typeOptions.entrySet()) {
				if(entry.getKey().equals(OptionEnum.ReturnType))
					res.addAll(entry.getValue());
			}
		}		

		return res;
	}

	public static List<Option> filter(OptionFilter optionFilter) {

		if(optionFilter == null)
			return null;
		else if(optionFilter.isFilterByOption())
			return filterByOption(optionFilter.getOption());

		return null;	
	}
}
