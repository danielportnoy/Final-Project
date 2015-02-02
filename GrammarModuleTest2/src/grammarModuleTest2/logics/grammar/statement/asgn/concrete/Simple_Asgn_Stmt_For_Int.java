package grammarModuleTest2.logics.grammar.statement.asgn.concrete;

import grammarModuleTest2.logics.grammar.expression.Expr;
import grammarModuleTest2.logics.grammar.expression.identifier.Idnt;
import grammarModuleTest2.logics.grammar.operator.binary.asgn.concrete.Simple_Asgn_For_Int;
import grammarModuleTest2.logics.grammar.statement.asgn.Bin_Asgn_Stmt_For_Int;

public class Simple_Asgn_Stmt_For_Int extends Bin_Asgn_Stmt_For_Int {

	public Simple_Asgn_Stmt_For_Int(Idnt<Integer> identifier , Expr<Integer> expression) {
		super(identifier, new Simple_Asgn_For_Int() , expression);
	}

	@Override
	public void run() {
		getIdentifier().setValue(getExpression().run());
	}

}
