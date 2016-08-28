package com.example.dongzhe.mycarpool;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
        nm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int Start = nm.getSelectionStart();
                int end = nm.getSelectionEnd();
                String str = s.toString();
                if (!(str.matches("[a-zA-Z-0-9]{0,13}"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("不正なユーザー名");
                    builder.setMessage("Please enter the letters or numbers");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    s.delete(end - 1, end);
                }
            }
        });
        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int Start = pwd.getSelectionStart();
                int end = pwd.getSelectionEnd();
                String str = s.toString();
                if (!(str.matches("[a-zA-Z-0-9]{0,13}"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("不正なパスワード");
                    builder.setMessage("Please enter the letters or numbers");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    s.delete(end - 1, end);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){
            // @Override
            public void onClick(View v) {
                get_nm = nm.getText().toString();
                get_pwd = pwd.getText().toString();
                get_eml = eml.getText().toString();
                if(get_nm.length()>6||get_nm.length()<4){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("ユーザー名のフォーマットエラー");
                    builder.setMessage("Length should be 4-6");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if (get_pwd.length()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("パスワードを入力してください");
                    builder.setMessage("Password can not be empty");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else if (!(get_eml.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("メールのフォーマットエラー");
                    builder.setMessage("Mailbox is malformed");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    new reg().execute();
                }

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
