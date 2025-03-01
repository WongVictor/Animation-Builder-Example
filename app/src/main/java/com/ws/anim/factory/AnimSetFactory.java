package com.ws.anim.factory;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.NonNull;

import com.ws.anim.bean.AnimItem;
import com.ws.anim.bean.AnimSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/03/01
 *     desc   :
 * </pre>
 */
public class AnimSetFactory {
    private static final String TAG = "AnimSetFactory";
    private static final int LINEAR_INTERPOLATOR = 1;
    private static final int ACCELERATE_INTERPOLATOR = 2;
    private static final int ACCELERATE_DECELERATE_INTERPOLATOR = 3;
    private static final int DECELERATE_INTERPOLATOR = 4;
    private static final int ANTICIPATE_INTERPOLATOR = 5;
    private static final int ANTICIPATE_OVERSHOOT_INTERPOLATOR = 6;
    private static final int OVERSHOOT_INTERPOLATOR = 7;
    private static final int BOUNCE_INTERPOLATOR = 8;

    private static final int ALPHA = 0;
    private static final int SCALE_X = 1;
    private static final int SCALE_Y = 2;
    private static final int TRANS_X = 3;
    private static final int TRANS_Y = 4;
    private static final int ROTATION = 5;
    private static final int ROTATION_X = 6;
    private static final int ROTATION_Y = 7;

    /**
     * 创建动画集合
     *
     * @param animSet 动画集合实体
     * @param view    作用控件
     * @return 动画集合
     */
    public static Optional<AnimatorSet> createAnimatorSet(@NonNull AnimSet animSet, @NonNull View view) {
        if (animSet.getAnimItemList() == null || animSet.getAnimItemList().size() == 0) {
            return Optional.empty();
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (animSet.getDuration() != 0) {
            animatorSet.setDuration(animSet.getDuration());
        }
        animatorSet.setStartDelay(animSet.getDelay());
        if (getInterpolator(animSet.getInterpolator()).isPresent()) {
            getInterpolator(animSet.getInterpolator());
        }

        List<Animator> animatorList = new ArrayList<>();
        for (AnimItem item : animSet.getAnimItemList()) {
            if (createObjectAnimator(item, view).isPresent()) {
                animatorList.add(createObjectAnimator(item, view).get());
            }
        }
        animatorSet.playTogether(animatorList);
        return Optional.of(animatorSet);
    }

    private static Optional<Animator> createObjectAnimator(AnimItem animItem, View view) {
        if (getPropertyName(animItem.getType()).isPresent()) {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, getPropertyName(animItem.getType()).get(), animItem.getStart(), animItem.getEnd());
            objectAnimator.setDuration(animItem.getDuration());
            objectAnimator.setStartDelay(animItem.getDelay());
            if (getInterpolator(animItem.getInterpolator()).isPresent()) {
                objectAnimator.setInterpolator(getInterpolator(animItem.getInterpolator()).get());
            }
            return Optional.of(objectAnimator);
        }
        return Optional.empty();
    }

    private static Optional<TimeInterpolator> getInterpolator(int interpolator) {
        TimeInterpolator timeInterpolator;
        switch (interpolator) {
            case LINEAR_INTERPOLATOR:
                timeInterpolator = new LinearInterpolator();
                break;
            case ACCELERATE_INTERPOLATOR:
                timeInterpolator = new AccelerateInterpolator();
                break;
            case ACCELERATE_DECELERATE_INTERPOLATOR:
                timeInterpolator = new AccelerateDecelerateInterpolator();
                break;
            case DECELERATE_INTERPOLATOR:
                timeInterpolator = new DecelerateInterpolator();
                break;
            case ANTICIPATE_INTERPOLATOR:
                timeInterpolator = new AnticipateInterpolator();
                break;
            case ANTICIPATE_OVERSHOOT_INTERPOLATOR:
                timeInterpolator = new AnticipateOvershootInterpolator();
                break;
            case OVERSHOOT_INTERPOLATOR:
                timeInterpolator = new OvershootInterpolator();
                break;
            case BOUNCE_INTERPOLATOR:
                timeInterpolator = new BounceInterpolator();
                break;
            default:
                Log.e(TAG, "Error Interpolator");
                timeInterpolator = null;
                break;
        }
        if (timeInterpolator == null) {
            return Optional.empty();
        }
        return Optional.of(timeInterpolator);
    }

    private static Optional<String> getPropertyName(int type) {
        String propertyName = null;
        switch (type) {
            case ALPHA:
                propertyName = "alpha";
                break;
            case SCALE_X:
                propertyName = "scaleX";
                break;
            case SCALE_Y:
                propertyName = "scaleY";
                break;
            case TRANS_X:
                propertyName = "translationX";
                break;
            case TRANS_Y:
                propertyName = "translationY";
                break;
            case ROTATION:
                propertyName = "rotation";
                break;
            case ROTATION_X:
                propertyName = "rotationX";
                break;
            case ROTATION_Y:
                propertyName = "rotationY";
                break;
            default:
                Log.e(TAG, "Error Anim Type");
                break;
        }
        if (propertyName == null) {
            return Optional.empty();
        }
        return Optional.of(propertyName);
    }
}
