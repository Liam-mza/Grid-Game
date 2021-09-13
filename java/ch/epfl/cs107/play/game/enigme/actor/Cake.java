package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Cake extends Collectables{

	/**
	 * Constructor for Cake
	 * @param area (Area): the Area of the Cake
	 * @param orientation (Orientation): the Orientation of the Cake  
	 * @param position (DiscreteCoordinates): the coordinates of the Cake 
	 */
	public Cake(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position, "cake.1");
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		super.acceptInteraction(v);
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	public Area getCurrentArea() {
		return this.getOwnerArea();
	}
}
