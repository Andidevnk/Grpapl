package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class warehouse_mngr_detail implements Parcelable {

    String id;
    String name;
    String number;
    String email;

    public warehouse_mngr_detail(String name,String number,String email)
    {
        this.name = name;
        this.number = number;
        this.email = email;
    }

    protected warehouse_mngr_detail(Parcel in) {
        id = in.readString();
        name = in.readString();
        number = in.readString();
        email = in.readString();
    }

    public static final Creator<warehouse_mngr_detail> CREATOR = new Creator<warehouse_mngr_detail>() {
        @Override
        public warehouse_mngr_detail createFromParcel(Parcel in) {
            return new warehouse_mngr_detail(in);
        }

        @Override
        public warehouse_mngr_detail[] newArray(int size) {
            return new warehouse_mngr_detail[size];
        }
    };

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
    }

    public static Creator<warehouse_mngr_detail> getCREATOR() {
        return CREATOR;
    }

    public String getId() {return id; }
    public void setId(String id) {this.id = id;}

    public String getName() {return name; }
    public void setName(String name) {this.name = name;}

    public String getNumber() {return number; }
    public void setNumber(String number) {this.number = number; }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
}
