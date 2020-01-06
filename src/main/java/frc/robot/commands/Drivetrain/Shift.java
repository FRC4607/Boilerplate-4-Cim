package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Shift extends Command{

  public boolean mIsFinished = true;
  
  public Shift() {
        requires(Robot.mDrivetrain);
      }
    
      @Override
      protected void initialize() {
        mIsFinished = false;
      }
    
      @Override
      protected void execute() {
        Robot.mDrivetrain.setHighGear(!Robot.mDrivetrain.isHighGear());
        mIsFinished = true;
      }
    
      @Override
      protected boolean isFinished() {
        return mIsFinished;
      }
    
      @Override
      protected void end() {
      }
    
      @Override
      protected void interrupted() {
      }
    }
    
