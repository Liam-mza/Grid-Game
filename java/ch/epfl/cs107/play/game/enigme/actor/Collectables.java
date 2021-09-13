package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * 
 * a Collectable is an object that can be collected with a signal
 *
 */
public abstract class Collectables extends AreaEntity implements Interactable, Logic{

	// Say if the Collectable is collected or not
	private boolean isCollected;
	
	// The sprite of the Collectables
	private Sprite sprite;
	/**
	 * The constructor of Collectable
	 * @param area (Area): the Area of the Collectable
	 * @param orientation (Orientation): the Orientation of the Collectable
	 * @param position (DiscreteCoordinates): the coordinates of the Collectable
	 * @param sprite (Sprite): the sprite of the Collectable
	 */
	public Collectables(Area area, Orientation orientation, DiscreteCoordinates position, String sprite) {
		
	super(area, orientation, position);
	this.sprite = new Sprite(sprite, 1, 1.f, this);
	isCollected=false;
	}
	
	/**
	 * The constructor of Collectable that allow to choose the dimensions of the sprite
	 * @param area (Area): the Area of the Collectable
	 * @param orientation (Orientation): the Orientation of the Collectable
	 * @param position (DiscreteCoordinates): the coordinates of the Collectable
	 * @param sprite (Sprite): the sprite of the Collectable
	 * @param a (float):the width of the sprite
	 * @param b (float):the height of the sprite
	 */
	public Collectables(Area area, Orientation orientation, DiscreteCoordinates position, String sprite,float a, float b) {
		
		super(area, orientation, position);
		this.sprite = new Sprite(sprite, a, b, this);
		isCollected=false;
		}

	/**
	 * Make the Collectable enter in the given Area
	 * @param area (Area): the Area of the Collectables
	 * @param position (DiscreteCoordinates): the coordinates of the Collectable in the Area
	 */
	public void enterArea(Area area, DiscreteCoordinates position)  {
		
		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		this.setOwnerArea(area);
		area.enterAreaCells(this, this.getCurrentCells());
		
		
	}
	
	/**
	 * Setter for the attribut isCollected
	 * @param a (boolean): the value that we want to give to isCollected
	 */
	public void setIsCollected (boolean a) {
		isCollected=a;
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

	/**
	 * Override of the method draw:
	 * draw the sprite only if the Collectable hasn't been collected	
	 */
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (!isCollected) {
		sprite.draw(canvas);
		}
	}
	/**
	 * The Collectabled take cell space only if it hasn't been collected
	 */
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return !isCollected;
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
	
	/**
	 * Getter for isCollected
	 * @return isCollected (boolean)
	 */
	protected boolean getIsCollected() {
		return isCollected;
	}
	
	/**
	 * The signal of a Collectable is on if it has been collected
	 */
	@Override
	public boolean isOn() {
		
		if (getIsCollected()) {
			
			return true;
			
		}
		else {
		return false;
		}
	}
	
	

}
