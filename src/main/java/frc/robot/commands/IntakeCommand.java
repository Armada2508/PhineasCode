package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

    private IntakeSubsystem subsystem;
    private double power = 0;

    public IntakeCommand(double power, IntakeSubsystem subsystem) {
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
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
