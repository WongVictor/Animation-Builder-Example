package com.ws.anim.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ws.anim.R;
import com.ws.anim.ui.MotionEventActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_main);
        button.setOnClickListener(v->{
//            startIt(AnimBarActivity.class);
            startIt(MotionEventActivity.class);
        });

    }

    private void startIt(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}