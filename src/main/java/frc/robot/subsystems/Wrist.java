package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.Wrist.WristJoystick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Creates the elevator subsystem
public class Wrist extends Subsystem {

  public enum SystemState {
    Autonomous,
    OpenLoop,
    Testing
  }

  public static WPI_TalonSRX mWristMotor = new WPI_TalonSRX(RobotMap.kWristMotorID);

  private double targetpos;

  // Logger
  private static Logger mLogger = LoggerFactory.getLogger(Wrist.class);

  public static void initDefaultSetup() {
    mWristMotor.setNeutralMode(NeutralMode.Brake);
    mLogger.info("Wrist subsystem created");

    mWristMotor.config_kP(0, 0.0);
    mWristMotor.config_kI(0, 0.0);
    mWristMotor.config_kD(0, 0.0);
    mWristMotor.config_kF(0, 0.0);
  }

  public void WristStop() {
    mWristMotor.set(0.0);
  }

  public void OpenLoop(double xWrist) {
    mWristMotor.set(xWrist);
  }

  public void WristClosedLoop() {
    targetpos = 2000.0;
    mWristMotor.set(ControlMode.MotionMagic, targetpos);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristJoystick());
  }

  // if (mMaster.getControlMode() == ControlMode.MotionMagic) {
  //   mPeriodicIO.active_trajectory_position = mMaster.getActiveTrajectoryPosition();

  //   if (mPeriodicIO.active_trajectory_position < kReverseSoftLimit) {
  //       DriverStation.reportError("Active trajectory past reverse soft limit!", false);
  //   } else if (mPeriodicIO.active_trajectory_position > kForwardSoftLimit) {
  //       DriverStation.reportError("Active trajectory past forward soft limit!", false);
  //   }
  //   final int newVel = mMaster.getActiveTrajectoryVelocity();

  //   if (Util.epsilonEquals(newVel, Constants.kWristCruiseVelocity, 5) ||
  //           Util.epsilonEquals(newVel, mPeriodicIO.active_trajectory_velocity, 5)) {
  //       // Wrist is ~constant velocity.
  //       mPeriodicIO.active_trajectory_acceleration_rad_per_s2 = 0.0;
  //   } else {
  //       // Wrist is accelerating.
  //       mPeriodicIO.active_trajectory_acceleration_rad_per_s2 = Math.signum(newVel - mPeriodicIO
  //               .active_trajectory_velocity) * RobotMap.kWristAcceleration * 20.0 * Math.PI /
  //               4096;
  //   }

}
