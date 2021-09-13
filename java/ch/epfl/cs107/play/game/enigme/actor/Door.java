package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity implements Interactable {
	
	//The area that the door lead on
	private String destArea;
	//The Coordinates in the destination Area where the door send us
	private DiscreteCoordinates destCoor;
	
	//The Coordinates of the cell occupied by the door 
	private List<DiscreteCoordinates> occupiedCells;
	
	//The sprite of the door 
	private Sprite sprite;
	
	/**
	 * The constructor of Door 
	 * @param currentArea (Area): the Area of the Door 
	 * @param destArea (Area):the area that the door lead on
	 * @param orientation (Orientation): the Orientation
	 * @param destCoor (DiscreteCoordinates): the Coordinates in the destination Area where the door send us
	 * @param position (DiscreteCoordinates): the Coordinates of the door in its Area
	 * @param coordinates (Ellipse of DiscreteCoordinates): Coordinates of the additional cells occupied by the door 
	 */
	public Door(Area currentArea, String destArea,  Orientation orientation, DiscreteCoordinates destCoor, DiscreteCoordinates position, DiscreteCoordinates ...coordinates  ) {
		super(currentArea, orientation, position);
		
		this.destArea = destArea;
		this.destCoor= destCoor;
		occupiedCells = new LinkedList<>();
		occupiedCells.add(position);
		
		for (DiscreteCoordinates coor: coordinates) {
			occupiedCells.add(coor);
		}
		if (destArea.equals("")) {
		sprite= new Sprite("door.close.1", 1, 1.f, this);
		}
		else {
			sprite=null;
		}
		
	}
	/**
	 * Make the Door enter in the given Area
	 * @param area (Area): the Area of the Door
	 * @param position (DiscreteCoordinates): the coordinates of the Door in the Area
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		
		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		this.setOwnerArea(area);
		area.enterAreaCells(this, this.getCurrentCells());
		
		
	}
	/**
	 * Getter for the destination Area of the Door 
	 * @return destArea (Area): the destination Area of the Door 
	 */
	public String getDestArea() {
		return destArea;
	}
	
	/**
	 * Getter for the destination Coordinates of the Door 
	 * @return destCoor (DiscreteCoordinates): the destination Coordinates of the Door 
	 */
	public DiscreteCoordinates getDestCoor() {
		return destCoor;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		//return occupiedCells;
		return occupiedCells;
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
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (sprite != null) {
		sprite.draw(canvas);
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	
	
	



}
