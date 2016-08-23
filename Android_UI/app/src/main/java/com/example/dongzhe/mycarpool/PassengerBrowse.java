package com.example.dongzhe.mycarpool;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PassengerBrowse extends Fragment {

    String tm=null;
    String fm=null;
    String to=null;


    String [] strarray;
    Client csearch = new Client();
    ArrayList<TextView> list=new ArrayList<TextView>();

    Button button;
    EditText et_time,et_name,et_pwd;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_passenger_browse, container, false);

        et_time = (EditText) view.findViewById(R.id.time);
        et_name = (EditText)view.findViewById(R.id.name);
        et_pwd = (EditText)view.findViewById(R.id.pwd);

        textView1 = (TextView)view.findViewById(R.id.tv1);
        textView2 = (TextView)view.findViewById(R.id.tv2);
        textView3 = (TextView)view.findViewById(R.id.tv3);
        textView4 = (TextView)view.findViewById(R.id.tv4);
        textView5 = (TextView)view.findViewById(R.id.tv5);
        textView6 = (TextView)view.findViewById(R.id.tv6);
        textView7 = (TextView)view.findViewById(R.id.tv7);
        textView8 = (TextView)view.findViewById(R.id.tv8);

        list.add(textView1);
        list.add(textView2);
        list.add(textView3);
        list.add(textView4);
        list.add(textView5);
        list.add(textView6);
        list.add(textView7);
        list.add(textView8);

        button = (Button)view.findViewById(R.id.but);


        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tm = et_time.getText().toString();
                fm = et_name.getText().toString();
                to = et_pwd.getText().toString();

                new Spilt().execute();

            }
        });


        return view;
    }

    private class Spilt extends AsyncTask<String,Void,Integer> {
        Integer sh=0;
        String sr;
        @Override
        protected Integer doInBackground(String... params) {

            try {
                if(tm!=null&&fm!=null&&to!=null) {
                    sr = csearch.start("search|" + tm + "|" + fm + "|" + to);
                    sh = 1;

                }
                else{
                    sh = 0;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return sh;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if(sh==1){
                strarray=sr.split("\\|");
                for(int i=0;i<strarray.length;i++){
                    list.get(i).setText(strarray[i]);
                }
            }
            else {
                Toast.makeText(getActivity(),"Please write informations", Toast.LENGTH_SHORT).show();
            }
        }
    }




}
