package frc.robot.subsystems;

public class Constants {
    public class DriveBase { // Moreso referring to the file rather than the concept (since Arm functions are located in DriveBase.java).
        public class Encoders {
            public static final double encoderconversion = 34.19;  // Preset Value
            public static final double zero = 0;
            public static final double testingthrottle = 0.5; // Subject to change.
            public static final double nullthrottle = 0;
        }

        public class Controllers {
            public static final double zero = 0;
            public static final double turntollerance = 2;
            public static final double drivetollerance = 5;
            public static final double balltollerance = 0.25;
            public static final double balldrive = 10;
            public static final double balldrivetollerance = 0.5;
        }

        public class MotorControllers {
            public static final double openramprate = 0.5;
        }

        public class Arm {
            public static final double raisevelocity = -0.4;
            public static final double lowervelocity = 0.25;
            public static final double intakevelocity = 0.3;
            public static final double extakevelocity = -0.3;
            public static final double zero = 0.0;
            public static final double holdvelocity = -0.2;
        }
    }

    public class Robot {  // File Robot.java
        public static final double target = 2;
        public static final double nulltarget = 0;
        public class Encoders {
            public static final double wheeldiameter = 6; // 6 inches.
            public static final double ticksperrotation = 360; // Where ticks are the unit of measurement output by SparkMax Encoders.
        }
    }

    public class Autonomous {  // All files with folders autoarmsubsystems and autodrivesubsystems
        public static final double lowvelocity = 2;
        public static final double mediumvelocity = 4;
        public static final double highvelocity = 10;

    }
}