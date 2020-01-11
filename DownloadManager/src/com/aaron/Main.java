package com.aaron;

import static java.lang.Thread.sleep;



public class Main {
    static class AppDownloadCompleteCallBack implements DownloadProgressCallback {
        @Override
        public void onComplete() {
            System.out.println("App received notification on download complete");
        }

        @Override
        public void onError() {

        }
    }

    public static void main(String[] args) {
	// write your code here
        DownloadManager dm = DownloadManager.getInstance();
        if(dm == null)
            return;
        DownloadProgress dc1 = dm.download("URL 1", "File 1", null);
        AppDownloadCompleteCallBack downloadcallback2 = new AppDownloadCompleteCallBack();
        dm.download("URL 2", "File 2", downloadcallback2);
        dm.download("URL 3", "File 3", null);
        dm.download("URL 4", "File 4", null);
        

        try {
            sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(dc1.getStatus());
        System.out.println(dc1.getDownloadPercentage());
        dc1.pause();
        System.out.println(dc1.getStatus());

        try {
            sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }

        dc1.resume();
        System.out.println(dc1.getStatus());



    }
}
