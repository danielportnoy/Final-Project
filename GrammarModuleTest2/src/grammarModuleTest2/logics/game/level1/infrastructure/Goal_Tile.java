package grammarModuleTest2.logics.game.level1.infrastructure;

public class Goal_Tile extends Tile {

	public Goal_Tile(Coordinate location) {
		super("G", location, true);
	}

	public Goal_Tile(Goal_Tile other) {
		super(other);
	}

	@Override
	public Goal_Tile makeCopy() {
		return new Goal_Tile(this);
	}

}
