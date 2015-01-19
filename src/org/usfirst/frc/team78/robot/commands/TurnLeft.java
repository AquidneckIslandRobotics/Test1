package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnLeft extends Command {
	
	int m_target;
	final int ERROR_COUNT_THRESHOLD = 2;

    public TurnLeft(int target) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    	m_target = target;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetTurnData();
    	Robot.chassis.setSpeedLeft(.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.controlLeftTurnSpeed(m_target);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.chassis.errorNeutralizedCount > ERROR_COUNT_THRESHOLD;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopDrive();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
