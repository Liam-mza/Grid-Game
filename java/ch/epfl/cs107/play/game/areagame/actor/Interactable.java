package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
    // TODO implements me #PROJECT #TUTO

	/**
	 * Getter for the list of the cells occupied by the entity 
	 * @return (List) the list of the cells occupied by the entity 
	 */
	public List<DiscreteCoordinates> getCurrentCells();
	
	/**
	 * Say if the cell occupied by the entity is still reachable by other entity 
	 * @return (boolean): true if the cell is still reachable or false if not 
	 */
	public boolean takeCellSpace();
	
	/**
	 * Say if the entity accept remote interactions with other entity 
	 * @return (boolean): true if it's the case , false otherwise 
	 */
	public boolean isViewInteractable();
	
	/**
	 * Say if the entity accept contact interactions with other entity 
	 * @return (boolean): true if it's the case , false otherwise 
	 */
	public boolean isCellInteractable();
	
	
	public void acceptInteraction(AreaInteractionVisitor v);

	
}
