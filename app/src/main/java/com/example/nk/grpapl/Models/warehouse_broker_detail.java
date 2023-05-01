package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class warehouse_broker_detail implements Parcelable {

    String id;
    String name;
    String number;
    String email;
    String fees;
    String agree_fees;

    public warehouse_broker_detail(Parcel in) {
        id = in.readString();
        name = in.readString();
        number = in.readString();
        email = in.readString();
        fees = in.readString();
        agree_fees = in.readString();
    }

    public static final Creator<warehouse_broker_detail> CREATOR = new Creator<warehouse_broker_detail>() {
        @Override
        public warehouse_broker_detail createFromParcel(Parcel in) {
            return new warehouse_broker_detail(in);
        }

        @Override
        public warehouse_broker_detail[] newArray(int size) {
            return new warehouse_broker_detail[size];
        }
    };

    public warehouse_broker_detail(String string, String string1, String string2) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(number);
        dest.writeString(email);
        dest.writeString(fees);
        dest.writeString(agree_fees);
    }
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

    public String getAgree_fees() {
        return agree_fees;
    }

    public void setAgree_fees(String agree_fees) {
        this.agree_fees = agree_fees;
    }

    public static Creator<warehouse_broker_detail> getCREATOR() {
        return CREATOR;
    }
}
