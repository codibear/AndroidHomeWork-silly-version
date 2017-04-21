package com.example.androidclass.androidclass;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 29185 on 2017/4/1.
 */

public class SqliteLogin extends Activity {
    private List<Account> list;
    private AccountDuo duo;
    private EditText editName;
    private EditText editPasswd;
    private MyAdapter adapter;
    // que Adapter
    private ListView accountLV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        initView();
        duo = new AccountDuo(this);
        list = duo.queryAll();
        adapter = new MyAdapter();
        accountLV.setAdapter(adapter);
    }

    private void initView() {
        editName = (EditText) findViewById(R.id.edit_name);
        editPasswd = (EditText) findViewById(R.id.edit_passwd);
        accountLV = (ListView) findViewById(R.id.sqliteList);
        accountLV.setOnItemClickListener(new MyOnItemClickListenr());
    }




    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView != null ? convertView : View.inflate(getApplicationContext(), R.layout.activity_item, null);
            TextView idTV = (TextView)findViewById(R.id.itemId);
            TextView nameTV = (TextView)findViewById(R.id.itemName);
            TextView passwdTV = (TextView)findViewById(R.id.itemPasswd);
            final Account a =list.get(position);
            idTV.setText(a.getId()+"");
            nameTV.setText(a.getName()+"");
            passwdTV.setText(a.getPasswd()+"");
            return item;
        }
    }
    private class MyOnItemClickListenr implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?>parent,View view,int position,long id){
            Account a = (Account)parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),a.toString(),Toast.LENGTH_LONG).show();
        }
    }
}

