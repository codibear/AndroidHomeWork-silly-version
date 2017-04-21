package com.example.androidclass.androidclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

/**
 * Created by 29185 on 2017/3/22.
 */

public class SignUp extends Activity {

    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        imageView = (ImageView)findViewById(R.id.signimage);

        getData();

    }
    public void getData(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String passwd = intent.getStringExtra("passwd");
        if(name.equals("zhangzhixiong")&&passwd.equals("4140206231") ){
            imageView.setImageResource(R.drawable.xiong);
        }
        if(name.equals("lishengda")&&passwd.equals("4140206239") ){
            imageView.setImageResource(R.drawable.da);
        }
        if(name.equals("zhaizexing")&&passwd.equals("4140206233") ){
        imageView.setImageResource(R.drawable.xing);
        }
    }

}
