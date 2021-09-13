package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

/**
 * LogicNumber interprets many signal as a number which is a power of 2
 */
public class LogicNumber extends LogicSignal{

	// The list of signal
	private List<Logic> signalList;
	
	//The number we want 
	private float nb;


	/** Constructor of LogicNumber
	 * @param nb (float): the number we want that the interpreted number is equal 
	 * @param signal (Ellipse of Signal):The list of signal
	 */
	public LogicNumber(float nb, Logic...signals) {
		this.nb = nb;
		signalList = new LinkedList<>();
		for( Logic x: signals) {
		signalList.add(x); //Choix de référencer la même liste car on s'intéresse seulement à analyser les éléments
		}					  //de la liste et non à les modifier
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		double signalNumber =0;
		if((signalList.size() > 12) || (nb > Math.pow(2, signalList.size())) || (nb < 0)) {
			return false;
		}
		else {
			for(int i=0;i<signalList.size();++i) {
				signalNumber += Math.pow(2, i) * signalList.get(i).getIntensity();
			}
			if(signalNumber == nb) {
				return true;
			}
			else {
				return false;
			}
		}
	}


}
