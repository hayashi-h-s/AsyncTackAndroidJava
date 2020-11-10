package com.e.asynctaskandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TestTask testTask;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        Button  buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ボタンをタップして非同期処理を開始
                testTask = new TestTask();
                // Listenerを設定
                testTask.setListener(createListener());
                testTask.execute(0);
            }
        });

        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(0));
            }
        });
    }

    @Override
    protected void onDestroy() {
        testTask.setListener(null);
        super.onDestroy();
    }

    private TestTask.Listener createListener() {
        return new TestTask.Listener() {
            @Override
            public void onSuccess(int count) {
                textView.setText(String.valueOf(count));
            }
        };
    }

}