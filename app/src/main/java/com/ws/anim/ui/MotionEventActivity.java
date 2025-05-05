package com.ws.anim.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.ws.anim.R;
import com.ws.anim.adapter.AppBarStateChangeListener;
import com.ws.anim.adapter.BasicRecyclerAdapter;
import com.ws.anim.adapter.BasicViewpagerAdapter;
import com.ws.anim.ui.view.BasicItemView;
import com.ws.anim.ui.view.CustomFrameLayout;
import com.ws.anim.ui.view.CustomViewPager;
import com.ws.anim.ui.view.DragView;
import com.ws.anim.utils.ColorUtil;
import com.ws.anim.utils.LogUtil;

import java.util.ArrayList;

public class MotionEventActivity extends AppCompatActivity {
    private final float FLIP_DISTANCE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
        ArrayList<View> itemViews = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            itemViews.add(new BasicItemView(this));
        }

        ViewPager viewPager = findViewById(R.id.vp_view_pager);
        viewPager.setAdapter(new BasicViewpagerAdapter(itemViews));

        RecyclerView recyclerView = findViewById(R.id.recycler);
        CustomLinearManager manager = new CustomLinearManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new BasicRecyclerAdapter(this, 16, BasicRecyclerAdapter.RECYCLER_TYPE));

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if( state == State.EXPANDED ) {
//                    manager.setCanScroll(false);
                    //展开状态

                }else if(state == State.COLLAPSED){
                    recyclerView.stopNestedScroll();
                    manager.setCanScroll(false);
                    recyclerView.smoothScrollToPosition(0);
                    manager.setCanScroll(true);
//                    manager.setCanScroll(true);
                    //折叠状态

                }else {
//                    manager.setCanScroll(false);
                    //中间状态

                }
            }
        });

    }

    class CustomLinearManager extends LinearLayoutManager{
        private boolean canScroll = true;
        public CustomLinearManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public void setCanScroll(boolean canScroll) {
            this.canScroll = canScroll;
        }
        @Override
        public boolean canScrollVertically() {
            return canScroll;
        }
    }


}