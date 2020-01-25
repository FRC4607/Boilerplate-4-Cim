package frc.robot.commands.Flywheel;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.Flywheel.FlywheelJoystick;
import frc.robot.Robot;
// import frc.robot.RobotMap;
import frc.robot.OI;

public class FlywheelJoystick extends Command {

  public FlywheelJoystick() {
    requires(Robot.mFlywheel);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // Get the joystick inputs

    double xFlywheelSpin = (OI.mOperatorJoystick.getRawAxis(0));

    Robot.mFlywheel.setOpenLoopOutput(xFlywheelSpin);

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
