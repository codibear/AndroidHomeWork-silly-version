package com.example.androidclass.androidclass;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by 29185 on 2017/3/21.
 */

public class SecondActivity extends Activity {

    private EditText sec_name_edit;
    private EditText sec_passwd_edit;
    private TextView gander;
    private TextView favor;
    private Button back;
    private SharedPreferences pref;

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
    public void restore(){
        String restoreAccount = sec_name_edit.getText().toString().trim();
        String restorePasswd = sec_passwd_edit.getText().toString().trim();
        String restoreGander = gander.getText().toString().trim();
        pref = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("account",restoreAccount);
        editor.putString("passwd",restorePasswd);
        editor.putString("gander",restoreGander);
        editor.commit();
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
        String changeGander = gander.getText().toString().trim();
        Intent secIntent = new Intent(SecondActivity.this,MainActivity.class);
        secIntent.putExtra("changeName",changeName);
        secIntent.putExtra("changePasswd",changePasswd);
        secIntent.putExtra("changeGander",changeGander);
        startActivity(secIntent);
        finish();
    }

}
