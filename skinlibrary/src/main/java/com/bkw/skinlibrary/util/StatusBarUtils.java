package com.bkw.skinlibrary.util;

import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

public class StatusBarUtils {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void forStatusBar(AppCompatActivity activity) {
        TypedArray a = activity.getTheme().obtainStyledAttributes(0, new int[]{
                android.R.attr.statusBarColor
        });
        int color = a.getColor(0, 0);
        activity.getWindow().setStatusBarColor(color);
        a.recycle();
    }

    /**
     * 更换为指定颜色
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void forStatusBar(AppCompatActivity activity, int skinColor) {
        activity.getWindow().setStatusBarColor(skinColor);
    }
}
