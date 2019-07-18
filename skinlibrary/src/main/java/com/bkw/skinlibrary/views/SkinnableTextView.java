package com.bkw.skinlibrary.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.bkw.skinlibrary.R;
import com.bkw.skinlibrary.bean.AttrsBean;
import com.bkw.skinlibrary.core.ViewMatch;

public class SkinnableTextView extends AppCompatTextView implements ViewMatch {
    private AttrsBean attrsBean;

    public SkinnableTextView(Context context) {
        this(context, null);
    }

    public SkinnableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinnableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        attrsBean = new AttrsBean();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableTextView, defStyleAttr, 0);

        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableTextView);
        //回收
        typedArray.recycle();
    }

    @Override
    public void skinnableView() {
        //获取自定义属性，兼容包转换
        int key = R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_android_background];
        //根据styleable获取控件某属性的resouceId
        int backgroudColor = attrsBean.getViewResource(key);
        if (backgroudColor > 0) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroudColor);
            setBackgroundDrawable(drawable);
        }

        key = R.styleable.SkinnableTextView[R.styleable.SkinnableTextView_android_textColor];
        int textColorId = attrsBean.getViewResource(key);
        if (textColorId > 0) {
            ColorStateList colorStateList = ContextCompat.getColorStateList(getContext(), textColorId);
            setTextColor(colorStateList);
        }


    }
}
