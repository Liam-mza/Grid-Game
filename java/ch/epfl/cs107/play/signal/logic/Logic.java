package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal{
	
	// Say if the signal is on or off
	boolean isOn();
	
	/**
	 * Surchage of getIntensity(float t) with no parameters
	 * getter for the Intensity() 
	 * @return (float):1.0f if on, 0.0f if off  
	 */
	default float getIntensity() {
		if (isOn()) {
			return 1.0f;
		}
		else {
			return 0.0f;
		}
	}
	
	
	@Override
	default float getIntensity(float t) {
		
		return getIntensity(); 
	}
	
	Logic TRUE = new Logic() { 
		
		@Override
		public boolean isOn() { 
		return true;
		}
 
	};
	
	Logic FALSE = new Logic() { 
		@Override
		public boolean isOn() { 
			return false;
		} 
	};

}
