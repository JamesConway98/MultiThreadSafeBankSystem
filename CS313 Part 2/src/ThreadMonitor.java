import java.util.ArrayList;

public class ThreadMonitor {

    ThreadGroup rootThreadGroup;
    Thread[] threads;
    ThreadGroup[] threadGroups;

    public ThreadMonitor() {
        monitorThreads();
    }

    public void monitorThreads() {

        rootThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentThreadGroup;
        while ((parentThreadGroup = rootThreadGroup.getParent()) != null) {
            rootThreadGroup = parentThreadGroup;
        }

        threads = new Thread[rootThreadGroup.activeCount()];
        while (rootThreadGroup.enumerate(threads, true) == threads.length) {
            threads = new Thread[threads.length * 2];
        }

        threadGroups = new ThreadGroup[rootThreadGroup.activeGroupCount()];
        while (rootThreadGroup.enumerate(threadGroups, true) == threadGroups.length) {
            threadGroups = new ThreadGroup[threadGroups.length * 2];
        }

        for (Thread thread : threads) {
            printThread(thread);
        }
    }

    public Thread[] getThreads() {
        return threads;
    }

    public void printThread(Thread thread) {
        if (thread != null) {
            System.out.println("\n");
            System.out.println("the name of this thread is " + thread.getName());
            System.out.println("the id of this thread is " + thread.getId());
            System.out.println("the state of this thread is " + thread.getState());
            System.out.println("the priority of this thread is " + thread.getPriority());
            if (thread.isDaemon()) {
                System.out.println("This thread is a Daemon");
            } else {
                System.out.println("This thread is not a Daemon");
            }
        }
    }

    public Thread searchThreads(String name) {
        for (Thread thread : threads) {
            if (thread.getName().equals(name)) {
                return thread;
            }
        }
        return null;
    }

    public ThreadGroup filterByThreadGroup(String name) {
        for (ThreadGroup threadGroup : threadGroups) {
            if (threadGroup.getName().equals(name)) {
                return threadGroup;
            }
        }
        return null;
    }

    public boolean addThread() {
        return true;
    }

    public boolean deleteThread() {
        return true;
    }
}
