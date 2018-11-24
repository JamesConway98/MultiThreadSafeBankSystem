public class ThreadMonitor {

    private Thread[] threads;
    private ThreadGroup[] threadGroups;

    public ThreadMonitor() {
        monitorThreads();
    }

    public void monitorThreads() {

        ThreadGroup rootThreadGroup = Thread.currentThread().getThreadGroup();
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
    }

    public Thread[] getThreads() {
        return threads;
    }


    public Thread searchThreads(String name) {
        try {
            for (Thread thread : threads) {
                if (thread.getName().equals(name)) {
                    return thread;
                }
            }
        } catch (NullPointerException npe) {
            return null;
        }
        return null;
    }

    public ThreadGroup filterByThreadGroup(String name) {
        try {
            for (ThreadGroup threadGroup : threadGroups) {
                if (threadGroup.getName().equals(name)) {
                    return threadGroup;
                }
            }
        } catch (NullPointerException npe) {
            return null;
        }
            return null;
        }
}
