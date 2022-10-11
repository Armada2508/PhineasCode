package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Elevator;

public class ElevatorSubsystem extends SubsystemBase {
    
    private static WPI_TalonSRX talonSRX1;
    private static WPI_TalonSRX talonSRX2;
    private static final int pidIdx = 0;
	private static final int driveSystemTimeoutMs = 0;
    private double targetPos;

    public ElevatorSubsystem() {
        talonSRX1 = new WPI_TalonSRX(5);
        talonSRX2 = new WPI_TalonSRX(6);
        talonSRX1.setInverted(true);
        talonSRX2.setInverted(true);
        talonSRX1.setSensorPhase(true);
        talonSRX2.setSensorPhase(true);
        talonSRX2.set(ControlMode.Follower, 0);
        talonSRX2.follow(talonSRX1);

        talonSRX1.setNeutralMode(NeutralMode.Brake);
        talonSRX2.setNeutralMode(NeutralMode.Brake);
        talonSRX1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidIdx, driveSystemTimeoutMs);
        talonSRX1.config_kP(0, Elevator.kP, driveSystemTimeoutMs);
		talonSRX1.config_kI(0, Elevator.kI, driveSystemTimeoutMs); 
		talonSRX1.config_kD(0, Elevator.kD, driveSystemTimeoutMs);
		talonSRX1.config_kF(0, Elevator.kF, driveSystemTimeoutMs);
        talonSRX1.configClosedLoopPeakOutput(0, .1, driveSystemTimeoutMs);
        talonSRX2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, pidIdx, driveSystemTimeoutMs);
        talonSRX2.config_kP(0, Elevator.kP, driveSystemTimeoutMs);
		talonSRX2.config_kI(0, Elevator.kI, driveSystemTimeoutMs); 
		talonSRX2.config_kD(0, Elevator.kD, driveSystemTimeoutMs);
		talonSRX2.config_kF(0, Elevator.kF, driveSystemTimeoutMs);
        talonSRX2.configClosedLoopPeakOutput(0, .1, driveSystemTimeoutMs);
        targetPos = 0;
        calibrate();
        // talonSRX1.config_IntegralZone(0, 200, 10);
    }

    public void setPower(double power) {
        talonSRX1.set(TalonSRXControlMode.PercentOutput, power);
        // talonSRX2.set(TalonSRXControlMode.PercentOutput, power);
    }

    // set motor to go to a position
    public void setPosition(double pos) {
        talonSRX1.set(TalonSRXControlMode.Position, pos);
        // talonSRX2.set(TalonSRXControlMode.Position, pos);
    }

    public void calibrate() {
        talonSRX1.setSelectedSensorPosition(0);
    }

    public double getSensorPosition() {
        return talonSRX1.getSelectedSensorPosition();
    }

    public double getTargetPosition() {
        return targetPos;
    }

    public void setTargetPosition(double pos) {
        targetPos = pos;
    }

}
