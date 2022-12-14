package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.StallCommand;
import frc.robot.commands.Driving.DriveCommand;
import frc.robot.commands.Driving.MoveToRelative;
import frc.robot.commands.Driving.MoveToRelative.EndingRotation;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class RobotContainer {

    private final Joystick joystick;
    private final DriveSubsystem driveSubsystem;
    private final ElevatorSubsystem elevatorSubsystem;
    private final IntakeSubsystem intakeSubsystem;

    public RobotContainer()  {
        // create joystick, drive, elevator and intake subsytem
        joystick = new Joystick(0);
        driveSubsystem = new DriveSubsystem();
        elevatorSubsystem = new ElevatorSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        driveSubsystem.calibrate();
        elevatorSubsystem.calibrate();
        // create and set default drive and elevator command
        driveSubsystem.setDefaultCommand(new DriveCommand(() -> joystick.getRawAxis(1)*-1, () -> joystick.getRawAxis(2), driveSubsystem)); // default to driving from joystick input
        elevatorSubsystem.setDefaultCommand(new StallCommand(() -> elevatorSubsystem.getTargetPosition(), elevatorSubsystem)); // default to stalling
        CameraServer.startAutomaticCapture(); // start camera
        initButtons();
    }

    public void initButtons() {
        new JoystickButton(joystick, 3).whileHeld(new ElevatorCommand(-.3, elevatorSubsystem)); // elevator down
        new JoystickButton(joystick, 4).whileHeld(new ElevatorCommand(.5, elevatorSubsystem)); // elevator up
        new JoystickButton(joystick, 5).whileHeld(new IntakeCommand(.5, intakeSubsystem)); // intake in
        new JoystickButton(joystick, 6).whileHeld(new IntakeCommand(-.5, intakeSubsystem)); // intake out
    }

    public Command getAutoCommand() {
        return new SequentialCommandGroup(
            new MoveToRelative(3, 2, driveSubsystem, EndingRotation.LEFT),
            new IntakeCommand(.5, intakeSubsystem, 3)
        );
    }

}