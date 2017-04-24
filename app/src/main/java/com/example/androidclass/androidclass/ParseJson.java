package com.example.androidclass.androidclass;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by 29185 on 2017/4/21.
 */
//解析服务器返回的json数据
public class ParseJson {
    static String completedString;
    public String parseJSONWithGet(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String passwd = jsonObject.getString("passwd");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(id);
            stringBuilder.append(name);
            stringBuilder.append(passwd);
            completedString = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return completedString;
    }
    private void parseJSONWithPost(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String user = jsonObject.getString("last_seen");
            String study = jsonObject.getString("studys");
            String usersid=jsonObject.getString("username");
            Log.e("Main", "user is :"+user );
            Log.e("Main", "stud"+ study );
            Log.e("Main", "userid "+usersid );

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void parseJSONWithPUT(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            String message = jsonObject.getString("message");
            Log.e("Main", "msg is :"+message );

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
