package org.usfirst.frc.team78.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public boolean sol1Forward  = false;
	public boolean sol2Forward  = false;
	public boolean sol3Forward  = false;
	
	
public Compressor compressor = new Compressor();
	
	public DoubleSolenoid sol_1 = new DoubleSolenoid(0, 1);
	public DoubleSolenoid sol_2 = new DoubleSolenoid(2, 3);
	public DoubleSolenoid sol_3 = new DoubleSolenoid(4, 5);

    public void initDefaultCommand() {
        
    }
}

