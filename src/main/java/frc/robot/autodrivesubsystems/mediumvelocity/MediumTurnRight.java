package frc.robot.autodrivesubsystems.mediumvelocity;

import frc.robot.Robot;
import frc.robot.autodrivesubsystems.*;
import frc.robot.subsystems.Constants;

public class MediumTurnRight implements Action {

    private boolean complete = false;

    /*
     *-1: Task failed
     *0: Task not started
     *1: Task in progress
     *2: Task complete
     */
    
    private int status = 0;

    public MediumTurnRight(double target) {
    }

    public boolean isComplete() {
        return complete;
    }

    public void start() {
        status = 1;

        Robot.base.reset();
        Robot.base.setDistance(Constants.Autonomous.mediumvelocity);
        Robot.base.driveTurnRight();

        while (!Robot.base.driveOnTarget()) {
            Robot.base.driveTurnRight();
        }

        complete = true;
        status = 2;
    }

    public int status() {
        return status;
    }
}