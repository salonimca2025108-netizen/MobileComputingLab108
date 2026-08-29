package com.example.practical2108;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class StudentProvider extends ContentProvider {

    // Authority identifies this Content Provider
    public static final String AUTHORITY =
            "com.example.providerapp.provider";

    // Content URI
    public static final Uri CONTENT_URI =
            Uri.parse(
                    "content://" + AUTHORITY + "/students"
            );

    // Database object
    StudentDatabase database;


    @Override
    public boolean onCreate() {

        database = new StudentDatabase(
                getContext()
        );

        return true;
    }


    // Insert student data
    @Override
    public Uri insert(
            Uri uri,
            ContentValues values) {

        SQLiteDatabase db =
                database.getWritableDatabase();

        long id = db.insert(
                StudentDatabase.TABLE_NAME,
                null,
                values
        );

        if (id == -1) {
            return null;
        }

        return Uri.withAppendedPath(
                CONTENT_URI,
                String.valueOf(id)
        );
    }


    // Read student data
    @Override
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {

        SQLiteDatabase db =
                database.getReadableDatabase();

        return db.query(
                StudentDatabase.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }


    // Update student data
    @Override
    public int update(
            Uri uri,
            ContentValues values,
            String selection,
            String[] selectionArgs) {

        SQLiteDatabase db =
                database.getWritableDatabase();

        return db.update(
                StudentDatabase.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }


    // Delete student data
    @Override
    public int delete(
            Uri uri,
            String selection,
            String[] selectionArgs) {

        SQLiteDatabase db =
                database.getWritableDatabase();

        return db.delete(
                StudentDatabase.TABLE_NAME,
                selection,
                selectionArgs
        );
    }


    @Override
    public String getType(Uri uri) {

        return "vnd.android.cursor.dir/vnd.providerapp.student";
    }
}