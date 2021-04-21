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
    }

    public class Robot {  // File Robot.java
        public static final double target = 2;
        public static final double nulltarget = 0;
        public class Encoders {
            public static final double wheeldiameter = 19; // 19 inches.
            public static final double ticksperrotation = 365; // Where ticks are the unit of measurement output by SparkMax Encoders.
            // 363.881103515625  Is one Rotation.
            // 242.5 Is 90 Degrees.
            // 360 is 90 degrees
            public static final double ticksper90degree = 300;
            public static final double block = ((30/19) * 365);
        }
        
        public class Arm {
            public static final double raisevelocity = 0.45;
            public static final double lowervelocity = -0.3;  // Needs Testing.

            public static final double intakevelocity = 0.3;
            public static final double extakevelocity = -0.2;

            public static final double zero = 0.0;
            public static final double holdvelocity = -0.2;
        }
        
        public class InternalIntake {
            public static final double upstreamvelocity = 0.5;
            public static final double firevelocity = 0.5;
        }
        
        public class HighShooter {
            // Velocities for Fly Wheel Shooter (Larger is faster).
            public static final double profileA = 0.3;
            public static final double profileB = 0.5;
            public static final double profileC = 0.7;
            public static final double profileD = 0.9;
        }
        
    }

    public class Autonomous {  // All files with folders autoarmsubsystems and autodrivesubsystems
        public static final double lowvelocity = 2;
        public static final double mediumvelocity = 4;
        public static final double highvelocity = 10;

    }

    public class Miscellaneous {
        // Ctrl + Click these:
        public static final String GalacticSearchPathA = "https://drive.google.com/file/d/1MqgVkRMYJU1uSFocNPDPhAya1UzYpbfO/view?usp=sharing";
        public static final String GalacticSearchPathB = "https://drive.google.com/file/d/1G7EiDR72gUnLWYOTLIeRuydL8sMISSXr/view?usp=sharing";
        public static final String AutoNavBarrelRacePath = "https://drive.google.com/file/d/1X9xwL_okj-gKlCmrSjrOGJVX9gVblS8N/view?usp=sharing";
        public static final String AutoNavBouncePath = "https://drive.google.com/file/d/1FxbwO75Iy6Jo_N9NVvj1wMnp7mkxT6Ql/view?usp=sharing";
        public static final String AutoNavSlalomPath = "https://drive.google.com/file/d/1yMqzmcAT7viHvbSk-a2Ho7NP8HiCow0O/view?usp=sharing";
    }
}