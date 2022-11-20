package frc.robot.commands.Driving;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;

public class MoveToRelative extends CommandBase {

    private double targetX = 0; // in feet, pos is right, negative is left
    private double targetY = 0; // in feet, pos is forwards, negative is back
    private EndingRotation endingRotation = EndingRotation.FORWARD;
    private SequentialCommandGroup group;
    private DriveSubsystem subsystem;

    public enum EndingRotation {
        FORWARD,
        RIGHT,
        LEFT,
        BACKWARD
    }

    /**
     * Moves the robot to a position in 2D space relative to its current position
     * @param x left and right positon in 2d space to move to
     * @param y forward and backward positon in 2d space to move to
     * @param subsystem DriveSubsystem
     */
    public MoveToRelative(int x, int y, DriveSubsystem subsystem) {
       this(x, y, subsystem, EndingRotation.FORWARD);
    }

    /**
     * Moves the robot to a position in 2D space and ending rotation relative to its current position
     * @param x left and right positon in 2d space to move to
     * @param y forward and backward positon in 2d space to move to
     * @param subsystem DriveSubsystem
     * @param rotation the ending rotation that the robot should be at
     */
    public MoveToRelative(int x, int y, DriveSubsystem subsystem, EndingRotation rotation) {
        targetX = x;
        targetY = y;
        endingRotation = rotation;
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        Command command = new AutoTurnCommand(0, subsystem);
        Command end;
        double degreeOffset = 0;
        if (targetX > 0) {
            command = new AutoTurnCommand(90, subsystem);
            degreeOffset = 90;
        }
        else if (targetX < 0) {
            command = new AutoTurnCommand(-90, subsystem);
            degreeOffset = -90;
        }
        switch(endingRotation) {
            default:
            case FORWARD:
                end = new AutoTurnCommand(0-degreeOffset, subsystem);
                break;
            case RIGHT:
                end = new AutoTurnCommand(90-degreeOffset, subsystem);
                break;
            case LEFT:
                end = new AutoTurnCommand(-90-degreeOffset, subsystem);
                break;
            case BACKWARD:
                end = new AutoTurnCommand(180-degreeOffset, subsystem);
                break;
        }
        group = new SequentialCommandGroup(
            new AutoDriveCommand(targetY, subsystem),
            command,
            new AutoDriveCommand(targetX, subsystem),
            end
        );
        group.schedule();
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        group.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return (group.isFinished()) ? true : false;
    }
}