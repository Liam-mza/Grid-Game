package game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;
/**
 * 
 * Demo2Area is area of the game Demo2
 *
 */
public abstract class Demo2Area extends Area {
	
	//The scale Factor forvthe game demo2
	private static final int scaleFactor = 22;

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setBehavior(new Demo2Behavior(window, getTitle()));
		initActors();
		
		return true;
	}
	
	@Override
	public abstract String getTitle();

	@Override
	public float getCameraScaleFactor() {
		return scaleFactor;
	}
	/**
	 * Initialise the Actors of the area at its creation 
	 */
	protected abstract void initActors();

	
	
}