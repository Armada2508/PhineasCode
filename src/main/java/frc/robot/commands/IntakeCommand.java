package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

    private IntakeSubsystem subsystem;
    private double power = 0;
    private boolean auto = false;
    private double time = 0; // in seconds

    public IntakeCommand(double power, IntakeSubsystem subsystem, boolean auto) {
        this.power = power;
        this.subsystem = subsystem;
        this.auto = auto;
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {
        subsystem.setPower(power);
    }

    @Override
    public void execute() {
        time += 0.020;
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.setPower(0);
    }

    @Override
    public boolean isFinished() {
        if (auto && time > 2.5) return true;
        return false;
    }
    
}
