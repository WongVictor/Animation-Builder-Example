package com.ws.anim.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/05/05
 *     desc   : 屏幕工具类
 * </pre>
 */
public class DisplayUtil {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
