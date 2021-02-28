package frc.robot.autodrivesubsystems.lowvelocity;

import frc.robot.Robot;
import frc.robot.autodrivesubsystems.*;
import frc.robot.subsystems.Constants;

public class LowDriveBackwards implements Action {

    private boolean complete = false;

    /*
     *-1: Task failed
     *0: Task not started
     *1: Task in progress
     *2: Task complete
     */
    
    private int status = 0;

    public LowDriveBackwards(double target) {
    }

    public boolean isComplete() {
        return complete;
    }

    public void start() {
        status = 1;

        Robot.base.reset();
        Robot.base.setDistance(Constants.Autonomous.lowvelocity);
        Robot.base.driveBackwards();

        while (!Robot.base.driveOnTarget()) {
            Robot.base.driveBackwards();
        }

        complete = true;
        status = 2;
    }

    public int status() {
        return status;
    }
}