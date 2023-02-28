package frc.robot.auton;

public abstract class Task {

    protected boolean initialized = false;
    protected boolean completed = false;

    public abstract void init();
    public abstract void execute();
    public abstract void done();

    public boolean isInitialized(){
        return initialized;
    }

    public boolean isComplete() {
        return completed;
    }
}
