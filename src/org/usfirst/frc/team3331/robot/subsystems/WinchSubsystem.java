package org.usfirst.frc.team3331.robot.subsystems;

import org.usfirst.frc.team3331.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void winchStop() {
    	RobotMap.winchMotor.set(0);
    }
    
    public void winchUp() {
    	RobotMap.winchMotor.set(-1.0);
    }
    
    public void winchDown() {
    	RobotMap.winchMotor.set(1.0);
    }
}

