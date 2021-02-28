package frc.robot.autoarmsubsystems;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.autodrivesubsystems.Action;
import frc.robot.subsystems.Arm;


public class ArmUp implements Action {

    @Override
    public boolean isComplete() {
        Arm.set(0);
        return true;
    }

    @Override
    public void start() {
        armUp();
    }

    private void armUp() {
        Timer Timer = new Timer();
        Timer.start();  
        Arm.set(-.3);
        edu.wpi.first.wpilibj.Timer.delay(2);
        }

    @Override
    public int status() {
        return 0;
    }

}