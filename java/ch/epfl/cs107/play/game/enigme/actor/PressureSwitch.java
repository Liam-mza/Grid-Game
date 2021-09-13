package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * 
 * A PressurePlate is a switcher that is activate/deactivate when we walk on   
 *
 */
public class PressureSwitch extends Switcher{
	
	//Two boolean that help us manage interactions 
	private boolean wantToInteract;
	private boolean hasInteracted;
	
	
	/**
	 * Constructor for PressureSwitch
	 * @param area (Area): the Area of the PressureSwitch 
	 * @param orientation (Orientation): the Orientation of the PressureSwitch
	 * @param position (DiscreteCoordinates): the coordinates of the PressureSwitch
	 * @param defaultState (boolean): the state of the PressureSwitch at its creation
	 */
	public PressureSwitch(Area area, Orientation orientation, DiscreteCoordinates position, boolean defaultState) {
		super(area, orientation, position, "GroundLightOn", "GroundLightOff", defaultState);
		wantToInteract=false;
		hasInteracted=false;
	
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
		
		if(wantToInteract) {
			wantToInteract=false;
			
		}
		else {
			hasInteracted=false;
			
		}
		
		
	}
	

}
