package frc.robot.autosubsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import frc.robot.subsystems.Constants;
import frc.robot.autosubsystems.AutoCorrect;

public class AutoNav1 {

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
    public AutoNav1() {
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
    /*
    double step = 1;
    public void movementTest() {
      while (step == 2) {
        if(leftfrontencoder.getPosition() < (Constants.Robot.Encoders.ticksperrotation*4) || rightfrontencoder.getPosition() < (Constants.Robot.Encoders.ticksperrotation*4)) {
            encoderReadings();
            forward();
        }
        while(leftfrontencoder.getPosition() > (Constants.Robot.Encoders.ticksperrotation*4) || rightfrontencoder.getPosition() > (Constants.Robot.Encoders.ticksperrotation*4)) {
            encoderReadings();  
            leftfrontmotor.set(0);
            leftrearmotor.set(0);
            rightfrontmotor.set(0);
            rightrearmotor.set(0);
            //AutoCorrect.autoCorrectLeft(200);
            //AutoCorrect.autoCorrectRight(200);
        }
      }
      while (step == 2) {
      if (rightfrontencoder.getPosition() > ((Constants.Robot.Encoders.ticksperrotation*4)-340) || leftfrontencoder.getPosition() > ((Constants.Robot.Encoders.ticksperrotation*4)-340)) {
          leftfrontmotor.set(-0.1);
          leftrearmotor.set(-0.1);
          rightfrontmotor.set(-0.1);
          rightrearmotor.set(-0.1);
      }
      if (rightfrontencoder.getPosition() < ((Constants.Robot.Encoders.ticksperrotation*4)-340) || leftfrontencoder.getPosition() < ((Constants.Robot.Encoders.ticksperrotation*4)-340)) {
          leftfrontmotor.set(0);
          leftrearmotor.set(0);
          rightfrontmotor.set(0);
          rightrearmotor.set(0);
      
        }
      }
    }
    */
/*
    public void testMethod(double desired, double targetrightdistance) {
        getDistance();
        double actualRightDistance = ((rightfrontencoder.getPosition() + rightrearencoder.getPosition()) / 2);
        double actual = ((leftfrontencoder.getPosition() + leftrearencoder.getPosition()) / 2);
        double target = desired + actual;

        while(target < actual) {
            actual = ((leftfrontencoder.getPosition() + leftrearencoder.getPosition()) / 2);
            System.out.println("Actual Distance is " + actual + "   " + "Target Distance is " + target);
            System.out.println("Leftfrontmotor = " + leftfrontencoder.getPosition() + "   " + "Leftrearmotor = " + leftrearencoder.getPosition());
            leftfrontmotor.set(0.1);
            leftrearmotor.set(0.1);
            //rightfrontmotor.set(0.1);
            //rightrearmotor.set(0.1);      
            //System.out.println("Here is the actual distance" + );  
          }
        
          leftfrontmotor.set(0);
          leftrearmotor.set(0);
          //rightfrontmotor.set(0);
          //rightrearmotor.set(0);  
        }
*/

        public void forward(double desired, double speed) {
          getDistance();
          desired = desired * Constants.Robot.Encoders.block;
          double actual = ((leftfrontencoder.getPosition() + -rightfrontencoder.getPosition()) / 2);
          double target = -1*desired + actual;
  
          while(target < actual) {
              actual = ((leftfrontencoder.getPosition() + -rightfrontencoder.getPosition()) / 2);
              System.out.println("");
              //System.out.println("Leftfrontmotor = " + leftfrontencoder.getPosition() + "   " + "Leftrearmotor = " + leftrearencoder.getPosition());
              leftfrontmotor.set(-speed);
              leftrearmotor.set(-speed);
              rightfrontmotor.set(speed);
              rightrearmotor.set(speed);      
              //System.out.println("Here is the actual distance" + );  
            }
          
            leftfrontmotor.set(0);
            leftrearmotor.set(0);
            rightfrontmotor.set(0);
            rightrearmotor.set(0);  
          }

        public void rightTurn(double desired, double speed) {
          getDistance();
          desired = desired * Constants.Robot.Encoders.ticksper90degree;
          double actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
          double target = -1*desired + actual;

          while(target < actual) {
            actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
            System.out.print("Acutal   " + actual + "        " + "Target    "  + target);
            System.out.println("");
            //System.out.println("Leftfrontmotor = " + leftfrontencoder.getPosition() + "   " + "Leftrearmotor = " + leftrearencoder.getPosition());
            leftfrontmotor.set(-speed);
            leftrearmotor.set(-speed);
            rightfrontmotor.set(-speed);
            rightrearmotor.set(-speed);      
            //System.out.println("Here is the actual distance" + );  
          }
        
          leftfrontmotor.set(0);
          leftrearmotor.set(0);
          rightfrontmotor.set(0);
          rightrearmotor.set(0);  
        }
        
        public void leftTurn(double desired, double speed) {
          getDistance();
          desired = desired * Constants.Robot.Encoders.ticksper90degree;
          double actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
          double target = desired + actual;

          while(target > actual) {
            actual = ((leftfrontencoder.getPosition() + rightfrontencoder.getPosition()) / 2);
            System.out.print("Acutal   " + actual + "        " + "Target    "  + target);
            System.out.println("");
            //System.out.println("Leftfrontmotor = " + leftfrontencoder.getPosition() + "   " + "Leftrearmotor = " + leftrearencoder.getPosition());
            leftfrontmotor.set(speed);
            leftrearmotor.set(speed);
            rightfrontmotor.set(speed);
            rightrearmotor.set(speed);      
            //System.out.println("Here is the actual distance" + );  
          }
        
          leftfrontmotor.set(0);
          leftrearmotor.set(0);
          rightfrontmotor.set(0);
          rightrearmotor.set(0);  
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
      
      double averageRightDistance = ((rightfrontencoder.getPosition() + rightrearencoder.getPosition()) / 2);
      double averageLeftDistance = ((leftfrontencoder.getPosition() + leftrearencoder.getPosition()) / 2);
    }

    public void forward() {
      leftfrontmotor.set(-0.1);
      leftrearmotor.set(-0.1);
      rightfrontmotor.set(0.1);
      rightrearmotor.set(0.1);
    }

    public void stop() {
      leftfrontmotor.set(0);
      leftrearmotor.set(0);
      rightfrontmotor.set(0);
      rightrearmotor.set(0);
    }
}