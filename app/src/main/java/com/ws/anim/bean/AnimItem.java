package com.ws.anim.bean;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/03/01
 *     desc   :
 * </pre>
 */
public class AnimItem {
    /**
     * 动画类型
     * 0-alpha 1-scaleX 2-scaleY 3-translationX 4-translationY
     * 5-rotation 6-rotationX 7-rotationY
     */
    private int type;

    /**
     * 起始值
     */
    private float start;

    /**
     * 结束值
     */
    private float end;

    /**
     * 持续时间
     */
    private long duration;

    /**
     * 延迟时间
     */
    private long delay;

    /**
     * 插值器类型
     * 1-Linear 2-Accelerate 3-AccelerateDecelerate 4-decelerate
     * 5-Anticipate 6-AnticipateOvershoot 7-Overshoot 8-Bounce
     */
    private int interpolator;

    public AnimItem() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getStart() {
        return start;
    }

    public void setStart(float start) {
        this.start = start;
    }

    public float getEnd() {
        return end;
    }

    public void setEnd(float end) {
        this.end = end;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public int getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(int interpolator) {
        this.interpolator = interpolator;
    }
}
