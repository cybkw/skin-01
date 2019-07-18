package com.bkw.skinlibrary.util;

import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

public class NavigationUtils {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void forNavigation(AppCompatActivity activity) {
        TypedArray a = activity.obtainStyledAttributes(0, new int[]{
                android.R.attr.navigationBarColor
        });
        int color = a.getColor(0, 0);
        activity.getWindow().setNavigationBarColor(color);
        a.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void forNavigation(AppCompatActivity activity, int skinColor) {
        activity.getWindow().setNavigationBarColor(skinColor);
    }
}
