package com.bkw.skinlibrary.bean;

import android.content.res.TypedArray;
import android.util.SparseIntArray;

/**
 * 控件的JavaBean对象，用于存储控件的key,value
 * 如：key=android:textColor,value="@color/xx"
 *
 * @author bkw
 */
public class AttrsBean {
    private SparseIntArray resourcesMap;
    private static final int DEFAULT_VALUE = -1;

    public AttrsBean() {
        resourcesMap = new SparseIntArray();
    }

    /**
     * 存储控件key,value
     */
    public void saveViewResource(TypedArray typedArray, int[] styleable) {
        for (int i = 0; i < typedArray.length(); i++) {
            int key = styleable[i];
            int resourceId = typedArray.getResourceId(i, DEFAULT_VALUE);
            resourcesMap.put(key, resourceId);
        }
    }

    /**
     * 获取控件某属性的resourceId
     *
     * @param styleable 自定义属性，参考attrs.xml
     * @return 某控件某属性的resourceId
     */
    public int getViewResource(int styleable) {
        return resourcesMap.get(styleable);
    }

}
