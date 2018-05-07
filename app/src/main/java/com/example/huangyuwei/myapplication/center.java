package com.example.huangyuwei.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.huangyuwei.myapplication.ask.ask;
import com.example.huangyuwei.myapplication.cure.cure_main;
import com.example.huangyuwei.myapplication.eat.eat_main;
import com.example.huangyuwei.myapplication.laugh.laugh;
import com.example.huangyuwei.myapplication.link.link;
import com.example.huangyuwei.myapplication.mem.self_main;
import com.example.huangyuwei.myapplication.move.move_main;
import com.example.huangyuwei.myapplication.talk.talk;

public class center extends AppCompatActivity {
    private static center mInstance = null;
    private static Context context= null;
    private GlobalVariable globalVariable;

    private Toolbar titleBar;
    ImageButton Btn_ask;
    ImageButton Btn_link;
    ImageButton Btn_mem;
    ImageButton Btn_move;
    ImageButton Btn_talk;
    ImageButton Btn_eat;
    ImageButton Btn_laugh;
    ImageButton Btn_cure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context=this;
        mInstance=this;
        globalVariable = (GlobalVariable) getApplicationContext();

        Intent intent = new Intent();
        switch (globalVariable.theNextAction){
            case TO_CURE:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, cure_main.class);
                startActivity(intent);
                break;
            case TO_MOVE:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, move_main.class);
                startActivity(intent);
                break;
            case TO_EAT:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, eat_main.class);
                startActivity(intent);
                break;
            case TO_ASK:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, ask.class);
                startActivity(intent);
                break;
            case TO_LINK:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, link.class);
                startActivity(intent);
                break;
            case TO_MEM:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, self_main.class);
                startActivity(intent);
                break;
            case TO_TALK:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, talk.class);
                startActivity(intent);
                break;
            case TO_LAUGH:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                intent.setClass(this, laugh.class);
                startActivity(intent);
                break;
            case SIGN_OUT:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                UserData.getSharedPreferences(this).edit().clear().apply();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case NO_ACTION:
                break;
            case EXIT:
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                finish();
        }

        Btn_laugh= (ImageButton) findViewById(R.id.Btn_laugh);
        Btn_laugh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this  , laugh.class);
                startActivity(intent);
            }
        });

        Btn_cure= (ImageButton) findViewById(R.id.Btn_cure);
        Btn_cure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this  , cure_main.class);
                startActivity(intent);
            }
        });

        Btn_eat= (ImageButton) findViewById(R.id.Btn_eat);
        Btn_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this, eat_main.class);
                startActivity(intent);
            }
        });

        Btn_ask= (ImageButton) findViewById(R.id.Btn_ask);
        Btn_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this  , ask.class);
                startActivity(intent);
            }
        });

        Btn_link= (ImageButton) findViewById(R.id.Btn_link);
        Btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this  , link.class);
                startActivity(intent);
            }
        });

        Btn_mem= (ImageButton) findViewById(R.id.Btn_mem);
        Btn_mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this  , self_main.class);
                startActivity(intent);
            }
        });

        Btn_move= (ImageButton) findViewById(R.id.Btn_move);
        Btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this  , move_main.class);
                startActivity(intent);
            }
        });

        Btn_talk= (ImageButton) findViewById(R.id.Btn_talk);
        Btn_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(center.this  ,talk.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.center_menu, menu);   //https://cutler.github.io/android-B03/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.setting:
                Toast.makeText(this, "載入個人頁面", Toast.LENGTH_SHORT).show();
                intent.setClass(center.this, user_profile.class);
                startActivity(intent);
                break;
            case R.id.signOut:
                UserData.getSharedPreferences(this).edit().clear().apply();
                intent.setClass(center.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static center getInstance(){
        return mInstance;
    }
    public static Context getContext(){
        return context;
    }
}
