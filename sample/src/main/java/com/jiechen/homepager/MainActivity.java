package com.jiechen.homepager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>(Arrays.asList("QuickStartActivity",
            "CustomInXmlActivity", "CustomInJavaActivity", "UseInFragment",
            "RadioGroupActivity", "TabHostActivity"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        MyAdapter adapter = new MyAdapter(mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<String> data;

        public MyAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.textView.setText(data.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    switch (position) {
                        case 0:
                            intent = new Intent(MainActivity.this, QuickStartActivity.class);
                            break;

                        case 1:
                            intent = new Intent(MainActivity.this, CustomXmlActivity.class);
                            break;

                        case 2:
                            intent = new Intent(MainActivity.this, CustomJavaActivity.class);
                            break;

                        case 3:
                            intent = new Intent(MainActivity.this, UseFragmentActivity.class);
                            break;

                        case 4:
                            intent = new Intent(MainActivity.this, RadioGroupActivity.class);
                            break;

                        case 5:
                            intent = new Intent(MainActivity.this, TabHostActivity.class);
                            break;
                    }

                    MainActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
