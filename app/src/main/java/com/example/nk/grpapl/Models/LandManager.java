package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 7/21/2018.
 */

public class LandManager implements Parcelable {
    public LandManager(Parcel in) {readFromParcel(in);
    }

    public static final Creator<LandManager> CREATOR = new Creator<LandManager>() {
        @Override
        public LandManager createFromParcel(Parcel in) {
            return new LandManager(in);
        }

        @Override
        public LandManager[] newArray(int size) {
            return new LandManager[size];
        }
    };



    String id;
    String lnd_mng_cn;
    String lnd_mng_nm;
    String lnd_mng_num;
    String lnd_mng_email;

    public LandManager(String string, String string1, String string2, String string3) {
        this.lnd_mng_cn = string;
        this.lnd_mng_nm = string1;
        this.lnd_mng_num = string2;
        this.lnd_mng_email = string3;
    }


    public void readFromParcel(Parcel in)
    {
        id = in.readString();
        lnd_mng_cn = in.readString();
        lnd_mng_nm = in.readString();
        lnd_mng_num = in.readString();
        lnd_mng_email = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(lnd_mng_cn);
        dest.writeString(lnd_mng_nm);
        dest.writeString(lnd_mng_num);
        dest.writeString(lnd_mng_email);
    }
    public static Creator<LandManager> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLnd_mng_cn() {
        return lnd_mng_cn;
    }

    public void setLnd_mng_cn(String lnd_mng_cn) {
        this.lnd_mng_cn = lnd_mng_cn;
    }


    public String getLnd_mng_nm() {
        return lnd_mng_nm;
    }

    public void setLnd_mng_nm(String lnd_mng_nm) {
        this.lnd_mng_nm = lnd_mng_nm;
    }

    public String getLnd_mng_num() {
        return lnd_mng_num;
    }

    public void setLnd_mng_num(String lnd_mng_num) {
        this.lnd_mng_num = lnd_mng_num;
    }

    public String getLnd_mng_email() {
        return lnd_mng_email;
    }

    public void setLnd_mng_email(String lnd_mng_email) {
        this.lnd_mng_email = lnd_mng_email;
    }
}
