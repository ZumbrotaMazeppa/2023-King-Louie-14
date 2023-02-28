package frc.robot.auton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;

public class DriveTurnTask extends Task {
    private DriveTrain m_DriveTrain;
    private double degrees;
    private boolean turnLeft;

    public DriveTurnTask(DriveTrain dt, double degrees) {
        m_DriveTrain = dt;
        this.degrees = degrees;
    }

    public void init(){
        m_DriveTrain.stop();
        m_DriveTrain.initDrive();

        if (degrees > 0) {
            turnLeft = true;
        } else {
            turnLeft = false;
        }

        initialized = true;
    }

    public void execute(){
        double position = m_DriveTrain.turn(turnLeft);

        if (Math.abs(position * 11.95) > Math.abs(degrees)) {
            m_DriveTrain.stop();
            completed = true;
        }
        SmartDashboard.putNumber("DriveTurnTask position", position);
        SmartDashboard.putNumber("DriveTurnTask degrees", position);
        SmartDashboard.putNumber("DriveTurnTask calc", Math.abs(position * 11.95));
    } 

    public void done(){
        m_DriveTrain.stop();
    }
}
