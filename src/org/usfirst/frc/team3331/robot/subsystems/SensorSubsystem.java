package org.usfirst.frc.team3331.robot.subsystems;

import org.usfirst.frc.team3331.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SensorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public final double MIN_RANGE_INCHES = 7.9;
	public final double MIN_RANGE_MM = 0.2;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void calibrateGyro() {
    	RobotMap.gyro.calibrate();
    }
    
    public double getGyroAngle() {
    	return RobotMap.gyro.getAngle();
    }
    
    public double getRangeMM() {
    	return RobotMap.ultrasonic.getRangeMM();
    }
    
    public double getRangeInches() {
    	return RobotMap.ultrasonic.getRangeInches();
    }
}

