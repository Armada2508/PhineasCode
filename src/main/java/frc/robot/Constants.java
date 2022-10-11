package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.Lib.config.FeedbackConfig;
import frc.robot.Lib.util.Util;

public class Constants {

    public static final class Elevator {
        // (1.0 * 1023 / 4096, 0.0001 * 1023 / 4096, 0.00 * 1023 / 4096, 0, 0.0, 2500, 6, 5);
        // PID Constants courtsey of those before me
        public static final double kP = 1.0 * 1023 / 4096;
        public static final double kI = 0.0001 * 1023 / 4096;
        public static final double kD = 0.00 * 1023 / 4096;
        public static final double kF = 0;
    }
    
    public static final class Drive {
        public static final int RID = 3;
        public static final int RFID = 4;
        public static final int LID = 7;
        public static final int LFID = 8;
        public static final int turnAdjustment = 3; 

        public static final double diameter = 6;
        public static final int encoderUnits = 4096;
        public static final double gearboxRatio = 12.75;
        public static final double kTrackWidth = Util.inchesToMeters(23.5);

        public static final FeedbackConfig kFeedbackConfig = new FeedbackConfig(FeedbackDevice.IntegratedSensor, encoderUnits, gearboxRatio);
    }

    // ========================================
    //    Global Motor Controller Constants
    // ========================================
    public static final class MotorController {

        // Status frames are sent over CAN that contain data about the Talon.
        // They are broken up into different pieces of data and the frequency
        // at which they are sent can be changed according to your needs.
        // The period at which their are sent is measured in ms

        public static final int kTalonFrame1Period = 20;  // How often the Talon reports basic info(Limits, limit overrides, faults, control mode, invert)
        public static final int kTalonFrame2Period = 20;  // How often the Talon reports sensor info(Sensor position/velocity, current, sticky faults, profile)
        public static final int kTalonFrame3Period = 160;  // How often the Talon reports non selected quad info(Position/velocity, edges, quad a and b pin, index pin)
        public static final int kTalonFrame4Period = 160;  // How often the Talon reports additional info(Analog position/velocity, temperature, battery voltage, selected feedback sensor)
        public static final int kTalonFrame8Period = 160;  // How often the Talon reports more encoder info(Talon Idx pin, PulseWidthEncoded sensor velocity/position)
        public static final int kTalonFrame10Period = 160;  // How often the Talon reports info on motion magic(Target position, velocity, active trajectory point)
        public static final int kTalonFrame13Period = 160; // How often the Talon reports info on PID(Error, Integral, Derivative)

    }
    
}
