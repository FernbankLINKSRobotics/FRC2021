package frc.robot.autosubsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

public class EncoderValues {

    public static CANSparkMax leftfrontmotor;
    public static CANSparkMax leftrearmotor;
    public static CANSparkMax rightfrontmotor;
    public static CANSparkMax rightrearmotor;

    public static CANEncoder leftfrontencoder;
    public static CANEncoder leftrearencoder;
    public static CANEncoder rightfrontencoder;
    public static CANEncoder rightrearencoder;

    public static void encoderReading() {
         
        leftfrontencoder = DriveBase().leftfrontmotor.getEncoder();    
        leftrearencoder = DriveBase().leftrearmotor.getEncoder();    
        rightfrontencoder = DriveBase().rightfrontmotor.getEncoder();    
        rightrearencoder = DriveBase().rightrearmotor.getEncoder();
        
        System.out.println("LeftFrontEncoder --> " + -leftfrontencoder.getPosition());
        System.out.println("LeftRearencoder --> " + -leftrearencoder.getPosition());
        System.out.println("RightFrontEncoder --> " + rightfrontencoder.getPosition());
        System.out.println("RighRearEncoder --> " + rightrearencoder.getPosition());
        System.out.println("");
    }
    
    public static frc.robot.subsystems.DriveBase DriveBase() {
        return new frc.robot.subsystems.DriveBase(1, 2, 3, 4);
        }
    }