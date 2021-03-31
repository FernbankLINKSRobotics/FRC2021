package frc.robot.autosubsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Constants;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Constants;


public class AutoProfiles {

    double standardSpeed = 0.1;

    public void GLPathA() {
        // Start at E1, parallel to the vertical line, bumpers facing row 11.
        String GalacticSearchPathA = Constants.Miscellaneous.GalacticSearchPathA; // <- Ctrl + Click Variable.
        double time = 3;

        Timer Timer = new Timer();
        Timer.start();

        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0.2);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0.2);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(3, standardSpeed, 0.2);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2.5, standardSpeed, 0);

        Timer.stop();
    }

    public void GLPathB() {
        // Start at D1, parallel to the vertical line, bumpers facing row 11.
        String GalacticSearchPathB = Constants.Miscellaneous.GalacticSearchPathB; // <- Ctrl + Click Variable.
        double time = 3;

        Timer Timer = new Timer();
        Timer.start();

        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0.2);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0.2);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2.5, standardSpeed, 0);

        Timer.stop();
    }

    public void ANBouncePath() {
        // Start at C10, parallel to the vertical line, bumpers facing row 1.
        String AutoNavBouncePath = Constants.Miscellaneous.AutoNavBouncePath; // <- Ctrl + Click Variable.
        double time = 3;

        Timer Timer = new Timer();
        Timer.start();

        AutoCommands.forward(2.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);        
        timerDelay(time);
        AutoCommands.forward(3, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(180, standardSpeed);       
        timerDelay(time);
        AutoCommands.forward(3, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);        
        timerDelay(time);
        AutoCommands.forward(0.5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(180, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(180, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);   
        timerDelay(time);
        AutoCommands.forward(3, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(180, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);

        Timer.stop();
    }

    public void ANBarrelRacing() {
        // Start at D1, parallel to the vertical line, bumpers facing row 11.
        String AutoNavBarrelRacePath = Constants.Miscellaneous.AutoNavBarrelRacePath; // <- Ctrl + Click Variable.
        double time = 3;

        Timer Timer = new Timer();
        Timer.start();

        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(5, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(4, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(10, standardSpeed, 0);
        timerDelay(time);

        Timer.stop();
    }

    public void ANSlalomPath() {
        // Start at E11, parallel to the vertical line, bumpers facing row 1.
        String AutoNavSlalomPath = Constants.Miscellaneous.AutoNavSlalomPath; // <- Ctrl + Click Variable.
        double time = 3;

        Timer Timer = new Timer();
        Timer.start();

        AutoCommands.forward(1, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(6, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);  
        AutoCommands.forward(6, standardSpeed, 0);
        timerDelay(time);
        AutoCommands.leftTurn(90, standardSpeed);
        timerDelay(time);  
        AutoCommands.forward(2, standardSpeed, 0);
        timerDelay(time);    
        AutoCommands.rightTurn(90, standardSpeed);
        timerDelay(time);
        AutoCommands.forward(1.5, standardSpeed, 0);
        timerDelay(time);   

        Timer.stop();
    }




    public void exampleMission() {
        /* Don't Edit */ Timer Timer = new Timer();
        /* Don't Edit */ Timer.start();

        // Enter Mission Instructions (Start):

        // This is where you should put references to the Timer, such as "Timer.delay(x);" or some reference to a method, such as "ClassName.methodName(datatype parameter);"

        // Enter Mission Instructions (FInish):
        
        /* Don't Edit */ Timer.stop();
    }
    
/*---------------------------------------------------------------------*/
    // Requirements (Do not edit):

    public ExampleEncoderActions ExampleEncoderActions;
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
        
        this.AutoCommands = new AutoCommands();
        this.ExampleEncoderActions = new ExampleEncoderActions();
        this.DriveBase = new DriveBase(1, 2, 3, 4);
    }
    
    public static frc.robot.subsystems.DriveBase DriveBase() {
        return new frc.robot.subsystems.DriveBase(1, 2, 3, 4);
    }

    public void timerDelay(double time) {
        // The purpose for this method is because  it looks cleaner.
        edu.wpi.first.wpilibj.Timer.delay(time);
    }
    
/*---------------------------------------------------------------------*/
}