package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{
	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	protected final int width, height;
	/// We will convert the image into an array of cells 
	private final Cell[][] cells;

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
    	
        // TODO implements me #PROJECT #TUTO
    	
    	behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
    	width = behaviorMap.getWidth();
    	height = behaviorMap.getHeight();
    	cells = new Cell [width][height];
    	
    	
    }
    
    /**
     * Getter for the behavior map of the AreaBehavior
     * @return behaviorMap (Image): the behavior map of the AreaBehavior
     */
    protected Image getBehaviorMap() {
    	return behaviorMap;
    }
   
    /**
     * 
     * Cell is a private inner Class of Area Behavior 
     * Area Behavior is a grid of Cells
     * 
     *
     */
    public abstract class Cell implements Interactable { 
    	private DiscreteCoordinates coordonées;
    	private Set<Interactable> interactables;
    	
    	 /**
         * Default Cell Constructor
         * @param x (int): the coordinates x of our cell
         * @param y (int): the coordinates y of our cell
         */
    	public Cell (int x, int y) {
    		coordonées= new DiscreteCoordinates(x,y);
    		interactables= new HashSet<>();
    	}
    	
    	/**
    	 * Getter for the list of interactables that are in the cell
    	 * @return interactables (Set<Interactable>): the list of interactables that are in the cell
    	 */
    	public Set<Interactable> getinteractables(){
    		return interactables;
    	}
    	
    	/**
         * Getter for the Current cells 
         * @return (List<DiscreteCoordinates>) : the list of the Coordinates of the current cells
         */
    	public List<DiscreteCoordinates> getCurrentCells(){
    		
    		List<DiscreteCoordinates> liste = new LinkedList<>();
    		liste.add(coordonées);
    		return liste;
    	}
    	
    	/**
    	 * Add the given Interactable to the list interactables (so add  the given Interactable to the cell)
    	 * @param inter (Interactable): the Interactable that need to be add to the cells
         * 
         */
    	public void enter(Interactable inter) {
    		interactables.add(inter);
    	}
    	
    	/**
    	 * Remove the given Interactable from the list interactables (so remove it from the cell)
    	 * @param inter (Interactable): the Interactable that need to be removed from the cells
         * 
         */
    	public void leave (Interactable inter) {
    		interactables.remove(inter);
    	}
    	
    	/**
    	 * Check if the given Interactable can enter in the cell
    	 * @param entity (Interactable): the Interactable that need to be add to the cells
         * @return (Boolean): true if it can be add, false otherwise
         */
    	protected abstract boolean canEnter(Interactable entity);
    	
    	/**
    	 * Check if the given Interactable can leave in the cell
    	 * @param entity (Interactable): the Interactable that need to be removed from the cells
         * @return (Boolean): true if it can be removed, false otherwise
         */
    	protected abstract boolean canLeave(Interactable entity);
    	
    	/**
    	 * If there is an Interactable in the cell that is Cell interactable: launch the interaction of the given Interactor with the Interactable
    	 * @param interactor (Interactor):  the Interactor that want to interact with the interactables of the cell 
    	 */
    	private void cellInteractionOf(Interactor interactor) {
    		
    		for(Interactable interactable :interactables){
    			if(interactable.isCellInteractable()) interactor.interactWith(interactable);
    			}
    	}
    	
    	/**
    	 * If there is an Interactable in the cell that is view interactable: launch the interaction of the given Interactor with the Interactable
    	 * @param interactor (Interactor):  the Interactor that want to interact with the interactables of the cell 
    	 */
    	private void viewInteractionOf(Interactor interactor) {
    		for(Interactable interactable : interactables){
    			if(interactable.isViewInteractable()) interactor.interactWith(interactable);
    			}
    	}

    	
    }
    //END OF THE CLASS CELL
    
    /**
     * Getter for the cells width
     * @return width (int) : the width which is the number of rows
     */
    public int getWidth() {
    	return width;
    }
    
    /**
     * Getter for cells height
     * @return height (int) : the height which is the number of cols
     */
    public int getHeight() {
    	return height;
    }
    
    /**
     * Getter for the tab of cells of Area behavior
     * @return cells (Cell[][]) : the tab of cells of Area behavior
     */
    public Cell[][] getCells() {
    	return cells;
    }
    
    /**
     * Say if the entity can leave some cells
	 * @param entity (Interactable): the Interactable that need to leave the cells
	 * @param coordinates (List<DiscreteCoordinates>) the list of coordinates of the cells that are going to be leaved by entity
     * @return (Boolean): true if entity can leave the cells, false otherwise
     */
    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	
    	for (int i=0; i<coordinates.size(); ++i) {
    		if ( !((cells[coordinates.get(i).x][coordinates.get(i).y]).canLeave(entity)) ) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    /**
     * Say if the entity can enter in some cells
	 * @param entity (Interactable): the Interactable that need to enter in the cells
	 * @param coordinates (List<DiscreteCoordinates>) the list of coordinates of the cells that entity want to enter in
     * @return (Boolean): true if entity can enter in the cells, false otherwise
     */
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	
    	for (int i=0; i<coordinates.size(); ++i) {
    		
			
    		if ( coordinates.get(i).x < 0 || coordinates.get(i).x >= cells.length ) {
    			
    			return false;
    		}
    		
    		if (coordinates.get(i).y < 0 || coordinates.get(i).y >= height) {
    		return false;
    		}
    		
    		if ( !( (cells[coordinates.get(i).x][coordinates.get(i).y]).canEnter(entity) ) ) {
    			
    			return false;
    		}
    	}
    	return true;
    	
    }
    
    /**
     * Remove an entity from some cells
	 * @param entity (Interactable): the Interactable that need to be removed from the cells
	 * @param coordinates (List<DiscreteCoordinates>) the list of coordinates of the cells that entity need to leave
     */
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	
    	for (int i=0; i<coordinates.size(); ++i) {
    		
    		(cells[coordinates.get(i).x][coordinates.get(i).y]).leave(entity);
    	}
    }
    
    /**
     * add an entity to some cells
	 * @param entity (Interactable): the Interactable that need to be add the cells
	 * @param coordinates (List<DiscreteCoordinates>) the list of coordinates of the cells that entity want to enter in
     */
    
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	
    	for (int i=0; i<coordinates.size(); ++i) {
    		(cells[coordinates.get(i).x][coordinates.get(i).y]).enter(entity);
    	}
    	
    }
    
    
    /**
     * For each of the Current cells of the given Interactor ask for cell interactions 
     * @param interactor (Interactor): the interactor that want to interact
     */
    public void cellInteractionOf(Interactor interactor) {
    	for (int i=0; i<interactor.getCurrentCells().size(); ++i ) {
    		(cells [interactor.getCurrentCells().get(i).x][interactor.getCurrentCells().get(i).y]).cellInteractionOf(interactor);
    	}
    }
    /**
     * For each of the Field of view cells of the given Interactor ask for view interactions
     * @param interactor (Interactor): the interactor that want to interact
     */
    public void viewInteractionOf(Interactor interactor) {
    	for (int i=0; i<interactor.getFieldOfViewCells().size(); ++i ) {
    		
    		(cells [interactor.getFieldOfViewCells().get(i).x][interactor.getFieldOfViewCells().get(i).y]).viewInteractionOf(interactor);
    	}
    }
    
    
    
}
