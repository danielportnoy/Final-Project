package logic.grammar.operator;

public abstract class Bin_Op<R,T> extends Op {

	public Bin_Op(String name) {
		super(name);
	}

	public abstract R run(T left , T right);

}
