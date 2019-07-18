package com.bkw.skin_builtin;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.bkw.skinlibrary.BaseSkinActivity;
import com.bkw.skinlibrary.util.PreferencesUtils;

public class MainActivity extends BaseSkinActivity {

    private final static String SP_NAME = "isNight";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        boolean isNight = PreferencesUtils.getBoolean(this, SP_NAME);
        if (isNight) {
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * 点击事件
     */
    public void bt_click(View view) {
        int uiMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (uiMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                PreferencesUtils.putBoolean(this, SP_NAME, true);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                setDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                PreferencesUtils.putBoolean(this, SP_NAME, false);
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean openChangeSkin() {
        return true;
    }
}
