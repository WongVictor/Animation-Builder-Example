package com.ws.anim.factory;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/03/01
 *     desc   :
 * </pre>
 */
public class AnimFactory {
    private final AnimatorSet animatorSet;

    /**
     * 构造函数
     *
     * @param builder 构造器
     */
    public AnimFactory(Builder builder) {
        this.animatorSet = builder.animatorSet;
    }

    /**
     * 获取结合
     *
     * @return 动画集合
     */
    public AnimatorSet get() {
        return animatorSet;
    }

    public static class Builder {

        private final View view;

        private final AnimatorSet animatorSet;

        private final List<Animator> animatorList;

        public Builder(View view) {
            this.view = view;
            animatorList = new ArrayList<>();
            animatorSet = new AnimatorSet();
        }

        private ObjectAnimator createAnimator(String name, float start, float end, long duration, TimeInterpolator interpolator) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, name, start, end);
            animator.setDuration(duration);
            if (interpolator != null) {
                animator.setInterpolator(interpolator);
            }
            return animator;
        }

        /**
         * 添加横向缩放动画
         *
         * @param start        初始值
         * @param end          结束值
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addScaleX(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("scaleX", start, end, duration, interpolator));
            return this;
        }


        /**
         * 添加纵向缩放动画
         *
         * @param start        初始值
         * @param end          结束值
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addScaleY(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("scaleY", start, end, duration, interpolator));
            return this;
        }

        /**
         * 添加横向位移动画
         *
         * @param start        初始值
         * @param end          结束值
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addTransX(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("translationX", start, end, duration, interpolator));
            return this;
        }

        /**
         * 添加纵向位移动画
         *
         * @param start        初始值
         * @param end          结束值
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addTransY(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("translationY", start, end, duration, interpolator));
            return this;
        }

        /**
         * 添加透明度动画
         *
         * @param start        初始值-[0f, 1f]
         * @param end          结束值-[0f, 1f]
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addAlpha(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("alpha", start, end, duration, interpolator));
            return this;
        }

        /**
         * 添加旋转动画
         *
         * @param start        初始值-正值表示顺时针,负值表示逆时针
         * @param end          结束值-正值表示顺时针,负值表示逆时针
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addRotation(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("rotation", start, end, duration, interpolator));
            return this;
        }

        /**
         * 添加X轴翻转动画
         *
         * @param start        初始值-正值表示顺时针,负值表示逆时针
         * @param end          结束值-正值表示顺时针,负值表示逆时针
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addRotationX(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("rotationX", start, end, duration, interpolator));
            return this;
        }

        /**
         * 添加Y轴翻转动画
         *
         * @param start        初始值-正值表示顺时针,负值表示逆时针
         * @param end          结束值-正值表示顺时针,负值表示逆时针
         * @param duration     持续周期
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder addRotationY(float start, float end, long duration, TimeInterpolator interpolator) {
            animatorList.add(createAnimator("rotationY", start, end, duration, interpolator));
            return this;
        }

        /**
         * 设置动画集合持续时间
         * 注:集合的时间会覆盖单项动画的持续时间
         *
         * @param duration 持续时间
         * @return Builder
         */
        public AnimFactory.Builder setDuration(long duration) {
            if (duration != 0f) {
                animatorSet.setDuration(duration);
            }
            return this;
        }

        /**
         * 设置动画集合插值器
         * 注:集合的时间会覆盖单项动画的插值器
         *
         * @param interpolator 插值器
         * @return Builder
         */
        public AnimFactory.Builder setDuration(TimeInterpolator interpolator) {
            if (interpolator != null) {
                animatorSet.setInterpolator(interpolator);
            }
            return this;
        }

        /**
         * 设置集合播放延时
         *
         * @param delay 延时时间
         * @return Builder
         */
        public AnimFactory.Builder setDelay(long delay) {
            animatorSet.setStartDelay(delay);
            return this;
        }

        /**
         * 构造集合
         *
         * @return AnimFactory
         */
        public AnimFactory build() {
            animatorSet.playTogether(animatorList);
            return new AnimFactory(this);
        }
    }
}
