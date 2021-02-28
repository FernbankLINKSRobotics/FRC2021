package frc.robot.subsystems;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class DriveTurnLeft implements Action {

    private double degrees;

    private boolean complete = false;

    /*
     *-1: Task failed
     *0: Task not started
     *1: Task in progress
     *2: Task complete
     */
    
    private int status = 0;

    public DriveTurnLeft(double target) {
        degrees = target;
    }

    public boolean isComplete() {
        return complete;
    }

    public void start() {
        status = 1;

        Timer Timer = new Timer();
        Timer.start();
        edu.wpi.first.wpilibj.Timer.delay(1);

        Robot.base.reset();
        Robot.base.setDistance(degrees);
        Robot.base.driveTurnLeft();

        int counter = 0;

        while (!Robot.base.driveOnTarget()) {
            counter++;
            Robot.base.driveTurnLeft();
            if (counter == 10000) {
                status = -1;
                return;
            }

            continue;
        }

        complete = true;
        status = 2;
    }

    public int status() {
        return status;
    }
}