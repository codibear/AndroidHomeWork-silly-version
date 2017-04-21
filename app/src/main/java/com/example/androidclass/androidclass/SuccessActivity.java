package com.example.androidclass.androidclass;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by 29185 on 2017/4/1.
 */

public class SuccessActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Toast.makeText(SuccessActivity.this,"登录成功",Toast.LENGTH_LONG).show();
    }
}
