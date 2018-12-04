package com.thebign.quiz.intentcommunication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private static final String TAG = "com.bignerd.main";

    private Button mLeftButton;
    private Button mRightButton;
    private Button mParentCheckButton;
    private Button mCallChildButton;

    private String mParentLastClick;
    private String mChildLastClick;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLeftButton = (Button) findViewById(R.id.left_button);
        mRightButton = (Button) findViewById(R.id.right_button);
        mParentCheckButton = (Button) findViewById(R.id.parent_check_button);
        mCallChildButton = (Button) findViewById(R.id.call_child_button);

        mLeftButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mParentLastClick = getString(R.string.left_button);
                makeToast(mParentLastClick + " is clicked");
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mParentLastClick = getString(R.string.right_button);
                makeToast(mParentLastClick + " is clicked");
            }
        });

        mParentCheckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "the value of last child click is " + mChildLastClick);
                if(mChildLastClick == null)
                    makeToast(getString(R.string.message_none));
                else if(mChildLastClick.equals(getString(R.string.a_button)))
                    makeToast(getString(R.string.message_a));
                else
                    makeToast(getString(R.string.message_b));
            }
        });

        mCallChildButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent childIntent = ChildActivity.makeIntent(MainActivity.this, mParentLastClick);
//                startActivity(childIntent);
                startActivityForResult(childIntent, REQUEST_CODE);
            }
        });
    }

    private void makeToast(String message) {
        if(mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mToast != null)
            mToast.cancel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "checking result data..");
        if(resultCode != Activity.RESULT_OK) {
            Log.d(TAG, "The result is not oke..");
            return;
        }
        if(requestCode != REQUEST_CODE) {
            Log.d(TAG, "The requestCode doesn't match..");
            return;
        }
        mChildLastClick = ChildActivity.lastChildClick(data);
        Log.d(TAG, "child last click value " + mChildLastClick);
    }
}
