package frc.robot;

// import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveBase {

    //------------------------------------------------------------------------------------------------------------
    // - DriveBase Variables - 
    
    // Tunes/Drivebase (Motors):
    private CANSparkMax leftfrontmotor, leftrearmotor, rightfrontmotor, rightrearmotor; 

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


    private WPI_TalonSRX Arm = new WPI_TalonSRX(22);
    private WPI_VictorSPX Mouth = new WPI_VictorSPX(21);

    /*
    - Keep these in case ports need to be switched - 
    private WPI_VictorSPX elevator = new WPI_VictorSPX(12);
    private WPI_TalonSRX winch =  new WPI_TalonSRX(11);
    */

    public double intakeVelocity = 0.0;  // Value to be set after testing.
    public double extakeVelocity = 0.0; // Value to be set after testing.
    public double liftVelocity = 0.0;  // Value to be set after testing.
    public double lowerVelocity = -0.0;// Value to be set after testing.
    public double holdVelocity = 0.0;// Value to be set after testing.

    // - 

    public DriveBase(int leftfrontmotorPort, int leftrearmotorPort, int rightfrontmotorPort, int rightrearmotorPort) {
        this.leftfrontmotor = new CANSparkMax(leftfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.leftrearmotor = new CANSparkMax(leftrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightfrontmotor = new CANSparkMax(rightfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightrearmotor = new CANSparkMax(rightrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void initialize() {

        // These tunes are not needed at the moment as they have been set manually (via SparkMAX software):
        /*
        leftfrontmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        leftrearmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightfrontmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightrearmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);

        leftfrontmotor.setOpenLoopRampRate(0.5);
        leftrearmotor.setOpenLoopRampRate(0.5);
        rightfrontmotor.setOpenLoopRampRate(0.5);
        rightrearmotor.setOpenLoopRampRate(0.5);
        */

        // More Motor Tunes (to occur during each initialization period):
        leftfrontmotor.getEncoder().setPositionConversionFactor(34.19);
        leftrearmotor.getEncoder().setPositionConversionFactor(34.19);
        rightfrontmotor.getEncoder().setPositionConversionFactor(34.19);
        rightrearmotor.getEncoder().setPositionConversionFactor(34.19);

        turnController.setSetpoint(0);
        turnController.setTolerance(2);

        driveController.setSetpoint(0);
        driveController.setTolerance(5);

        ballTurnController.setSetpoint(0);
        ballTurnController.setTolerance(0.25);

        ballDriveController.setSetpoint(10);
        ballDriveController.setTolerance(0.5);
        
        reset();
    }

    public void reset() {
        leftfrontmotor.getEncoder().setPosition(0);
        leftrearmotor.getEncoder().setPosition(0);
        rightfrontmotor.getEncoder().setPosition(0);
        rightrearmotor.getEncoder().setPosition(0);

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

    public void arm (XboxController controller1, GenericHID.Hand left, GenericHID.Hand right) {
        double raiseVelocity = .2;
        double lowerVelocity = -.5;
        double intakeVelocity = .5;
        double extakeVelocity = -.75;

        if (controller1.getAButtonPressed()){
            Arm.set(raiseVelocity); 
        }

        if (controller1.getAButtonReleased()){
            Arm.set(0.0);
        }

        if (controller1.getBButtonPressed()){
            Arm.set(lowerVelocity);
        }

        if (controller1.getBButtonReleased()){
            Arm.set(0.0);
        }

        if (controller1.getXButtonPressed()) {
            Mouth.set(intakeVelocity);
        }

        if (controller1.getXButtonReleased()) {
            Mouth.set(0.0);
        }

        if (controller1.getYButtonPressed()) {
            Mouth.set(extakeVelocity); 
        }

        if (controller1.getYButtonReleased()) {
            Mouth.set(0.0);
        }
  }

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
    
    public void forwardDrive() {
        double throttle = driveController.calculate(getLeftPosition());

        leftfrontmotor.set(-throttle);
        leftrearmotor.set(-throttle);

        rightfrontmotor.set(throttle);
        rightrearmotor.set(throttle);
    }

    double setpoint = 1;
    public void setPoint(double setpoint) {
        //double setpoint = new setpoint(1.0);
    }

//------------------------------------------------------------------------------------------------------------
// Public Auto Objects:

    public boolean driveOnTarget() {
        return driveController.atSetpoint();
    }

    public void driveForward() {
        double throttle = driveController.calculate(getLeftPosition());

        leftfrontmotor.set(-throttle);
        leftrearmotor.set(-throttle);

        rightfrontmotor.set(throttle);
        rightrearmotor.set(throttle);
    }
    
    public void driveBackwards() {
        double throttle = driveController.calculate(getLeftPosition());

        leftfrontmotor.set(throttle);
        leftrearmotor.set(throttle);

        rightfrontmotor.set(-throttle);
        rightrearmotor.set(-throttle);
    }
    
    public void driveTurnLeft() {
        double throttle = driveController.calculate(getLeftPosition());

        leftfrontmotor.set(throttle);
        leftrearmotor.set(throttle);

        rightfrontmotor.set(throttle);
        rightrearmotor.set(throttle);
    }

    public void driveTurnRight() {
        double throttle = driveController.calculate(getLeftPosition());

        leftfrontmotor.set(-throttle);
        leftrearmotor.set(-throttle);

        rightfrontmotor.set(-throttle);
        rightrearmotor.set(-throttle);
    }

    public void driveStop() {

        leftfrontmotor.set(0);
        leftrearmotor.set(0);

        rightfrontmotor.set(0);
        rightrearmotor.set(0);
    }
//------------------------------------------------------------------------------------------------------------
}