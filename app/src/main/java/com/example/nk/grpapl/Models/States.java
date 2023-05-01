package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class States implements Parcelable {



    String city_state;

    protected States(Parcel in) {
        city_state = in.readString();
    }

    public static final Creator<States> CREATOR = new Creator<States>() {
        @Override
        public States createFromParcel(Parcel in) {
            return new States(in);
        }

        @Override
        public States[] newArray(int size) {
            return new States[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city_state);
    }
    public String getCity_state() {
        return city_state;
    }

    public void setCity_state(String city_state) {
        this.city_state = city_state;
    }

    public static Creator<States> getCREATOR() {
        return CREATOR;
    }
}
