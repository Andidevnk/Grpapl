package com.example.nk.grpapl.Models;

import java.util.ArrayList;

/**
 * Created by user on 06-09-2016.
 */
public class BaseEntity
{
    String status;
    String code;
    String id;
    String msg;
    String pid;

    String image;
    String name;

    String guide_number_of_duties;

    String support_num;
    String support_email;

    public ArrayList<log_det> log_det;
    public  ArrayList<States> states;
    public  ArrayList<Cities> cities;
    public  ArrayList<ci_user> ci_user;
    public  ArrayList<bank_det> bank_det;
    public  ArrayList<addressss> addressss;



    public String getStatus(){return status;}
    public void setStatus(){this.status =status;}

    public String getCode(){return code;}
    public void setCode(){this.code =code;}

    public String getId(){return id;}
    public void setId(){this.id =id;}

    public String getMsg(){return msg;}
    public void setMsg(){this.msg =msg;}

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}
