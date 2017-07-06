package com.example.matt.shopping_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by matt on 7/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME="list.db";
    public final static String TABLE_NAME="list_items";
    public final static String COL_1="ID";
    public final static String COL_2="Content";
    public final static String COL_3="Completed";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
       SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Content TEXT, Completed BOOLEAN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();

        contentValue.put(COL_2, content);
        contentValue.put(COL_3, false);
        long result = db.insert(TABLE_NAME, null, contentValue);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean toggleDone(Integer id) {

        return true;
    }

    public boolean deleteItem(Integer id) {

        return true;
    }

    public boolean clearList() {
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        return result;
    }
}
