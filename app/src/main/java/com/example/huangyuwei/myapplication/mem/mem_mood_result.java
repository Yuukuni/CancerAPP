package com.example.huangyuwei.myapplication.mem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.database.CancerDatabase;
import com.example.huangyuwei.myapplication.database.MemActivity;
import com.example.huangyuwei.myapplication.database.MoodTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.huangyuwei.myapplication.MainActivity.cb;

public class mem_mood_result extends AppCompatActivity {

    private static final String[] RESULT = { "一般正常範圍", "輕度情緒困擾", "中度情緒困擾", "重度情緒困擾" };

    private static final String[] ADVICE = {
            "心理健康狀況維持良好\n請繼續維持自己的好心情",

            "要注意調整一下自己的壓力狀況\n試著多放鬆心情喔",

            "目前狀況可能有心理困擾\n建議您找心理衛生專業人員談一談",

            "目前心理困擾程度\n需要醫療專業的協助諮詢\n請找專業醫師幫忙"
    };

    private TextView score, result, advice;
    private EditText diary;
    private Button save, cancel;

    private int intScore, state;

    private SimpleDateFormat dateIDFormatter, timeIDFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_mood_result);
        findViews();
        init();
        setSaveOnClickListener();
        setCancelOnClickListener();
    }

    private void findViews() {
        score = (TextView) findViewById(R.id.score);
        result = (TextView) findViewById(R.id.result);
        advice = (TextView) findViewById(R.id.advice);
        diary = (EditText) findViewById(R.id.diary);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
    }

    private void init() {
        intScore = getIntent().getExtras().getInt("score");

        if(intScore <= 5) {
            state = 0;
        }
        else if(intScore < 10) {
            state = 1;
        }
        else if(intScore < 15) {
            state = 2;
        }
        else {
            state = 3;
        }

        score.setText(Integer.toString(intScore));
        result.setText(RESULT[state]);
        advice.setText(ADVICE[state]);

        dateIDFormatter = new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN);
        timeIDFormatter = new SimpleDateFormat("HHmmss", Locale.TAIWAN);
    }

    private void setSaveOnClickListener() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateIDFormatter.format(Calendar.getInstance().getTime());
                String time = timeIDFormatter.format(Calendar.getInstance().getTime());

                MoodTime moodTime = new MoodTime();
                moodTime.date_id = Integer.parseInt(date);
                moodTime.time_id = Integer.parseInt(time);
                moodTime.score = intScore;
                moodTime.diary = diary.getText().toString();
                addMoodTime(cb, moodTime);

                Intent intent = new Intent();
                intent.setClass(mem_mood_result.this, mem_mood.class);
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
                intent.setClass(mem_mood_result.this, mem_mood.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addMoodTime(final CancerDatabase db, MoodTime moodTime) {
        db.beginTransaction();
        try {
            db.moodTimeDao().insertMoodTime(moodTime);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
