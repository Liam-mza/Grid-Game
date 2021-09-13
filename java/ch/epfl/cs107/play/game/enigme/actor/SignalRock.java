package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
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

public class SignalRock extends AreaEntity implements Interactable{
	
	private Sprite sprite;
	private Logic s;
	
	/**
	 * Constructor of SignalRock
	 * @param currentArea (Area): the Area of the SignalRock  
	 * @param orientation (Orientation): the Orientation of the SignalRock
	 * @param position  (DiscreteCoordinates): the coordinates of the door
	 * @param s (Logic): the signal link with the door 
	 */
	public SignalRock(Area area, Orientation orientation, DiscreteCoordinates position, Logic s) {
		super(area, orientation, position);
		sprite= new Sprite("rock.3", 1, 1.f, this);
		this.s= s;
	}
	
	/**
	 * Make the SignalRock enter in the given Area
	 * @param area (Area): the Area of the SignalRock
	 * @param position (DiscreteCoordinates): the coordinates of the SignalRock in the Area
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		
		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		this.setOwnerArea(area);
		area.enterAreaCells(this, this.getCurrentCells());
		
		
	}
	/**
	 * The SignalRock is draw only if the signal s is off
	 */
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(!s.isOn()) {
			sprite.draw(canvas);
		}
		
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	/**
	 * The SignalRock is take cell space only if the signal s is off
	 */
	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return !s.isOn();
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

}
