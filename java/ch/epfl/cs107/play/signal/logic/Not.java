package ch.epfl.cs107.play.signal.logic;

/**
 * 
 * Or compute the negation of the given signal
 *
 */
public class Not extends LogicSignal {

	//the Logic to negate
	private Logic s;
	
	/** Constructor for Not  
	 * 
	 * @param s (Logic): the Logic to negate
	 */

	public Not(Logic signal) {
		s=signal;
	}
	
	
	@Override
	public boolean isOn() {
		
		
		if (s != null)	{
			if (!s.isOn()) {
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

}
