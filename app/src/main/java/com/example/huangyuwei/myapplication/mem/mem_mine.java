package com.example.huangyuwei.myapplication.mem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.huangyuwei.myapplication.ActivityWith8bigMenu;
import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.UserData;

import org.w3c.dom.Text;

public class mem_mine extends ActivityWith8bigMenu {
    private Context context;
    private static mem_mine instance;
    private Button btn_edit;
    private TextView text_birth, text_height, text_menstruation, text_surgery_date, text_surgery_type, text_cell_type, text_hermone_er, text_hermone_pr, text_her_2, text_fish, text_my_cure;
    private String birth, height, menstruation, surgery_date, surgery_type, cell_type, hermone_er, hermone_pr, her_2, fish, my_cure;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_mine);
        context = this;
        instance = this;
        init();
        getSharedData();
        updateData();

        btn_edit = (Button) findViewById(R.id.btn_edit_mem_mine);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mem_mine.this, mem_mine_edit.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void init(){
        text_birth = (TextView)findViewById(R.id.birth);
        text_height = (TextView)findViewById(R.id.height);
        text_menstruation = (TextView)findViewById(R.id.menstruation);
        text_surgery_date = (TextView)findViewById(R.id.surgery_date);
        text_surgery_type = (TextView)findViewById(R.id.surgery_type);
        text_cell_type = (TextView)findViewById(R.id.cell_type);
        text_hermone_er = (TextView)findViewById(R.id.hermone_er);
        text_hermone_pr = (TextView)findViewById(R.id.hermone_pr);
        text_her_2 = (TextView)findViewById(R.id.her_2);
        text_fish = (TextView)findViewById(R.id.fish);
        text_my_cure = (TextView)findViewById(R.id.my_cure);
    }

    void getSharedData(){
        sharedPreferences = UserData.getSharedPreferences(context);
        birth = sharedPreferences.getString("BIRTH", "");
        height = sharedPreferences.getString("HEIGHT", "");
        menstruation = sharedPreferences.getString("MENSTRUATION", "");
        surgery_date = sharedPreferences.getString("SURGERY_DATE", "");
        surgery_type = sharedPreferences.getString("SURGERY_TYPE", "");
        cell_type = sharedPreferences.getString("CELL_TYPE", "");
        hermone_er = sharedPreferences.getString("HERMONE_ER", "");
        hermone_pr = sharedPreferences.getString("HERMONE_PR", "");
        her_2 = sharedPreferences.getString("HER_2", "");
        fish = sharedPreferences.getString("FISH", "");
        my_cure = sharedPreferences.getString("MY_CURE", "");
    }

    void updateData(){
        text_birth.setText(birth);
        text_height.setText(height);
        text_menstruation.setText(menstruation);
        text_surgery_date.setText(surgery_date);
        text_surgery_type.setText(surgery_type);
        text_cell_type.setText(cell_type);
        text_hermone_er.setText(hermone_er);
        text_hermone_pr.setText(hermone_pr);
        text_her_2.setText(her_2);
        text_fish.setText(fish);
        text_my_cure.setText(my_cure);
    }

}
