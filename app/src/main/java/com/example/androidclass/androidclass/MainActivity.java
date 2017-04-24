package com.example.androidclass.androidclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RequestForHttp requestForHttp;
    private ParseJson parseJson;
    private Button register;
    private Button signup;
    private EditText editName;
    private EditText editPasswd;
    private RadioButton female,male;
    private CheckBox chb1,chb2,chb3,chb4,chbsp;
    private SharedPreferences pref1;
    private SharedPreferences.Editor editor1;
    public  final String url = "http://127.0.0.1:8080/accountandpasswd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
        chbsp = (CheckBox)findViewById(R.id.chbsp);

        requestForHttp = new RequestForHttp();

        parseJson = new ParseJson();

        pref1 = getSharedPreferences("remember_passwd",MODE_PRIVATE);

        boolean isRemember = pref1.getBoolean("remember",false);
        chbsp.setChecked(isRemember);
       // editor1 = pref1.edit();
        /*if(isRemember){
            String account = pref1.getString("account","");
            String passwd = pref1.getString("passwd","");
            editName.setText(account);
            editPasswd.setText(passwd);

        }*/


           /* editor1.putString("account",name);
            editor1.putString("passwd",passwd);*/
        //editor1.putBoolean("remember",true);
new Thread(new Runnable() {
    @Override
    public void run() {

        String gainName =pref1.getString("account","");
        String gainPasswd =pref1.getString("passwd","");
        editName.setText(gainName);
        editPasswd.setText(gainPasswd);

    }
}).start();



        /*String name = editName.getText().toString().trim();
        String passwd = editPasswd.getText().toString().trim();
        editor1 = pref1.edit();
        if(chbsp.isChecked()){
            editor1.putBoolean("remember",true);
            editor1.putString("account",name);
            editor1.putString("passwd",passwd);
        }else{editor1.clear();}
        editor1.apply();
*/

        getData();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.regisiter:
                sendDataSecond();
                break;
            case R.id.signup:
                 check();
                break;
            default:
                break;
        }
    }
    //check passwd & name
    public void check(){

        String name = editName.getText().toString().trim();
        String passwd = editPasswd.getText().toString().trim();
        //想服务器上传送帐号密码，用来验证
        String data=requestForHttp.getData(url,name,passwd);
        String jsonData = parseJson.parseJSONWithGet(data);
        // 获取存储的账号密码信息
        editor1 = pref1.edit();
        if(chbsp.isChecked()){
            editor1.putBoolean("remember",true);
            editor1.putString("account",name);
            editor1.putString("passwd",passwd);
        }else{editor1.clear(); editor1.putBoolean("remember",false);}
        editor1.apply();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String spName = sp.getString("account",null);
        String spPasswd = sp.getString("passwd",null);
        String spGander = sp.getString("gander",null);


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
        }else if(name.equals(spName)&&passwd.equals(spPasswd)){

            Intent intentS4 = new Intent(MainActivity.this,SuccessActivity.class);
            // 直接用putExtra调试
            startActivity(intentS4);
        }else if(jsonData != null && jsonData.length()!=0){
            Intent intentS4 = new Intent(MainActivity.this,SuccessActivity.class);
            // 直接用putExtra调试
            startActivity(intentS4);
        }
        else {
            Toast.makeText(MainActivity.this,"登录失败!",Toast.LENGTH_SHORT).show();
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

}
