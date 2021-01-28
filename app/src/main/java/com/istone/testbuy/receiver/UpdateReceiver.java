package com.istone.testbuy.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;

public class UpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (StringUtils.equals(intent.getAction(), DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Cursor cursor = downloadManager.query(new DownloadManager.Query().setFilterById(downloadId));
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        int status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            Intent installIntent = IntentUtils.getInstallAppIntent(
                                    downloadManager.getUriForDownloadedFile(downloadId));
                            if (installIntent != null) context.startActivity(installIntent);
                        }
                    }
                } catch (Exception e) {
                    LogUtils.d(e);
                } finally {
                    cursor.close();
                }
            }
        }
    }

}
