package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 7/23/2018.
 */

public class OfficespaceBroker implements Parcelable {
    public OfficespaceBroker(Parcel in) {readFromParcel(in);
    }

    public static final Creator<OfficespaceBroker> CREATOR = new Creator<OfficespaceBroker>() {
        @Override
        public OfficespaceBroker createFromParcel(Parcel in) {
            return new OfficespaceBroker(in);
        }

        @Override
        public OfficespaceBroker[] newArray(int size) {
            return new OfficespaceBroker[size];
        }
    };
    String id;
    String os_brok_cn;
    String os_brok_nm;
    String os_brok_num;
    String os_brok_email;
    String os_brrrrok_fees;
    String os_brok_aagfees;

    public OfficespaceBroker(String string, String string1, String string2, String string3, String string4, String string5) {

        this.os_brok_cn =string;
        this.os_brok_nm =string1;
        this.os_brok_num =string2;
        this.os_brok_email =string3;
        this.os_brrrrok_fees =string4;
        this.os_brok_aagfees =string5;
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
        dest.writeString(os_brok_cn);
        dest.writeString(os_brok_nm);
        dest.writeString(os_brok_num);
        dest.writeString(os_brok_email);
        dest.writeString(os_brrrrok_fees);
        dest.writeString(os_brok_aagfees);
    }

    public static Creator<OfficespaceBroker> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getOs_brok_cn() {
        return os_brok_cn;
    }

    public void setOs_brok_cn(String os_brok_cn) {
        this.os_brok_cn = os_brok_cn;
    }

    public String getOs_brok_nm() {
        return os_brok_nm;
    }

    public void setOs_brok_nm(String os_brok_nm) {
        this.os_brok_nm = os_brok_nm;
    }

    public String getOs_brok_num() {
        return os_brok_num;
    }

    public void setOs_brok_num(String os_brok_num) {
        this.os_brok_num = os_brok_num;
    }

    public String getOs_brok_email() {
        return os_brok_email;
    }

    public void setOs_brok_email(String os_brok_email) {
        this.os_brok_email = os_brok_email;
    }
    public String getOs_brrrrok_fees() {
        return os_brrrrok_fees;
    }

    public void setOs_brrrrok_fees(String os_brrrrok_fees) {
        this.os_brrrrok_fees = os_brrrrok_fees;
    }


    public String getOs_brok_aagfees() {
        return os_brok_aagfees;
    }

    public void setOs_brok_aagfees(String os_brok_aagfees) {
        this.os_brok_aagfees = os_brok_aagfees;
    }

}
