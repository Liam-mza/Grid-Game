package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * 
 * Demo2Behavior is a AreaBehavior for the game Demo2
 *
 */
public class Demo2Behavior extends AreaBehavior {

	/**
	 * 
	 * Demo2CellType is an enumerate class to specify the type of the cells of the Demo2Behavior
	 *
	 */
	public enum Demo2CellType {
		NULL(0),
		WALL(-16777216),
		DOOR(-65536),
		WATER(-16776961),
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
		
		// The type of the cell
		final int type;
		/**
		 * The constructor of Demo2CellType
		 * @param type (int): the int  associate to the type of the cell
		 */
		Demo2CellType(int type){ 
			this.type = type;
		} 
		/**
		 * Give the type associate to the given int
		 * @param type (int): the int  associate to the type of the cell 
		 * @return (Demo2CellType): The type associate to the given int
		 */
		static Demo2CellType toType(int type) {
			
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
	 * Demo2Cell are the cells that we are going to fill Demo2Behavior with
	 *
	 */
	public class Demo2Cell extends Cell{
		
		//The type of the cell
		private Demo2CellType nature;
		
		/**
		 * The constructor of Demo2Behavior
		 * @param the value x of the cells coordinates
		 * @param the value x of the cells coordinates
		 * @param type (Demo2CellType) the type of the cell
		 */
		Demo2Cell(int x, int y, Demo2CellType type){
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
			
			if (nature == Demo2CellType.NULL || nature == Demo2CellType.WALL) {
				return false;
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
		 * @return nature (Demo2CellType): the type of the cell
		 */
		protected Demo2CellType getType() {
			return nature;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// do nothing for the game demo2
			
		}
	}
	//End of the class Demo2Cell

	/**
	 * The constructor of Demo2Behavior
	 * @param window (Window): the window
	 * @param fileName (String): the name of the file 
	 */
	public Demo2Behavior (Window window, String fileName){
		super(window, fileName );
		
		for (int x=0; x<getWidth(); ++x) {
			for (int y=0; y<getHeight(); ++y) {
				Demo2CellType cellType = Demo2CellType.toType(getBehaviorMap().getRGB(height-1-y, x));
				getCells()[x][y] = new Demo2Cell (x,y, cellType);
			}
		}
			
	}
	
	/**
	 * Say if the type of the cell is  door 
	 * @param coor (DiscreteCoordinates): the coordinates of the cell that we want to check if it's a door 
	 * @return (boolean): true if it's a door, false otherwise
	 */
	public boolean isADoor(DiscreteCoordinates coor) {
		
		if ( ((Demo2Cell) getCells()[coor.x][coor.y]).getType() == Demo2CellType.DOOR) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
