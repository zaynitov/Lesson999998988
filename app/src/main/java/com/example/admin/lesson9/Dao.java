package com.example.admin.lesson9;

public interface Dao {
    long addNotification(Notification notification);

    int updateNotification(Notification notification);

    int updateNotificationById(int id, Notification notification);

    void deleteNotificationById(int id);

    Notification getNotificationById(int id);

}
