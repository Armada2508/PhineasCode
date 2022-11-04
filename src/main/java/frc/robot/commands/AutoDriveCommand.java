package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoDriveCommand extends CommandBase {

    double distance = 0;
    DriveSubsystem subsystem;

    /**
     * Drives the robot to the relative distance at the power defined 
     * @param distance distance to travel in feet
    */
    public AutoDriveCommand(double distance, DriveSubsystem subsystem) {
        this.distance = distance;
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        subsystem.setRelativePosition(distance);
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
        if (currentPos >= targetpos-250 && currentPos <= targetpos+250) {
            return true;
        }
        return false;    
    }
    
}
