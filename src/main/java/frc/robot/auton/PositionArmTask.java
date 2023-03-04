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

    public PositionArmTask(Scissor scissor, boolean extend) {
        m_Scissor = scissor;
        this.extend = extend;
    }

    public void init(){
        
        m_Scissor.stop();

       

        msMoveScissor = 750;
        initialized = true;
    }

    public void execute(){

        long now = System.currentTimeMillis();

        if (startTime == 0) {
            startTime = now;
        }


        
        if (now - startTime < msMoveScissor) {
            m_Scissor.move(extend);
        } else {
            m_Scissor.stop();
        }
    } 

    public void done(){
       
        m_Scissor.stop();
    }
}
