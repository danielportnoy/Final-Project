package grammarModuleTest2.logics.game.level1.infrastructure.stmt;

import grammarModuleTest2.logics.game.level1.Level1;
import grammarModuleTest2.logics.game.level1.infrastructure.Coordinate;
import grammarModuleTest2.logics.game.level1.infrastructure.Tile;
import grammarModuleTest2.logics.grammar.statement.Stmt;

public class GoRightStmt extends Stmt {

	private Level1 level;

	public GoRightStmt(Level1 level) {
		this.level = level;
	}
	@Override
	public void run() {

		int cuurCol = level.getHero().getLocation().getColCoord();

		Coordinate targetCoord = new Coordinate(level.getHero().getLocation());
		targetCoord.setColCoord(cuurCol+1);

		Tile targetTile = level.getBoard().getTileAtLoaction(targetCoord);

		if(targetTile==null)
			return;
		else if(!targetTile.isHabitable())
			return;

		level.getHero().getLocation().setColCoord(cuurCol+1);	
	}

	@Override
	public String toString() {
		return "goRight();";
	}	
}
