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
    //ObserverAgent constructor and patches of grass
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
                world.changed();
                if (paused) {
                    synchronized (this) {
                        while (paused && !stopped) {
                            wait();
                        }
                    }
                }
            }
            catch (InterruptedException i){
                onInterrupted();
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
        paused = false;
        notify();
    }
    public double getDistance (Agent other){
        return Math.sqrt(Math.pow(other.xc - xc, 2) + Math.pow(other.yc - yc, 2));
    }
    public abstract void update();
    public void onStart(){}
    public void onInterrupted(){}
    public void onExit(){}
    public int[] getPoint() {
        return new int[]{xc, yc};
    }
    public void setWorld(World w) { world = w; }

    // wait for me to die:
    public synchronized void join() {
        try {
            if (myThread != null)
                myThread.join();
        } catch (InterruptedException e) {
            onInterrupted();
        }
    }

    public void setXc(int xc) {
        this.xc = xc;
    }

    public void setYc(int yc) {
        this.yc = yc;
    }

    public boolean isStopped(){
        return stopped;
    }

    public void setPosition(int x, int y) {
        this.xc = x;
        this.yc = y;
    }
}
