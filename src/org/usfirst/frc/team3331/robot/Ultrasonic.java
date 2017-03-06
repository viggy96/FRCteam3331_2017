package org.usfirst.frc.team3331.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic {
	private AnalogInput analog;
	private final static double CM_PER_VOLT = 271.122597;
	private final static double MM_PER_INCH = 25.4;
	private final static double CORRECTION = 2.222;
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
