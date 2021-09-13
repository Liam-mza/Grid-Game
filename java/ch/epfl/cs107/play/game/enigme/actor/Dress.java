package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Dress extends MovableAreaEntity{
	private Animation sprite;
	private Animation sprite2;
	private boolean on;
	public Dress(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		Vector anchor3 = new Vector(0.4f, 0.1f);
		sprite = new Animation("max.new.1", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor3);
		sprite2 = new Animation("max.new.2", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor3);
		on=false;
		// TODO Auto-generated constructor stub
	}
public void enterArea(Area area, DiscreteCoordinates position)  {
		
		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		this.setOwnerArea(area);
		area.enterAreaCells(this, this.getCurrentCells());
		
		
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

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		((EnigmeInteractionVisitor)v).interactWith(this);

		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(on) {
			sprite2.getSpriteI(1, 0).draw(canvas);
		}
		else {
		sprite.getSpriteI(1, 0).draw(canvas);
		}
	}
	
	public void setOn(boolean a) {
		on=a;
	}
	
	public boolean getOn() {
		return on;
	}
	
	public Area getCurrentArea() {
		return this.getOwnerArea();
	}

}
