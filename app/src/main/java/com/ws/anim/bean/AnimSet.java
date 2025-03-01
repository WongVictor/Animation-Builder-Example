package com.ws.anim.bean;

import java.util.List;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/03/01
 *     desc   :
 * </pre>
 */
public class AnimSet {
    private long duration;

    private long delay;

    /**
     * 插值器类型
     * 1-Linear
     */
    private int interpolator;

    private List<AnimItem> animItemList;

    public AnimSet() {
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

    public List<AnimItem> getAnimItemList() {
        return animItemList;
    }

    public void setAnimItemList(List<AnimItem> animItemList) {
        this.animItemList = animItemList;
    }
}
