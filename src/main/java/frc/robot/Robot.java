/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Constants;
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
    System.out.println("Robot Initializing");
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
    System.out.println("Robot in Periodic");
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
    System.out.println("Autonomous Initializing");    
      getDriveBaseInstance().initialize();
      getDriveBaseInstance().reset();
  }    

  @Override
  public void autonomousPeriodic() {
    System.out.println("Autonomous Periodic");
  }

  @Override
  public void teleopInit() {
    System.out.println("Teleop Initializing");
    getDriveBaseInstance().initialize();
    getDriveBaseInstance().reset();  
  }

  @Override
  public void teleopPeriodic() {
    System.out.println("Robot in Teleop");
    encoderTests();
    armAndIntake();
    reseter(); 
    drive();
  }

  private void armAndIntake() {
    getDriveBaseInstance().armSystem(controller, left, right);
  }

  //------------------------------------------------------------------------------------------------------------
  // Encoders:
  public CANEncoder leftfrontencoder;
  public CANEncoder leftrearencoder;
  public CANEncoder rightfrontencoder;
  public CANEncoder rightrearencoder;
  
  public double leftsideencoderPosition() {
    double leftsideencoders;
    return leftsideencoders = ((leftfrontencoder.getPosition() + leftrearencoder.getPosition()) / 2);
  }

  public double rightsideencoderPosition() {
    double rightsideencoders;
    return rightsideencoders = ((rightfrontencoder.getPosition() + rightrearencoder.getPosition()) / 2);
  }

  public double leftsideDistance() {
    double leftsidedistance;
    return leftsidedistance = ((leftsideencoderPosition() / Constants.Robot.Encoders.ticksperrotation) * Constants.Robot.Encoders.wheeldiameter);
  }

  public double rightsideDistance() {
    double rightsidedistance;
    return rightsidedistance = ((rightsideencoderPosition() / Constants.Robot.Encoders.ticksperrotation) * Constants.Robot.Encoders.wheeldiameter);
  }

  public double averageDistance() {
    double averagedistance;
    return averagedistance = ((leftsideDistance() + rightsideDistance()) / 2);
  }

  private void encoderTests() {
    leftfrontencoder = getDriveBaseInstance().leftfrontmotor.getEncoder();    
    leftrearencoder = getDriveBaseInstance().leftrearmotor.getEncoder();    
    rightfrontencoder = getDriveBaseInstance().rightfrontmotor.getEncoder();    
    rightrearencoder = getDriveBaseInstance().rightrearmotor.getEncoder();

    System.out.println("LeftFrontEncoder --> " + leftfrontencoder.getPosition());
    System.out.println("LeftRearencoder --> " + leftrearencoder.getPosition());
    System.out.println("RightFrontEncoder --> " + rightfrontencoder.getPosition());
    System.out.println("RighRearEncoder --> " + rightrearencoder.getPosition());
    System.out.println("");
    System.out.println("Entire Left Side" + leftsideencoderPosition());
    System.out.println("Entire Right Side" + rightsideencoderPosition());
    System.out.println("");
  }

  // Reinitializing DriveBase once to then refer to later:
  public DriveBase getDriveBaseInstance() {
    return new DriveBase(1, 2, 3, 4);
  }

  public void driveStraight() {
    // !!! None of this is actually included in AutoInit or AutoPeriodic !!!
    while ((rightsideDistance() > (leftsideDistance() -100)) || (rightsideDistance() < (leftsideDistance() + 100))) {
      if ((rightsideDistance() > 0) || (leftsideDistance() > 0)){
        double throttle = Constants.DriveBase.Encoders.testingthrottle;
        getDriveBaseInstance().leftfrontmotor.set(-throttle);
        getDriveBaseInstance().leftrearmotor.set(-throttle);

        getDriveBaseInstance().rightfrontmotor.set(throttle);
        getDriveBaseInstance().rightrearmotor.set(throttle);
          
        if ((rightsideDistance() > 720) || (leftsideDistance() > 720)) {
          double nullthrottle = Constants.DriveBase.Encoders.nullthrottle;
          getDriveBaseInstance().leftfrontmotor.set(nullthrottle);
          getDriveBaseInstance().leftrearmotor.set(nullthrottle);
    
          getDriveBaseInstance().rightfrontmotor.set(nullthrottle);
          getDriveBaseInstance().rightrearmotor.set(nullthrottle);
        }
      }
      if (((rightsideDistance() > 0) || (leftsideDistance() > 0 )) || ((rightsideDistance() > 720) || (leftsideDistance() > 720))) {
        double throttle = Constants.DriveBase.Encoders.nullthrottle;
        getDriveBaseInstance().leftfrontmotor.set(throttle);
        getDriveBaseInstance().leftrearmotor.set(throttle);
  
        getDriveBaseInstance().rightfrontmotor.set(throttle);
        getDriveBaseInstance().rightrearmotor.set(throttle);
      }
    }
  }
  //------------------------------------------------------------------------------------------------------------

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
}