package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;


/**
 * AreaGame is a type of Game displayed in a (MxN) Grid called Area
 * An AreaGame has multiple Areas
 */
abstract public class AreaGame implements Game {

	// Context objects
	private Window window;
	private FileSystem fileSystem;
	/// A map containing all the Area of the Game 
	private Map<String, Area> areas;
	/// The current area the game is in
	private Area currentArea;
	
	//say if the reset has been caused by a Game over
	boolean becauseGameOver;
    

    /**
     * Add an Area to the AreaGame list
     * @param a (Area): The area to add, not null
     */
    protected final void addArea(Area a){
        // TODO implements me #PROJECT #TUTO
    	areas.put(a.getTitle(), a);
    }

    /**
     * Setter for the current area: Select an Area in the list from its key
     * - the area is then begin or resume depending if the area is already started or not and if it is forced
     * @param key (String): Key of the Area to select, not null
     * @param forceBegin (boolean): force the key area to call begin even if it was already started
     * @return (Area): after setting it, return the new current area
     */
    protected final Area setCurrentArea(String key, boolean forceBegin){
        // TODO implements me #PROJECT #TUTO
    	
    	Area pastArea = currentArea;
    	
    	if ( currentArea!=null) {
    		currentArea.suspend();	
    	}
    	currentArea = areas.get(key);
    	
    	if (currentArea != null) {
    		if (forceBegin || !(currentArea.getLancee())) {
    			currentArea.begin(window, fileSystem);
    			
    			
    			if (currentArea.getTitle().equals("LevelSelector")) {
    				if(becauseGameOver) {
    					currentArea.isNeededToBeDraw("GAME OVER");
    					becauseGameOver=false;
    				}
    				else {
    				currentArea.isNeededToBeDraw("Bienvenue dans level selector! Commencez par rentrer dans une porte");
    				}
                }
    			if (currentArea.getTitle().equals("Level1")) {
    				currentArea.isNeededToBeDraw("Bienvenue dans level 1, ceci est juste un test, baladez vous!");
                }
                if (currentArea.getTitle().equals("Level2")) {
                	currentArea.isNeededToBeDraw("Bienvenue dans level 2! Ramassez cette pomme avec la touche L!");
                }
                if (currentArea.getTitle().equals("Level3")) {
                	currentArea.isNeededToBeDraw("Bienvenue dans level 3! Essayez de vous échapper");
                }
                if (currentArea.getTitle().equals("Enigme2")) {
                	currentArea.isNeededToBeDraw("Bienvenue dans level 4! Goutez le gateau pour une surprise");
                }
                if (currentArea.getTitle().equals("Enigme0")) {
                	currentArea.isNeededToBeDraw("Bienvenue dans level 5! Trouvez comment enlever les rochers pour avoir la clé");
                }
                
                
    			
    			
    		
    		
    		}
    		else { currentArea.resume(window, fileSystem);}
    	}
    	
    	if (currentArea == null) {
    		if (pastArea == null) {
    			throw new NullPointerException();
    			
    		}
    		else {
    			currentArea=pastArea;
    		}
    	}
    	
        return currentArea;
    }


    /**@return (Window) : the Graphic and Audio context*/
    protected final Window getWindow(){
        // TODO implements me #PROJECT #TUTO
        return null;
    }

    /**@return (FIleSystem): the linked file system*/
    protected final FileSystem getFileSystem(){
        // TODO implements me #PROJECT #TUTO
        return null;
    }


    /// AreaGame implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        // TODO implements me #PROJECT #TUTO
    	areas = new HashMap<>();
    	this.window= window;
    	this.fileSystem = fileSystem;
    	currentArea =null;
    	becauseGameOver=false;
        return true;
        
    }


    @Override
    public void update(float deltaTime) {
        // TODO implements me #PROJECT #TUTO
    	currentArea.update(deltaTime);
    	
    }

    @Override
    public void end() {
        // TODO save the game states somewhere
    }
    protected Area getCurrentArea() {
    	return currentArea;
    }
    
    /**
     * Setter for the attribute becauseGameOver
     * @param a (boolean): tha value we want to give to becauseGameOver
     */
    public void setBecauseGameOver(boolean a) {
    	becauseGameOver=a;
    }

}
