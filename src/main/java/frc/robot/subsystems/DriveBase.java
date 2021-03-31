package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase implements DriveAction {

    public CANSparkMax leftfrontmotor, leftrearmotor, rightfrontmotor, rightrearmotor; 

    public final double pTurn = 0.005;
    public final double iTurn = 0;
    public final double dTurn = 0;

    public final double pDrive = 0.016;
    public final double iDrive = 0;
    public final double dDrive = 0;

    public PIDController turnController = new PIDController(pTurn, iTurn, dTurn);
    public PIDController driveController = new PIDController(pDrive, iDrive, dDrive);
    public PIDController ballTurnController = new PIDController(pTurn, iTurn, dTurn);
    public PIDController ballDriveController = new PIDController(pDrive, iDrive, dDrive);
    
    // - Arm & Intake Variables - 
    public WPI_TalonSRX Arm = new WPI_TalonSRX(5);
    public WPI_VictorSPX Mouth = new WPI_VictorSPX(6);
    public WPI_VictorSPX InternalIntake = new WPI_VictorSPX(15);
    public WPI_VictorSPX ConveyorBelt = new WPI_VictorSPX(16);
    public WPI_TalonSRX ShooterA = new WPI_TalonSRX(25);
    public WPI_TalonSRX ShooterB = new WPI_TalonSRX(26);

    // - Xbox Controllers - 
    public static XboxController controller = new XboxController(0);
  
    public DriveBase(int leftfrontmotorPort, int leftrearmotorPort, int rightfrontmotorPort, int rightrearmotorPort) {
        this.leftfrontmotor = new CANSparkMax(leftfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.leftrearmotor = new CANSparkMax(leftrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightfrontmotor = new CANSparkMax(rightfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightrearmotor = new CANSparkMax(rightrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void initialize() {

        // !! Change Motor kType back after autonomous testing  !!
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

        if (controller.getX(left) > 0.05 || controller.getX(left) < -0.05) {
            turn = controller.getX(left);
            turn = -turn;
        } 

        leftfrontmotor.set(turn + throttle);
        leftrearmotor.set(turn + throttle);

        rightfrontmotor.set(turn - throttle);
        rightrearmotor.set(turn - throttle);
        
    }

    public void armSystem (XboxController controller, GenericHID.Hand left, GenericHID.Hand right) {
        double raiseVelocity = Constants.DriveBase.Arm.raisevelocity;
        double lowerVelocity = Constants.DriveBase.Arm.lowervelocity;
        double intakeVelocity = Constants.DriveBase.Arm.intakevelocity;
        double extakeVelocity = Constants.DriveBase.Arm.extakevelocity;
        double zeroVelocity = Constants.DriveBase.Arm.zero;
        double holdVelocity = Constants.DriveBase.Arm.holdvelocity;
        double upstreamVelocity = Constants.DriveBase.InternalIntake.upstreamvelocity;
        double fireVelocity = Constants.DriveBase.InternalIntake.firevelocity;

        if (controller.getAButtonPressed()){
            Arm.set(raiseVelocity); 
        }
        
        if (controller.getBButtonPressed()){
            Arm.set(lowerVelocity);
        }
        
        if (controller.getXButtonPressed()) {
            Timer Timer = new Timer();
            Timer.start();
            Mouth.set(intakeVelocity);  
            InternalIntake.set(upstreamVelocity);
            ConveyorBelt.set(-upstreamVelocity);
            edu.wpi.first.wpilibj.Timer.delay(.25);
            Timer.stop();          
        }

        if (controller.getYButtonPressed()) {
            Mouth.set(extakeVelocity); 
        }
    }
    
    public void highShooter (XboxController controller, GenericHID.Hand left, GenericHID.Hand right) {
        double upstreamVelocity = Constants.DriveBase.InternalIntake.upstreamvelocity;
        double fireVelocity = Constants.DriveBase.InternalIntake.firevelocity;
        double zeroVelocity = Constants.DriveBase.Arm.zero;

        if (controller.getBumperPressed(right)) {
            Timer Timer = new Timer();
            Timer.start();
            ShooterA.set(fireVelocity);
            ShooterB.set(fireVelocity);
            edu.wpi.first.wpilibj.Timer.delay(.25);
            Timer.stop();     
        }
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