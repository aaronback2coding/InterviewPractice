package com.aaron;

import java.util.LinkedList;
import java.util.Queue;

public class DownloadManager {
    private static DownloadManager downloadManager = null;

    static public DownloadManager getInstance() {
        if (downloadManager == null)
            downloadManager = new DownloadManager();

        return downloadManager;
    }

    private DownloadManager() {
        this.networkManager = NetworkManager.getInstance();
    }

    private NetworkManager networkManager;

    public DownloadContext download(
//            ConnectionContext connContext,
//            Priority pri,
            String URL,
            String localFile,
            DownloadProgressCallback callback
    ) {
        DownloadContext downloadContext = new DownloadContext(URL, localFile, callback);
        networkManager.add(downloadContext);
        return downloadContext;
    }
}

