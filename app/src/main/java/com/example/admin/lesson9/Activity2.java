package com.example.admin.lesson9;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.lesson9.model.DBManager;

public class Activity2 extends Activity {
    DBManager dbManager;

   private EditText idEdit;
    private EditText nameEdit;
    private EditText contentEdit;
    private EditText dateEdit;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        init();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void init() {
       idEdit=(EditText) findViewById(R.id.editID);
        nameEdit = (EditText) findViewById(R.id.editName);
        contentEdit = (EditText) findViewById(R.id.editContent);
        dateEdit = (EditText) findViewById(R.id.editDate);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        idEdit.setText(getIntent().getStringExtra("id"));
        nameEdit.setText(getIntent().getStringExtra("name"));
        dateEdit.setText(getIntent().getStringExtra("date"));
        contentEdit.setText(getIntent().getStringExtra("content"));



        if (getIntent().getStringExtra("color") != null) {
            contentEdit.setBackgroundColor(Color.parseColor(getIntent().getStringExtra("color")));
        }

    }


    public void onClickSave(View view) {
        dbManager = new DBManager(this);
        dbManager.deleteNotificationbyName(getIntent().getStringExtra("name"));
        dbManager.addNotificatoin(null, nameEdit.getText().toString(), contentEdit.getText().toString());
    }
}
