package org.usfirst.frc.team6544.robot.subsystems;

import org.usfirst.frc.team6544.robot.RobotMap;
import org.usfirst.frc.team6544.robot.commands.XboxDrive;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class DriveSystem extends Subsystem {
	static double encoderCount;
	public static double DriveSpeed = 1.5;
	private static final ADXRS450_Gyro aGyro = new ADXRS450_Gyro();
	private static final WPI_TalonSRX _frontLeftMotor = new WPI_TalonSRX(RobotMap.kFronLeftCIM);
	private static final WPI_TalonSRX _backRightMotor = new WPI_TalonSRX(RobotMap.kBackRightCIM);
	private static final WPI_TalonSRX _frontRightMotor = new WPI_TalonSRX(RobotMap.kBackLeftCIM);
	private static final WPI_TalonSRX _backLeftMotor = new WPI_TalonSRX(RobotMap.kFrontRightCIM);
	private static final SpeedControllerGroup left = new SpeedControllerGroup(_frontLeftMotor, _backLeftMotor);
	private static final SpeedControllerGroup right = new SpeedControllerGroup(_frontRightMotor, _backRightMotor);
	private static final DifferentialDrive ArcadeDrive = new DifferentialDrive(left, right);
	/**
   	 * DriveSystem constructor
   	*/
    public DriveSystem() {
    	driveBaseInit();
    }
    
    
    /**
   	 * Initialization Method Put things that need to be setup before use here
   	 */
    public void driveBaseInit() {
    	//_frontLeftMotor.setInverted(true);
    	//_backRightMotor.setInverted(true);
    	_frontRightMotor.setInverted(false);
    	//_backLeftMotor.setInverted(true);
    	_frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	_frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
    }
    
    
    /**
	 * Get Gyro Angle
	 */
    public double getAngle() {
    	return aGyro.getAngle();
    }
    
    /**
	 * Reset Gyro to 0
	 */
    public void reset() {
    	aGyro.reset();
    }
    
    /**
	 * Arcade drive using Xbox Controller
	 *
	 * @param joy XboxController style joystick to use as the input for Arcade drive.
	 */
    public void arcadeDrive(Joystick joy) {
    	ArcadeDrive.arcadeDrive((-joy.getRawAxis(1)/DriveSpeed),(joy.getRawAxis(4)/DriveSpeed),true);
    }

	/**
   	 * Auto Arcade drive
   	 * @param speed value to use as the speed input for Arcade drive.
   	 * @param rotation value to use as the rotation input for Arcade drive.
   	 */
    public void arcadeDrive(double speed, double rotation) {
    	 ArcadeDrive.arcadeDrive(speed, rotation);
    }
    
    /**
	 * Sends zero to arcade drive.
	 * Called when used to stop the robot.
	 */
    public void stop() {
    	 ArcadeDrive.arcadeDrive(0, 0);
    }
    
    /**
     *Subsystem default command is XboxDrive
	 */
    public void initDefaultCommand() {
      setDefaultCommand(new XboxDrive());
    }
    
    /**
   	 * log method used to send data to the SmartDashboard, called periodically in the robot class
   	 */
    public static void log() {
      SmartDashboard.putNumber("Speed Setting", DriveSpeed);
//      SmartDashboard.putNumber("Drive Distance", getDistance());
//      SmartDashboard.putNumber("Encoder count", _frontRightMotor.getSelectedSensorPosition(0));
    }
}

