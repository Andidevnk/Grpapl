package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 7/21/2018.
 */

public class LandBroker implements Parcelable {
    public LandBroker(Parcel in) {
    }

    public static final Creator<LandBroker> CREATOR = new Creator<LandBroker>() {
        @Override
        public LandBroker createFromParcel(Parcel in) {
            return new LandBroker(in);
        }

        @Override
        public LandBroker[] newArray(int size) {
            return new LandBroker[size];
        }
    };


    String id;
    String lnd_brok_cn;
    String lnd_brok_nm;
    String lnd_brok_num;
    String lnd_brok_email;
    String lnd_brok_fees;
    String lnd_brok_aagfees;

    public LandBroker(String string, String string1, String string2, String string3, String string4, String string5) {

        this.lnd_brok_cn = string;
        this.lnd_brok_nm =string1;
        this.lnd_brok_num =string2;
        this.lnd_brok_email =string3;
        this.lnd_brok_fees =string4;
        this.lnd_brok_aagfees =string5;
    }


    public void readFromParcel(Parcel in) {
      id = in.readString();
      lnd_brok_cn = in.readString();
      lnd_brok_nm =in.readString();
      lnd_brok_num = in.readString();
      lnd_brok_email = in.readString();
      lnd_brok_fees = in.readString();
      lnd_brok_aagfees = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeString(id);
        dest.writeString(lnd_brok_cn);
        dest.writeString(lnd_brok_nm);
        dest.writeString(lnd_brok_num);
        dest.writeString(lnd_brok_email);
        dest.writeString(lnd_brok_fees);
        dest.writeString(lnd_brok_aagfees);
    }
    public static Creator<LandBroker> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLnd_brok_cn() {
        return lnd_brok_cn;
    }

    public void setLnd_brok_cn(String lnd_brok_cn) {
        this.lnd_brok_cn = lnd_brok_cn;
    }

    public String getLnd_brok_nm() {
        return lnd_brok_nm;
    }

    public void setLnd_brok_nm(String lnd_brok_nm) {
        this.lnd_brok_nm = lnd_brok_nm;
    }

    public String getLnd_brok_num() {
        return lnd_brok_num;
    }

    public void setLnd_brok_num(String lnd_brok_num) {
        this.lnd_brok_num = lnd_brok_num;
    }

    public String getLnd_brok_email() {
        return lnd_brok_email;
    }

    public void setLnd_brok_email(String lnd_brok_email) {
        this.lnd_brok_email = lnd_brok_email;
    }

    public String getLnd_brok_fees() {
        return lnd_brok_fees;
    }

    public void setLnd_brok_fees(String lnd_brok_fees) {
        this.lnd_brok_fees = lnd_brok_fees;
    }

    public String getLnd_brok_aagfees() {
        return lnd_brok_aagfees;
    }

    public void setLnd_brok_aagfees(String lnd_brok_aagfees) {
        this.lnd_brok_aagfees = lnd_brok_aagfees;
    }

}
