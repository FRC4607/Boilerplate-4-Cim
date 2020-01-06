package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class ElevatorJoystick extends Command {

  public ElevatorJoystick() {
    requires(Robot.mElevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // Get the joystick inputs
    double xElevator = -OI.operatorJoystick.getRawAxis(5);

    // if (xElevator < 0.05 && xElevator > -0.05) {
    //   xElevator = 0.0;
    // }

    Robot.mElevator.OpenLoop(xElevator);
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
