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
public class mem_cure_fragment_1 extends Fragment {
    private static final String TAG = "mem_cure_fragment_1";
    private mem_cure_fragment_1 instance;
    private Context context;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat datedbFormatter;
    private SimpleDateFormat timeFormatter;
    private SimpleDateFormat timedbFormatter;
    private TableLayout c_c_table;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    private OnListFragmentInteractionListener mListener;
    private Button add_c_c;

    private Button btn_select_date, btn_confirm;
    private TextView dateText;
    private int nowYear, nowMonth, nowDay;
    private int mYear, mMonth, mDay;
    private Date currentDateView;

    private List<ChemCure> ChemCures;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public mem_cure_fragment_1() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static mem_cure_fragment_1 newInstance(int columnCount) {
        mem_cure_fragment_1 fragment = new mem_cure_fragment_1();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mem_cure_fragment_1, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.TAIWAN);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.TAIWAN);
        datedbFormatter = new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN);
        timedbFormatter = new SimpleDateFormat("HHmmss",Locale.TAIWAN);
        add_c_c=(Button)getView().findViewById(R.id.addFoodDay);
        c_c_table = (TableLayout) getActivity().findViewById(R.id.c_c_daytable);
        currentDateView=Calendar.getInstance().getTime();
        ChemCures=CancerDatabase.getInMemoryDatabase(getContext()).chemCureDao().getAllChemCure();
        for (int i = 0; i <ChemCures.size(); i++) {
            //if(Integer.parseInt(datedbFormatter.format(currentDateView)) == ChemCures.get(i).date_id) {
                Log.d("TAG", ChemCures.get(i).date + " " + ChemCures.get(i).cure);
                addTableRow(c_c_table, ChemCures.get(i));
            //}
        }

        add_c_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View dialoglayout = inflater.inflate(R.layout.fragment_mem_cure_fragment_1_edit,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                dateText = (EditText) dialoglayout.findViewById(R.id.dateText);
                dateText.setInputType(InputType.TYPE_NULL);
                dateText.requestFocus();
                setTimeField();
                dateText.setText(dateFormatter.format(Calendar.getInstance().getTime()));

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final EditText datetext = (EditText) dialoglayout.findViewById(R.id.dateText);
                        String date = datetext.getText().toString();

                        Date datedb = Calendar.getInstance().getTime(); //initialize
                        try {
                            datedb = dateFormatter.parse(date);
                        } catch (ParseException e){
                            e.getErrorOffset();
                        }

                        String dateindb=datedbFormatter.format(datedb);

                        final CheckBox[] cbs = new CheckBox[16];
                        cbs[0] = (CheckBox) dialoglayout.findViewById(R.id.checkBox1);
                        cbs[1] = (CheckBox) dialoglayout.findViewById(R.id.checkBox2);
                        cbs[2] = (CheckBox) dialoglayout.findViewById(R.id.checkBox3);
                        cbs[3] = (CheckBox) dialoglayout.findViewById(R.id.checkBox4);
                        cbs[4] = (CheckBox) dialoglayout.findViewById(R.id.checkBox5);
                        cbs[5] = (CheckBox) dialoglayout.findViewById(R.id.checkBox6);
                        cbs[6] = (CheckBox) dialoglayout.findViewById(R.id.checkBox7);
                        cbs[7] = (CheckBox) dialoglayout.findViewById(R.id.checkBox8);
                        cbs[8] = (CheckBox) dialoglayout.findViewById(R.id.checkBox9);
                        cbs[9] = (CheckBox) dialoglayout.findViewById(R.id.checkBox10);
                        cbs[10] = (CheckBox) dialoglayout.findViewById(R.id.checkBox11);
                        cbs[11] = (CheckBox) dialoglayout.findViewById(R.id.checkBox12);
                        cbs[12] = (CheckBox) dialoglayout.findViewById(R.id.checkBox13);
                        cbs[13] = (CheckBox) dialoglayout.findViewById(R.id.checkBox14);
                        cbs[14] = (CheckBox) dialoglayout.findViewById(R.id.checkBox15);
                        cbs[15] = (CheckBox) dialoglayout.findViewById(R.id.checkBox16);

                        int checkAmount = 0;
                        boolean firstLine = true;
                        String data = "";

                        for(int i = 0; i < 16; i++) {
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

                        ChemCure chemCure = new ChemCure();
                        chemCure.createDate = createDate;
                        chemCure.createTime = createTime;
                        chemCure.date = dateindb;
                        chemCure.cure = data;
                        chemCure.checkAmount = checkAmount;
                        Log.i(TAG,  "1231 save"+ "\ncreateDate: " + chemCure.createDate + "\ncreateTime: " + chemCure.createTime + "\ndate: " + chemCure.date + "\ncure: " + chemCure.cure + "\n");
                        //addFoodDay(CancerDatabase.getInMemoryDatabase(getContext()),day);
                        addChemCure(cb, chemCure);
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
    private void addTableRow(TableLayout tl, final ChemCure cdata){
        String date = cdata.date;
        String cure = cdata.cure;
        int checkAmount = cdata.checkAmount;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        TableRow tr = (TableRow)inflater.inflate(R.layout.table_row2, tl, false);
        tr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT));

        TextView Name = (TextView)tr.findViewById(R.id.Name);
        Name.setText(date);

        // Add the 3rd Column
        TextView Phone = (TextView)tr.findViewById(R.id.Phone);
        Phone.setText(cure);

        int height, baseHeight = 35;
        if(checkAmount < 2) {
            height = baseHeight;
        }
        else {
            height = baseHeight + (checkAmount - 1) * 16;
        }

        float heightPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources().getDisplayMetrics());
        Name.getLayoutParams().height = (int) heightPx;
        Phone.getLayoutParams().height = (int) heightPx;

        Log.i(TAG,  cure + "\n\n" + "height: "+ height +"\n" + "checkAmount: "+ checkAmount);

        final String s = "確定刪除"+date+"的化療嗎？";

        tr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog d = new AlertDialog.Builder(getContext())
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                deleteChemCure(cb, cdata);
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

    private FoodTime addFoodTime(final CancerDatabase db, FoodTime time) {
        db.beginTransaction();
        try {
            db.foodTimeDao().insertFoodTime(time);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return time;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTimeField() {
        dateText.setOnClickListener(new View.OnClickListener() {
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
                            dateText.setText(dateFormatter.format(date));
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
        void onListFragmentInteraction(ChemCure item);
    }

    public void refreshList(){
        List<ChemCure> list = CancerDatabase.getInMemoryDatabase(context).chemCureDao().getAllChemCure();

    }
    private ChemCure addChemCure(final CancerDatabase db, ChemCure cure) {
        db.beginTransaction();
        try {
            db.chemCureDao().insertChemCure(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }

    private ChemCure deleteChemCure(final CancerDatabase db, ChemCure cure) {
        db.beginTransaction();
        try {
            db.chemCureDao().delete(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }

    private ChemCure updateChemCure(final CancerDatabase db, ChemCure cure) {
        db.beginTransaction();
        try {
            db.chemCureDao().updateChemCure(cure);
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
        int count = c_c_table.getChildCount();
        for (int i = 2; i < count; i++) {
            View child = c_c_table.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }

        ChemCures.clear();
        ChemCures=CancerDatabase.getInMemoryDatabase(getContext()).chemCureDao().getAllChemCure();
        for (int i = 0; i <ChemCures.size(); i++) {
            //if(Integer.parseInt(datedbFormatter.format(currentDateView)) == ChemCures.get(i).date_id) {
                Log.d("TAG", ChemCures.get(i).date + " " + ChemCures.get(i).cure);
                addTableRow(c_c_table, ChemCures.get(i));
            //}
        }
    }
}
