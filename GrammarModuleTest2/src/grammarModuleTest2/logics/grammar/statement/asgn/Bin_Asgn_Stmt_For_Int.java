package grammarModuleTest2.logics.grammar.statement.asgn;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.operator.binary.asgn.Bin_Asgn_Op;

public abstract class Bin_Asgn_Stmt_For_Int extends Bin_Asgn_Stmt<Integer>{

	public Bin_Asgn_Stmt_For_Int(Idnt<Integer> identifier,Bin_Asgn_Op<Integer> operator, Expr<Integer> expression) {
		super(identifier, operator, expression);
	}
}
