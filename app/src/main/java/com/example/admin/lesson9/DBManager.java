package com.example.admin.lesson9;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        contentValues.put("date", dateFormat.format(date));

        contentValues.put("content", content);
        return contentValues;

    }

    public void addNotifInternal(SQLiteDatabase database, ContentValues contentValues) {
        database.insert("Notif", null, contentValues);
    }


    public List<String[]> getNotif() {
        List<String[]> notif = null;
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

    private List<String[]> parseNotif(Cursor cursor) {

        List<String[]> resultNotif = new ArrayList<>();
        while (cursor.moveToNext()) {
            String[] stringArray = new String[3];
            stringArray[0] = cursor.getString(cursor.getColumnIndex("name"));
            stringArray[1] = cursor.getString(cursor.getColumnIndex("content"));
            stringArray[2] = cursor.getString(cursor.getColumnIndex("date"));

            resultNotif.add(stringArray);
        }


        return resultNotif;

    }


    public String[] getNotifbyName(String nameToFind) {
        Cursor cursor = null;
        String[] stringArray = new String[3];
        SQLiteDatabase database = null;

        try {
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM Notif WHERE name=?", new String[]{nameToFind});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                stringArray[0] = cursor.getString(cursor.getColumnIndex("name"));
                System.out.println(stringArray[0]);
                stringArray[2] = cursor.getString(cursor.getColumnIndex("content"));
                System.out.println(stringArray[1]);

                stringArray[1] = cursor.getString(cursor.getColumnIndex("date"));
            }
            return stringArray;
        } finally {
            cursor.close();
        }
    }

    public void deleteNotifbyName(String nameToFind) {
        Cursor cursor = null;
        String[] stringArray = new String[3];
        SQLiteDatabase database = null;

        database = dbHelper.getWritableDatabase();

        String table = "Notif";
        String whereClause = "name=?";
        String[] whereArgs = new String[]{nameToFind};
        database.delete(table, whereClause, whereArgs);
        database.close();
    }


}



