package logics;

public abstract class Statement {

	public String name;

	public Statement(String name) {
		this.name = name;
	}

	public abstract Type Run();
}
