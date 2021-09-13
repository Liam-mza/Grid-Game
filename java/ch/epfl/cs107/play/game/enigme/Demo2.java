package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import game.enigme.actor.demo2.Demo2Player;
import game.enigme.area.demo2.Room0;
import game.enigme.area.demo2.Room1;

/** 
 * Demo2 is just a specific AreaGame 
 */
public class Demo2 extends AreaGame {

	// The different areas of the game
	private Area room0;
	private Area room1;
	private Actor ghost;
	
	// the spawn coordinates in room0
	private DiscreteCoordinates level0Coor = new DiscreteCoordinates(5,5);
	
	
	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "demo2";
	}
	/**
	 * Override of the method begin :
	 * Initialize the different Areas, Actors of the game 
	 * Set the view Candidate
	 * Set the starting Area 
	 */
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		 room0 = new Room0();
		 room1 = new Room1();
		 ghost = new Demo2Player( room0 , level0Coor);
		addArea(room0);
		addArea(room1);
		setCurrentArea(room0.getTitle(), true);
		((Demo2Player) ghost).enterArea(room0, level0Coor);
		room0.setViewCandidate(ghost);
		
		return true;
	}
	
	/**
	 * Override of the method update:
	 * If the Actor ghost reach a door change the area from room0 to room1 and conversely
	 */
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if (((Demo2Player) ghost).getIsReachingDoor()) {
			if( this.getCurrentArea().getTitle().equals("LevelSelector") ) {
				
				((Demo2Player) ghost).leaveArea(room0) ;
				setCurrentArea(room1.getTitle(), false);
				((Demo2Player) ghost).enterArea(room1, new DiscreteCoordinates(5,2));
				room1.setViewCandidate(ghost);
				((Demo2Player) ghost).setIsReachingDoor(false);
			}
			else 
			{
				((Demo2Player) ghost).leaveArea(room1) ;
				setCurrentArea(room0.getTitle(), false);
				((Demo2Player) ghost).enterArea(room0, new DiscreteCoordinates(5,5));
				room0.setViewCandidate(ghost);
				((Demo2Player) ghost).setIsReachingDoor(false);
			}
		}
		
		
	}
}
