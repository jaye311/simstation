package simstation;

import mvc.Utilities;

import java.awt.*;
import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {
    protected World world;
    protected int xc;
    protected int yc;
    protected boolean paused = false;
    volatile protected boolean stopped = false;
    protected String agentName;
    volatile transient protected Thread myThread;
    public Agent(){
    }
    public Agent(World world){
        this.world = world;
        xc = Utilities.rng.nextInt(World.SIZE);
        yc = Utilities.rng.nextInt(World.SIZE);
    }
    
    public int getX() {
    	return xc; 
    }
    
    public int getY() {
    	return yc; 
    }
    
    @Override
    public void run(){
        onStart();
        Thread thisThread = myThread;
        while(thisThread == myThread) {
            try {
                while (!stopped) {
                    update();
                    if (paused) {
                        synchronized (this) {
                            while (paused && thisThread == myThread)
                                wait(50);
                        }
                    }
                }
                if (stopped)
                    stop();
            }
            catch (InterruptedException i){
                onInterrupted();
            }
            catch (Exception e) {
                System.err.println(e.getMessage());//eventually remove this
            }
        }
        onExit();
    }
    public void start() {
    	if (myThread == null) {
    		myThread = new Thread(this);
    	}
    	myThread.start();
    }
    public void stop() {
        stopped = true;
        myThread = null;
    }
    public void pause() {
        paused = true;
    }
    public void resume() {
        paused = false;
    }
    public abstract void update() throws Exception;
    public void onStart(){}
    public void onInterrupted(){}
    public void onExit(){}
    public Dimension getPoint(){
        return new Dimension(xc, yc);
    }
}
