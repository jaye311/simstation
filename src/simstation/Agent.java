package simstation;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    protected World world;
    protected int xc;
    protected int yc;
    protected boolean paused = false;
    protected boolean stopped = false;
    protected String agentName;
    transient protected Thread myThread;
    public Agent(){
    }
    public Agent(World world){
        this.world = world;
    }
    @Override
    public void run(){
        try {
            while (!stopped && !paused)
                update();
            onStart();
            onInterrupted();
            onExit();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void start() {
    	if (myThread == null) {
    		myThread = new Thread(this);
    	}
    	myThread.start();
        
    }
    public void stop() {
        stopped = true;
    }
    public void pause() {
        paused = true;
    }
    public void resume() {
        paused = false;
    }
    public abstract void update() throws Exception;
    public void onStart(){};
    public void onInterrupted(){};
    public void onExit(){};
}
