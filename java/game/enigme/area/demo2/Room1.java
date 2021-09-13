package game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

/**
 * 
 * Room1 is an Area for the game Demo2
 *
 */
public class Room1 extends Demo2Area {
			

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Level1";
	}

	@Override
	protected void initActors() {
		// TODO Auto-generated method stub
		registerActor(new Background(this));
	}

}
