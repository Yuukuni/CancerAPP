package com.example.huangyuwei.myapplication.mem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.huangyuwei.myapplication.ActivityWith8bigMenu;
import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.UserData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class mem_mine_edit extends ActivityWith8bigMenu {
    private static final String[] CELL_TYPE = {
            "浸潤性腺管癌",
            "乳腺管原位癌",
            "浸潤性小葉癌",
            "乳小葉原位癌",
            "其他"
    };

    private static final String[] NEG_POS = {
            "陰性",
            "陽性",
    };

    private static final String[] HER_2 = {
            "陰性",
            "陽性 1+",
            "陽性 2+",
            "陽性 3+"
    };

    private Context context;
    private Button btn_save, btn_cancel;

    int surgery_type_amount = 6;
    int cell_type_amount = 5;
    int neg_pos_amount = 2;
    int her_2_amount = 4;
    int my_cure_amount = 4;

    private DatePicker dp_birth, dp_surgery_date;
    private EditText edt_height;
    private RadioGroup rg_menstruation;
    private CheckBox[] cb_surgery_type;
    private Spinner sp_cell_type, sp_hermone_er, sp_hermone_pr, sp_her_2, sp_fish;
    private CheckBox[] cb_my_cure;

    private boolean surgeryType1, surgeryType2, surgeryType3, surgeryType4, surgeryType5, surgeryType6;
    private boolean chemical, radio, biaoba, hermone;
    RadioButton menstruation_select;
    private String birth, height, menstruation, surgery_date, surgery_type, cell_type, hermone_er, hermone_pr, her_2, fish, my_cure;

    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_mine_edit);
        context = this;
        init();
        initData();
        btnSetting();
    }

    private void init(){
        dp_birth = (DatePicker) findViewById(R.id.birth);
        edt_height = (EditText) findViewById(R.id.edt_height);
        rg_menstruation = (RadioGroup) findViewById(R.id.menstruation);
        dp_surgery_date = (DatePicker) findViewById(R.id.surgery_date);
        cb_surgery_type = new CheckBox[surgery_type_amount];
        cb_surgery_type[0] = (CheckBox) findViewById(R.id.cb_surgery_type_1);
        cb_surgery_type[1] = (CheckBox) findViewById(R.id.cb_surgery_type_2);
        cb_surgery_type[2] = (CheckBox) findViewById(R.id.cb_surgery_type_3);
        cb_surgery_type[3] = (CheckBox) findViewById(R.id.cb_surgery_type_4);
        cb_surgery_type[4] = (CheckBox) findViewById(R.id.cb_surgery_type_5);
        cb_surgery_type[5] = (CheckBox) findViewById(R.id.cb_surgery_type_6);
        sp_cell_type = (Spinner) findViewById(R.id.cell_type);
        sp_hermone_er = (Spinner) findViewById(R.id.hermone_er);
        sp_hermone_pr = (Spinner) findViewById(R.id.hermone_pr);
        sp_her_2 = (Spinner) findViewById(R.id.her_2);
        sp_fish = (Spinner) findViewById(R.id.fish);
        cb_my_cure = new CheckBox[my_cure_amount];
        cb_my_cure[0] = (CheckBox)findViewById(R.id.cb_chemical);
        cb_my_cure[1] = (CheckBox)findViewById(R.id.cb_radio);
        cb_my_cure[2] = (CheckBox)findViewById(R.id.cb_biaoba);
        cb_my_cure[3] = (CheckBox)findViewById(R.id.cb_hermone);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
    }

    void initData() {
        SharedPreferences sharedPreferences = UserData.getSharedPreferences(context);

        Calendar now = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);

        birth = sharedPreferences.getString("BIRTH", "");
        if(birth.equals("")) {
            dp_birth.updateDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        }
        else {
            Date date = Calendar.getInstance().getTime();
            try {
                date = dateFormat.parse(birth);
            } catch (ParseException e){
                e.getErrorOffset();
            }
            Calendar setBirth = Calendar.getInstance();
            setBirth.setTime(date);
            dp_birth.updateDate(setBirth.get(Calendar.YEAR), setBirth.get(Calendar.MONTH), setBirth.get(Calendar.DAY_OF_MONTH));
        }

        height = sharedPreferences.getString("HEIGHT", "");
        edt_height.setText(height);

        menstruation = sharedPreferences.getString("MENSTRUATION", "");
        if(menstruation.equals("是") || menstruation.equals("")) {
            RadioButton yes = (RadioButton)findViewById(R.id.yes);
            yes.setChecked(true);
        }
        else {
            RadioButton no = (RadioButton)findViewById(R.id.no);
            no.setChecked(true);
        }

        surgery_date = sharedPreferences.getString("SURGERY_DATE", "");
        if(surgery_date.equals("")) {
            dp_surgery_date.updateDate(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        }
        else {
            Date date = Calendar.getInstance().getTime();
            try {
                date = dateFormat.parse(surgery_date);
            } catch (ParseException e){
                e.getErrorOffset();
            }
            Calendar setSurgeryDate = Calendar.getInstance();
            setSurgeryDate.setTime(date);
            dp_surgery_date.updateDate(setSurgeryDate.get(Calendar.YEAR), setSurgeryDate.get(Calendar.MONTH), setSurgeryDate.get(Calendar.DAY_OF_MONTH));
        }

        surgery_type = sharedPreferences.getString("SURGERY_TYPE", "");
        for(int i = 0; i < surgery_type_amount; i++) {
            if(surgery_type.indexOf(cb_surgery_type[i].getText().toString()) == -1) {
                cb_surgery_type[i].setChecked(false);
            }
            else {
                cb_surgery_type[i].setChecked(true);
            }
        }

        cell_type = sharedPreferences.getString("CELL_TYPE", "");
        sp_cell_type.setSelection(0);
        for(int i = 0; i < cell_type_amount; i++) {
            if(cell_type.equals(CELL_TYPE[i])) {
                sp_cell_type.setSelection(i);
                break;
            }
        }

        hermone_er = sharedPreferences.getString("HERMONE_ER", "");
        sp_hermone_er.setSelection(0);
        if(hermone_er == NEG_POS[1]) {
            sp_hermone_er.setSelection(1);
        }

        hermone_pr = sharedPreferences.getString("HERMONE_PR", "");
        sp_hermone_pr.setSelection(0);
        if(hermone_pr == NEG_POS[1]) {
            sp_hermone_er.setSelection(1);
        }

        her_2 = sharedPreferences.getString("HER_2", "");
        sp_her_2.setSelection(0);
        for(int i = 0; i < her_2_amount; i++) {
            if(her_2.equals(HER_2[i])) {
                sp_her_2.setSelection(i);
                break;
            }
        }

        fish = sharedPreferences.getString("FISH", "");
        sp_fish.setSelection(0);
        if(fish == NEG_POS[1]) {
            sp_fish.setSelection(1);
        }

        my_cure = sharedPreferences.getString("MY_CURE", "");
        for(int i = 0; i < my_cure_amount; i++) {
            if(my_cure.indexOf(cb_my_cure[i].getText().toString()) == -1) {
                cb_my_cure[i].setChecked(false);
            }
            else {
                cb_my_cure[i].setChecked(true);
            }
        }
    }

    private void btnSetting() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                saveToSharedPreference();
                Intent intent = new Intent();
                intent.setClass(mem_mine_edit.this, mem_mine.class);
                startActivity(intent);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mem_mine_edit.this, mem_mine.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getData(){
        String year, month, date;
        year = Integer.toString(dp_birth.getYear());
        if(dp_birth.getMonth() + 1 < 10) {
            month = "0" + Integer.toString(dp_birth.getMonth() + 1);
        }
        else {
            month = Integer.toString(dp_birth.getMonth() + 1);
        }
        if(dp_birth.getDayOfMonth() < 10) {
            date = "0" + Integer.toString(dp_birth.getDayOfMonth());
        }
        else {
            date = Integer.toString(dp_birth.getDayOfMonth());
        }
        birth = year + "-" + month + "-" + date;

        height = edt_height.getText().toString();

        menstruation_select = (RadioButton) findViewById(rg_menstruation.getCheckedRadioButtonId());
        menstruation = menstruation_select.getText().toString();

        year = Integer.toString(dp_surgery_date.getYear());
        if(dp_surgery_date.getMonth() + 1 < 10) {
            month = "0" + Integer.toString(dp_surgery_date.getMonth() + 1);
        }
        else {
            month = Integer.toString(dp_surgery_date.getMonth() + 1);
        }
        if(dp_surgery_date.getDayOfMonth() < 10) {
            date = "0" + Integer.toString(dp_surgery_date.getDayOfMonth());
        }
        else {
            date = Integer.toString(dp_surgery_date.getDayOfMonth());
        }
        surgery_date = year + "-" + month + "-" + date;

        boolean firstLine = true;
        surgery_type = "";
        for(int i = 0; i < surgery_type_amount; i++) {
            if(cb_surgery_type[i].isChecked()) {
                if(firstLine) {
                    firstLine = false;
                }
                else {
                    surgery_type += "\n";
                }
                surgery_type += cb_surgery_type[i].getText().toString();
            }
        }

        cell_type = sp_cell_type.getSelectedItem().toString();
        hermone_er = sp_hermone_er.getSelectedItem().toString();
        hermone_pr = sp_hermone_pr.getSelectedItem().toString();
        her_2 = sp_her_2.getSelectedItem().toString();
        fish = sp_fish.getSelectedItem().toString();

        firstLine = true;
        my_cure = "";
        for(int i = 0; i < my_cure_amount; i++) {
            if(cb_my_cure[i].isChecked()) {
                if(firstLine) {
                    firstLine = false;
                }
                else {
                    my_cure += "\n";
                }
                my_cure += cb_my_cure[i].getText().toString();
            }
        }
    }

    private void saveToSharedPreference(){
        SharedPreferences sharedPreferences = UserData.getSharedPreferences(context);
        sharedPreferences.edit()
                .putString("BIRTH", birth)
                .putString("HEIGHT", height)
                .putString("MENSTRUATION", menstruation)
                .putString("SURGERY_DATE", surgery_date)
                .putString("SURGERY_TYPE", surgery_type)
                .putString("CELL_TYPE", cell_type)
                .putString("HERMONE_ER", hermone_er)
                .putString("HERMONE_PR", hermone_pr)
                .putString("HER_2", her_2)
                .putString("FISH", fish)
                .putString("MY_CURE", my_cure)
                .apply();
    }
}
