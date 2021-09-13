package ch.epfl.cs107.play.game.enigme;



import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.Level4;
import ch.epfl.cs107.play.game.enigme.area.Level5;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

    // The player is a concept of RPG games
	private Actor player;

	// The different Area of the game
	private Area levelSelector;
	private Area level1;
	private Area level2;
	private Area level3;
	private Area level4;
	private Area level5;
	
	// the spawn coordinates in LevelSelector
	private DiscreteCoordinates level0Coor = new DiscreteCoordinates(5,5);
	
	
    /// Enigme implements Playable

    @Override
    public String getTitle() {
        return "Enigme";
    }
    /**
	 * Override of the method begin :
	 * Initialize the different Areas, Actors of the game 
	 * Set the view Candidate
	 * Set the starting Area 
	 */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        // TODO implements me #PROJECT
    	super.begin(window, fileSystem);
    	levelSelector = new LevelSelector();
    	level1 = new Level1();
    	level2 = new Level2();
    	level3 = new Level3();
    	level4 = new Level4();
    	level5= new Level5();
    	player = new EnigmePlayer( levelSelector , level0Coor);
    	addArea(levelSelector);
    	addArea(level1);
    	addArea(level2);
    	addArea(level3);
    	addArea(level4);
    	addArea (level5);
    	setCurrentArea (levelSelector.getTitle(), true);
    	((EnigmePlayer) player).enterArea(levelSelector, level0Coor);
    	
    	
        return true;
    }
    /**
	 * Override of the method update:
	 * If the Actor ghost interact with a door change the area for the destination Area of the door
	 * if the player die (GAME OVER) reset the game
	 */
    @Override
    public void update(float deltaTime) {
        // TODO implements me #PROJECT
    	
    	
    	super.update(deltaTime);
    	
    	
    	if (((EnigmePlayer) player).getIsPassingDoor()) {
    		
    		((EnigmePlayer) player).leaveArea(getCurrentArea());
    		setCurrentArea(((EnigmePlayer) player).passedDoor().getDestArea(), false );
    		((EnigmePlayer) player).enterArea(getCurrentArea(), ((EnigmePlayer) player).passedDoor().getDestCoor() );
    		
    	}
    	
    	if (((EnigmePlayer) player).getGameOver()) {
    		
    		setBecauseGameOver(true);
    		((EnigmePlayer) player).leaveArea(getCurrentArea());
        	
    		levelSelector.end();
        	level1.end();
        	level2.end();
        	level3.end();
        	level4.end();
        	level5.end();
        	setCurrentArea(levelSelector.getTitle(),false);
        	((EnigmePlayer) player).enterArea(levelSelector,level0Coor);
        	((EnigmePlayer) player).setGameOver(false);
        	((EnigmePlayer) player).isHeal(3);
    	}

    	
    	
    	
    }
    
   


    @Override
    public int getFrameRate() {
        return 24;
    }
}
