package frc.robot.autosubsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import frc.robot.subsystems.Constants;

public class EncoderActions {

    /*------------------------------------------------------------------------------------------------------*/
    // Defining Motor and Encoders:

    public CANSparkMax leftfrontmotor;
    public CANSparkMax leftrearmotor;
    public CANSparkMax rightfrontmotor;
    public CANSparkMax rightrearmotor;

    public CANEncoder leftfrontencoder;
    public CANEncoder leftrearencoder;
    public CANEncoder rightfrontencoder;
    public CANEncoder rightrearencoder;

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
    public EncoderActions() {
        this.leftfrontmotor = DriveBase().leftfrontmotor;
        this.leftrearmotor = DriveBase().leftrearmotor;
        this.rightfrontmotor = DriveBase().rightfrontmotor;
        this.rightrearmotor = DriveBase().rightrearmotor;

        this.leftfrontencoder = DriveBase().leftfrontmotor.getEncoder();    
        this.leftrearencoder = DriveBase().leftrearmotor.getEncoder();    
        this.rightfrontencoder = DriveBase().rightfrontmotor.getEncoder();    
        this.rightrearencoder = DriveBase().rightrearmotor.getEncoder();
    }

    public void forwardDrive(double Distance) {
        while (Distance == Distance) {
            encoderSpark();
            if (rightfrontencoder.getPosition() == 0.01 || leftfrontencoder.getPosition() == 0.01) {
                leftfrontmotor.set(-0.1);
                leftrearmotor.set(-0.1);
                rightfrontmotor.set(0.1);
                rightrearmotor.set(0.1);
                getDistance();
            }
            if ((rightfrontencoder.getPosition() > (Distance * 576.3156) || leftfrontencoder.getPosition() > (Distance * 576.3156)) || 
                                  (rightfrontencoder.getPosition() != 0) || leftfrontencoder.getPosition() != 0)  {
                leftfrontmotor.set(0);
                leftrearmotor.set(0);
                rightfrontmotor.set(0);
                rightrearmotor.set(0);
                getEncoderReset();
                getDistance();
            }
        }
    }

    public void backwardDrive(double Distance) {
        // Values inputted into double Distance is equivalent to feet.

        while (Distance == Distance) {
            encoderSpark();
            if (rightfrontencoder.getPosition() == 0.01 || leftfrontencoder.getPosition() == 0.01) {
                leftfrontmotor.set(0.1);
                leftrearmotor.set(0.1);
                rightfrontmotor.set(-0.1);
                rightrearmotor.set(-0.1);
                getDistance();
            }
            if ((rightfrontencoder.getPosition() < (Distance * -576.3156) || leftfrontencoder.getPosition() < (Distance * -576.3156)) || 
                                    (rightfrontencoder.getPosition() != 0 || leftfrontencoder.getPosition() != 0)) {
                leftfrontmotor.set(0);
                leftrearmotor.set(0);
                rightfrontmotor.set(0);
                rightrearmotor.set(0);
                getEncoderReset();
                getDistance();
            }
        }
    }

    public void clockwiseTurn(double Degree) {
        // Values inputted into double Degrees should be divisible by 90.

        encoderSpark();
        while (Degree == Degree ) {
            if (rightfrontencoder.getPosition() == 0.01 || leftfrontencoder.getPosition() == 0.01) {
                leftfrontmotor.set(-0.1);
                leftrearmotor.set(-0.1);
                rightfrontmotor.set(-0.1);
                rightrearmotor.set(-0.1);
                getDistance();
            }
            if ((rightfrontencoder.getPosition() < ((Degree / 90) * -340) || leftfrontencoder.getPosition() < ((Degree / 90) * -340)) || 
                                     (leftfrontencoder.getPosition() != 0 || rightfrontencoder.getPosition() != 0 )) {
                leftfrontmotor.set(0);
                leftrearmotor.set(0);
                rightfrontmotor.set(0);
                rightrearmotor.set(0);
                getEncoderReset();
                getDistance();
            }
        }
    }

    public void counterclockwiseTurn(double Degree) {
        //  Values inputted into double Degrees should be divisible by 90.

        while (Degree == Degree) {
            if ((rightfrontencoder.getPosition() < ((Degree / 90) * 340) || leftfrontencoder.getPosition() < ((Degree / 90) * 340))) {
                leftfrontmotor.set(0.1);
                leftrearmotor.set(0.1);
                rightfrontmotor.set(0.1);
                rightrearmotor.set(0.1);
                getEncoderReset();
                getDistance();
            }
            if (rightfrontencoder.getPosition() > ((Degree / 90) * 340) || leftfrontencoder.getPosition() > ((Degree / 90) * 340) || 
                                 (rightfrontencoder.getPosition() != 0) || (leftfrontencoder.getPosition() != 0)){
                leftfrontmotor.set(0);
                leftrearmotor.set(0);
                rightfrontmotor.set(0);
                rightrearmotor.set(0);
                getDistance();
            }
        }
    }

    public static frc.robot.subsystems.DriveBase DriveBase() {
        return new frc.robot.subsystems.DriveBase(1, 2, 3, 4);
    }

    public static Object isComplete() {
        return isComplete();
    }
    
    public void getDistance() {
        System.out.println("LeftSideEncoderPosition: " + leftsideencoderPosition());
        System.out.println("RightSideEncoderPosition: " + rightsideencoderPosition());
    }

    public void getEncoderReset()  {
        leftfrontencoder.setPosition(0);
        leftrearencoder.setPosition(0);
        rightfrontencoder.setPosition(0);
        rightrearencoder.setPosition(0);
    }

    public void encoderSpark() {
        leftfrontencoder.setPosition(0.01);
        leftrearencoder.setPosition(0.01);
        rightfrontencoder.setPosition(0.01);
        rightrearencoder.setPosition(0.01);
    }
}
