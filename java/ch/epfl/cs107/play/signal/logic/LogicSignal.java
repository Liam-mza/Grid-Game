package ch.epfl.cs107.play.signal.logic;

public abstract class LogicSignal implements Logic {
	
	@Override
	public final  float getIntensity(float t) {
		
		return getIntensity(); 
	}
	/**
	 * Surchage of getIntensity(float t) with no parameters
	 * getter for the Intensity() 
	 * @return (float):1.0f if on, 0.0f if off  
	 */
	public final float getIntensity() {
		if (isOn()) {
			return 1.0f;
		}
		else {
			return 0.0f;
		}
	}

}
