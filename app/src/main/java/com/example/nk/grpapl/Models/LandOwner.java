package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.TabHost;

/**
 * Created by Nk on 7/19/2018.
 */

public class LandOwner implements Parcelable {

    public LandOwner(Parcel in) {
        readFromParcel(in);
    }

    public LandOwner(String string, String cursorString, String s, String string1){
        this.lnd_own_comp_name=string;
        this.land_own_cont_nm=cursorString;
        this.land_owner_num = s;
        this.land_own_email = string1;
    }

    public static final Creator<LandOwner> CREATOR = new Creator<LandOwner>() {
        @Override
        public LandOwner createFromParcel(Parcel in) {
            return new LandOwner(in);
        }

        @Override
        public LandOwner[] newArray(int size) {
            return new LandOwner[size];
        }

    };

    String id;
    String lnd_own_comp_name;
    String land_own_cont_nm;
    String land_owner_num;
    String land_own_email;

    public void readFromParcel(Parcel in)
    {
        id = in.readString();
        lnd_own_comp_name = in.readString();
        land_own_cont_nm = in.readString();
        land_owner_num = in.readString();
        land_own_email = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(lnd_own_comp_name);
        dest.writeString(land_own_cont_nm);
        dest.writeString(land_owner_num);
        dest.writeString(land_own_email);
    }

    public static Creator<LandOwner> getCreator(){return CREATOR;}

    public String getId(){return id;}
    public void setId(){this.id= id;}


    public String getlnd_own_comp_name(){return lnd_own_comp_name;}
    public void setlnd_own_comp_name(){this.lnd_own_comp_name= lnd_own_comp_name;}


    public String getland_own_cont_nm(){return land_own_cont_nm;}
    public void setland_own_cont_nm(){this.land_own_cont_nm= land_own_cont_nm;}


    public String getland_owner_num(){return land_owner_num;}
    public void setland_owner_num(){this.land_owner_num= land_owner_num;}


    public String getland_own_email(){return land_own_email;}
    public void setland_own_email(){this.land_own_email= land_own_email;}

}
