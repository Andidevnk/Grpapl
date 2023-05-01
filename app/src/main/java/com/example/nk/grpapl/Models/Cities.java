package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cities  implements Parcelable {



    String city_name;

    protected Cities(Parcel in) {

        city_name = in.readString();
    }

    public static final Creator<Cities> CREATOR = new Creator<Cities>() {
        @Override
        public Cities createFromParcel(Parcel in) {
            return new Cities(in);
        }

        @Override
        public Cities[] newArray(int size) {
            return new Cities[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(city_name);
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public static Creator<Cities> getCREATOR() {
        return CREATOR;
    }

}
