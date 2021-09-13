package ch.epfl.cs107.play.signal.logic;

/**
 * 
 * And combines two Logic in a signal that is true if both Logic are true, false otherwise
 *
 */
public class And extends LogicSignal{

	// The Logic that need to be combined
	private Logic s1;
	private Logic s2;
	
	/** Constructor for And
	 * 
	 * @param sig1 (Logic): the first signal 
	 * @param sig2 (Logic): the second signal 
	 * 
	 */

	public And(Logic sig1, Logic sig2) {
		s1=sig1;
		s2=sig2;
		
	}
	
	/**
	 * Computes the logical operator And
	 * @return (boolean) : true if both signals are activated, and false otherwise
	 */

	@Override
	public boolean isOn() {
	// TODO Auto-generated method stub
		
		if (s1!=null && s2!=null) {
			
			if(s1.isOn() && s2.isOn()) {
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
