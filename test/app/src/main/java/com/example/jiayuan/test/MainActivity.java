package com.example.jiayuan.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button b;
    TextView t,t1,t2;
    String str="wo|ai|ni";
    String [] strarray;
    ArrayList<TextView> list=new ArrayList<TextView>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t=(TextView)findViewById(R.id.TextView1);
        t1=(TextView)findViewById(R.id.TextView2);
        t2=(TextView)findViewById(R.id.TextView3);
        b=(Button)findViewById(R.id.Button1);
        list.add(t);
        list.add(t1);
        list.add(t2);
      b.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Spilt();
          }
      });
    }
    public void Spilt(){
        strarray=str.split("\\|");
        for(int i=0;i<strarray.length;i++){
            list.get(i).setText(strarray[i]);
        }
    }
}
