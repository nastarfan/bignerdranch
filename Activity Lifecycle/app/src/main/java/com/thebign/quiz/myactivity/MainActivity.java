package com.thebign.quiz.myactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MyActivity";
    private TextView main_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_tv = (TextView) findViewById(R.id.main_tv);

        String message = "onCreate() is invoked";
        main_tv.setText(main_tv.getText() + message + "\n");
    }

    @Override
    protected void onPause() {
        super.onPause();
        String message = "onPause() is invoked";
        main_tv.setText(main_tv.getText() + message + "\n");
        Log.d(TAG, message);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String message = "onResume() is invoked";
        main_tv.setText(main_tv.getText() + message + "\n");
        Log.d(TAG, message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String message = "onStop() is invoked";
        main_tv.setText(main_tv.getText() + message + "\n");
        Log.d(TAG, message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String message = "onStart() is invoked";
        main_tv.setText(main_tv.getText() + message + "\n");
        Log.d(TAG, message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String message = "onDestroy() is invoked";
        main_tv.setText(main_tv.getText() + message + "\n");
        Log.d(TAG, message);
    }
}
