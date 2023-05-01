package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 7/30/2018.
 */

public class log_det implements Parcelable{



    String email;
    String password;
    String id;
    String username;
    String firstname;
    String lastname;
    String fileupload;
    String f_hname;
    String mname;
    String gender;
    String dob;
    String pob;
    String marital;
    String mobile_no;
    String is_admin;
    String last_ip;
    String created_at;
    String updated_at;
    String unique_id;
    String user_id;
    String add_supply;
    String list_of_all;
    String add_requirements;
    String list_requirement;
    String profile_pic;


    protected log_det(Parcel in) {
        email = in.readString();
        password = in.readString();
        id = in.readString();
        username = in.readString();
        firstname = in.readString();
        lastname = in.readString();
        fileupload = in.readString();
        f_hname = in.readString();
        mname = in.readString();
        gender = in.readString();
        dob = in.readString();
        pob = in.readString();
        marital = in.readString();
        mobile_no = in.readString();
        is_admin = in.readString();
        last_ip = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        unique_id = in.readString();
        user_id = in.readString();
        add_supply = in.readString();
        list_of_all = in.readString();
        add_requirements = in.readString();
        list_requirement = in.readString();
        profile_pic = in.readString();
    }

    public static final Creator<log_det> CREATOR = new Creator<log_det>() {
        @Override
        public log_det createFromParcel(Parcel in) {
            return new log_det(in);
        }

        @Override
        public log_det[] newArray(int size) {
            return new log_det[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(fileupload);
        dest.writeString(f_hname);
        dest.writeString(mname);
        dest.writeString(gender);
        dest.writeString(dob);
        dest.writeString(pob);
        dest.writeString(marital);
        dest.writeString(mobile_no);
        dest.writeString(is_admin);
        dest.writeString(last_ip);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        dest.writeString(unique_id);
        dest.writeString(user_id);
        dest.writeString(add_supply);
        dest.writeString(list_of_all);
        dest.writeString(add_requirements);
        dest.writeString(list_requirement);
        dest.writeString(profile_pic);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFileupload() {
        return fileupload;
    }

    public void setFileupload(String fileupload) {
        this.fileupload = fileupload;
    }

    public String getF_hname() {
        return f_hname;
    }

    public void setF_hname(String f_hname) {
        this.f_hname = f_hname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAdd_supply() {
        return add_supply;
    }

    public void setAdd_supply(String add_supply) {
        this.add_supply = add_supply;
    }

    public String getList_of_all() {
        return list_of_all;
    }

    public void setList_of_all(String list_of_all) {
        this.list_of_all = list_of_all;
    }

    public String getAdd_requirements() {
        return add_requirements;
    }

    public void setAdd_requirements(String add_requirements) {
        this.add_requirements = add_requirements;
    }

    public String getList_requirement() {
        return list_requirement;
    }

    public void setList_requirement(String list_requirement) {
        this.list_requirement = list_requirement;
    }
    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        profile_pic = profile_pic;
    }


    public static Creator<log_det> getCREATOR() {
        return CREATOR;
    }
}
