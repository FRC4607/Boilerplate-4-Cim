package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import frc.robot.commands.Drivetrain.DriveJoystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ctre.phoenix.ErrorCode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

// Creates the drivetrain subsystem
public class Drivetrain extends Subsystem {

  private static Logger mLogger = LoggerFactory.getLogger(Drivetrain.class);
  private final Logger mSelftestLogger = LoggerFactory.getLogger("Drivetrain_Selftest");

  private boolean mIsHighGear;
  private boolean mIsBrakeMode;

  // Map the CIM motors to the TalonSRX's
  public static WPI_TalonSRX mLeftFollowerA  = new WPI_TalonSRX(RobotMap.kLeftFollerAId);
  public static WPI_TalonSRX mLeftLeader     = new WPI_TalonSRX(RobotMap.kLeftLeaderId);
  public static WPI_TalonSRX mRightFollowerA = new WPI_TalonSRX(RobotMap.kRightFollowerAId);
  public static WPI_TalonSRX mRightLeader    = new WPI_TalonSRX(RobotMap.kRightLeaderId);

  // Add the motors to the speed controller groups and create the differential drivetrain
  public static SpeedControllerGroup leftDrive = new SpeedControllerGroup(mLeftLeader);
  public static SpeedControllerGroup rightDrive = new SpeedControllerGroup(mRightLeader);
  public static DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);

  // Map the pneumatics for the drivetrain
  public static DoubleSolenoid mShifter = new DoubleSolenoid(RobotMap.kPCMId, 2, 3);

 // This method will set up the default settings of the drivetrain motor controllers 
  public static void initDefaultSetup() {
    // Set the front and middle motors to be the followers of the rear motors
    mRightFollowerA.set(ControlMode.Follower, RobotMap.kRightLeaderId);
    mLeftFollowerA.set(ControlMode.Follower, RobotMap.kLeftLeaderId);

    // Set Neutral mode
    mLeftLeader.setNeutralMode(NeutralMode.Brake);
    mLeftFollowerA.setNeutralMode(NeutralMode.Brake);
    mRightLeader.setNeutralMode(NeutralMode.Brake);
    mRightFollowerA.setNeutralMode(NeutralMode.Brake);

    diffDrive.setSafetyEnabled(false);

    // Set the solenoids
    mShifter.set(DoubleSolenoid.Value.kReverse);

    mLogger.info("Drivetrain subsystem created");
  }

  // Takes joystick inputs and runs through the drivetrain
  public void setDrivetrainMove(double xSpeed, double zRotation) {
    Drivetrain.diffDrive.arcadeDrive(xSpeed, zRotation);
  }

//   public void shiftDrivetrain() {
//     if (Drivetrain.m_Shifter.get() == DoubleSolenoid.Value.kForward) {
//       Drivetrain.m_Shifter.set(DoubleSolenoid.Value.kReverse);
//   } else {
//     Drivetrain.m_Shifter.set(DoubleSolenoid.Value.kForward);
//   }
//   mLogger.info("Drivetrain shifted");
// }

public void setHighGear(boolean wantsHighGear) {
  if (wantsHighGear == true) {
    mShifter.set(Value.kForward);
    mIsHighGear = true;
  } else if (wantsHighGear == false) {
    mShifter.set(Value.kReverse);
    mIsHighGear = false;
  }
  mLogger.info("Drivetrain shifted");
 }

 public boolean isHighGear() {
   return mIsHighGear;
 }

 /**
  Shifts the neutral mode of the drivetrain according to wantsBreakMode
  @param wantsBreakMode a boolean input to determine brake/coast mode
  */
  public void setBrakeMode(boolean wantsBreakMode) {
    if (wantsBreakMode == true) {
      mLeftLeader.setNeutralMode(NeutralMode.Brake);
      mLeftFollowerA.setNeutralMode(NeutralMode.Brake);
      mRightLeader.setNeutralMode(NeutralMode.Brake);
      mRightFollowerA.setNeutralMode(NeutralMode.Brake);
      mIsBrakeMode = true;
      mLogger.info("Drivetrain set to brake mode");
   } else if (wantsBreakMode == false) {
      mLeftLeader.setNeutralMode(NeutralMode.Coast);
      mLeftFollowerA.setNeutralMode(NeutralMode.Coast);
      mRightLeader.setNeutralMode(NeutralMode.Coast);
      mRightFollowerA.setNeutralMode(NeutralMode.Coast);
      mIsBrakeMode = false;
      mLogger.info("Drivetrain set to coast mode");
   }
  }
 
  public boolean isBrakeMode() {
   return mIsBrakeMode;
  }
 

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveJoystick());
  }


// Testing portion

