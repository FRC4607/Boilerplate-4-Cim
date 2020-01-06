package frc.robot.commands.Wrist;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WristJoystick extends Command {

  private final Logger mLogger = LoggerFactory.getLogger(WristJoystick.class);

  public WristJoystick() {
    requires(Robot.mWrist);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    // Get the joystick inputs
    double xWrist = (OI.operatorJoystick.getY() * 0.5);

    if (xWrist < 0.05 && xWrist > -0.05) {
      xWrist = 0.0;
    }

    if (xWrist > 0.5) {
        mLogger.info("Positive input: {}", xWrist);
    } else if (xWrist < -0.2) {
        mLogger.info("Negative Input: {}", xWrist);
    }

    Robot.mWrist.OpenLoop(xWrist);
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
