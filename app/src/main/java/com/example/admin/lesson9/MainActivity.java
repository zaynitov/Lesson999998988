package com.example.admin.lesson9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        List<String> listForRecView = new ArrayList<>();
        dbManager = new DBManager(this);
        mAdapter = new CustomAdapter(dbManager.getNotif());
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        init();

    }

    private String stringArrayToString(String[] strings) {
        StringBuilder builder = new StringBuilder();
        for(String s : strings) {
            builder.append(s);
        }
        return builder.toString();
    }


    public void onClickCreate(View view) {
        Intent intent = new Intent(MainActivity.this, Activity3.class);
        startActivity(intent);

    }
}
