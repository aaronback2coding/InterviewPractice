package com.aaron;

//To do - split download context into internal state management vs. external download progress tracking

public class DownloadContext {
    String URL;
    String localFile;

    public DownloadContext(String URL, String localFile) {
        this.URL = URL;
        this.localFile = localFile;
    }

    public void pause() {

    }

    public void resume() {

    }

    public void cancel() {

    }

//    public DownloadStatus getStatus() {
//        return null;
//    }

    public int getDownloadSize() {
        return 0;
    }

}
