package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 8/8/2018.
 */

public class Landpartavailable  implements Parcelable {
    public Landpartavailable(Parcel in) {readFromParcel(in);



    }


    public static final Creator<Landpartavailable> CREATOR = new Creator<Landpartavailable>() {
        @Override
        public Landpartavailable createFromParcel(Parcel in) {
            return new Landpartavailable(in);
        }

        @Override
        public Landpartavailable[] newArray(int size) {
            return new Landpartavailable[size];
        }
    };

    String id;
    String lnd_part_ava_cn;
    String lnd_part_ava_cpn;
    String lnd_part_ava_num;
    String lnd_part_ava_email;
    String lnd_part_ava_area_sold;
    String lnd_part_ava_salesprice;
    String lnd_part_ava_sold_by_grpapl;

    public Landpartavailable(String string, String string1, String string2, String string3, String string4, String string5, String string6) {
        this.lnd_part_ava_cn = string;
        this.lnd_part_ava_cpn = string1;
        this.lnd_part_ava_num = string2;
        this.lnd_part_ava_email = string3;
        this.lnd_part_ava_area_sold = string4;
        this.lnd_part_ava_salesprice = string5;
        this.lnd_part_ava_sold_by_grpapl = string6;
    }

    public void readFromParcel(Parcel in)
    {
        id = in.readString();
        lnd_part_ava_cn = in.readString();
        lnd_part_ava_cpn = in.readString();
        lnd_part_ava_num = in.readString();
        lnd_part_ava_email = in.readString();
        lnd_part_ava_area_sold = in.readString();
        lnd_part_ava_salesprice = in.readString();
        lnd_part_ava_sold_by_grpapl = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(lnd_part_ava_cn);
        dest.writeString(lnd_part_ava_cpn);
        dest.writeString(lnd_part_ava_num);
        dest.writeString(lnd_part_ava_email);
        dest.writeString(lnd_part_ava_area_sold);
        dest.writeString(lnd_part_ava_salesprice);
        dest.writeString(lnd_part_ava_sold_by_grpapl);
    }

    public static Creator<Landpartavailable> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLnd_part_ava_cn() {
        return lnd_part_ava_cn;
    }

    public void setLnd_part_ava_cn(String lnd_part_ava_cn) {
        this.lnd_part_ava_cn = lnd_part_ava_cn;
    }

    public String getLnd_part_ava_cpn() {
        return lnd_part_ava_cpn;
    }

    public void setLnd_part_ava_cpn(String lnd_part_ava_cpn) {
        this.lnd_part_ava_cpn = lnd_part_ava_cpn;
    }

    public String getLnd_part_ava_num() {
        return lnd_part_ava_num;
    }

    public void setLnd_part_ava_num(String lnd_part_ava_num) {
        this.lnd_part_ava_num = lnd_part_ava_num;
    }

    public String getLnd_part_ava_email() {
        return lnd_part_ava_email;
    }

    public void setLnd_part_ava_email(String lnd_part_ava_email) {
        this.lnd_part_ava_email = lnd_part_ava_email;
    }

    public String getLnd_part_ava_area_sold() {
        return lnd_part_ava_area_sold;
    }

    public void setLnd_part_ava_area_sold(String lnd_part_ava_area_sold) {
        this.lnd_part_ava_area_sold = lnd_part_ava_area_sold;
    }

    public String getLnd_part_ava_salesprice() {
        return lnd_part_ava_salesprice;
    }

    public void setLnd_part_ava_salesprice(String lnd_part_ava_salesprice) {
        this.lnd_part_ava_salesprice = lnd_part_ava_salesprice;
    }

    public String getLnd_part_ava_sold_by_grpapl() {
        return lnd_part_ava_sold_by_grpapl;
    }

    public void setLnd_part_ava_sold_by_grpapl(String lnd_part_ava_sold_by_grpapl) {
        this.lnd_part_ava_sold_by_grpapl = lnd_part_ava_sold_by_grpapl;
    }

}
