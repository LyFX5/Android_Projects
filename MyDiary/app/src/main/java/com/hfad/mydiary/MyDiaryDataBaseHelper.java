package com.hfad.mydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDiaryDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ElementsDB";
    private static final int DB_VERSION = 1;

    MyDiaryDataBaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ELEMENTS("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "NAME TEXT, "
        + "DESCRIPTION TEXT, "
        + "CONTENT TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void insertElement(SQLiteDatabase db, Element element){
        String name = element.getName();
        String description = element.getDiscription();
        String content = element.getContent();
        ContentValues values = new ContentValues();
        values.put("NAME",name);
        values.put("DESCRIPTION",description);
        values.put("CONTENT",content);
        db.insert("ELEMENTS",null,values);
    }

    public static void delateElement(SQLiteDatabase db, int index){
        db.delete("ELEMENTS",
                "_id = ?",
                new String[]{Integer.toString(index)});
    }
}
