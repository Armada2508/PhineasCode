package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    
    WPI_TalonSRX talonSRX1;
    WPI_TalonSRX talonSRX2;
    MotorControllerGroup motorGroup;

    public IntakeSubsystem() {
        talonSRX1 = new WPI_TalonSRX(1);
        talonSRX2 = new WPI_TalonSRX(2);
        talonSRX1.setInverted(true);
        motorGroup = new MotorControllerGroup(talonSRX1, talonSRX2);
    }

    public void setPower(double power) {
        motorGroup.set(power);
    }

}
