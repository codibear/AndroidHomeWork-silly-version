package com.example.androidclass.androidclass;

/**
 * Created by 29185 on 2017/4/1.
 */

public class Account {
    private long id;
    private String name;
    private String passwd;

    public void setId(Long id){
        this.id = id;
    }
    public long getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPasswd(String passwd){
        this.passwd = passwd;
    }
    public String getPasswd(){
        return  passwd;
    }
    public Account(Long id , String name ,String passwd){
        super();
        this.id = id;
        this.name = name ;
        this.passwd = passwd;
    }
    public Account (String name , String passwd){
        super();
        this.name = name;
        this.passwd = passwd;
    }
    public Account(){
        super();
    }
    public String toString(){
        return "[序号："+id+",账号："+name+",密码："+passwd+"]";
    }
}
