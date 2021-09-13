package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {

    // TODO implements me #PROJECT #TUTO
	/// an AreaEntity knows its own Area
	private Area ownerArea;
	/// Orientation of the AreaEntity in the Area
	private Orientation orientation;
	/// Coordinate of the main Cell linked to the entity 
	private DiscreteCoordinates currentMainCellCoordinates;

    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {

        super(position.toVector());
        ownerArea = area;
        this.orientation = orientation;
        currentMainCellCoordinates = position;
        
    }
    /**
     * Override of setCurrentPosition of Entity
     */
    @Override
   protected void setCurrentPosition(Vector v) {
	   if ( DiscreteCoordinates.isCoordinates(v) ){
   		super.setCurrentPosition(v.round());
   		currentMainCellCoordinates = new DiscreteCoordinates ((int)this.getPosition().getX(), (int)this.getPosition().getY()) ;
	   }
	   else { 
		   super.setCurrentPosition(v); 
		   }
   }

    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return currentMainCellCoordinates (DiscreteCoordinates): the current main cell coordinates
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){
        // TODO implements me #PROJECT #TUTO
        return currentMainCellCoordinates;
    }
    
    /**
     * Getter for the orientation of the AreaEntity
     * @return (Orientation)
     */
    protected Orientation getOrientation() {
    	return orientation;
    }
    
    /**
     * Setter for the orientation of the AreaEntity
     */
    protected void setOrientation(Orientation orientation) {
    	this.orientation= orientation;
    }
    
    /**
     * Getter for ownerArea of the AreaEntity
     * @return ownerArea (Area): the area of the AreaEntity
     */
    protected Area getOwnerArea (){
    	return ownerArea;
    }
    protected void setOwnerArea(Area a) {
		ownerArea = a;
	}
    
}
