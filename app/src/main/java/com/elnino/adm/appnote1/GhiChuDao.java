package com.elnino.adm.appnote1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GhiChuDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public GhiChuDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean inserGhichu(GhiChu objGhiChu) {
        ContentValues values = new ContentValues();
        values.put("title", objGhiChu.getTitle());
        values.put("content", objGhiChu.getContent());
        try {
            long result = db.insert("GhiChu", null, values);
            if (result <= 0) return false;
        } catch (Exception ex) {
            Log.e("GhiChu", ex.toString());
            return false;
        }
        return true;
    }
     public List<GhiChu> getAllGhiChu(){
        List<GhiChu> dsGhiChu = new ArrayList<>();
        Cursor cursor = db.rawQuery("select title,content from GhiChu",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            GhiChu objGhiChu = new GhiChu();
            objGhiChu.setTitle(cursor.getString(0));
            objGhiChu.setContent(cursor.getString(1));
            dsGhiChu.add(objGhiChu);
            cursor.moveToNext();
        }
        cursor.close();
        return dsGhiChu;
     }
}
