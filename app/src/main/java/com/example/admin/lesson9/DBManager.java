package com.example.admin.lesson9;


import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public DBManager(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void addNotificatoin(Integer id, String name, String content) {
        SQLiteDatabase database = null;
        try {
            database = dbHelper.getWritableDatabase();
            ContentValues contentValues = getContentValues(id, name, content);

            addNotificationInternal(database, contentValues);
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


    public ContentValues getContentValues(Integer id, String name, String content) {
        ContentValues contentValues = new ContentValues();
        if (id!=null) {
            contentValues.put("id", id);
        }
        contentValues.put("name", name);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        contentValues.put("date", dateFormat.format(date));
        contentValues.put("content", content);
        return contentValues;

    }

    public void addNotificationInternal(SQLiteDatabase database, ContentValues contentValues) {
        database.insert("Notifications", null, contentValues);
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


    public String[] getNotificationbyID(Integer id) {
        Cursor cursor = null;
        String[] stringArray = new String[3];
        SQLiteDatabase database = null;

        try {
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM Notif WHERE id=?", new String[]{id.toString()});
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


    public String[] getNotificationbyName(String name) {
        Cursor cursor = null;
        String[] stringArray = new String[3];
        SQLiteDatabase database = null;

        try {
            database = dbHelper.getReadableDatabase();
            cursor = database.rawQuery("SELECT * FROM Notif WHERE name=?", new String[]{name.toString()});
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









    public void deleteNotificationbyID(Integer id) {
        Cursor cursor = null;
        String[] stringArray = new String[3];
        SQLiteDatabase database = null;

        database = dbHelper.getWritableDatabase();

        String table = "Notification";
        String whereClause = "id=?";
        String[] whereArgs = new String[]{id.toString()};
        database.delete(table, whereClause, whereArgs);
        database.close();
    }


}



