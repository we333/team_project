package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
        Button driverimfomationActivitySwitchButton=(Button)findViewById(R.id.button_imformation);

        driverimfomationActivitySwitchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                Intent intent=new Intent(getApplicationContext(),DriverImformationActivity.class);
                startActivity(intent);
            }
        });
        Button driverraiseSwitchButton=(Button)findViewById(R.id.button_raise);
        driverraiseSwitchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                Intent intent=new Intent(getApplicationContext(),DriverRaiseActivity.class);
                startActivity(intent);
            }
        });
        Button driverbrowseSwitchButton=(Button)findViewById(R.id.button_browse);
        driverbrowseSwitchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                Intent intent=new Intent(getApplicationContext(),driver_browse.class);
                startActivity(intent);
            }
        });



    }

}
