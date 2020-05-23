package com.example.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdressBookDb extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "AdressBook.db";
    public static final String TABLE_NAME = "Contacts";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Phone";
    public static final String COL_4 = "Email";
    public static final String COL_5 = "Street";
    public static final String COL_6 = "City";
    public static final String COL_7 = "State";
    public static final String COL_8 = "Zip";

    public AdressBookDb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT," +
                "Phone TEXT, Email TEXT, Street TEXT, City TEXT, State TEXT, Zip TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String phone,String email, String street, String city, String state, String zip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,phone);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, street);
        contentValues.put(COL_6, city);
        contentValues.put(COL_7, state);
        contentValues.put(COL_8, zip);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
