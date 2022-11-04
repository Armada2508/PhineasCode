package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Drive;
import frc.robot.Lib.Encoder;
import frc.robot.Lib.util.Util;


public class DriveSubsystem extends SubsystemBase {
    private WPI_TalonSRX TalonL; 
    private WPI_TalonSRX TalonFL; 
    private WPI_TalonSRX TalonR; 
    private WPI_TalonSRX TalonFR; 
    private final DifferentialDriveKinematics mKinematics = new DifferentialDriveKinematics(Drive.kTrackWidth); 

    public DriveSubsystem(){
        TalonL = new WPI_TalonSRX(Drive.LID); 
        TalonFL = new WPI_TalonSRX(Drive.LFID);
        TalonFL.follow(TalonL);
        TalonR = new WPI_TalonSRX(Drive.RID);
        TalonFR = new WPI_TalonSRX(Drive.RFID);
        TalonFR.follow(TalonR);
        TalonL.setSensorPhase(true);
        TalonL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        TalonR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        TalonL.config_kP(0, .25);
        TalonR.config_kP(0, .25);
        TalonL.configClosedLoopPeakOutput(0, .2);
        TalonR.configClosedLoopPeakOutput(0, .2);
    }

    @Override
    public void periodic() {
    }

    public void setPower(double rightPower, double leftPower){
        TalonL.set(leftPower);
        TalonR.set(rightPower);
    }

    public void setRelativePosition(double pos) { // Sets relative position in feet
        double ePos = Encoder.fromDistance(pos, Drive.encoderUnits, Drive.gearboxRatio, Drive.diameter);
        TalonL.set(TalonSRXControlMode.Position, ePos+TalonL.getSelectedSensorPosition());
        TalonR.set(TalonSRXControlMode.Position, ePos+TalonR.getSelectedSensorPosition());
    }
    
    public double getleftPostition(){
        return Encoder.toDistance(TalonL.getSelectedSensorPosition(), Drive.encoderUnits, Drive.gearboxRatio, Drive.diameter); 
        
    }

    public double getRightPostition(){
        return Encoder.toDistance(TalonR.getSelectedSensorPosition(), Drive.encoderUnits, Drive.gearboxRatio, Drive.diameter); 
        
    }

    public double getLeftSensorPostition(){
        return TalonL.getSelectedSensorPosition(); 
    }

    public double getLeftSensorTarget() {
        return TalonL.getClosedLoopTarget();
    }

    public void brake(){
        TalonL.setNeutralMode(NeutralMode.Brake);
        TalonFL.setNeutralMode(NeutralMode.Brake);
        TalonR.setNeutralMode(NeutralMode.Brake);
        TalonFR.setNeutralMode(NeutralMode.Brake);
    }

    public void calibrate() {
        TalonL.setSelectedSensorPosition(0);
        TalonR.setSelectedSensorPosition(0);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(getVelocityLeft(), getVelocityRight());
    }

    public void setVelocity(DifferentialDriveWheelSpeeds speeds) {
        TalonR.set(ControlMode.Velocity, fromVelocity(speeds.rightMetersPerSecond));
        TalonL.set(ControlMode.Velocity, fromVelocity(speeds.leftMetersPerSecond));
    }

    public void setVelocity(DifferentialDriveWheelSpeeds speeds, double deadband) {
        if(Util.inRange(speeds.rightMetersPerSecond, deadband)) {
            TalonR.set(ControlMode.PercentOutput, 0);
        } else {
            TalonR.set(ControlMode.Velocity, fromVelocity(speeds.rightMetersPerSecond));
        }
        if(Util.inRange(speeds.leftMetersPerSecond, deadband)) {
            TalonL.set(ControlMode.PercentOutput, 0);
        } else {
            TalonL.set(ControlMode.Velocity, fromVelocity(speeds.rightMetersPerSecond));
        }
    }

    public void setVoltage(double voltsL, double voltsR) {
        TalonL.setVoltage(voltsL);
        TalonR.setVoltage(voltsR);
    }
    //Maybe change bc it's only checking one motor on the right
    public double getVelocityRight() {
        return toVelocity((int)TalonR.getSelectedSensorVelocity());
    }

    //Maybe change bc it's only checking one motor on the left
    public double getVelocityLeft() {
        return toVelocity((int)TalonL.getSelectedSensorVelocity());
    }

    public double getVelocity() {
        return mKinematics.toChassisSpeeds(getWheelSpeeds()).vxMetersPerSecond;
    }

    public double toVelocity(int velocity) {
        return Encoder.toVelocity(velocity, Drive.kFeedbackConfig.getEpr(), Drive.kFeedbackConfig.getGearRatio(), Drive.diameter);
    }

    public double fromVelocity(double velocity) {
        return Encoder.fromVelocity(velocity, Drive.kFeedbackConfig.getEpr(), Drive.kFeedbackConfig.getGearRatio(), Drive.diameter);
    }

}
