package org.usfirst.frc.team6544.robot.commands;

import org.usfirst.frc.team6544.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShootOne extends InstantCommand {
	
    public ShootOne() {
        super();
       
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooterOne);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.shooterOne.shoot(1.0);
    }

}
