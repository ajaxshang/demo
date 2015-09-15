package com.ajax.demo.Utils.cache;

import android.content.Context;
import android.os.Environment;

/**
 * Created by Administrator on 2015/8/27.
 */
public class FileCache extends AbstractFileCache {

    public FileCache(Context context) {
        super(context);
    }

    @Override
    public String getSavePath(String url) {
        String filename = String.valueOf(url.hashCode());
        return getCacheDir() + filename;
    }

    @Override
    public String getCacheDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.ajax/files/";
    }
}
