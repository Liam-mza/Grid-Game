package game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * 
 * Room0 is an area of the game Demo2
 *
 */
public class Room0 extends Demo2Area{
	

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "LevelSelector";
	}

	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		registerActor(new Background(this));
	}

}
