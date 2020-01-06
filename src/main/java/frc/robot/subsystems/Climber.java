// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import frc.robot.RobotMap;
// import frc.robot.commands.Climber.;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.DoubleSolenoid;

// // Creates the drivetrain subsystem
// public class Climber extends Subsystem {

//   // Map the CIM motors to the TalonSRX's
//   public static WPI_TalonSRX mLeftFollowerA  = new WPI_TalonSRX(RobotMap.kLeftFollerAId);
//   public static WPI_TalonSRX mLeftFollowerB  = new WPI_TalonSRX(RobotMap.kLeftFollwerBId);
//   public static WPI_TalonSRX mLeftLeader     = new WPI_TalonSRX(RobotMap.kLeftLeaderId);
//   public static WPI_TalonSRX mRightFollowerB = new WPI_TalonSRX(RobotMap.kRightFollowerBId);
//   public static WPI_TalonSRX mRightFollowerA = new WPI_TalonSRX(RobotMap.kRightFollowerAId);
//   public static WPI_TalonSRX mRightLeader    = new WPI_TalonSRX(RobotMap.kRightLeaderId);

//   // Add the motors to the speed controller groups and create the differential drivetrain
//   public static SpeedControllerGroup leftDrive = new SpeedControllerGroup(mLeftLeader);
//   public static SpeedControllerGroup rightDrive = new SpeedControllerGroup(mRightLeader);
//   public static DifferentialDrive diffDrive = new DifferentialDrive(leftDrive, rightDrive);

//   // Map the pneumatics for the drivetrain
//   public static DoubleSolenoid m_Shifter = new DoubleSolenoid(RobotMap.kPCMId, 2, 3);

//  // This method will set up the default settings of the drivetrain motor controllers 
//   public static void initDefaultSetup() {
//     // Set the front and middle motors to be the followers of the rear motors
//     mRightFollowerA.set(ControlMode.Follower, RobotMap.kRightLeaderId);
//     mRightFollowerB.set(ControlMode.Follower, RobotMap.kRightLeaderId);
//     mLeftFollowerA.set(ControlMode.Follower, RobotMap.kLeftLeaderId);
//     mLeftFollowerB.set(ControlMode.Follower, RobotMap.kLeftLeaderId);

//     // Set Neutral mode
//     mLeftLeader.setNeutralMode(NeutralMode.Brake);
//     mLeftFollowerA.setNeutralMode(NeutralMode.Brake);
//     mLeftFollowerB.setNeutralMode(NeutralMode.Brake);
//     mRightLeader.setNeutralMode(NeutralMode.Brake);
//     mRightFollowerA.setNeutralMode(NeutralMode.Brake);
//     mRightFollowerB.setNeutralMode(NeutralMode.Brake);

//     diffDrive.setSafetyEnabled(false);

//     // Set the solenoids
//     m_Shifter.set(DoubleSolenoid.Value.kReverse);
//   }

//   // Takes joystick inputs and runs through the drivetrain
//   public void setDrivetrainMove(double xSpeed, double zRotation) {
//     Drivetrain.diffDrive.arcadeDrive(xSpeed, zRotation);
//   }

//   public void shiftDrivetrain() {
//     if (Drivetrain.m_Shifter.get() == DoubleSolenoid.Value.kForward) {
//       Drivetrain.m_Shifter.set(DoubleSolenoid.Value.kReverse);
//   } else {
//     Drivetrain.m_Shifter.set(DoubleSolenoid.Value.kForward);
//   }
// }

//   @Override
//   public void initDefaultCommand() {
//     setDefaultCommand(new ClimberJoystick());
//   }
// }
