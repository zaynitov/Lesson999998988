package com.example.admin.lesson9;

import android.content.ContentValues;

public class Converter {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CONTENT = "content";

    public static Notification convertValuesToNotification(ContentValues contentValues) {
        return new Notification(contentValues.getAsInteger(ID),
                contentValues.getAsString(NAME), contentValues.getAsString(CONTENT));

    }

    public static ContentValues convertNotificationtoValues(Notification notification) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, notification.getId());
        contentValues.put(NAME, notification.getName());
        contentValues.put(CONTENT, notification.getContent());
        return contentValues;
    }


}
