package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 7/23/2018.
 */

public class OfficespaceManager implements Parcelable{
    public OfficespaceManager(Parcel in) {readFromParcel(in);
    }

    public static final Creator<OfficespaceManager> CREATOR = new Creator<OfficespaceManager>() {
        @Override
        public OfficespaceManager createFromParcel(Parcel in) {
            return new OfficespaceManager(in);
        }

        @Override
        public OfficespaceManager[] newArray(int size) {
            return new OfficespaceManager[size];
        }
    };
    String id;
    String os_mng_cn;
    String os_mng_nm;
    String os_mng_num;
    String os_mng_email;

    public OfficespaceManager(String string, String string1, String string2, String string3) {
        this.os_mng_cn = string;
        this.os_mng_nm = string1;
        this.os_mng_num = string2;
        this.os_mng_email = string3;
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
        dest.writeString(os_mng_cn);
        dest.writeString(os_mng_nm);
        dest.writeString(os_mng_num);
        dest.writeString(os_mng_email);
    }
    public static Creator<OfficespaceManager> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOs_mng_cn() {
        return os_mng_cn;
    }

    public void setOs_mng_cn(String os_mng_cn) {
        this.os_mng_cn = os_mng_cn;
    }


    public String getOs_mng_nm() {
        return os_mng_nm;
    }

    public void setOs_mng_nm(String os_mng_nm) {
        this.os_mng_nm = os_mng_nm;
    }

    public String getOs_mng_num() {
        return os_mng_num;
    }

    public void setOs_mng_num(String os_mng_num) {
        this.os_mng_num = os_mng_num;
    }

    public String getOs_mng_email() {
        return os_mng_email;
    }

    public void setOs_mng_email(String os_mng_email) {
        this.os_mng_email = os_mng_email;
    }

}
