package org.usfirst.frc.team3331.robot.subsystems;

import org.usfirst.frc.team3331.robot.Robot;
import org.usfirst.frc.team3331.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SensorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public final double MIN_RANGE_INCHES = 7.9;
	public final double MIN_RANGE_MM = 0.2;

	public SensorSubsystem() {
		RobotMap.gyro.calibrate();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void calibrateGyro() {
    	RobotMap.gyro.reset();
    }
    
    public double getGyroAngle() {
    	return RobotMap.gyro.getAngle();
    }
    
    public double getGyroRate() {
    	return RobotMap.gyro.getRate();
    }
    
    public double normaliseRange(double distance, Ultrasonic.Unit units) {
    	if (units == Ultrasonic.Unit.kInches && distance < Robot.sensorSubsystem.MIN_RANGE_INCHES) 
    		return Robot.sensorSubsystem.MIN_RANGE_INCHES;
    	else if (units == Ultrasonic.Unit.kMillimeters && distance < Robot.sensorSubsystem.MIN_RANGE_MM) 
    		return Robot.sensorSubsystem.MIN_RANGE_MM;
    	else return distance;
    }
    
    public double getRangeMM() {
    	return RobotMap.ultrasonic.getRangeMM();
    }
    
    public double getRangeInches() {
    	return RobotMap.ultrasonic.getRangeInches();
    }

	public double getVoltage() {
		return RobotMap.ultrasonic.getVoltage();
	}
    
}

