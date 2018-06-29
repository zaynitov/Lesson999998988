package com.example.admin.lesson9;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class DBManager {
    private DBHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void addNotif(String name,String content) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues contentValues =getContentValues(name,content);

        }
        {

        }

    }


    public ContentValues getContentValues(String name, String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("date", new Date().getTime());
        contentValues.put("content", content);
        return contentValues;

    }

    public void addNotifInternal(SQLiteDatabase database, ContentValues contentValues){

        database.insert("Notif", null,contentValues);


    }


}
