package com.example.androidclass.androidclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editorMain;
    private Button register;
    private Button signup;
    private EditText editName;
    private EditText editPasswd;
    private RadioButton female,male;
    private CheckBox chb1,chb2,chb3,chb4,remember;

    private List<Account> list;
    private AccountDuo duo;
    private MyAdapter adapter;
    // que Adapter
    private ListView accountLV;
    static String sqlname;
    static String sqlpasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        duo = new AccountDuo(this);
        list = duo.queryAll();
        adapter = new MyAdapter();
        accountLV.setAdapter(adapter);
        init();
        updateCheckbox();
        getData();
        isRemember();
    }
    //初始化界面
    public void init(){
        register = (Button)findViewById(R.id.regisiter);
        signup = (Button)findViewById(R.id.signup) ;
        editName = (EditText)findViewById(R.id.edit_name);
        editPasswd = (EditText)findViewById(R.id.edit_passwd);
        register.setOnClickListener(this);
        signup.setOnClickListener(this);

        female = (RadioButton)findViewById(R.id.female);
        male = (RadioButton)findViewById(R.id.male);

        chb1 = (CheckBox)findViewById(R.id.chb1) ;
        chb2 = (CheckBox)findViewById(R.id.chb2) ;
        chb3 = (CheckBox)findViewById(R.id.chb3) ;
        chb4 = (CheckBox)findViewById(R.id.chb4) ;
        remember = (CheckBox)findViewById(R.id.remember) ;

        accountLV = (ListView) findViewById(R.id.sqliteList);
        accountLV.setOnItemClickListener(new MyOnItemClickListenr());


    }

    private class MyOnItemClickListenr implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?>parent,View view,int position,long id){
            Account a = (Account)parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),a.toString(),Toast.LENGTH_LONG).show();
        }
    }
    //更新界面的记忆checkbox状态
    public void updateCheckbox(){
        SharedPreferences mainRemembersp =  getSharedPreferences("main_restore",MODE_PRIVATE);
        boolean rememberStatus = mainRemembersp.getBoolean("remember",false);
        remember.setChecked(rememberStatus);
    }
//记住“记住密码”CheckBox的状态
    public void isRemember(){
        SharedPreferences mainRemembersp =  getSharedPreferences("main_restore",MODE_PRIVATE);
        SharedPreferences.Editor editor = mainRemembersp.edit();
        if(remember.isChecked()){
            editor.putBoolean("remember",true);
            getSecRestore();
        }else {editor.clear();}
        editor.apply();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.regisiter:
                sendDataSecond();
                //requsetData();
                break;
            case R.id.signup:
                 check();
                isRemember();
                break;
            default:
                break;
        }
    }
    //判断帐号密码是否与已有的匹配并登录
    public void check(){

        String name = editName.getText().toString().trim();
        String passwd = editPasswd.getText().toString().trim();

        SharedPreferences preferences = getSharedPreferences("sec_restore",MODE_PRIVATE);
        String nameSecRestore= preferences.getString("sec_name","");
        String passwdSecRestore= preferences.getString("sec_name","");

        if(name.equals("zhangzhixiong")&&passwd.equals("4140206231") ){
            Intent intentS = new Intent(MainActivity.this,SignUp.class);
            // 直接用putExtra调试
            intentS.putExtra("name",name);
            intentS.putExtra("passwd",passwd);
            startActivity(intentS);
        }
       else if(name.equals("lishengda")&&passwd.equals("4140206239") ){
            Intent intentS2 = new Intent(MainActivity.this,SignUp.class);
            // 直接用putExtra调试
            intentS2.putExtra("name",name);
            intentS2.putExtra("passwd",passwd);
            startActivity(intentS2);
        }
        else if(name.equals("zhaizexing")&&passwd.equals("4140206233") ){
            Intent intentS3 = new Intent(MainActivity.this,SignUp.class);
            // 直接用putExtra调试
            intentS3.putExtra("name",name);
            intentS3.putExtra("passwd",passwd);
            startActivity(intentS3);
        }else if(name.equals(nameSecRestore)&&passwd.equals(passwdSecRestore) ) {
            Intent intentS4 = new Intent(MainActivity.this,SqliteLogin.class);
            // 直接用putExtra调试
            intentS4.putExtra("name", name);
            intentS4.putExtra("passwd", passwd);
            startActivity(intentS4);}
         else {
                Toast.makeText(MainActivity.this, "您的帐号密码有误\n请查正后再登录", Toast.LENGTH_SHORT).show();
            }
        }
    //向SecondActivity发送数据
    public void sendDataSecond(){
        String name = editName.getText().toString().trim();
        String passwd = editPasswd.getText().toString().trim();
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        // 直接用putExtra调试

        intent.putExtra("name",name);
        intent.putExtra("passwd",passwd);

        String gander = "";
        String favor = "";
        if(female.isChecked()){
          gander = female.getText().toString().trim();
        }else if(male.isChecked()){
            gander = male.getText().toString().trim();
        }
        intent.putExtra("gander",gander);

        if(chb1.isChecked()){
            favor = chb1.getText().toString().trim()+"\n";
        } if(chb2.isChecked()){
            favor = chb2.getText().toString().trim()+"\n" + favor;
        } if(chb3.isChecked()){
            favor = chb3.getText().toString().trim()+"\n"+ favor;
        } if(chb4.isChecked()){
            favor = chb4.getText().toString().trim()+"\n"+ favor;
        }
        intent.putExtra("favor",favor);
        startActivity(intent);
    }

//用向下一个活动传递数据方法
    public void getData(){
        Intent intent = getIntent();
        String returnName = intent.getStringExtra("changeName");
        String returnPasswd = intent.getStringExtra("changePasswd");
        editName.setText(returnName);
        editPasswd.setText(returnPasswd);
    }
    //获取注册成功界面所存储的帐号密码
    public void getSecRestore(){
        SharedPreferences preferences = getSharedPreferences("sec_restore",MODE_PRIVATE);
        String nameSecRestore= preferences.getString("sec_name","");
        String passwdSecRestore= preferences.getString("sec_name","");
        editName.setText(nameSecRestore);
        editPasswd.setText(passwdSecRestore);
    }
    public void add(View v) {

        Account a = new Account(sqlname, sqlpasswd); //此处需要特定的构造函数,woyingjing gai le
        duo.insert(a);
        list.add(a);
        adapter.notifyDataSetChanged();
        accountLV.setSelection(accountLV.getCount() - 1);
        editName.setText("");
        editPasswd.setText("");
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

}
