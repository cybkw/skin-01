package com.bkw.skinlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.bkw.skinlibrary.R;
import com.bkw.skinlibrary.bean.AttrsBean;
import com.bkw.skinlibrary.core.ViewMatch;

public class SkinnableRelativeLayout extends RelativeLayout implements ViewMatch {
    private AttrsBean attrsBean;

    public SkinnableRelativeLayout(Context context) {
        this(context, null);
    }

    public SkinnableRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        attrsBean = new AttrsBean();
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableRelativeLayout, defStyleAttr, 0);

        //临时存储自定义属性
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableRelativeLayout);
        typedArray.recycle();
    }


    @Override
    public void skinnableView() {
        //根据自定义属性，获取styleable中的属性值
        int key = R.styleable.SkinnableRelativeLayout[R.styleable.SkinnableRelativeLayout_android_background];

        int backgroudColor = attrsBean.getViewResource(key);
        if (backgroudColor > 0) {
            //兼容包转换
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroudColor);
            //赋值
            setBackground(drawable);
        }
    }
}
