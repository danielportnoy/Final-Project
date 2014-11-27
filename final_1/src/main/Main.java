package main;

/*import logics.game.Scenario;*/
import logics.game.levels.level1.Level1;
import logics.grammar.statements.Block;

public class Main {

	public static void main(String[] args) {

		Level1 s = new Level1(3, 3, "H", 1, 1 , "G", 1 , 2);
		
		
		Block b = new Block();
		
		b.addStatemnt(s.GoRightStatement());
		
		s.RunCode(b);
		
		
	}	
}
