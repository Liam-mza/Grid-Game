package demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import demo1.actor.MovingRock;
/**
 * 
 * Demo 1 is a game 
 *
 */
public class demo1 implements Game  {
	
	// Actors of demo1
	private Actor actor1;
	private Actor actor2;
	
	//The Window of demo1
	private Window window;
	
	//The filesysteme of demo1
	private FileSystem fileSystem;
	
	//The texte that is giong to follow our circle
	private final TextGraphics boom = new TextGraphics("BOUM!!!", 0.06f, Color.RED);
	
	//The radius of the circle
	private float radius;
	
	/**
	 * Getter for the title of demo1
	 * @return (String): the title of the game
	 */

	public String getTitle() {
		return "demo1";
	}
	
	/**
	 * Getter for the title of demo1
	 * @return (int): the framerate of the game
	 */
	public int getFrameRate(){
		return 24;
	}
	
	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Override of the method update in updatable
	 * Evaluates if the key "DownArrow" is pressed, if it is it updates actor in area
	 * Checks the distance between circle and rock, if the distance is inferior to the radius of circle, draw text
	 */

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
		Keyboard keyboard = window.getKeyboard(); 
		Button downArrow = keyboard.get(Keyboard.DOWN);
		
		
		if(downArrow.isDown()) {
			actor2.update(deltaTime);
			}
		if ( (actor1.getPosition().sub(actor2.getPosition())).getLength() <= radius) {
			boom.setAnchor(new Vector(0.04f,0.04f));
			boom.draw(window);
		}
		actor1.draw(window);
		actor2.draw(window);
	}
	
	/**
	 * Override of the method begin in playable
	 * Creates new actors in area
	 */

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		this.window = window;
		this.fileSystem = fileSystem;
		this.radius=0.2f;
		actor1 = new GraphicsEntity(Vector.ZERO,new ShapeGraphics(new Circle(radius), null,Color.RED, 0.005f));
		actor2 = new MovingRock(new Vector(0.3f, 0.3f), "je suis un caillou");
		return true;
	}
}



