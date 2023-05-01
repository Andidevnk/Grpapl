package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 8/8/2018.
 */

public class Property_specification_verified implements Parcelable {
    public Property_specification_verified(Parcel in) {readFromParcel(in);
    }

    public static final Creator<Property_specification_verified> CREATOR = new Creator<Property_specification_verified>() {
        @Override
        public Property_specification_verified createFromParcel(Parcel in) {
            return new Property_specification_verified(in);
        }

        @Override
        public Property_specification_verified[] newArray(int size) {
            return new Property_specification_verified[size];
        }
    };

    String id;
    String property_verfied_nm;
    String property_verfied_desg;
    String property_verfied_em;
    String property_verfied_mnum;
    String property_fix_lnum;

    public Property_specification_verified(String string, String string1, String string2, String string3, String string4) {

        this.property_verfied_nm = string;
        this.property_verfied_desg = string1;
        this.property_verfied_em = string2;
        this.property_verfied_mnum =string3;
        this.property_fix_lnum =string4;

    }


    public void readFromParcel(Parcel in)
    {
        id = in.readString();
        property_verfied_nm = in.readString();
        property_verfied_desg = in.readString();
        property_verfied_em = in.readString();
        property_verfied_mnum = in.readString();
        property_fix_lnum = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(property_verfied_nm);
        dest.writeString(property_verfied_desg);
        dest.writeString(property_verfied_em);
        dest.writeString(property_verfied_mnum);
        dest.writeString(property_fix_lnum);
    }

    public static Creator<Property_specification_verified> getCREATOR() {
        return CREATOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProperty_verfied_nm() {
        return property_verfied_nm;
    }

    public void setProperty_verfied_nm(String property_verfied_nm) {
        this.property_verfied_nm = property_verfied_nm;
    }

    public String getProperty_verfied_desg() {
        return property_verfied_desg;
    }

    public void setProperty_verfied_desg(String property_verfied_desg) {
        this.property_verfied_desg = property_verfied_desg;
    }

    public String getProperty_verfied_em() {
        return property_verfied_em;
    }

    public void setProperty_verfied_em(String property_verfied_em) {
        this.property_verfied_em = property_verfied_em;
    }

    public String getProperty_verfied_mnum() {
        return property_verfied_mnum;
    }

    public void setProperty_verfied_mnum(String property_verfied_mnum) {
        this.property_verfied_mnum = property_verfied_mnum;
    }

    public String getProperty_fix_lnum() {
        return property_fix_lnum;
    }

    public void setProperty_fix_lnum(String property_fix_lnum) {
        this.property_fix_lnum = property_fix_lnum;
    }

}
