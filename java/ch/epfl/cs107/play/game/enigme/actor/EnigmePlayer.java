package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

/**
 * Enigme player is an interactor of our game Enigme 
 */

public class EnigmePlayer extends MovableAreaEntity implements Interactor{
	// Say if the actor is reaching a door
	private boolean isPassingDoor;

	// give the last door passed
	private Door lastDoor;

	// The sprite of the actor	
	private Animation sprite;


	// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;

	//The handler of EnigmePlayer
	private EnigmePlayerHandler handler;

	//Say if player wants view interactions
	private boolean wantViewInteractions;
	
	//Streak is just a counter that we use to manage the Animation
	private int streak;
	
	//Say if the player is sprinting
	private boolean isSprinting;
	
	//Say if the player is dead or loosed
	private boolean gameOver;
	
	//Say if the player is hit by something (Take damage)
	private boolean hit;
	
	//Represent the life of the player 
	private List<Sprite> hp;
	
	//numberOfHeal is just a counter that we use to manage the healing
	private int numberOfHeal;
	
	//Say if the player have collected the cake of level4
	private boolean hasCake; 
	
	//Animation for the bird
	private Animation sprite2;
	
	//Alternative sprite
	private Animation altSprite1;
	private Animation altSprite2;


	
	/**
	 * Constructor of EnigmePlayer
	 * @param area (Area): the Area of the EnigmePlayer
	 * @param orientation (Orientation): the orientation of the EnigmePlayer
	 * @param position (DiscreteCoordinates): the coordinates of the EnigmePlayer
	 */
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		isSprinting= false;
		isPassingDoor = false;
		Vector anchor = new Vector(0.1f, 0.1f);
		altSprite1 = new Animation("max.new.2", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
		Vector anchor3 = new Vector(0.1f, 0.1f);
		altSprite2 = new Animation("max.new.1", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor3);
		Vector anchor2 = new Vector(0.25f, 0.9f);
		sprite2 = new Animation("bird.1", 0.75f, 1.0f, this, new RegionOfInterest(0,0,16,21), anchor2);
		lastDoor = null;
		handler = new EnigmePlayerHandler();
		hp = new LinkedList<>();
		for(int i=0;i<3;++i) {
			hp.add(new Sprite("shield.1", 0.3f, 0.3f, this, new RegionOfInterest(0, 0, 16, 84), new Vector((float)(0.1+0.3*i), 1.3f)));
		}
		gameOver=false;
		hit=false;
		hasCake=false;
		streak=0;
		sprite=altSprite1;
		

	}
	
	/**
	 * Constructor of EnigmePlayer that initialize the Orientation at down
	 * @param area (Area): the Area of the EnigmePlayer
	 * @param orientation (Orientation): the orientation of the EnigmePlayer
	 * @param position (DiscreteCoordinates): the coordinates of the EnigmePlayer
	 */
	public EnigmePlayer(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN , coordinates);
		isPassingDoor = false;
		Vector anchor = new Vector(0.1f, 0.1f);
		altSprite1 = new Animation("max.new.2", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
		Vector anchor3 = new Vector(0.1f, 0.1f);
		altSprite2 = new Animation("max.new.1", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor3);
//		Vector anchor = new Vector(0.1f, 0.1f);
//		sprite = new Animation("max.new.2", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
		Vector anchor2 = new Vector(0.25f, 0.9f);
		sprite2 = new Animation("bird.1", 0.75f, 1.0f, this, new RegionOfInterest(0,0,16,21), anchor2);
//		Vector anchor3 = new Vector(0.1f, 0.1f);
//		altSprite = new Animation("max.new.2", 0.75f, 1.0f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
		lastDoor = null;
		handler = new EnigmePlayerHandler();
		hp = new LinkedList<>();
		for(int i=0;i<3;++i) {
			hp.add(new Sprite("shield.1", 0.3f, 0.3f, this, new RegionOfInterest(0, 0, 16, 84), new Vector((float)(0.1+0.3*i), 1.1f)));
		}
		gameOver=false;
		hit=false;
		hasCake=false;
		streak=0;
		sprite=altSprite1;
	}

