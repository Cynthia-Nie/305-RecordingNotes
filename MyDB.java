package com.example.recordingnotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper {
    public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  void onCreate(SQLiteDatabase db) {
        String sql = "create table user (" + "id integer primary key autoincrement, " + "username text, " + "password text)";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //onCreate(db);
    }
}
