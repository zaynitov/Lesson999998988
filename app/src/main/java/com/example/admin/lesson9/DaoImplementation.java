package com.example.admin.lesson9;

import android.content.ContentValues;

public class DaoImplementation implements Dao {
    private final DBManager dbManager;

    public DaoImplementation(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public long addNotification(Notification notification) {
        ContentValues contentValues = Converter.convertNotificationtoValues(notification);
        dbManager.addNotificatoin(null, contentValues.getAsString(Converter.NAME), contentValues.getAsString(Converter.CONTENT));
        return 1;

    }

    @Override
    public int updateNotification(Notification notification) {
        ContentValues contentValues = Converter.convertNotificationtoValues(notification);
        dbManager.deleteNotificationbyID(notification.getId());
        dbManager.addNotificatoin(null, contentValues.getAsString(Converter.NAME), contentValues.getAsString(Converter.CONTENT));
        return 1;
    }

    @Override
    public int updateNotificationById(int id, Notification notification) {
        ContentValues contentValues = Converter.convertNotificationtoValues(notification);
        dbManager.deleteNotificationbyID(notification.getId());
        dbManager.addNotificatoin(contentValues.getAsInteger(Converter.ID),
                contentValues.getAsString(Converter.NAME), contentValues.getAsString(Converter.CONTENT));
        return 1;

    }

    @Override
    public void deleteNotificationById(int id) {
        dbManager.deleteNotificationbyID(id);

    }

    @Override
    public Notification getNotificationById(int id) {

        String[] notificationAraybyID = dbManager.getNotificationbyID(id);
        Notification notification = new Notification();
        notification.setId(id);
        notification.setName(notificationAraybyID[0]);
        notification.setDate(notificationAraybyID[1]);
        notification.setContent(notificationAraybyID[2]);
        return notification;


    }
}
