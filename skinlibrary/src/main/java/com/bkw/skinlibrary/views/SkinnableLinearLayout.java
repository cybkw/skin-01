package com.bkw.skinlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bkw.skinlibrary.R;
import com.bkw.skinlibrary.bean.AttrsBean;
import com.bkw.skinlibrary.core.ViewMatch;

public class SkinnableLinearLayout extends LinearLayout implements ViewMatch {
    private AttrsBean attrsBean;

    public SkinnableLinearLayout(Context context) {
        this(context, null);
    }

    public SkinnableLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsBean = new AttrsBean();

        //根据自定义属性，匹配控件属性的类型集合，如：backgroud
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableLinearLayout, defStyleAttr, 0);

        //存储到临时JavaBean对象
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableLinearLayout);
        //回收
        typedArray.recycle();
    }

    @Override
    public void skinnableView() {
        //根据自定义属性，获取styleable中的属性
        int key = R.styleable.SkinnableLinearLayout[R.styleable.SkinnableLinearLayout_android_background];
        //根据styleable获取控件某属性的resourceId
        int backgroudResourceId = attrsBean.getViewResource(key);
        if (backgroudResourceId > 0) {
            //兼容包转换
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroudResourceId);
            //控件自带api,这里不用setBackgroudColor,因为在9.0测试不通过
            //setBackgroudDrawable过时了
            setBackground(drawable);
        }
    }
}
