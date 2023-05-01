package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class service_off_workspace  implements Parcelable{

    String id;
    String workspace_type;
    String work_size;
    String setign_cap;
    String quoted_rent;
    String quoted_rent_seat;
    String chrg_byond_normal;

    public service_off_workspace(Parcel in) {
        id = in.readString();
        workspace_type = in.readString();
        work_size = in.readString();
        setign_cap = in.readString();
        quoted_rent = in.readString();
        quoted_rent_seat = in.readString();
        chrg_byond_normal = in.readString();
    }

    public service_off_workspace(String string, String string1, String string2, String string3, String string4, String string5) {
        this.workspace_type=string;
        this.work_size=string1;
        this.setign_cap=string2;
        this.quoted_rent=string3;
        this.quoted_rent_seat=string4;
        this.chrg_byond_normal=string5;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(workspace_type);
        dest.writeString(work_size);
        dest.writeString(setign_cap);
        dest.writeString(quoted_rent);
        dest.writeString(quoted_rent_seat);
        dest.writeString(chrg_byond_normal);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<service_off_workspace> CREATOR = new Creator<service_off_workspace>() {
        @Override
        public service_off_workspace createFromParcel(Parcel in) {
            return new service_off_workspace(in);
        }

        @Override
        public service_off_workspace[] newArray(int size) {
            return new service_off_workspace[size];
        }
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkspace_type() {
        return workspace_type;
    }

    public void setWorkspace_type(String workspace_type) {
        this.workspace_type = workspace_type;
    }

    public String getWork_size() {
        return work_size;
    }

    public void setWork_size(String work_size) {
        this.work_size = work_size;
    }

    public String getSetign_cap() {
        return setign_cap;
    }

    public void setSetign_cap(String setign_cap) {
        this.setign_cap = setign_cap;
    }

    public String getQuoted_rent() {
        return quoted_rent;
    }

    public void setQuoted_rent(String quoted_rent) {
        this.quoted_rent = quoted_rent;
    }

    public String getQuoted_rent_seat() {
        return quoted_rent_seat;
    }

    public void setQuoted_rent_seat(String quoted_rent_seat) {
        this.quoted_rent_seat = quoted_rent_seat;
    }

    public String getChrg_byond_normal() {
        return chrg_byond_normal;
    }

    public void setChrg_byond_normal(String chrg_byond_normal) {
        this.chrg_byond_normal = chrg_byond_normal;
    }

    public static Creator<service_off_workspace> getCREATOR() {
        return CREATOR;
    }


}
