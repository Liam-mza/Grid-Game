package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Spikes;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;


/**
 * Is a new area of the game Enigme
 */
public class Level5 extends EnigmeArea {

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Enigme0" ;

	}


	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		registerActor(new Background(this));

		Logic signal1 = Logic.FALSE;
		SignalRock rock1 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(16,26), signal1);
		rock1.enterArea(this, new DiscreteCoordinates(16,26));
		SignalRock rock2 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(17,26), signal1);
		rock2.enterArea(this, new DiscreteCoordinates(17,26));
		SignalRock rock3 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(11,29), signal1);
		rock3.enterArea(this, new DiscreteCoordinates(11,29));
		SignalRock rock4 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(9,29), signal1);
		rock4.enterArea(this, new DiscreteCoordinates(9,29));
		Key keys =  new Key(this, Orientation.DOWN, new DiscreteCoordinates(17,27));
		keys.enterArea(this, new DiscreteCoordinates(17,27));

		SignalDoor door = new SignalDoor(this, "LevelSelector", Orientation.DOWN, new DiscreteCoordinates(4,4) ,new DiscreteCoordinates(10,29),((Logic)keys));
		door.enterArea(this, new DiscreteCoordinates(10,29));

		Spikes spike5 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(17,28));
		spike5.enterArea(this, new DiscreteCoordinates(17,28));
		
		Spikes spike6 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(16,28));
		spike6.enterArea(this, new DiscreteCoordinates(16,28));
		
		Spikes spike7 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(15,28));
		spike7.enterArea(this, new DiscreteCoordinates(15,28));
		
		Spikes spike8 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(14,28));
		spike8.enterArea(this, new DiscreteCoordinates(14,28));
		
		Spikes spike9 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(13,28));
		spike9.enterArea(this, new DiscreteCoordinates(13,28));
		
		Spikes spike10 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(12,28));
		spike10.enterArea(this, new DiscreteCoordinates(12,28));
		
		Spikes spike11 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(12,27));
		spike11.enterArea(this, new DiscreteCoordinates(12,27));
		
		Spikes spike12 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(12,26));
		spike12.enterArea(this, new DiscreteCoordinates(12,26));
		
		Spikes spike13 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(12,25));
		spike13.enterArea(this, new DiscreteCoordinates(12,25));
		
		Spikes spike14 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(15,26));
		spike14.enterArea(this, new DiscreteCoordinates(15,26));
		
		Spikes spike15 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(14,26));
		spike15.enterArea(this, new DiscreteCoordinates(14,26));
		
		Spikes spike16 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(14,25));
		spike16.enterArea(this, new DiscreteCoordinates(14,25));
		
		Spikes spike17 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(0,12));
		spike17.enterArea(this, new DiscreteCoordinates(0,12));
		
		Spikes spike18 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(0,11));
		spike18.enterArea(this, new DiscreteCoordinates(0,11));
		
		Spikes spike19 = new Spikes(this,Orientation.DOWN,new DiscreteCoordinates(9,7));
		spike19.enterArea(this, new DiscreteCoordinates(9,7));

		PressurePlate pres1= new PressurePlate(this,Orientation.DOWN,new DiscreteCoordinates(17, 25), false, 3);
		pres1.enterArea(this, new DiscreteCoordinates(17,25));
		SignalRock rock5 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(16,27), ((Logic)pres1));
		rock5.enterArea(this, new DiscreteCoordinates(16,27));

		PressureSwitch pressureSwitch = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(9,8), false);
		pressureSwitch.enterArea(this, new DiscreteCoordinates(9,8));
		SignalRock rock6 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(15,27), ((Logic)pressureSwitch));
		rock6.enterArea(this, new DiscreteCoordinates(15,27));

		Lever lev2 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(2,28), false);
		lev2.enterArea(this, new DiscreteCoordinates(2,28));
		SignalRock rock7 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(14,27), ((Logic)lev2));
		rock7.enterArea(this, new DiscreteCoordinates(14,27));

		Lever lev1 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(16,2), false);
		lev1.enterArea(this, new DiscreteCoordinates(16,2));
		SignalRock rock8 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(13,26), ((Logic)lev1));
		rock8.enterArea(this, new DiscreteCoordinates(13,26));

		Torch torch1 = new Torch (this, Orientation.DOWN, new DiscreteCoordinates(24,18), false);
		torch1.enterArea(this, new DiscreteCoordinates(24,18));
		SignalRock rock9 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(13,25),((Logic)torch1));
		rock9.enterArea(this, new DiscreteCoordinates(13,25));

		Potion pot1 = new Potion(this, Orientation.DOWN, new DiscreteCoordinates(13,27));
		pot1.enterArea(this, new DiscreteCoordinates(13,27));




	}

}
