package com.example.nk.grpapl.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nk.grpapl.Models.LandBroker;
import com.example.nk.grpapl.Models.LandManager;
import com.example.nk.grpapl.Models.LandOwner;
import com.example.nk.grpapl.Models.Landpartavailable;
import com.example.nk.grpapl.Models.Office_space_part_available;
import com.example.nk.grpapl.Models.Office_space_rented;
import com.example.nk.grpapl.Models.OfficespaceBroker;
import com.example.nk.grpapl.Models.OfficespaceManager;
import com.example.nk.grpapl.Models.OfficespaceOwner;
import com.example.nk.grpapl.Models.Property_specification_verified;
import com.example.nk.grpapl.Models.service_off_workspace;
import com.example.nk.grpapl.Models.service_offic_manager;
import com.example.nk.grpapl.Models.warehouse_broker_detail;
import com.example.nk.grpapl.Models.warehouse_mngr_detail;
import com.example.nk.grpapl.Models.warehouse_owner_detail;
import com.example.nk.grpapl.Models.warehouse_part_avail;
import com.example.nk.grpapl.Models.warehouse_rent;
import com.example.nk.grpapl.Models.wrehouse_ready_move;

import java.util.ArrayList;

/**
 * Created by Nk on 7/20/2018.
 */

public class Databse_Handler extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "grapapl";

    //WAREHOUSE
    // Contacts table name
    private static final String TABLE_Warehouse_Mngrdetail = "warehouse_mangr_detail";
    private static final String TABLE_Warehouse_Broker_detail = "warehouse_broker_detail";
    private static final String TABLE_Warehouse_Owner_detail = "warehouse_owner_detail";
    private static final String TABLE_Service_office_Manager= "service_offic_manager";
    private static final String TABLE_Service_office_workspace= "service_off_workspace";
    private static final String TABLE_warehouse_rent= "warehouse_rent";
    private static final String TABLE_warehouse_part_avail= "warehouse_part_avail";
    private static final String TABLE_warehouse_ready2_move= "wrehouse_ready_move";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_Mngr_nm = "mngr_nm";
    private static final String KEY_Mngr_num = "mngr_num";
    private static final String KEY_Mngr_emial = "mngr_email";

    // Contacts Table Columns names
    private static final String KEY_Owner_ID = "id";
    private static final String KEY_Owner_nm = "owner_nm";
    private static final String KEY_Owner_num = "owner_num";
    private static final String KEY_Owner_emial = "owner_email";

    // Contacts Table Columns names
    private static final String KEY_Broker_ID = "id";
    private static final String KEY_Broker_nm = "broker_nm";
    private static final String KEY_Broker_num = "broker_num";
    private static final String KEY_Broker_emial = "broker_email";
    private static final String KEY_Broker_fee = "broker_fee";
    private static final String KEY_Broker_agreefee = "broker_agreefee";


    // Contacts Table Columns names
    private static final String KEY_Service_ID = "id";
    private static final String KEY_Service_Name = "service_name";
    private static final String KEY_Service_Designation = "service_desig";
    private static final String KEY_Service_Num = "service_num";
    private static final String KEY_Service_email = "service_email";
    private static final String KEY_Service_fees = "service_fees";


    //LAND
    // Contacts table name
    private static final String TABLE_LAND_OWNER = "tbl_land_owner";
    private static final String TABLE_LAND_MANAGER = "tbl_land_manager";
    private static final String TABLE_LAND_BROKER = "tbl_land_broker";
    private static final String TABLE_LAND_PART_AVA = "tbl_land_part_ava";

    // Contacts Table Columns names owner
    private static final String KEY_LAND_OWNER_ID = "id";
    private static final String KEY_LAND_OWNER_COMPNAY_NM = "onr_comp_nm";
    private static final String KEY_LAND_OWNER_CONT_NM = "onr_cont_nm";
    private static final String KEY_LAND_OWNER_NUM = "onr_num";
    private static final String KEY_LAND_OWNER_EML = "onr_eml";


    // Contacts Table Columns names manager
    private static final String KEY_LAND_MANAGER_ID = "id";
    private static final String KEY_LAND_MANAGER_CN = "mngr_cn";
    private static final String KEY_LAND_MANAGER_NM = "mngr_nm";
    private static final String KEY_LAND_MANAGER_NUM = "mngr_num";
    private static final String KEY_LAND_MANAGER_EMAIL = "mngr_email";


    // Contacts Table Columns names broker
    private static final String KEY_LAND_BROKER_ID = "id";
    private static final String KEY_LAND_BROKER_CN = "brok_cn";
    private static final String KEY_LAND_BROKER_NM = "brok_nm";
    private static final String KEY_LAND_BROKER_NUM = "brok_num";
    private static final String KEY_LAND_BROKER_EMAIL = "brok_email";
    private static final String KEY_LAND_BROKER_FEES = "brok_fees";
    private static final String KEY_LAND_BROKER_AGFEES = "brok_agfees";


    //Contacts Tbale colums names land part available


    private static final String KEY_LAND_PART_AVA_ID = "id";
    private static final String KEY_LAND_PART_AVA_CN = "part_ava_cn";
    private static final String KEY_LAND_PART_AVA_CPN = "part_ava_cpn";
    private static final String KEY_LAND_PART_AVA_NUM = "part_ava_num";
    private static final String KEY_LAND_PART_AVA_EMAIL = "part_ava_email";
    private static final String KEY_LAND_PART_AVA_AREA_SOLD = "part_ava_area_sold";
    private static final String KEY_LAND_PART_AVA_SALES_PRICE = "part_ava_sales_price";
    private static final String KEY_LAND_PART_AVA_SOLD_GRPAPL = "part_ava_sold_grpapl";



