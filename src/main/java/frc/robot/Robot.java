/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autoarmsubsystems.ArmUp;
import frc.robot.autodrivesubsystems.DriveStop;
import frc.robot.autodrivesubsystems.lowvelocity.*;
import frc.robot.autodrivesubsystems.mediumvelocity.*;
import frc.robot.autodrivesubsystems.highvelocity.*;

import frc.robot.subsystems.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.autodrivesubsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Controller:
  public static XboxController controller = new XboxController(0);
  public static final GenericHID.Hand left = GenericHID.Hand.kLeft;
  public static final GenericHID.Hand right = GenericHID.Hand.kRight;

  
  //------------------------------------------------------------------------------------------------------------
  // Auto (Custom):
    // Forward:
  public LowDriveForward autonomousLowDriveForward = new LowDriveForward(Constants.Robot.target);  // "2" is a target for DriveForward.
  public MediumDriveForward autonomousMediumDriveForward = new MediumDriveForward(Constants.Robot.target);  // "2" is a target for DriveForward.
  public HighDriveForward autonomousHighDriveForward = new HighDriveForward(Constants.Robot.target);  // "2" is a target for DriveForward.

    // Backwards:
  public LowDriveBackwards autonomousLowDriveBackwards = new LowDriveBackwards(Constants.Robot.target); // "2" is a target for DriveBackwards.
  public MediumDriveBackwards autonomousMediumDriveBackwards = new MediumDriveBackwards(Constants.Robot.target); // "2" is a target for DriveBackwards.
  public HighDriveBackwards autonomousHighDriveBackwards = new HighDriveBackwards(Constants.Robot.target); // "2" is a target for DriveBackwards.

    // Turn Left:
  public LowTurnLeft autonomousLowTurnLeft = new LowTurnLeft(Constants.Robot.target); // "0" is a target for DriveTurnLeft.
  public MediumTurnLeft autonomousMediumTurnLeft = new MediumTurnLeft(Constants.Robot.target); // "0" is a target for DriveTurnLeft.
  public HighTurnLeft autonomousHighTurnLeft = new HighTurnLeft(Constants.Robot.target); // "0" is a target for DriveTurnLeft.

    // Turn Right:
  public LowTurnRight autonomousLowTurnRight = new LowTurnRight(Constants.Robot.target); // "0" is a target for DriveTurnRight.
  public MediumTurnRight autonomousMediumTurnRight = new MediumTurnRight(Constants.Robot.target); // "0" is a target for DriveTurnRight.
  public HighTurnRight autonomousHighTurnRight = new HighTurnRight(Constants.Robot.target); // "0" is a target for DriveTurnRight.

  public DriveStop autonomousDriveStop = new DriveStop(Constants.Robot.nulltarget); // "0" is a target for DriveBackwards.

  public ArmUp autonomousArmUp = new ArmUp();
  // Auto (Default):
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  public static DriveBase base = new DriveBase(1, 2, 3, 4);
  //------------------------------------------------------------------------------------------------------------
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    getDriveBaseInstance().initialize();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */

  @Override 
  public void autonomousInit() {    
      getDriveBaseInstance().initialize();
      getDriveBaseInstance().reset();
  }

  double power;
  double timeInMillis;
    
/*
  private void DriveStraightForDistance(double power, double timeInMillis) {
    this.power = power;
    this.timeInMillis = timeInMillis; 
  }
*/
  @Override
  public void autonomousPeriodic() {
      Timer Timer = new Timer();
      Timer.start();
  }

  private Object Success() {
    System.out.println("Success!");
    return null;
  }

  @Override
  public void teleopInit() {
    getDriveBaseInstance().initialize();
    getDriveBaseInstance().reset();  
  }


  @Override
  public void teleopPeriodic() {

    armAndIntake();
    reseter(); 
    drive();

  }

  
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  public void reseter() {
  }

  public DriveBase getDriveBaseInstance() {
    return new DriveBase(1, 2, 3, 4);
  }

}
