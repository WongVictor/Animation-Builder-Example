package com.ws.anim.ui;

import androidx.appcompat.content.res.AppCompatResources;

import android.animation.AnimatorSet;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ws.anim.R;
import com.ws.anim.bean.AnimItem;
import com.ws.anim.bean.AnimSet;
import com.ws.anim.factory.AnimBuilder;
import com.ws.anim.factory.AnimSetFactory;
import com.ws.anim.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimBarActivity extends BaseActivity {

    private ImageView humImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_anim_bar;
    }

    @Override
    protected void initData() {
        initViews();
    }

    private void initViews() {
        humImageView = findViewById(R.id.img_cra);
        Glide.with(this)
                .load(AppCompatResources.getDrawable(this, R.drawable.human))
                .into(humImageView);

        Button btn1 = findViewById(R.id.btn_play_1);
        btn1.setOnClickListener(v->{
            Optional<AnimatorSet> optional = AnimSetFactory.createAnimatorSet(initAnimSet(), humImageView);
            optional.ifPresent(AnimatorSet::start);
        });

        Button btn2 = findViewById(R.id.btn_play_2);
        btn2.setOnClickListener(v->{
            AnimBuilder animBuilder = new AnimBuilder.Builder(humImageView)
                    .addTransX(dp2px(200), dp2px(300), 1000, null)
                    .addAlpha(0f, 1f, 1000, null)
                    .build();
            animBuilder.get().start();
        });
    }

    private AnimSet initAnimSet(){
        AnimSet animSet = new AnimSet();
//        animSet.setDuration(1000);
        animSet.setInterpolator(3);

        List<AnimItem> animItemList = new ArrayList<>();

        AnimItem animItem1 = new AnimItem();
        animItem1.setType(0);
        animItem1.setStart(0f);
        animItem1.setEnd(1f);
        animItem1.setDuration(2000);
        animItem1.setInterpolator(1);
        animItemList.add(animItem1);

        AnimItem animItem2 = new AnimItem();
        animItem2.setType(3);
        animItem2.setStart(0f);
        animItem2.setEnd(200f);
        animItem2.setDuration(1000);
        animItem2.setInterpolator(1);
        animItemList.add(animItem2);

        animSet.setAnimItemList(animItemList);
        return animSet;
    }

    private void playObjectAnim() {
        AnimBuilder animBuilder = new AnimBuilder.Builder(humImageView)
                .addTransX(-dp2px(100), dp2px(300), 2000, null)
                .addAlpha(0f, 1f, 1000, null)
                .build();
        animBuilder.get().start();

    }

    private void playValueAnim() {
        humImageView.setTranslationX(-dp2px(100));
        humImageView.setAlpha(0f);
        humImageView.animate()
                .translationX(dp2px(300))
                .alphaBy(1)
                .setDuration(2000)
                .start();
    }
}