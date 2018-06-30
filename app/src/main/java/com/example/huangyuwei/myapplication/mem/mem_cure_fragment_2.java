package com.example.huangyuwei.myapplication.mem;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
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
import com.example.huangyuwei.myapplication.database.ChemCure;
import com.example.huangyuwei.myapplication.database.PutCure;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.huangyuwei.myapplication.MainActivity.cb;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mem_cure_fragment_2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link mem_cure_fragment_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mem_cure_fragment_2 extends Fragment {
    private static final String TAG = "mem_cure_fragment_2";
    private mem_cure_fragment_2 instance;
    private Context context;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat datedbFormatter;
    private SimpleDateFormat timeFormatter;
    private SimpleDateFormat timedbFormatter;

    private TableLayout c_p_table;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    //private mem_cure_fragment_2.OnListFragmentInteractionListener mListener;
    private Button add_c_p;

    private Button btn_select_date, btn_confirm;
    private TextView dateText_s;
    private TextView dateText_e;
    private int nowYear, nowMonth, nowDay;
    private int mYear, mMonth, mDay;
    private Date currentDateView;

    private List<PutCure> PutCures;


    private OnFragmentInteractionListener mListener;

    public mem_cure_fragment_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mem_cure_fragment_2.
     */
    // TODO: Rename and change types and number of parameters
    public static mem_cure_fragment_2 newInstance(String param1, String param2) {
        mem_cure_fragment_2 fragment = new mem_cure_fragment_2();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mem_cure_fragment_2, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.TAIWAN);
        timeFormatter = new SimpleDateFormat("HH:mm", Locale.TAIWAN);
        datedbFormatter = new SimpleDateFormat("yyyyMMdd");
        timedbFormatter = new SimpleDateFormat("HHmmss",Locale.TAIWAN);
        add_c_p=(Button)getView().findViewById(R.id.add_c_p);
        c_p_table = (TableLayout) getActivity().findViewById(R.id.c_p_daytable);
        currentDateView= Calendar.getInstance().getTime();
        PutCures= CancerDatabase.getInMemoryDatabase(getContext()).putCureDao().getAllPutCure();
        for (int i = 0; i <PutCures.size(); i++) {
//            if(Integer.parseInt(datedbFormatter.format(currentDateView)) == PutCures.get(i).date_id) {
                addTableRow(c_p_table, PutCures.get(i));
            //}
        }

        add_c_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View dialoglayout = inflater.inflate(R.layout.fragment_mem_cure_fragment_2_edit,null);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                dateText_s = (EditText) dialoglayout.findViewById(R.id.edt_start);
                dateText_s.setInputType(InputType.TYPE_NULL);
                dateText_s.requestFocus();
                dateText_s.setText(dateFormatter.format(Calendar.getInstance().getTime()));
                dateText_e = (EditText) dialoglayout.findViewById(R.id.edt_end);
                dateText_e.setInputType(InputType.TYPE_NULL);
                dateText_e.requestFocus();
                dateText_e.setText(dateFormatter.format(Calendar.getInstance().getTime()));
                setTimeField();

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final EditText dateStart = (EditText) dialoglayout.findViewById(R.id.edt_start);
                        final EditText dateEnd = (EditText) dialoglayout.findViewById(R.id.edt_end);
                        String date_s = dateStart.getText().toString();
                        String date_e = dateEnd.getText().toString();
                        Date datedb_s = Calendar.getInstance().getTime(); //initialize
                        Date datedb_e = Calendar.getInstance().getTime(); //initialize

                        try {
                            datedb_s = dateFormatter.parse(date_s);
                            datedb_e = dateFormatter.parse(date_e);
                        } catch (ParseException e){

                        }

                        String dateindb_s=datedbFormatter.format(datedb_s);
                        String dateindb_e=datedbFormatter.format(datedb_e);

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

                        PutCure putCure = new PutCure();
                        putCure.createDate = createDate;
                        putCure.createTime = createTime;
                        putCure.start = dateindb_s;
                        putCure.end = dateindb_e;
                        putCure.place = data;
                        putCure.checkAmount = checkAmount;
                        //addFoodDay(CancerDatabase.getInMemoryDatabase(getContext()),day);
                        addPutCure(cb, putCure);
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private PutCure addPutCure(final CancerDatabase db, PutCure cure) {
        db.beginTransaction();
        try {
            db.putCureDao().insertPutCure(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }

    private PutCure deletePutCure(final CancerDatabase db, PutCure cure) {
        db.beginTransaction();
        try {
            db.putCureDao().delete(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }

    private PutCure updatePutCure(final CancerDatabase db, PutCure cure) {
        db.beginTransaction();
        try {
            db.putCureDao().updatePutCure(cure);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return cure;
    }

    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "."
                + String.valueOf(monthOfYear + 1) + "."
                + String.valueOf(dayOfMonth);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refreshTable(){
        int count = c_p_table.getChildCount();
        for (int i = 2; i < count; i++) {
            View child = c_p_table.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }

        PutCures.clear();
        PutCures=CancerDatabase.getInMemoryDatabase(getContext()).putCureDao().getAllPutCure();
        for (int i = 0; i <PutCures.size(); i++) {
            //if(Integer.parseInt(datedbFormatter.format(currentDateView)) == PutCures.get(i).date_id) {
                addTableRow(c_p_table, PutCures.get(i));
            }
        //}
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTimeField() {
        dateText_s.setOnClickListener(new View.OnClickListener() {
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
                            dateText_s.setText(dateFormatter.format(date));
                        }
                    }, nowYear,nowMonth, nowDay).show();
                }
            }
        });

        dateText_e.setOnClickListener(new View.OnClickListener() {
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
                            dateText_e.setText(dateFormatter.format(date));
                        }
                    }, nowYear,nowMonth, nowDay).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addTableRow(TableLayout tl, final PutCure cdata){

        String start = cdata.start;
        String end = cdata.end;
        String place = cdata.place;
        int checkAmount = cdata.checkAmount;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        TableRow tr = (TableRow)inflater.inflate(R.layout.table_row, tl, false);
        tr.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT));

        // Add First Column
        TextView Name = (TextView)tr.findViewById(R.id.Name);
        Name.setText(place);

        // Add the 3rd Column
        TextView Phone = (TextView)tr.findViewById(R.id.Phone);
        Phone.setText(start);

        TextView Address = (TextView)tr.findViewById(R.id.Address);
        Address.setText(end);

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
        Address.getLayoutParams().height = (int) heightPx;

        Log.i(TAG,  place + "\n\n" + "height: "+ height +"\n" + "checkAmount: "+ checkAmount);

        final String s = "確定刪除從"+ start + "到" + end + "的放療嗎？";

        tr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog d = new AlertDialog.Builder(getContext())
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                deletePutCure(cb, cdata);
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
}