private void SelfTestMeasurement(WPI_TalonSRX talon, boolean polarity, boolean wantsHighGear) {
  int sensorPosition, sensorVelocity;
  double motorOutputPercent, motorOutputVoltage, endTime; 
  ErrorCode setSensorPosition;
  int talonId = talon.getDeviceID();
  double setMotorOutputPercent = polarity ? 0.5 : -0.5;

  setHighGear(wantsHighGear);
  talon.set(setMotorOutputPercent);
  setSensorPosition = talon.setSelectedSensorPosition(0);
  if (setSensorPosition != ErrorCode.OK) {
    mSelftestLogger.error("Could not set sensor position to 0, EC: [{}]", setSensorPosition);
    //Flash red LEDs
  } else {
    endTime = Timer.getFPGATimestamp() + 2.0;
    do {
      sensorPosition = talon.getSelectedSensorPosition();
      sensorVelocity = talon.getSelectedSensorVelocity();
      motorOutputPercent = talon.getMotorOutputPercent();
      motorOutputVoltage = talon.getMotorOutputVoltage();
      mSelftestLogger.info("TalonSRX_{},{},{},{},{},{},{}", talonId, wantsHighGear, setMotorOutputPercent, motorOutputPercent, motorOutputVoltage, sensorPosition, sensorVelocity);
    } while (Timer.getFPGATimestamp() < endTime);   
  }
  talon.set(0.0);
}

private void SelfTestMeasurement(WPI_TalonSRX talonFollow, boolean polarity, boolean wantsHighGear, WPI_TalonSRX talonLeader) {
  int sensorPosition, sensorVelocity;
  double motorOutputPercent, motorOutputVoltage, endTime; 
  ErrorCode setSensorPosition;  
  int talonID = talonFollow.getDeviceID();
  double setMotorOutputPercent = polarity ? 0.5 : -0.5;

  setHighGear(wantsHighGear);
  talonFollow.set(setMotorOutputPercent);
  setSensorPosition = talonLeader.setSelectedSensorPosition(0);
  if (setSensorPosition != ErrorCode.OK) {
    mSelftestLogger.error("Could not set sensor position to 0, EC: [{}]", setSensorPosition);
    //Flash red LEDs
  } else {
    endTime = Timer.getFPGATimestamp() + 2.0;
    do {
      sensorPosition = talonLeader.getSelectedSensorPosition();
      sensorVelocity = talonLeader.getSelectedSensorVelocity();
      motorOutputPercent = talonFollow.getMotorOutputPercent();
      motorOutputVoltage = talonFollow.getMotorOutputVoltage();
      mSelftestLogger.info("VictorSPX_{},{},{},{},{},{},{}", talonID, wantsHighGear, setMotorOutputPercent, motorOutputPercent, motorOutputVoltage, sensorPosition, sensorVelocity);
    } while (Timer.getFPGATimestamp() < endTime);   
  }
  talonFollow.set(0.0);
}


public void SelfTest() {
  
  // Set all motors to coast, percent output, and turn off the compressor
  setBrakeMode(false);
  mLeftLeader.set(ControlMode.PercentOutput, 0.0);
  mLeftFollowerA.set(ControlMode.PercentOutput, 0.0);
  mRightLeader.set(ControlMode.PercentOutput, 0.0);
  mRightFollowerA.set(ControlMode.PercentOutput, 0.0);

  // For each motor, polarity, gear: drive at 50% power for 2 seconds and capture encoder/power data
  mSelftestLogger.info("Motor ID, High Gear, Motor Output, Desired Motor Output Percent, Set Motor Output Percent, Motor Output Voltage, Sensor Position, Sensor Velocity");
  SelfTestMeasurement(mLeftLeader, true, true);
  SelfTestMeasurement(mRightLeader, true, true);
  SelfTestMeasurement(mLeftFollowerA, true, true, mLeftLeader);
  SelfTestMeasurement(mRightFollowerA, true, true, mRightLeader);
  SelfTestMeasurement(mLeftLeader, false, true);
  SelfTestMeasurement(mRightLeader, false, true);
  SelfTestMeasurement(mLeftFollowerA, false, true, mLeftLeader);
  SelfTestMeasurement(mRightFollowerA, false, true, mRightLeader);
  SelfTestMeasurement(mLeftLeader, true, false);
  SelfTestMeasurement(mRightLeader, true, false);
  SelfTestMeasurement(mLeftFollowerA, true, false, mLeftLeader);
  SelfTestMeasurement(mRightFollowerA, true, false, mRightLeader);
  SelfTestMeasurement(mLeftLeader, false, false);
  SelfTestMeasurement(mRightLeader, false, false);
  SelfTestMeasurement(mLeftFollowerA, false, false, mLeftLeader);
  SelfTestMeasurement(mRightFollowerA, false, false, mRightLeader);

  // Return back to follower mode and set to open loop mode
  mLeftFollowerA.set(ControlMode.Follower, RobotMap.kLeftLeaderId);
  mRightFollowerA.set(ControlMode.Follower, RobotMap.kRightLeaderId); 
 }
}