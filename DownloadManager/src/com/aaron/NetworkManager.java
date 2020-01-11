package com.aaron;

import javax.print.attribute.standard.DialogOwner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class NetworkManager {
    //Interfaces for Download Manager
    static public NetworkManager getInstance() {
        if (networkManager == null)
            networkManager = new NetworkManager();
        return networkManager;
    }

    public void add(DownloadContext downloadContext) {
        synchronized(queue) {
            queue.add(downloadContext);
            queue.notifyAll();
        }
    }

    //Implementation
    private static NetworkManager networkManager = null;
    private static int MAX_THREADS = 2;

    private Queue<DownloadContext> queue;
    private Worker[] workers;

    private NetworkManager() {
        this.queue = new LinkedList<>();
        this.workers = new Worker[MAX_THREADS];
        for (int i=0; i<MAX_THREADS; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }

    }

    private class Worker extends Thread {
        public void run() {
            DownloadContext downloadContext = null;
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
                    downloadContext = queue.remove();
                }
                try {
                    if(downloadContext != null) {
                        System.out.println("Starting downloading " + downloadContext.URL);
                        downloadContext.setStatus(DownloadStatus.IN_PROGRESS);
                        for (int i = 0; i < 10; i++) {
                            sleep(1000);
                            downloadContext.waitIfPaused();
                            int percent = downloadContext.increaseDownloadPercentage(10);
                            System.out.println(downloadContext.URL + ": " + percent + " percent");
                        }
                        System.out.println("Downloading complete: " + downloadContext.URL);
                        downloadContext.setStatus(DownloadStatus.COMPLETED);
                        if(downloadContext.callback != null)
                            downloadContext.callback.onComplete();
                    }
                    downloadContext = null;
                }
                catch (RuntimeException | InterruptedException e) {
                }
            }
        }
    }
}




