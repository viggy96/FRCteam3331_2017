package org.usfirst.frc.team3331.robot.subsystems;

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
	
	public DriveSubsystem() {
		RobotMap.drivetrain.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TeleopDriveCommand());
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
    	RobotMap.drivetrain.tankDrive(RobotMap.gamepad.getRawAxis(RobotMap.leftStickY), 
    			RobotMap.gamepad.getRawAxis(RobotMap.rightStickY));
    }
}

