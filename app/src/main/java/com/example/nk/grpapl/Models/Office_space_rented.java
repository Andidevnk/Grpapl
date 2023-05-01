package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 8/10/2018.
 */

public class Office_space_rented implements Parcelable {
    public Office_space_rented(Parcel in) {readFromParcel(in);
    }

    public static final Creator<Office_space_rented> CREATOR = new Creator<Office_space_rented>() {
        @Override
        public Office_space_rented createFromParcel(Parcel in) {
            return new Office_space_rented(in);
        }

        @Override
        public Office_space_rented[] newArray(int size) {
            return new Office_space_rented[size];
        }
    };

    String id;
    String os_rented_com_nm;
    String os_rented_con_per_nm;
    String os_rented_desig;
    String os_rented_con_num;
    String os_rented_em;
    String os_rented_in_expiry;
    String os_rented_lease_expiry;
    String os_rented_rent;
    String os_rented_rent_esc;
    String os_rented_esc_period;
    String os_rented_by_grpapl;

    public Office_space_rented(String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10) {
        this.os_rented_com_nm = string;
        this.os_rented_con_per_nm = string1;
        this.os_rented_desig = string2;
        this.os_rented_con_num =string3;
        this.os_rented_em =string4;
        this.os_rented_in_expiry =string5;
        this.os_rented_lease_expiry =string6;
        this.os_rented_rent =string7;
        this.os_rented_rent_esc =string8;
        this.os_rented_esc_period =string9;
        this.os_rented_by_grpapl =string10;
    }


    public void readFromParcel(Parcel in)
    {
        id = in.readString();
        os_rented_com_nm = in.readString();
        os_rented_con_per_nm = in.readString();
        os_rented_desig = in.readString();
        os_rented_con_num = in.readString();
        os_rented_em = in.readString();
        os_rented_in_expiry = in.readString();
        os_rented_lease_expiry = in.readString();
        os_rented_rent = in.readString();
        os_rented_rent_esc = in.readString();
        os_rented_esc_period = in.readString();
        os_rented_by_grpapl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(os_rented_com_nm);
        dest.writeString(os_rented_con_per_nm);
        dest.writeString(os_rented_desig);
        dest.writeString(os_rented_con_num);
        dest.writeString(os_rented_em);
        dest.writeString(os_rented_in_expiry);
        dest.writeString(os_rented_lease_expiry);
        dest.writeString(os_rented_rent);
        dest.writeString(os_rented_rent_esc);
        dest.writeString(os_rented_esc_period);
        dest.writeString(os_rented_by_grpapl);
    }

    public static Creator<Office_space_rented> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOs_rented_com_nm() {
        return os_rented_com_nm;
    }

    public void setOs_rented_com_nm(String os_rented_com_nm) {
        this.os_rented_com_nm = os_rented_com_nm;
    }

    public String getOs_rented_con_per_nm() {
        return os_rented_con_per_nm;
    }

    public void setOs_rented_con_per_nm(String os_rented_con_per_nm) {
        this.os_rented_con_per_nm = os_rented_con_per_nm;
    }

    public String getOs_rented_desig() {
        return os_rented_desig;
    }

    public void setOs_rented_desig(String os_rented_desig) {
        this.os_rented_desig = os_rented_desig;
    }

    public String getOs_rented_con_num() {
        return os_rented_con_num;
    }

    public void setOs_rented_con_num(String os_rented_con_num) {
        this.os_rented_con_num = os_rented_con_num;
    }

    public String getOs_rented_em() {
        return os_rented_em;
    }

    public void setOs_rented_em(String os_rented_em) {
        this.os_rented_em = os_rented_em;
    }

    public String getOs_rented_in_expiry() {
        return os_rented_in_expiry;
    }

    public void setOs_rented_in_expiry(String os_rented_in_expiry) {
        this.os_rented_in_expiry = os_rented_in_expiry;
    }

    public String getOs_rented_lease_expiry() {
        return os_rented_lease_expiry;
    }

    public void setOs_rented_lease_expiry(String os_rented_lease_expiry) {
        this.os_rented_lease_expiry = os_rented_lease_expiry;
    }

    public String getOs_rented_rent() {
        return os_rented_rent;
    }

    public void setOs_rented_rent(String os_rented_rent) {
        this.os_rented_rent = os_rented_rent;
    }

    public String getOs_rented_rent_esc() {
        return os_rented_rent_esc;
    }

    public void setOs_rented_rent_esc(String os_rented_rent_esc) {
        this.os_rented_rent_esc = os_rented_rent_esc;
    }

    public String getOs_rented_esc_period() {
        return os_rented_esc_period;
    }

    public void setOs_rented_esc_period(String os_rented_esc_period) {
        this.os_rented_esc_period = os_rented_esc_period;
    }

    public String getOs_rented_by_grpapl() {
        return os_rented_by_grpapl;
    }

    public void setOs_rented_by_grpapl(String os_rented_by_grpapl) {
        this.os_rented_by_grpapl = os_rented_by_grpapl;
    }



}
