package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Nk on 8/14/2018.
 */

public class ci_user implements Parcelable {


    String id;
    String firstname;
    String lastname;
    String profile_pic;
    String email;
    String mobile_no;

    protected ci_user(Parcel in) {
        id = in.readString();
        firstname = in.readString();
        lastname = in.readString();
        profile_pic = in.readString();
        email = in.readString();
        mobile_no = in.readString();
    }

    public static final Creator<ci_user> CREATOR = new Creator<ci_user>() {
        @Override
        public ci_user createFromParcel(Parcel in) {
            return new ci_user(in);
        }

        @Override
        public ci_user[] newArray(int size) {
            return new ci_user[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(profile_pic);
        dest.writeString(email);
        dest.writeString(mobile_no);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }



    public static Creator<ci_user> getCREATOR() {
        return CREATOR;
    }

}
