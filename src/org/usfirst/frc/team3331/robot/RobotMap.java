package org.usfirst.frc.team3331.robot;

import com.ctre.CANTalon;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
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
	
	public static final CANTalon winchMotor = new CANTalon(5);
	public static final CANTalon shooterMotor = new CANTalon(6);
	
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static final Joystick gamepad = new Joystick(0);
	public static final int leftStickX = 0; 
	public static final int leftStickY = 1;
	public static final int rightStickX = 4;
	public static final int rightStickY = 5;
	public static final int aButton = 1, bButton = 2, xButton = 3, yButton = 4,
			leftTrigger = 5, rightTrigger = 6;
	
	public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static final Ultrasonic ultrasonic = new Ultrasonic(0);
	
	public static final UsbCamera camera1 = new UsbCamera("cam0", 0);
	
	public static final int image_width = 320, image_height = 240;
	public static final double FOV = 64.4;
	public static final double focal_length = 0.5 * (image_width / Math.tan(FOV / 2)); 
	
	public static final double DISTANCE_FROM_TARGET_INCHES = 52, DISTANCE_FROM_TARGET_MM = 1320.8;
	
	public static void init() {
		drivetrain.setInvertedMotor(MotorType.kFrontLeft, true);
		drivetrain.setInvertedMotor(MotorType.kRearLeft, true);
		drivetrain.setInvertedMotor(MotorType.kFrontRight, true);
		drivetrain.setInvertedMotor(MotorType.kRearRight, true);
		
		//camera1.setFPS(12);
    	//camera1.setResolution(image_width, image_height);
    	CameraServer.getInstance().startAutomaticCapture(camera1);
	}
}
