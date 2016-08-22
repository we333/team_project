package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PassengerMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);

        Button passengerimformationSwitchButton=(Button)findViewById(R.id.passenger_imformation);
        passengerimformationSwitchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                Intent intent=new Intent(getApplicationContext(),PassengerImformation.class);
                startActivity(intent);
            }
        });

        Button passengerbrowseSwitchButton=(Button)findViewById(R.id.button_passengerbrowse);
        passengerbrowseSwitchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0){
                Intent intent=new Intent(getApplicationContext(),PassengerBrowse.class);
                startActivity(intent);
            }
        });
    }
}
