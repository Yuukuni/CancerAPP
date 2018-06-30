package com.example.huangyuwei.myapplication.laugh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.huangyuwei.myapplication.R;


public class laugh_night extends Fragment {


    ImageButton btn_sleep;
    ImageButton btn_relax;
    ImageButton btn_message;
    ImageButton btn_mind;
    public laugh_night() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_laugh_night, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btn_sleep= (ImageButton)getView().findViewById(R.id.btn_sleep);
        btn_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity() , laugh_night_sleep.class);
                startActivity(intent);
            }
        });

        btn_relax= (ImageButton)getView().findViewById(R.id.btn_relax);
        btn_relax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity() , laugh_night_movie.class);
                startActivity(intent);
            }
        });

        btn_message= (ImageButton)getView().findViewById(R.id.btn_message);
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity() , laugh_night_message.class);
                startActivity(intent);
            }
        });

        btn_mind= (ImageButton)getView().findViewById(R.id.btn_mind);
        btn_mind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity() , laugh_night_mind.class);
                startActivity(intent);
            }
        });
    }



}