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
 * Potion is a Collectable that heal the player when he collects it
 *
 */
public class Potion extends Collectables{
	
	//Say if the potion has already heal someone
	private boolean hasHeal;
	
	/**
	 * Constructor of Potion
	 * @param area (Area): the Area of the Potion
	 * @param orientation (Orientation): the orientation of the Potion
	 * @param position (DiscreteCoordinates): the coordinates of the Potion
	 */
	public Potion(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position,"potion.5",(float)0.7, (float)0.7);
		
		hasHeal=false;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	/**
	 * Override of acceptInteraction:
	 * Act as a Collectable and as a Potion
	 */
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		super.acceptInteraction(v);
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	/**
	 * Getter for the Attribute hasHeal
	 * @return hasHeal (boolean)
	 */
	public boolean getHasHeal() {
		return hasHeal;
	}
	
	/**
	 * Setter for the Attribute hasHeal
	 * @param a (boolean): the value we want to give to hasHeal
	 */
	public void setHasHeal(boolean a) {
		hasHeal=a;
	}

}
