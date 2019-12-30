package com.aaron;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NetworkManager {
    private static NetworkManager networkManager = null;
    private static int MAX_THREADS = 5;

    private Queue<DownloadContext> queue;
    private Worker[] workers;


    static public NetworkManager getIntance() {
        if (networkManager == null)
            networkManager = new NetworkManager();
        return networkManager;
    }

    private NetworkManager() {
        this.queue = new LinkedList<>();
        this.workers = new Worker[MAX_THREADS];
        for (int i=0; i<MAX_THREADS; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }

    }

    public void execute(Runnable r) {
        synchronized(queue) {
            queue.addLast(r);
            queue.notify();
        }
    }


    private class Worker extends Thread {
        public void run() {
            Runnable r;

            while (true) {
                synchronized(queue) {
                    while (queue.isEmpty()) {
                        try
                        {
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }

                    r = (Runnable) queue.remove();
                }

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    r.run();
                }
                catch (RuntimeException e) {
                    // You might want to log something here
                }
            }
        }
    }
}




