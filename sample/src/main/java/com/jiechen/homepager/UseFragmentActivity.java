package com.jiechen.homepager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiechen.homepager.fragment.SampleFragment;

public class UseFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_fragment);

        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new SampleFragment()).commit();
    }
}