//OFFICE SPACE

    private static final String TABLE_OFFICE_SPACE_OWNER = "tbl_office_space_owner";
    private static final String TABLE_OFFICE_SPACE_MANAGER = "tbl_office_space_manager";
    private static final String TABLE_OFFICE_SPACE_BROKER = "tbl_office_space_broker";
    private static final String TABLE_OFFICE_SPACE_PART_AVAILABLE = "tbl_office_space_part_ava";
    private static final String TABLE_OFFICE_SPACE_RENTED = "tbl_office_space_rented";


    // Contacts Table Columns names owner
    private static final String KEY_OFFICE_SPACE_OWNER_ID = "id";
    private static final String KEY_OFFICE_SPACEOWNER_COMPNAY_NM = "os_comp_nm";
    private static final String KEY_OFFICE_SPACE_OWNER_CONT_NM = "os_cont_nm";
    private static final String KEY_OFFICE_SPACE_OWNER_NUM = "os_num";
    private static final String KEY_OFFICE_SPACE_OWNER_EML = "os_eml";


    // Contacts Table Columns names manager
    private static final String KEY_OFFICE_SPACE_MANAGER_ID = "id";
    private static final String KEY_OFFICE_SPACE_MANAGER_NM = "os_mngr_nm";
    private static final String KEY_OFFICE_SPACE_MANAGER_NUM = "os_mngr_num";
    private static final String KEY_OFFICE_SPACE_MANAGER_EMAIL = "os_mngr_email";


    // Contacts Table Columns names broker
    private static final String KEY_OFFICE_SPACE_BROKER_ID = "id";
    private static final String KEY_OFFICE_SPACE_BROKER_NM = "os_brok_nm";
    private static final String KEY_OFFICE_SPACE_BROKER_NUM = "os_brok_num";
    private static final String KEY_OFFICE_SPACE_BROKER_EMAIL = "os_brok_email";
    private static final String KEY_OFFICE_SPACE_BROKER_FEES = "os_brok_fees";
    private static final String KEY_OFFICE_SPACE_BROKER_AGFEES = "os_brok_agfees";


    //colums Table office_space_part_ava
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_ID = "id";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVAILABLE  = "os_part_ava_space_ava";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_COM_NM = "os_part_ava_cm_nm";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_PER_NM = "os_part_ava_con_per_nm";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_DESIG = "os_part_ava_desig";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_NUM = "os_part_ava_con_num";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_EM = "os_part_ava_em";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_AREA_RENTAD ="os_part_ava_area_rentad";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_FLOOR = "os_part_ava_floor";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVA = "os_part_ava_spce_ava";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_LOC_IN_EXPIRY = "os_part_ava_loc_in_expiry";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_LEASE_EXPIREY = "os_part_ava_lease_expirey";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_RENT = "os_part_ava_rent";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC = "os_part_ava_rent_esc";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC_PERIOD = "os_part_ava_rent_esc_perio";
    private static final String KEY_OFFICE_SPACE_PART_AVAILABLE_RENTED_BY_GRPAPL = "os_part_ava_rented_by_grpapl";


    //colums Table office space rented
    private static final String KEY_OFFICE_SPACE_RENTED_ID = "id";
    private static final String KEY_OFFICE_SPACE_RENTED_COM_NM = "os_rented_cm_nm";
    private static final String KEY_OFFICE_SPACE_RENETD_CONT_PER_NM = "os_renetd_con_per_nm";
    private static final String KEY_OFFICE_SPACE_RENTED_DESIG = "os_rented_desig";
    private static final String KEY_OFFICE_SPACE_RENETD_CONT_NUM = "os_renetd_con_num";
    private static final String KEY_OFFICE_SPACE_RENTED_EM = "os_rented_em";
    private static final String KEY_OFFICE_SPACE_RENETD_LOC_IN_EXPIRY = "os_rented_loc_in_expiry";
    private static final String KEY_OFFICE_SPACE_RENETD_LEASE_EXPIREY = "os_rented_lease_expirey";
    private static final String KEY_OFFICE_SPACE_RENETD_RENT = "os_rented_rent";
    private static final String KEY_OFFICE_SPACE_RENETD_RENT_ESC = "os_rented_rent_esc";
    private static final String KEY_OFFICE_SPACE_RENETD_RENT_ESC_PERIOD = "os_rented_rent_esc_perio";
    private static final String KEY_OFFICE_SPACE_RENETD_RENTED_BY_GRPAPL = "os_rented_rented_by_grpapl";



    //property_specification

    private static final String TABLE_PROPERTY_SPECIFICATION_VERIFIED = "tbl_property_specification_verified";

    //contacts Table columns property specification
    private static final String KEY_PROPERTY_SPECIFICATION_VERIFIED_ID = "id";
    private static final String KEY_PROPERTY_SPECIFICATION_VERIFIED_NM = "ps_v_nm";
    private static final String KEY_PROPERTY_SPECIFICATION_VERIFIED_DESIG = "ps_v_desi";
    private static final String KEY_PROPERTY_SPECIFICATION_VERIFIED_EM = "ps_v_em";
    private static final String KEY_PROPERTY_SPECIFICATION_VERIFIED_MNUM = "ps_v_mnum";
    private static final String KEY_PROPERTY_SPECIFICATION_VERIFIED_FIXED_NUM = "ps_v_fnum";


    // Contacts Table Columns names
    private static final String KEY_warehouse_rent_id = "id";
    private static final String KEY_warehouse_rent_compname = "comp_name";
    private static final String KEY_warehouse_rent_personname = "person_name";
    private static final String KEY_warehouse_rent_desig = "desig";
    private static final String KEY_warehouse_rent_num = "num";
    private static final String KEY_warehouse_rent_email = "email";
    private static final String KEY_warehouse_rent_leas_expir = "expir";
    private static final String KEY_warehouse_rent_rent = "rent";
    private static final String KEY_warehouse_rent_rentbygrpapl = "rent_by_grpapl";

    // Contacts Table Columns names
    private static final String KEY_warehouse_part_id = "id";
    private static final String KEY_warehouse_space_avail = "space_avail";
    private static final String KEY_warehouse_part_compname = "comp_name";
    private static final String KEY_warehouse_part_personname = "person_name";
    private static final String KEY_warehouse_part_desig = "desig";
    private static final String KEY_warehouse_part_num = "num";
    private static final String KEY_warehouse_part_email = "email";
    private static final String KEY_warehouse_part_area_avail = "area_vail";
    private static final String KEY_warehouse_part_floor = "floor";
    private static final String KEY_warehouse_part_leas_expir = "expir";
    private static final String KEY_warehouse_part_rent = "rent";
    private static final String KEY_warehouse_part_rentbygrpapl = "rent_by_grpapl";

    // Contacts Table Columns names
    private static final String KEY_ready_id = "id";
    private static final String KEY_ready_Warehouse_Picture = "ware_pic";
    private static final String KEY_ready_Road_Width = "road";
    private static final String KEY_ready_Number_Car_Parks = "carpark";
    private static final String KEY_ready_Number_Truck_Parks = "truckpark";
    private static final String KEY_ready_Electricity_Provision = "ec_pro";
    private static final String KEY_ready_Power_Backup = "pow_back";
    private static final String KEY_ready_Floor = "floor";
    private static final String KEY_ready_Leasable_Area = "lease_area";
    private static final String KEY_ready_Covered_Shed_dim = "cov_shed";
    private static final String KEY_ready_Shed_Height = "shed_height";
    private static final String KEY_ready_Clear_Height = "clear_height";
    private static final String KEY_ready_Centre_Height = "center_height";
    private static final String KEY_ready_Number_Pillars = "num_pill";
    private static final String KEY_ready_Flooring = "floore";
    private static final String KEY_ready_Number_Exclusive_Docks = "num_exc_dox";
    private static final String KEY_ready_Number_Shared_Dock = "num_shr_dox";
    private static final String KEY_ready_Lift= "lift";
    private static final String KEY_ready_Warehouse_Pic = "war_pic";
    private static final String KEY_ready_Warehouse_Layout = "war_lay";
    private static final String KEY_ready_Lockin = "lock";
    private static final String KEY_ready_Year_Construction = "year_cons";
    private static final String KEY_ready_Year_Previous_Tenant = "tenant";

    // Contacts Table Columns names
    private static final String KEY_servic_wrkspc_ID = "id";
    private static final String KEY_servic_workspace_type= "workspc_type";
    private static final String KEY_servic_wrkspc_work_size = "work_size";
    private static final String KEY_servic_wrkspc_setign_cap= "seting_cap";
    private static final String KEY_servic_wrkspc_quoted_rent = "quot_rent";
    private static final String KEY_servic_wrkspc_quoted_rent_seat = "quoted_rent_seat";
    private static final String KEY_servic_wrkspc_chrg_byond_normal = "chrg_beyond_normal";

    public Databse_Handler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



