package main;

import java.util.LinkedList;

import logics.Scenario;
import logics.Statement;

public class Main {

	public static void main(String[] args) {

		Scenario s = new Scenario(3, 3, "H", 1, 1 , "G", 1 , 2);
		
		LinkedList<Statement> l = new LinkedList<Statement>();
		l.add(s.GoRightStatement());
		
		s.RunCode(l);
		
	}	
}
