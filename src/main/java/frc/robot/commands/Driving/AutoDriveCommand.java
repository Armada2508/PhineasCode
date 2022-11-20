package frc.robot.commands.Driving;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDriveCommand extends CommandBase {

    private double targetDistance = 0;
    private DriveSubsystem subsystem;

    /**
     * Drives the robot to the relative distance at the power defined 
     * @param distance distance to travel in feet, positive is forward, negative is backwards
    */
    public AutoDriveCommand(double distance, DriveSubsystem subsystem) {
        if (distance == 0) cancel();
        targetDistance = distance;
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.setRelativePosition(targetDistance);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.setPower(0, 0);
    }

    @Override
    public boolean isFinished() {
        double currentPos = subsystem.getLeftSensorPostition();
        double targetpos = subsystem.getLeftSensorTarget();
        if (currentPos >= targetpos) {
            return true;
        }
        return false;    
    }
    
}
