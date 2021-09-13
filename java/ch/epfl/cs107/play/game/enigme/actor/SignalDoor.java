package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicSignal;
import ch.epfl.cs107.play.window.Canvas;

/**
 * 
 * A SignalDoor is a door that can be close or open depending on a signal
 *
 */
public class SignalDoor extends Door{ //extends AreaEntity implements Interactable {

	//The sprite that is going to be drawn 
	private Sprite sprite;
	
	//The sprite of the door when closed
	private Sprite spriteClose;
	
	//The sprite of the door when closed
	private Sprite spriteOpen;
	
	//The logic signal that the door is link with 
	private Logic s;
	
	/**
	 * Constructor of SignalDoor
	 * @param currentArea (Area): the Area of the SignalDoor
	 * @param destArea (Area): the destination Area of the door  
	 * @param orientation (Orientation): the Orientation of the door
	 * @param destCoor (DiscreteCoordinates): the destination coordinates of the door
	 * @param position  (DiscreteCoordinates): the coordinates of the door
	 * @param s (Logic): the signal link with the door 
	 * @param coordinates (Ellipse of DiscreteCoordinates): Coordinates of the additional cells occupied by the door 
	 */
	public SignalDoor(Area currentArea, String destArea,  Orientation orientation, DiscreteCoordinates destCoor, DiscreteCoordinates position,Logic  s, DiscreteCoordinates ...coordinates  ) {
		super( currentArea, destArea, orientation,destCoor, position, coordinates );
		
		
		spriteClose= new Sprite("door.close.1", 1, 1.f, this);
		
		spriteOpen= new Sprite("door.open.1", 1, 1.f, this);
		this.s=s;
	}

	@Override
	public void draw(Canvas canvas) {
		if (s.isOn()) {
			sprite= spriteOpen;
		}
		else {
			sprite= spriteClose;
		}
		sprite.draw(canvas);
		
	}


	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return !s.isOn();
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return s.isOn();
	}


}
