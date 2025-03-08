package com.ws.anim.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;

import com.ws.anim.R;
import com.ws.anim.utils.Colors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/03/03
 *     desc   :
 * </pre>
 */
public class ColorCalculateView extends FrameLayout {
    private ExecutorService executorService;
    public ColorCalculateView(Context context) {
        super(context);
        executorService = Executors.newSingleThreadExecutor();
        LayoutInflater.from(context).inflate(R.layout.view_color_calculate, this);
    }

    private void setButtonsColor(Bitmap bitmap) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if (!executorService.isShutdown()) {
                    int height = bitmap.getHeight() / 4;
                    int width = bitmap.getWidth() / 2;
                    Bitmap scaledBitmapTop = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, 0, 0,width, height), 1, 1, false);    //Resize the bitmap to 1x1
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            @ColorInt int color = Color.WHITE;
                            if (ColorUtils.calculateLuminance(scaledBitmapTop.getPixel(0, 0)) >= 0.5f){ // 亮色
                                color = Color.BLACK;
                            }
                            findViewById(R.id.view_color_show).setBackgroundColor(color);

                        }
                    });

                    Bitmap scaledBitmapBottom = Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, width, bitmap.getHeight() - height, width, height), 5, 5, false);    //Resize the bitmap to 1x1
                    int color = Colors.getFitColor(scaledBitmapBottom.getPixel(0, 0));
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.view_color_show).setBackgroundColor(color);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        executorService.shutdown();
        executorService.shutdownNow();
    }
}
