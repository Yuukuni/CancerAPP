package com.example.huangyuwei.myapplication.mem;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.huangyuwei.myapplication.ActivityWith8bigMenu;
import com.example.huangyuwei.myapplication.MainActivity;
import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.UserData;
import com.example.huangyuwei.myapplication.ask.ask;
import com.example.huangyuwei.myapplication.center;
import com.example.huangyuwei.myapplication.cure.cure_main;
import com.example.huangyuwei.myapplication.eat.eat_main;
import com.example.huangyuwei.myapplication.laugh.laugh;
import com.example.huangyuwei.myapplication.link.link;
import com.example.huangyuwei.myapplication.move.move_main;
import com.example.huangyuwei.myapplication.talk.talk;
import com.example.huangyuwei.myapplication.user_profile;

public class self_main extends ActivityWith8bigMenu {
    private static self_main mInstance = null;

    ImageButton btn_mood;
    ImageButton btn_food;
    ImageButton btn_body;
    ImageButton btn_cure;
    ImageButton btn_mine;
    ImageButton btn_move;
    ImageButton btn_activity;
    ImageButton btn_back;
    ImageButton btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_main);
        mInstance=this;

        btn_mine = (ImageButton) findViewById(R.id.mem_me_button);
        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(self_main.this  , mem_mine.class);
                startActivity(intent);
            }
        });

        btn_mood= (ImageButton) findViewById(R.id.mem_mood_button);
        btn_mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(self_main.this  , mem_mood_main.class);
                startActivity(intent);
            }
        });

        btn_food= (ImageButton)findViewById(R.id.mem_food_button);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext() , mem_food_main.class);
                startActivity(intent);
            }
        });
        btn_cure = (ImageButton)findViewById(R.id.mem_cure_button);
        btn_cure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext() , mem_cure_main.class);
                startActivity(intent);
            }
        });

        btn_body = (ImageButton)findViewById(R.id.mem_body_button);
        btn_body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext() , mem_body_main.class);
                startActivity(intent);
            }
        });

        btn_move = (ImageButton)findViewById(R.id.mem_move_button);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext() , mem_move_main.class);
                startActivity(intent);
            }
        });

        btn_activity = (ImageButton) findViewById(R.id.mem_activity_button);
        btn_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(self_main.this  , mem_activity.class);
                startActivity(intent);
            }
        });

        btn_back = (ImageButton) findViewById(R.id.mem_back_button);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(self_main.this  , mem_back.class);
                startActivity(intent);
            }
        });

        btn_setting = (ImageButton) findViewById(R.id.mem_setting_button);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(self_main.this  , mem_setting.class);
                startActivity(intent);
            }
        });
    }

    public static self_main getInstance(){
        return mInstance;
    }
}
