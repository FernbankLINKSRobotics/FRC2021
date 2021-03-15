/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autosubsystems.EncoderActions;
import frc.robot.autosubsystems.EncoderValues;
import frc.robot.subsystems.DriveBase;

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
  public DriveBase DriveBase;

  // Motor/Drivebase:
  public CANSparkMax leftfrontmotor;
  public CANSparkMax leftrearmotor;
  public CANSparkMax rightfrontmotor;
  public CANSparkMax rightrearmotor;

  public static DriveBase base = new DriveBase(1, 2, 3, 4);

  public EncoderActions EncoderActions;

    // Auto (Default):
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  
  public Robot() {
     this.EncoderActions = new EncoderActions();
     this.DriveBase = new DriveBase(1, 2, 3, 4);
  }


  //------------------------------------------------------------------------------------------------------------
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    base.initialize();
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
      base.initialize();
      base.reset();
      // starting point of your route
      EncoderActions.getDistance();
      EncoderActions.forwardDrive(1);
      EncoderActions.backwardDrive(1);
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    base.initialize();
    base.reset();  
  }

  @Override
  public void teleopPeriodic() {
    EncoderValues.encoderReading();
    reseter(); 
    drive();
    armControl();
  }

  public void drive() {
    base.arcadeDrive(controller, left, right);
  }

  public void armControl() {
    DriveBase.armSystem(controller, left, right);
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

}