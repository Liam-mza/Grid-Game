package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Or;

public class Level3 extends EnigmeArea{

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Level3";
	}

	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		registerActor(new Background(this));
		
		Key keys =  new Key(this, Orientation.DOWN, new DiscreteCoordinates(1,3));
		keys.enterArea(this, new DiscreteCoordinates(1,3));
		
		Torch torch1 = new Torch (this, Orientation.DOWN, new DiscreteCoordinates(7,5), false);
		torch1.enterArea(this, new DiscreteCoordinates(7,5));
		
		PressurePlate pres1= new PressurePlate(this,Orientation.DOWN,new DiscreteCoordinates(9,8), false, 3);
		pres1.enterArea(this, new DiscreteCoordinates(9,8));
		
		PressureSwitch pressureSwitch1 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4,4), false);
		pressureSwitch1.enterArea(this, new DiscreteCoordinates(4,4));
		
		PressureSwitch pressureSwitch2 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,4), false);
		pressureSwitch2.enterArea(this, new DiscreteCoordinates(5,4));
		
		PressureSwitch pressureSwitch3 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6,4), false);
		pressureSwitch3.enterArea(this, new DiscreteCoordinates(6,4));
		
		PressureSwitch pressureSwitch4 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,5), false);
		pressureSwitch4.enterArea(this, new DiscreteCoordinates(5,5));
		
		PressureSwitch pressureSwitch5 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4,6), false);
		pressureSwitch5.enterArea(this, new DiscreteCoordinates(4,6));
		
		PressureSwitch pressureSwitch6 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,6), false);
		pressureSwitch6.enterArea(this, new DiscreteCoordinates(5,6));
		
		PressureSwitch pressureSwitch7 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6,6), false);
		pressureSwitch7.enterArea(this, new DiscreteCoordinates(6,6));
		
		Lever lev1 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(10,5), false);
		lev1.enterArea(this, new DiscreteCoordinates(10,5));
		
		Lever lev2 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(9,5), false);
		lev2.enterArea(this, new DiscreteCoordinates(9,5));
		
		Lever lev3 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(8,5), false);
		lev3.enterArea(this, new DiscreteCoordinates(8,5));
		
		SignalDoor door2 = new SignalDoor(this, "LevelSelector", Orientation.DOWN, new DiscreteCoordinates(3,6) ,new DiscreteCoordinates(5,9),((Logic)keys));
		door2.enterArea(this, new DiscreteCoordinates(5,9));
		
		SignalRock rock1 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(6,8), ((Logic)pres1) );
		rock1.enterArea(this, new DiscreteCoordinates(6,8));
		
		Logic signal1 = new MultipleAnd(pressureSwitch1, pressureSwitch2, pressureSwitch3, pressureSwitch4, pressureSwitch5, pressureSwitch6, pressureSwitch7);
		
		SignalRock rock2 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(5,8), signal1 );
		rock2.enterArea(this, new DiscreteCoordinates(5,8));
		
		Logic signal2 = new LogicNumber(5,lev1,lev2,lev3);
		
		Logic signal3 = new Or(signal2,torch1);
		
		SignalRock rock3 = new SignalRock(this,Orientation.DOWN,new DiscreteCoordinates(4,8), signal3 );
		rock3.enterArea(this, new DiscreteCoordinates(4,8));
		
	}

}
