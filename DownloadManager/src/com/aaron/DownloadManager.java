package com.aaron;

import java.util.LinkedList;
import java.util.Queue;

public class DownloadManager {
    private static DownloadManager downloadManager = null;

    static public DownloadManager getIntance() {
        if (downloadManager == null)
            downloadManager = new DownloadManager();

        return downloadManager;
    }

    private DownloadManager() {
        this.networkManager = NetworkManager.getIntance();
    }

    private NetworkManager networkManager;

    public DownloadContext download(
//            ConnectionContext connContext,
//            Priority pri,
//            DownloadProgressCallBack callback,
            String URL,
            String localFile
    ) {
        return null;
    }

}