//LAND
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MANAGER_TABLE = "CREATE TABLE " + TABLE_Warehouse_Mngrdetail + "("+ KEY_Owner_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_Mngr_nm + " TEXT," + KEY_Mngr_num + " TEXT,"+ KEY_Mngr_emial +
                " TEXT)";

        String CREATE_WAREHOUS_TABLE = "CREATE TABLE " + TABLE_Warehouse_Broker_detail + "("+ KEY_Broker_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_Broker_nm + " TEXT," + KEY_Broker_num + " TEXT,"+ KEY_Broker_emial +
                " TEXT," + KEY_Broker_fee + " TEXT," + KEY_Broker_agreefee + " TEXT)";

        String CREATE_OWNER_TABLE = "CREATE TABLE " + TABLE_Warehouse_Owner_detail + "("+ KEY_Owner_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_Owner_nm + " TEXT," + KEY_Owner_num + " TEXT,"+ KEY_Owner_emial + " TEXT)";

        String CREATE_LAND_OWNER_TABLE = "CREATE TABLE " + TABLE_LAND_OWNER + "("+ KEY_LAND_OWNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_LAND_OWNER_COMPNAY_NM + " TEXT," + KEY_LAND_OWNER_CONT_NM +
                " TEXT,"+ KEY_LAND_OWNER_NUM + " TEXT," + KEY_LAND_OWNER_EML + " TEXT)";

        String CREATE_LAND_MANAGER_TABLE = "CREATE TABLE " + TABLE_LAND_MANAGER +"("+ KEY_LAND_MANAGER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LAND_MANAGER_CN + " TEXT, " + KEY_LAND_MANAGER_NM + " TEXT," + KEY_LAND_MANAGER_NUM + " TEXT,"+
                KEY_LAND_MANAGER_EMAIL +" TEXT )";


        String CREATE_LAND_BROKER_TABLE = "CREATE TABLE " +TABLE_LAND_BROKER +"("+ KEY_LAND_BROKER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LAND_BROKER_CN + " TEXT ," + KEY_LAND_BROKER_NM + " TEXT," + KEY_LAND_BROKER_NUM + " TEXT,"
                + KEY_LAND_BROKER_EMAIL + " TEXT , " + KEY_LAND_BROKER_FEES + " TEXT," + KEY_LAND_BROKER_AGFEES + " TEXT ) ";


        String CREATE_LAND_PART_AVA_TABLE = " CREATE TABLE " +TABLE_LAND_PART_AVA + "("+ KEY_LAND_PART_AVA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_LAND_PART_AVA_CN + " TEXT," + KEY_LAND_PART_AVA_CPN + " TEXT,"
                + KEY_LAND_PART_AVA_NUM + " TEXT," + KEY_LAND_PART_AVA_EMAIL + " TEXT," + KEY_LAND_PART_AVA_AREA_SOLD + " TEXT," + KEY_LAND_PART_AVA_SALES_PRICE + " TEXT,"
                + KEY_LAND_PART_AVA_SOLD_GRPAPL+ " TEXT )";

   ///OFFICE SPACE

        String CREATE_OFFICE_SPACE_OWNER_TABLE = "CREATE TABLE " + TABLE_OFFICE_SPACE_OWNER + "("+ KEY_OFFICE_SPACE_OWNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ KEY_OFFICE_SPACEOWNER_COMPNAY_NM + " TEXT," + KEY_OFFICE_SPACE_OWNER_CONT_NM +
                " TEXT,"+ KEY_OFFICE_SPACE_OWNER_NUM + " TEXT," + KEY_OFFICE_SPACE_OWNER_EML + " TEXT )";

        String CREATE_OFFICE_SPACE_MANAGER_TABLE = "CREATE TABLE " +TABLE_OFFICE_SPACE_MANAGER +"("+ KEY_OFFICE_SPACE_MANAGER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_OFFICE_SPACE_MANAGER_NM + " TEXT,"+KEY_OFFICE_SPACE_MANAGER_NUM + " TEXT,"+
                KEY_OFFICE_SPACE_MANAGER_EMAIL + " TEXT )";


        String CREATE_OFFICE_SPACE_BROKER_TABLE = "CREATE TABLE " +TABLE_OFFICE_SPACE_BROKER +"("+KEY_OFFICE_SPACE_BROKER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_OFFICE_SPACE_BROKER_NM + " TEXT,"+KEY_OFFICE_SPACE_BROKER_NUM + " TEXT,"
                + KEY_OFFICE_SPACE_BROKER_EMAIL + " TEXT," + KEY_OFFICE_SPACE_BROKER_FEES + " TEXT,"+KEY_OFFICE_SPACE_BROKER_AGFEES+ " TEXT )";

        String CREATE_OFFICE_SPACE_PART_AVAILABLE = "CREATE TABLE " +TABLE_OFFICE_SPACE_PART_AVAILABLE + "("+KEY_OFFICE_SPACE_PART_AVAILABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVAILABLE + " TEXT,"  + KEY_OFFICE_SPACE_PART_AVAILABLE_COM_NM + " TEXT," +KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_PER_NM + " TEXT," +
                KEY_OFFICE_SPACE_PART_AVAILABLE_DESIG + " TEXT," + KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_NUM + " TEXT," +KEY_OFFICE_SPACE_PART_AVAILABLE_EM + " TEXT," +KEY_OFFICE_SPACE_PART_AVAILABLE_AREA_RENTAD + " TEXT," +
                KEY_OFFICE_SPACE_PART_AVAILABLE_FLOOR + " TEXT," + KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVA + " TEXT,"  + KEY_OFFICE_SPACE_PART_AVAILABLE_LOC_IN_EXPIRY + " TEXT," + KEY_OFFICE_SPACE_PART_AVAILABLE_LEASE_EXPIREY +
                " TEXT," + KEY_OFFICE_SPACE_PART_AVAILABLE_RENT + " TEXT," + KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC + " TEXT," + KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC_PERIOD + " TEXT," + KEY_OFFICE_SPACE_PART_AVAILABLE_RENTED_BY_GRPAPL + " TEXT)";


        String CREATE_OFFICE_SPACE_RENTED = "CREATE TABLE " + TABLE_OFFICE_SPACE_RENTED + "("+ KEY_OFFICE_SPACE_RENTED_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_OFFICE_SPACE_RENTED_COM_NM + " TEXT," +  KEY_OFFICE_SPACE_RENETD_CONT_PER_NM + " TEXT," +
                KEY_OFFICE_SPACE_RENTED_DESIG + " TEXT," + KEY_OFFICE_SPACE_RENETD_CONT_NUM + " TEXT," + KEY_OFFICE_SPACE_RENTED_EM + " TEXT,"  +
                KEY_OFFICE_SPACE_RENETD_LOC_IN_EXPIRY + " TEXT," + KEY_OFFICE_SPACE_RENETD_LEASE_EXPIREY + " TEXT," + KEY_OFFICE_SPACE_RENETD_RENT + " TEXT," + KEY_OFFICE_SPACE_RENETD_RENT_ESC + " TEXT," +
                KEY_OFFICE_SPACE_RENETD_RENT_ESC_PERIOD + " TEXT," + KEY_OFFICE_SPACE_RENETD_RENTED_BY_GRPAPL + " TEXT)";



        String CREATE_PROPERTY_SPACIFIACTION_VERIFIED_TABLE = "CREATE TABLE " +TABLE_PROPERTY_SPECIFICATION_VERIFIED +"(" +KEY_PROPERTY_SPECIFICATION_VERIFIED_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PROPERTY_SPECIFICATION_VERIFIED_NM + " TEXT,"
                + KEY_PROPERTY_SPECIFICATION_VERIFIED_DESIG+ " TEXT," + KEY_PROPERTY_SPECIFICATION_VERIFIED_EM + " TEXT," +KEY_PROPERTY_SPECIFICATION_VERIFIED_MNUM + " TEXT," +KEY_PROPERTY_SPECIFICATION_VERIFIED_FIXED_NUM + " TEXT)";


        String CREATE_Warehouse_ready2_move_TABLE = "CREATE TABLE " + TABLE_warehouse_ready2_move + "("+ KEY_ready_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_ready_Warehouse_Picture + " TEXT," + KEY_ready_Road_Width + " TEXT,"+ KEY_ready_Number_Car_Parks +
                " TEXT, " + KEY_ready_Number_Truck_Parks + " TEXT, " + KEY_warehouse_part_desig + " TEXT, " + KEY_ready_Electricity_Provision + " TEXT," + KEY_ready_Power_Backup + " TEXT,"
                + KEY_ready_Floor + " TEXT, " + KEY_ready_Leasable_Area + " TEXT, " + KEY_ready_Covered_Shed_dim + " TEXT,"
                + KEY_ready_Shed_Height +" TEXT, " + KEY_ready_Clear_Height + " TEXT, " + KEY_ready_Centre_Height + " TEXT,"
                + KEY_ready_Number_Pillars +" TEXT, " + KEY_ready_Flooring + " TEXT,  " + KEY_ready_Number_Exclusive_Docks + " TEXT, "
                + KEY_ready_Number_Shared_Dock + " TEXT, " + KEY_ready_Lift + " TEXT, " + KEY_ready_Warehouse_Pic + " TEXT,"
                + KEY_ready_Warehouse_Layout + " TEXT, " + KEY_ready_Lockin + " TEXT, " + KEY_ready_Year_Construction + " TEXT,"
                + KEY_ready_Year_Previous_Tenant + " TEXT)";

        String CREATE_Warehouse_Rent_TABLE = "CREATE TABLE " + TABLE_warehouse_rent + "("+ KEY_warehouse_rent_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_warehouse_rent_compname + " TEXT," + KEY_warehouse_rent_personname + " TEXT,"+ KEY_warehouse_rent_desig +
                " TEXT," + KEY_warehouse_rent_num + " TEXT," + KEY_warehouse_rent_email +" TEXT," + KEY_warehouse_rent_leas_expir + " TEXT,"
                + KEY_warehouse_rent_rent +" TEXT," + KEY_warehouse_rent_rentbygrpapl +" TEXT)";

        String CREATE_Warehouse_part_avail_TABLE = "CREATE TABLE " + TABLE_warehouse_part_avail + "("+ KEY_warehouse_part_id + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_warehouse_space_avail + " TEXT," + KEY_warehouse_part_compname + " TEXT,"+ KEY_warehouse_part_personname +
                " TEXT," + KEY_warehouse_part_desig + " TEXT," + KEY_warehouse_part_num + " TEXT," + KEY_warehouse_part_email + " TEXT,"
                + KEY_warehouse_part_area_avail + " TEXT," + KEY_warehouse_part_floor + " TEXT, " + KEY_warehouse_part_leas_expir + " TEXT,"
                + KEY_warehouse_part_rent +" TEXT, " + KEY_warehouse_part_rentbygrpapl + " TEXT)";


        String CREATE_service_workspace_TABLE = "CREATE TABLE " +TABLE_Service_office_workspace +"(" +KEY_servic_wrkspc_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_servic_workspace_type + " TEXT,"
                + KEY_servic_wrkspc_work_size+ " TEXT," + KEY_servic_wrkspc_setign_cap + " TEXT," +KEY_servic_wrkspc_quoted_rent + " TEXT," +KEY_servic_wrkspc_quoted_rent_seat + " TEXT, " +KEY_servic_wrkspc_chrg_byond_normal + " TEXT)";

        String CREATE_service_manager_TABLE = "CREATE TABLE " + TABLE_Service_office_Manager + "("+ KEY_Service_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_Service_Name + " TEXT," + KEY_Service_Designation + " TEXT,"+ KEY_Service_Num +
                " TEXT," + KEY_Service_email + " TEXT," + KEY_Service_fees +" TEXT)";


        db.execSQL(CREATE_LAND_OWNER_TABLE);
        db.execSQL(CREATE_LAND_MANAGER_TABLE);
        db.execSQL(CREATE_LAND_BROKER_TABLE);
        db.execSQL(CREATE_LAND_PART_AVA_TABLE);
        db.execSQL(CREATE_OFFICE_SPACE_OWNER_TABLE);
        db.execSQL(CREATE_OFFICE_SPACE_MANAGER_TABLE);
        db.execSQL(CREATE_OFFICE_SPACE_BROKER_TABLE);
        db.execSQL(CREATE_OFFICE_SPACE_PART_AVAILABLE);
        db.execSQL(CREATE_OFFICE_SPACE_RENTED);
        db.execSQL(CREATE_OWNER_TABLE);
        db.execSQL(CREATE_MANAGER_TABLE);
        db.execSQL(CREATE_WAREHOUS_TABLE);
        db.execSQL(CREATE_service_manager_TABLE);
        db.execSQL(CREATE_PROPERTY_SPACIFIACTION_VERIFIED_TABLE);
        db.execSQL(CREATE_Warehouse_Rent_TABLE);
        db.execSQL(CREATE_Warehouse_part_avail_TABLE);
        db.execSQL(CREATE_Warehouse_ready2_move_TABLE);
        db.execSQL(CREATE_service_workspace_TABLE);


    }

