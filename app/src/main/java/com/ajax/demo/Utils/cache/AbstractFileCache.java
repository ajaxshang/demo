package com.ajax.demo.Utils.cache;

import android.content.Context;
import android.util.Log;

import com.ajax.demo.Utils.FileHelper;

import java.io.File;

/**
 * Created by Administrator on 2015/8/27.
 */
public abstract class AbstractFileCache {
    private String dirString;

    public AbstractFileCache(Context context) {
        dirString = getCacheDir();
        boolean ret = FileHelper.createDirectory(dirString);
        Log.e("", "FileHelper.createDirectory:" + dirString + ", ret = " + ret);
    }

    public File getFile(String url) {
        File f = new File(getSavePath(url));
        return f;
    }

    public abstract String getSavePath(String url);

    public abstract String getCacheDir();

    public void clear() {
        FileHelper.deleteDirectory(dirString);
    }
}
