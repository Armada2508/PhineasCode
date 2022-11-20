package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class StallCommand extends CommandBase {

    private DoubleSupplier targetSupplier;
    private ElevatorSubsystem subsystem;
   
    public StallCommand(DoubleSupplier targetSupplier, ElevatorSubsystem subsystem) {
        this.subsystem = subsystem;
        this.targetSupplier = targetSupplier;
        addRequirements(subsystem);
        System.out.println("Constructed");
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        // System.out.println(stall());
        // System.out.println("Current " + subsystem.getSensorPosition() + " \nTarget " + targetSupplier.getAsDouble());
        if (stall()) {
            // System.out.println("START STALLING SHITTER");
            subsystem.setPosition(targetSupplier.getAsDouble());
        } else {
            // System.out.println("STOP STALLING SHITTER");
            subsystem.setPower(0);
        }
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private boolean stall() {
        int range = 8; 
        double currentPos = subsystem.getSensorPosition();
        double targetPos = targetSupplier.getAsDouble(); 
        if (currentPos <= targetPos + range && currentPos >= targetPos - range) {
            return false;
        }   
        return true;
    }
    
}
