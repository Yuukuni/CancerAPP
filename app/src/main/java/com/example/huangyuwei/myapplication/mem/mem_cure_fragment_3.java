package com.example.huangyuwei.myapplication.mem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.TimePicker;

import com.example.huangyuwei.myapplication.R;
import com.example.huangyuwei.myapplication.database.CancerDatabase;
import com.example.huangyuwei.myapplication.database.ChemCure;
import com.example.huangyuwei.myapplication.database.FoodTime;
import com.example.huangyuwei.myapplication.database.TargetCure;
import com.example.huangyuwei.myapplication.mem.dummy.DummyContent;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.huangyuwei.myapplication.MainActivity.cb;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class mem_cure_fragment_3 extends Fragment {
    private static final String TAG = "mem_cure_fragment_3";
    private mem_cure_fragment_3 instance;
    private Context context;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat datedbFormatter;
    private SimpleDateFormat timeFormatter;
    private SimpleDateFormat timedbFormatter;
    private TableLayout c_b_table;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    private OnListFragmentInteractionListener mListener;
    private Button add_c_b;

    private Button btn_select_date, btn_confirm;
    private TextView startDate, endDate;
    private int nowYear, nowMonth, nowDay;
    private int mYear, mMonth, mDay;
    private Date currentDateView;

    private List<TargetCure> TargetCures;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public mem_cure_fragment_3() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static mem_cure_fragment_3 newInstance(int columnCount) {
        mem_cure_fragment_3 fragment = new mem_cure_fragment_3();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mem_cure_fragment_3, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.TAIWAN);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.TAIWAN);
        datedbFormatter = new SimpleDateFormat("yyyyMMdd");
        timedbFormatter = new SimpleDateFormat("HHmmss",Locale.TAIWAN);
        add_c_b=(Button)getView().findViewById(R.id.addFoodDay);
        c_b_table = (TableLayout) getActivity().findViewById(R.id.c_b_daytable);
        currentDateView=Calendar.getInstance().getTime();
        TargetCures=CancerDatabase.getInMemoryDatabase(getContext()).targetCureDao().getAllTargetCure();
        for (int i = 0; i < TargetCures.size(); i++) {
            //if(Integer.parseInt(datedbFormatter.format(currentDateView)) == ChemCures.get(i).date_id) {
            Log.d("TAG", TargetCures.get(i).createDate + "\n" + TargetCures.get(i).createTime + "\n" + TargetCures.get(i).startDate + "\n" + TargetCures.get(i).endDate + "\n" + TargetCures.get(i).cure + "\n" + TargetCures.get(i).checkAmount + "\n\n");
            addTableRow(c_b_table, TargetCures.get(i));
            //}
        }

        add_c_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View dialoglayout = inflater.inflate(R.layout.fragment_mem_cure_fragment_3_edit,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                startDate = (EditText) dialoglayout.findViewById(R.id.editTextStart);
                startDate.setInputType(InputType.TYPE_NULL);
                startDate.requestFocus();
                startDate.setText(dateFormatter.format(Calendar.getInstance().getTime()));

                endDate = (EditText) dialoglayout.findViewById(R.id.editTextEnd);
                endDate.setInputType(InputType.TYPE_NULL);
                endDate.requestFocus();
                endDate.setText(dateFormatter.format(Calendar.getInstance().getTime()));

                setTimeField();

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final EditText editTextStart = (EditText) dialoglayout.findViewById(R.id.editTextStart);
                        final EditText editTextEnd = (EditText) dialoglayout.findViewById(R.id.editTextEnd);

                        String startDate = editTextStart.getText().toString();
                        String endDate = editTextEnd.getText().toString();

                        Date datedbStart = Calendar.getInstance().getTime(); //initialize
                        Date datedbEnd = Calendar.getInstance().getTime();

                        try {
                            datedbStart = dateFormatter.parse(startDate);
                            datedbEnd = dateFormatter.parse(endDate);
                        } catch (ParseException e){
                            e.getErrorOffset();
                        }

                        String dateindbStart = datedbFormatter.format(datedbStart);
                        String dateindbEnd = datedbFormatter.format(datedbEnd);

                        final CheckBox[] cbs = new CheckBox[6];
                        cbs[0] = (CheckBox) dialoglayout.findViewById(R.id.cb1);
                        cbs[1] = (CheckBox) dialoglayout.findViewById(R.id.cb2);
                        cbs[2] = (CheckBox) dialoglayout.findViewById(R.id.cb3);
                        cbs[3] = (CheckBox) dialoglayout.findViewById(R.id.cb4);
                        cbs[4] = (CheckBox) dialoglayout.findViewById(R.id.cb5);
                        cbs[5] = (CheckBox) dialoglayout.findViewById(R.id.cb6);

                        int checkAmount = 0;
                        boolean firstLine = true;
                        String data = "";

                        for(int i = 0; i < 6; i++) {
                            if(cbs[i].isChecked()) {
                                checkAmount++;
                                if(firstLine) {
                                    firstLine = false;
                                }
                                else {
                                    data += "\n";
                                }
                                data += cbs[i].getText().toString();
                            }
                        }

                        Date currentDate = Calendar.getInstance().getTime();
                        int createDate = Integer.parseInt(datedbFormatter.format(currentDate));
                        int createTime = Integer.parseInt(timedbFormatter.format(currentDate));

                        TargetCure targetCure = new TargetCure();
                        targetCure.createDate = createDate;
                        targetCure.createTime = createTime;
                        targetCure.startDate = dateindbStart;
                        targetCure.endDate = dateindbEnd;
                        targetCure.cure = data;
                        targetCure.checkAmount = checkAmount;
                        Log.i(TAG,  "1231 save"+ "\ncreateDate: " + targetCure.createDate + "\ncreateTime: " + targetCure.createTime + "\nstartDate: " + targetCure.startDate + "\nendDate: " + targetCure.endDate + "\ncure: " + targetCure.cure + "\n");
                        //addFoodDay(CancerDatabase.getInMemoryDatabase(getContext()),day);
                        addTargetCure(cb, targetCure);
                        refreshTable();
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
    private void addTableRow(TableLayout tl, final TargetCure tdata){
        String startDate = tdata.startDate;
        String endDate = tdata.endDate;
        String cure = tdata.cure;
        int checkAmount = tdata.checkAmount;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        TableRow tr = (TableRow)inflater.inflate(R.layout.table_row3, tl, false);
        tr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT));

        TextView col_1 = (TextView)tr.findViewById(R.id.col_1);
        col_1.setText(cure);

        TextView col_2 = (TextView)tr.findViewById(R.id.col_2);
        col_2.setText(startDate);

        TextView col_3 = (TextView)tr.findViewById(R.id.col_3);
        col_3.setText(endDate);

        int height, baseHeight = 35;
        if(checkAmount < 2) {
            height = baseHeight;
        }
        else {
            height = baseHeight + (checkAmount - 1) * 16;
        }

        float heightPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources().getDisplayMetrics());
        col_1.getLayoutParams().height = (int) heightPx;
        col_2.getLayoutParams().height = (int) heightPx;
        col_3.getLayoutParams().height = (int) heightPx;

        Log.i(TAG,  cure + "\n\n" + "height: "+ height +"\n" + "checkAmount: "+ checkAmount);

        final String s = "確定刪除從"+ startDate +"到" + endDate + "的標靶嗎？";

        tr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog d = new AlertDialog.Builder(getContext())
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                deleteTargetCure(cb, tdata);
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
    private void setTimeField() {
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                nowYear = c.get(Calendar.YEAR);
                nowMonth = c.get(Calendar.MONTH);
                nowDay = c.get(Calendar.DAY_OF_MONTH);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Calendar setDate = Calendar.getInstance();
                            setDate.set(year, month, dayOfMonth);
                            Date date = setDate.getTime();
                            startDate.setText(dateFormatter.format(date));
                        }
                    }, nowYear,nowMonth, nowDay).show();
                }
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                nowYear = c.get(Calendar.YEAR);
                nowMonth = c.get(Calendar.MONTH);
                nowDay = c.get(Calendar.DAY_OF_MONTH);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Calendar setDate = Calendar.getInstance();
                            setDate.set(year, month, dayOfMonth);
                            Date date = setDate.getTime();
                            endDate.setText(dateFormatter.format(date));
                        }
                    }, nowYear,nowMonth, nowDay).show();
                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(TargetCure item);
    }

    public void refreshList(){
        List<TargetCure> list = CancerDatabase.getInMemoryDatabase(context).targetCureDao().getAllTargetCure();

    }
    private TargetCure addTargetCure(final CancerDatabase db, TargetCure cure) {
        db.beginTransaction();
        try {
            db.targetCureDao().insertTargetCure(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }

    private TargetCure deleteTargetCure(final CancerDatabase db, TargetCure cure) {
        db.beginTransaction();
        try {
            db.targetCureDao().delete(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }

    private TargetCure updateTargetCure(final CancerDatabase db, TargetCure cure) {
        db.beginTransaction();
        try {
            db.targetCureDao().updateTargetCure(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshTable(){
        int count = c_b_table.getChildCount();
        for (int i = 2; i < count; i++) {
            View child = c_b_table.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }

        TargetCures.clear();
        TargetCures=CancerDatabase.getInMemoryDatabase(getContext()).targetCureDao().getAllTargetCure();
        for (int i = 0; i < TargetCures.size(); i++) {
            //if(Integer.parseInt(datedbFormatter.format(currentDateView)) == ChemCures.get(i).date_id) {
            addTableRow(c_b_table, TargetCures.get(i));
            //}
        }
    }
}
