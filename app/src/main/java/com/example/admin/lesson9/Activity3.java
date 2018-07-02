package com.example.admin.lesson9;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class Activity3 extends AppCompatActivity {

    EditText textViewName;
    EditText textViewContent;
    Button buttonOk;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout3);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {
        dbManager = new DBManager(this);
        textViewName = (EditText) findViewById(R.id.editTextName);
        textViewContent = (EditText) findViewById(R.id.editTextContent);

        buttonOk = (Button) findViewById(R.id.buttonOk);
    }


    private void createNotif(String name, String content) {
        dbManager = new DBManager(this);
        dbManager.addNotif(name, content);

    }

    public void clickOk(View view) {


        createNotif(textViewName.getText().toString(), textViewContent.getText().toString());
        buttonOk.setText("Created");


    }
}
