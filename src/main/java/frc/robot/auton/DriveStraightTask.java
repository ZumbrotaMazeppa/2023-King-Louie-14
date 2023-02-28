package frc.robot.auton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;

public class DriveStraightTask extends Task {
    private DriveTrain m_DriveTrain;
    private double numFeetToMove;

    public DriveStraightTask(DriveTrain dt, double numberoffeet) {
        m_DriveTrain = dt;
        numFeetToMove = numberoffeet;
    }

    public void init(){
        m_DriveTrain.stop();
        m_DriveTrain.initDrive();
        initialized = true;
    }

    public void execute(){
        double position = m_DriveTrain.moveForward(true);

        if (Math.abs(position * 0.3) > Math.abs(numFeetToMove)) {
            m_DriveTrain.stop();
            completed = true;
        }
    } 

    public void done(){
        m_DriveTrain.stop();
    }
}
