package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1  extends EnigmeArea {

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Level1";
	}

	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		registerActor(new Background(this));
		
		
		Door door1 =new Door(this, "LevelSelector", Orientation.DOWN, new DiscreteCoordinates(1,6),new DiscreteCoordinates(5,0));
		enterAreaCells(door1, door1.getCurrentCells());
	}

}