//LAND
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAND_OWNER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LAND_MANAGER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LAND_BROKER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LAND_PART_AVA);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_OFFICE_SPACE_OWNER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_OFFICE_SPACE_MANAGER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_OFFICE_SPACE_BROKER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_OFFICE_SPACE_PART_AVAILABLE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_OFFICE_SPACE_RENTED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Warehouse_Mngrdetail);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Warehouse_Owner_detail);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Warehouse_Broker_detail);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_PROPERTY_SPECIFICATION_VERIFIED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_warehouse_rent);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_warehouse_part_avail);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_warehouse_ready2_move);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Service_office_workspace);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Service_office_Manager);
        onCreate(db);

    }
    public void addownerland(String own_cm,String own_cpn,String own_num,String own_emaila){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAND_OWNER_COMPNAY_NM,own_cm);
        values.put(KEY_LAND_OWNER_CONT_NM,own_cpn);
        values.put(KEY_LAND_OWNER_NUM,own_num);
        values.put(KEY_LAND_OWNER_EML,own_emaila);
        db.insert(TABLE_LAND_OWNER,null,values);
        db.close();
    }

    public void addmanagerland(String mngr_cn,String mngr_nm,String mngr_num,String mngr_em){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAND_MANAGER_CN,mngr_cn);
        values.put(KEY_LAND_MANAGER_NM,mngr_nm);
        values.put(KEY_LAND_MANAGER_NUM,mngr_num);
        values.put(KEY_LAND_MANAGER_EMAIL,mngr_em);
        db.insert(TABLE_LAND_MANAGER,null,values);
        db.close();
    }


    public void addbrokerland(String brok_cn,String brok_nm,String brok_num,String brok_em,String brok_fees,String brok_agfees){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAND_BROKER_CN,brok_cn);
        values.put(KEY_LAND_BROKER_NM,brok_nm);
        values.put(KEY_LAND_BROKER_NUM,brok_num);
        values.put(KEY_LAND_BROKER_EMAIL,brok_em);
        values.put(KEY_LAND_BROKER_FEES,brok_fees);
        values.put(KEY_LAND_BROKER_AGFEES,brok_agfees);
        db.insert(TABLE_LAND_BROKER,null,values);
        db.close();
    }

    public void addwarehouserent(String comp_name, String per_name, String desig,String num,String email,String leas_exp,String rent,String rentby) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_warehouse_rent_compname, comp_name);
        values.put(KEY_warehouse_rent_personname, per_name);
        values.put(KEY_warehouse_rent_desig, desig);
        values.put(KEY_warehouse_rent_num, num);
        values.put(KEY_warehouse_rent_email, email);
        values.put(KEY_warehouse_rent_leas_expir,leas_exp);
        values.put(KEY_warehouse_rent_rent,rent);
        values.put(KEY_warehouse_rent_rentbygrpapl,rentby);
        db.insert(TABLE_warehouse_rent,null, values);
        db.close();
    }

    public void addwarehouse_partavail(String space_avail,String comp_name, String per_name, String desig,String num,String email,String area_avail,String floor,String leas_exp,String rent,String rentby) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_warehouse_space_avail, space_avail);
        values.put(KEY_warehouse_part_compname, comp_name);
        values.put(KEY_warehouse_part_personname, per_name);
        values.put(KEY_warehouse_part_desig, desig);
        values.put(KEY_warehouse_part_num, num);
        values.put(KEY_warehouse_part_email, email);
        values.put(KEY_warehouse_part_area_avail,area_avail);
        values.put(KEY_warehouse_part_floor,floor);
        values.put(KEY_warehouse_part_leas_expir,leas_exp);
        values.put(KEY_warehouse_part_rent,rent);
        values.put(KEY_warehouse_part_rentbygrpapl,rentby);
        db.insert(TABLE_warehouse_part_avail,null, values);
        db.close();
    }

    public void addwarehouse_ready2_move(String ware_pic,String road, String carpark, String truckpark,String ec_pro,String pow_back,
                                         String floor,String lease_area, String cov_shed, String shed_height,String clear_height,String center_height,
                                         String num_pill,String floore, String num_exc_dox, String num_shr_dox,String lift,String war_pic,
                                         String war_lay,String lock, String year_cons, String tenant)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ready_Warehouse_Picture, ware_pic);
        values.put(KEY_ready_Road_Width, road);
        values.put(KEY_ready_Number_Car_Parks, carpark);
        values.put(KEY_ready_Number_Truck_Parks, truckpark);
        values.put(KEY_ready_Electricity_Provision, ec_pro);
        values.put(KEY_ready_Power_Backup, pow_back);
        values.put(KEY_ready_Floor, floor);
        values.put(KEY_ready_Leasable_Area, lease_area);
        values.put(KEY_ready_Covered_Shed_dim, cov_shed);
        values.put(KEY_ready_Shed_Height, shed_height);
        values.put(KEY_ready_Clear_Height, clear_height);
        values.put(KEY_ready_Centre_Height, center_height);
        values.put(KEY_ready_Number_Pillars, num_pill);
        values.put(KEY_ready_Flooring, floore);
        values.put(KEY_ready_Number_Exclusive_Docks, num_exc_dox);
        values.put(KEY_ready_Number_Shared_Dock, num_shr_dox);
        values.put(KEY_ready_Lift, lift);
        values.put(KEY_ready_Warehouse_Pic, war_pic);
        values.put(KEY_ready_Warehouse_Layout, war_lay);
        values.put(KEY_ready_Lockin, lock);
        values.put(KEY_ready_Year_Construction, year_cons);
        values.put(KEY_ready_Year_Previous_Tenant, tenant);
        db.insert(TABLE_warehouse_ready2_move,null, values);
        db.close();
    }



    public void addlandpartava(String lnd_part_ava_cn,String lnd_part_ava_cpn,String lnd_part_ava_num,String lnd_part_ava_em,String lnd_part_ava_areasold,String lnd_part_ava_salesprice,String lnd_part_ava_soldgrpapl){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAND_PART_AVA_CN,lnd_part_ava_cn);
        values.put(KEY_LAND_PART_AVA_CPN,lnd_part_ava_cpn);
        values.put(KEY_LAND_PART_AVA_NUM,lnd_part_ava_num);
        values.put(KEY_LAND_PART_AVA_EMAIL,lnd_part_ava_em);
        values.put(KEY_LAND_PART_AVA_AREA_SOLD,lnd_part_ava_areasold);
        values.put(KEY_LAND_PART_AVA_SALES_PRICE,lnd_part_ava_salesprice);
        values.put(KEY_LAND_PART_AVA_SOLD_GRPAPL,lnd_part_ava_soldgrpapl);
        db.insert(TABLE_LAND_PART_AVA,null,values);
        db.close();
    }



    public void addpropertyspecificationverified(String ps_v_nm,String ps_v_desig,String ps_v_em,String ps_v_mnum,String ps_v_fx_num){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_NM,ps_v_nm);
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_DESIG,ps_v_desig);
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_EM,ps_v_em);
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_MNUM,ps_v_mnum);
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_FIXED_NUM,ps_v_fx_num);
        db.insert(TABLE_PROPERTY_SPECIFICATION_VERIFIED,null,values);
        db.close();
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ gat data in array
    public void addMngr(String mng_nm, String num, String email_add) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Mngr_nm, mng_nm);
        values.put(KEY_Mngr_num, num);
        values.put(KEY_Mngr_emial, email_add);
        db.insert(TABLE_Warehouse_Mngrdetail, null, values);
        db.close();
    }


    public void addowner(String own_nm, String own_num, String own_email) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_Owner_nm, own_nm);
            values.put(KEY_Owner_num, own_num);
            values.put(KEY_Owner_emial, own_email);
            db.insert(TABLE_Warehouse_Owner_detail, null, values);
            db.close();
        }catch (Exception e){
            Log.e("Sqlite Error", ""+e);
        }
    }
    public void addbroker(String broker_nm, String broker_num, String broker_email,String broker_fee,String broker_agreefee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Broker_nm, broker_nm);
        values.put(KEY_Broker_num, broker_num);
        values.put(KEY_Broker_emial, broker_email);
        values.put(KEY_Broker_fee, broker_fee);
        values.put(KEY_Broker_agreefee, broker_agreefee);
        db.insert(TABLE_Warehouse_Broker_detail, null, values);
        db.close();
    }

    //============office space
    public void addservice_manager(String servic_nm, String service_desig,String service_num, String service_email,String service_fee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Service_Name, servic_nm);
        values.put(KEY_Service_Designation, service_desig);
        values.put(KEY_Service_Num, service_num);
        values.put(KEY_Service_email, service_email);
        values.put(KEY_Service_fees, service_fee);
        db.insert(TABLE_Service_office_Manager, null, values);
        db.close();
    }

    public void addservice_workspace(String servic_nm, String service_desig,String service_num, String service_email,String service_fee,String service_normal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_servic_workspace_type, servic_nm);
        values.put(KEY_servic_wrkspc_work_size, service_desig);
        values.put(KEY_servic_wrkspc_setign_cap, service_num);
        values.put(KEY_servic_wrkspc_quoted_rent, service_email);
        values.put(KEY_servic_wrkspc_quoted_rent_seat, service_fee);
        values.put(KEY_servic_wrkspc_chrg_byond_normal, service_normal);
        db.insert(TABLE_Service_office_workspace, null, values);
        db.close();
    }

