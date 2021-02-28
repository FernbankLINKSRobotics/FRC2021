package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase {

    private CANSparkMax leftfrontmotor, leftrearmotor, rightfrontmotor, rightrearmotor;
    private WPI_VictorSPX Intake;
    private WPI_TalonSRX Lift;

    //private AHRS gyro = new AHRS();

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

    //private Limelight camera = new Limelight(Pipeline.BALL);

    private double tx_prev = 0;

    public DriveBase(int leftfrontmotorPort, int leftrearmotorPort, int rightfrontmotorPort, int rightrearmotorPort) {
        this.leftfrontmotor = new CANSparkMax(leftfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.leftrearmotor = new CANSparkMax(leftrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightfrontmotor = new CANSparkMax(rightfrontmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        this.rightrearmotor = new CANSparkMax(rightrearmotorPort, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void initialize() {
        leftfrontmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        leftrearmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightfrontmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightrearmotor.setIdleMode(CANSparkMax.IdleMode.kCoast);

        leftfrontmotor.setOpenLoopRampRate(0.5);
        leftrearmotor.setOpenLoopRampRate(0.5);
        rightfrontmotor.setOpenLoopRampRate(0.5);
        rightrearmotor.setOpenLoopRampRate(0.5);

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

        //gyro.reset();

        turnController.setSetpoint(0);
        driveController.setSetpoint(0);

        tx_prev = 0;
    }

    public void arcadeDrive(XboxController controller, GenericHID.Hand left, GenericHID.Hand right) {
        double throttle = 0;
        double turn = 0;

        if (controller.getY(left) > 0.05 || controller.getY(left) < -0.05) {
            throttle = controller.getY(left);
            throttle = -throttle;  //to southpaw or not.  currently not.

        } else {
            throttle =0;
        }

        if (controller.getX(left) > 0.05 || controller.getX(left) < -0.05) {
            turn = controller.getX(left);
        } else{
            turn = 0;
        }

        leftfrontmotor.set(turn - throttle);
        leftrearmotor.set(turn - throttle);

        rightfrontmotor.set(turn + throttle);
        rightrearmotor.set(turn + throttle);
    }

    public void angleLockedDrive(XboxController controller, GenericHID.Hand left) {
        double throttle = 0;
        //double turn = turnController.calculate(getAngle());

        if (controller.getY(left) > 0.05 || controller.getY(left) < -0.05) {
            throttle = controller.getY(left);
            throttle = -throttle; //to not southpaw
        } else if (controller.getY(left) < 0.05 || controller.getY(left) > -0.05) {
            throttle = 0;
        }
    }
        
/*
    public void Lift() {
        if (controller.getAngle)
    }

    public void snapToAngle() {
        //double turn = turnController.calculate(getAngle());

        leftfrontmotor.set(turn);
        leftrearmotor.set(turn);

        rightfrontmotor.set(turn);
        rightrearmotor.set(turn);
    }

  
    public void ballFollowDrive() {
        if (camera.getValues()[1] != 0) {
            tx_prev = camera.getValues()[1];
        }

        if (camera.getValues()[0] == 0) {
            ballSeekDrive();
        } else {
            double throttle = -ballDriveController.calculate(camera.getValues()[2]);
            double turn = -ballTurnController.calculate(camera.getValues()[1]);

            leftfrontmotor.set(turn - throttle);
            leftrearmotor.set(turn - throttle);

            rightfrontmotor.set(turn + throttle);
            rightrearmotor.set(turn + throttle);
        }
        
    }
    */
/*
    private void ballSeekDrive() {
        if (tx_prev > 0) {
            leftfrontmotor.set(0.2);
            leftrearmotor.set(0.2);

            rightfrontmotor.set(0.2);
            rightrearmotor.set(0.2);
        } else if (tx_prev < 0) {
            leftfrontmotor.set(-0.2);
            leftrearmotor.set(-0.2);

            rightfrontmotor.set(-0.2);
            rightrearmotor.set(-0.2);
        }   
    }
*/
    public void distanceDrive() {
        double throttle = driveController.calculate(getLeftPosition());

        leftfrontmotor.set(-throttle);
        leftrearmotor.set(-throttle);

        rightfrontmotor.set(throttle);
        rightrearmotor.set(throttle);
    }

    public void setAngle(double angle) {
        turnController.setSetpoint(angle);
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
    /*
    public double getAngle() {
        return gyro.getAngle();
    }
    */
    public boolean turnOnTarget() {
        return turnController.atSetpoint();
    }

    public boolean driveOnTarget() {
        return driveController.atSetpoint();
    }

    public boolean ballTurnOnTarget() {
        return ballTurnController.atSetpoint();
    }

    public boolean ballDriveOnTarget() {
        return ballDriveController.atSetpoint();
    }

    public void dashboard() {
        SmartDashboard.putData("Turn Controller", turnController);
        SmartDashboard.putData("Drive Controller", driveController);
        SmartDashboard.putData("Ball Turn Controller", ballTurnController);
        SmartDashboard.putData("Ball Drive Controller", ballDriveController);

        SmartDashboard.putNumber("Left Position", getLeftPosition());
        SmartDashboard.putNumber("Right Position", getRightPosition());
        SmartDashboard.putNumber("Left Velocity", getLeftVelocity());
        SmartDashboard.putNumber("Right Velocity", getRightVelocity());

        //SmartDashboard.putNumber("Angle", getAngle());

        //SmartDashboard.putNumber("tv", camera.getValues()[0]);
        //SmartDashboard.putNumber("tx", camera.getValues()[1]);
        //SmartDashboard.putNumber("ta", camera.getValues()[2]);
    }
}