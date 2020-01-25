package frc.robot.subsystems;

import com.;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Flywheel.FlywheelJoystick;

public class TransferWheel extends Subsystem {

    public static WPI_TalonSRX mTransferWheel = new WPI_TalonSRX(RobotMap.kTransferWheel);
    
    private boolean mIsBrakeMode;

    public boolean isBrakeMode() {
      return mIsBrakeMode;
    }
    
    public void ApplyDriveSignal(double throttle) {
      double mThrottle = throttle;
      mTransferWheel.set(mThrottle);
    }

    public void setOpenLoopControl() {
      setBrakeMode(true);
    }

    public TransferWheel(WPI_TalonSRX master) {
      mTransferWheel = master;
  
      mIsBrakeMode = false;
      setBrakeMode(true);
  
      // This is inverted alongside the joystick inputs in order to create working limit switches
      mTransferWheel.setInverted(true);
  
      // Start off in open loop control
      setOpenLoopControl();
    }

    private void setBrakeMode(boolean b) {
    }

    public TransferWheel() {
    }
  
    @Override
    public void initDefaultCommand() {
      setDefaultCommand(new FlywheelJoystick());
    }

    public static void initDefaultSetup() {
    }
		
	}
      
    