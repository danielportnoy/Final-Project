package final_2.logics.game.level1.infrastructure;

public abstract class Tile {

	private String mark;

	private Coordinate location;
	
	private boolean isHabitable;

	public Tile(String mark , Coordinate location , boolean isHabitable) {
		setMark(mark);
		setLocation(location);
		setHabitable(isHabitable);
	}

	public boolean isHabitable() {
		return isHabitable;
	}

	public void setHabitable(boolean isHabitable) {
		this.isHabitable = isHabitable;
	}

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getMark() {
		return mark;
	}

}
