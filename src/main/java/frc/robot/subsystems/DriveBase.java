package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.GenericHID;
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

        turnController.setSetpoint(0);
        driveController.setSetpoint(0);
    }

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
        SmartDashboard.putString("Null log.:", null);
    }
}