package com.aaron;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public interface DownloadProgress {

    public void pause();
    public void resume();
    public void cancel();
    public DownloadStatus getStatus();
    public int getDownloadPercentage();
}
