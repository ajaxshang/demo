package com.ajax.demo.Utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2015/8/29.
 */
public class MeasureUtil {
    public static int[] getScreenSize(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }
}
