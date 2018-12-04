package com.thebign.quiz.simplequiz2;

public class Question {
    private int mResId;
    private boolean mAnswerTrue;

    public Question(int resId, boolean answerTrue) {
        mResId = resId;
        mAnswerTrue = answerTrue;
    }

    public int getResId() {
        return mResId;
    }

    public void setResId(int resId) {
        mResId = resId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
