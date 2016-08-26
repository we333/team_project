package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
	
    Client register = new Client();
    EditText nm,pwd,eml;
    Button btn1;
    String get_nm,get_pwd,get_eml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        btn1 = (Button) findViewById(R.id.button_reg);

        nm = (EditText)findViewById(R.id.editText_name);
        pwd = (EditText)findViewById(R.id.editText_pass);
        eml = (EditText)findViewById(R.id.editText_mail);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                get_nm = nm.getText().toString();
                get_pwd = pwd.getText().toString();
                get_eml = eml.getText().toString();

                new reg().execute();

            }
        });

    }

    public class reg extends AsyncTask<String,Void,Integer>{
        String rg;
        Integer rt=0;
        @Override
        protected Integer doInBackground(String... params) {
            try {
	//connect to server
                rg = register.start("register|"+get_nm+"|"+get_pwd+"|"+get_eml);
                if(rg.equals("success")){
                    rt = 1;
                }
                else {
                    rt = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rt;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if(rt==1){
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(),"UserName/Email is exist!", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
