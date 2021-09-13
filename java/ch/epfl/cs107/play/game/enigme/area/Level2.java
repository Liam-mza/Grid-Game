package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2 extends EnigmeArea{

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Level2";
	}

	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		registerActor(new Background(this));
		
		Door door1 =new Door(this, "LevelSelector", Orientation.DOWN, new DiscreteCoordinates(2,6),new DiscreteCoordinates(5,0));
		enterAreaCells(door1, door1.getCurrentCells());
	
	
		Apple apple1 = new Apple(this,new DiscreteCoordinates(5,6));
		apple1.enterArea(this, new DiscreteCoordinates(5,6));
	}

}
