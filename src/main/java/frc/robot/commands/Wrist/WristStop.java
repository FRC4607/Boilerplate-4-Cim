package frc.robot.commands.Wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristStop extends Command {

  public WristStop() {
    requires(Robot.mCargo);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.mWrist.WristStop();
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
