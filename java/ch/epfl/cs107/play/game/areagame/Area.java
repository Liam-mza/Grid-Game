package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {

	// Context objects
	private Window window;
	private FileSystem fileSystem;
	/// List of Actors inside the area
	private List<Actor> actors;
	
	List<Interactor>interactors;
	
	// Lists of actor that need to be added or removed
	private List<Actor> registeredActors; 
	private List<Actor> unregisteredActors;
	
	// Camera Parameter
	// actor on which the view is centered 
	private Actor viewCandidate;
	// effective center of the view 
	private Vector viewCenter;
	
	/// The behavior Map
	private AreaBehavior areaBehavior;

	//Give the information if the Area has been launch one time 
	private boolean lancee;
	
	// Lists of Interactables whit the coordinates of the cells that they want to enter in or being removed from
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter ;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave ;
    // Context objects
	private Dialog d;
	
	// the text of the dialog
	private String text;
	
	//Say if the dialog need to be draw
	boolean needToBeDraw;
	
	//Say if the game is in pause
	boolean pause;
	

	
	
    // TODO implements me #PROJECT #TUTO

	/**
	 * 	Getter for the boolean Lancee
	 *  @return (Boolean): the attribut lancee, say if the Area has already been launched */
	public boolean getLancee() {
		return lancee;
	}
	
	/** 
	 * Getter for camera scale factor
	 * @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();
    
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {
        // TODO implements me #PROJECT #TUTO
   
    	
    	boolean errorOccured = !actors.add(a);
    	
    	if(a instanceof Interactable) errorOccured = errorOccured || !enterAreaCells(((Interactable)a), ((Interactable) a).getCurrentCells());
    	
    	if(a instanceof Interactor) errorOccured = errorOccured || !interactors.add((Interactor) a);
    	
    	if(errorOccured && !forced) { 
    		System.out.println("Actor " + a + " cannot be completely added, so remove it from where it was");
    	removeActor(a, true);
    	}
    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced){
        // TODO implements me #PROJECT #TUTO
    	
    	boolean errorOccured = !actors.remove(a);
    	
    	if(a instanceof Interactable) errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
    	
    	if(a instanceof Interactor) errorOccured = errorOccured || !interactors.remove((Interactor) a);
    	
    	if(errorOccured && !forced) { 
    		System.out.println("Actor " + a + " cannot be completely removed, so let it where it was");
    		addActor(a, true);
    	}
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
        // TODO implements me #PROJECT #TUTO
    	registeredActors.add(a);
        return true;
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){
        // TODO implements me #PROJECT #TUTO
    	unregisteredActors.add(a);
        return true;
    }

    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
        return areaBehavior.getWidth();
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
        return areaBehavior.getHeight();
    }

    /** 
     * Getter for the Window Keyboard 
     * @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        // TODO implements me #PROJECT #TUTO
        return window.getKeyboard();
    }

    /// Area implements Playable
    
    
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        // TODO implements me #PROJECT #TUTO
    	actors = new LinkedList<>();
    	registeredActors = new LinkedList<>();
    	unregisteredActors = new LinkedList<>();
    	interactablesToEnter = new HashMap<>();
    	interactablesToLeave = new HashMap<>();
    	this.window=window;
    	this.fileSystem=fileSystem;
    	viewCandidate=null;
    	viewCenter= Vector.ZERO;
    	lancee = true;
    	interactors = new LinkedList<>();
    	text="ok";
    	pause=false;
    	
    	
    	needToBeDraw=false;
        return true;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
        return true;
    }
    /**
     * This an override that at each update:
     * Checks if the the key b of the keyboard is pressed, if yes puts the game in pause
     * Updates and draws the actors and the camera
     * checks if a dialog need to be drawn, if yes draws it
     * 
     */
    @Override
    public void update(float deltaTime) {
        // TODO implements me #PROJECT #TUTO
    	Keyboard keyboard = this.getKeyboard();
 	   Button b = keyboard.get(Keyboard.B);
    	if(b.isPressed()) {

    		
    		this.isNeededToBeDraw("en pause");
    		
    	}
    	
    	purgeRegistration();
    	updateCamera ();
    	
    	
    	for (int i=0; i< actors.size(); ++i) {
    		
    		
    		if(!pause) {
    		(actors.get(i)).update(deltaTime);
    		}
    		(actors.get(i)).draw(window);

    	}
    	
    	if (interactors!= null) {
    		
    		for (Interactor interactor : interactors) { 
    		
    			if (interactor.wantsCellInteraction()) {
    				
    				areaBehavior.cellInteractionOf(interactor);
    			}
    			if (interactor.wantsViewInteraction()) {
    				
    				areaBehavior.viewInteractionOf(interactor);
    			} 
    		}
    	}
    	if(needToBeDraw) {
    		isNeededToBeDraw(text);
    		
    	}
    	
    	if (needToBeDraw) {
    		
    		d = new Dialog(text, "dialog.1", this);
			d.draw(window);
		}
    	
    }

    /**
     * Update the viewCenter of the camera and can focus it on a designated view candidate 
     */
    private void updateCamera () {
        // TODO implements me #PROJECT #TUTO
    	if (viewCandidate != null) {
    		viewCenter = viewCandidate.getPosition();
    	}
    	Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
    	window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        // Do nothing by default
    	purgeRegistration();
    }

    /**
     * End the Area
     */
    @Override
    public void end() {
        // TODO save the AreaState somewhere
    	lancee=false;
    }
    
    /**
     * This method add the Actors (interactable and interactor) that are waiting to be register to the Area and remove the one that are waiting to 
     * be unregistered from the Area 
     * Once it is done this method clear the lists registeredActors, unregisteredActors, interactablesToEnter and interactablesToLeave
     */
    private final void purgeRegistration() {
    	for(int i=0; i<registeredActors.size(); ++i) {
    		addActor(registeredActors.get(i), false);	
    	}
    	
    	for(int i=0; i<unregisteredActors.size(); ++i) {
    		
    		removeActor(unregisteredActors.get(i), false);	
    	}
    	registeredActors.clear();
    	unregisteredActors.clear();
    	
    	if(interactablesToEnter.size() !=0) {
    		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToEnter.entrySet()) {
    		
    		//areaBehavior.getCells()[ pair.getValue().get(i).x][pair.getValue().get(i).y].enter(pair.getKey());
    			areaBehavior.enter(pair.getKey(),  pair.getValue());
    		
    		}
    	}
    	
    	if(interactablesToLeave.size() !=0) {
    		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToLeave.entrySet() ) {
    		areaBehavior.leave(pair.getKey(),  pair.getValue());
    		}
    	}
    	
    	interactablesToEnter.clear();
    	interactablesToLeave.clear();
    }
    
    /**
     * Setter for the view candidate
     * @param a (Actor): the Actor that is going to be the view candidate 
     */
    public final void setViewCandidate(Actor a){ 
    	this.viewCandidate = a;
    }
    
    /**
     * Setter of the areaBehavior of the Area
     * @param ab (AreaBehavior): the areaBehavior of the Area
     */
    
    protected final void setBehavior(AreaBehavior ab) {
    	
    	areaBehavior = ab;
    	
    }
    
    /**
     * Say if an interactable can leave some cells of the Area
     * @param entity (Interactable):The interactable that need to leave some cells of the Area
     * @param coordinates (List<DiscreteCoordinates>): the list of the coordinates of the cells that the interactable needs to leave
     * @return True if the interactable can leave the given cells of the Area
     */
   
    public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {

    	if ( areaBehavior.canLeave(entity, coordinates) ){
    		interactablesToLeave.put(entity, coordinates);
    		return true;
    	}	
    	else {
    		return false;
    	}
    }
    
    /**
     * Say if an interactable can enter in some cells of the Area
     * @param entity (Interactable):The interactable that need to enter in some cells of the Area
     * @param coordinates (List<DiscreteCoordinates>): the list of the coordinates of the cells that the interactable needs to enter in
     * @return True if the interactable can enter in the given cells of the Area
     */
    public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	
    	
    	if ( areaBehavior.canEnter(entity, coordinates)){
    		interactablesToEnter.put(entity, coordinates);
    		
    		return true;
    	}
    	else {
    		return false;
    	}	
    }
    
    /**
     * Getter for the Area behavior of the Area
     * @return areaBehavior (AreaBehavior): the Area behavior of the Area
     */
   public AreaBehavior getAreaBehavior () {
	   return areaBehavior;
   }
   
   
   /**
    * This method will initiate a dialog with the given text that can be removed with the key w
    * @param text text (string): the text of the dialog 
    */
   public void isNeededToBeDraw(String text) {
	   this.text=text;
	   Keyboard keyboard = this.getKeyboard();
	   Button w = keyboard.get(Keyboard.W);
	   if (w.isDown()) {
		   dontNeedToBeDraw();
	   }
	   else {
		   needToBeDraw=true;
		   pause=true;
	   }
	   
   }
   
   /**
    * Say that the dialog don't need to be drawn anymore
    */
   public void dontNeedToBeDraw() {
	   needToBeDraw=false;
	   pause = false;
   }
   
    
}

