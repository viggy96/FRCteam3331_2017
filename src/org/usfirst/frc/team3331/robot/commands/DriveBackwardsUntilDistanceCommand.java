package org.usfirst.frc.team3331.robot.commands;

import org.usfirst.frc.team3331.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveBackwardsUntilDistanceCommand extends Command {
	
	double distance;
	Ultrasonic.Unit units;

    public DriveBackwardsUntilDistanceCommand(double distance, Ultrasonic.Unit units) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.sensorSubsystem);
    	
    	if (units == Ultrasonic.Unit.kInches && distance < Robot.sensorSubsystem.MIN_RANGE_INCHES) 
    		this.distance = Robot.sensorSubsystem.MIN_RANGE_INCHES;
    	else if (units == Ultrasonic.Unit.kMillimeters && distance < Robot.sensorSubsystem.MIN_RANGE_MM) 
    		this.distance = Robot.sensorSubsystem.MIN_RANGE_MM;
    	else this.distance = distance;
    	this.units = units;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensorSubsystem.calibrateGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.drive(-1.0, Robot.sensorSubsystem.getGyroAngle() * Robot.driveSubsystem.CURVE_SCALE_FACTOR);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (units == Ultrasonic.Unit.kInches) return (Robot.sensorSubsystem.getRangeInches() <= distance);
    	else if (units == Ultrasonic.Unit.kMillimeters) return (Robot.sensorSubsystem.getRangeMM() <= distance);
    	else return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
