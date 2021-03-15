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

    // flag
    boolean forwardFlag;
    boolean backwardFlag;

    // carry out the positions
    double rightfrontencoderPositionForward;
    double leftfrontencoderPositionForward;

    double rightfrontencoderPositionBackward;
    double leftfrontencoderPositionBackward;

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

        this.rightfrontencoderPositionForward = this.rightfrontencoder.getPosition();
        this.leftfrontencoderPositionForward = this.leftfrontencoder.getPosition();

        this.leftfrontencoderPositionBackward = 0;
        this.rightfrontencoderPositionBackward = 0;

        this.forwardFlag = true;
        this.backwardFlag = false;
    }

    public void forwardDrive(double Distance) {
        //while (Distance == Distance) {
            System.out.println("FORWARDDRIVE!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("init left front encoder position : " + leftfrontencoder.getPosition());
            System.out.println("init right front encoder position : " + rightfrontencoder.getPosition());
            System.out.println("init left front position : " + this.leftfrontencoderPositionForward);
            System.out.println("init right front position : " + this.rightfrontencoderPositionForward);
            // start
            
        //if(forwardFlag) {
            // start
            //if (rightfrontencoder.getPosition() == 0 || leftfrontencoder.getPosition() == 0) {
                leftfrontmotor.set(-0.1);
                leftrearmotor.set(-0.1);
                rightfrontmotor.set(0.1);
                rightrearmotor.set(0.1);
                getDistance();
            //}
            // wait
            while (rightfrontencoder.getPosition() < (Distance * 576.3156) && leftfrontencoder.getPosition() < (Distance * 576.3156)) {
                //getEncoderReset();
                getDistance();
                backwardFlag = true;
                forwardFlag = false;
                //assignFrontPosition(leftfrontencoder.getPosition(), rightfrontencoder.getPosition());
            }

            //stop
            leftfrontmotor.set(0);
            leftrearmotor.set(0);
            rightfrontmotor.set(0);
            rightrearmotor.set(0);
            //backwardDrive(1);
        //}
            
        //}
    }

    public void backwardDrive(double Distance) {
        // Values inputted into double Distance is equivalent to feet.

        System.out.println("BACKWARD--------------------------");
       // System.out.println("carry out left front position : " + this.leftfrontencoderPosition);
        //System.out.println("carry out right front position : " + this.rightfrontencoderPosition);
        //System.out.println("carry out left front encoder position encoder : " + leftfrontencoder.getPosition());
        //System.out.println("carry out right front encoder position encoder : " + rightfrontencoder.getPosition());
        
        //getEncoderReset();
        //while (Distance == Distance) {
            //encoderSpark();
        //if(backwardFlag) {
            //if (rightfrontencoder.getPosition() == 0 || leftfrontencoder.getPosition() == 0) {
                // start
                leftfrontmotor.set(0.1);
                leftrearmotor.set(0.1);
                rightfrontmotor.set(-0.1);
                rightrearmotor.set(-0.1);
                System.out.println("BACKWARD--------------------------DONE!!!!!!");

                getDistance();
            //}
            // wait
            /*while (rightfrontencoder.getPosition() > (Distance * -576.3156) && leftfrontencoder.getPosition() > (Distance * -576.3156)) {
                
                //getEncoderReset();
                getDistance();
                backwardFlag = false;
                forwardFlag = true;
                //assignFrontPosition(leftfrontencoder.getPosition(), rightfrontencoder.getPosition());

            }*/

            //stop
            //leftfrontmotor.set(0);
            //leftrearmotor.set(0);
            //rightfrontmotor.set(0);
            //rightrearmotor.set(0);
        //}
        //}
    }

    public void clockwiseTurn(double Degree) {
        // Values inputted into double Degrees should be divisible by 90.

        //encoderSpark();
        //System.out.println("carry out left front position : " + this.leftfrontencoderPosition);
        //System.out.println("carry out right front position : " + this.rightfrontencoderPosition);
        System.out.println("carry out left front encoder position encoder : " + leftfrontencoder.getPosition());
        System.out.println("carry out right front encoder position encoder : " + rightfrontencoder.getPosition());
        //while (Degree == Degree ) {
            //getEncoderReset();
            // start
            //if (rightfrontencoder.getPosition() == 0 || leftfrontencoder.getPosition() == 0) {
                leftfrontmotor.set(-0.1);
                leftrearmotor.set(-0.1);
                rightfrontmotor.set(-0.1);
                rightrearmotor.set(-0.1);
                getDistance();
            //}
            //wait
            // while condition ???? 
            while (rightfrontencoder.getPosition() < ((Degree / 90) * -340) || leftfrontencoder.getPosition() < ((Degree / 90) * -340)) { 
                
                //getEncoderReset();
                assignFrontPosition(leftfrontencoder.getPosition(), rightfrontencoder.getPosition());
                getDistance();
            }

            // stop
            leftfrontmotor.set(0);
                leftrearmotor.set(0);
                rightfrontmotor.set(0);
                rightrearmotor.set(0);
        //}
    }

    public void counterclockwiseTurn(double Degree) {
        //  Values inputted into double Degrees should be divisible by 90.

        while (Degree == Degree) {
            if (rightfrontencoder.getPosition() == Math.abs(0.1) || leftfrontencoder.getPosition() == Math.abs(0.1)) {
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
        leftfrontencoder.setPosition(0.1);
        leftrearencoder.setPosition(0.1);
        rightfrontencoder.setPosition(0.1);
        rightrearencoder.setPosition(0.1);
    }

    private void assignFrontPosition(double leftFrontPosition, double rightFrontPosition) {
        this.rightfrontencoderPositionForward = rightFrontPosition;
        this.leftfrontencoderPositionForward = leftFrontPosition;
    }
}
