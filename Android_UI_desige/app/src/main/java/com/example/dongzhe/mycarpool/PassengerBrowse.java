package com.example.dongzhe.mycarpool;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PassengerBrowse extends Fragment {

    Button btn_s,btn1;
    EditText et_time,et_from,et_to;

    String time=null;
    String from=null;
    String to=null;
    String mm;

    String [] strarray;
    Client csearch = new Client();

    TextView textView1,textView2,textView3,textView4,textView5,textView6,mail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_passenger_browse, container, false);

        btn_s=(Button) view.findViewById(R.id.button_p_search);
        btn1=(Button) view.findViewById(R.id.button_pass_car);

        et_time=(EditText)view.findViewById(R.id.editText_search_date) ;
        et_from=(EditText)view.findViewById(R.id.editText_search_from) ;
        et_to=(EditText)view.findViewById(R.id.editText_search_to) ;




        textView1=(TextView)view.findViewById(R.id.textView_s_date);
        textView2=(TextView)view.findViewById(R.id.textView_p_from);
        textView3=(TextView)view.findViewById(R.id.textView_p_to);
        textView4=(TextView)view.findViewById(R.id.textView_p_price);
        textView5=(TextView)view.findViewById(R.id.textView_p_seat);
        textView6=(TextView)view.findViewById(R.id.textView_p_comment);


        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                init();
            }
        });

        btn_s.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                time = et_time.getText().toString();
                from = et_from.getText().toString();
                to = et_to.getText().toString();

                new Spilt().execute();

            }
        });

        return view;
    }

    public void init(){
        LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout=inflater.inflate(R.layout.passenger_browse_dialog,(ViewGroup)getActivity().findViewById(R.id.LinearLayout1));
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        TextView t = (TextView)layout.findViewById(R.id.TextView_dia_mail);
        t.setText(mm);
        builder.setTitle("車主情報");
        builder.setView(layout);
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    private class Spilt extends AsyncTask<String,Void,Integer> {
        Integer sh = 0;
        String sr;

        @Override
        protected Integer doInBackground(String... params) {
            try {
                if (time==null || from==null || to==null) {
                    sh = 0;
                } else {
                    sr = csearch.start("search|" + time + "|" + from + "|" + to);

                    sh = 1;
                    time = null;
                    from = null;
                    to = null;
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
                if(sr==null){
                    Toast.makeText(getActivity(),"no result", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        System.out.println(sr);
                        strarray=sr.split("\\|");

                        btn1.setText(strarray[0]);
                        mm = strarray[1];
                        textView1.setText(strarray[2]);
                        textView2.setText(strarray[3]);
                        textView3.setText(strarray[4]);
                        textView4.setText(strarray[5]);
                        textView5.setText(strarray[6]);
                        textView6.setText(strarray[7]);

                    }catch(Exception e){e.printStackTrace();}
                }
            }
            else {
                Toast.makeText(getActivity(),"Please write informations", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
