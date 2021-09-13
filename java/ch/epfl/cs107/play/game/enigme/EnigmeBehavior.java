package ch.epfl.cs107.play.game.enigme;

import java.util.Set;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;

import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
* 
* EnigmeBehavior is a AreaBehavior for the game Enigme
*
*/
public class EnigmeBehavior extends AreaBehavior {

	/**
	 * 
	 * EnigmeCellType is an enumerate class to specify the type of the cells of the EnigmeBehavior
	 *
	 */
	public enum EnigmeCellType {
		NULL(0),
		WALL(-16777216),
		DOOR(-65536),
		WATER(-16776961),
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
		
		// The type of the cell
		final int type;
		/**
		 * The constructor of EnigmeCellType
		 * @param type (int): the int  associate to the type of the cell
		 */
		EnigmeCellType (int type){ 
			this.type = type;
		} 
		/**
		 * Give the type associate to the given int
		 * @param type (int): the int associate to the type of the cell 
		 * @return (EnigmeCellType): The type associate to the given int
		 */
		static EnigmeCellType toType(int type) {
			
			switch (type){
			case 0:
				return NULL;
			case -16777216:
				return WALL;
			
			
			case -65536:
				return DOOR;
			
			
			case -16776961:
				return WATER;
			
			
			case -1:
				return INDOOR_WALKABLE;
	
			
			case -14112955:
				return OUTDOOR_WALKABLE;
		
			
			default: 
				return NULL;
	
				
			}
		}
		
	}
	/**
	 * 
	 * EnigmeCell are the cells that we are going to fill EnigmeBehavior with
	 *
	 */
	public class EnigmeCell extends Cell{
		
		// the type of the cell
		private EnigmeCellType nature;
		
		/**
		 * The constructor of EnigmeCell
		 * @param the value x of the cells coordinates
		 * @param the value x of the cells coordinates
		 * @param type (EnigmeCellType) the type of the cell
		 */
		EnigmeCell(int x, int y, EnigmeCellType type){
			super(x,y);
			nature = type;
		}

		@Override
		public boolean takeCellSpace() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isViewInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isCellInteractable() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			// TODO Auto-generated method stub
			
			if (nature == EnigmeCellType.NULL || nature == EnigmeCellType.WALL || nature == EnigmeCellType.WATER) {
				return false;
			}
			for (Interactable inter:  this.getinteractables()) {
				if (inter.takeCellSpace()) {
					return false;
				}
			}
    		
			return true;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			// TODO Auto-generated method stub
			return true;
		}
		
		/**
		 * Getter for the type of the cell
		 * @return nature (EnigmeCellType): the type of the cell
		 */
		protected EnigmeCellType getType() {
			return nature;
		}
		
		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			((EnigmeInteractionVisitor)v).interactWith(this);
		}
	}
	//End of the class cell
	
	/**
	 * The constructor of EnigmeBehavior
	 * @param window (Window): the window
	 * @param fileName (String): the name of the file 
	 */
	public EnigmeBehavior (Window window, String fileName){
		super(window, fileName );
		
		for (int x=0; x<getWidth(); ++x) {
			for (int y=0; y<getHeight(); ++y) {
				EnigmeCellType cellType = EnigmeCellType.toType(getBehaviorMap().getRGB(height-1-y, x));
				getCells()[x][y] = new EnigmeCell (x,y, cellType);
			}
		}
			
	}
	
}
