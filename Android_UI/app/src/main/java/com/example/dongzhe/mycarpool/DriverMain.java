package com.example.dongzhe.mycarpool;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DriverMain extends AppCompatActivity implements View.OnClickListener{

    // 三个tab布局
    private RelativeLayout knowLayout, iWantKnowLayout, meLayout;

    // 底部标签切换的Fragment
    private Fragment knowFragment, iWantKnowFragment, meFragment,
            currentFragment;
    // 底部标签图片
    private ImageView knowImg, iWantKnowImg, meImg;
    // 底部标签的文本
    private TextView knowTv, iWantKnowTv, meTv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        initUI();
        initTab();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    /**
     * 初始化UI
     */
    private void initUI() {
        knowLayout = (RelativeLayout) findViewById(R.id.rl_know);
        iWantKnowLayout = (RelativeLayout) findViewById(R.id.rl_want_know);
        meLayout = (RelativeLayout) findViewById(R.id.rl_me);
        knowLayout.setOnClickListener( this);
        iWantKnowLayout.setOnClickListener(this);
        meLayout.setOnClickListener( this);

        knowImg = (ImageView) findViewById(R.id.iv_know);
        iWantKnowImg = (ImageView) findViewById(R.id.iv_i_want_know);
        meImg = (ImageView) findViewById(R.id.iv_me);
        knowTv = (TextView) findViewById(R.id.tv_know);
        iWantKnowTv = (TextView) findViewById(R.id.tv_i_want_know);
        meTv = (TextView) findViewById(R.id.tv_me);

    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (knowFragment == null) {
            knowFragment = new DriverRaiseActivity();
        }

        if (!knowFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, knowFragment).commit();

            // 记录当前Fragment
            currentFragment = knowFragment;
            // 设置图片文本的变化
            knowImg.setImageResource(R.drawable.btn_know_pre);
            knowTv.setTextColor(getResources()
                    .getColor(R.color.bottomtab_press));
            iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
            iWantKnowTv.setTextColor(getResources().getColor(
                    R.color.bottomtab_normal));
            meImg.setImageResource(R.drawable.btn_my_nor);
            meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_know: // 募集
                clickTab1Layout();
                break;
            case R.id.rl_want_know: // 搜索
                clickTab2Layout();
                break;
            case R.id.rl_me: // me
                clickTab3Layout();
                break;
            default:
                break;
        }
    }

    /**
     * 点击第一个tab
     */
    private void clickTab1Layout() {
        if (knowFragment == null) {
            knowFragment = new DriverRaiseActivity();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), knowFragment);

        // 设置底部tab变化
        knowImg.setImageResource(R.drawable.btn_know_pre);
        knowTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
        iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
        iWantKnowTv.setTextColor(getResources().getColor(
                R.color.bottomtab_normal));
        meImg.setImageResource(R.drawable.btn_my_nor);
        meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
    }

    /**
     * 点击第二个tab
     */
    private void clickTab2Layout() {
        if (iWantKnowFragment == null) {
            iWantKnowFragment = new driver_browse();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), iWantKnowFragment);

        knowImg.setImageResource(R.drawable.btn_know_nor);
        knowTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
        iWantKnowImg.setImageResource(R.drawable.btn_wantknow_pre);
        iWantKnowTv.setTextColor(getResources().getColor(
                R.color.bottomtab_press));
        meImg.setImageResource(R.drawable.btn_my_nor);
        meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

    }

    /**
     * 点击第三个tab
     */
    private void clickTab3Layout() {
        if (meFragment == null) {
            meFragment = new DriverImformationActivity();
        }

        addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);
        knowImg.setImageResource(R.drawable.btn_know_nor);
        knowTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
        iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
        iWantKnowTv.setTextColor(getResources().getColor(
                R.color.bottomtab_normal));
        meImg.setImageResource(R.drawable.btn_my_pre);
        meTv.setTextColor(getResources().getColor(R.color.bottomtab_press));

    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }

        currentFragment = fragment;
    }




}
