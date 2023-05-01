package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 8/9/2018.
 */

public class Office_space_part_available implements Parcelable {
    public Office_space_part_available(Parcel in) {readFromParcel(in);
    }

    public static final Creator<Office_space_part_available> CREATOR = new Creator<Office_space_part_available>() {
        @Override
        public Office_space_part_available createFromParcel(Parcel in) {
            return new Office_space_part_available(in);
        }

        @Override
        public Office_space_part_available[] newArray(int size) {
            return new Office_space_part_available[size];
        }
    };


    String id;
    String os_part_ava_space_ava;
    String os_part_ava_com_nm;
    String os_part_ava_con_per_nm;
    String os_part_ava_desig;
    String os_part_ava_con_num;
    String os_part_ava_em;
    String os_part_ava_area_rentad;
    String os_part_ava_floor;
    String os_part_ava_spac_ava;
    String os_part_ava_loc_in_expiry;
    String os_part_ava_lease_expiry;
    String os_part_ava_rent;
    String os_part_ava_rent_esc;
    String os_part_ava_rent_esc_period;
    String os_part_ava_rented_by_grpapl;

    public Office_space_part_available(String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13, String string14) {
        this.os_part_ava_space_ava = string;
        this.os_part_ava_com_nm = string1;
        this.os_part_ava_con_per_nm =string2;
        this.os_part_ava_desig = string3;
        this.os_part_ava_con_num =string4;
        this.os_part_ava_em =string5;
        this.os_part_ava_area_rentad =string6;
        this.os_part_ava_floor =string7;
        this.os_part_ava_spac_ava =string8;
        this.os_part_ava_loc_in_expiry =string9;
        this.os_part_ava_lease_expiry =string10;
        this.os_part_ava_rent =string11;
        this.os_part_ava_rent_esc =string12;
        this.os_part_ava_rent_esc_period =string13;
        this.os_part_ava_rented_by_grpapl =string14;
    }


    public void readFromParcel(Parcel in)
    {
        id = in.readString();
        os_part_ava_space_ava = in.readString();
        os_part_ava_com_nm = in.readString();
        os_part_ava_con_per_nm = in.readString();
        os_part_ava_desig = in.readString();
        os_part_ava_con_num = in.readString();
        os_part_ava_em = in.readString();
        os_part_ava_area_rentad = in.readString();
        os_part_ava_floor = in.readString();
        os_part_ava_spac_ava = in.readString();
        os_part_ava_loc_in_expiry = in.readString();
        os_part_ava_lease_expiry = in.readString();
        os_part_ava_rent = in.readString();
        os_part_ava_rent_esc = in.readString();
        os_part_ava_rent_esc_period = in.readString();
        os_part_ava_rented_by_grpapl = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(os_part_ava_space_ava);
        dest.writeString(os_part_ava_com_nm);
        dest.writeString(os_part_ava_con_per_nm);
        dest.writeString(os_part_ava_desig);
        dest.writeString(os_part_ava_con_num);
        dest.writeString(os_part_ava_em);
        dest.writeString(os_part_ava_area_rentad);
        dest.writeString(os_part_ava_floor);
        dest.writeString(os_part_ava_spac_ava);
        dest.writeString(os_part_ava_loc_in_expiry);
        dest.writeString(os_part_ava_lease_expiry);
        dest.writeString(os_part_ava_rent);
        dest.writeString(os_part_ava_rent_esc);
        dest.writeString(os_part_ava_rent_esc_period);
        dest.writeString(os_part_ava_rented_by_grpapl);
    }

    public static Creator<Office_space_part_available> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOs_part_ava_space_ava() {
        return os_part_ava_space_ava;
    }

    public void setOs_part_ava_space_ava(String os_part_ava_space_ava) {
        this.os_part_ava_space_ava = os_part_ava_space_ava;
    }

    public String getOs_part_ava_com_nm() {
        return os_part_ava_com_nm;
    }

    public void setOs_part_ava_com_nm(String os_part_ava_com_nm) {
        this.os_part_ava_com_nm = os_part_ava_com_nm;
    }

    public String getOs_part_ava_con_per_nm() {
        return os_part_ava_con_per_nm;
    }

    public void setOs_part_ava_con_per_nm(String os_part_ava_con_per_nm) {
        this.os_part_ava_con_per_nm = os_part_ava_con_per_nm;
    }

    public String getOs_part_ava_desig() {
        return os_part_ava_desig;
    }

    public void setOs_part_ava_desig(String os_part_ava_desig) {
        this.os_part_ava_desig = os_part_ava_desig;
    }

    public String getOs_part_ava_con_num() {
        return os_part_ava_con_num;
    }

    public void setOs_part_ava_con_num(String os_part_ava_con_num) {
        this.os_part_ava_con_num = os_part_ava_con_num;
    }

    public String getOs_part_ava_em() {
        return os_part_ava_em;
    }

    public void setOs_part_ava_em(String os_part_ava_em) {
        this.os_part_ava_em = os_part_ava_em;
    }

    public String getOs_part_ava_area_rentad() {
        return os_part_ava_area_rentad;
    }

    public void setOs_part_ava_area_rentad(String os_part_ava_area_rentad) {
        this.os_part_ava_area_rentad = os_part_ava_area_rentad;
    }

    public String getOs_part_ava_floor() {
        return os_part_ava_floor;
    }

    public void setOs_part_ava_floor(String os_part_ava_floor) {
        this.os_part_ava_floor = os_part_ava_floor;
    }

    public String getOs_part_ava_spac_ava() {
        return os_part_ava_spac_ava;
    }

    public void setOs_part_ava_spac_ava(String os_part_ava_spac_ava) {
        this.os_part_ava_spac_ava = os_part_ava_spac_ava;
    }

    public String getOs_part_ava_loc_in_expiry() {
        return os_part_ava_loc_in_expiry;
    }

    public void setOs_part_ava_loc_in_expiry(String os_part_ava_loc_in_expiry) {
        this.os_part_ava_loc_in_expiry = os_part_ava_loc_in_expiry;
    }

    public String getOs_part_ava_lease_expiry() {
        return os_part_ava_lease_expiry;
    }

    public void setOs_part_ava_lease_expiry(String os_part_ava_lease_expiry) {
        this.os_part_ava_lease_expiry = os_part_ava_lease_expiry;
    }

    public String getOs_part_ava_rent() {
        return os_part_ava_rent;
    }

    public void setOs_part_ava_rent(String os_part_ava_rent) {
        this.os_part_ava_rent = os_part_ava_rent;
    }

    public String getOs_part_ava_rent_esc() {
        return os_part_ava_rent_esc;
    }

    public void setOs_part_ava_rent_esc(String os_part_ava_rent_esc) {
        this.os_part_ava_rent_esc = os_part_ava_rent_esc;
    }

    public String getOs_part_ava_rent_esc_period() {
        return os_part_ava_rent_esc_period;
    }

    public void setOs_part_ava_rent_esc_period(String os_part_ava_rent_esc_period) {
        this.os_part_ava_rent_esc_period = os_part_ava_rent_esc_period;
    }

    public String getOs_part_ava_rented_by_grpapl() {
        return os_part_ava_rented_by_grpapl;
    }

    public void setOs_part_ava_rented_by_grpapl(String os_part_ava_rented_by_grpapl) {
        this.os_part_ava_rented_by_grpapl = os_part_ava_rented_by_grpapl;
    }

}
