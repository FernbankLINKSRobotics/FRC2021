package frc.robot.autosubsystems;

import frc.robot.Robot;

public class DriveBackwards implements Action {

    private double degrees;

    private boolean complete = false;

    /*
     *-1: Task failed
     *0: Task not started
     *1: Task in progress
     *2: Task complete
     */
    
    private int status = 0;

    public DriveBackwards(double target) {
        degrees = target;
    }

    public boolean isComplete() {
        return complete;
    }

    public void start() {
        status = 1;

        Robot.base.reset();
        Robot.base.setDistance(degrees);
        Robot.base.driveBackwards();

        int counter = 0;

        while (!Robot.base.driveOnTarget()) {
            counter++;
            Robot.base.driveBackwards();
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