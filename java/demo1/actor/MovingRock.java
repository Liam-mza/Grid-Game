package demo1.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

/**
 * Graphic Entity of game demo1 that is movable
 */


public class MovingRock extends GraphicsEntity {
	private final TextGraphics text;
	
	/**
	 * Constructor of MovingRock
	 * @param position (Vector) : the position of the entity when created
	 * @param text (String) : the text that is going to be displayed
	 * Initialization of the entity 
	 * Attaches text to entity
	 */

	public MovingRock(Vector position, String text) {
		super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"), 0.1f,0.1f, null, Vector.ZERO, 1.0f, -Float.MAX_VALUE));
		this.text= new TextGraphics(text, 0.04f, Color.BLUE);
		this.text.setParent(this);
		this.text.setAnchor(new Vector(-0.3f, 0.1f));
	}
	/**
	 * Override method update in Actor
	 * Sets the current position of the graphic entity
	 */

	@Override
	public void update(float deltaTime) {
		// (here compute displacement in function of deltaTime for example)
		// for simplification, deltaTime ignored :
		setCurrentPosition(getPosition().sub(0.005f, 0.005f));
	}
	 /**
	  * Override of method draw in GraphicsEntity
	 * Draw the text as well
	 */

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		this.text.draw(canvas);
	}
}
