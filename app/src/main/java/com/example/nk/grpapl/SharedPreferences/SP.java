package com.example.nk.grpapl.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 13-07-2016.
 */
public class SP
{
    public static SP instance;

    static String packagename= "com.example.nk.grpal";

    String property_type;
    String username;
    String userid;
    String password;
    String prof_img;
    String firstname;
    String lastname;


    private SP(){

    }

    public static SP getInstance()
    {
        if(instance==null)
        {
            instance=new SP();
        }
        return instance;
    }

    public String getUsername(Context context) {
        SharedPreferences prfs = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        return prfs.getString("username","");
    }
    public void setUsername(String username,Context context) {
        this.username = username;
        SharedPreferences prfs = context.getSharedPreferences(packagename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString("username",username);
        editor.commit();
    }

    public String getUserid(Context context){
        SharedPreferences prfs = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        return prfs.getString("userid","");
    }
    public void setUserid(String userid, Context context){
        this.userid = userid;
        SharedPreferences prfs = context.getSharedPreferences(packagename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString("userid",userid);
        editor.commit();
    }
    public String getPassword(Context context) {
        SharedPreferences prfs = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        return prfs.getString("password","");
    }
    public void setPassword(String password,Context context) {
        this.password = password;
        SharedPreferences prfs = context.getSharedPreferences(packagename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString("password",password);
        editor.commit();
    }

    public String getProperty_type(Context context) {
        SharedPreferences prfs = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        return prfs.getString("property_type","");
    }
    public void setProperty_type(String property_type,Context context) {
        this.property_type = property_type;
        SharedPreferences prfs = context.getSharedPreferences(packagename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString("property_type",property_type);
        editor.commit();
    }

    public String getProf_img(Context context) {
        SharedPreferences prfs = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        return prfs.getString("prof_img","");
    }
    public void setProf_img(String prof_img,Context context) {
        this.prof_img = prof_img;
        SharedPreferences prfs = context.getSharedPreferences(packagename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString("prof_img",prof_img);
        editor.commit();
    }

    public String getFirstname(Context context) {
        SharedPreferences prfs = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        return prfs.getString("first_name","");
    }
    public void setFirstname(String firstname,Context context) {
        this.firstname = firstname;
        SharedPreferences prfs = context.getSharedPreferences(packagename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString("first_name",firstname);
        editor.commit();
    }

    public String getLastname(Context context) {
        SharedPreferences prfs = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        return prfs.getString("last_name","");
    }
    public void setLastname(String lastname,Context context) {
        this.lastname = lastname;
        SharedPreferences prfs = context.getSharedPreferences(packagename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prfs.edit();
        editor.putString("last_name",lastname);
        editor.commit();
    }



}
