package com.example.admin.lesson9;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void addNotif(String name, String content) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues contentValues = getContentValues(name, content);
            addNotifInternal(database, contentValues);
            database.beginTransaction();
            database.setTransactionSuccessful();
        } catch (SQLiteException e) {


        } finally {
            if (database != null) {
                if (database.inTransaction()) {
                    database.endTransaction();
                }
            }
        }
    }


    public ContentValues getContentValues(String name, String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("date", new Date().getTime());
        contentValues.put("content", content);
        return contentValues;

    }

    public void addNotifInternal(SQLiteDatabase database, ContentValues contentValues) {

        database.insert("Notif", null, contentValues);


    }


    public List<String> getNotif() {
        List<String> notif = null;
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getReadableDatabase();
            database.beginTransaction();
            Cursor cursor = database.query("Notif", null, null, null,
                    null, null, null);
            notif = parseNotif(cursor);
            cursor.close();
            database.setTransactionSuccessful();


        } catch (SQLiteException e) {

        } finally {
            if (database != null) {
                if (database.inTransaction()) {
                    database.endTransaction();
                }
            }


        }
        return notif;
    }

    private List<String> parseNotif(Cursor cursor) {

        List<String> resultNotif = new ArrayList<>();
        while (cursor.moveToNext()) {
            String fullName = cursor.getString(cursor.getColumnIndex("name")) +
                    cursor.getString(cursor.getColumnIndex("content")) +
                    cursor.getString(cursor.getColumnIndex("date"));
            System.out.println(fullName);
            resultNotif.add(fullName);
        }


        return resultNotif;

    }
}



