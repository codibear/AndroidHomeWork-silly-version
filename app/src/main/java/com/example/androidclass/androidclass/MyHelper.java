package com.example.androidclass.androidclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 29185 on 2017/4/1.
 */

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context){
        super(context,"itcast.db",null,2);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE account(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(50)," +
                "passwd VARCHAR(50))");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
