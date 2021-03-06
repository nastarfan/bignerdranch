package com.thebign.quiz.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;


    public Crime() {
        // Generate unique identifier
        mID = UUID.randomUUID();
        // mDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        mDate = cal.getTime();
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