	/**
	 * Make the EnigmePlayer enter in the given Area
	 * @param area (Area): the Area of the EnigmePlayer
	 * @param position (DiscreteCoordinates): the coordinates of the EnigmePlayer in the Area
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {

		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		this.setOwnerArea(area);
		this.resetMotion();
		area.setViewCandidate(this);
		isPassingDoor=false;
	}
	
	/**
	 * Make the EnigmePlayer leave the given Area
	 * @param area (Area): the Area of the EnigmePlayer
	 */
	public void leaveArea(Area area) {

		this.getOwnerArea().unregisterActor(this);
	}
	
	/**
	 * Setter for the attribute isPassingDoor
	 * @param door (Door): the door that EnigmePlayer passed 
	 */
	public void setIsPassingDoor (Door door) {
		lastDoor=door;
		isPassingDoor=true;
	}
	
	/**
	 * Getter for the attribute isPassingDoor
	 * @return isPassingDoor (boolean): true if the player is interacting with a door 
	 */
	public boolean getIsPassingDoor() {
		return isPassingDoor;
	}
	
	/**
	 * Getter for the the attribute lastDoor
	 * @return lastDoor (Door): the last Door passed by the EnigmePlayer   
	 */
	public Door passedDoor() {
		return lastDoor;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * Override of the method Update:
	 * take in charge the movement,the interaction, the orientation, the sprint, the damages and the heal
	 */
	@Override
	public void update(float deltaTime) {


		Keyboard keyboard = this.getOwnerArea().getKeyboard();


		Button left = keyboard.get(Keyboard.LEFT);


		Button right = keyboard.get(Keyboard.RIGHT);


		Button up = keyboard.get(Keyboard.UP);


		Button down = keyboard.get(Keyboard.DOWN);

		Button l = keyboard.get(Keyboard.L);

		Button s = keyboard.get(Keyboard.S);

		int animationDuration = ANIMATION_DURATION ;

		if(s.isDown()) {

			isSprinting=true;
			animationDuration /= 2;

		}
		else {
			isSprinting=false;
		}


		if (left.isDown()) {
			if(this.getOrientation() == Orientation.LEFT ) {
				this.move(animationDuration);
			}
			else {
				this.setOrientation(Orientation.LEFT);
			}	
		}

		if (right.isDown()) {
			if (this.getOrientation() == Orientation.RIGHT) {
				this.move(animationDuration);
			}
			else {
				this.setOrientation(Orientation.RIGHT);
			}
		}

		if (up.isDown()) {
			if (this.getOrientation() == Orientation.UP) {
				this.move(animationDuration);
			}
			else {
				this.setOrientation(Orientation.UP);
			}
		}

		if (down.isDown()) {
			if (this.getOrientation() == Orientation.DOWN) {
				this.move(animationDuration);
			}
			else {
				this.setOrientation(Orientation.DOWN);
			}
		}

		if (l.isPressed()){
			wantViewInteractions= true;
		}
		else {
			wantViewInteractions= false;
		}


		super.update(deltaTime);
		
		if(hit) {
			this.isHit();
			hit=false;
		}
		
		if (numberOfHeal!=0) {
			this.isHeal(numberOfHeal);
			numberOfHeal=0;
		}


	}
	
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		for(Sprite x : hp) {
			x.draw(canvas);
		}
		
		if(this.getIsMoving()) {
			if(isSprinting) {
				if(streak <= 7) {
					++streak;
				}
				if(streak > 7) {
					streak = 0;
				}
				switch(streak) {
				case 0 :
				case 1 : 
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 0).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 0).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 0).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 0).draw(canvas);
					}
					break;
				case 2 :
				case 3 :
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 1).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 1).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 1).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 1).draw(canvas);
					}
				case 4 :
				case 5 :
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 2).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 2).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 2).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 2).draw(canvas);
					}
					break;
				case 6 :
				case 7 :
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 3).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 3).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 3).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 3).draw(canvas);
					}
					break;
				}
			}
			else {
				if(streak <= 15) {
					++streak;
				}
				if(streak > 15) {
					streak = 0;
				}

				switch(streak) {
				case 0 :
				case 1 :
				case 2 :
				case 3 : 
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 0).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 0).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 0).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 0).draw(canvas);
					}
					break;
				case 4 :
				case 5 :
				case 6 :
				case 7 :
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 1).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 1).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 1).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 1).draw(canvas);
					}
					break;
				case 8 :
				case 9 :
				case 10 :
				case 11 : 
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 2).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 2).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 2).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 2).draw(canvas);
					}
					break;
				case 12 :
				case 13 :
				case 14 :
				case 15 :
					if(this.getOrientation() == Orientation.LEFT) {
						sprite.getSpriteI(1, 3).draw(canvas);
					}
					if(this.getOrientation() == Orientation.RIGHT) {
						sprite.getSpriteI(3, 3).draw(canvas);
					}
					if(this.getOrientation() == Orientation.UP) {
						sprite.getSpriteI(2, 3).draw(canvas);
					}
					if(this.getOrientation() == Orientation.DOWN) {
						sprite.getSpriteI(0, 3).draw(canvas);
					}
					break;
				}
			}
		}
		else {
			if(this.getOrientation() == Orientation.LEFT) {
				sprite.getSpriteI(1, 0).draw(canvas);
			}
			if(this.getOrientation() == Orientation.RIGHT) {
				sprite.getSpriteI(3, 0).draw(canvas);
			}
			if(this.getOrientation() == Orientation.UP) {
				sprite.getSpriteI(2, 0).draw(canvas);
			}
			if(this.getOrientation() == Orientation.DOWN) {
				sprite.getSpriteI(0, 0).draw(canvas);
			}
		}
		if(gameOver) {
			hasCake=false;
		}
		if(hasCake) {
			if(streak <= 15) {
				++streak;
			}
			if(streak > 15) {
			streak = 0;
			}
			switch(streak) {
			case 0 :
			case 1 :
			case 2 :
			case 3 : 
				if(this.getOrientation() == Orientation.LEFT) {
					sprite2.getSpriteI(1, 0).draw(canvas);
				}
				if(this.getOrientation() == Orientation.RIGHT) {
					sprite2.getSpriteI(3, 0).draw(canvas);
				}
				if(this.getOrientation() == Orientation.UP) {
					sprite2.getSpriteI(2, 0).draw(canvas);
				}
				if(this.getOrientation() == Orientation.DOWN) {
					sprite2.getSpriteI(0, 0).draw(canvas);
				}
				break;
			case 4 :
			case 5 :
			case 6 :
			case 7 :
				if(this.getOrientation() == Orientation.LEFT) {
					sprite2.getSpriteI(1, 1).draw(canvas);
				}
				if(this.getOrientation() == Orientation.RIGHT) {
					sprite2.getSpriteI(3, 1).draw(canvas);
				}
				if(this.getOrientation() == Orientation.UP) {
					sprite2.getSpriteI(2, 1).draw(canvas);
				}
				if(this.getOrientation() == Orientation.DOWN) {
					sprite2.getSpriteI(0, 1).draw(canvas);
				}
				break;
			case 8 :
			case 9 :
			case 10 :
			case 11 : 
				if(this.getOrientation() == Orientation.LEFT) {
					sprite2.getSpriteI(1, 2).draw(canvas);
				}
				if(this.getOrientation() == Orientation.RIGHT) {
					sprite2.getSpriteI(3, 2).draw(canvas);
				}
				if(this.getOrientation() == Orientation.UP) {
					sprite2.getSpriteI(2, 2).draw(canvas);
				}
				if(this.getOrientation() == Orientation.DOWN) {
					sprite2.getSpriteI(0, 2).draw(canvas);
				}
				break;
			case 12 :
			case 13 :
			case 14 :
			case 15 :
				if(this.getOrientation() == Orientation.LEFT) {
					sprite2.getSpriteI(1, 3).draw(canvas);
				}
				if(this.getOrientation() == Orientation.RIGHT) {
					sprite2.getSpriteI(3, 3).draw(canvas);
				}
				if(this.getOrientation() == Orientation.UP) {
					sprite2.getSpriteI(2, 3).draw(canvas);
				}
				if(this.getOrientation() == Orientation.DOWN) {
					sprite2.getSpriteI(0, 3).draw(canvas);
				}
				break;
			}

		}
		
		
	}

	/**
	 * Remove hp and say that the player is dead if it has no more hp 
	 */
	public void isHit() {
		
		if(!hp.isEmpty()) {
			hp.remove(hp.get(hp.size()-1));
			if(hp.isEmpty()){
				
				gameOver=true;
				
				//GAME OVER
			}
		}
		else {
			gameOver=true;
			
			//GAME OVER
		}
	}
	/**
	 * Give back HP to the player
	 * @param v (int): the number of HP we want to give back
	 */
	public void isHeal(int v) {
		
		if(hp.size() == 3) {
			//Do nothing
		}
		else {
			if((hp.size()+v) <= 3) {
				for(int i=0;i<v;++i) {
					hp.add(new Sprite("shield.1", 0.3f, 0.3f, this, new RegionOfInterest(0, 0, 16, 84), new Vector((float)(0.1+0.3*hp.size()), 1.1f)));
				}
			}
			else {
				isHeal(3-hp.size());
			}
		}
	}
	
	/**
	 * Getter for the attribute gameOver
	 * @return gameOver (boolean)
	 */
	public boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * Setter for the attribute gameOver
	 * @param a (boolean): the value that we want to give to gameOver
	 */
	public void setGameOver(boolean a) {
		gameOver=a;
	}



	/**
	 * Specific interaction handler for an EnigmePlayer 
	 */
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {


		@Override
		public void interactWith(Door door) {
			// gère ce qui se passe lorsque le personnage passe les porte
			setIsPassingDoor(door);

		}


		@Override
		public void interactWith(EnigmePlayer player) {

		}

		@Override
		public void interactWith(Collectables coll) {
			coll.setIsCollected(true);

		}

		@Override
		public void interactWith(Switcher swit){
			swit.setCurrentState(!swit.getCurrentState());

		}

		@Override
		public void interactWith(PressureSwitch pres){ 


			pres.setCurrentState(!pres.getCurrentState());

		}
		
		@Override
		public void interactWith(Spikes spikes){ 

			hit=true;

		}
		@Override
		public void interactWith(Potion pot) {
			
			if (!pot.getHasHeal()) {
			numberOfHeal=1;
			pot.setHasHeal(true);
			}
			
		}
		
		@Override
		public void interactWith(Cake cake) {
			hasCake=true;
			(cake.getCurrentArea()).isNeededToBeDraw("oh un oiseau commence a vous suivre");
		}
		public void interactWith(CampFire cf) {
			cf.setAll(!cf.getAll());
			
		}
		public void interactWith(Dress d) {
			
			if (d.getOn()) {
				sprite=altSprite1;
			}
			if(!d.getOn()) {
				sprite=altSprite2;
			}
			
			d.setOn(!d.getOn());
			
		}



	}
//Fin du Handler de EnigmePlayer


	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);

	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		// TODO Auto-generated method stub
		List<DiscreteCoordinates> fieldOfViewCells = new LinkedList<>();
		List<DiscreteCoordinates> cells = new LinkedList<>();
		cells.addAll(getCurrentCells());

		for (DiscreteCoordinates coor : cells) {

			Vector direction = coor.toVector().add(this.getOrientation().toVector()); 
			fieldOfViewCells.add(new DiscreteCoordinates((int)direction.x, (int)direction.y));
		}

		return fieldOfViewCells;
	}

	@Override
	public boolean wantsCellInteraction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		// TODO Auto-generated method stub

		return wantViewInteractions;
	}

	@Override
	public void interactWith(Interactable other) {
		// TODO Auto-generated method stub
		other.acceptInteraction(handler);
	}
	
	public void setSprite(int i) {
		if (i==1) {
			sprite=altSprite1;
		}
		if (i==2) {
			sprite=altSprite2;
		}
	}



}
