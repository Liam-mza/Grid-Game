package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class EnigmeArea extends Area {
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setBehavior(new EnigmeBehavior(window, getTitle()));
		initActors();
		
		return true;
	}
	
	/**
     * Getter for title of the Area
     * @return title (int): the title of the area
     */
	@Override
	public abstract String getTitle();

	@Override
	public float getCameraScaleFactor() {
		return 15;
	}
	
	/**
     * This function initializes actors in the Area such as the background
     */
	protected abstract void initActors();

}
