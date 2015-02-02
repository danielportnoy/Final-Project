package grammarModuleTest2.logics.game.scenario;

import grammarModuleTest2.logics.grammar.statement.Block_Stmt;

public interface Scenario {

	public abstract void RunCode(Block_Stmt codeBlock);

	public abstract boolean checkWin();
	
	public abstract void reset();
}
