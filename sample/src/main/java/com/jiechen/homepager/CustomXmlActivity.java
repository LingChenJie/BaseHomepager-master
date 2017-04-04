package com.jiechen.homepager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jiechen.homepager.fragment.FragmentCommon;
import com.jiechen.tabview.TabView;
import com.jiechen.tabview.TabViewChild;

import java.util.ArrayList;
import java.util.List;

public class CustomXmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_xml);

        TabView tabView = (TabView) findViewById(R.id.tab_view);

        List<TabViewChild> tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild1 = new TabViewChild(R.drawable.tab01_sel, R.drawable.tab01_unsel, "首页", FragmentCommon.newInstance("首页"));
        TabViewChild tabViewChild2 = new TabViewChild(R.drawable.tab02_sel, R.drawable.tab02_unsel, "分类", FragmentCommon.newInstance("分类"));
        TabViewChild tabViewChild3 = new TabViewChild(R.drawable.tab03_sel, R.drawable.tab03_unsel, "咨询", FragmentCommon.newInstance("咨询"));
        TabViewChild tabViewChild4 = new TabViewChild(R.drawable.tab04_sel, R.drawable.tab04_unsel, "购物车", FragmentCommon.newInstance("购物车"));
        TabViewChild tabViewChild5 = new TabViewChild(R.drawable.tab05_sel, R.drawable.tab05_unsel, "我的", FragmentCommon.newInstance("我的"));

        tabViewChildList.add(tabViewChild1);
        tabViewChildList.add(tabViewChild2);
        tabViewChildList.add(tabViewChild3);
        tabViewChildList.add(tabViewChild4);
        tabViewChildList.add(tabViewChild5);

        tabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
    }
}
