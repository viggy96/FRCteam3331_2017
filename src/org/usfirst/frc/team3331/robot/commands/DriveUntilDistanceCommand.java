package org.usfirst.frc.team3331.robot.commands;

import org.usfirst.frc.team3331.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveUntilDistanceCommand extends Command {
	
	double distance;
	Ultrasonic.Unit units;
	double speed = 0.6;
	
    public DriveUntilDistanceCommand(double distance, Ultrasonic.Unit units) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	requires(Robot.sensorSubsystem);
    	
    	
    	this.distance = Robot.sensorSubsystem.normaliseRange(distance, units);
    	this.units = units;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensorSubsystem.calibrateGyro();
    	
    	if (units == Ultrasonic.Unit.kInches) speed = (Robot.sensorSubsystem.getRangeInches() < distance) ? speed : -speed;
    	else speed = (Robot.sensorSubsystem.getRangeMM() < distance) ? speed : -speed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Distance: " + Robot.sensorSubsystem.getRangeInches());
    	
    	if (speed < 0) {
    		Robot.driveSubsystem.drive(speed, 
    				-Robot.sensorSubsystem.getGyroAngle() * Robot.driveSubsystem.CURVE_SCALE_FACTOR);
    	} else {
    		Robot.driveSubsystem.drive(speed, 
    				Robot.sensorSubsystem.getGyroAngle() * Robot.driveSubsystem.CURVE_SCALE_FACTOR);
    	}
    			
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (units == Ultrasonic.Unit.kInches) {
    		if (speed < 0) return (Robot.sensorSubsystem.getRangeInches() <= distance + 1.5);
    		else return (Robot.sensorSubsystem.getRangeInches() >= distance);
    	}
    	else {
    		if (speed < 0) return (Robot.sensorSubsystem.getRangeMM() <= distance + 50);
    		else return (Robot.sensorSubsystem.getRangeMM()>= distance);
    	} 
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.stop();
    }
}
