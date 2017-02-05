package org.usfirst.frc.team3331.robot.subsystems;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.usfirst.frc.team3331.robot.RobotMap;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionSubsystem extends Subsystem {
	
	Thread visionServer;
	static double angleToTarget = 0;
	
	public final static double EPSILON = 0.0625; // = 1/16

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public VisionSubsystem() {
    	RobotMap.camera.setFPS(10);
    	RobotMap.camera.setResolution(RobotMap.image_width, RobotMap.image_height);
    	CameraServer.getInstance().startAutomaticCapture(RobotMap.camera);
    	/*
    	visionServer = new Thread(new Runnable() {
    		DatagramSocket serverSocket;
    		byte[] receiveData = new byte[1024];
    		JSONParser parser;
    		JSONObject json;
    		
			@Override
			public void run() {
				try {
					serverSocket = new DatagramSocket(3331);
					while (true) {
						DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		                try {
							serverSocket.receive(receivePacket);
						} catch (IOException e) {
							System.out.println("Could not receive packet");
							e.printStackTrace();
						}
		                
		                json = (JSONObject) parser.parse(new String(receivePacket.getData()));
		                
		                angleToTarget = 
		                		Math.atan((Double.parseDouble(json.get("x").toString()) - (RobotMap.image_width/2)) / RobotMap.focal_length);
					}
				} catch (SocketException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					System.out.println("Unable to parse JSON.");
					e.printStackTrace();
				}
			}
    		
    	});
    	
    	visionServer.start();
    	*/
    }
    
    public double getAngleToTarget() {
    	return angleToTarget;
    }
}

