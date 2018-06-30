package com.example.huangyuwei.myapplication.mem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.database.CancerDatabase;
import com.example.huangyuwei.myapplication.database.MemOther;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import static com.example.huangyuwei.myapplication.MainActivity.cb;

public class mem_body_other extends Fragment {

    private EditText fromDateEtxt;
    private TableLayout othertable;
    private Button addOther;
    private String otherStr;
    private DatePickerDialog fromDatePickerDialog;
    private Date currentDateView;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat datedbFormatter;

    private List<MemOther> memotherdays;

    public mem_body_other() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        return inflater.inflate(R.layout.fragment_mem_body_other, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.TAIWAN);
        datedbFormatter = new SimpleDateFormat("yyyyMMdd");

        findViewsById();

        memotherdays = CancerDatabase.getInMemoryDatabase(getContext()).memOtherDao().getAllMemOther();
        currentDateView = Calendar.getInstance().getTime();
        for (int i = 0; i < memotherdays.size(); i++) {
            addTableRow(othertable, memotherdays.get(i));
        }

        addOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View dialoglayout = inflater.inflate(R.layout.otherday_dialog,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                fromDateEtxt = (EditText) dialoglayout.findViewById(R.id.EditTextTime);
                fromDateEtxt.setInputType(InputType.TYPE_NULL);
                fromDateEtxt.requestFocus();
                setDateField();
                fromDateEtxt.setText(dateFormatter.format(Calendar.getInstance().getTime()));

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final EditText datetext = (EditText) dialoglayout.findViewById(R.id.EditTextTime);
                        String date = datetext.getText().toString();

                        Date datedb = Calendar.getInstance().getTime(); //initialize
                        try {
                            datedb = dateFormatter.parse(date);

                        } catch (ParseException e) {
                            e.getErrorOffset();
                        }

                        String dateindb = datedbFormatter.format(datedb);

                        Date timedb = Calendar.getInstance().getTime();
                        String timeindb = datedbFormatter.format(timedb);

                        int checkBoxAmount = 12;
                        CheckBox[] cbs = new CheckBox[checkBoxAmount];
                        cbs[0] = (CheckBox) dialoglayout.findViewById(R.id.tightSkin);
                        cbs[1] = (CheckBox) dialoglayout.findViewById(R.id.tightShoulder);
                        cbs[2] = (CheckBox) dialoglayout.findViewById(R.id.daohan);
                        cbs[3] = (CheckBox) dialoglayout.findViewById(R.id.secretion);
                        cbs[4] = (CheckBox) dialoglayout.findViewById(R.id.skinRash);
                        cbs[5] = (CheckBox) dialoglayout.findViewById(R.id.edema);
                        cbs[6] = (CheckBox) dialoglayout.findViewById(R.id.hand_footSlow);
                        cbs[7] = (CheckBox) dialoglayout.findViewById(R.id.tingling);
                        cbs[8] = (CheckBox) dialoglayout.findViewById(R.id.redness);
                        cbs[9] = (CheckBox) dialoglayout.findViewById(R.id.irregularMenstruation);
                        cbs[10] = (CheckBox) dialoglayout.findViewById(R.id.jointPain);
                        cbs[11] = (CheckBox) dialoglayout.findViewById(R.id.empty);

                        final EditText other = (EditText) dialoglayout.findViewById(R.id.EditTextCustomize);
                        otherStr = other.getText().toString();

                        String data = "";
                        boolean firstLine = true;
                        for(int i = 0; i < checkBoxAmount - 1; i++) {
                            if(cbs[i].isChecked()) {
                                if(firstLine) {
                                    firstLine = false;
                                }
                                else {
                                    data += "\n";
                                }
                                data += cbs[i].getText().toString();
                            }
                        }

                        if (cbs[11].isChecked() && !otherStr.equals("")) {
                            if(!firstLine) {
                                data += "\n";
                            }
                            data += otherStr;
                        }
                        else {
                            otherStr = "";
                        }

                        MemOther memOther = new MemOther();

                        memOther.date_id = Integer.parseInt(dateindb);
                        memOther.text = data;
                        memOther.otherText = otherStr;
                        Log.d("TAG",dateindb+" "+otherStr);

                        boolean unique=true;
                        for(int i=0;i<memotherdays.size();i++){
                            if(memotherdays.get(i).date_id==Integer.parseInt(dateindb))
                                unique=false;
                        }
                        if(unique) {
                            addMemOther(cb, memOther);
                            refreshTable();
                            other.setText("");
                            dialog.dismiss();
                        }
                        else{
                            final AlertDialog d = new AlertDialog.Builder(getContext())
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setMessage( "同一天只能輸一次喔" )
                                    .create();
                            d.show();
                        }

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.setView(dialoglayout).create();
                dialog.show();
            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addTableRow(TableLayout tl, final MemOther otherdata) {
        final Integer date = otherdata.date_id;
        final String TEXT = otherdata.text;
        otherStr = otherdata.otherText;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        TableRow tr = (TableRow)inflater.inflate(R.layout.table_row2, tl, false);
        tr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT));


