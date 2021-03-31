package frc.robot.autosubsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import frc.robot.autosubsystems.*;

public class AutoCorrect {
    public CANSparkMax leftfrontmotor;
    public CANSparkMax leftrearmotor;
    public CANSparkMax rightfrontmotor;
    public CANSparkMax rightrearmotor;

    public CANEncoder leftfrontencoder;
    public CANEncoder leftrearencoder;
    public CANEncoder rightfrontencoder;
    public CANEncoder rightrearencoder;

    public AutoCorrect() {
        this.leftfrontmotor = DriveBase().leftfrontmotor;
        this.leftrearmotor = DriveBase().leftrearmotor;
        this.rightfrontmotor = DriveBase().rightfrontmotor;
        this.rightrearmotor = DriveBase().rightrearmotor;

        this.leftfrontencoder = DriveBase().leftfrontmotor.getEncoder();    
        this.leftrearencoder = DriveBase().leftrearmotor.getEncoder();    
        this.rightfrontencoder = DriveBase().rightfrontmotor.getEncoder();    
        this.rightrearencoder = DriveBase().rightrearmotor.getEncoder();
    }

    public static frc.robot.subsystems.DriveBase DriveBase() {
        return new frc.robot.subsystems.DriveBase(1, 2, 3, 4);
    }
    
    public void autoCorrectLeft(double lefttarget) {
        //Assuming Starting Values are greater than 0.
        if(leftfrontencoder.getPosition() > lefttarget) {
            while(leftfrontencoder.getPosition() > lefttarget) {
                leftfrontmotor.set(-0.075);
                leftfrontmotor.set(-0.075);
            }
        }
        
        if(leftfrontencoder.getPosition() < lefttarget) {
            while(leftfrontencoder.getPosition() < lefttarget) {
                leftfrontmotor.set(0.075);  
                leftfrontmotor.set(0.075);
            }
        }
    }
    public void autoCorrectRight(double righttarget) {
        //Assuming Starting Values are greater than 0.
        if(rightfrontencoder.getPosition() > righttarget) {
            while(rightfrontencoder.getPosition() > righttarget) {
                rightfrontmotor.set(-0.075);
                rightrearmotor.set(-0.075);
            }
        }

        if(rightfrontencoder.getPosition() < righttarget) {
            while(rightfrontencoder.getPosition() < righttarget) {
                rightfrontmotor.set(0.075);
                rightrearmotor.set(0.075);
            }
        }
    }
}