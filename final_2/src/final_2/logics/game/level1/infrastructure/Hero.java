package final_2.logics.game.level1.infrastructure;

public class Hero {

	private String look;

	private Coordinate location;

	public Hero(String look , Coordinate location) {
		setLook(look);
		setLocation(location);
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public String getLook() {
		return look;
	}

	public void setLook(String look) {
		this.look = look;
	}

}
