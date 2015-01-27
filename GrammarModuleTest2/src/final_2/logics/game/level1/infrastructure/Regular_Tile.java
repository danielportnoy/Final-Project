package final_2.logics.game.level1.infrastructure;

public class Regular_Tile extends Tile {

	public Regular_Tile(Coordinate location) {
		super("-",location,true);
	}

	public Regular_Tile(Regular_Tile other) {
		super(other);
	}

	@Override
	public Regular_Tile makeCopy() {
		return new Regular_Tile(this);
	}

}
