package org.usfirst.frc.team3331.robot.subsystems;

import org.usfirst.frc.team3331.robot.RobotMap;
import org.usfirst.frc.team3331.robot.commands.TeleopDriveCommand;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

	public static final int ARRAY_DEPTH = 4;
	
	public static double[] rollingLeftArray;
	public static double[] rollingRightArray;
	
	public static double leftSpeed;
	public static double rightSpeed;

	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public final double CURVE_SCALE_FACTOR = 0.03;
	
	public DriveSubsystem() {
		RobotMap.drivetrain.setSafetyEnabled(false);
		
		// allocate memory for rolling-average arrays
		rollingLeftArray = new double[ARRAY_DEPTH];
		rollingRightArray = new double[ARRAY_DEPTH];
		
		leftSpeed = 0;
		rightSpeed = 0;
		
		// initialize rolling-average arrays
		for (int i = 0; i < ARRAY_DEPTH; i++) {
			rollingLeftArray[i] = 0.0;
			rollingRightArray[i] = 0.0;
		}
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
    	
    	
    	// This one needs shaft encoder feedback from the motors...
		// Team 237's ramp:
		// MOTOR += ( (signed char) (JOYSTICK - MOTOR) / RAMP_CONSTANT);
    	
    	// balance left/right motors since right motor is slower than the left
    	// Ideally, this would be handled by a PIDController class. First, we
    	// need shaft encoders on the left/right motors...
    	rightValue *= 1.10;
    	
    	// This is the previous half-speed setting
    	if (RobotMap.gamepad.getRawButton(RobotMap.leftTrigger)) {
    		leftValue *= 0.5;
    		rightValue *= 0.5;
        // This is a new option which calculates a rolling average value that is applied to the motors
        } else if (RobotMap.gamepad.getRawButton(RobotMap.rightTrigger)) {
        		
        	// array maintenance - shift the array elements upward by one
        	for (int i = 1; i < ARRAY_DEPTH; i++) {
        		rollingLeftArray[i] = rollingLeftArray[i - 1];
        		rollingRightArray[i] = rollingRightArray[i - 1];
          	}
        		
        	// array maintenance - load the new left/right values into the first array element
        	rollingLeftArray[0] = leftValue;
        	rollingRightArray[0] = rightValue;
        		
        	//
        	// calculate the new left/right values based on the rolling average
        	//
        	leftValue = rollingLeftArray[0] / ARRAY_DEPTH;
        	rightValue = rollingRightArray[0] / ARRAY_DEPTH;
        	for (int i = 1; i < ARRAY_DEPTH; i++) {
          	  	leftValue += rollingLeftArray[i] / ARRAY_DEPTH;
          		rightValue += rollingRightArray[i] / ARRAY_DEPTH;
          	}
        // this is the previous full-speed setting
    	} else {
    		// pass left/right values through unchanged.
    		// leftValue = leftValue;
    		// rightValue = rightValue;
    		// leftValue = Math.copySign(Math.pow(leftValue, 2), leftValue);
    		// rightValue = Math.copySign(Math.pow(rightValue,2), rightValue);
        	if (leftValue > 0.1 && leftSpeed < 1.0) {
        		leftSpeed += 0.01;
        	} else if (leftSpeed > 0 && leftValue < 0.1){
        		leftSpeed -= 0.01;
        	} 
        	else if (leftValue < -0.1 && leftSpeed > -1.0) {
        		leftSpeed -= 0.01;
        	} else if (leftSpeed < 0 && leftValue > -0.1){
        		leftSpeed += 0.01;
        	} else if (leftSpeed < 0.1 && leftSpeed > -0.1) leftSpeed = 0;

        	
        	if (rightValue > 0.1 && rightSpeed < 1.0) {
        		rightSpeed += 0.01;
        	} else if (rightSpeed > 0 && rightValue < 0.1){
        		rightSpeed -= 0.01;
        	}
        	else if (rightValue < -0.1 && rightSpeed < 1.0) {
        		rightSpeed -= 0.01;
        	} else if (rightSpeed < 0 && rightValue > -0.1){
        		rightSpeed += 0.01;
        	} else if (rightSpeed < 0.1 && rightSpeed > -0.1) rightSpeed = 0;
        	leftValue = leftSpeed;
        	rightValue = rightSpeed;
    	}
    	// linear acceleration of the left and right motors

    	
    	//RobotMap.drivetrain.tankDrive(leftSpeed, rightSpeed);
    	
    	// Feed the drivetrain the raw input values
    	RobotMap.drivetrain.tankDrive(leftValue, rightValue);
    }
}

