package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

/**
 * 
 * A PressurePlate is a switcher that deactivate itself after a given time 
 *
 */
public class PressurePlate extends Switcher {
	
	//The number of seconds that the plate stay activate 
	int temps;
	
	//Two boolean that help us manage interactions
	private boolean wantToInteract;
	private boolean hasInteracted;
	
	//A counter to manage the time 
	private float counter;
	
	//The sprites
	private Sprite spriteOn;
	private Sprite spriteOff;
	
	/**
	 * Constructor for Pressure plate 
	 * @param area (Area): the Area of the PressurePlate 
	 * @param orientation (Orientation): the Orientation of the PressurePlate
	 * @param position (DiscreteCoordinates): the coordinates of the PressurePlate
	 * @param defaultState (boolean): the state of the PressurePlate at its creation
	 * @param temps (int): The number of seconds that the plate stay activate 
	 */
	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position, boolean defaultState, int temps) {
		super(area, orientation, position, "GroundLightOn", "GroundPlateOff", defaultState);
		this.temps=temps;
		counter=0;
		this.spriteOn= new Sprite("GroundLightOn", 1, 1.f, this);
		this.spriteOff = new Sprite("GroundPlateOff", 1, 1.f, this);
		
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		wantToInteract=true;
		if (!hasInteracted) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		hasInteracted=true;
		
		}
		
	}
	
	@Override
	public void update (float deltaTime) {
		super.update(deltaTime);
		
		if (this.isOn()) {
			counter += deltaTime;
			
			if (counter>=temps) {
				this.setCurrentState(false);
				counter=0;
			}
			
		}
		
		if(wantToInteract) {
			wantToInteract=false;
			counter=0;
			
		}
		else {
			hasInteracted=false;
			
		}
		
		
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		spriteOff.draw(canvas);
		if (isOn()) {
			spriteOn.draw(canvas);
		}
	}

}
