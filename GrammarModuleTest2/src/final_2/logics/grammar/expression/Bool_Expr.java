package final_2.logics.grammar.expression;

public abstract class Bool_Expr extends Expr<Boolean>{

	public boolean equals(Bool_Expr other) {
		return this.run()==other.run();
	}

}
