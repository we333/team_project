package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AndroidCharacter;
import android.util.AndroidException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PassengerBrowse extends Fragment {

   Button btn_s,btn1,btn2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_passenger_browse, container, false);

        // btn_s=(Button) view.findViewById(R.id.button_p_search);
        btn1=(Button) view.findViewById(R.id.button_pass_car);
        //btn2=(Button) vieww.findViewById(R.id.button_pass_car2);

        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("car name")
                        .setMessage("Dialog")
                        .setPositiveButton("確認",null)
                        .create()
                        .show();
            }
        });

        return view;
    }



}
