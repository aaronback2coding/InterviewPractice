package com.aaron;

//To do - split download context into internal state management vs. external download progress tracking


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadContext implements DownloadProgress{
    String URL = null;
    String localFile = null;
    DownloadProgressCallback callback = null;

    private volatile int downloadPercentage = 0; // 0 - 100.
    private volatile DownloadStatus status = DownloadStatus.WAIT;
    private ReentrantLock statusUpdateLock = null;
    private Condition pausedCondition = null;


    public DownloadContext(String URL, String localFile) {
        this.URL = URL;
        this.localFile = localFile;
        downloadPercentage = 0;
        statusUpdateLock = new ReentrantLock();
        pausedCondition = statusUpdateLock.newCondition();
    }

    public DownloadContext(String URL, String localFile, DownloadProgressCallback callback) {
        this.URL = URL;
        this.localFile = localFile;
        downloadPercentage = 0;
        statusUpdateLock = new ReentrantLock();
        pausedCondition = statusUpdateLock.newCondition();
        this.callback = callback;
    }

    @Override
    public void pause() {
        statusUpdateLock.lock();
        try {
            status = DownloadStatus.PAUSED;
            pausedCondition.signalAll();
        } finally {
            statusUpdateLock.unlock();
        }
    }

    void waitIfPaused() {
        statusUpdateLock.lock();
        try {
            while (status == DownloadStatus.PAUSED) {
                try {
                    pausedCondition.await();
                } catch (InterruptedException e) { }
            }
        } finally {
            statusUpdateLock.unlock();
        }

    }

    @Override
    public void resume() {
        statusUpdateLock.lock();
        try {
            status = DownloadStatus.IN_PROGRESS;
            pausedCondition.signalAll();
        } finally {
            statusUpdateLock.unlock();
        }
    }

    @Override
    public void cancel() {

    }

    void setStatus(DownloadStatus status) {
        statusUpdateLock.lock();
        try {
            this.status = status;
        } finally {
            statusUpdateLock.unlock();
        }
    }

    @Override
    public DownloadStatus getStatus() {
        statusUpdateLock.lock();
        try {
            return status;
        } finally {
            statusUpdateLock.unlock();
        }
    }

    @Override
    public int getDownloadPercentage() {
        statusUpdateLock.lock();
        try {
            return downloadPercentage;
        } finally {
            statusUpdateLock.unlock();
        }
    }

    int increaseDownloadPercentage(int val) {
        statusUpdateLock.lock();
        try {
            downloadPercentage += val;
            return downloadPercentage;
        } finally {
            statusUpdateLock.unlock();
        }
    }

}

