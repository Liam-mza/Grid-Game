package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Cake;
import ch.epfl.cs107.play.game.enigme.actor.CampFire;
import ch.epfl.cs107.play.game.enigme.actor.Collectables;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Dress;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Spikes;
import ch.epfl.cs107.play.game.enigme.actor.Switcher;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor{
	/**
	* Simulate and interaction between a enigme Interactors
	and an enigme Apple
	* @param apple (Apple), not null 
	*/
	default void interactWith(Apple apple){
		// by default the interaction is empty
	}

	/**
	* Simulate and interaction between a enigme Interactors
	and an enigme Cell
	* @param cell (EnigmeCell), not null 
	*/
	default void interactWith(EnigmeBehavior.EnigmeCell cell){ 
		// by default the interaction is empty
	}
	
	/**
	* Simulate and interaction between a enigme Interactors
	and an enigme Door
	* @param door (Door), not null 
	*/
	default void interactWith(Door door){ 
		// by default the interaction is empty
	}
	
	
	default void interactWith(EnigmePlayer player) {
		
	}
	/**
	 * Simulate and interaction between a enigme Interactors
	and a key
	 * @param key (Key), not null
	 */
	default void interactWith(Key key){ 
		// by default the interaction is empty
	}
	/**
	 * Simulate and interaction between a enigme Interactors
	and a Collectables
	 * @param coll (Collectables), not null
	 */
	default void interactWith(Collectables coll) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate and interaction between a enigme Interactors
	and a Switcher
	 * @param swit (Switcher), not null
	 */
	default void interactWith(Switcher swit){ 
		// by default the interaction is empty
	}
	
	/**
	 * Simulate and interaction between a enigme Interactors
	and a PressureSwitch 
	 * @param pres (PressureSwitch), not null
	 */
	default void interactWith(PressureSwitch pres){ 
		// by default the interaction is empty
	}
	
	/**
	 * Simulate and interaction between a enigme Interactors
	and a SignalRock
	 * @param rock (SignalRock), not null
	 */
	default void interactWith(SignalRock rock) {
		// by default the interaction is empty
	}
	
	/**
	 * Simulate and interaction between a enigme Interactors
	and a Spikes
	 * @param rock (Spikes), not null
	 */
	default void interactWith(Spikes spick) {
		
	}
	/**
	 * Simulate and interaction between a enigme Interactors
	and a potion
	 * @param pot (Potion), not null
	 */
	default void interactWith(Potion pot) {
		
	}
	/**
	 * Simulate and interaction between a enigme Interactors
	and a cake
	 * @param cake (Cake), not null
	 */
	default void interactWith(Cake cake) {
		
	}
	
	default void interactWith(CampFire cf) {
		
		
	}
default void interactWith(Dress d) {
		
		
	}

}
