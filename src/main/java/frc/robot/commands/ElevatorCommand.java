package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorCommand extends CommandBase {

    private ElevatorSubsystem subsystem;
    
    private double power = 0;

    public ElevatorCommand(double power, ElevatorSubsystem subsystem) {
        this.power = power;
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {
        subsystem.setPower(power);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.setPower(0);
        subsystem.setTargetPosition(subsystem.getSensorPosition());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
