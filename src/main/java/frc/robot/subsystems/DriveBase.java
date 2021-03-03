package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
// import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveBase implements DriveAction {

    //------------------------------------------------------------------------------------------------------------
    // - DriveBase Variables - 
    
    // Tunes/Drivebase (Motors):
    public CANSparkMax leftfrontmotor, leftrearmotor, rightfrontmotor, rightrearmotor; 

    // private AHRS gyro = new AHRS();

    private final double pTurn = 0.005;
    private final double iTurn = 0;
    private final double dTurn = 0;

    private final double pDrive = 0.016;
    private final double iDrive = 0;
    private final double dDrive = 0;

    private PIDController turnController = new PIDController(pTurn, iTurn, dTurn);
    private PIDController driveController = new PIDController(pDrive, iDrive, dDrive);
    private PIDController ballTurnController = new PIDController(pTurn, iTurn, dTurn);
    private PIDController ballDriveController = new PIDController(pDrive, iDrive, dDrive);
    


    // - Arm & Intake Variables - 
    private WPI_TalonSRX Arm = new WPI_TalonSRX(5);
    private WPI_VictorSPX Mouth = new WPI_VictorSPX(6);

    // - Xbox Controllers - 
    public static XboxController controller = new XboxController(0);
    //public static XboxController controller = new XboxController(1);

    

    public DriveBase(int leftfrontmotorPort, int leftrearmotorPort, int rightfrontmotorPort, int rightrearmotorPort) {
        this.leftfrontmotor = new CANSparkMax(leftfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.leftrearmotor = new CANSparkMax(leftrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightfrontmotor = new CANSparkMax(rightfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightrearmotor = new CANSparkMax(rightrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void initialize() {
        // These tunes are not needed at the moment as they have been set manually (via SparkMAX software):

        leftfrontmotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        leftrearmotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightfrontmotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightrearmotor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        leftfrontmotor.setOpenLoopRampRate(Constants.DriveBase.MotorControllers.openramprate);
        leftrearmotor.setOpenLoopRampRate(Constants.DriveBase.MotorControllers.openramprate);
        rightfrontmotor.setOpenLoopRampRate(Constants.DriveBase.MotorControllers.openramprate);
        rightrearmotor.setOpenLoopRampRate(Constants.DriveBase.MotorControllers.openramprate);
        

        // More Motor Tunes (to occur during each initialization period):
        leftfrontmotor.getEncoder().setPositionConversionFactor(Constants.DriveBase.Encoders.encoderconversion);
        leftrearmotor.getEncoder().setPositionConversionFactor(Constants.DriveBase.Encoders.encoderconversion);
        rightfrontmotor.getEncoder().setPositionConversionFactor(Constants.DriveBase.Encoders.encoderconversion);
        rightrearmotor.getEncoder().setPositionConversionFactor(Constants.DriveBase.Encoders.encoderconversion);

        turnController.setSetpoint(Constants.DriveBase.Controllers.zero);
        turnController.setTolerance(Constants.DriveBase.Controllers.turntollerance);

        driveController.setSetpoint(Constants.DriveBase.Controllers.zero);
        driveController.setTolerance(Constants.DriveBase.Controllers.drivetollerance);

        ballTurnController.setSetpoint(Constants.DriveBase.Controllers.zero);
        ballTurnController.setTolerance(Constants.DriveBase.Controllers.balltollerance);

        ballDriveController.setSetpoint(Constants.DriveBase.Controllers.balldrive);
        ballDriveController.setTolerance(Constants.DriveBase.Controllers.balldrivetollerance);
        
        reset();
    }

    public void reset() {
        leftfrontmotor.getEncoder().setPosition(Constants.DriveBase.Encoders.zero);
        leftrearmotor.getEncoder().setPosition(Constants.DriveBase.Encoders.zero);
        rightfrontmotor.getEncoder().setPosition(Constants.DriveBase.Encoders.zero);
        rightrearmotor.getEncoder().setPosition(Constants.DriveBase.Encoders.zero);
        

        // Not needed as there is no Gyro on the current robot:
        // gyro.reset();

        turnController.setSetpoint(0);
        driveController.setSetpoint(0);
    }

    //------------------------------------------------------------------------------------------------------------
    // Teleop Drive:
    public void arcadeDrive(XboxController controller, GenericHID.Hand left, GenericHID.Hand right) {
        double throttle = 0;
        double turn = 0;
        

        if (controller.getY(left) > 0.05 || controller.getY(left) < -0.05) {
            throttle = controller.getY(left);
        } 

        if (controller.getX(right) > 0.05 || controller.getX(right) < -0.05) {
            turn = controller.getX(right);
            turn = -turn;
        } 

        leftfrontmotor.set(turn + throttle);
        leftrearmotor.set(turn + throttle);

        rightfrontmotor.set(turn - throttle);
        rightrearmotor.set(turn - throttle);
        

        // Deemed Unsafe:
        /*
        if (controller.getBumper(left)) {
            Timer timer = new Timer();
            timer.start();
            leftfrontmotor.set(0);
            leftrearmotor.set(0);
            rightfrontmotor.set(0);
            rightrearmotor.set(0);
            Timer.delay(5); // Five second time out.
        }
        */
    }

    public void armSystem (XboxController controller, GenericHID.Hand left, GenericHID.Hand right) {
        double raiseVelocity = Constants.DriveBase.Arm.raisevelocity;
        double lowerVelocity = Constants.DriveBase.Arm.lowervelocity;
        double intakeVelocity = Constants.DriveBase.Arm.intakevelocity;
        double extakeVelocity = Constants.DriveBase.Arm.extakevelocity;
        double zeroVelocity = Constants.DriveBase.Arm.zero;
        double holdVelocity = Constants.DriveBase.Arm.holdvelocity;

        if (controller.getAButtonPressed()){
            Arm.set(raiseVelocity); 
        }

        if (controller.getAButtonReleased()){
            Arm.set(zeroVelocity);
        }

        if (controller.getBButtonPressed()){
            Arm.set(lowerVelocity);
        }

        if (controller.getBButtonReleased()) {
            Arm.set(zeroVelocity);
        }

        if (controller.getXButtonPressed()) {
            Mouth.set(intakeVelocity);
        }

        if (controller.getXButtonReleased()) {
            Mouth.set(zeroVelocity);
        }

        if (controller.getYButtonPressed()) {
            Mouth.set(extakeVelocity); 
        }

        if (controller.getYButtonReleased()) {
            Mouth.set(zeroVelocity);
        }

        // Deemed Unsafe:
        /*
        if (controller.getBumper(left)) {
            Timer timer = new Timer();
            timer.start();
            Mouth.set(0);
            Arm.set(0);
            Timer.delay(5); // Five second time out.

        }
        */
        /*
        if (controller.getBumper(right)) {
            Timer Timer = new Timer();
            Timer.start();  
            Arm.set(-.3);
            edu.wpi.first.wpilibj.Timer.delay(.75);
            Arm.set(0);
        }
        */
        /*
        if (controller.getBumper(right)) {
            Timer Timer = new Timer();
            Timer.start();
            Arm.set(-.5);
            edu.wpi.first.wpilibj.Timer.delay(.25);
            Arm.set(0);
        */
        }
    

//------------------------------------------------------------------------------------------------------------
// NEOSpark Max Encoder Logic Objects:

    public void setDistance(double degrees) {
        driveController.setSetpoint(degrees);
    }

    public double getLeftPosition() {
        return leftfrontmotor.getEncoder().getPosition();
    }

    public double getRightPosition() {
        return rightfrontmotor.getEncoder().getPosition();
    }

    public double getLeftVelocity() {
        return leftfrontmotor.getEncoder().getVelocity();
    }

    public double getRightVelocity() {
        return rightfrontmotor.getEncoder().getVelocity();
    }

    public double getAveragePosition() {
        return ((getLeftPosition() - getRightPosition()) / 2);
    }

    public double getAverageVelocity() {
        return ((getLeftVelocity() - getRightVelocity()) / 2);
    }

//------------------------------------------------------------------------------------------------------------
// Auto Drive Objects (These are hardcoded, likely to removed soon!):

    public boolean driveOnTarget() {
        return driveController.atSetpoint();
    }

    double setpoint = 1;  // Setpoint for Auto.
    public void setPoint(double setpoint) {
        //double setpoint = new setpoint(1.0);
    }

    public void driveStop() {

        leftfrontmotor.set(0);
        leftrearmotor.set(0);

        rightfrontmotor.set(0);
        rightrearmotor.set(0);
    }

//------------------------------------------------------------------------------------------------------------


    @Override
    public void start() {
        SmartDashboard.putString("Starting...", null);
    }
    

    @Override
    public void stop() {
        SmartDashboard.putString("Stopping...:", null);
    }
               
    @Override
    public void log() {
        SmartDashboard.putString("Data Report:", null);
        SmartDashboard.putNumber("Left Drive Train Position: ", getLeftPosition());
        SmartDashboard.putNumber("Right Drive Train Position: ", getRightPosition());
        SmartDashboard.putNumber("Average Position: ", getAveragePosition());
        SmartDashboard.putNumber("Left Drive Train Velocity: ", getLeftVelocity());
        SmartDashboard.putNumber("Right Drive Train Velocity: ", getRightVelocity());
        SmartDashboard.putNumber("Average Velocity: ", getAverageVelocity());

    }
}