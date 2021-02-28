package frc.robot.subsystems;

public interface Action {

    boolean isComplete();

    void start();

    int status();
    
}