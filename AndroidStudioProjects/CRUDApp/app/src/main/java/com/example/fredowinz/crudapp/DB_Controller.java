package com.example.fredowinz.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by fredowinz on 1/14/17.
 */

public class DB_Controller extends SQLiteOpenHelper{
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "text.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS( ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(sqLiteDatabase);

    }


    //Create, Read, Update, Delete

    public void insert_student(String firstname, String lastname){
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME", firstname);
        contentValues.put("LASTNAME", lastname);
        this.getWritableDatabase().insertOrThrow("STUDENTS","",contentValues);
    }

    public void delete_student(String firstname){
        this.getWritableDatabase().delete("STUDENTS","FIRSTNAME='"+firstname+"'",null);
    }

    public void update_student(String old_fname, String new_fname){
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='"+new_fname+"' WHERE FIRSTNAME='"+old_fname+"'");
    }

    public void list_all_students(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1) +" "+ cursor.getString(2) +"\n");
        }
    }

    public void list_all_students2(TextView thisList){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS",null);
        thisList.setText("");
        while (cursor.moveToNext()){
            thisList.append(cursor.getString(1) +" "+ cursor.getString(2) +"\n");
        }
    }
}

