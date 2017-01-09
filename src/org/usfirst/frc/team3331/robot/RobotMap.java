package org.usfirst.frc.team3331.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	private static final CANTalon frontLeftMotor = new CANTalon(1);
	private static final CANTalon rearLeftMotor = new CANTalon(2);
	private static final CANTalon frontRightMotor = new CANTalon(3);
	private static final CANTalon rearRightMotor = new CANTalon(4);
	
	public static final RobotDrive drivetrain = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	
	public static final Joystick gamepad = new Joystick(0);
	public static final int leftStickX = 0; 
	public static final int leftStickY = 1;
	public static final int rightStickX = 4;
	public static final int rightStickY = 5;
	
	public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static final Ultrasonic ultrasonic = new Ultrasonic(0, 1);
}
