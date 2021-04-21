package frc.robot.autosubsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Constants;
import frc.robot.subsystems.DriveBase;


public class AutoProfiles {

    double standardSpeed = 0.15;

/* -------------------------------------------------------------------------------------------------------- */

    public void GLPathA() {
        // Start at E1, parallel to the vertical line, bumpers facing row 11.
        String GalacticSearchPathA = Constants.Miscellaneous.GalacticSearchPathA; // <- Ctrl/Command + Click Variable.
        double time = 0.5;

        Timer Timer = new Timer();
        Timer.start();

        AutoCommands.forward(4, 0.2, 0);
        timerDelay(0.75);
        AutoCommands.forward(2, standardSpeed, 0.35);
        timerDelay(1.5);
        AutoCommands.leftTurn(87.5, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(0.25);
        AutoCommands.forward(1, standardSpeed, 0.3);
        timerDelay(1.5);
        AutoCommands.rightTurn(105, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.forward(1.5, standardSpeed, .3);
        timerDelay(time);
        AutoCommands.leftTurn(25, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1, standardSpeed, 0);

        Timer.stop();
    }
/* -------------------------------------------------------------------------------------------------------- */

    public void GLPathB() {
        // Start at D1, parallel to the vertical line, bumpers facing row 11.
        String GalacticSearchPathB = Constants.Miscellaneous.GalacticSearchPathB; // <- Ctrl/Command + Click Variable.
        double time = 0.5;

        Timer Timer = new Timer();
        Timer.start();
        
        AutoCommands.forward(3.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(22.5, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1.25, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.forward(1.75, standardSpeed, 0.3);
        timerDelay(0.5);
        Arm.set(0.45);
        Mouth.set(0.3);
        timerDelay(2);
        Arm.set(-0.2);
        timerDelay(0.5);
        AutoCommands.leftTurn(32.5, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.forward(1, standardSpeed, 0.3);
        timerDelay(0.5);
        Arm.set(0.45);
        Mouth.set(0.3);
        ConveyorBelt.set(-0.3);
        timerDelay(2);
        Arm.set(-0.2);
        timerDelay(1);
        AutoCommands.rightTurn(115, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.forward(1.25, standardSpeed, 0.30);
        timerDelay(1.5);
        Arm.set(0.45);
        Mouth.set(0.3);
        ConveyorBelt.set(-0.3);
        timerDelay(0.75);
        Arm.set(0.2);
        Mouth.set(0);
        ConveyorBelt.set(0); 
        AutoCommands.leftTurn(150, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(0.5, standardSpeed, 0);

        Timer.stop();
    }

/* -------------------------------------------------------------------------------------------------------- */

    public void ANBouncePath() {
        // Start at C10, parallel to the vertical line, bumpers facing row 1.
        String AutoNavBouncePath = Constants.Miscellaneous.AutoNavBouncePath; // <- Ctrl/Command + Click Variable.
        double time = 0.5; // Same as barrel racing path.
        double standardTurn = 0.15; // Same as barrel racing path.

        Timer Timer = new Timer();
        Timer.start();

        //Start of Section 1
        /*  A  */AutoCommands.forward(1.1, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(100, standardTurn);        
        timerDelay(time);
        /*  B  */AutoCommands.forward(1, standardSpeed, 0);
        /*  B  */AutoCommands.forward(0.25, 0.05, 0);
        timerDelay(time);
        AutoCommands.rightTurn(205, standardTurn);       
        timerDelay(time);
        /*  C  */AutoCommands.forward(1.25, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardTurn);        
        timerDelay(time);
        /*  D  */AutoCommands.forward(1.25, standardSpeed, 0);
        // End of Section 1

        timerDelay(time);

        // Start of Section 2
        AutoCommands.rightTurn(90, standardTurn);
        timerDelay(time);
        /*  E  */AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(95, standardTurn);
        timerDelay(time);
        /*  F  */AutoCommands.forward(1.75, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(93, standardTurn);
        timerDelay(time);
        /*  G  */AutoCommands.forward(2.75, standardSpeed, 0);
        /*  G  */AutoCommands.forward(0.3, 0.05, 0);
        timerDelay(time);
        AutoCommands.leftTurn(175, 0.35);
        timerDelay(time);
        /*  H  */AutoCommands.forward(3, standardSpeed, 0);
        // End of Section 2

        timerDelay(time);

        // Start of Section 3
        AutoCommands.leftTurn(95, standardTurn);   
        timerDelay(time);
        /*  I  */AutoCommands.forward(2.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(95, standardTurn);
        timerDelay(time);
        /*  J  */AutoCommands.forward(3.35, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(200, standardTurn);
        timerDelay(time);
        /*  K  */AutoCommands.forward(1.25, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardTurn);
        timerDelay(time);
        /*  L  */AutoCommands.forward(2, standardSpeed, 0);
        // End of Section 3

        Timer.stop();
    }
/* -------------------------------------------------------------------------------------------------------- */

    public void ANSlalomPath() {
        // Start at E11, parallel to the vertical line, bumpers facing row 1.
        String AutoNavSlalomPath = Constants.Miscellaneous.AutoNavSlalomPath; // <- Ctrl/Command + Click Variable.
        double time = 0.5; // Same as barrel racing path.
        double standardTurn = 0.15; // Same as barrel racing path.

        Timer Timer = new Timer();
        Timer.start();
        // Start of Section 1
        /*  A  */AutoCommands.forward(1.5, standardSpeed, 0);  
        timerDelay(time);
        AutoCommands.leftTurn(92.5, standardTurn);
        timerDelay(time);
        /*  B  */AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(97, standardTurn);
        timerDelay(time);
        /*  C  */ AutoCommands.forward(6, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(95, standardTurn);
        // End of Section 1

        timerDelay(time);

        // Start of Section 2
        /*  D  */AutoCommands.forward(1.6, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.leftTurn(95, standardTurn);
        timerDelay(time);
        /*  E  */AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.leftTurn(92.5, standardTurn);
        timerDelay(time);
        /*  F */AutoCommands.forward(1.75, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.leftTurn(92.5, standardTurn);
        timerDelay(time);
        /*  G  */AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.leftTurn(92.5, standardTurn);
        timerDelay(time);
        /*  H  */AutoCommands.forward(1.87, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.rightTurn(82.5, standardTurn);
        // End of Section 2

        timerDelay(time);  

        // Start of Section 3
        /*  I  */AutoCommands.forward(6, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardTurn);
        timerDelay(time);  
        /*  J  */AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.leftTurn(95, standardTurn);
        timerDelay(time);
        /*  K  */AutoCommands.forward(1.25, standardSpeed, 0);
        timerDelay(time);   
        // End of Section 3
        
        Timer.stop();
    }

/* -------------------------------------------------------------------------------------------------------- */

    public void exampleMission() {
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

    
/* --------------------------------------------------------------------- */
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

    public AutoProfiles() {
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
        timerDelay(time);
    }
/* --------------------------------------------------------------------- */
}