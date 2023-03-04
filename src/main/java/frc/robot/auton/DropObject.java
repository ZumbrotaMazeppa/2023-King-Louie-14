package frc.robot.auton;

import frc.robot.subsystems.Grippy;

public class DropObject extends Task {
    private Grippy m_Grippy;
    private long startTime;

    public DropObject(Grippy g) {
        m_Grippy = g;
    }

    public void init(){
        initialized = true;
    }

    public void execute(){

        long now = System.currentTimeMillis();

        if (startTime == 0) {
            startTime = now;
        }

        if (now - startTime >= 1000) {
            m_Grippy.close();
            completed = true;
        } else {
            m_Grippy.open();
        }
    } 

    public void done(){
        m_Grippy.off();
    }
}
