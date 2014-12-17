package final_2.logics.grammar.statement;

public abstract class Stmt {

	private String name;

	public Stmt(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void run();

}
