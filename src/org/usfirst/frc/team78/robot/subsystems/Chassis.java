package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem
	{
	
	Victor leftDrive1 = new Victor(RobotMap.LEFT_DRIVE_1);
	Victor leftDrive2 = new Victor(RobotMap.LEFT_DRIVE_2);
	public Victor rightDrive1 = new Victor(RobotMap.RIGHT_DRIVE_1);
	public Victor rightDrive2 = new Victor(RobotMap.RIGHT_DRIVE_2);
	public Victor hDrive = new Victor(4);
	
	RobotDrive drive = new RobotDrive(leftDrive1, leftDrive2, rightDrive1, rightDrive2);
	
	//sensors
	Gyro gyro = new Gyro(RobotMap.GYRO);
	
	
	//variables
	double error;
	double P = (.0068);
	double I = (.00004);
    double IComponent;
	double D = (0.0);
	double DComponent;
	double lastError = 0;
	public int errorNeutralizedCount = 0;
	final double ERROR_THRESHOLD = 2.5;
	
	double C = (0.01);
	
	double leftDriveValue;
	double rightDriveValue;
	double hDriveValue;
	
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand (new DriveWithJoysticks());
  
    }//end initDefaultCommand
    
    
  //___________________________________________________________________________________________________________________________________________________
    
  //drive methods
    
    
    public void driveWithJoysticks(){
    	double left = -Robot.oi.getDriverLeftStick();
    	double right = -Robot.oi.getDriverRightStick();
    	drive.setSafetyEnabled(false);
    	drive.tankDrive(left, right);
   
    }//end driveWithJoysticks
    
    public void driveStraight(){
    	double power = Robot.oi.getDriverLeftStick();
    	double error = getGyro();
    	drive.tankDrive(power-((C)*error), power+((C)*error));
    	
    }
    
    public void correctSteering(double speed){
    	double error = getGyro();
    	drive.tankDrive(speed-((C)*error), speed+((C)*error));
    }
    
    public void setSpeed(double left, double right){
    	//drive.tankDrive(left, right);
    	leftDrive1.set(left);
    	leftDrive2.set(left);
    	rightDrive1.set(left);
    	rightDrive2.set(left);
    }
    
    public void stopDrive()
    {
    	drive.tankDrive(0, 0);
    }
    
    public void codControl(){
    	//left stick = getx, gety
    	//right stick = get throttle, get twist
    	
    	leftDriveValue = (Robot.oi.driverStick.getY() + -Robot.oi.driverStick.getTwist());
    	rightDriveValue = (Robot.oi.driverStick.getY() - -Robot.oi.driverStick.getTwist());
    	hDriveValue = Robot.oi.driverStick.getX();
    	
    	
    	leftDrive1.set(leftDriveValue);
    	leftDrive2.set(leftDriveValue);
    	rightDrive1.set(-rightDriveValue);
    	rightDrive2.set(-rightDriveValue);
    	hDrive.set(hDriveValue);
    
    }
    
    //___________________________________________________________________________________________________________________________________________________
    
    
    //turn methods
     public void setSpeedLeft(double speed)
    {
    	drive.tankDrive(-speed, speed);
    } 
     
    /*public boolean hasTurnedDegrees(int degrees){
    	if(gyro.getAngle() < degrees){
    	return true;
    	}
    	return false;
    }*/
    
    public void controlLeftTurnSpeed(int target){
    	double speed;
    	error = target-getGyro();
    	speed = (-1)*P*(error);
    	IComponent += (-1)*I*(error);
    	speed += IComponent;
    	DComponent = (-1)*(D*((error-lastError)));
    	speed += DComponent;
    	
    	if (speed > .7){
    		speed = .7;
    	}
    	if(speed < -.7){ 
    		speed = -.7;
    	}
    	
    	if (speed < .30 && speed > 0){
    		speed = .30;
    	}
    	if(speed > -.30 && speed < 0){ 
    		speed = -.30;
    	}
    	
    	setSpeedLeft(speed);
    	//lastError = error;
    	
    	if(Math.abs(error) < ERROR_THRESHOLD){
    		errorNeutralizedCount ++;
    	}
    	else{
    		errorNeutralizedCount = 0;
    	}
    	
    }

    
    
    

//_____________________________________________________________________________________________________________________________________________________
    
     //sensor methods
     
     public double getGyro()
    {
    	return gyro.getAngle();
    }//end get gyro
    
     public void resetGyro (){
    	gyro.reset();
    }//end reset gyro
     
    public void resetTurnData(){
    	gyro.reset();
    	IComponent = 0;
    	DComponent = 0;
    }//end reset turn data
    
   
}

