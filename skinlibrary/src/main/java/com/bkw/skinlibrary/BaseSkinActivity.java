package com.bkw.skinlibrary;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bkw.skinlibrary.core.MyCompatViewInflater;
import com.bkw.skinlibrary.core.ViewMatch;
import com.bkw.skinlibrary.util.ActionBarUtils;
import com.bkw.skinlibrary.util.NavigationUtils;
import com.bkw.skinlibrary.util.StatusBarUtils;

/**
 * 换肤Activity父类
 * 用法：
 * 1.继承此类
 * 2.重写openChangeSkin()方法
 */
public class BaseSkinActivity extends AppCompatActivity {

    private MyCompatViewInflater viewInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (openChangeSkin()) {
            if (viewInflater == null) {
                viewInflater = new MyCompatViewInflater(context);
            }
            viewInflater.setName(name);
            viewInflater.setAttrs(attrs);
            return viewInflater.autoMatch();
        }
        return super.onCreateView(parent, name, context, attrs);
    }

    /**
     * 是否开启换肤，增加此开关是为了避免开发者误继承此父类，导致未知bug
     */
    protected boolean openChangeSkin() {
        return false;
    }

    protected void setDayNightMode(@AppCompatDelegate.NightMode int nightMode) {
        final boolean isPost21 = Build.VERSION.SDK_INT >= 21;

        getDelegate().setLocalNightMode(nightMode);

        //如果是5.0版本或以上
        if (isPost21) {
            //换状态栏
            StatusBarUtils.forStatusBar(this);
            //换标题栏
            ActionBarUtils.forActionBar(this);
            //换底部导航栏
            NavigationUtils.forNavigation(this);
        }

        //取得顶层容器
        View decorView = getWindow().getDecorView();
        applyDayNightForView(decorView);
    }

    /**
     * 回调接口 给具体控件换肤操作
     */
    private void applyDayNightForView(View view) {
        if (view instanceof ViewMatch) {
            ViewMatch viewMatch = (ViewMatch) view;
            viewMatch.skinnableView();
        }

        if (view instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) view;
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                applyDayNightForView(parent.getChildAt(i));
            }
        }
    }
}
