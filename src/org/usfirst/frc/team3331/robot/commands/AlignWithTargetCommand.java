package org.usfirst.frc.team3331.robot.commands;

import org.usfirst.frc.team3331.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AlignWithTargetCommand extends Command {

	double angleToTarget;
	
    public AlignWithTargetCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.sensorSubsystem);
    	requires(Robot.visionSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensorSubsystem.calibrateGyro();
    	angleToTarget = Robot.visionSubsystem.getAngleToTarget();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.drive(0, Math.copySign(0.5, angleToTarget));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Math.abs(Robot.sensorSubsystem.getGyroAngle()) - Math.abs(angleToTarget)) <= Robot.visionSubsystem.EPSILON;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
