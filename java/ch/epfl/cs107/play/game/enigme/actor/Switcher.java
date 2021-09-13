package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

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
 * A Switcher is an object that has a state which can be switch with an interaction
 *
 */
public abstract  class Switcher extends AreaEntity implements Logic{
	
	//The sprite that is going to be drawn 
	private Sprite sprite;
	
	//The sprite of the Switcher when activate
	private Sprite  spriteOn;
	
	//The sprite of the Switcher when deactivate
	private Sprite  spriteOff;
	
	//The sate of the switcher at its creation
	private boolean defaultState;
	
	//the state of the switcher
	private boolean currentState;

	/**
	 * 
	 * @param area (Area): the Area of the Switcher
	 * @param orientation (Orientation): the Orientation of the Switcher
	 * @param position (DiscreteCoordinates): the coordinates of the Switcher
	 * @param spriteOn (Sprite): The sprite of the Switcher when activate
	 * @param spriteOff (Sprite): The sprite of the Switcher when deactivate
	 * @param defaultState (boolean): The sate of the switcher at its creation
	 */
	public Switcher(Area area, Orientation orientation, DiscreteCoordinates position,String spriteOn, String spriteOff, boolean defaultState) {
		
		super(area, orientation, position);
		
		this.spriteOn= new Sprite(spriteOn, 1, 1.f, this);
		this.spriteOff = new Sprite(spriteOff, 1, 1.f, this);
		this.defaultState= defaultState;
		this.currentState = defaultState;
	}
	
	/**
	 * Make the Switcher enter in the given Area
	 * @param area (Area): the Area of the Switcher
	 * @param position (DiscreteCoordinates): the coordinates of the Switcher in the Area
	 */
	public void enterArea(Area area, DiscreteCoordinates position)  {
		
		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		this.setOwnerArea(area);
		area.enterAreaCells(this, this.getCurrentCells());
		
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if (isOn()) {
			sprite = spriteOn;
		}
		else {
			sprite = spriteOff;
		}
		sprite.draw(canvas);
		
	}
	public void setCurrentState(boolean a) {
		currentState=a;
	}
	
	public boolean getCurrentState() {
		return currentState;
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		
		return currentState;
	}


	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}
	
	@Override
	public void update (float deltaTime) {
		super.update(deltaTime);
		
	}
	
	

}
