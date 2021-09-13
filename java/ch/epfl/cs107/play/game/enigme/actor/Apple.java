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
 * An apple is just a Collectable with a sprite representing an apple
 *
 */
public class Apple extends Collectables{ 
	

	/**
	 * Constructor of Apple
	 * @param area (Area): the Area of the apple
	 * @param orientation (Orientation): the orientation of the apple
	 * @param position (DiscreteCoordinates): the coordinates of the apple
	 */
	public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position,"apple.1");
	}
	/**
	 * Constructor of Apple that set orientation at Down
	 * @param area (Area): the Area of the apple
	 * @param position (DiscreteCoordinates): the coordinates of the apple
	 */
	public Apple(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN , coordinates,"apple.1");

	}
	

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}



}
