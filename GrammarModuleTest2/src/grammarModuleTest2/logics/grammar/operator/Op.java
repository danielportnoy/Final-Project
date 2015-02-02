package grammarModuleTest2.logics.grammar.operator;

public abstract class Op {

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Op(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
