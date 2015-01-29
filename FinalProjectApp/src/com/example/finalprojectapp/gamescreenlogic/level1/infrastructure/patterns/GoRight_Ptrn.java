package com.example.finalprojectapp.gamescreenlogic.level1.infrastructure.patterns;

import com.example.finalprojectapp.codescreenlogic.pattern.Pattern;
import com.example.finalprojectapp.gamescreenlogic.level1.Level1;

public class GoRight_Ptrn extends Pattern {

	private Level1 level;

	public GoRight_Ptrn(Level1 level) {
		super(0);
		this.level = level;
	}

	/*@Override
	public Object run() {

		int cuurCol = level.getHero().getLocation().getColCoord();

		Coordinate targetCoord = new Coordinate(level.getHero().getLocation());
		targetCoord.setColCoord(cuurCol+1);

		Tile targetTile = level.getBoard().getTileAtLoaction(targetCoord);

		if(targetTile==null)
			return null;
		else if(!targetTile.isHabitable())
			return null;

		level.getHero().getLocation().setColCoord(cuurCol+1);	

		return null;
	}
*/
	@Override
	public String toString() {
		return "goRight();";
	}	
}
