package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

/**
 * 
 * A Key is just a Collectable with a sprite representing a key
 *
 */
public class Key extends Collectables {

	/**
	 Constructor of Key
	 * @param area (Area): the Area of the Key
	 * @param orientation (Orientation): the orientation of the Key
	 * @param position (DiscreteCoordinates): the coordinates of the Key
	 */
	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, "key.1");
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
	return Collections.singletonList(getCurrentMainCellCoordinates());
	}


}
