package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewPassword extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        btn1=(Button)findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(NewPassword.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
