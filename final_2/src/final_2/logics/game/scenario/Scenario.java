package final_2.logics.game.scenario;

import final_2.logics.grammar.statement.Block_Stmt;

public interface Scenario {

	public abstract void RunCode(Block_Stmt codeBlock);

	public abstract boolean checkWin();
}
