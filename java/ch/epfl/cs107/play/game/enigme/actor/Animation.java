package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Contains information to render an array of images on a window, which can be attached to any player
 */

public class Animation extends Sprite {

	private Sprite[][] sprites;

	/**
	 * Constructor of Animation
	 * @param name (String) : the name of the image 
	 * @param width (float) : width of the image
	 * @param height (float) : Heigth of the image
	 * @param parent (Positionable) : the position of the image (which is the same as the position of a player linked to it)
	 * @param roi (RegionOfInterest) : the region of the image that we are interested 
	 * @param anchor (Vector) : anchor in the cell
	 */

	public Animation(String name, float width, float height, Positionable parent, RegionOfInterest roi, Vector anchor) {
		super(name, width, height, parent, roi, anchor);
		// TODO Auto-generated constructor stub
		sprites = new Sprite[4][4];
		for(int i=0;i<4;++i) {
			for(int j=0;j<4;++j) {
				sprites[i][j] = new Sprite(name, 0.85f, 1.0f, this, new RegionOfInterest(i * 16 , j * 21, 16, 21), anchor);
			}
		}

	}
	
	/**
	 * Getter for a specific cell in our array
	 * @param i (int) : row of the array
	 * @param j (int) : column of the array
	 * @return sprites[i][j] (Sprite) : the cell which corresponds to row i and column j
	 */

	protected Sprite getSpriteI(int i, int j) { 
		return sprites[i][j];
	}

}


