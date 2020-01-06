package frc.robot.commands.PanelIntake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PanelIntakeShift extends Command{

  public boolean mIsFinished = true;
  
  public PanelIntakeShift() {
      }
    
      @Override
      protected void initialize() {
        mIsFinished = false;
      }
    
      @Override
      protected void execute() {
        Robot.mPanel.shiftPanelIntake(!Robot.mPanel.isPanelClosed());
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
    
