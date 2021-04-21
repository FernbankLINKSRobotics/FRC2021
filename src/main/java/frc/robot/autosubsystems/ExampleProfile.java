package frc.robot.autosubsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Constants;
import frc.robot.subsystems.DriveBase;


public class ExampleProfile {
    /* ----------------------------------------------------------- */
    // Autonomous Profile (Modifiable):
    public void Profile() {
        /* Don't Edit */ Timer Timer = new Timer();
        /* Don't Edit */ Timer.start();

        // Enter Mission Instructions (Start):

        /* 
         * (You can delete this!) This is where you should put references to the Timer, 
         * such as "Timer.delay(x);" or some reference to a method, such as 
         * "ClassName.methodName(datatype parameter);"
        */

        // Enter Mission Instructions (FInish):
        
        /* Don't Edit */ Timer.stop();
    }

    /* ----------------------------------------------------------- */
    // Requirements (Do not edit):

    public frc.robot.autosubsystems.AutoCommands AutoCommands;
    public frc.robot.subsystems.DriveBase DriveBase;
    public AutoCorrect AutoCorrect;

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

    public ExampleProfile() {
        // Prerequisites (Motor Defining and Class Calling):

        this.leftfrontmotor = DriveBase().leftfrontmotor;
        this.leftrearmotor = DriveBase().leftrearmotor;
        this.rightfrontmotor = DriveBase().rightfrontmotor;
        this.rightrearmotor = DriveBase().rightrearmotor;

        this.Arm = Arm().Arm;
        this.Mouth = Arm().Mouth;
        this.InternalIntake = Arm().InternalIntake;
        this.ConveyorBelt = Arm().ConveyorBelt;
        this.ShooterA = Arm().ShooterA;
        this.ShooterB = Arm().ShooterB;

        this.leftfrontencoder = DriveBase().leftfrontmotor.getEncoder();    
        this.leftrearencoder = DriveBase().leftrearmotor.getEncoder();    
        this.rightfrontencoder = DriveBase().rightfrontmotor.getEncoder();    
        this.rightrearencoder = DriveBase().rightrearmotor.getEncoder();

        this.AutoCorrect = new AutoCorrect();
        
        this.AutoCommands = new AutoCommands();
        this.DriveBase = new DriveBase(1, 2, 3, 4);
    }

    public static frc.robot.subsystems.DriveBase DriveBase() {
        return new frc.robot.subsystems.DriveBase(1, 2, 3, 4);
    }

    public static frc.robot.subsystems.Arm Arm() {
        return new frc.robot.subsystems.Arm();
    }

    public void timerDelay(double time) {
        // The purpose for this method is because it looks cleaner.
        edu.wpi.first.wpilibj.Timer.delay(time);
    }
    /* ----------------------------------------------------------- */
}