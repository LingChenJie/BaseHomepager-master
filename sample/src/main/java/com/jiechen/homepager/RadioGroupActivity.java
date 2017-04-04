package com.jiechen.homepager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.jiechen.homepager.fragment.FragmentCommon;
import com.jiechen.homepager.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class RadioGroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RadioGroupActivity";

    private RadioButton rb01, rb02, rb03, rb04, rb05;
    private NoScrollViewPager viewPager;

    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);

        viewPager = (NoScrollViewPager) findViewById(R.id.view_pager);

        rb01 = (RadioButton) findViewById(R.id.tab_01);
        rb02 = (RadioButton) findViewById(R.id.tab_02);
        rb03 = (RadioButton) findViewById(R.id.tab_03);
        rb04 = (RadioButton) findViewById(R.id.tab_04);
        rb05 = (RadioButton) findViewById(R.id.tab_05);

        rb01.setOnClickListener(this);
        rb02.setOnClickListener(this);
        rb03.setOnClickListener(this);
        rb04.setOnClickListener(this);
        rb05.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        fragmentList.add(FragmentCommon.newInstance("首页"));
        fragmentList.add(FragmentCommon.newInstance("分类"));
        fragmentList.add(FragmentCommon.newInstance("咨询"));
        fragmentList.add(FragmentCommon.newInstance("购物车"));
        fragmentList.add(FragmentCommon.newInstance("我的"));

        viewPager.setAdapter(new RadioFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOffscreenPageLimit(5);// 缓存5个页面
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        int item = 0;
        switch (v.getId()) {
            case R.id.tab_01:
                item = 0;
                break;

            case R.id.tab_02:
                item = 1;
                break;

            case R.id.tab_03:
                item = 2;
                break;

            case R.id.tab_04:
                item = 3;
                break;

            case R.id.tab_05:
                item = 4;
                break;
        }

        viewPager.setCurrentItem(item, false);// 切换时去除动画效果
    }

    class RadioFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public RadioFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
