package main;

/*import logics.game.Scenario;*/
import logics.game.Coordinate;
import logics.game.levels.level1.Level1;
import logics.grammar.literals.Boolean_Literal;
import logics.grammar.statements.Block;
import logics.grammar.statements.If_Statement;

public class Main {

	public static void main(String[] args) {

		Level1 s = new Level1(3, 3, "H", new Coordinate(1, 1), "G", new Coordinate(1, 2));
		
		Block b = new Block();
		
		b.addStatemnt(new If_Statement(new Boolean_Literal(true), s.GoRightStatement(), null) );
		
		s.RunCode(b);
		
		
	}	
}
