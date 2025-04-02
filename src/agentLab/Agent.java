package agentLab;

abstract class Agent implements Runnable {

    protected String name;
    protected Thread myThread;
    private boolean suspended, stopped;
    protected Manager manager;

    public Agent(String name) {
        this.name = name;
        suspended = false;
        stopped = false;
        myThread = null;
    }

    public void setManager(Manager m) { manager = m; }
    public String getName() { return name; }
    public synchronized String toString() {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (suspended) result += " (suspended)";
        else result += " (running)";
        return result;
    }
    // thread stuff:
    public synchronized void stop() { stopped = true; }
    public synchronized boolean isStopped() { return stopped; }
    public synchronized void suspend() { suspended = true; }
    public synchronized boolean isSuspended() { return suspended;  }
    public synchronized void resume() { notify(); }
    // wait for me to die:
    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch(InterruptedException e) {
            manager.println(e.getMessage());
        }
    }
    // wait for notification if I am not stopped, and I am suspended
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            manager.println(e.getMessage());
        }
    }


    public void run() {
        myThread = Thread.currentThread();
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(1000);
                checkSuspended();
            } catch(InterruptedException e) {
                manager.println(e.getMessage());
            }
        }
        manager.stdout.println(name + " stopped");
    }

    public abstract void update();
}