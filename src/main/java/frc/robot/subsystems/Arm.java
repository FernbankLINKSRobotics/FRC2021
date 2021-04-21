package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

public class Arm {
    public WPI_TalonSRX Arm = new WPI_TalonSRX(5);
    public WPI_VictorSPX Mouth = new WPI_VictorSPX(6);
    public WPI_VictorSPX InternalIntake = new WPI_VictorSPX(15);
    public WPI_VictorSPX ConveyorBelt = new WPI_VictorSPX(16);
    public WPI_TalonSRX ShooterA = new WPI_TalonSRX(25);
    public WPI_TalonSRX ShooterB = new WPI_TalonSRX(26);
    public Timer Timer = new Timer();

    public void armSystem (XboxController controller, GenericHID.Hand left, GenericHID.Hand right) {
        double lowerVelocity = Constants.Robot.Arm.lowervelocity;
        double intakeVelocity = Constants.Robot.Arm.intakevelocity;
        double upstreamVelocity = Constants.Robot.InternalIntake.upstreamvelocity;
        double raiseVelocity = Constants.Robot.Arm.raisevelocity;
        double extakeVelocity = Constants.Robot.Arm.extakevelocity;


        if (controller.getRawButtonPressed(7 /* This is the tiny button to the left of the home (X) button! */)) {
            Arm.set(lowerVelocity);
        }

        if (controller.getRawButtonReleased(7 /* This is the tiny button to the left of the home (X) button! */)) {
            Arm.set(0);
        }

        if (controller.getRawButtonPressed(8 /* This is the tiny button to the right of the home (X) button! */)) {
            Arm.set(raiseVelocity);
        }

        if (controller.getRawButtonReleased(8 /* This is the tiny button to the right of the home (X) button! */)) {
            Arm.set(0);
        }    

        if (controller.getBumperReleased(left)) {
            Arm.set(0);
        }
        
        if (controller.getBumperPressed(right)) {
            Mouth.set(intakeVelocity);  
            InternalIntake.set(upstreamVelocity);
            ConveyorBelt.set(-upstreamVelocity);
        }

        if (controller.getBumperReleased(right)) {
            Mouth.set(0);  
            InternalIntake.set(0);
            ConveyorBelt.set(0);
        }

        if (controller.getRawButtonPressed(9)) {
            Mouth.set(intakeVelocity);
        }

        if (controller.getRawButtonReleased(9)) {
            Mouth.set(0);
        }

        if (controller.getRawButtonPressed(10)) {
            Mouth.set(extakeVelocity);
        }

        if (controller.getRawButtonReleased(10)) {
            Mouth.set(0);
        }

        if (controller.getBumperPressed(left)) {
            Mouth.set(0);  
            InternalIntake.set(0);
            ConveyorBelt.set(0);
            ShooterA.set(0);
            ShooterB.set(0);
        }
    }
    
    public void highShooter (XboxController controller, GenericHID.Hand left, GenericHID.Hand right) {
        double upstreamVelocity = Constants.Robot.InternalIntake.upstreamvelocity;
        double profileA = Constants.Robot.HighShooter.profileA;
        double profileB = Constants.Robot.HighShooter.profileB;
        double profileC = Constants.Robot.HighShooter.profileC;
        double profileD = Constants.Robot.HighShooter.profileD;

        if (controller.getAButtonPressed()) {
            ConveyorBelt.set(-upstreamVelocity);
            ShooterA.set(profileA);
            ShooterB.set(profileA);
        }

        if (controller.getAButtonReleased()) {
            ConveyorBelt.set(0);
            ShooterA.set(0);
            ShooterB.set(0);
        }

        if (controller.getBButtonPressed()) {
            ConveyorBelt.set(-upstreamVelocity);
            ShooterA.set(profileB);
            ShooterB.set(profileB);
        }

        if (controller.getBButtonReleased()) {
            ConveyorBelt.set(0);
            ShooterA.set(0);
            ShooterB.set(0);
        }
        
        if (controller.getYButtonPressed()) {
            ConveyorBelt.set(-upstreamVelocity);
            ShooterA.set(profileC);
            ShooterB.set(profileC);
        }

        if (controller.getYButtonReleased()) {
            ConveyorBelt.set(0);
            ShooterA.set(0);
            ShooterB.set(0);
        }

        if (controller.getXButtonPressed()) {
            ConveyorBelt.set(-upstreamVelocity);
            ShooterA.set(profileD);
            ShooterB.set(profileD);
        }

        if (controller.getXButtonReleased()) {
            ConveyorBelt.set(0);
            ShooterA.set(0);
            ShooterB.set(0);
        }
    }
}