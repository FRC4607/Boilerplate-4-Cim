package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.OI;

public class DriveJoystick extends Command {

  public DriveJoystick() {
    requires(Robot.mDrivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // Get the joystick inputs
    double xSpeed = OI.driverJoystick.getY();
    double zTurn = (OI.driverJoystick.getX() * RobotMap.kDriverZJoystickGain);

    // Apply a joystick deadband;
    if (xSpeed < RobotMap.kJoystickDeadband && xSpeed > -RobotMap.kJoystickDeadband) {
      xSpeed = 0.0;
    }
    if (zTurn < RobotMap.kJoystickDeadband && zTurn > -RobotMap.kJoystickDeadband) {
      zTurn = 0.0;
    }
  
    Robot.mDrivetrain.setDrivetrainMove(xSpeed, zTurn);

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
