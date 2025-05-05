package com.ws.anim.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/05/05
 *     desc   :
 * </pre>
 */
public class CustomViewPager extends ViewPager {
    public CustomViewPager(@NonNull Context context) {
        this(context, null);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
