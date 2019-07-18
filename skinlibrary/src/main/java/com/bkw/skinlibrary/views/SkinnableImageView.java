package com.bkw.skinlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bkw.skinlibrary.R;
import com.bkw.skinlibrary.bean.AttrsBean;
import com.bkw.skinlibrary.core.ViewMatch;

public class SkinnableImageView extends AppCompatImageView implements ViewMatch {
    private AttrsBean attrsBean;

    public SkinnableImageView(Context context) {
        this(context,null);
    }

    public SkinnableImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SkinnableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attrsBean = new AttrsBean();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SkinnableImageView, defStyleAttr, 0);
        attrsBean.saveViewResource(typedArray, R.styleable.SkinnableImageView);
        typedArray.recycle();
    }

    @Override
    public void skinnableView() {
        int key = R.styleable.SkinnableImageView[R.styleable.SkinnableImageView_android_src];
        int srcId = attrsBean.getViewResource(key);
        if (srcId > 0) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), srcId);
            setImageDrawable(drawable);
        }

    }
}
