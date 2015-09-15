package com.ajax.demo.Utils;

import android.os.Environment;

/**
 * Created by Administrator on 2015/8/27.
 */
public class FileManager {
    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    public static String getRootFilePath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";// filePath:/sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/data/"; // filePath: /data/data/
        }
    }

    public static String getSaveFilePath() {
        if (hasSDCard()) {
            return getRootFilePath() + "com.ajax/files/";
        } else {
            return getRootFilePath() + "com.ajax/files";
        }
    }
}
