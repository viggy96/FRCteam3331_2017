package org.usfirst.frc.team3331.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic {
	private AnalogInput analog;
	//0.004883
	private final static double CM_PER_VOLT = 271.122597;
	private final static double MM_PER_INCH = 25.4;
	private final static double CORRECTION = 1.905;
	private double distanceMM, distanceInches;
	
	public Ultrasonic(int channel) {
		analog = new AnalogInput(channel);
	}
	
	public double getRangeMM() {
		distanceMM = (analog.getAverageVoltage() * CM_PER_VOLT) * 10 + CORRECTION;
		distanceInches = distanceMM / MM_PER_INCH;
		
		return distanceMM;
	}
	
	public double getRangeInches() {
		distanceMM = (analog.getAverageVoltage() * CM_PER_VOLT) * 10 + CORRECTION;
		distanceInches = distanceMM / MM_PER_INCH;
		
		return distanceInches;
	}
	
	public double getVoltage() {
		return analog.getAverageVoltage();
	}
}
