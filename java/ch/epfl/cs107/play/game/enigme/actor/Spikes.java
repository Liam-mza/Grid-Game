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
import ch.epfl.cs107.play.window.Canvas;

/**
 * 
 * A Spikes is an object that make damage when we walk on it 
 *
 */
public class Spikes extends AreaEntity implements Interactable {
	
	//The sprite of the Spikes
	private Sprite sprite;
	
	//Two boolean that help us manage the interactions
	private boolean wantToInteract;
	private boolean hasInteracted;

	/**
	 * 
	 * @param area (Area): the Area of the Spikes
	 * @param orientation (Orientation): the Orientation of the Spikes
	 * @param position (DiscreteCoordinates): the coordinates of the Spikes
	 */
	public Spikes(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// TODO Auto-generated constructor stub
		sprite= new Sprite("platformIndustrial_052", 1, 1.f, this);
	}

	/**
	 * Make the Spikes enter in the given Area
	 * @param area (Area): the Area of the Spikes
	 * @param position (DiscreteCoordinates): the coordinates of the Spikes in the Area
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
		sprite.draw(canvas);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
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
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		wantToInteract=true;
		if (!hasInteracted) {
			((EnigmeInteractionVisitor)v).interactWith(this);
			hasInteracted=true;

		}
	}
	@Override
	public void update(float deltaTime) {
		if(wantToInteract) {
			wantToInteract=false;

		}
		else {
			hasInteracted=false;

		}
	}

}

