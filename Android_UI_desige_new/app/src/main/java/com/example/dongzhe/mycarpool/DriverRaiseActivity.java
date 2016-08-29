package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DriverRaiseActivity extends Fragment {

    Client send = new Client();

    EditText time,from,to,price,others;
    Button butt1;
    String get_time,get_from,get_to,get_price,get_others,get_seat,drivername;

    private List<String> list = new ArrayList<String>();
    private TextView myTextView;
    private Spinner mySpinner;
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_driver_raise, container, false);

        time = (EditText)view.findViewById(R.id.editText_date) ;
        from = (EditText)view.findViewById(R.id.editText_kara);
        to = (EditText)view.findViewById(R.id.editText_made) ;
        price = (EditText)view.findViewById(R.id.editText_pri) ;
        others = (EditText)view.findViewById(R.id.editText_comm);

        butt1 = (Button)view.findViewById(R.id.button3);



        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        myTextView = (TextView)view.findViewById(R.id.textView_seat);
        mySpinner = (Spinner)view.findViewById(R.id.spinner_seat);

        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, list);

        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);

        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                /* 将所选mySpinner 的值带入myTextView 中*/
                myTextView.setText("選択したのは："+ adapter.getItem(arg2));
                /* 将mySpinner 显示*/
                arg0.setVisibility(View.VISIBLE);

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                myTextView.setText("NONE");
                arg0.setVisibility(View.VISIBLE);
            }
        });
        /*下拉菜单弹出的内容选项触屏事件处理*/
        mySpinner.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                /**
                 *
                 */
                return false;
            }
        });
        /*下拉菜单弹出的内容选项焦点改变事件处理*/
        mySpinner.setOnFocusChangeListener(new Spinner.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

            }
        });

        butt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                get_time = time.getText().toString();
                get_from = from.getText().toString();
                get_to = to.getText().toString();
                get_price = price.getText().toString();
                get_others = others.getText().toString();
                get_seat = mySpinner.getSelectedItem().toString();

                System.out.println(get_time);
                System.out.println(get_from);
                System.out.println(get_to);
                System.out.println(get_price);
                System.out.println(get_others);
                System.out.println(get_seat);

                new bosyu().execute();
            }
        });

        return view;
    }

    private class bosyu extends AsyncTask<String,Void,Integer>{
        String sd;
        Integer rt=0;
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Intent intent = getActivity().getIntent();
                drivername = intent.getStringExtra("name");
                System.out.println(drivername);
                sd = send.start("upload|"+drivername+"|"+get_time+"|"+get_from+"|"+get_to+"|"+get_price+"|"+get_seat+"|"+get_others+"|");
                if(sd.equals("success")){
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
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment fragment = new driver_browse();
                fm.beginTransaction().replace(R.id.content_layout,fragment).commit();

            }else{
                Toast.makeText(getActivity(),"UserName/PassWord is wrong!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
