package frc.robot.commands.Driving;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class AutoTurnCommand extends CommandBase {

    double targetDegrees = 0;
    int turningConstant = 54; // trial and error found this number
    double targetpos;
    DriveSubsystem subsystem;

    /**
     * Turns the robot to the relative angle 
     * @param degrees degrees to turn
    */
    public AutoTurnCommand(double degrees, DriveSubsystem subsystem) {
        if (degrees == 0) cancel();
        targetDegrees = degrees;
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        targetpos = subsystem.getLeftSensorPostition() + targetDegrees*turningConstant;
        if (targetDegrees > 0) {
            subsystem.setPower(-.25, .25);
        } else {
            subsystem.setPower(.25, -.25);
        }
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
        System.out.println(currentPos + " " + targetpos);
        if (currentPos >= targetpos-2*turningConstant && currentPos <= targetpos+2*turningConstant) {
            return true;
        }
        return false;
    }
    
}
