package com.example.nk.grpapl.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class wrehouse_ready_move implements Parcelable {

    String id;
    String Warehouse_Picture;
    String Road_Width;
    String Number_Car_Parks;
    String Number_Truck_Parks;
    String Electricity_Provision;
    String Power_Backup;
    String Floor;
    String Leasable_Area;
    String Covered_Shed_dim;
    String Shed_Height;
    String Clear_Height;
    String Centre_Height;
    String Number_Pillars;
    String Flooring;
    String Number_Exclusive_Docks;
    String Number_Shared_Dock;
    String Lift;
    String Warehouse_Pic;
    String Warehouse_Layout;
    String Lockin;
    String Year_Construction;
    String Previous_Tenant;

    public wrehouse_ready_move(Parcel in) {
        id = in.readString();
        Warehouse_Picture = in.readString();
        Road_Width = in.readString();
        Number_Car_Parks = in.readString();
        Number_Truck_Parks = in.readString();
        Electricity_Provision = in.readString();
        Power_Backup = in.readString();
        Floor = in.readString();
        Leasable_Area = in.readString();
        Covered_Shed_dim = in.readString();
        Shed_Height = in.readString();
        Clear_Height = in.readString();
        Centre_Height = in.readString();
        Number_Pillars = in.readString();
        Flooring = in.readString();
        Number_Exclusive_Docks = in.readString();
        Number_Shared_Dock = in.readString();
        Lift = in.readString();
        Warehouse_Pic = in.readString();
        Warehouse_Layout = in.readString();
        Lockin = in.readString();
        Year_Construction = in.readString();
        Previous_Tenant = in.readString();
    }

    public static final Creator<wrehouse_ready_move> CREATOR = new Creator<wrehouse_ready_move>() {
        @Override
        public wrehouse_ready_move createFromParcel(Parcel in) {
            return new wrehouse_ready_move(in);
        }

        @Override
        public wrehouse_ready_move[] newArray(int size) {
            return new wrehouse_ready_move[size];
        }
    };

    public wrehouse_ready_move(String s, String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13, String string14, String string15, String string16, String string17, String string18, String string19, String string20, String string21) {

        this.id= s;
        this.Warehouse_Picture= string;
        this.Road_Width= string1;
        this.Number_Car_Parks= string2;
        this.Number_Truck_Parks= string3;
        this.Electricity_Provision= string4;
        this.Power_Backup= string5;
        this.Floor= string6;
        this.Leasable_Area= string7;
        this.Covered_Shed_dim= string8;
        this.Shed_Height= string9;
        this.Clear_Height= string10;
        this.Centre_Height= string11;
        this.Number_Pillars= string12;
        this.Flooring= string13;
        this.Number_Exclusive_Docks= string14;
        this.Number_Shared_Dock= string15;
        this.Lift= string16;
        this.Warehouse_Pic= string17;
        this.Warehouse_Layout= string18;
        this.Lockin= string19;
        this.Year_Construction= string20;
        this.Previous_Tenant= string21;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(Warehouse_Picture);
        dest.writeString(Road_Width);
        dest.writeString(Number_Car_Parks);
        dest.writeString(Number_Truck_Parks);
        dest.writeString(Electricity_Provision);
        dest.writeString(Power_Backup);
        dest.writeString(Floor);
        dest.writeString(Leasable_Area);
        dest.writeString(Covered_Shed_dim);
        dest.writeString(Shed_Height);
        dest.writeString(Clear_Height);
        dest.writeString(Centre_Height);
        dest.writeString(Number_Pillars);
        dest.writeString(Flooring);
        dest.writeString(Number_Exclusive_Docks);
        dest.writeString(Number_Shared_Dock);
        dest.writeString(Lift);
        dest.writeString(Warehouse_Pic);
        dest.writeString(Warehouse_Layout);
        dest.writeString(Lockin);
        dest.writeString(Year_Construction);
        dest.writeString(Previous_Tenant);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouse_Picture() {
        return Warehouse_Picture;
    }

    public void setWarehouse_Picture(String warehouse_Picture) {
        this.Warehouse_Picture = warehouse_Picture;
    }

    public String getRoad_Width() {
        return Road_Width;
    }

    public void setRoad_Width(String road_Width) {
        this.Road_Width = road_Width;
    }

    public String getNumber_Car_Parks() {
        return Number_Car_Parks;
    }

    public void setNumber_Car_Parks(String number_Car_Parks) {
        Number_Car_Parks = number_Car_Parks;
    }

    public String getNumber_Truck_Parks() {
        return Number_Truck_Parks;
    }

    public void setNumber_Truck_Parks(String number_Truck_Parks) {
        this.Number_Truck_Parks = number_Truck_Parks;
    }

    public String getElectricity_Provision() {
        return Electricity_Provision;
    }

    public void setElectricity_Provision(String electricity_Provision) {
        this.Electricity_Provision = electricity_Provision;
    }

    public String getPower_Backup() {
        return Power_Backup;
    }

    public void setPower_Backup(String power_Backup) {
        Power_Backup = power_Backup;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getLeasable_Area() {
        return Leasable_Area;
    }

    public void setLeasable_Area(String leasable_Area) {
        Leasable_Area = leasable_Area;
    }

    public String getCovered_Shed_dim() {
        return Covered_Shed_dim;
    }

    public void setCovered_Shed_dim(String covered_Shed_dim) {
        Covered_Shed_dim = covered_Shed_dim;
    }

    public String getShed_Height() {
        return Shed_Height;
    }

    public void setShed_Height(String shed_Height) {
        Shed_Height = shed_Height;
    }

    public String getClear_Height() {
        return Clear_Height;
    }

    public void setClear_Height(String clear_Height) {
        Clear_Height = clear_Height;
    }

    public String getCentre_Height() {
        return Centre_Height;
    }

    public void setCentre_Height(String centre_Height) {
        Centre_Height = centre_Height;
    }

    public String getNumber_Pillars() {
        return Number_Pillars;
    }

    public void setNumber_Pillars(String number_Pillars) {
        Number_Pillars = number_Pillars;
    }

    public String getFlooring() {
        return Flooring;
    }

    public void setFlooring(String flooring) {
        Flooring = flooring;
    }

    public String getNumber_Exclusive_Docks() {
        return Number_Exclusive_Docks;
    }

    public void setNumber_Exclusive_Docks(String number_Exclusive_Docks) {
        Number_Exclusive_Docks = number_Exclusive_Docks;
    }

    public String getNumber_Shared_Dock() {
        return Number_Shared_Dock;
    }

    public void setNumber_Shared_Dock(String number_Shared_Dock) {
        Number_Shared_Dock = number_Shared_Dock;
    }

    public String getLift() {
        return Lift;
    }

    public void setLift(String lift) {
        Lift = lift;
    }

    public String getWarehouse_Pic() {
        return Warehouse_Pic;
    }

    public void setWarehouse_Pic(String warehouse_Pic) {
        Warehouse_Pic = warehouse_Pic;
    }

    public String getWarehouse_Layout() {
        return Warehouse_Layout;
    }

    public void setWarehouse_Layout(String warehouse_Layout) {
        Warehouse_Layout = warehouse_Layout;
    }

    public String getLockin() {
        return Lockin;
    }

    public void setLockin(String lockin) {
        Lockin = lockin;
    }

    public String getYear_Construction() {
        return Year_Construction;
    }

    public void setYear_Construction(String year_Construction) {
        Year_Construction = year_Construction;
    }

    public String getPrevious_Tenant() {
        return Previous_Tenant;
    }

    public void setPrevious_Tenant(String previous_Tenant) {
        Previous_Tenant = previous_Tenant;
    }

    public static Creator<wrehouse_ready_move> getCREATOR() {
        return CREATOR;
    }


}
