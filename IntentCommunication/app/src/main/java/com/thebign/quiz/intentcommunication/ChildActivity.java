package com.thebign.quiz.intentcommunication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChildActivity extends AppCompatActivity {
    private static final String EXTRA_PARENT_BUTTON =
            "com.thebign.IntentCommunication.parent_button";
    private static final String EXTRA_CHILD_BUTTON =
            "com.thebign.IntentCommunication.child_button";

    private static final String TAG = "com.thebign";

    private Button mAButton;
    private Button mBButton;
    private Button mCheckParentButton;

    private String mParentLastClick;
    private String mChildLastClick;

    private Toast mToast;

    public static Intent makeIntent(Context context, String parentLastClick) {
        Intent childIntent = new Intent(context, ChildActivity.class);
        childIntent.putExtra(EXTRA_PARENT_BUTTON, parentLastClick);
        return childIntent;
    }

    public static String lastChildClick(Intent result){
        return result.getStringExtra(EXTRA_CHILD_BUTTON);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        mAButton = (Button) findViewById(R.id.a_button);
        mBButton = (Button) findViewById(R.id.b_button);
        mCheckParentButton = (Button) findViewById(R.id.child_check_button);

        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChildLastClick = getString(R.string.a_button);
                makeToast(getString(R.string.a_button) + " is clicked");
                setAnswerChildLastClick();
            }
        });

        mBButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mChildLastClick = getString(R.string.b_button);
                makeToast(getString(R.string.b_button) + " is clicked");
                setAnswerChildLastClick();
            }
        });

        mCheckParentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mParentLastClick == null)
                    makeToast(getString(R.string.message_none));
                else if(mParentLastClick.equals(getString(R.string.left_button)))
                    makeToast(getString(R.string.message_left));
                else
                    makeToast(getString(R.string.message_right));
            }
        });

        mParentLastClick = getIntent().getStringExtra(EXTRA_PARENT_BUTTON);
    }

    private void setAnswerChildLastClick() {
        Intent data = new Intent();
        data.putExtra(EXTRA_CHILD_BUTTON, mChildLastClick);
        setResult(Activity.RESULT_OK, data);
    }

    private void makeToast(String message) {
        if(mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(ChildActivity.this, message, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mToast != null)
            mToast.cancel();
        Log.d(TAG, "returning the result..");
    }
}
