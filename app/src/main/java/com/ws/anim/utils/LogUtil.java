package com.ws.anim.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/05/05
 *     desc   : 日志工具类
 * </pre>
 */
public class LogUtil {

    /**
     * 快捷吐司提示
     *
     * @param context 上下文
     * @param str 提示文字
     */
    public static void showToast(@NonNull Context context, String str) {
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        });
    }

    public static void logger(String tag, String str) {
        Log.d(tag, str);
    }
}
