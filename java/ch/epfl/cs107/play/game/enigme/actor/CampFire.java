package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class CampFire extends AreaEntity{
	private Sprite sprite1;
	private Sprite sprite2;
	private Sprite sprite3;
	private int count;
	private boolean all;
	private final int e;
	
	public CampFire(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		Vector anchor3 = new Vector(0.4f, 0.1f);
		sprite1= new Sprite("fire.on.1", 1, 1.f, this,anchor3);
		sprite2= new Sprite("fire.on.2", 1, 1.f, this,anchor3);
		sprite3= new Sprite("fire.off", 1, 1.f, this,anchor3);
		count=0;
		all=false;
		e=6;
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
		
		if(!all) {
			sprite3.draw(canvas);
		}
		else {
		if (count%4 == 0) {
			sprite1.draw(canvas);
		}
		else {
			sprite2.draw(canvas);
		}
		++count;
		
		if (count==60) {
			count=0;
		}
		}
		
		
	}
	public void setAll(boolean a) {
		 all=a;
	}
	public boolean getAll() {
		 return all;
	}
}
