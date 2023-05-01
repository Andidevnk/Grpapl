package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class warehouse_rent implements Parcelable {

    String  id;
    String  company_name;
    String contact_person_name;
    String designation;
    String number;
    String email;
    String leas_expire;
    String rent;
    String Rent_by_grpapl;

    public warehouse_rent(Parcel in) {
        id = in.readString();
        company_name = in.readString();
        contact_person_name = in.readString();
        designation = in.readString();
        number = in.readString();
        email = in.readString();
        leas_expire = in.readString();
        rent = in.readString();
        Rent_by_grpapl = in.readString();
    }

    public warehouse_rent(String s, String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7) {

         this.id=s;
        this.company_name=string;
        this.contact_person_name=string1;
        this.designation=string2;
        this.number=string3;
        this.email=string4;
        this.leas_expire=string5;
        this.rent=string6;
        this.Rent_by_grpapl=string7;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(company_name);
        dest.writeString(contact_person_name);
        dest.writeString(designation);
        dest.writeString(number);
        dest.writeString(email);
        dest.writeString(leas_expire);
        dest.writeString(rent);
        dest.writeString(Rent_by_grpapl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<warehouse_rent> CREATOR = new Creator<warehouse_rent>() {
        @Override
        public warehouse_rent createFromParcel(Parcel in) {
            return new warehouse_rent(in);
        }

        @Override
        public warehouse_rent[] newArray(int size) {
            return new warehouse_rent[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContact_person_name() {
        return contact_person_name;
    }

    public void setContact_person_name(String contact_person_name) {
        this.contact_person_name = contact_person_name;
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

    public String getLeas_expire() {
        return leas_expire;
    }

    public void setLeas_expire(String leas_expire) {
        this.leas_expire = leas_expire;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRent_by_grpapl() {
        return Rent_by_grpapl;
    }

    public void setRent_by_grpapl(String rent_by_grpapl) {
        Rent_by_grpapl = rent_by_grpapl;
    }

    public static Creator<warehouse_rent> getCREATOR() {
        return CREATOR;
    }


}
