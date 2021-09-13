package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects asking for interaction (i.e. can interact with some Interactable)
 * @see Interactable
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactor {

    // TODO implements me #PROJECT #TUTO
	/**
	 * Getter for the current cells of the interactor
	 * @return (List<DiscreteCoordinates> ): the list of the coordinates of the currents cells of the interactor
	 */
	public List<DiscreteCoordinates> getCurrentCells() ;
	
	
	/**
	 * Getter for cells in the field of view of the interactor
	 * @return (List<DiscreteCoordinates> ): the list of the coordinates of cells in the field of view of the interactor
	 */
	public List<DiscreteCoordinates> getFieldOfViewCells();
	
	
	/**
	 * Say if the interactor want cell interactions
	 * @return (boolean): true if yes, false otherwise
	 */
	public boolean wantsCellInteraction();
	
	/**
	 * Say if the interactor want view interactions
	 * @return (boolean): true if yes, false otherwise
	 */
	public boolean wantsViewInteraction();
	
	
	/**
	 * Simulate the interaction of the actor with a given Interactable 
	 * @param other: the Interactable to interact with
	 */
	public void interactWith(Interactable other);
}
