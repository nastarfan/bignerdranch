package com.thebign.quiz.mysaveinstancestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mMainEdit;
    private TextView mMainTv;
    private TextView mMainTv2;
    private Button mKopas;

    private static final String KEY_TV1 = "textview1";
    private static final String KEY_TV2 = "textview2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainEdit = (EditText) findViewById(R.id.main_text);
        mMainTv = (TextView) findViewById(R.id.main_tv);
        mMainTv2 = (TextView) findViewById(R.id.main_tv2);
        mKopas = (Button) findViewById(R.id.kopas_button);

        mMainEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMainTv.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mKopas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainTv2.setText(mMainTv.getText());
            }
        });

        if(savedInstanceState != null){
            mMainTv.setText(savedInstanceState.getString(KEY_TV1));
            mMainTv2.setText(savedInstanceState.getString(KEY_TV2));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_TV1, mMainTv.getText().toString());
        savedInstanceState.putString(KEY_TV2, mMainTv2.getText().toString());
    }
}
