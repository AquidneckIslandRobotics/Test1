package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleSol2 extends Command {

    public ToggleSol2() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.pneumatics.sol2Forward){
    		Robot.pneumatics.sol_2.set(DoubleSolenoid.Value.kReverse);
    		Robot.pneumatics.sol2Forward = false;
    	}
    	else{
    		Robot.pneumatics.sol_2.set(DoubleSolenoid.Value.kForward);
        	Robot.pneumatics.sol2Forward = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
