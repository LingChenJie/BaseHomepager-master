package com.jiechen.homepager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiechen.homepager.R;

/**
 * FragmentCommon
 * Created by JieChen on 2017/4/1.
 */

public class FragmentCommon extends Fragment {

    public static FragmentCommon newInstance(String title) {
        FragmentCommon fragmentCommon = new FragmentCommon();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        String title = getArguments().getString("title");
        tvTitle.setText(title);

        return view;
    }
}
