package com.example.dongzhe.mycarpool;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PassengerBrowse extends Fragment {

    private Handler sendHandler;

    Button btn_s,btn1;
    ImageButton btn_m;
    EditText et_time,et_from,et_to;

    String time=null;
    String from=null;
    String to=null;
    String mm;

    String content;

    String [] strarray;
    Client csearch = new Client();

    TextView textView1,textView2,textView3,textView4,textView5,textView6,mail;

    EditText ss;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_passenger_browse, container, false);

        sendHandler =  new Handler();

        btn_s=(Button) view.findViewById(R.id.button_p_search);
        btn1=(Button) view.findViewById(R.id.button_pass_car);
        btn_m=(ImageButton) view.findViewById(R.id.Button_sendmail);

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

        mail = (TextView)layout.findViewById(R.id.TextView_dia_mail);
        ss = (EditText) layout.findViewById(R.id.editText_sendmail_in);

        TextView t = (TextView)layout.findViewById(R.id.TextView_dia_mail);
        t.setText(mm);

        btn_m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                content =ss.getText().toString();
                SendTask sTask = new SendTask();
                sTask.execute();
            }
        });

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

    class SendTask extends AsyncTask<Integer, Integer, String> {
        //后面尖括号内分别是参数（例子里是线程休息时间），进度(publishProgress用到)，返回值 类型

        @Override
        protected void onPreExecute() {
            //第一个执行方法
            Toast.makeText(getActivity(), "Begin Send!", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            //第二个执行方法,onPreExecute()执行完后执行
            // TODO Auto-generated method stub
            Mail m = new Mail("dongzhe2015", "ribenliuxue");

            String[] toArr = {mm};
            m.setTo(toArr);
            m.setFrom("dongzhe2015@yahoo.co.jp");
            m.setSubject("募集した情報");
            m.setBody(content);


            try {
                //If you want add attachment use function addAttachment.
                //m.addAttachment("/sdcard/filelocation");

                if(m.send()) {
                    System.out.println("Email was sent successfully.");
                } else {
                    System.out.println("Email was not sent.");
                }
            } catch(Exception e) {
                //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                Log.e("MailApp", "Could not send email", e);
            }

            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            //这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
            //但是这里取到的是一个数组,所以要用progesss[0]来取值
            //第n个参数就用progress[n]来取值
            super.onProgressUpdate(progress);
        }

        @Override
        protected void onPostExecute(String r) {
            //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
            //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"
            //setTitle(result);
            super.onPostExecute(r);
        }

    }
}
