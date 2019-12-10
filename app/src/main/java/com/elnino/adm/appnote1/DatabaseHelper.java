package com.elnino.adm.appnote1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "dbGhiChu", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableGhiChu = "create table GhiChu(title text primary key, content text)";
        sqLiteDatabase.execSQL(createTableGhiChu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists GhiChu");
        onCreate(sqLiteDatabase);
    }

}
