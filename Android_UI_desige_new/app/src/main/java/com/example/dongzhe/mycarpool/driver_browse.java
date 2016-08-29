package com.example.dongzhe.mycarpool;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class driver_browse extends Fragment {

    TextView tDate,tDep,tDes,tCom;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_driver_browse, container, false);

        tDate=(TextView)view.findViewById(R.id.Text_date);
        tDep=(TextView)view.findViewById(R.id.Text_depature);
        tDes=(TextView)view.findViewById(R.id.Text_destination);
        tCom=(TextView)view.findViewById(R.id.Text_command);

        return view;

    }
}
