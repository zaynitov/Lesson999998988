package com.example.admin.lesson9;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.lesson9.dao.Dao;
import com.example.admin.lesson9.helpers.RecyclerItemClickListener;
import com.example.admin.lesson9.model.DBManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Dao daoImplementation;
    private DBManager dbManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    private Button buttonToCreate;
    private String nameColour;


    private static final String AUTHORITY = "com.example.admin.lesson9";
    private static final String NOTE_TABLE = "Notification";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + NOTE_TABLE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void init() {
        buttonToCreate = (Button) findViewById(R.id.buttonadd);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleview);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        List<String> listForRecView = new ArrayList<>();
        dbManager = new DBManager(this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mAdapter = new CustomAdapter(dbManager.getNotifications());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        TextView viewById = (TextView) view.findViewById(R.id.textview);
                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        Log.d("notif", "onItemClicked" + viewById.getText().toString());
                        Log.d("notif",
                                dbManager.getNotificationbyName(viewById.getText().toString())[2]);
                        Log.d("notifcrea", "s" + viewById.getText().toString()
                                + " " + dbManager.getNotificationbyName(viewById.getText().toString())[0] + " " +
                                dbManager.getNotificationbyName(viewById.getText().toString())[1] + " " +
                                dbManager.getNotificationbyName(viewById.getText().toString())[2] + " " +
                                dbManager.getNotificationbyName(viewById.getText().toString())[3]);

                        intent.putExtra("id", dbManager.getNotificationbyName(viewById.getText().toString())[0]);
                        intent.putExtra("name", dbManager.getNotificationbyName(viewById.getText().toString())[1]);
                        intent.putExtra("date", dbManager.getNotificationbyName(viewById.getText().toString())[2]);
                        intent.putExtra("content", dbManager.getNotificationbyName(viewById.getText().toString())[3]);
                        intent.putExtra("color", nameColour);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        Button button = (Button) findViewById(R.id.yourbuttonid);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent dbmanager = new Intent(MainActivity.this, AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        System.out.println("HOOOOOOOOOOOOOOOOOOOOOOOOOO");
        System.out.println("HOOOOOOOOOOOOOOOOOOOOOOOOOO");
        int columnCount = cursor.getColumnCount();
        System.out.println("Count of columns is " + columnCount);
        while (cursor.moveToNext()){
            System.out.println((cursor.getString(cursor.getColumnIndex("name"))));
            System.out.println((cursor.getString(cursor.getColumnIndex("content"))));

        }
        System.out.println(cursor);
    }

    public void onClickCreate(View view) {
        Intent intent = new Intent(MainActivity.this, Activity3.class);
        startActivity(intent);
    }

    public void onClickChangeProp(View view) {
        Intent intent = new Intent(MainActivity.this, Activity4.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        nameColour = data.getStringExtra("color");
    }
}
