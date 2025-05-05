package com.ws.anim.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ws.anim.utils.ColorUtil;
import com.ws.anim.utils.LogUtil;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/05/05
 *     desc   :
 * </pre>
 */
public class BasicItemView extends FrameLayout {
    public BasicItemView(@NonNull Context context) {
        this(context, null , -1, -1);
    }

    public BasicItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1, -1);
    }

    public BasicItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public BasicItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
    }

    private void initViews() {
        int randomColor = ColorUtil.getRandColor();
        setBackgroundColor(randomColor);
        TextView textView = new TextView(getContext());
        textView.setTextColor(ColorUtil.getFitColor(randomColor));
        textView.setText(String.valueOf(randomColor));
        addView(textView);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.showToast(getContext(), "Item is Click");
            }
        });
    }
}
