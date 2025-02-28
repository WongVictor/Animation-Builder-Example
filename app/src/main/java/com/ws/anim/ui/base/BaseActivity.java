package com.ws.anim.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * <pre>
 *     author : WongVictor
 *     time   : 2025/03/01
 *     desc   :
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    private enum State{
        OnCreate,
        OnStart,
        OnRestart,
        OnResume,
        OnPause,
        OnStop,
        OnDestroy
    }

    protected Context mContext;

    /**
     * 获取布局ID
     *
     * @return LayoutId
     */
    protected abstract int getLayoutId();

    /**
     * 数据初始化操作
     */
    protected abstract void initData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logState(State.OnCreate);
        mContext = new WeakReference<>(this).get();
        setContentView(getLayoutId());
        this.initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logState(State.OnStart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logState(State.OnResume);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logState(State.OnRestart);
    }

    @Override
    protected void onPause() {
        super.onPause();
        logState(State.OnPause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        logState(State.OnStop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logState(State.OnDestroy);
    }

    protected float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    protected void Log(String msg) {
        Log.d(TAG, msg);
    }

    private void logState(State state) {
        Log(String.valueOf(state));
    }

    protected void showToast(String msg) {
        if(isFinishing()) return;
        runOnUiThread(()-> Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show());
    }
}
