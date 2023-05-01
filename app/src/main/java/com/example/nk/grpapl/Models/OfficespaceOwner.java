package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 7/23/2018.
 */

public class OfficespaceOwner implements Parcelable {
    public OfficespaceOwner(Parcel in) {readFromParcel(in);
    }

    public static final Creator<OfficespaceOwner> CREATOR = new Creator<OfficespaceOwner>() {
        @Override
        public OfficespaceOwner createFromParcel(Parcel in) {
            return new OfficespaceOwner(in);
        }

        @Override
        public OfficespaceOwner[] newArray(int size) {
            return new OfficespaceOwner[size];
        }
    };

    String id;
    String os_own_comp_name;
    String os_own_cont_nm;
    String os_owner_num;
    String os_own_email;

    public OfficespaceOwner(String cursorString, String string, String string1, String string2) {
       this.os_own_comp_name = cursorString;
       this.os_own_cont_nm = string;
       this.os_owner_num = string1;
       this.os_own_email = string2;
    }

    public void readFromParcel(Parcel in) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(os_own_comp_name);
        dest.writeString(os_own_cont_nm);
        dest.writeString(os_owner_num);
        dest.writeString(os_own_email);
    }
    public static Creator<OfficespaceOwner> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOs_own_comp_name() {
        return os_own_comp_name;
    }

    public void setOs_own_comp_name(String os_own_comp_name) {
        this.os_own_comp_name = os_own_comp_name;
    }

    public String getOs_own_cont_nm() {
        return os_own_cont_nm;
    }

    public void setOs_own_cont_nm(String os_own_cont_nm) {
        this.os_own_cont_nm = os_own_cont_nm;
    }

    public String getOs_owner_num() {
        return os_owner_num;
    }

    public void setOs_owner_num(String os_owner_num) {
        this.os_owner_num = os_owner_num;
    }

    public String getOs_own_email() {
        return os_own_email;
    }

    public void setOs_own_email(String os_own_email) {
        this.os_own_email = os_own_email;
    }

}
