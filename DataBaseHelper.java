package com.s22010176.finderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "Hotels.db";
    public static final String TABLE_NAME= "hotels_table";
    public static final String COL_1= "ID";
    public static final String COL_2= "HOTEL_NAME";
    public static final String COL_3= "ADDRESS";
    public static final String COL_4= "PHONE_NUMBER";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ " HOTEL_NAME TEXT, ADDRESS TEXT, PHONE_NUMBER TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String hotelName, String address, String phoneNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,hotelName);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,phoneNumber);
        long results = db.insert(TABLE_NAME,null,contentValues);
        if (results == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor result= db.rawQuery(" Select * from "+ TABLE_NAME,null);
        return result;
    }

    public boolean updateData(String id,String hotelName, String address, String phoneNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,hotelName);
        contentValues.put(COL_3,address);
        contentValues.put(COL_4,phoneNumber);
        db.update(TABLE_NAME,contentValues,"id=?", new String[] {id});
        return true;
    }

    public  Integer deleteData(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID=?",new String[] {id});
    }
}
