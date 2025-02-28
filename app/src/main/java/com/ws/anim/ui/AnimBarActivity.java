package com.ws.anim.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ws.anim.R;
import com.ws.anim.factory.AnimFactory;
import com.ws.anim.ui.base.BaseActivity;

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
            AnimFactory animFactory = new AnimFactory.Builder(humImageView)
                    .addTransX(-dp2px(100), dp2px(300), 2000, null)
                    .addAlpha(0f, 1f, 1000, null)
                    .build();
            animFactory.get().start();
        });

        Button btn2 = findViewById(R.id.btn_play_2);
        btn2.setOnClickListener(v->{
            AnimFactory animFactory = new AnimFactory.Builder(humImageView)
                    .addTransX(dp2px(200), dp2px(300), 1000, null)
                    .addAlpha(0f, 1f, 1000, null)
                    .build();
            animFactory.get().start();
        });
    }

    private void playObjectAnim() {
        AnimFactory animFactory = new AnimFactory.Builder(humImageView)
                .addTransX(-dp2px(100), dp2px(300), 2000, null)
                .addAlpha(0f, 1f, 1000, null)
                .build();
        animFactory.get().start();

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