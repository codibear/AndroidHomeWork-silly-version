package com.example.androidclass.androidclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 29185 on 2017/4/1.
 */

public class AccountDuo {
    private MyHelper helper;
    public AccountDuo(Context context){
        helper = new MyHelper(context);
    }
    public void insert(Account account){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",account.getName());
        values.put("passwd",account.getPasswd());
        Long id = db.insert("account",null,values);
        account.setId(id);
        db.close();
    }

    public List<Account> queryAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("account",null,null,null,null,null,null);
        List<Account>list = new ArrayList<Account>();
        while (c.moveToNext()){
            Long id = c.getLong(c.getColumnIndex("_id"));
            String name = c.getString(1);
            String passwd = c.getString(2);
            list.add(new Account(id,name,passwd));
        }
        c.close();
        db.close();
        return list;
    }
}
