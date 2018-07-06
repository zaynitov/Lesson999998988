package com.example.admin.lesson9;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.admin.lesson9";
    private static final String NOTIFICATION_TABLE = "notifications";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NOTIFICATION_TABLE);
    public static final int NOTIFICATIONS = 1;
    public static final int NOTIFICATION_ID = 2;
    private static final UriMatcher sURIMatcger = new UriMatcher(UriMatcher.NO_MATCH);


    private Dao mDao;
    private DBHelper dbHelper;


    static {
        sURIMatcger.addURI(AUTHORITY, NOTIFICATION_TABLE, NOTIFICATIONS);
        sURIMatcger.addURI(AUTHORITY, NOTIFICATION_TABLE + "/#", NOTIFICATION_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        mDao = new DaoImplementation(new DBManager(dbHelper));
        return dbHelper != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(NOTIFICATION_TABLE);
        int uriType = sURIMatcger.match(uri);

        switch (uriType) {
            case NOTIFICATION_ID:
                queryBuilder.appendWhere(Converter.ID + "=" + uri.getLastPathSegment());
                break;
            case NOTIFICATIONS:
                break;
            default:
                throw new IllegalArgumentException("Unknown URL");
        }

        Cursor cursor = queryBuilder.query(dbHelper.getReadableDatabase(), projection, selection, selectionArgs, null,
                null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int uriType = sURIMatcger.match(uri);
        long id = 0;
        switch (uriType) {
            case NOTIFICATIONS:
                id = mDao.addNotification(Converter.convertValuesToNotification(contentValues));
                break;
            default:
                throw new IllegalArgumentException("Unknown URL");
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(NOTIFICATION_TABLE + "/" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
