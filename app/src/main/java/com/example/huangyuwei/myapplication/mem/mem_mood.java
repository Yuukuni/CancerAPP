package com.example.huangyuwei.myapplication.mem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.huangyuwei.myapplication.ActivityWith8bigMenu;
import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.database.CancerDatabase;
import com.example.huangyuwei.myapplication.database.MoodTime;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.huangyuwei.myapplication.MainActivity.cb;
import static com.example.huangyuwei.myapplication.MainActivity.getContext;

public class mem_mood extends ActivityWith8bigMenu {

    private static final String[] RESULT = { "一般正常範圍", "輕度情緒困擾", "中度情緒困擾", "重度情緒困擾" };

    private static final String[] ADVICE = {
            "心理健康狀況維持良好\n請繼續維持自己的好心情",

            "要注意調整一下自己的壓力狀況\n試著多放鬆心情喔",

            "目前狀況可能有心理困擾\n建議您找心理衛生專業人員談一談",

            "目前心理困擾程度\n需要醫療專業的協助諮詢\n請找專業醫師幫忙"
    };

    private TableLayout moodtable;
    private Button addMoodDay;

    private List<MoodTime> mooddays;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_mood);
        init();
        addTable();
        setAddMoodDayOnClickListener();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        moodtable = (TableLayout) findViewById(R.id.mood_daytable);
        addMoodDay = (Button) findViewById(R.id.addMoodDay);
        mooddays = cb.moodTimeDao().getAllMoodTime();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addTable() {
        for(int i = 0; i < mooddays.size(); i++) {
            addTableRow(moodtable, mooddays.get(i));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setAddMoodDayOnClickListener() {
        addMoodDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mem_mood.this, mem_mood_test.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addTableRow(TableLayout tl, final MoodTime mooddata){
        Integer date = mooddata.date_id;
        String mood = mooddata.score.toString();
        String diary = mooddata.diary;

        LayoutInflater inflater = getLayoutInflater();
        TableRow tr = (TableRow)inflater.inflate(R.layout.table_row, tl, false);

        TextView Name = (TextView)tr.findViewById(R.id.Name);
        Name.setText(date.toString());

        TextView Phone = (TextView)tr.findViewById(R.id.Phone);
        Phone.setText(mood);

        for(int i = 0; i < diary.length(); i++) {
            if(diary.charAt(i) == '\n' || i >= 10) {
                diary = diary.substring(0, i) + "...";
                break;
            }
        }

        TextView Address = (TextView)tr.findViewById(R.id.Address);
        Address.setText(diary);

        final String s = "確定刪除" + date + "的心情嗎？";

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(mem_mood.this);
                final View dialoglayout = inflater.inflate(R.layout.activity_mem_mood_dialog,null);

                TextView date = (TextView) dialoglayout.findViewById(R.id.date);
                date.setText(Integer.toString(mooddata.date_id));

                TextView score = (TextView) dialoglayout.findViewById(R.id.score);
                score.setText(Integer.toString(mooddata.score));

                int state, intScore = mooddata.score;

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

                TextView result = (TextView) dialoglayout.findViewById(R.id.result);
                result.setText(RESULT[state]);

                TextView advice = (TextView) dialoglayout.findViewById(R.id.advice);
                advice.setText(ADVICE[state]);

                EditText diary = (EditText) dialoglayout.findViewById(R.id.diary);
                diary.setText(mooddata.diary);

                AlertDialog.Builder builder = new AlertDialog.Builder(mem_mood.this);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText diary = (EditText) dialoglayout.findViewById(R.id.diary);

                        MoodTime mtime = new MoodTime();
                        mtime.date_id = mooddata.date_id;
                        mtime.time_id = mooddata.time_id;
                        mtime.score = mooddata.score;
                        mtime.diary = diary.getText().toString();

                        updateMoodTime(cb, mtime);
                        refreshTable();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog d = builder.setView(dialoglayout).create();
                d.show();
            }

        });

        tr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog d = new AlertDialog.Builder(mem_mood.this)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                deleteMoodTime(cb, mooddata);
                                refreshTable();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setMessage( s )
                        .setTitle("刪除" )
                        .create();
                d.show();
                return false;
            }
        });

        tl.addView(tr);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshTable(){
        int count = moodtable.getChildCount();
        for (int i = 1; i < count; i++) {
            View child = moodtable.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
        mooddays.clear();
        mooddays = cb.moodTimeDao().getAllMoodTime();
        for (int i = 0; i <mooddays.size(); i++) {
            addTableRow(moodtable, mooddays.get(i));
        }
    }

    private MoodTime deleteMoodTime(final CancerDatabase db, MoodTime time) {
        db.beginTransaction();
        try {
            db.moodTimeDao().delete(time);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return time;
    }

    private MoodTime updateMoodTime(final CancerDatabase db, MoodTime time) {
        db.beginTransaction();
        try {
            db.moodTimeDao().updateMoodTime(time);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return time;
    }
}
