package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nk on 8/14/2018.
 */

public class addressss implements Parcelable {

    String id;
    String pid;
    String add1;
    String add2;
    String lmark1;
    String lmark2;
    String city1;
    String city2;
    String state1;
    String state2;
    String pin1;
    String pin2;

    protected addressss(Parcel in) {
        id = in.readString();
        pid = in.readString();
        add1 = in.readString();
        add2 = in.readString();
        lmark1 = in.readString();
        lmark2 = in.readString();
        city1 = in.readString();
        city2 = in.readString();
        state1 = in.readString();
        state2 = in.readString();
        pin1 = in.readString();
        pin2 = in.readString();
    }

    public static final Creator<addressss> CREATOR = new Creator<addressss>() {
        @Override
        public addressss createFromParcel(Parcel in) {
            return new addressss(in);
        }

        @Override
        public addressss[] newArray(int size) {
            return new addressss[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(pid);
        dest.writeString(add1);
        dest.writeString(add2);
        dest.writeString(lmark1);
        dest.writeString(lmark2);
        dest.writeString(city1);
        dest.writeString(city2);
        dest.writeString(state1);
        dest.writeString(state2);
        dest.writeString(pin1);
        dest.writeString(pin2);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getLmark1() {
        return lmark1;
    }

    public void setLmark1(String lmark1) {
        this.lmark1 = lmark1;
    }

    public String getLmark2() {
        return lmark2;
    }

    public void setLmark2(String lmark2) {
        this.lmark2 = lmark2;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getPin1() {
        return pin1;
    }

    public void setPin1(String pin1) {
        this.pin1 = pin1;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public static Creator<addressss> getCREATOR() {
        return CREATOR;
    }

}
