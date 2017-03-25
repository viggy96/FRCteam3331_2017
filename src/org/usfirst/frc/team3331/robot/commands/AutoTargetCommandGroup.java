package org.usfirst.frc.team3331.robot.commands;

import org.usfirst.frc.team3331.robot.Robot;
import org.usfirst.frc.team3331.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTargetCommandGroup extends CommandGroup {

    public AutoTargetCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new AlignWithTargetCommand());
    	// Hey, Viggy, I forced a value of 0.5 for speed...
    	addSequential(new DriveUntilDistanceCommand(RobotMap.DISTANCE_FROM_TARGET_INCHES, 0.5, Ultrasonic.Unit.kInches));
    }
}
