package grammarModuleTest2.main;

import java.util.HashMap;

public class ScopeTable {
	
	public static HashMap<String, Integer> IntScope = new HashMap<String, Integer>();
	
	public static HashMap<String, Boolean> BoolScope = new HashMap<String, Boolean>();
	
	public static void reset(){
		
		IntScope.clear();
		BoolScope.clear();
		
	}


}
