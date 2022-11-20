package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {

    private double power = 0;
    private double currentTime = 0; // in seconds
    private double autoTime = 0; // in seconds
    private IntakeSubsystem subsystem;

    /**
     * Runs the intake motors until interrupted
     * @param power The power to drive the motors at between -1 and 1
     * @param subsystem intake subsystem
     */
    public IntakeCommand(double power, IntakeSubsystem subsystem) {
        this(power, subsystem, 0);
    }

    /**
     * Runs the intake motors for the specified amount of time
     * @param power The poewr to drive the motors at between -1 and 1
     * @param subsystem intake subsystem
     * @param time The time to run the intake for in seconds
     */
    public IntakeCommand(double power, IntakeSubsystem subsystem, double time) {
        this.power = power;
        this.subsystem = subsystem;
        autoTime = time;
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {
        subsystem.setPower(power);
    }

    @Override
    public void execute() {
        currentTime += 0.020;
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.setPower(0);
    }

    @Override
    public boolean isFinished() {
        if (autoTime != 0 && currentTime >= autoTime) return true;
        return false;
    }
    
}
