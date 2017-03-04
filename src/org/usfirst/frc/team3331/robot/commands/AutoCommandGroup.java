package org.usfirst.frc.team3331.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommandGroup extends CommandGroup {
	
	public enum Direction {
		RIGHT, LEFT;
	}

    public AutoCommandGroup(Direction direction) {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commds at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new DriveUntilDistanceCommand(12.0, Ultrasonic.Unit.kInches));
    	addSequential(new WaitCommand(5.0));
    	addSequential(new DriveUntilDistanceCommand(24.0, Ultrasonic.Unit.kInches));
    	if (direction == Direction.LEFT) {
    		addSequential(new Turn90LeftCommand());
    		addSequential(new DriveUntilDistanceCommand(24.0, Ultrasonic.Unit.kInches));
    		addSequential(new Turn90RightCommand());
    		addSequential(new DriveUntilDistanceCommand(12.0, Ultrasonic.Unit.kInches));
    	} else if (direction == Direction.RIGHT) {
    		addSequential(new Turn90RightCommand());
    		addSequential(new DriveUntilDistanceCommand(24.0, Ultrasonic.Unit.kInches));
    		addSequential(new Turn90LeftCommand());
    		addSequential(new DriveUntilDistanceCommand(12.0, Ultrasonic.Unit.kInches));
    	}
    }
}
