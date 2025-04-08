package simstation;

import mvc.Utilities;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    protected World world;
    protected int xc;
    protected int yc;
    protected boolean paused = false;
    protected boolean stopped = false;
    protected String agentName;
    protected transient Thread myThread;
    public Agent(){
        agentName = "Agent " + Utilities.getID();
    }
    //ObserverAgent constructor
    public Agent(World world){
        this();
        setWorld(world);
    }
    @Override
    public void run(){
        onStart();
        myThread = Thread.currentThread();
        while(!stopped) {
            try {
                update();
                Thread.sleep(20);
                if (paused) {
                    synchronized (this) {
                        while (paused && !stopped) {
                            wait();
                            paused = false;
                        }
                    }
                }
            }
            catch (InterruptedException i){
                onInterrupted();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        onExit();
    }
    public void start() {
        Thread thread = new Thread(this);
    	thread.start();
    }
    public synchronized void stop() {
        stopped = true;
    }
    public synchronized void pause() {
        paused = true;
    }
    public synchronized void resume() {
        notify();
    }
    public abstract void update();
    public void onStart(){}
    public void onInterrupted(){Utilities.error("Interrupted!");}
    public void onExit(){}
    public int[] getPoint(){
        return new int[]{xc, yc};
    }
    public void setWorld(World w) { world = w; }

}
