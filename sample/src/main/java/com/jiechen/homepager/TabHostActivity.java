package com.jiechen.homepager;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jiechen.homepager.fragment.FragmentCommon;

public class TabHostActivity extends AppCompatActivity {

    private FragmentTabHost tabHost;
    private String[] names = new String[]{"首页", "分类", "咨询", "购物车", "我的"};
    private int[] imgSelectors = new int[]{R.drawable.tab_01_selector, R.drawable.tab_02_selector,
            R.drawable.tab_03_selector, R.drawable.tab_04_selector, R.drawable.tab_05_selector};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        // 初始化
        tabHost.setup(this, getSupportFragmentManager(), R.id.frame_layout);

        for (int i = 0; i < names.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_tab, null);

            ImageView ivImg = (ImageView) view.findViewById(R.id.iv_img);
            TextView tvText = (TextView) view.findViewById(R.id.tv_text);
            ivImg.setImageResource(imgSelectors[i]);
            tvText.setText(names[i]);

            TabHost.TabSpec tabSpec = tabHost.newTabSpec(names[i])
                    .setIndicator(view);

            Bundle bundle = new Bundle();
            bundle.putString("title", names[i]);

            tabHost.addTab(tabSpec, FragmentCommon.class, bundle);
        }
    }
}
