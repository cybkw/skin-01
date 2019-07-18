package com.bkw.skinlibrary.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;

import com.bkw.skinlibrary.R;
import com.bkw.skinlibrary.bean.AttrsBean;
import com.bkw.skinlibrary.core.ViewMatch;

public class SkinnableButton extends AppCompatButton implements ViewMatch {
    private AttrsBean attrsBean;

    public SkinnableButton(Context context) {
        this(context,null);
    }

    public SkinnableButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SkinnableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        attrsBean = new AttrsBean();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableButton, defStyleAttr, 0);
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableButton);
        typedArray.recycle();
    }


    @Override
    public void skinnableView() {
        int key = R.styleable.SkinnableButton[R.styleable.SkinnableButton_android_background];
        Log.e("TAG",""+key);

        int backgroudColor = attrsBean.getViewResource(key);
        if (backgroudColor > 0) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), backgroudColor);
            setBackground(drawable);
        }

        key = R.styleable.SkinnableButton[R.styleable.SkinnableButton_android_textColor];
        int textColor = attrsBean.getViewResource(key);
        if (textColor > 0) {
            ColorStateList colorStateList = ContextCompat.getColorStateList(getContext(), textColor);
            setTextColor(colorStateList);
        }
    }
}
