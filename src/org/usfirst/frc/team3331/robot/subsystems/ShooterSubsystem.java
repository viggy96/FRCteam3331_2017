package org.usfirst.frc.team3331.robot.subsystems;

import org.usfirst.frc.team3331.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stop() {
    	RobotMap.shooterMotor.set(0);
    }
    
    public void startShooterMotor() {
    	RobotMap.shooterMotor.set(0.95);
    }
    
    public void reverseShooterMotor() {
    	RobotMap.shooterMotor.set(-1.0);
    }
}

