package org.usfirst.frc.team3331.robot.commands;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommandGroup extends CommandGroup {
	
	public enum Station {
		ONE, TWO, THREE;
	}
	
	public enum Direction {
		RIGHT, LEFT, NULL;
	}

    public AutoCommandGroup(Station station, Direction direction) {
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
    	
    	// *** AUTONOMOUS STRATEGY ***
		//
		//
		//                 \                /
		//                1*\   AIRSHIP    /*3
		//                   \            /   
		//___base line________////////////_______base line_________
		//                         *
		//                         2
		//
		//////////////////// Driver Station ///////////////////////
		//
    	// For positions 1 & 3, the robot drives and then just stops. The robot will 
    	// have placed the gear on the peg AND crossed the base line. There is no 
    	// need to do anything else. For positions 1 & 3, the robot must be placed at the 
    	// extreme edge of the driver station diamond plate with one corner touching
    	// the diamond plate. As accurately as possible, the robot must be placed to
    	// face the peg at the appropriate airship position. It is not possible to place
    	// the robot so that it faces the pegs at positions 1 & 3 perfectly. The robot
    	// must be placed as well as you can.
    	
    	// For position 2, the robot will drive and stop at the peg. The airship
    	// operator must lift the gear out of the gear chute (within 5 seconds of
    	// stopping). The robot then reverses two feet, turns right or left 90 degrees
    	// (depending on chooser selection), drives toward the side wall and stops,
    	// turns 90 degrees and then drives over the baseline.
    	// The choice of turning right or left after placing the gear should be made
    	// after consulting the other two alliance teams. It may be advantageous to choose
    	// one direction over the other.
    	addSequential(new DriveUntilDistanceCommand(12.0, Ultrasonic.Unit.kInches));
    	if (station == Station.TWO) {
        	addSequential(new WaitCommand(5.0));
        	addSequential(new DriveUntilDistanceCommand(24.0, Ultrasonic.Unit.kInches));
    		if (direction == Direction.LEFT) {
    			addSequential(new Turn90LeftCommand());
    			addSequential(new DriveUntilDistanceCommand(24.0, Ultrasonic.Unit.kInches));
    			addSequential(new Turn90RightCommand());
        		addSequential(new AutoDriveForwardCommand(5.0)); // stop after five seconds instead of crossing field
    		} else if (direction == Direction.RIGHT) {
    			addSequential(new Turn90RightCommand());
    			addSequential(new DriveUntilDistanceCommand(24.0, Ultrasonic.Unit.kInches));
    			addSequential(new Turn90LeftCommand());
        		addSequential(new AutoDriveForwardCommand(5.0)); // stop after five seconds instead of crossing field
    		}
    	}
    }
}
