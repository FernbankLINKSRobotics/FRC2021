package frc.robot.autodrivesubsystems;

public interface Action {

    boolean isComplete();

    void start();

    int status();
    
}