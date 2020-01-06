package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Panel;
import frc.robot.subsystems.Wrist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  public static Drivetrain mDrivetrain = new Drivetrain();
  public static Elevator mElevator = new Elevator();
  public static Cargo mCargo = new Cargo();
  public static Wrist mWrist = new Wrist();
  public static OI mOI = new OI();
  public static Panel mPanel = new Panel();
  private final Logger mLogger = LoggerFactory.getLogger(Robot.class);

  @Override
  public void robotInit() {
    // Create the slave motors and brake mode of the Drivetrain
    Drivetrain.initDefaultSetup();
    Elevator.initDefaultSetup();
    Panel.initDefaultSetup();
    Wrist.initDefaultSetup();
    // mDrivetrain.SelfTest();
  }
  
  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    mLogger.info("Teleop Start");
    CameraServer.getInstance().startAutomaticCapture();
  }
  

  // @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
