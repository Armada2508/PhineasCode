package frc.robot.commands.Driving;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

public class MoveToRelative extends CommandBase {

    private double targetX; // in feet, pos is right, negative is left
    private double targetY; // in feet, pos is forwards, negative is back
    private DriveSubsystem subsystem;
    private SequentialCommandGroup group;

    public MoveToRelative(int x, int y, DriveSubsystem subsystem) {
        targetX = x;
        targetY = y;
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        Command command = new AutoTurnCommand(0, subsystem);
        if (targetX > 0) {
            command = new AutoTurnCommand(90, subsystem);
        }
        else if (targetX < 0) {
            command = new AutoTurnCommand(-90, subsystem);
        }
        group = new SequentialCommandGroup(
            new AutoDriveCommand(targetY, subsystem),
            command,
            new AutoDriveCommand(targetX, subsystem)
        );
        group.schedule();
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return (group.isFinished()) ? true : false;
    }
}