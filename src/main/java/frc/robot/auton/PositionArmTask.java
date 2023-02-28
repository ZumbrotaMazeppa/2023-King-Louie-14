package frc.robot.auton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Scissor;
import frc.robot.subsystems.Winch;

public class PositionArmTask extends Task {
    private Winch m_Winch;
    private Scissor m_Scissor;
    private boolean extend;
    private long msMoveWinch;
    private long msMoveScissor;
    private long startTime;

    public PositionArmTask(Winch winch, Scissor scissor, boolean extend) {
        m_Winch = winch;
        m_Scissor = scissor;
        this.extend = extend;
    }

    public void init(){
        m_Winch.stop();
        m_Scissor.stop();

        if (extend) {
            msMoveWinch = 10000;
        } else {
            // going down is easier 
            msMoveWinch = 9000;
        }

        msMoveScissor = 9000;
        initialized = true;
    }

    public void execute(){

        long now = System.currentTimeMillis();

        if (startTime == 0) {
            startTime = now;
        }

        if (now - startTime < msMoveWinch) {
            m_Winch.move(!extend);
        } else {
            m_Winch.stop();
            completed = true;
        }
        
        if (now - startTime < msMoveScissor) {
            m_Scissor.move(extend);
        } else {
            m_Scissor.stop();
        }
    } 

    public void done(){
        m_Winch.stop();
        m_Scissor.stop();
    }
}
