package com.example.courswork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "Calmness.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDb){
        MyDb.execSQL("create Table users(email TEXT primary key, password TEXT, name TEXT)");

    }

    public void onUpgrade(SQLiteDatabase MyDb, int oldVersion, int newVersion){
        MyDb.execSQL("drop table if exists users");

        onCreate(MyDb);

    }

    public Boolean insertData(String email, String name, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("password", password);
        Long results = MyDb.insert("users", null, contentValues);
        if(results.equals(-1))
            return false;
        else
            return true;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where email = ?", new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("Select * from users where email = ? and password = ?", new String[] {email, password});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }


}
