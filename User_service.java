package com.example.recordingnotes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class User_service {
    private MyDB dbHelper;
    private static User_service userService;
    private SQLiteDatabase db;
    public User_service(Context context) {
        dbHelper = new MyDB(context, "user.db", null, 1);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static User_service getInstance(Context context) {
        if (userService == null) {
            userService = new User_service(context);
        }
        return userService;
    }

    public int  saveUser(User user) {
        if (user != null) {
            Cursor cursor = db.rawQuery("select * from user where username=?", new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into user(username,password) values(?,?) ", new String[]{user.getUsername().toString(), user.getPassword().toString()});
                } catch (Exception e) {
                    Log.d("It is a mistake", e.getMessage().toString());
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    public List<User> loadUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int Quer(String pwd,String name)
    {
        Cursor cursor =db.rawQuery("select * from user where username=?", new String[]{name});
        if (cursor.getCount()>0)
        {
            Cursor pwdcursor =db.rawQuery("select * from user where password=? and username=?",new String[]{pwd,name});
            if (pwdcursor.getCount()>0)
            {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }
    }
}
