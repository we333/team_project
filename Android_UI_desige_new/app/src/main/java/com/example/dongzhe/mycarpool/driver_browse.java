package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class driver_browse extends Fragment {
    String [] strarray;
    Client upload = new Client();
    String d_name,rbk;
    TextView tDate,tDep,tDes,tPri,tSea,tCom;
    Button btn_u;

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
        tPri=(TextView)view.findViewById(R.id.Text_price);
        tSea=(TextView)view.findViewById(R.id.Text_seat);
        tCom=(TextView)view.findViewById(R.id.Text_command);

        btn_u=(Button)view.findViewById(R.id.button_update);

        btn_u.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = getActivity().getIntent();
                d_name = intent.getStringExtra("name");

                new up().execute();
            }
        });

        return view;

    }

    private class up extends AsyncTask<String,Void,Integer>{
        Integer m = 0;
        @Override
        protected Integer doInBackground(String... params) {
            try {
                rbk = upload.start("checkbooking|"+d_name);
                m = 1;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return m;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if(m==1){
                if(rbk.equals("noresults")){
                    Toast.makeText(getActivity(),"No information", Toast.LENGTH_SHORT).show();
                }
                else {
                    try{
                        strarray=rbk.split("\\|");
                        System.out.println(rbk);
                        tDate.setText(strarray[0]);
                        tDep.setText(strarray[1]);
                        tDes.setText(strarray[2]);
                        tPri.setText(strarray[3]);
                        tSea.setText(strarray[4]);
                        tCom.setText(strarray[5]);
                }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
