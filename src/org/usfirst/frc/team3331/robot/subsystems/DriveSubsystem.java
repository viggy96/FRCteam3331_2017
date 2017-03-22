package org.usfirst.frc.team3331.robot.subsystems;

import org.usfirst.frc.team3331.robot.Robot;
import org.usfirst.frc.team3331.robot.RobotMap;
import org.usfirst.frc.team3331.robot.commands.TeleopDriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public final double CURVE_SCALE_FACTOR = 0.03;
	private final double EPSILON = 0.03;
	private final double MOTOR_RAMP_RATE = 0.2;
	private final double ZER0_EPSILON = 0.03;
	private final double SLOW_SPEED = 0.6;
	private static double oldLeftValue = 0, oldRightValue = 0;
	
	public DriveSubsystem() {
		RobotMap.drivetrain.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopDriveCommand());
    }
    
    public void init() {
    	RobotMap.drivetrain.setLeftRightMotorOutputs(0, 0);
    }
    
    public void stop() {
    	RobotMap.drivetrain.stopMotor();
    }
    
    public void drive(double magnitude, double curve) {
    	RobotMap.drivetrain.drive(magnitude, curve);
    }
    
    public void tankDrive(double leftValue, double rightValue) {
    	RobotMap.drivetrain.tankDrive(leftValue, rightValue);
    }
    
    public void teleopDrive() {
    	double leftValue = RobotMap.gamepad.getRawAxis(RobotMap.leftStickY);
    	double rightValue = RobotMap.gamepad.getRawAxis(RobotMap.rightStickY);
    	double speed = (leftValue + rightValue) / 2;
    	
    	if (RobotMap.gamepad.getRawButton(RobotMap.leftTrigger)) {
    		leftValue *= SLOW_SPEED;
    		rightValue *= SLOW_SPEED;
    	} else {
    		
    		// Ramping left value to avoid unintended acceleration
    		if (leftValue > oldLeftValue) leftValue = oldLeftValue + MOTOR_RAMP_RATE;
    		else if (leftValue < oldLeftValue) leftValue = oldLeftValue - MOTOR_RAMP_RATE;
    		
    		// Ramping right value to avoid unintended acceleration
    		if (rightValue > oldRightValue) rightValue = oldRightValue + MOTOR_RAMP_RATE;
    		else if (rightValue < oldRightValue) rightValue = oldRightValue - MOTOR_RAMP_RATE;
    		
    		// Stopping the drive, when joysticks are within ZERO_EPSILON of 0
    		if (Math.abs(leftValue) < ZER0_EPSILON && Math.abs(rightValue) < ZER0_EPSILON) 
    			RobotMap.drivetrain.drive(0, 0);
    		
    		// Detecting when to use gyro to help drive straight
    		if (Math.abs(leftValue - rightValue) <= EPSILON && speed > (SLOW_SPEED + 0.1)) {
    			RobotMap.gyro.reset();
    			
    			// Gyro correction for going forward, in order to drive straight
    			if (speed > 0) {
    				RobotMap.drivetrain.drive(speed, 
    						-Robot.sensorSubsystem.getGyroAngle() * Robot.driveSubsystem.CURVE_SCALE_FACTOR);
    			} 
    			// Gyro correction for going backwards in order to drive straight
    			else { 
    				RobotMap.drivetrain.drive(speed, 
    						Robot.sensorSubsystem.getGyroAngle() * Robot.driveSubsystem.CURVE_SCALE_FACTOR);
    			}
    		} else {
    			RobotMap.drivetrain.tankDrive(leftValue, rightValue);
    		}
    	}
    	
    	oldLeftValue = leftValue;
    	oldRightValue = rightValue;
    }
}

