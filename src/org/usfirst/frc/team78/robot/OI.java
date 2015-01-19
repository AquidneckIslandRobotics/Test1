package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.CODControl;
import org.usfirst.frc.team78.robot.commands.DriveStraightCorrection;
import org.usfirst.frc.team78.robot.commands.DriveStraightWithJoysticks;
import org.usfirst.frc.team78.robot.commands.ToggleSol1;
import org.usfirst.frc.team78.robot.commands.ToggleSol2;
import org.usfirst.frc.team78.robot.commands.ToggleSol3;
import org.usfirst.frc.team78.robot.commands.TurnLeft;
import org.usfirst.frc.team78.robot.commands.stupidIdea;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */


 

public class OI {
	
	public JoystickButton turnLeftButton;
	public JoystickButton DriveStraightCorrectionBtn;
	public Joystick driverStick;
	public Button straight;
	public Button driveForever;
	public Button stupidButton;
	public Button codButton;
	
	
	public Joystick manipulatorStick;
	public Button toggleSol1;
	public Button toggleSol2;
	public Button toggleSol3;
	
		
    
public OI(){
		driverStick = new Joystick(1);
		
		turnLeftButton = new JoystickButton(driverStick, 1);
		turnLeftButton.whenPressed(new TurnLeft(-90));
		
		straight = new JoystickButton(driverStick, 2);
		straight.whileHeld(new DriveStraightWithJoysticks());
		
		DriveStraightCorrectionBtn = new JoystickButton(driverStick, 4);
		DriveStraightCorrectionBtn.whenPressed(new DriveStraightCorrection());	
		
		stupidButton = new JoystickButton(driverStick, 5);
		stupidButton.whenPressed(new stupidIdea());
		
		manipulatorStick = new Joystick(2);
		toggleSol1 = new JoystickButton(manipulatorStick, 1);
		toggleSol1.whenPressed(new ToggleSol1());
		
		toggleSol2 = new JoystickButton(manipulatorStick, 2);
		toggleSol2.whenPressed(new ToggleSol2());
		
		toggleSol3 = new JoystickButton(manipulatorStick, 3);
		toggleSol3.whenPressed(new ToggleSol3());
		
		codButton = new JoystickButton(driverStick, 8);
		codButton.whileHeld(new CODControl());
		
		
	}
	

	
	public double getDriverLeftStick(){
		return driverStick.getY();
	}
	public double getDriverRightStick(){
		return driverStick.getThrottle();
	}
	
	
}

