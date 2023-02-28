package frc.robot.auton;

public class WaitTask extends Task {
    private long msWaitTime;
    private long startTime;

    public WaitTask(long msWaitTime) {
        this.msWaitTime = msWaitTime;
    }

    public void init(){
        initialized = true;
    }

    public void execute(){

        long now = System.currentTimeMillis();

        if (startTime == 0) {
            startTime = now;
        }

        if (now - startTime >= msWaitTime) {
            completed = true;
        }
    } 

    public void done(){
    }
}
