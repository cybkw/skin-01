package com.bkw.skinlibrary.core;

import android.content.Context;
import android.support.v7.app.AppCompatViewInflater;
import android.util.AttributeSet;
import android.view.View;

import com.bkw.skinlibrary.views.SkinnableButton;
import com.bkw.skinlibrary.views.SkinnableImageView;
import com.bkw.skinlibrary.views.SkinnableTextView;
import com.bkw.skinlibrary.views.SkinnableLinearLayout;
import com.bkw.skinlibrary.views.SkinnableRelativeLayout;

public class MyCompatViewInflater extends AppCompatViewInflater {
    /**
     * 控件名
     */
    private String name;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 每个控件对应的所有属性
     */
    private AttributeSet attrs;


    public MyCompatViewInflater(Context context) {
        this.context = context;
    }


    public void setAttrs(AttributeSet attrs) {
        this.attrs = attrs;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 自动匹配控件名，并初始化控件对象
     * 这里只是表示自定义控件的初始化
     * 如果这里没有的就会调用父类AppCompatViewInflater里的实例化
     */
    public View autoMatch() {
        View view = null;
        switch (name) {
            case "LinearLayout":
                //源码写法
//                view=super.createTextView()
                view = new SkinnableLinearLayout(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "RelativeLayout":
                view = new SkinnableRelativeLayout(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "TextView":
                view = new SkinnableTextView(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "ImageView":
                view = new SkinnableImageView(context, attrs);
                this.verifyNotNull(view, name);
                break;
            case "Button":
                view = new SkinnableButton(context, attrs);
                this.verifyNotNull(view, name);
                break;
            default:
                break;
        }
        return view;
    }

    private void verifyNotNull(View view, String name) {
        if (view == null) {
            throw new IllegalStateException(this.getClass().getName() + " asked to inflate view for <" + name + ">, but returned null");
        }
    }
}
