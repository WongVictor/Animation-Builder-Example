package com.ws.anim.utils;

import android.graphics.Color;

import androidx.core.graphics.ColorUtils;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/03/09
 *     desc   :
 * </pre>
 */
public class Colors {
    private static final float MIN_ALPHA_CONTRAST = 3.0f; // 透明度最小对比度

    public static int getAntiColor(int sPixel) {
        int sA = (sPixel >> 24) & 0xff;
        int sR = (sPixel >> 16) & 0xff;

        int sG = (sPixel >>  8) & 0xff;

        int sB = (sPixel) & 0xff;

        sR = 255 - sR;
        sG = 255 - sG;
        sB = 255 - sB;

        return Color.argb(sA, sR, sG, sB);
    }

    /**
     * 获取适合在背景色下正常显示的前景色
     * 该方法改编自Platte的ensureTextColorsGenerated()函数
     *
     * @param color 背景色
     * @return 合适的前景色
     */
    public static int getFitColor(int color) {
        int fitColor;

        // 深色背景下，前景色的最小透明度
        final int lightForeAlpha = ColorUtils.calculateMinimumAlpha(
                Color.WHITE, color, MIN_ALPHA_CONTRAST);

        // 符合透明度要求，设置前景色为最小透明度的白色
        if (lightForeAlpha != -1) {
            fitColor = ColorUtils.setAlphaComponent(Color.WHITE, lightForeAlpha);
            return fitColor;
        }

        // 浅色背景下，前景色的最小透明度
        final int darkForeAlpha = ColorUtils.calculateMinimumAlpha(
                Color.BLACK, color, MIN_ALPHA_CONTRAST);

        // 符合透明度要求，设置前景色为最小透明度的黑色
        if (darkForeAlpha != -1) {
            fitColor = ColorUtils.setAlphaComponent(Color.BLACK, darkForeAlpha);
            return fitColor;
        }

        // 都不符合时，根据颜色亮度设置前景色
        double colorLightness = ColorUtils.calculateLuminance(color);
        fitColor =  colorLightness >= 0.5f ? ColorUtils.setAlphaComponent(Color.BLACK, darkForeAlpha) : ColorUtils.setAlphaComponent(Color.WHITE, lightForeAlpha);
        return fitColor;
    }
}
