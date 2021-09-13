package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

    // TODO implements me #PROJECT #TUTO
	/// Indicate if the actor is currently moving
	private boolean isMoving;
	/// Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;
	/// The target cell (i.e. where the mainCell will be after the motion)
	private DiscreteCoordinates targetMainCellCoordinates;

    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        // TODO implements me #PROJECT #TUTO
        this.resetMotion();
        
    }
    
    /**
     * Getter for the coordinates of the main cell occupied by the MovableAreaEntity
     * @return targetMainCellCoordinates (DiscreteCoordinates): the target main cell coordinates 
     */
    protected final DiscreteCoordinates getTargetMainCellCoordinates(){
    	return targetMainCellCoordinates;
    }
    
    /**
     * Getter for the coordinates of the  cells that the MovableAreaEntity is going to leave
     * @return liste (List<DiscreteCoordinates>): the list  of the  cells that the MovableAreaEntity is going to leave
     */
    protected final List<DiscreteCoordinates> getLeavingCells(){
    	
    	List<DiscreteCoordinates> liste = new LinkedList<>();
    	liste.addAll(this.getCurrentCells());
    	return liste;
    }
    
    /**
     * Getter for the coordinates of the  cells that the MovableAreaEntity is going to enter in
     * @return currentCoor (List<DiscreteCoordinates>): the list  of the  cells that the MovableAreaEntity is going to enter in
     */
    protected final List<DiscreteCoordinates> getEnteringCells(){
    	
    	
    	List<DiscreteCoordinates> currentCoor =new LinkedList<>();
    	
    	
    	for (int i=0; i<this.getCurrentCells().size(); ++i) {
    		currentCoor.add(this.getCurrentCells().get(i).jump(getOrientation().toVector()));
    	}
    	
    	
    	return currentCoor;
    }
    
    
    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        // TODO implements me #PROJECT #TUTO
    	isMoving = false;
    	framesForCurrentMove = 0;
    	targetMainCellCoordinates = this.getCurrentMainCellCoordinates();

    }

    /**
     * Take in charge the movement of MovableAreaEntity 
     * @param frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
  
    protected  boolean move(int framesForMove){
        // TODO implements me #PROJECT #TUTO
    	
    	if (!this.isMoving || targetMainCellCoordinates.equals(this.getCurrentMainCellCoordinates()) ) {
    		// TODO : add area conditions here
    		
    		
    		if ( this.getOwnerArea().enterAreaCells(this, this.getEnteringCells()) && this.getOwnerArea().leaveAreaCells(this, this.getLeavingCells()) ) {
    			if( framesForMove<1) {
    				framesForCurrentMove=1;
    			}
    			else {
    				framesForCurrentMove=framesForMove;
    			}
    			
    			Vector orientation = getOrientation().toVector(); 
    			targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
    			isMoving=true;
    			
    			return true;
    		}
    		else { 
    			
    			return false;
        		}
    	}
    	else { 
    		return false;
    		}
    	
       
    }


    /// MovableAreaEntity implements Actor
    
  
    /**
     * Override of the method update:
     * if the MovableAreaEntity is moving make the movement progress, else just reset motion
     */
    @Override
    public void update(float deltaTime) {
        // TODO implements me #PROJECT #TUTO
    	
    	if (isMoving) {
    		if (!targetMainCellCoordinates.equals(this.getCurrentMainCellCoordinates())) {
    	
    		Vector distance = getOrientation().toVector(); 
    		distance = distance.mul(1.0f / framesForCurrentMove); 
    		setCurrentPosition(getPosition().add(distance));
    		
    		}
    		else {
    			this.resetMotion();
    		}
    	}
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // TODO implements me #PROJECT #TUTO
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by 
    	// framesForCurrentMove
        return null;
    }
    
    /**
     * override of the method setOrientation:
     * Set the Orientation only if the MovableAreaEntity is not moving
     */
    @Override
    protected void setOrientation(Orientation orientation) {
    	if (!isMoving) {
    	super.setOrientation(orientation);
    	}
    }
    
    /**
     * Getter for isMoving
     * @return isMoving (boolean): the boolean that say if the MovableAreaEntity is moving or not 
     */
    protected boolean getIsMoving() {
		return isMoving;
	}

    
    
}
