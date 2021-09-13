package ch.epfl.cs107.play.signal.logic;


/**
 * 
 * Or take to signal and compute a signal that is true if a least one of the given signal is true
 *
 */
public class Or extends LogicSignal{

	//The two Logics to combine
	private Logic s1;
	private Logic s2;
	
	/** Constructor for or
	 * 
	 * @param s1 (Logic): the first Logic 
	 * @param s2 (Logic): the second Logic 
	 * 
	 */
	public Or(Logic s1, Logic s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		if(s1 != null && s2 != null){ 
				
			if (!s1.isOn() && !s2.isOn()) {
			return false;
			}
		
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

}


