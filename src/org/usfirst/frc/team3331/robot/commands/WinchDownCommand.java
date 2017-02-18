package org.usfirst.frc.team3331.robot.commands;

import org.usfirst.frc.team3331.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchDownCommand extends Command {

    public WinchDownCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winchSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winchSubsystem.winchStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.winchSubsystem.winchDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winchSubsystem.winchStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winchSubsystem.winchStop();
    }
}
