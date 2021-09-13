package game.enigme.actor.demo2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

/**
 * 
 * Demo2Player is a player for the game Demo2
 *
 */
public class Demo2Player extends MovableAreaEntity {

// Say if the actor is reaching a door
	private boolean isReachingDoor;
	
// The sprite of the actor	
	private Sprite sprite;
	
/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;
	
	
	/**
	 * Constructor of Demo2Player
	 * @param area (Area): the Area of the Demo2Player
	 * @param orientation (Orientation): the orientation of the Demo2Player
	 * @param position (DiscreteCoordinates): the coordinates of the Demo2Player
	 */
	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		isReachingDoor = false;
		sprite = new Sprite("ghost.1", 1, 1.f, this);	
		
	}
	
	/**
	 * Constructor of Demo2Player that initialize the Orientation at down
	 * @param area (Area): the Area of the Demo2Player
	 * @param orientation (Orientation): the orientation of the Demo2Player
	 * @param position (DiscreteCoordinates): the coordinates of the Demo2Player
	 */
	public Demo2Player(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN , coordinates);
		isReachingDoor = false;
		sprite = new Sprite("ghost.1", 1, 1.f, this);
	}
	
	/**
	 * Make the Demo2Player enter in the given Area
	 * @param area (Area): the Area of the Demo2Player
	 * @param position (DiscreteCoordinates): the coordinates of the Demo2Player in the Area
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		
		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		this.setOwnerArea(area);
		this.resetMotion();
	}
	/**
	 * Make the Demo2Player leave the given Area
	 * @param area (Area): the Area of the Demo2Player
	 */
	public void leaveArea(Area area) {
		
		this.getOwnerArea().unregisterActor(this);
	}
	
	/**
	 * Setter for the attribute isPassingDoor
	 * @param a (boolean): the value we want to give to isPassingDoor
	 */
	public void setIsReachingDoor (boolean a) {
		isReachingDoor=a;
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
		return true;
	}
	
	@Override
    public void update(float deltaTime) {
		
		Keyboard keyboard = this.getOwnerArea().getKeyboard();
		Button left = keyboard.get(Keyboard.LEFT);
		
		Keyboard keyboard2 = this.getOwnerArea().getKeyboard();
		Button right = keyboard2.get(Keyboard.RIGHT);
		
		Keyboard keyboard3 = this.getOwnerArea().getKeyboard();
		Button up = keyboard3.get(Keyboard.UP);
		
		Keyboard keyboard4 = this.getOwnerArea().getKeyboard();
		Button down = keyboard4.get(Keyboard.DOWN);
		
		if (left.isDown()) {
			if(this.getOrientation() == Orientation.LEFT ) {
				this.move(ANIMATION_DURATION);
			}
			else {
				this.setOrientation(Orientation.LEFT);
			}	
		}
		
		if (right.isDown()) {
			if (this.getOrientation() == Orientation.RIGHT) {
				this.move(ANIMATION_DURATION);
			}
			else {
				this.setOrientation(Orientation.RIGHT);
			}
		}
		
		if (up.isDown()) {
			if (this.getOrientation() == Orientation.UP) {
				this.move(ANIMATION_DURATION);
			}
			else {
				this.setOrientation(Orientation.UP);
			}
		}
		
		if (down.isDown()) {
			if (this.getOrientation() == Orientation.DOWN) {
				this.move(ANIMATION_DURATION);
			}
			else {
				this.setOrientation(Orientation.DOWN);
			}
		}
		super.update(deltaTime);
		
		
		
		
		
	}
	
	/**
	 * Getter for the attribute isPassingDoor
	 * @return isPassingDoor (boolean): true if the player is interacting with a door 
	 */
	public boolean getIsReachingDoor() {
		return isReachingDoor;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		sprite.draw(canvas);
	}
	
	@Override
	protected  boolean move(int framesForMove){
		
		
		
		boolean move =super.move(framesForMove);
		
			
		 	if ( ((Demo2Behavior) this.getOwnerArea().getAreaBehavior()).isADoor(this.getTargetMainCellCoordinates()) ) {
		 		this.setIsReachingDoor(true);
		 	}
			
		
			return move;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// Do nothing in the game Demo2
		
	}
	
	
}
