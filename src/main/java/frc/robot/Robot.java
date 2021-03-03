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
    /* 

    // Jank Demonstration:
      autonomousMediumDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(1.5);
      autonomousMediumDriveBackwards.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();

      autonomousLowDriveForward.start();
      edu.wpi.first.wpilibj.Timer.delay(1.5);
      autonomousLowDriveForward.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();

      autonomousLowDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(1.5);
      autonomousLowDriveBackwards.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();

      autonomousMediumDriveForward.start();
      edu.wpi.first.wpilibj.Timer.delay(1.5);
      autonomousMediumDriveForward.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();
      
      autonomousMediumDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(1);
      autonomousMediumDriveBackwards.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();

      autonomousHighDriveForward.start();
      edu.wpi.first.wpilibj.Timer.delay(1.5);
      autonomousHighDriveForward.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();
      
      autonomousHighDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(1);
      autonomousHighDriveBackwards.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(1000);
      autonomousDriveStop.isComplete();

    // End of Jank Demonstration

    */ 
      //Timer Timer = new Timer();
      //Timer.start();

      //double power = 0.5;
      //double timeInMillis = 2;
      //DriveStraightForDistance(power, timeInMillis);   
      
      

      //System.out.println("Timer Started!");
      //edu.wpi.first.wpilibj.Timer.delay(1);
      /*
      autonomousArmUp.start();
      edu.wpi.first.wpilibj.Timer.delay(1);
      autonomousArmUp.isComplete();
      */
      //System.out.print("DriveForward Starting!");

      /*
      autonomousMediumDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(1.5);
      autonomousMediumDriveBackwards.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(0.001);
      autonomousDriveStop.isComplete();

      autonomousMediumDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(1.5);
      autonomousMediumDriveBackwards.isComplete();

      /*

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(100);
      autonomousDriveStop.isComplete();

      autonomousMediumDriveForward.start();
      edu.wpi.first.wpilibj.Timer.delay(5);
      autonomousMediumDriveForward.isComplete();

      //System.out.print("DriveStop Starting!");
      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();

      autonomousMediumDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(5);
      autonomousMediumDriveBackwards.isComplete();

      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(100);
      autonomousDriveStop.isComplete();
      /*
      autonomousDriveTurnLeft.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveTurnLeft.isComplete();

      
      //Drive Forward (For 2 Seconds)
      autonomousDriveForward.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveForward.isComplete();

      //Drive Stop (For 2 Seconds)
      autonomousDriveStop.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveStop.isComplete();

      //Drive Backwards (For 2 Seconds)
      autonomousDriveBackwards.start();
      edu.wpi.first.wpilibj.Timer.delay(2);
      autonomousDriveBackwards.start();

      //Drive Stop
      autonomousDriveStop.start();
      autonomousDriveStop.isComplete();
      */
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
    test();
    armAndIntake();
    reseter(); 
    drive();

  }

  private void armAndIntake() {
    getDriveBaseInstance().armSystem(controller, left, right);

  }

  public CANEncoder leftfrontencoder;
  public CANEncoder leftrearencoder;
  public CANEncoder rightfrontencoder;
  public CANEncoder rightrearencoder;
  
  public double leftsideencoders() {
    double leftsideencoders;
    return leftsideencoders = ((leftfrontencoder.getPosition() + leftrearencoder.getPosition()) / 2);
  }


  private void test() {
    //CANSparkMax leftfrontmotorTest = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    leftfrontencoder = getDriveBaseInstance().leftfrontmotor.getEncoder();    
    leftrearencoder = getDriveBaseInstance().leftrearmotor.getEncoder();    
    rightfrontencoder = getDriveBaseInstance().rightfrontmotor.getEncoder();    
    rightrearencoder = getDriveBaseInstance().rightrearmotor.getEncoder();    

    

    //System.out.print("After leftfrontencoder instantiate ------------------------------------------------------------------------------")
    System.out.println("LeftFrontEncoder --> " + leftfrontencoder.getPosition());
    System.out.println("LeftRearencoder --> " + leftrearencoder.getPosition());
    System.out.println("RightFrontEncoder --> " + rightfrontencoder.getPosition());
    System.out.println("RighRearEncoder --> " + rightrearencoder.getPosition());
    System.out.println("");

    System.out.println("Entire Left Side" + leftsideencoders());




    //leftfrontencoder = leftfrontmotor.getEncoder();
    //System.out.println("Encoder Position" + leftfrontencoder.getPosition());
    //SmartDashboard.putNumber("Encoder Position", leftfrontencoder.getPosition());
  }

  public void drive() {
    getDriveBaseInstance().arcadeDrive(controller, left, right);
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
