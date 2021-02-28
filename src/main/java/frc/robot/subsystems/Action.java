package frc.robot.autosubsystems;

public interface Action {

    boolean isComplete();

    void start();

    int status();
    
}