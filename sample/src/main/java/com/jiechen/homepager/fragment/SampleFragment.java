package com.jiechen.homepager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiechen.homepager.R;
import com.jiechen.tabview.TabView;
import com.jiechen.tabview.TabViewChild;

import java.util.ArrayList;
import java.util.List;

/**
 * SampleFragment
 * Created by JieChen on 2017/4/4.
 */

public class SampleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);

        TabView tabView = (TabView) view.findViewById(R.id.tab_view);

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

        tabView.setTabViewChild(tabViewChildList, getChildFragmentManager());

        return view;
    }
}
