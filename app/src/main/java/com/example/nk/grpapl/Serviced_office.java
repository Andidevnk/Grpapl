package com.example.nk.grpapl;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nk.grpapl.Models.BaseEntity;
import com.example.nk.grpapl.Models.Cities;
import com.example.nk.grpapl.Models.States;
import com.example.nk.grpapl.Models.service_off_workspace;
import com.example.nk.grpapl.Models.service_offic_manager;
import com.example.nk.grpapl.SharedPreferences.SP;
import com.example.nk.grpapl.Url.ServerUrl;
import com.example.nk.grpapl.Utils.ConnectionDetector;
import com.example.nk.grpapl.Utils.GPSTracking;
import com.example.nk.grpapl.dialogs.CommonDialog;
import com.example.nk.grpapl.sqlite.Databse_Handler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serviced_office extends AppCompatActivity {
Spinner categoryspinner;
Spinner workstationspinner;
Spinner carparkingspinner;
Spinner states_spi,cities_spi;
EditText latti,longt;
SP sp;
    private RequestQueue requestQueue;
    String result;
    ScrollView sv;

    private JSONObject jsonObject;
    ArrayList<Uri> imagesUriList;
    ArrayList<String> encodedImageList;
    String imageURI;


    AppCompatActivity mActivity =Serviced_office.this;
    ProgressBar progressBar;
    public static String state_nm , p_id="";
    ConnectionDetector connectionDetector;
    CommonDialog cd;

   Databse_Handler mdatabase_handler;

    EditText m_name,m_desig,m_num,m_email,m_fees;

    EditText brand_name,legal_name,s_website,s_GSTIN,blng_address,buid_name,buld_add,buld_city,buld_state,buld_pin,s_latitude,s_longitude,NPTF,
            s_distance,tot_flor_ocup,tot_area,tot_num_workstation,num_of_cabin,num_of_meetroom,number_of_confroom,break_out_area,work_day,work_hour,
            workstation_size,sating_cap_afor_work,quoted_rent,quoted_rent_seat,charge_beyond_workday,beverags,intenet,telphone,s_intercom,s_cabins,
            meeting_room,conference_room,car_park_chrgin,getCar_park_chrgout,tmln_to_movein,one_time_setup,exit,agree_tenure,Lock_in,noticed_per,
            security_depo,rent_escalation,rent_escalation_per;
    Button m_button ,main_submit,buil_outside,buil_inside,ser_offic_interior,workspace,submit;

    RadioGroup rg_maintanance,rg_ec_charg,rg_ac,rg_hkeeping,rg_itsupport,rg_security,rg_mend_to_serv_lockin;

Button btninside,btnoutside,btninteriorinside;

ImageView insideimg1,insideimg2,insideimg3,insideimg4,insideimg5,outsideimg1,outsideimg2,outsideimg3,outsideimg4,outsideimg5,interiorimg1,interiorimg2,
    interiorimg3,interiorimg4,interiorimg5;

LinearLayout insidelayout,outsidelayout,interiorlayout;
CardView insidecardview,outsidecardview,interiorcardview;
    private static final int PICK_FROM_GALLERY = 1;
    private static final int PICK_FROM_GALLERY_1 = 2;
    private static final int PICK_FROM_GALLERY_2 = 3;
    String imageEncoded;
    List<String> imagesEncodedList;

    public static ArrayList<States> states = new ArrayList<>();
    public static ArrayList<String> states_nm = new ArrayList<>();
    public static ArrayList<Cities>  cities= new ArrayList<>();
    public static ArrayList<String> cities_nm = new ArrayList<>();
    ArrayList<service_offic_manager>service_manager =new ArrayList<service_offic_manager>();
    ArrayList<service_off_workspace>service_workspace =new ArrayList<service_off_workspace>();

    GPSTracking gpsTracking;
    private int REQUEST_FINE_LOCATION = 2000; /* 2 sec */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_serviced_office);
        mdatabase_handler=new Databse_Handler(this);
        connectionDetector = new ConnectionDetector(mActivity);
        cd=new CommonDialog(mActivity);
        sp = SP.getInstance();
        progressBar =(ProgressBar)findViewById(R.id.progressBar_serviced);


        jsonObject = new JSONObject();
        encodedImageList = new ArrayList<>();


        insideimg1 =(ImageView)findViewById(R.id.pic1);
        insideimg2 =(ImageView)findViewById(R.id.pic2);
        insideimg3 =(ImageView)findViewById(R.id.pic3);
        insideimg4 =(ImageView)findViewById(R.id.pic4);
        insideimg5 =(ImageView)findViewById(R.id.pic5);

        outsideimg1 =(ImageView)findViewById(R.id.pic11);
        outsideimg2 =(ImageView)findViewById(R.id.pic22);
        outsideimg3 =(ImageView)findViewById(R.id.pic33);
        outsideimg4 =(ImageView)findViewById(R.id.pic44);
        outsideimg5 =(ImageView)findViewById(R.id.pic55);

        interiorimg1 =(ImageView)findViewById(R.id.pic111);
        interiorimg2 =(ImageView)findViewById(R.id.pic222);
        interiorimg3 =(ImageView)findViewById(R.id.pic333);
        interiorimg4 =(ImageView)findViewById(R.id.pic444);
        interiorimg5 =(ImageView)findViewById(R.id.pic555);

        insidelayout =(LinearLayout)findViewById(R.id.insidelayoutserviced);
        outsidelayout =(LinearLayout)findViewById(R.id.ousidelayout);
        interiorlayout =(LinearLayout)findViewById(R.id.interiorlayout);

        insidecardview =(CardView)findViewById(R.id.picinsideserviced);
        outsidecardview =(CardView)findViewById(R.id.picoutsidetext);
        interiorcardview =(CardView)findViewById(R.id.picinteriortext);

        btninside =(Button)findViewById(R.id.Buildininside);
        btnoutside =(Button)findViewById(R.id.Buildingoutside);
        btninteriorinside =(Button)findViewById(R.id.ServicedOfficeInteriorInside);

        workstationspinner = (Spinner)findViewById(R.id.Workstationtype);
        categoryspinner = (Spinner)findViewById(R.id.Catogoryservicedoffice);
        carparkingspinner = (Spinner)findViewById(R.id.carparking);
        states_spi = (Spinner)findViewById(R.id.spinner_staes_serviceoffic);
        cities_spi = (Spinner)findViewById(R.id.spinner_cities_serviceoffic);

        m_name=(EditText)findViewById(R.id.managernameservicedoffice);
        m_desig=(EditText)findViewById(R.id.Designationmanagerservicedoffice);
        m_num=(EditText)findViewById(R.id.numbermanagerservicedoffice);
        m_email=(EditText)findViewById(R.id.emailmanagerservicedoffice);
        m_fees=(EditText)findViewById(R.id.AgreedFeeinoftheTotalOutFlow);
        //=============================================================
        brand_name=(EditText)findViewById(R.id.BrandName);
        legal_name=(EditText)findViewById(R.id.LegalName);
        s_website=(EditText)findViewById(R.id.Website);
        s_GSTIN=(EditText)findViewById(R.id.GSTIN);
        blng_address=(EditText)findViewById(R.id.BillingAddress);
        buid_name=(EditText)findViewById(R.id.BuildingNameservicedoffice);
        buld_add=(EditText)findViewById(R.id.Addressservicedoffice);
       // buld_city=(EditText)findViewById(R.id.Cityservicedoffice);
        buld_pin=(EditText)findViewById(R.id.PINservicedoffice);
        s_latitude=(EditText)findViewById(R.id.latitude_serviced);//
        s_longitude=(EditText)findViewById(R.id.longitute_service);//
        NPTF=(EditText)findViewById(R.id.NearestPublicTransportationFacility);
        s_distance=(EditText)findViewById(R.id.Distance);
        tot_flor_ocup=(EditText)findViewById(R.id.TotalNumberofFloorsOccupied);
        tot_area=(EditText)findViewById(R.id.TotalAreainsq);
        tot_num_workstation=(EditText)findViewById(R.id.TotalNumberofWorkstations);
        num_of_cabin=(EditText)findViewById(R.id.NumberofCabin);
        num_of_meetroom=(EditText)findViewById(R.id.NumberofMeetingRoom);
        number_of_confroom=(EditText)findViewById(R.id.NumberofConferenceRoom);
        break_out_area=(EditText)findViewById(R.id.BreakoutArea);
        work_day=(EditText)findViewById(R.id.WorkingDays);
        work_hour=(EditText)findViewById(R.id.WorkingHours);
        beverags=(EditText)findViewById(R.id.Beverages);
        intenet=(EditText)findViewById(R.id.Internet);
        telphone=(EditText)findViewById(R.id.Telephone);
        s_intercom=(EditText)findViewById(R.id.Intercom);
        s_cabins=(EditText)findViewById(R.id.Cabins);
        meeting_room=(EditText)findViewById(R.id.MeetingRoom);
        conference_room=(EditText)findViewById(R.id.ConferenceRoomserviceoffice);
        car_park_chrgin=(EditText)findViewById(R.id.CarParkingChargesInsidetheBuilding);
        getCar_park_chrgout=(EditText)findViewById(R.id.CarParkingChargesOutsidetheBuilding);
        tmln_to_movein=(EditText)findViewById(R.id.TimelinetoMoveIN);
        one_time_setup=(EditText)findViewById(R.id.OneTimeSetupMoveINFeeNonRefundable);
        exit=(EditText)findViewById(R.id.ExitMoveOUTCost);
        agree_tenure=(EditText)findViewById(R.id.AgreementTenure);
        Lock_in=(EditText)findViewById(R.id.LockIN);
        noticed_per=(EditText)findViewById(R.id.NoticePeriod);
        security_depo=(EditText)findViewById(R.id.SecurityDeposit);
        rent_escalation=(EditText)findViewById(R.id.RentEscalation);
        rent_escalation_per=(EditText)findViewById(R.id.RentEscalationPeriod);

        //================================
        workstation_size=(EditText)findViewById(R.id.WorkstationSize);
        sating_cap_afor_work=(EditText)findViewById(R.id.SeatingCapacityfortheaforesaidworkstationsize);
        quoted_rent=(EditText)findViewById(R.id.QuotedRentservicedoffice);
        quoted_rent_seat=(EditText)findViewById(R.id.QuotedRentINRseatmonth);
        charge_beyond_workday=(EditText)findViewById(R.id.Chargesbeyondnormalworkingdays1);

        //===========================================button==================================
        workspace=(Button)findViewById(R.id.Workspace3);
        m_button=(Button)findViewById(R.id.manager3servicedoffice);
        main_submit=(Button)findViewById(R.id.button_service_submit);

      //  buil_outside=(Button)findViewById(R.id.BuildinOutside);
      //  buil_inside=(Button)findViewById(R.id.BuildingInside);
       // ser_offic_interior=(Button)findViewById(R.id.ServicedOfficeInteriorInside);

        //==============================================radio button==================================
        rg_maintanance =(RadioGroup)findViewById(R.id.radio_maintainance);
        rg_ec_charg =(RadioGroup)findViewById(R.id.radio_ec_charg);
        rg_ac =(RadioGroup)findViewById(R.id.radio_ac);
        rg_hkeeping =(RadioGroup)findViewById(R.id.radio_houskeeping);
        rg_itsupport =(RadioGroup)findViewById(R.id.radio_it_support);
        rg_security =(RadioGroup)findViewById(R.id.radio_security);
        rg_mend_to_serv_lockin =(RadioGroup)findViewById(R.id.radio_mandetry);

        //============================================== sqlite data base storage

        m_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (m_num.getText().toString().length() == 10)
                {
                }
                else
                {
                   m_num.setError("Enter correct number");
                }
            }
        });


        m_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (m_email.getText().toString().matches(emailPattern))
                {
                }
                else
                {
                    m_email.setError("Invalid E-mail");
                }

            }
        });


        workspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String var =  workstationspinner.getSelectedItem().toString();
                String var1 =  workstation_size.getText().toString();
                String var2 =   sating_cap_afor_work.getText().toString();
                String var3 =   quoted_rent.getText().toString();
                String var4 =   quoted_rent_seat.getText().toString();
                String var5 =   charge_beyond_workday.getText().toString();

                mdatabase_handler.addservice_workspace(var,var1,var2,var3,var4,var5);
                workstation_size.setText("");
                sating_cap_afor_work.setText("");
                quoted_rent.setText("");
                quoted_rent_seat.setText("");
                charge_beyond_workday.setText("");

            }
        });

        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String var = m_name.getText().toString();
                String var1 = m_desig.getText().toString();
                String var2 = m_num.getText().toString();
                String var3 = m_email.getText().toString();
                String var4 = m_fees.getText().toString();

                mdatabase_handler.addservice_manager(var,var1,var2,var3,var4);
                m_name.setText("");
                m_desig.setText("");
                m_num.setText("");
                m_email.setText("");
                m_fees.setText("");

            }
        });

        main_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload_image();
                submitdata();


            }
        });




       //==========================================================multiple images code
            btninside.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                try {
                    if (ActivityCompat.checkSelfPermission(Serviced_office.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Serviced_office.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_FROM_GALLERY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnoutside.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                try {
                    if (ActivityCompat.checkSelfPermission(Serviced_office.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Serviced_office.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_FROM_GALLERY_1);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btninteriorinside.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                try {
                    if (ActivityCompat.checkSelfPermission(Serviced_office.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Serviced_office.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_FROM_GALLERY_2);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        gpsTracking = new GPSTracking(this, Serviced_office.this);
        latti=(EditText)findViewById(R.id.latitude);
        longt=(EditText)findViewById(R.id.longitute);
        checkPermissions();


        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Serviced_office.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.category));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryspinner.setAdapter(myadapter);

        ArrayAdapter<String> mmyadapter = new ArrayAdapter<String>(Serviced_office.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Workstationtype));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workstationspinner.setAdapter(mmyadapter);

        ArrayAdapter<String> mmmyadapter = new ArrayAdapter<String>(Serviced_office.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.carparking));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carparkingspinner.setAdapter(mmmyadapter);

        gotoGetStates();

        states_spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    state_nm="";
                } else{
                    state_nm = states_nm.get(position).toString();
                    gotoGetCities();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracking.getLastLocation();
            double var= gpsTracking.getLatitude();
            double var1= gpsTracking.getLongitude();
          //  Toast.makeText(this,""+var +" "+var1,Toast.LENGTH_LONG).show();

            s_latitude.setText(""+var);
            s_longitude.setText(""+var1);

        } else {
            requestPermissions();
            return false;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_FROM_GALLERY);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;

            case PICK_FROM_GALLERY_1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_FROM_GALLERY_1);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;

            case PICK_FROM_GALLERY_2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_FROM_GALLERY_2);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    imagesEncodedList.add(imageEncoded);

                    imagesUriList = new ArrayList<Uri>();
                    encodedImageList.clear();

                    insideimg1.setImageURI(mImageUri);
                    insideimg1.setVisibility(View.VISIBLE);
                    insideimg2.setVisibility(View.GONE);
                    insideimg3.setVisibility(View.GONE);
                    insideimg4.setVisibility(View.GONE);
                    insideimg5.setVisibility(View.GONE);
                    insidelayout.setVisibility(View.VISIBLE);
                    insidecardview.setVisibility(View.VISIBLE);


                    imageURI  = cursor.getString(columnIndex);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                    String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    encodedImageList.add(encodedImage);
                    cursor.close();

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            insidelayout.setVisibility(View.VISIBLE);
                            insidecardview.setVisibility(View.VISIBLE);


                            if(mArrayUri.size()==1)
                            {
                                insideimg1.setImageURI(mArrayUri.get(0));
                                insideimg1.setVisibility(View.VISIBLE);
                                insideimg2.setVisibility(View.GONE);
                                insideimg3.setVisibility(View.GONE);
                                insideimg4.setVisibility(View.GONE);
                                insideimg5.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                insideimg1.setImageURI(mArrayUri.get(0));
                                insideimg2.setImageURI(mArrayUri.get(1));
                                insideimg1.setVisibility(View.VISIBLE);
                                insideimg2.setVisibility(View.VISIBLE);
                                insideimg3.setVisibility(View.GONE);
                                insideimg4.setVisibility(View.GONE);
                                insideimg5.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                insideimg1.setImageURI(mArrayUri.get(0));
                                insideimg2.setImageURI(mArrayUri.get(1));
                                insideimg3.setImageURI(mArrayUri.get(2));
                                insideimg1.setVisibility(View.VISIBLE);
                                insideimg2.setVisibility(View.VISIBLE);
                                insideimg3.setVisibility(View.VISIBLE);
                                insideimg4.setVisibility(View.GONE);
                                insideimg5.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==4)
                            {
                                insideimg1.setImageURI(mArrayUri.get(0));
                                insideimg2.setImageURI(mArrayUri.get(1));
                                insideimg3.setImageURI(mArrayUri.get(2));
                                insideimg4.setImageURI(mArrayUri.get(3));
                                insideimg1.setVisibility(View.VISIBLE);
                                insideimg2.setVisibility(View.VISIBLE);
                                insideimg3.setVisibility(View.VISIBLE);
                                insideimg4.setVisibility(View.VISIBLE);
                                insideimg5.setVisibility(View.GONE);

                            }

                            else if(mArrayUri.size()==5)
                            {
                                insideimg1.setImageURI(mArrayUri.get(0));
                                insideimg2.setImageURI(mArrayUri.get(1));
                                insideimg3.setImageURI(mArrayUri.get(2));
                                insideimg4.setImageURI(mArrayUri.get(3));
                                insideimg5.setImageURI(mArrayUri.get(4));
                                insideimg1.setVisibility(View.VISIBLE);
                                insideimg2.setVisibility(View.VISIBLE);
                                insideimg3.setVisibility(View.VISIBLE);
                                insideimg4.setVisibility(View.VISIBLE);
                                insideimg5.setVisibility(View.VISIBLE);

                            }
                            else
                            {

                                insideimg1.setVisibility(View.GONE);
                                insideimg2.setVisibility(View.GONE);
                                insideimg3.setVisibility(View.GONE);
                                insideimg4.setVisibility(View.GONE);
                                insideimg5.setVisibility(View.GONE);
                                insidelayout.setVisibility(View.GONE);
                                insidecardview.setVisibility(View.GONE);

                                Toast.makeText(this," You can  select only 5 images",Toast.LENGTH_LONG).show();
                            }
                            imageURI  = cursor.getString(columnIndex);
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                            encodedImageList.add(encodedImage);
                            cursor.close();
                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            }

            else if(requestCode == PICK_FROM_GALLERY_1 && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    imagesEncodedList.add(imageEncoded);

                    imagesUriList = new ArrayList<Uri>();
                    encodedImageList.clear();

                    outsideimg1.setImageURI(mImageUri);
                    outsideimg1.setVisibility(View.VISIBLE);
                    outsideimg2.setVisibility(View.GONE);
                    outsideimg3.setVisibility(View.GONE);
                    outsideimg4.setVisibility(View.GONE);
                    outsideimg5.setVisibility(View.GONE);
                    outsidecardview.setVisibility(View.VISIBLE);
                    outsidelayout.setVisibility(View.VISIBLE);


                    imageURI  = cursor.getString(columnIndex);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                    String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    encodedImageList.add(encodedImage);
                    cursor.close();


                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            outsidecardview.setVisibility(View.VISIBLE);
                            outsidelayout.setVisibility(View.VISIBLE);


                            if(mArrayUri.size()==1)
                            {
                                outsideimg1.setImageURI(mArrayUri.get(0));
                                outsideimg1.setVisibility(View.VISIBLE);
                                outsideimg2.setVisibility(View.GONE);
                                outsideimg3.setVisibility(View.GONE);
                                outsideimg4.setVisibility(View.GONE);
                                outsideimg5.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                outsideimg1.setImageURI(mArrayUri.get(0));
                                outsideimg2.setImageURI(mArrayUri.get(1));
                                outsideimg2.setVisibility(View.VISIBLE);
                                outsideimg1.setVisibility(View.VISIBLE);
                                outsideimg3.setVisibility(View.GONE);
                                outsideimg4.setVisibility(View.GONE);
                                outsideimg5.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                outsideimg1.setImageURI(mArrayUri.get(0));
                                outsideimg2.setImageURI(mArrayUri.get(1));
                                outsideimg3.setImageURI(mArrayUri.get(2));
                                outsideimg2.setVisibility(View.VISIBLE);
                                outsideimg1.setVisibility(View.VISIBLE);
                                outsideimg3.setVisibility(View.VISIBLE);
                                outsideimg4.setVisibility(View.GONE);
                                outsideimg5.setVisibility(View.GONE);

                            }

                            else if(mArrayUri.size()==4)
                            {
                                outsideimg1.setImageURI(mArrayUri.get(0));
                                outsideimg2.setImageURI(mArrayUri.get(1));
                                outsideimg3.setImageURI(mArrayUri.get(2));
                                outsideimg4.setImageURI(mArrayUri.get(3));
                                outsideimg2.setVisibility(View.VISIBLE);
                                outsideimg1.setVisibility(View.VISIBLE);
                                outsideimg3.setVisibility(View.VISIBLE);
                                outsideimg4.setVisibility(View.VISIBLE);
                                outsideimg5.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==5)
                            {
                                outsideimg1.setImageURI(mArrayUri.get(0));
                                outsideimg2.setImageURI(mArrayUri.get(1));
                                outsideimg3.setImageURI(mArrayUri.get(2));
                                outsideimg4.setImageURI(mArrayUri.get(3));
                                outsideimg5.setImageURI(mArrayUri.get(4));
                                outsideimg2.setVisibility(View.VISIBLE);
                                outsideimg1.setVisibility(View.VISIBLE);
                                outsideimg3.setVisibility(View.VISIBLE);
                                outsideimg4.setVisibility(View.VISIBLE);
                                outsideimg5.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                outsideimg2.setVisibility(View.GONE);
                                outsideimg1.setVisibility(View.GONE);
                                outsideimg3.setVisibility(View.GONE);
                                outsideimg4.setVisibility(View.GONE);
                                outsideimg5.setVisibility(View.GONE);
                                outsidecardview.setVisibility(View.GONE);
                                outsidelayout.setVisibility(View.GONE);
                                Toast.makeText(this," You can  select only 5 images",Toast.LENGTH_LONG).show();
                            }
                            imageURI  = cursor.getString(columnIndex);
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                            encodedImageList.add(encodedImage);
                            cursor.close();
                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            }
            else if(requestCode == PICK_FROM_GALLERY_2 && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                imagesEncodedList = new ArrayList<String>();
                if(data.getData()!=null){

                    Uri mImageUri=data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded  = cursor.getString(columnIndex);
                    imagesEncodedList.add(imageEncoded);

                    imagesUriList = new ArrayList<Uri>();
                    encodedImageList.clear();


                    interiorimg1.setImageURI(mImageUri);
                    interiorimg1.setVisibility(View.VISIBLE);
                    interiorimg2.setVisibility(View.GONE);
                    interiorimg3.setVisibility(View.GONE);
                    interiorimg4.setVisibility(View.GONE);
                    interiorimg5.setVisibility(View.GONE);
                    interiorlayout.setVisibility(View.VISIBLE);
                    interiorcardview.setVisibility(View.VISIBLE);

                    imageURI  = cursor.getString(columnIndex);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                    String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    encodedImageList.add(encodedImage);
                    cursor.close();

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {

                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                            // Get the cursor
                            Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                            // Move to first row
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            imageEncoded  = cursor.getString(columnIndex);
                            imagesEncodedList.add(imageEncoded);
                            interiorlayout.setVisibility(View.VISIBLE);
                            interiorcardview.setVisibility(View.VISIBLE);


                            if(mArrayUri.size()==1)
                            {
                                interiorimg1.setImageURI(mArrayUri.get(0));
                                interiorimg1.setVisibility(View.VISIBLE);
                                interiorimg2.setVisibility(View.GONE);
                                interiorimg3.setVisibility(View.GONE);
                                interiorimg4.setVisibility(View.GONE);
                                interiorimg5.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                interiorimg1.setImageURI(mArrayUri.get(0));
                                interiorimg2.setImageURI(mArrayUri.get(1));
                                interiorimg1.setVisibility(View.VISIBLE);
                                interiorimg2.setVisibility(View.VISIBLE);
                                interiorimg3.setVisibility(View.GONE);
                                interiorimg4.setVisibility(View.GONE);
                                interiorimg5.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                interiorimg1.setImageURI(mArrayUri.get(0));
                                interiorimg2.setImageURI(mArrayUri.get(1));
                                interiorimg3.setImageURI(mArrayUri.get(2));
                                interiorimg1.setVisibility(View.VISIBLE);
                                interiorimg2.setVisibility(View.VISIBLE);
                                interiorimg3.setVisibility(View.VISIBLE);
                                interiorimg4.setVisibility(View.GONE);
                                interiorimg5.setVisibility(View.GONE);
                            }

                            else if(mArrayUri.size()==4)
                            {
                                interiorimg1.setImageURI(mArrayUri.get(0));
                                interiorimg2.setImageURI(mArrayUri.get(1));
                                interiorimg3.setImageURI(mArrayUri.get(2));
                                interiorimg4.setImageURI(mArrayUri.get(3));
                                interiorimg1.setVisibility(View.VISIBLE);
                                interiorimg2.setVisibility(View.VISIBLE);
                                interiorimg3.setVisibility(View.VISIBLE);
                                interiorimg4.setVisibility(View.VISIBLE);
                                interiorimg5.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==5)
                            {
                                interiorimg1.setImageURI(mArrayUri.get(0));
                                interiorimg2.setImageURI(mArrayUri.get(1));
                                interiorimg3.setImageURI(mArrayUri.get(2));
                                interiorimg4.setImageURI(mArrayUri.get(3));
                                interiorimg5.setImageURI(mArrayUri.get(4));
                                interiorimg1.setVisibility(View.VISIBLE);
                                interiorimg2.setVisibility(View.VISIBLE);
                                interiorimg3.setVisibility(View.VISIBLE);
                                interiorimg4.setVisibility(View.VISIBLE);
                                interiorimg5.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                interiorimg1.setVisibility(View.GONE);
                                interiorimg2.setVisibility(View.GONE);
                                interiorimg3.setVisibility(View.GONE);
                                interiorimg4.setVisibility(View.GONE);
                                interiorimg5.setVisibility(View.GONE);
                                interiorcardview.setVisibility(View.GONE);
                                interiorlayout.setVisibility(View.GONE);
                                Toast.makeText(this," You can  select only 5 images",Toast.LENGTH_LONG).show();
                            }
                            imageURI  = cursor.getString(columnIndex);
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                            encodedImageList.add(encodedImage);
                            cursor.close();
                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                    }
                }
            }
            else
            {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            //Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            Log.e("Error", ""+e);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    public void submitdata() {
        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
            String main;
            if(rg_maintanance.getCheckedRadioButtonId()==R.id.radio_maintainance_yes)
            {
                main="1";
            }
            else
            {
                main="0";
            }
            String ec;
            if(rg_ec_charg.getCheckedRadioButtonId()==R.id.radio_ec_charg_yes)
            {
                ec="1";
            }
            else
            {
                ec="0";
            }
            String ac;
            if(rg_ac.getCheckedRadioButtonId()==R.id.radio_ac_yes)
            {
                ac="1";
            }
            else
            {
                ac="0";
            }
            String hk;
            if(rg_hkeeping.getCheckedRadioButtonId()==R.id.radio_houskeeping_yes)
            {
                hk="1";
            }
            else
            {
                hk="0";
            }
            String itsup;
            if(rg_itsupport.getCheckedRadioButtonId()==R.id.radio_it_support_yes)
            {
                itsup="1";
            }
            else
            {
                itsup="0";
            }
            String sec;
            if(rg_security.getCheckedRadioButtonId()==R.id.radio_security_yes)
            {
                sec="1";
            }
            else
            {
                sec="0";
            }
            String mend;
            if(rg_mend_to_serv_lockin.getCheckedRadioButtonId()==R.id.radio_mandetry_yes)
            {
                mend="1";
            }
            else
            {
                mend="0";
            }
            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverService_office;
            Map<String, String> jsonparam = new HashMap<String, String>();

            jsonparam.put("category",""+categoryspinner.getSelectedItem().toString());
            jsonparam.put("b_name",""+brand_name.getText().toString());
            jsonparam.put("Legl_name",""+legal_name.getText().toString());
            jsonparam.put("website",""+ s_website.getText().toString());
            jsonparam.put("gstin",""+s_GSTIN.getText().toString());
            jsonparam.put("bilng_address",""+blng_address.getText().toString());
            jsonparam.put("buil_name",""+buid_name.getText().toString());
            jsonparam.put("buil_address",""+buld_add.getText().toString());
            jsonparam.put("states",""+states_spi.getSelectedItem().toString());//
            jsonparam.put("buil_city",""+cities_spi.getSelectedItem().toString());//
            jsonparam.put("pin",""+buld_pin.getText().toString());
            jsonparam.put("latitude",""+s_latitude.getText().toString());
            jsonparam.put("longtitude",""+s_longitude.getText().toString());
            jsonparam.put("NPTF",""+NPTF.getText().toString());
            jsonparam.put("distance",""+s_distance.getText().toString());
            jsonparam.put("b_pic_out",""+s_distance.getText().toString());//button
            jsonparam.put("b_pic_in",""+s_distance.getText().toString());//buton
            jsonparam.put("b_serv_offc_inter",""+s_distance.getText().toString());//butoon
            jsonparam.put("tot_num_flor_ocp",""+tot_flor_ocup.getText().toString());
            jsonparam.put("tot_area",""+tot_area.getText().toString());
            jsonparam.put("tot_num_workstatn",""+tot_num_workstation.getText().toString());
            jsonparam.put("num_cabin",""+num_of_cabin.getText().toString());
            jsonparam.put("num_metng_room",""+num_of_meetroom.getText().toString());
            jsonparam.put("num_conf_room",""+number_of_confroom.getText().toString());
            jsonparam.put("brk_o_area",""+break_out_area.getText().toString());
            jsonparam.put("w_days",""+work_day.getText().toString());
            jsonparam.put("w_hours",""+work_hour.getText().toString());
            jsonparam.put("ws_type",""+workstationspinner.getSelectedItem().toString());//spinner
            //jsonparam.put("buil_city",""+buld_state);//state
            jsonparam.put("maintenance",""+main);//radio start
            jsonparam.put("e_chrg",""+ec);
            jsonparam.put("a_conditiong",""+ac);
            jsonparam.put("h_keeping",""+hk);
            jsonparam.put("it_supp",""+itsup);
            jsonparam.put("security",""+sec);//radio end
            jsonparam.put("beverages",""+beverags.getText().toString());
            jsonparam.put("t_phone",""+telphone.getText().toString());
            jsonparam.put("internet",""+intenet.getText().toString());
            jsonparam.put("intercom",""+s_intercom.getText().toString());
            jsonparam.put("cabin",""+s_cabins.getText().toString());
            jsonparam.put("meeting_room",""+meeting_room.getText().toString());
            jsonparam.put("conference_room",""+conference_room.getText().toString());
            jsonparam.put("fre_car_park",""+carparkingspinner.getSelectedItem().toString());//spinner
            jsonparam.put("CPC_inside",""+car_park_chrgin.getText().toString());
            jsonparam.put("CPC_out",""+getCar_park_chrgout.getText().toString());
            jsonparam.put("tmln_move_in",""+tmln_to_movein.getText().toString());
            jsonparam.put("mv_in_fee_nonref",""+one_time_setup.getText().toString());
            jsonparam.put("move_out",""+exit.getText().toString());
            jsonparam.put("agree_tenur",""+agree_tenure.getText().toString());
            jsonparam.put("Lck_in",""+Lock_in.getText().toString());
            jsonparam.put("n_period",""+noticed_per.getText().toString());
            jsonparam.put("MNSF_in",""+mend);//radio
            jsonparam.put("s_deposit",""+security_depo.getText().toString());
            jsonparam.put("r_escalation",""+rent_escalation.getText().toString());
            jsonparam.put("r_esclt_period",""+rent_escalation_per.getText().toString());

            submitdata(url, jsonparam);
        }
    }
    public void submitdata(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Serviced_office.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.e("response",""+response);
                    result = response.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    BaseEntity model = gson.fromJson(result,BaseEntity.class);
                    if(model.getCode().equalsIgnoreCase("1") && model.getStatus().equalsIgnoreCase("success"))
                    {
                        p_id = model.getPid();
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+model.getMsg(), Toast.LENGTH_SHORT).show();
                        submitmanager();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+model.getMsg(), Toast.LENGTH_SHORT).show();
                        submitmanager();

                    }
                }
                catch (Exception e)
                {
                    Log.e("error",""+e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mActivity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",""+error);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
    }

    public void submitmanager() {
        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
            service_manager = mdatabase_handler.getservice_office_manager_detail();
            if(service_manager.size()==0)
            {
                submitwarkspace();
            }
            else {

                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < service_manager.size(); i++) {
                    String url = ServerUrl.serverserviceoffic_manager;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("m_name", "" + service_manager.get(i).getName());
                    jsonparam.put("m_num", "" + service_manager.get(i).getNumber());
                    jsonparam.put("m_desig", "" + service_manager.get(i).getDesignation());
                    jsonparam.put("m_email", "" + service_manager.get(i).getEmail());
                    jsonparam.put("m_agree_fees", "" + service_manager.get(i).getFees());
                    if(i== service_manager.size()-1){
                        submitwarkspace();
                    }else{}
                    submitmanager(url, jsonparam);
                }
            }
        }

    }
    public void submitmanager(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Serviced_office.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.e("response",""+response);
                    result = response.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    BaseEntity model = gson.fromJson(result,BaseEntity.class);
                    if(model.getCode().equalsIgnoreCase("1") && model.getStatus().equalsIgnoreCase("success"))
                    {

                        Toast.makeText(mActivity, ""+model.getMsg(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        submitwarkspace();

                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        submitwarkspace();
                    }
                }
                catch (Exception e)
                {
                    Log.e("error",""+e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mActivity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",""+error);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
    }

    public void submitwarkspace() {

        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
           service_workspace = mdatabase_handler.getservice_office_workspace_detail();
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 0; i < service_workspace.size(); i++) {
                String url = ServerUrl.serverserviceoffic_workspace;
                Map<String, String> jsonparam = new HashMap<String, String>();

                jsonparam.put("p_id", "" + p_id);
                jsonparam.put("ws_type", "" + service_workspace.get(i).getWorkspace_type());
                jsonparam.put("ws_size", "" + service_workspace.get(i).getWork_size());
                jsonparam.put("SCFW_size", "" + service_workspace.get(i).getSetign_cap());
                jsonparam.put("q_rent", "" + service_workspace.get(i).getQuoted_rent());
                jsonparam.put("q_rent_seats", "" + service_workspace.get(i).getQuoted_rent_seat());
                jsonparam.put("CBNWD", "" + service_workspace.get(i).getChrg_byond_normal());

                Log.e("jsonparam", ""+jsonparam);
                submitwarkspace(url, jsonparam);

            }

        }
    }
    public void submitwarkspace(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Serviced_office.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.e("response",""+response);
                    result = response.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    BaseEntity model = gson.fromJson(result,BaseEntity.class);
                    if(model.getCode().equalsIgnoreCase("1") && model.getStatus().equalsIgnoreCase("success"))
                    {


                        progressBar.setVisibility(View.GONE);
                       // submit_warehouse_redy2();

                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                       // submit_warehouse_redy2();
                    }
                }
                catch (Exception e)
                {
                    Log.e("error",""+e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mActivity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",""+error);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
    }

    public void gotoGetCities(){
        if(!connectionDetector.isConnectingToInternet()){
            cd.showNetworkDialog();

        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverCities;
            Map<String,String> jsonparam = new HashMap<String, String>();
            jsonparam.put("state",""+state_nm);
            gotoGetCities(url,jsonparam);
        }
    }
    public void gotoGetCities(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Serviced_office.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.e("response",""+response);
                    result = response.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    BaseEntity model = gson.fromJson(result,BaseEntity.class);
                    if(model.getCode().equalsIgnoreCase("1") && model.getStatus().equalsIgnoreCase("success"))
                    {

                        cities.clear();
                        cities_nm.clear();
                        cities = model.cities;
                        cities_nm.add("Select State");
                        for(int i=0; i<cities.size();i++){
                            cities_nm.add(cities.get(i).getCity_name());
                        }

                        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(mActivity,
                                android.R.layout.simple_list_item_1, cities_nm);
                        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        cities_spi.setAdapter(myadapter);
                        progressBar.setVisibility(View.GONE);
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+model.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Log.e("error",""+e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mActivity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",""+error);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
    }

    public void gotoGetStates(){
        if(!connectionDetector.isConnectingToInternet()){
            cd.showNetworkDialog();
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverStates;
            Map<String,String> jsonparam = new HashMap<String, String>();
            gotoGetStates(url,jsonparam);
        }
    }
    public void gotoGetStates(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Serviced_office.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.e("response",""+response);
                    result = response.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    BaseEntity model = gson.fromJson(result,BaseEntity.class);
                    if(model.getCode().equalsIgnoreCase("1") && model.getStatus().equalsIgnoreCase("success"))
                    {
                        states.clear();
                        states_nm.clear();
                        states = model.states;

                        for(int i=0; i<states.size();i++){
                            states_nm.add(states.get(i).getCity_state());
                        }

                        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(mActivity,
                                android.R.layout.simple_list_item_1, states_nm);
                        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        states_spi.setAdapter(myadapter);
                        progressBar.setVisibility(View.GONE);
                    }
                    else
                    {
                        //progress.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+model.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    Log.e("error",""+e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mActivity, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",""+error);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
        {
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));
                return headers;
            }
        };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);
        requestQueue.add(jsonObjectRequest);
    }

    public void Upload_image()
    {

        JSONArray jsonArray = new JSONArray();

        if (encodedImageList.isEmpty()){
            Toast.makeText(this, "Please select some images first.", Toast.LENGTH_SHORT).show();
            return;
        }

        for (String encoded: encodedImageList){
            jsonArray.put(encoded);
        }

        try {

            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();
            jsonObject.put(ServerUrl.imageName, ""+ts);
            jsonObject.put(ServerUrl.imageList, jsonArray);
        } catch (JSONException e) {
            Log.e("JSONObject Here", e.toString());
        }
        String url = ServerUrl.serverupload_iamge;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.e("Message from server", jsonObject.toString());
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplication(), "Images Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Message from server", volleyError.toString());
                Toast.makeText(getApplication(), "Error Occurred", Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( 200*30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

}
