package com.example.huangyuwei.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.huangyuwei.myapplication.ask.ask;
import com.example.huangyuwei.myapplication.cure.cure_main;
import com.example.huangyuwei.myapplication.eat.eat_main;
import com.example.huangyuwei.myapplication.laugh.laugh;
import com.example.huangyuwei.myapplication.link.link;
import com.example.huangyuwei.myapplication.mem.self_main;
import com.example.huangyuwei.myapplication.move.move_main;
import com.example.huangyuwei.myapplication.talk.talk;

public class ActivityWith8bigMenu extends AppCompatActivity {
    private GlobalVariable globalVariable;
    private Boolean toCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_8big_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        globalVariable = (GlobalVariable) getApplicationContext();
        toCenter = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.with_8big_menu, menu);   //https://cutler.github.io/android-B03/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.cure:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_CURE;
                break;
            case R.id.move:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_MOVE;
                break;
            case R.id.eat:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_EAT;
                break;
            case R.id.ask:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_ASK;
                break;
            case R.id.link:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_LINK;
                break;
            case R.id.mem:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_MEM;
                break;
            case R.id.talk:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_TALK;
                break;
            case R.id.laugh:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.TO_LAUGH;
                break;
            case R.id.eightBig:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.NO_ACTION;
                break;
            case R.id.setting:
                Toast.makeText(this, "載入個人頁面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(this, user_profile.class);
                startActivity(intent);
                break;
            case R.id.signOut:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.SIGN_OUT;
                break;
            case R.id.exit:
                toCenter = true;
                globalVariable.theNextAction = GlobalVariable.NextAction.EXIT;
                break;
        }

        if(toCenter){
            Intent intent = new Intent();
            intent.setClass(this, center.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
