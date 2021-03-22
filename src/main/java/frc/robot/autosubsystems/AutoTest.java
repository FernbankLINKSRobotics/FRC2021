package frc.robot.autosubsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import frc.robot.subsystems.Constants;
import frc.robot.autosubsystems.AutoCorrect;

public class AutoTest {

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
    public AutoTest() {
        this.leftfrontmotor = DriveBase().leftfrontmotor;
        this.leftrearmotor = DriveBase().leftrearmotor;
        this.rightfrontmotor = DriveBase().rightfrontmotor;
        this.rightrearmotor = DriveBase().rightrearmotor;

        this.leftfrontencoder = DriveBase().leftfrontmotor.getEncoder();    
        this.leftrearencoder = DriveBase().leftrearmotor.getEncoder();    
        this.rightfrontencoder = DriveBase().rightfrontmotor.getEncoder();    
        this.rightrearencoder = DriveBase().rightrearmotor.getEncoder();

        this.AutoCorrect = new AutoCorrect();

    }
    public void movementTest() {
        if(leftfrontencoder.getPosition() < 200 || rightfrontencoder.getPosition() < 200) {
            encoderReadings();
            leftfrontmotor.set(-0.1);
            leftrearmotor.set(-0.1);
            rightfrontmotor.set(0.1);
            rightrearmotor.set(0.1);
        }
        while(leftfrontencoder.getPosition() > 200 || rightfrontencoder.getPosition() > 200) {
            encoderReadings();  
            leftfrontmotor.set(0);
            leftrearmotor.set(0);
            rightfrontmotor.set(0);
            rightrearmotor.set(0);
            //AutoCorrect.autoCorrectLeft(200);
            //AutoCorrect.autoCorrectRight(200);
        }
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
}