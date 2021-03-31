package frc.robot.autosubsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import frc.robot.subsystems.Constants;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.autosubsystems.AutoCorrect;

public class AutoCommands {

    /*------------------------------------------------------------------------------------------------------*/
    // Defining:

    public CANSparkMax leftfrontmotor;
    public CANSparkMax leftrearmotor;
    public CANSparkMax rightfrontmotor;
    public CANSparkMax rightrearmotor;

    public CANEncoder leftfrontencoder;
    public CANEncoder leftrearencoder;
    public CANEncoder rightfrontencoder;
    public CANEncoder rightrearencoder;

    public WPI_TalonSRX Arm;
    public WPI_VictorSPX Mouth;
    public WPI_VictorSPX InternalIntake;
    public WPI_VictorSPX ConveyorBelt;
    public WPI_TalonSRX ShooterA;
    public WPI_TalonSRX ShooterB;

    public AutoCorrect AutoCorrect;

    /*------------------------------------------------------------------------------------------------------*/
    // Math Objects:
    public double leftsideencoderPosition() {
        double leftsideencoders;
        return leftsideencoders = ((-leftfrontencoder.getPosition() + -leftrearencoder.getPosition()) / 2);
      }
    
      public double rightsideencoderPosition() {
        double rightsideencoders;
        return rightsideencoders = ((rightfrontencoder.getPosition() + rightrearencoder.getPosition()) / 2);
      }
    
      public double leftsideDistance() {
        double leftsidedistance;
        return leftsidedistance = ((-leftsideencoderPosition() / Constants.Robot.Encoders.ticksperrotation) * Constants.Robot.Encoders.wheeldiameter);
      }
    
      public double rightsideDistance() {
        double rightsidedistance;
        return rightsidedistance = ((rightsideencoderPosition() / Constants.Robot.Encoders.ticksperrotation) * Constants.Robot.Encoders.wheeldiameter);
      }
    
      public double averageDistance() {
        double averagedistance;
        return averagedistance = ((leftsideDistance() + rightsideDistance()) / 2);
      }
    /*------------------------------------------------------------------------------------------------------*/

    // init
    public AutoCommands() {
        this.leftfrontmotor = DriveBase().leftfrontmotor;
        this.leftrearmotor = DriveBase().leftrearmotor;
        this.rightfrontmotor = DriveBase().rightfrontmotor;
        this.rightrearmotor = DriveBase().rightrearmotor;

        this.Arm = DriveBase().Arm;
        this.Mouth = DriveBase().Mouth;
        this.InternalIntake = DriveBase().InternalIntake;
        this.ConveyorBelt = DriveBase().ConveyorBelt;
        this.ShooterA = DriveBase().ShooterA;
        this.ShooterB = DriveBase().ShooterB;

        this.leftfrontencoder = DriveBase().leftfrontmotor.getEncoder();    
        this.leftrearencoder = DriveBase().leftrearmotor.getEncoder();    
        this.rightfrontencoder = DriveBase().rightfrontmotor.getEncoder();    
        this.rightrearencoder = DriveBase().rightrearmotor.getEncoder();
        
        this.AutoCorrect = new AutoCorrect();
    }

        public void forward(double desired, double speed, double intakeSpeed) {
          getDistance();
          desired = desired * Constants.Robot.Encoders.block;
          double actual = ((leftfrontencoder.getPosition() + -rightfrontencoder.getPosition()) / 2);
          double target = -1*desired + actual;

          while(target < actual) {
              Mouth.set(intakeSpeed);
              InternalIntake.set(intakeSpeed);
              ConveyorBelt.set(intakeSpeed);
              actual = ((leftfrontencoder.getPosition() + -rightfrontencoder.getPosition()) / 2);
              leftfrontmotor.set(-speed);
              leftrearmotor.set(-speed);
              rightfrontmotor.set(speed);
              rightrearmotor.set(speed);      
            }
            stop();
          }

        public void rightTurn(double desired, double speed) {
          getDistance();
          desired = (desired/90) * Constants.Robot.Encoders.ticksper90degree;
          double actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
          double target = -1*desired + actual;

          while(target < actual) {
            actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
            leftfrontmotor.set(-speed);
            leftrearmotor.set(-speed);
            rightfrontmotor.set(-speed);
            rightrearmotor.set(-speed);      
          }
          stop();
        }
        
        public void leftTurn(double desired, double speed) {
          getDistance();
          desired = (desired/90) * Constants.Robot.Encoders.ticksper90degree;
          double actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
          double target = desired + actual;

          while(target > actual) {
            actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
            leftfrontmotor.set(speed);
            leftrearmotor.set(speed);
            rightfrontmotor.set(speed);
            rightrearmotor.set(speed);      
          }
          stop();
        }
        
    public static frc.robot.subsystems.DriveBase DriveBase() {
        return new frc.robot.subsystems.DriveBase(1, 2, 3, 4);
    }

    public void encoderReadings() {
      System.out.println("LeftFrontEncoder --> " + -leftfrontencoder.getPosition());
      System.out.println("LeftRearencoder --> " + -leftrearencoder.getPosition());
      System.out.println("RightFrontEncoder --> " + rightfrontencoder.getPosition());
      System.out.println("RighRearEncoder --> " + rightrearencoder.getPosition());
      System.out.println("");
    }

    public void getDistance() { 
      leftfrontencoder.getPosition();
      leftrearencoder.getPosition();
      rightfrontencoder.getPosition();
      rightrearencoder.getPosition();
    }

    public void forward() {
      leftfrontmotor.set(-0.1);
      leftrearmotor.set(-0.1);
      rightfrontmotor.set(0.1);
      rightrearmotor.set(0.1);
    }

    public void stop() {
      double zeroVelocity = 0;
      leftfrontmotor.set(zeroVelocity);
      leftrearmotor.set(zeroVelocity);
      rightfrontmotor.set(zeroVelocity);
      rightrearmotor.set(zeroVelocity);
      Mouth.set(zeroVelocity);
      InternalIntake.set(zeroVelocity);
      ConveyorBelt.set(zeroVelocity);
    }

    public void autoCorrect(double leftTarget, double rightTarget) {
      AutoCorrect.autoCorrectLeft(leftTarget);
      AutoCorrect.autoCorrectRight(rightTarget);
    }
}