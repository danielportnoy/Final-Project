package grammarModuleTest2.logics.game.level1.infrastructure;

public class Hero {

	private String look;

	private Coordinate location;

	public Hero(String look , Coordinate location) {
		this.look=look;
		this.location = location.makeCopy();
	}

	public Hero(Hero other) {
		this.look = other.look;
		this.location = other.getLocation().makeCopy();
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location.makeCopy();
	}

	public String getLook() {
		return look;
	}

	public void setLook(String look) {
		this.look = look;
	}

}
