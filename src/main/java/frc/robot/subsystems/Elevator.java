package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import frc.robot.commands.Elevator.ElevatorJoystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// Creates the elevator subsystem
public class Elevator extends Subsystem {

  public enum SystemState {
    Autonomous,
    OpenLoop,
    Testing
  }

  private static Logger mLogger = LoggerFactory.getLogger(Elevator.class);

  public static WPI_TalonSRX mElevatorMotorA  = new WPI_TalonSRX(RobotMap.kElevatorMotorAId);
  public static WPI_TalonSRX mElevatorMotorB = new WPI_TalonSRX(RobotMap.kElevatorMotorBId);

public static void initDefaultSetup() {
    mElevatorMotorA.setNeutralMode(NeutralMode.Brake);
    mElevatorMotorB.setNeutralMode(NeutralMode.Brake);

    mElevatorMotorB.set(ControlMode.Follower, RobotMap.kElevatorMotorAId);
    mLogger.info("Elevator subsystem created");
}

  // Elevator OpenLoop
  public void OpenLoop(double xElevator) {
    mElevatorMotorA.set(xElevator);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorJoystick());
  }
}