        TextView Name = (TextView)tr.findViewById(R.id.Name);
        Name.setText(date.toString());

        // Add the 3rd Column
        TextView Phone = (TextView)tr.findViewById(R.id.Phone);
        Phone.setText(TEXT);

        final int dtime= date;
        final String s = "確定刪除"+date+"的資料嗎？";

        int height, baseHeight = 35;
        int checkAmount = 0;
        if(!TEXT.equals("")) {
            checkAmount++;
        }
        for(int i = 0; i < TEXT.length(); i++) {
            if(TEXT.charAt(i) == '\n') {
                checkAmount++;
            }
        }

        Log.d("TAG","************checkAmount: "+checkAmount + "\notherStr:" + otherStr);

        if(checkAmount < 2) {
            height = baseHeight;
        }
        else {
            height = baseHeight + (checkAmount - 1) * 16;
        }

        float heightPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources().getDisplayMetrics());
        Name.getLayoutParams().height = (int) heightPx;
        Phone.getLayoutParams().height = (int) heightPx;

        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View dialoglayout = inflater.inflate(R.layout.otherday_dialog,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                fromDateEtxt = (EditText) dialoglayout.findViewById(R.id.EditTextTime);
                fromDateEtxt.setInputType(InputType.TYPE_NULL);
                fromDateEtxt.requestFocus();
                setDateField();
                String tempString = Integer.toString(otherdata.date_id);
                Date temp = Calendar.getInstance().getTime();
                try {
                    temp = datedbFormatter.parse(tempString);
                } catch (ParseException e){
                    e.getErrorOffset();
                }

                fromDateEtxt.setText(dateFormatter.format(temp));

                int checkBoxAmount = 12;
                CheckBox[] cbs = new CheckBox[checkBoxAmount];
                cbs[0] = (CheckBox) dialoglayout.findViewById(R.id.tightSkin);
                cbs[1] = (CheckBox) dialoglayout.findViewById(R.id.tightShoulder);
                cbs[2] = (CheckBox) dialoglayout.findViewById(R.id.daohan);
                cbs[3] = (CheckBox) dialoglayout.findViewById(R.id.secretion);
                cbs[4] = (CheckBox) dialoglayout.findViewById(R.id.skinRash);
                cbs[5] = (CheckBox) dialoglayout.findViewById(R.id.edema);
                cbs[6] = (CheckBox) dialoglayout.findViewById(R.id.hand_footSlow);
                cbs[7] = (CheckBox) dialoglayout.findViewById(R.id.tingling);
                cbs[8] = (CheckBox) dialoglayout.findViewById(R.id.redness);
                cbs[9] = (CheckBox) dialoglayout.findViewById(R.id.irregularMenstruation);
                cbs[10] = (CheckBox) dialoglayout.findViewById(R.id.jointPain);
                cbs[11] = (CheckBox) dialoglayout.findViewById(R.id.empty);

                for(int i = 0; i < checkBoxAmount - 1; i++) {
                    if(TEXT.contains(cbs[i].getText().toString())) {
                        cbs[i].setChecked(true);
                    }
                }

                final EditText other = (EditText) dialoglayout.findViewById(R.id.EditTextCustomize);
                other.setText(otherdata.otherText);

                if(otherdata.otherText.equals("")) {
                    cbs[11].setChecked(false);
                }
                else {
                    cbs[11].setChecked(true);
                }

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final EditText datetext = (EditText) dialoglayout.findViewById(R.id.EditTextTime);
                        String date = datetext.getText().toString();

                        Date datedb = Calendar.getInstance().getTime(); //initialize
                        try {
                            datedb = dateFormatter.parse(date);

                        } catch (ParseException e) {
                            e.getErrorOffset();
                        }

                        String dateindb = datedbFormatter.format(datedb);

                        int checkBoxAmount = 12;
                        CheckBox[] cbs = new CheckBox[checkBoxAmount];
                        cbs[0] = (CheckBox) dialoglayout.findViewById(R.id.tightSkin);
                        cbs[1] = (CheckBox) dialoglayout.findViewById(R.id.tightShoulder);
                        cbs[2] = (CheckBox) dialoglayout.findViewById(R.id.daohan);
                        cbs[3] = (CheckBox) dialoglayout.findViewById(R.id.secretion);
                        cbs[4] = (CheckBox) dialoglayout.findViewById(R.id.skinRash);
                        cbs[5] = (CheckBox) dialoglayout.findViewById(R.id.edema);
                        cbs[6] = (CheckBox) dialoglayout.findViewById(R.id.hand_footSlow);
                        cbs[7] = (CheckBox) dialoglayout.findViewById(R.id.tingling);
                        cbs[8] = (CheckBox) dialoglayout.findViewById(R.id.redness);
                        cbs[9] = (CheckBox) dialoglayout.findViewById(R.id.irregularMenstruation);
                        cbs[10] = (CheckBox) dialoglayout.findViewById(R.id.jointPain);
                        cbs[11] = (CheckBox) dialoglayout.findViewById(R.id.empty);

                        final EditText other = (EditText) dialoglayout.findViewById(R.id.EditTextCustomize);
                        otherStr = other.getText().toString();

                        String data = "";
                        boolean firstLine = true;
                        for(int i = 0; i < checkBoxAmount - 1; i++) {
                            if(cbs[i].isChecked()) {
                                if(firstLine) {
                                    firstLine = false;
                                }
                                else {
                                    data += "\n";
                                }
                                data += cbs[i].getText().toString();
                            }
                        }

                        if (cbs[11].isChecked() && !otherStr.equals("")) {
                            if(!firstLine) {
                                data += "\n";
                            }
                            data += otherStr;
                        }
                        else {
                            otherStr = "";
                        }

                        Log.d("TAG",dateindb+" "+otherStr);

                        MemOther memOther = new MemOther();

                        memOther.date_id = Integer.parseInt(dateindb);
                        memOther.text = data;
                        memOther.otherText = otherStr;

                        deleteMemOther(cb, otherdata);
                        memotherdays = CancerDatabase.getInMemoryDatabase(getContext()).memOtherDao().getAllMemOther();
                        boolean unique=true;
                        for(int i=0;i<memotherdays.size();i++){
                            if(memotherdays.get(i).date_id.equals(memOther.date_id)) {
                                unique = false;
                            }
                        }
                        if(unique) {
                            addMemOther(cb, memOther);
                            refreshTable();
                            other.setText("");
                        }
                        else{
                            final AlertDialog d = new AlertDialog.Builder(getContext())
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setMessage( "你這時間已經有紀錄過東西哦！" )
                                    .setTitle("重複了哦！")
                                    .create();
                            d.show();
                        }
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.setView(dialoglayout).create();
                dialog.show();

            }
        });

        tr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog d = new AlertDialog.Builder(getContext())
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                deleteMemOther(cb, otherdata);
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

    private MemOther addMemOther(final CancerDatabase db, MemOther other) {
        db.beginTransaction();
        try {
            db.memOtherDao().insertMemOther(other);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return other;
    }

    private MemOther deleteMemOther(final CancerDatabase db, MemOther other) {
        db.beginTransaction();
        try {
            db.memOtherDao().delete(other);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return other;
    }

    private MemOther updateMemOther(final CancerDatabase db, MemOther other) {
        db.beginTransaction();
        try {
            db.memOtherDao().updateMemOther(other);
            db.setTransactionSuccessful();
            Log.d("TAG", "success123");
        } finally {
            db.endTransaction();
        }
        return other;
    }

    private void findViewsById() {
        othertable = (TableLayout) getActivity().findViewById(R.id.other_daytable);
        addOther = (Button) getView().findViewById(R.id.addOtherDay);
    }

    private void setDateField() {
        fromDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newCl = Calendar.getInstance();
                newCl.set(year, monthOfYear, dayOfMonth);
                currentDateView=newCl.getTime();
                fromDateEtxt.setText(dateFormatter.format(currentDateView.getTime()));
                refreshTable();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshTable() {
        int count = othertable.getChildCount();
        //Log.d("TAG",String.valueOf(count));
        for (int i = 1; i < count; i++) {
            View child = othertable.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }

        memotherdays.clear();
        memotherdays=CancerDatabase.getInMemoryDatabase(getContext()).memOtherDao().getAllMemOther();
        for (int i = 0; i <memotherdays.size(); i++) {
            addTableRow(othertable, memotherdays.get(i));
        }
    }
}
