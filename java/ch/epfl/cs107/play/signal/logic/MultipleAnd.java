package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * MultipleAnd is generalisation of the class And with more than 2 Logics
 *
 */

public class MultipleAnd extends LogicSignal {
	
	// The list of Logic to combine 
	private List<Logic> tabOfAnd;
	
	/**
	 * 
	 * @param multiplesAnd (Ellipse of Logic):The list of Logic to combine 
	 */
	public MultipleAnd(Logic...multiplesAnd) {
		tabOfAnd = new LinkedList<>();
		for( Logic x:multiplesAnd ) {
			tabOfAnd.add(x);
		}
	}
	
	@Override
	public boolean isOn() {
		
		if(tabOfAnd.size()!=0) {
			for( Logic m: tabOfAnd) {
				if (!m.isOn()) {
					return false;
				}	
			}
			return true;
		}
		else {
			return false;
		}
		
		
	}

}
