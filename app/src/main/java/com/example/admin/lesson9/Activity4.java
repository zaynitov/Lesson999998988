package com.example.admin.lesson9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity4 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);
        init();
    }

    private void init() {


    }

    public void onClick12px(View view) {

        Intent intent = new Intent();
        intent.putExtra("color", "#FFF000");
        setResult(RESULT_OK, intent);
        finish();

    }
}
