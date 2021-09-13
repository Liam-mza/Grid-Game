package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.CampFire;
import ch.epfl.cs107.play.game.enigme.actor.Dress;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class LevelSelector extends EnigmeArea {

	private DiscreteCoordinates destination =new DiscreteCoordinates(5,1);
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "LevelSelector";
	}

	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		
		registerActor(new Background(this));
		
		
		SignalDoor door1 = new SignalDoor(this, "Level1", Orientation.DOWN, destination,new DiscreteCoordinates(1,7),Logic.TRUE);
		door1.enterArea(this, new DiscreteCoordinates(1,7));
		
		SignalDoor door2 = new SignalDoor(this, "Level2", Orientation.DOWN, destination,new DiscreteCoordinates(2,7),Logic.TRUE);
		door2.enterArea(this, new DiscreteCoordinates(2,7));
		
		SignalDoor door3 = new SignalDoor(this, "Level3", Orientation.DOWN, destination,new DiscreteCoordinates(3,7),Logic.TRUE);
		door3.enterArea(this, new DiscreteCoordinates(3,7));
		
		SignalDoor door4 = new SignalDoor(this, "Enigme2", Orientation.DOWN, new DiscreteCoordinates(7,1),new DiscreteCoordinates(4,7),Logic.TRUE);
		door4.enterArea(this, new DiscreteCoordinates(4,7));
		
		SignalDoor door5 = new SignalDoor(this, "Enigme0", Orientation.DOWN, new DiscreteCoordinates(12,12),new DiscreteCoordinates(5,7),Logic.TRUE);
		door5.enterArea(this, new DiscreteCoordinates(5,7));
		
		SignalDoor door6 = new SignalDoor(this, "", Orientation.DOWN, new DiscreteCoordinates(6,6),new DiscreteCoordinates(6,7),Logic.FALSE);
		door6.enterArea(this, new DiscreteCoordinates(6,7));
		
		SignalDoor door7 = new SignalDoor(this, "", Orientation.DOWN, new DiscreteCoordinates(7,6),new DiscreteCoordinates(7,7),Logic.FALSE);
		door7.enterArea(this, new DiscreteCoordinates(7,7));
		
		SignalDoor door8 = new SignalDoor(this, "", Orientation.DOWN, new DiscreteCoordinates(8,6) ,new DiscreteCoordinates(8,7),Logic.FALSE);
		door8.enterArea(this, new DiscreteCoordinates(8,7));
		
		CampFire c1 = new CampFire(this,Orientation.DOWN,new DiscreteCoordinates(8,4));
		c1.enterArea(this, new DiscreteCoordinates(8,4));
		
		Dress d1= new Dress(this,Orientation.LEFT,new DiscreteCoordinates(8,5));
		d1.enterArea(this, new DiscreteCoordinates(8,5));
	}

}
