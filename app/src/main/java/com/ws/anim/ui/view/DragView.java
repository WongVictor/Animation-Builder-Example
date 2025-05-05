package com.ws.anim.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/05/05
 *     desc   :
 * </pre>
 */
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class DragView extends View {

    private int maxWidth;
    private int maxHeight;
    private int viewWidth;
    private int viewHeight;
    private float downx;
    private float downy;
    private Context mContext;

    private DisplayMetrics outMetrics;

    public DragView(Context context) {
        this(context, null);
    }

    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        outMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //屏幕的宽度
        maxWidth = outMetrics.widthPixels;
        //屏幕的高度
        maxHeight = outMetrics.heightPixels;
        /**
         * 控件的宽高
         */
        viewWidth = getWidth();
        viewHeight = getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                clearAnimation();
                downx = event.getX();
                downy = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getRawX() - downx;
                float moveY = event.getRawY() - downy;
                moveX = moveX < 0 ? 0 : (moveX + viewWidth > maxWidth) ? (maxWidth - viewWidth) : moveX;
                moveY = moveY < 0 ? 0 : (moveY + viewHeight) > maxHeight ? (maxHeight - viewHeight) : moveY;
                this.setY(moveY);
                this.setX(moveX);
                return true;
            case MotionEvent.ACTION_UP:
                //做吸附效果
                float centerY = getY() + viewHeight / 2;
                if (centerY > maxHeight / 2) {
                    //靠右吸附
                    animate().setInterpolator(new DecelerateInterpolator())
                            .setDuration(500)
                            .x(0)
                            .y(maxHeight - viewHeight)
                            .start();
//                    ObjectAnimator bottomAnim = ObjectAnimator.ofInt(maxHeight - viewHeight, "translationY");
//                    bottomAnim.setDuration(500);
//                    bottomAnim.setInterpolator(new DecelerateInterpolator());
//                    bottomAnim.start();
                } else {
                    animate().setInterpolator(new DecelerateInterpolator())
                            .setDuration(500)
                            .x(0)
                            .y(0)
                            .start();
//                    ObjectAnimator topAnim = ObjectAnimator.ofInt(0, "translationY");
//                    topAnim.setDuration(500);
//                    topAnim.setInterpolator(new DecelerateInterpolator());
//                    topAnim.start();
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
