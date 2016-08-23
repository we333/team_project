package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    Client ct = new Client();
    EditText et_name,et_pwd;

    String get_n,get_p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        btn1 = (Button) findViewById(R.id.button_diver);
        btn2 = (Button) findViewById(R.id.button_noru);
        btn3=(Button) findViewById(R.id.button_needhelp);
        btn4=(Button) findViewById(R.id.button_register);

        et_name =(EditText) findViewById(R.id.editText_name);
        et_pwd =(EditText) findViewById(R.id.editText_pass);


        btn1.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                get_n = et_name.getText().toString();
                get_p = et_pwd.getText().toString();
                new login_try_driver().execute();
            }

        });



        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                get_n = et_name.getText().toString();
                get_p = et_pwd.getText().toString();
                new login_try_pass().execute();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(getApplicationContext(),NeedHelp.class);
                startActivity(intent);
            }
        });

    }

    private class login_try_driver extends AsyncTask<String,Void,Integer>{

        @Override
        protected Integer doInBackground(String... params) {
            String bk;
            Integer res=0;
            try {

                bk = ct.start("login|"+get_n+"|"+get_p);
                if(bk.equals("success")){
                    res = 1;
                }
                else {
                    res = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(Integer res) {
            if(res==1){
                Intent intent=new Intent(getApplicationContext(),DriverMain.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"UserName/PassWord is wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class login_try_pass extends AsyncTask<String,Void,Integer>{

        @Override
        protected Integer doInBackground(String... params) {
            String bk;
            Integer res=0;
            try {

                bk = ct.start("login|"+get_n+"|"+get_p);
                if(bk.equals("success")){
                    res = 1;
                }
                else {
                    res = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }

        @Override
        protected void onPostExecute(Integer res) {
            if(res==1){
                Intent intent=new Intent(getApplicationContext(),PassengerMain.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"UserName/PassWord is wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
