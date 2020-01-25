package frc.robot.commands.TransferWheel;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.TransferWheel.TransferWheelJoystick;
import frc.robot.Robot;
// import frc.robot.RobotMap;
import frc.robot.OI;

public class TransferWheelJoystick extends Command {

  public TransferWheelJoystick() {
    requires(Robot.mFlywheel);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // Get the joystick inputs

    double xFlywheelSpin = (OI.mOperatorJoystick.getRawAxis(2));

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