///OFFICE SPACE
    public void addownerofficespace(String os_cm,String os_cpn,String os_num,String os_emaila){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACEOWNER_COMPNAY_NM,os_cm);
        values.put(KEY_OFFICE_SPACE_OWNER_CONT_NM,os_cpn);
        values.put(KEY_OFFICE_SPACE_OWNER_NUM,os_num);
        values.put(KEY_OFFICE_SPACE_OWNER_EML,os_emaila);
        db.insert(TABLE_OFFICE_SPACE_OWNER,null,values);
        db.close();
    }



    public void addmanagerofficespace(String mngrcnn, String os_mngr_nm, String os_mngr_num, String os_mngr_em){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_MANAGER_NM,os_mngr_nm);
        values.put(KEY_OFFICE_SPACE_MANAGER_NUM,os_mngr_num);
        values.put(KEY_OFFICE_SPACE_MANAGER_EMAIL,os_mngr_em);
        db.insert(TABLE_OFFICE_SPACE_MANAGER,null,values);
        db.close();
    }


    public void addbrokerofficespace(String brokcn, String os_brok_nm, String os_brok_num, String os_brok_em, String os_brok_fees, String os_brok_agfees){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_BROKER_NM,os_brok_nm);
        values.put(KEY_OFFICE_SPACE_BROKER_NUM,os_brok_num);
        values.put(KEY_OFFICE_SPACE_BROKER_EMAIL,os_brok_em);
        values.put(KEY_OFFICE_SPACE_BROKER_FEES,os_brok_fees);
        values.put(KEY_OFFICE_SPACE_BROKER_AGFEES,os_brok_agfees);
        db.insert(TABLE_OFFICE_SPACE_BROKER,null,values);
        db.close();
    }

    public void addofficespacepartava(String os_part_ava_space_available,String os_part_ava_cn,String os_part_ava_cpn,String os_part_ava_desi,String os_part_ava_num,String os_part_ava_em,String os_part_ava_area_rent,
                                      String os_part_ava_floor,String os_part_ava_space_ava, String os_part_ava_loc_in_ex ,String os_part_ava_leas_exp,String os_part_ava_rent,String os_part_ava_rent_esc,String os_part_ava_rent_esc_perio,
                                      String os_part_ava_rented_by_grpapl){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVAILABLE,os_part_ava_space_available);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_COM_NM,os_part_ava_cn);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_PER_NM,os_part_ava_cpn);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_DESIG,os_part_ava_desi);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_NUM,os_part_ava_num);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_EM,os_part_ava_em);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_AREA_RENTAD,os_part_ava_area_rent);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_FLOOR,os_part_ava_floor);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVA,os_part_ava_space_ava);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_LOC_IN_EXPIRY,os_part_ava_loc_in_ex);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_LEASE_EXPIREY,os_part_ava_leas_exp);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENT,os_part_ava_rent);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC,os_part_ava_rent_esc);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC_PERIOD,os_part_ava_rent_esc_perio);
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENTED_BY_GRPAPL,os_part_ava_rented_by_grpapl);
        db.insert(TABLE_OFFICE_SPACE_PART_AVAILABLE,null,values);
        db.close();
    }

    public void addofficespacerented(String os_rented_cn,String os_rented_cpn,String os_rented_desi,String os_rented_num,String os_rented_em,
                                     String os_rented_loc_in_ex ,String os_rented_leas_exp,String os_rented_rent,String os_rented_rent_esc,String os_rented_rent_esc_perio,
                                     String os_rented_rented_by_grpapl){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_RENTED_COM_NM,os_rented_cn);
        values.put(KEY_OFFICE_SPACE_RENETD_CONT_PER_NM,os_rented_cpn);
        values.put(KEY_OFFICE_SPACE_RENTED_DESIG,os_rented_desi);
        values.put(KEY_OFFICE_SPACE_RENETD_CONT_NUM,os_rented_num);
        values.put(KEY_OFFICE_SPACE_RENTED_EM,os_rented_em);
        values.put(KEY_OFFICE_SPACE_RENETD_LOC_IN_EXPIRY,os_rented_loc_in_ex);
        values.put(KEY_OFFICE_SPACE_RENETD_LEASE_EXPIREY,os_rented_leas_exp);
        values.put(KEY_OFFICE_SPACE_RENETD_RENT,os_rented_rent);
        values.put(KEY_OFFICE_SPACE_RENETD_RENT_ESC,os_rented_rent_esc);
        values.put(KEY_OFFICE_SPACE_RENETD_RENT_ESC_PERIOD,os_rented_rent_esc_perio);
        values.put(KEY_OFFICE_SPACE_RENETD_RENTED_BY_GRPAPL,os_rented_rented_by_grpapl);
        db.insert(TABLE_OFFICE_SPACE_RENTED,null,values);
        db.close();
    }

    //LAND
    public void addowner(ArrayList<LandOwner> landOwners) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAND_OWNER_ID, landOwners.get(0).getId());
        values.put(KEY_LAND_OWNER_COMPNAY_NM, landOwners.get(0).getlnd_own_comp_name());
        values.put(KEY_LAND_OWNER_CONT_NM, landOwners.get(0).getland_own_cont_nm());
        values.put(KEY_LAND_OWNER_NUM, landOwners.get(0).getland_owner_num());
        values.put(KEY_LAND_OWNER_EML, landOwners.get(0).getland_own_email());
        db.insert(TABLE_LAND_OWNER, null, values);
        db.close();
    }



    public void addmanager(ArrayList<LandManager> landManagers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LAND_MANAGER_ID, landManagers.get(0).getId());
        values.put(KEY_LAND_MANAGER_CN,landManagers.get(0).getLnd_mng_cn());
        values.put(KEY_LAND_MANAGER_NM, landManagers.get(0).getLnd_mng_nm());
        values.put(KEY_LAND_MANAGER_NUM, landManagers.get(0).getLnd_mng_num());
        values.put(KEY_LAND_MANAGER_EMAIL, landManagers.get(0).getLnd_mng_email());
        db.insert(TABLE_LAND_MANAGER, null, values);
        db.close();
    }


    public void addbroker(ArrayList<LandBroker> landBrokers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAND_BROKER_ID, landBrokers.get(0).getId());
        values.put(KEY_LAND_BROKER_CN,landBrokers.get(0).getLnd_brok_cn());
        values.put(KEY_LAND_BROKER_NM, landBrokers.get(0).getLnd_brok_nm());
        values.put(KEY_LAND_BROKER_NUM, landBrokers.get(0).getLnd_brok_num());
        values.put(KEY_LAND_BROKER_EMAIL, landBrokers.get(0).getLnd_brok_email());
        values.put(KEY_LAND_BROKER_FEES, landBrokers.get(0).getLnd_brok_fees());
        values.put(KEY_LAND_BROKER_AGFEES, landBrokers.get(0).getLnd_brok_aagfees());
        db.insert(TABLE_LAND_BROKER, null, values);
        db.close();
    }

    public void addlandpartava(ArrayList<Landpartavailable> landpartavailables) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LAND_PART_AVA_ID, landpartavailables.get(0).getId());
        values.put(KEY_LAND_PART_AVA_CN,landpartavailables.get(0).getLnd_part_ava_cn());
        values.put(KEY_LAND_PART_AVA_CPN, landpartavailables.get(0).getLnd_part_ava_cpn());
        values.put(KEY_LAND_PART_AVA_NUM, landpartavailables.get(0).getLnd_part_ava_num());
        values.put(KEY_LAND_PART_AVA_EMAIL, landpartavailables.get(0).getLnd_part_ava_email());
        values.put(KEY_LAND_PART_AVA_AREA_SOLD, landpartavailables.get(0).getLnd_part_ava_area_sold());
        values.put(KEY_LAND_PART_AVA_SALES_PRICE, landpartavailables.get(0).getLnd_part_ava_salesprice());
        values.put(KEY_LAND_PART_AVA_SOLD_GRPAPL, landpartavailables.get(0).getLnd_part_ava_sold_by_grpapl());
        db.insert(TABLE_LAND_PART_AVA, null, values);
        db.close();
    }


    public void addpropertyverified(ArrayList<Property_specification_verified> property_specification_verifieds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_ID, property_specification_verifieds.get(0).getId());
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_NM, property_specification_verifieds.get(0).getProperty_verfied_nm());
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_DESIG, property_specification_verifieds.get(0).getProperty_verfied_desg());
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_EM, property_specification_verifieds.get(0).getProperty_verfied_em());
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_MNUM, property_specification_verifieds.get(0).getProperty_verfied_mnum());
        values.put(KEY_PROPERTY_SPECIFICATION_VERIFIED_FIXED_NUM, property_specification_verifieds.get(0).getProperty_fix_lnum());
        db.insert(TABLE_PROPERTY_SPECIFICATION_VERIFIED, null, values);
        db.close();
    }

