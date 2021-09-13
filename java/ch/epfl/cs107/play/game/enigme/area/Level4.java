package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Cake;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Spikes;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Is a new area of the game Enigme
 */
public class Level4 extends EnigmeArea{

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Enigme2";
	}

	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		registerActor(new Background(this));
		Door door1 =new Door(this, "LevelSelector", Orientation.DOWN, new DiscreteCoordinates(4,6),new DiscreteCoordinates(7,0));
		enterAreaCells(door1, door1.getCurrentCells());

		Spikes spi1 = new Spikes (this,Orientation.DOWN ,new DiscreteCoordinates(2,4));
		spi1.enterArea(this, new DiscreteCoordinates(2,4));
		Spikes spi2 = new Spikes (this,Orientation.DOWN ,new DiscreteCoordinates(12,4));
		spi2.enterArea(this, new DiscreteCoordinates(12,4));
		Cake cake1= new Cake(this,Orientation.DOWN, new DiscreteCoordinates(7,10));
		cake1.enterArea(this, new DiscreteCoordinates(7,10));
	}

}

