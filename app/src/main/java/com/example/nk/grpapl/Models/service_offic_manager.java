package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class service_offic_manager implements Parcelable {

        String id;
        String name;
        String designation;
        String number;
        String email;
        String fees;


    public service_offic_manager(Parcel in) {
        id = in.readString();
        name = in.readString();
        designation = in.readString();
        number = in.readString();
        email = in.readString();
        fees = in.readString();
    }

    public service_offic_manager(String string, String string1, String string2, String string3, String string4) {
       this.name=string;
       this.designation=string1;
       this.number=string2;
       this.email=string3;
       this.fees=string4;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(designation);
        dest.writeString(number);
        dest.writeString(email);
        dest.writeString(fees);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<service_offic_manager> CREATOR = new Creator<service_offic_manager>() {
        @Override
        public service_offic_manager createFromParcel(Parcel in) {
            return new service_offic_manager(in);
        }

        @Override
        public service_offic_manager[] newArray(int size) {
            return new service_offic_manager[size];
        }
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public static Creator<service_offic_manager> getCREATOR() {
        return CREATOR;
    }


}
