package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * 
 * A Torch is just a Switcher with sprites representing a torch
 *
 */
public class Torch extends Switcher {
	
	/**
	 * 
	 * @param area (Area): the Area of the Torch
	 * @param orientation (Orientation): the Orientation of the Torch
	 * @param position (DiscreteCoordinates): the coordinates of the Torch
	 * @param defaultState (boolean):The sate of the Torch at its creation
	 */
	public Torch(Area area, Orientation orientation, DiscreteCoordinates position, boolean defaultState) {
		super(area, orientation, position, "torch.ground.on.1", "torch.ground.off", defaultState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

}
