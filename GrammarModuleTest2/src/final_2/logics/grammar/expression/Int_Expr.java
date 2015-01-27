package final_2.logics.grammar.expression;

public abstract class Int_Expr extends Expr<Integer> {
	
	public boolean equals(Int_Expr other) {
		return this.run()==other.run();
	}
	
	public Integer plus(Int_Expr other) {
		return this.run()+other.run();
	}

}
