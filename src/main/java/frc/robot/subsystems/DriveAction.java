package frc.robot.subsystems;

public interface DriveAction {

    void start();

    // void calibrate(); - Not needed due to initialize() in DriveBase.java

    void stop();

    void log();

    
}