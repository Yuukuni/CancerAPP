package com.example.huangyuwei.myapplication.mem;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.huangyuwei.myapplication.ActivityWith8bigMenu;
import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.database.MoodTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class mem_mood_test extends ActivityWith8bigMenu {
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    private RadioButton ans_1, ans_2, ans_3, ans_4, ans_5;
    private Button submit, cancel;

    private int selected;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_mood_test);
        init();
        setSubmitOnClickListener();
        setCancelOnClickListener();
    }

    private void init() {
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup4 = (RadioGroup) findViewById(R.id.radioGroup4);
        radioGroup5 = (RadioGroup) findViewById(R.id.radioGroup5);
        submit = (Button) findViewById(R.id.submit);
        cancel = (Button) findViewById(R.id.cancel);
    }

    private void setSubmitOnClickListener() {

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selected = radioGroup1.getCheckedRadioButtonId();
                ans_1 = (RadioButton) findViewById(selected);

                selected = radioGroup2.getCheckedRadioButtonId();
                ans_2 = (RadioButton) findViewById(selected);

                selected = radioGroup3.getCheckedRadioButtonId();
                ans_3 = (RadioButton) findViewById(selected);

                selected = radioGroup4.getCheckedRadioButtonId();
                ans_4 = (RadioButton) findViewById(selected);

                selected = radioGroup5.getCheckedRadioButtonId();
                ans_5 = (RadioButton) findViewById(selected);

                score = Integer.parseInt(ans_1.getText().toString()) + Integer.parseInt(ans_2.getText().toString()) + Integer.parseInt(ans_3.getText().toString()) + Integer.parseInt(ans_4.getText().toString()) + Integer.parseInt(ans_5.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putInt("score", score);

                Intent intent = new Intent();
                intent.setClass(mem_mood_test.this, mem_mood_result.class);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });
    }

    private void setCancelOnClickListener() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mem_mood_test.this, mem_mood.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
