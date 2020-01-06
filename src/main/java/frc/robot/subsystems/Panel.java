package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// Creates the panel subsystem
public class Panel extends Subsystem {

  public enum SystemState {
    Autonomous,
    OpenLoop,
    Testing
  }

  private static DoubleSolenoid mPanelShifter = new DoubleSolenoid(RobotMap.kPCMId, RobotMap.kHighGearSolenoid , RobotMap.kLowGearSolenoid);

  private boolean mPanelClosed;

  // Logger
  private static Logger mLogger = LoggerFactory.getLogger(Panel.class);

  public static void initDefaultSetup() {
    mPanelShifter.set(DoubleSolenoid.Value.kForward);
    mLogger.info("Panel subsystem created");
    SmartDashboard.putBoolean("Panel Intake actuated:", false); 
  }

  /**
   Shifts the panel intake to the opposite position (i.e, if currently open will close)
   **/
  public void shiftPanelIntake(boolean wantsPanelClosed) {
    if (wantsPanelClosed == true) {
      mPanelShifter.set(DoubleSolenoid.Value.kForward);
      mPanelClosed = true;
      SmartDashboard.putBoolean("Panel Intake actuated:", wantsPanelClosed);
    } else if (wantsPanelClosed == false) {
      mPanelShifter.set(DoubleSolenoid.Value.kReverse);
      mPanelClosed = false;
      SmartDashboard.putBoolean("Panel Intake actuated:", wantsPanelClosed);    
    }
    mLogger.info("Panel shifted");
   }

 public boolean isPanelClosed() {
   return mPanelClosed;
 }

// //   public Panel(DoubleSolenoid PanelShifter) {
// //     mPanelShifter = PanelShifter;
// //     mPanelShifter.set(DoubleSolenoid.Value.kReverse);
// //     mPanelClosed = false;

// //     mLogger.info("Panel Manipulator Created");
// //   }

// //   public static Panel create() {
// //     DoubleSolenoid PanelShifter = new DoubleSolenoid(RobotMap.kPCMId, RobotMap.kHighGearSolenoid, RobotMap.kLowGearSolenoid);

// //     return new Panel(PanelShifter);
// //   }

  @Override
  public void initDefaultCommand() {
  }
}