//OFFICE SPACE

    public void addownerofficespace(ArrayList<OfficespaceOwner> officespaceOwners) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_OWNER_ID, officespaceOwners.get(0).getId());
        values.put(KEY_OFFICE_SPACEOWNER_COMPNAY_NM, officespaceOwners.get(0).getOs_own_comp_name());
        values.put(KEY_OFFICE_SPACE_OWNER_CONT_NM, officespaceOwners.get(0).getOs_own_cont_nm());
        values.put(KEY_OFFICE_SPACE_OWNER_NUM, officespaceOwners.get(0).getOs_owner_num());
        values.put(KEY_OFFICE_SPACE_OWNER_EML, officespaceOwners.get(0).getOs_own_email());
        db.insert(TABLE_OFFICE_SPACE_OWNER, null, values);
        db.close();
    }


    public void addmanagerofficespace(ArrayList<OfficespaceManager> officespaceManagers) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_MANAGER_ID, officespaceManagers.get(0).getId());
        values.put(KEY_OFFICE_SPACE_MANAGER_NM, officespaceManagers.get(0).getOs_mng_nm());
        values.put(KEY_OFFICE_SPACE_MANAGER_NUM, officespaceManagers.get(0).getOs_mng_num());
        values.put(KEY_OFFICE_SPACE_MANAGER_EMAIL, officespaceManagers.get(0).getOs_mng_email());
        db.insert(TABLE_OFFICE_SPACE_MANAGER, null, values);
        db.close();
    }


    public void addbrokerofficespace(ArrayList<OfficespaceBroker> officespaceBrokers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_BROKER_ID, officespaceBrokers.get(0).getId());
        values.put(KEY_OFFICE_SPACE_BROKER_NM, officespaceBrokers.get(0).getOs_brok_nm());
        values.put(KEY_OFFICE_SPACE_BROKER_NUM, officespaceBrokers.get(0).getOs_brok_num());
        values.put(KEY_OFFICE_SPACE_BROKER_EMAIL, officespaceBrokers.get(0).getOs_brok_email());
        values.put(KEY_OFFICE_SPACE_BROKER_FEES, officespaceBrokers.get(0).getOs_brrrrok_fees());
        values.put(KEY_OFFICE_SPACE_BROKER_AGFEES, officespaceBrokers.get(0).getOs_brok_aagfees());
        db.insert(TABLE_OFFICE_SPACE_BROKER, null, values);
        db.close();
    }


    public void addofficespace_part_ava(ArrayList<Office_space_part_available> office_space_part_availables) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_ID, office_space_part_availables.get(0).getId());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVAILABLE,office_space_part_availables.get(0).getOs_part_ava_space_ava());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_COM_NM, office_space_part_availables.get(0).getOs_part_ava_com_nm());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_PER_NM, office_space_part_availables.get(0).getOs_part_ava_con_per_nm());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_DESIG, office_space_part_availables.get(0).getOs_part_ava_desig());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_CONT_NUM, office_space_part_availables.get(0).getOs_part_ava_con_num());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_EM, office_space_part_availables.get(0).getOs_part_ava_em());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_AREA_RENTAD, office_space_part_availables.get(0).getOs_part_ava_area_rentad());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_FLOOR, office_space_part_availables.get(0).getOs_part_ava_floor());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_SPACE_AVA, office_space_part_availables.get(0).getOs_part_ava_spac_ava());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_LOC_IN_EXPIRY, office_space_part_availables.get(0).getOs_part_ava_loc_in_expiry());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_LEASE_EXPIREY, office_space_part_availables.get(0).getOs_part_ava_lease_expiry());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENT, office_space_part_availables.get(0).getOs_part_ava_rent());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC, office_space_part_availables.get(0).getOs_part_ava_rent_esc());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENT_ESC_PERIOD, office_space_part_availables.get(0).getOs_part_ava_rent_esc_period());
        values.put(KEY_OFFICE_SPACE_PART_AVAILABLE_RENTED_BY_GRPAPL, office_space_part_availables.get(0).getOs_part_ava_rented_by_grpapl());
        db.insert(TABLE_OFFICE_SPACE_PART_AVAILABLE, null, values);
        db.close();
    }

    public void addofficespace_rented(ArrayList<Office_space_rented>office_space_renteds) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OFFICE_SPACE_RENTED_ID, office_space_renteds.get(0).getId());
        values.put(KEY_OFFICE_SPACE_RENTED_COM_NM, office_space_renteds.get(0).getOs_rented_com_nm());
        values.put(KEY_OFFICE_SPACE_RENETD_CONT_PER_NM, office_space_renteds.get(0).getOs_rented_con_per_nm());
        values.put(KEY_OFFICE_SPACE_RENTED_DESIG, office_space_renteds.get(0).getOs_rented_desig());
        values.put(KEY_OFFICE_SPACE_RENETD_CONT_NUM, office_space_renteds.get(0).getOs_rented_con_num());
        values.put(KEY_OFFICE_SPACE_RENTED_EM, office_space_renteds.get(0).getOs_rented_em());
        values.put(KEY_OFFICE_SPACE_RENETD_LOC_IN_EXPIRY, office_space_renteds.get(0).getOs_rented_in_expiry());
        values.put(KEY_OFFICE_SPACE_RENETD_LEASE_EXPIREY, office_space_renteds.get(0).getOs_rented_lease_expiry());
        values.put(KEY_OFFICE_SPACE_RENETD_RENT, office_space_renteds.get(0).getOs_rented_rent());
        values.put(KEY_OFFICE_SPACE_RENETD_RENT_ESC, office_space_renteds.get(0).getOs_rented_rent_esc());
        values.put(KEY_OFFICE_SPACE_RENETD_RENT_ESC_PERIOD, office_space_renteds.get(0).getOs_rented_esc_period());
        values.put(KEY_OFFICE_SPACE_RENETD_RENTED_BY_GRPAPL, office_space_renteds.get(0).getOs_rented_by_grpapl());
        db.insert(TABLE_OFFICE_SPACE_RENTED, null, values);
        db.close();
    }



    //LAND



    public  ArrayList<LandOwner>getlandowner(){
        ArrayList<LandOwner>land_owner_details = new ArrayList<LandOwner>();
        String selectQuery = "SELECT *FROM " +TABLE_LAND_OWNER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                land_owner_details.add(new LandOwner(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return land_owner_details;
    }

    public  ArrayList<LandManager>getlandmanager(){
        ArrayList<LandManager>land_manager_details = new ArrayList<LandManager>();
        String selectQuery = "SELECT *FROM " +TABLE_LAND_MANAGER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                land_manager_details.add(new LandManager(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return land_manager_details;
    }

    public  ArrayList<LandBroker>getlandbroker(){
        ArrayList<LandBroker>land_broker_details = new ArrayList<LandBroker>();
        String selectQuery = "SELECT *FROM " +TABLE_LAND_BROKER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                land_broker_details.add(new LandBroker(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return land_broker_details;
    }


    public  ArrayList<Landpartavailable>getlandpartavailable(){
        ArrayList<Landpartavailable>land_part_ava_details = new ArrayList<Landpartavailable>();
        String selectQuery = "SELECT *FROM " +TABLE_LAND_PART_AVA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                land_part_ava_details.add(new Landpartavailable(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7)));
            }while (cursor.moveToNext());
        }
        return land_part_ava_details;
    }


    public  ArrayList<Property_specification_verified>getpropertyspecificationverified(){
        ArrayList<Property_specification_verified>property_specification_verified_details = new ArrayList<Property_specification_verified>();
        String selectQuery = "SELECT *FROM " +TABLE_PROPERTY_SPECIFICATION_VERIFIED;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                property_specification_verified_details.add(new Property_specification_verified(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return property_specification_verified_details;
    }

    //OFFICE SPACE


    public  ArrayList<OfficespaceOwner>getofficespaceowner(){
        ArrayList<OfficespaceOwner>office_space_owner_details = new ArrayList<OfficespaceOwner>();
        String selectQuery = "SELECT *FROM " +TABLE_LAND_BROKER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                office_space_owner_details.add(new OfficespaceOwner(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return office_space_owner_details;
    }

    public  ArrayList<OfficespaceManager>getofficespacemanager(){
        ArrayList<OfficespaceManager>office_space_manager_details = new ArrayList<OfficespaceManager>();
        String selectQuery = "SELECT *FROM " +TABLE_LAND_MANAGER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                office_space_manager_details.add(new OfficespaceManager(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return office_space_manager_details;
    }
    public  ArrayList<OfficespaceBroker>getofficespacebroker(){
        ArrayList<OfficespaceBroker>office_space_broker_details = new ArrayList<OfficespaceBroker>();
        String selectQuery = "SELECT *FROM " +TABLE_LAND_BROKER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                office_space_broker_details.add(new OfficespaceBroker(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }while (cursor.moveToNext());
        }
        return office_space_broker_details;
    }

    public  ArrayList<Office_space_part_available>getofficespacepartava(){
        ArrayList<Office_space_part_available>office_space_part_availables_deatails = new ArrayList<Office_space_part_available>();
        String selectQuery = "SELECT *FROM " +TABLE_OFFICE_SPACE_PART_AVAILABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                office_space_part_availables_deatails.add(new Office_space_part_available(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                        cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),
                        cursor.getString(13),cursor.getString(14),cursor.getString(15)));
            }while (cursor.moveToNext());
        }
        return office_space_part_availables_deatails;
    }
    public  ArrayList<Office_space_rented>getofficespacerented(){
        ArrayList<Office_space_rented>office_space_rented_deatails = new ArrayList<Office_space_rented>();
        String selectQuery = "SELECT *FROM " +TABLE_OFFICE_SPACE_RENTED;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do {
                office_space_rented_deatails.add(new Office_space_rented(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                        cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11)));
            }while (cursor.moveToNext());
        }
        return office_space_rented_deatails;
    }
    public ArrayList<warehouse_mngr_detail> getmanager_detail(){
        ArrayList<warehouse_mngr_detail> mngr_details = new ArrayList<warehouse_mngr_detail>();
        String selectQuery = "SELECT  * FROM " + TABLE_Warehouse_Mngrdetail;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                mngr_details.add(new warehouse_mngr_detail(cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return mngr_details;
    }

    public ArrayList<warehouse_owner_detail> getowner_detail(){
        ArrayList<warehouse_owner_detail> owner_details = new ArrayList<warehouse_owner_detail>();
        String selectQuery = "SELECT  * FROM " + TABLE_Warehouse_Owner_detail;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                owner_details.add(new warehouse_owner_detail(""+cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return owner_details;
    }

    public ArrayList<warehouse_broker_detail> get_broker_detail(){
        ArrayList<warehouse_broker_detail> brokerDetails= new ArrayList<warehouse_broker_detail>();
        String selectQuery = "SELECT  * FROM " + TABLE_Warehouse_Broker_detail;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                brokerDetails.add(new warehouse_broker_detail(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return brokerDetails;
    }
    public ArrayList<warehouse_rent> get_warehouse_rent_detail(){
        ArrayList<warehouse_rent> warehouseRents= new ArrayList<warehouse_rent>();
        String selectQuery = "SELECT  * FROM " + TABLE_warehouse_rent;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                warehouseRents.add(new warehouse_rent(""+cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
            } while (cursor.moveToNext());
        }
        return warehouseRents;
    }

    public ArrayList<warehouse_part_avail> get_warehouse_part_detail(){
        ArrayList<warehouse_part_avail> warehousepart= new ArrayList<warehouse_part_avail>();
        String selectQuery = "SELECT  * FROM " + TABLE_warehouse_part_avail;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                warehousepart.add(new warehouse_part_avail(""+cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),
                        cursor.getString(9),cursor.getString(10),cursor.getString(11)));
            } while (cursor.moveToNext());
        }
        return warehousepart;
    }

    public ArrayList<wrehouse_ready_move> get_warehouse_ready2_detail(){
        ArrayList<wrehouse_ready_move> warehouse_ready= new ArrayList<wrehouse_ready_move>();
        String selectQuery = "SELECT  * FROM " + TABLE_warehouse_ready2_move;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                warehouse_ready.add(new wrehouse_ready_move(""+cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),
                        cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),
                        cursor.getString(14),cursor.getString(15),cursor.getString(16),cursor.getString(17),cursor.getString(18),
                        cursor.getString(19),cursor.getString(20),cursor.getString(21),cursor.getString(22)));
            } while (cursor.moveToNext());
        }
        return warehouse_ready;
    }

    public ArrayList<service_offic_manager> getservice_office_manager_detail(){
        ArrayList<service_offic_manager> service_manager = new ArrayList<service_offic_manager>();
        String selectQuery = "SELECT  * FROM " + TABLE_Service_office_Manager;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                service_manager.add(new service_offic_manager(cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return service_manager;
    }

    public ArrayList<service_off_workspace> getservice_office_workspace_detail(){
        ArrayList<service_off_workspace> service_workspace = new ArrayList<service_off_workspace>();
        String selectQuery = "SELECT  * FROM " + TABLE_Service_office_workspace;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                service_workspace.add(new service_off_workspace(cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        return service_workspace;
    }


}
