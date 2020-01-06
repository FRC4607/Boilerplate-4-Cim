package frc.robot;

import frc.robot.RobotMap;

import frc.robot.commands.Drivetrain.Shift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

  // blake, only need one joystick for boilerplate 
  public static Joystick driverJoystick = new Joystick(RobotMap.kDriveJoystickId);
  
  // shift the drivetrain between low gear and high gear
  public Button mShift = new JoystickButton(driverJoystick, 1);

  public OI(){

    mShift.whenPressed(new Shift());

  }
}