package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 8/14/2018.
 */

public class bank_det implements Parcelable {

    String id;
    String pid;
    String baccount;
    String saccount;
    String ifsc;

    protected bank_det(Parcel in) {
        id = in.readString();
        pid = in.readString();
        baccount = in.readString();
        saccount = in.readString();
        ifsc = in.readString();
    }

    public static final Creator<bank_det> CREATOR = new Creator<bank_det>() {
        @Override
        public bank_det createFromParcel(Parcel in) {
            return new bank_det(in);
        }

        @Override
        public bank_det[] newArray(int size) {
            return new bank_det[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(pid);
        dest.writeString(baccount);
        dest.writeString(saccount);
        dest.writeString(ifsc);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getBaccount() {
        return baccount;
    }

    public void setBaccount(String baccount) {
        this.baccount = baccount;
    }

    public String getSaccount() {
        return saccount;
    }

    public void setSaccount(String saccount) {
        this.saccount = saccount;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public static Creator<bank_det> getCREATOR() {
        return CREATOR;
    }

}
