package com.example.androidclass.androidclass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 29185 on 2017/3/21.
 */

public class SecondActivity extends Activity {

    private EditText sec_name_edit;
    private EditText sec_passwd_edit;
    private TextView gander;
    private TextView favor;
    private Button back;
    //////////////////////////
    private List<Account> list;
    private AccountDuo duo;
  //  private MyAdapter adapter;
    private ListView accountLV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
        secReceiveData();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secSendData();
                restore();
                sqliteRestroe();
            }
        });
    }

    //SecondActivity从MainActivity接收的数据
    public void secReceiveData(){
        Intent intent = getIntent();
        String mainName = intent.getStringExtra("name");
        String mainPasswd = intent.getStringExtra("passwd");
        String ganders = intent.getStringExtra("gander");
        String favors = intent.getStringExtra("favor");

        sec_name_edit.setText(mainName);
        sec_passwd_edit.setText(mainPasswd);
        favor.setText(favors);
        gander.setText(ganders);
    }
    //为SecondActivity初始化布局
    public void init(){
        sec_name_edit = (EditText)findViewById(R.id.sec_name_edit);
        sec_passwd_edit = (EditText)findViewById(R.id.sec_passwd_edit);
        favor = (TextView)findViewById(R.id.favor);
        gander = (TextView)findViewById(R.id.gander);
        back = (Button)findViewById(R.id.back);

    }

    public void secSendData(){
        String changeName = sec_name_edit.getText().toString().trim();
        String changePasswd = sec_passwd_edit.getText().toString().trim();
        Intent secIntent = new Intent(SecondActivity.this,MainActivity.class);
        secIntent.putExtra("changeName",changeName);
        secIntent.putExtra("changePasswd",changePasswd);
        startActivity(secIntent);
       // setResult(RESULT_OK,secIntent);
        finish();
    }
    protected  void restore(){
        String changeName = sec_name_edit.getText().toString().trim();
        String changePasswd = sec_passwd_edit.getText().toString().trim();
        String changeGander = gander.getText().toString().trim();
        SharedPreferences preferences = getSharedPreferences("sec_restore",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("sec_name",changeName);
        editor.putString("sec_passwd",changePasswd);
        editor.putString("sec_gander",changeGander);
        editor.commit();

    }

    //注册并存储到sqlite数据库中
    public void sqliteRestroe(){
       accountLV = (ListView)findViewById(R.id.sqliteList);
        String changeName = sec_name_edit.getText().toString().trim();
        String changePasswd = sec_passwd_edit.getText().toString().trim();
        Account a =new Account(changeName,changePasswd);
        duo.insert(a);
        list.add(a);
       // adapter.notifyDataSetChanged();
        accountLV.setSelection(accountLV.getCount()-1);
    }
/*    public class MyAdapter extends BaseAdapter {
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
    }*/
}
