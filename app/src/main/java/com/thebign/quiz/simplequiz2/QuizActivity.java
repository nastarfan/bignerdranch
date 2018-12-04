package com.thebign.quiz.simplequiz2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final String KEY_IS_CHEATING = "cheating";
    private static final String TAG = "MyQuiz";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button mCheatButton;
    private ImageButton mBackButton;
    private ImageButton mNextButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private TextView mQuestionTextView;
    private Toast mToast;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_asia, false),
        new Question(R.string.question_mideast, true),
        new Question(R.string.question_oceans, false)
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            mIsCheater = savedInstanceState.getBoolean(KEY_IS_CHEATING);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_tv);
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mBackButton = (ImageButton) findViewById(R.id.back_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent cheatActivity = new Intent(QuizActivity.this, CheatActivity.class);
                Intent cheatActivity =
                        CheatActivity.newIntent(QuizActivity.this, mQuestionBank[mCurrentIndex].isAnswerTrue());
//                startActivity(cheatActivity);
                startActivityForResult(cheatActivity, REQUEST_CODE_CHEAT);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex == 0)
                    mCurrentIndex = mQuestionBank.length - 1;
                else
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsCheater)
                    makeToast(R.string.judgment_toast);
                else if(checkAnswer(false))
                    makeToast(R.string.correct_toast);
                else
                    makeToast(R.string.wrong_toast);
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsCheater)
                    makeToast(R.string.judgment_toast);
                else if(checkAnswer(true))
                    makeToast(R.string.correct_toast);
                else
                    makeToast(R.string.wrong_toast);
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getResId();
        mQuestionTextView.setText(question);
    }

    private void makeToast(int rId) {
        if(mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(QuizActivity.this, rId, Toast.LENGTH_LONG);
        mToast.show();
    }

    private boolean checkAnswer(boolean answer) {
        return answer == mQuestionBank[mCurrentIndex].isAnswerTrue();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putBoolean(KEY_IS_CHEATING, mIsCheater);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_CODE_CHEAT) {
            if(data == null) {
                return;
            }
            // the inner implementation of data is within the CheatActivity, so let's ask him for the result
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }
}
