package org.usfirst.frc.team3331.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic {
	private AnalogInput analog;
	private final static double VOLTS_PER_CM = 0.004883;
	private final static double MM_PER_INCH = 25.4;
	private double distanceMM, distanceInches;
	
	public Ultrasonic(int channel) {
		analog = new AnalogInput(channel);
	}
	
	public double getRangeMM() {
		distanceMM = (analog.getAverageVoltage() / VOLTS_PER_CM) * 10;
		distanceInches = distanceMM * MM_PER_INCH;
		
		return distanceMM;
	}
	
	public double getRangeInches() {
		distanceMM = (analog.getAverageVoltage() / VOLTS_PER_CM) * 10;
		distanceInches = distanceMM * MM_PER_INCH;
		
		return distanceInches;
	}
}
