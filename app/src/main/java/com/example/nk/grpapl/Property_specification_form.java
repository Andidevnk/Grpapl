package com.example.nk.grpapl;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
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
import com.example.nk.grpapl.Models.Property_specification_verified;
import com.example.nk.grpapl.Models.States;
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

public class Property_specification_form extends AppCompatActivity {


    private RequestQueue requestQueue;
    String result;

    private JSONObject jsonObject;
    ArrayList<Uri> imagesUriList;
    ArrayList<String> encodedImageList;
    String imageURI;


    public static String p_id, state_nm;
    ImageView imageView1inside, imageView2inside, imageView3inside;
    ImageView imageView1outside, imageView2outside, imageView3outside;
    ImageView upload1pic1,upload1pic2,upload1pic3;
    ImageView upload2pic1,upload2pic2,upload3pic3;
    EditText buil_name, address, pin, totalnumberfloor, totalnumberbasement, totalarea, typicalfloorarea, caption, plan, charges, hoursoperation, chargesbeyond,
            carparking, additionalcharges, electricity, electricitycharges, name, designation, email, mobile, fixedlinenumber;
    Button picturindise, pictureoutside, uploadfile1, uploadfile2, represenattaive1, necxt;
    RadioGroup rg1;
    RadioButton buttonyes, buttonno;
    Spinner AirSpinner, cityspinner, statespinner;
    SP sp;
    CardView insidecardview, outsidecardview,upload1cardview,upload2cardview;
    LinearLayout insidelayout, outsidelayout,upload1layout,upload2layout;
    private static final int PICK_FROM_GALLERY = 1;
    private static final int PICK_FROM_GALLERY_1 = 2;
    private static final int PICK_FROM_GALLERY_2 = 3;
    private static final int PICK_FROM_GALLERY_3 = 4;
    String imageEncoded;
    List<String> imagesEncodedList;
    EditText latti, longi;
    int property_type;


    ArrayList<Property_specification_verified> specification_verified_details = new ArrayList<Property_specification_verified>();
    public static ArrayList<States> states = new ArrayList<>();
    public static ArrayList<String> states_nm = new ArrayList<>();
    public static ArrayList<Cities> cities = new ArrayList<>();
    public static ArrayList<String> cities_nm = new ArrayList<>();
    Button bt_next;
    ConnectionDetector connectionDetector;
    Databse_Handler databse_handler;
    ServerUrl serverUrl;
    ProgressBar progressBar;
    CommonDialog cd;
    GPSTracking gpsTracking;
    AppCompatActivity mActivity = Property_specification_form.this;
    private int REQUEST_FINE_LOCATION = 2000; /* 2 sec */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_property_specification_form);


        sp = SP.getInstance();
        connectionDetector = new ConnectionDetector(mActivity);
        databse_handler = new Databse_Handler(this);

        jsonObject = new JSONObject();
        encodedImageList = new ArrayList<>();

        progressBar = (ProgressBar) findViewById(R.id.ptogressbarproperty);


        rg1 = (RadioGroup) findViewById(R.id.power);

        buttonyes = (RadioButton) findViewById(R.id.pyes);
        buttonno = (RadioButton) findViewById(R.id.pno);

        buil_name = (EditText) findViewById(R.id.buildingname);
        address = (EditText) findViewById(R.id.buildingaddress);
        pin = (EditText) findViewById(R.id.pin);
        totalnumberfloor = (EditText) findViewById(R.id.numberoffloorindex);
        totalnumberbasement = (EditText) findViewById(R.id.numberofbasementindex);
        totalarea = (EditText) findViewById(R.id.totalareaindex);
        typicalfloorarea = (EditText) findViewById(R.id.typicalareaindex);
        caption = (EditText) findViewById(R.id.caption);
        plan = (EditText) findViewById(R.id.plan);
        charges = (EditText) findViewById(R.id.charges);
        hoursoperation = (EditText) findViewById(R.id.hoursofoperation);
        chargesbeyond = (EditText) findViewById(R.id.chargesbeyond);
        carparking = (EditText) findViewById(R.id.carparkingindex);
        additionalcharges = (EditText) findViewById(R.id.Additionalcarparkingindex);
        electricity = (EditText) findViewById(R.id.Electricity);
        electricitycharges = (EditText) findViewById(R.id.Electricitycharges);
        name = (EditText) findViewById(R.id.Name);
        designation = (EditText) findViewById(R.id.Designation);
        email = (EditText) findViewById(R.id.Email);
        mobile = (EditText) findViewById(R.id.mobilenumber);
        fixedlinenumber = (EditText) findViewById(R.id.fixedlinenumber);
        cityspinner = (Spinner) findViewById(R.id.cityspinner);
        statespinner = (Spinner) findViewById(R.id.statespinner);


        insidecardview = (CardView) findViewById(R.id.picinsidetext);
        outsidecardview = (CardView) findViewById(R.id.picoutsidetxt);
        upload1cardview = (CardView)findViewById(R.id.uploadinsidetext);
        upload2cardview = (CardView)findViewById(R.id.upload2sidetxt);


        insidelayout = (LinearLayout) findViewById(R.id.insidelayout);
        outsidelayout = (LinearLayout) findViewById(R.id.outsidelayout);
        upload1layout = (LinearLayout)findViewById(R.id.upload1layout);
        upload2layout = (LinearLayout)findViewById(R.id.upload2layout);

        picturindise = (Button) findViewById(R.id.insidepicture);
        pictureoutside = (Button) findViewById(R.id.outsidepicture);
        uploadfile1 = (Button) findViewById(R.id.upload1);
        uploadfile2 = (Button) findViewById(R.id.upload2);
        represenattaive1 = (Button) findViewById(R.id.res2);
        necxt = (Button) findViewById(R.id.nexttocommercial);


        imageView1inside = (ImageView) findViewById(R.id.pic1);
        imageView2inside = (ImageView) findViewById(R.id.pic2);
        imageView3inside = (ImageView) findViewById(R.id.pic3);
        imageView1outside = (ImageView) findViewById(R.id.pic1outside);
        imageView2outside = (ImageView) findViewById(R.id.pic2outside);
        imageView3outside = (ImageView) findViewById(R.id.pic3outside);

        upload1pic1 = (ImageView)findViewById(R.id.pic11);
        upload1pic2 = (ImageView)findViewById(R.id.pic22);
        upload1pic3 = (ImageView)findViewById(R.id.pic33);
        upload2pic1 = (ImageView)findViewById(R.id.pic111);
        upload2pic2 = (ImageView)findViewById(R.id.pic222);
        upload3pic3 = (ImageView)findViewById(R.id.pic333);

        gpsTracking = new GPSTracking(this, Property_specification_form.this);
        latti = (EditText) findViewById(R.id.latitude);
        longi = (EditText) findViewById(R.id.longitute);


        checkPermissions();

        picturindise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Property_specification_form.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Property_specification_form.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pictureoutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Property_specification_form.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Property_specification_form.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY_1);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        uploadfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Property_specification_form.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Property_specification_form.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY_2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        uploadfile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Property_specification_form.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Property_specification_form.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY_3);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        sp = SP.getInstance();
        property_type = Integer.parseInt(sp.getProperty_type(Property_specification_form.this));

        bt_next = (Button) findViewById(R.id.nexttocommercial);


        represenattaive1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String psvnm = name.getText().toString().trim();
                final String psvdes = designation.getText().toString().trim();
                final String psvem = email.getText().toString().trim();
                final String psvmob = mobile.getText().toString().trim();
                final String psvflnum = fixedlinenumber.getText().toString().trim();

                if (!TextUtils.isEmpty(psvnm) || !TextUtils.isEmpty(psvdes) || !TextUtils.isEmpty(psvem) || !TextUtils.isEmpty(psvmob) || !TextUtils.isEmpty(psvflnum)) {
                    databse_handler.addpropertyspecificationverified(psvnm, psvdes, psvem, psvmob, psvflnum);
                    name.setText("");
                    designation.setText("");
                    email.setText("");
                    mobile.setText("");
                    fixedlinenumber.setText("");
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), " Data Values not saved,Retry", Toast.LENGTH_LONG).show();
                }

            }

        });

        gotoGetStates();


        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* final String email_property_specification = email.getText().toString();
                final String num_property = mobile.getText().toString();
                final String fixed_numbr = fixedlinenumber.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (email_property_specification.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }
                if(num_property.length()==10||fixed_numbr.length()==10){

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }*/

                Upload_image();
                submitdata();

            }
        });

        statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    state_nm = "";
                } else {
                    state_nm = states_nm.get(position).toString();
                    gotoGetCities();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        AirSpinner = (Spinner) findViewById(R.id.airconditioningproperty);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Property_specification_form.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.AirConditoningPropertySpecification));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AirSpinner.setAdapter(myadapter);

    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracking.getLastLocation();
            double var = gpsTracking.getLatitude();
            double var1 = gpsTracking.getLongitude();
            // Toast.makeText(this,""+var +" "+var1,Toast.LENGTH_LONG).show();

            latti.setText("" + var);
            longi.setText("" + var1);

        } else {
            requestPermissions();
            return false;
        }
        return false;
    }


    public void submitdata() {
        if (!connectionDetector.isConnectingToInternet()) {
            cd.showNetworkDialog();

        } else {

            String powerbackup = "";
            if (rg1.getCheckedRadioButtonId() == R.id.pyes) {
                powerbackup = "1";
                Toast.makeText(mActivity, "" + powerbackup, Toast.LENGTH_SHORT).show();
            } else {
                powerbackup = "0";
                Toast.makeText(mActivity, "" + powerbackup, Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverProperty_specification_part1;
            Map<String, String> jsonparam = new HashMap<String, String>();
            jsonparam.put("bname", "" + buil_name.getText().toString());
            jsonparam.put("address", "" + address.getText().toString());
            jsonparam.put("city", "" + cityspinner.getSelectedItem().toString());
            jsonparam.put("state", "" + statespinner.getSelectedItem().toString());
            jsonparam.put("pin", "" + pin.getText().toString());
            jsonparam.put("latitude", "" + latti.getText().toString());
            jsonparam.put("longitute", "" + longi.getText().toString());
            jsonparam.put("tot_num_floor", "" + totalnumberfloor.getText().toString());
            jsonparam.put("Tot_num_basmnt", "" + totalnumberbasement.getText().toString());
            jsonparam.put("tot_area", "" + totalarea.getText().toString());
            jsonparam.put("typical_flo_area", "" + typicalfloorarea.getText().toString());
            jsonparam.put("f_caption", "" + caption.getText().toString());
            jsonparam.put("f_plan", "" + plan.getText().toString());
            jsonparam.put("cam_charges", "" + charges.getText().toString());
            jsonparam.put("cam_hour_oprtn", "" + hoursoperation.getText().toString());
            jsonparam.put("cam_bynd_oprtn_hr", "" + chargesbeyond.getText().toString());
            jsonparam.put("c_park_chrg", "" + carparking.getText().toString());
            jsonparam.put("c_park_add_chrg", "" + additionalcharges.getText().toString());
            jsonparam.put("ac_spinner", "" + additionalcharges.getText().toString());
            jsonparam.put("electricity", "" + electricity.getText().toString());
            jsonparam.put("electri_charg", "" + electricitycharges.getText().toString());
            jsonparam.put("pow_backup", "" + powerbackup);
            submitdata(url, jsonparam);

        }
    }


    public void submitdata(String url, Map<String, String> jsonparam) {
        requestQueue = Volley.newRequestQueue(Property_specification_form.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("response", "" + response);
                    result = response.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    BaseEntity model = gson.fromJson(result, BaseEntity.class);
                    if (model.getCode().equalsIgnoreCase("1") && model.getStatus().equalsIgnoreCase("success")) {


                        p_id = model.getPid();
                        progressBar.setVisibility(View.GONE);
                        submitrepresentative();
                        if (property_type == 2) {
                            Intent i = new Intent(Property_specification_form.this, Office_space.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        submitrepresentative();
                    }
                } catch (Exception e) {
                    Log.e("error", "" + e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mActivity, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", "" + error);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, "" + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
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


    public void submitrepresentative() {
        if (!connectionDetector.isConnectingToInternet()) {
            cd.showNetworkDialog();
        } else {
            specification_verified_details = databse_handler.getpropertyspecificationverified();
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 0; i < specification_verified_details.size(); i++) {
                String url = ServerUrl.serverProperty_specification_part2;
                Map<String, String> jsonparam = new HashMap<String, String>();
                jsonparam.put("p_id", "" + p_id);
                jsonparam.put("name", "" + specification_verified_details.get(i).getProperty_verfied_nm().toString());
                jsonparam.put("desig", "" + specification_verified_details.get(i).getProperty_verfied_desg().toString());
                jsonparam.put("email", "" + specification_verified_details.get(i).getProperty_verfied_em().toString());
                jsonparam.put("num", "" + specification_verified_details.get(i).getProperty_verfied_mnum().toString());
                jsonparam.put("fl_num", "" + specification_verified_details.get(i).getProperty_fix_lnum().toString());
                submitrepresentative(url, jsonparam);
            }
        }
    }

    public void submitrepresentative(String url, Map<String, String> jsonparam) {
        requestQueue = Volley.newRequestQueue(Property_specification_form.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("response", "" + response);
                    result = response.toString();
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    BaseEntity model = gson.fromJson(result, BaseEntity.class);
                    if (model.getCode().equalsIgnoreCase("1") && model.getStatus().equalsIgnoreCase("success")) {


                        progressBar.setVisibility(View.GONE);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    Log.e("error", "" + e.getMessage());
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(mActivity, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", "" + error);
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(mActivity, "" + error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
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


    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_FINE_LOCATION);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
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
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY_1);
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
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY_2);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;

            case PICK_FROM_GALLERY_3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY_3);
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

                    imageView1inside.setImageURI(mImageUri);
                    imageView1inside.setVisibility(View.VISIBLE);
                    imageView2inside.setVisibility(View.GONE);
                    imageView3inside.setVisibility(View.GONE);
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


                            if(mArrayUri.size()==1)
                            {
                                imageView1inside.setImageURI(mArrayUri.get(0));
                                imageView1inside.setVisibility(View.VISIBLE);
                                imageView2inside.setVisibility(View.GONE);
                                imageView3inside.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                imageView1inside.setImageURI(mArrayUri.get(0));
                                imageView2inside.setImageURI(mArrayUri.get(1));
                                imageView1inside.setVisibility(View.VISIBLE);
                                imageView2inside.setVisibility(View.VISIBLE);
                                imageView3inside.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                imageView1inside.setImageURI(mArrayUri.get(0));
                                imageView2inside.setImageURI(mArrayUri.get(1));
                                imageView3inside.setImageURI(mArrayUri.get(2));
                                imageView1inside.setVisibility(View.VISIBLE);
                                imageView2inside.setVisibility(View.VISIBLE);
                                imageView3inside.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                imageView1inside.setVisibility(View.GONE);
                                imageView2inside.setVisibility(View.GONE);
                                imageView3inside.setVisibility(View.GONE);
                                insidelayout.setVisibility(View.GONE);
                                insidecardview.setVisibility(View.GONE);

                                Toast.makeText(this," You can  select only 3 images",Toast.LENGTH_LONG).show();
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


                    imageView1outside.setImageURI(mImageUri);
                    imageView1outside.setVisibility(View.VISIBLE);
                    imageView2outside.setVisibility(View.GONE);
                    imageView3outside.setVisibility(View.GONE);
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


                            if(mArrayUri.size()==1)
                            {
                                imageView1outside.setImageURI(mArrayUri.get(0));
                                imageView1outside.setVisibility(View.VISIBLE);
                                imageView2outside.setVisibility(View.GONE);
                                imageView3outside.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                imageView1outside.setImageURI(mArrayUri.get(0));
                                imageView2outside.setImageURI(mArrayUri.get(1));
                                imageView1outside.setVisibility(View.VISIBLE);
                                imageView2outside.setVisibility(View.VISIBLE);
                                imageView3outside.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                imageView1outside.setImageURI(mArrayUri.get(0));
                                imageView2outside.setImageURI(mArrayUri.get(1));
                                imageView3outside.setImageURI(mArrayUri.get(2));
                                imageView1outside.setVisibility(View.VISIBLE);
                                imageView2outside.setVisibility(View.VISIBLE);
                                imageView3outside.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                imageView1outside.setVisibility(View.GONE);
                                imageView2outside.setVisibility(View.GONE);
                                imageView3outside.setVisibility(View.GONE);
                                outsidecardview.setVisibility(View.GONE);
                                outsidelayout.setVisibility(View.GONE);
                                Toast.makeText(this," You can  select only 3 images",Toast.LENGTH_LONG).show();
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


                    upload1pic1.setImageURI(mImageUri);
                    upload1pic1.setVisibility(View.VISIBLE);
                    upload1pic2.setVisibility(View.GONE);
                    upload1pic3.setVisibility(View.GONE);
                    upload1cardview.setVisibility(View.VISIBLE);
                    upload1layout.setVisibility(View.VISIBLE);


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


                            if(mArrayUri.size()==1)
                            {
                                upload1pic1.setImageURI(mArrayUri.get(0));
                                upload1pic1.setVisibility(View.VISIBLE);
                                upload1pic2.setVisibility(View.GONE);
                                upload1pic3.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                upload1pic1.setImageURI(mArrayUri.get(0));
                                upload1pic2.setImageURI(mArrayUri.get(1));
                                upload1pic1.setVisibility(View.VISIBLE);
                                upload1pic2.setVisibility(View.VISIBLE);
                                upload1pic3.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                upload1pic1.setImageURI(mArrayUri.get(0));
                                upload1pic2.setImageURI(mArrayUri.get(1));
                                upload1pic3.setImageURI(mArrayUri.get(2));
                                upload1pic1.setVisibility(View.VISIBLE);
                                upload1pic2.setVisibility(View.VISIBLE);
                                upload1pic3.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                upload1pic1.setVisibility(View.GONE);
                                upload1pic2.setVisibility(View.GONE);
                                upload1pic3.setVisibility(View.GONE);
                                upload1cardview.setVisibility(View.GONE);
                                upload1layout.setVisibility(View.GONE);
                                Toast.makeText(this," You can  select only 3 images",Toast.LENGTH_LONG).show();
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
            else if(requestCode == PICK_FROM_GALLERY_3 && resultCode == RESULT_OK
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


                    upload2pic1.setImageURI(mImageUri);
                    upload2pic1.setVisibility(View.VISIBLE);
                    upload2pic2.setVisibility(View.GONE);
                    upload3pic3.setVisibility(View.GONE);
                    upload2cardview.setVisibility(View.VISIBLE);
                    upload2layout.setVisibility(View.VISIBLE);


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


                            if(mArrayUri.size()==1)
                            {
                                upload2pic1.setImageURI(mArrayUri.get(0));
                                upload2pic1.setVisibility(View.VISIBLE);
                                upload2pic2.setVisibility(View.GONE);
                                upload3pic3.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                upload2pic1.setImageURI(mArrayUri.get(0));
                                upload2pic2.setImageURI(mArrayUri.get(1));
                                upload2pic1.setVisibility(View.VISIBLE);
                                upload2pic2.setVisibility(View.VISIBLE);
                                upload3pic3.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                upload2pic1.setImageURI(mArrayUri.get(0));
                                upload2pic2.setImageURI(mArrayUri.get(1));
                                upload3pic3.setImageURI(mArrayUri.get(2));
                                upload2pic1.setVisibility(View.VISIBLE);
                                upload2pic2.setVisibility(View.VISIBLE);
                                upload3pic3.setVisibility(View.VISIBLE);

                            }
                            else
                            {
                                upload2pic1.setVisibility(View.GONE);
                                upload2pic2.setVisibility(View.GONE);
                                upload3pic3.setVisibility(View.GONE);
                                upload2cardview.setVisibility(View.GONE);
                                upload2layout.setVisibility(View.GONE);
                                Toast.makeText(this," You can  select only 3 images",Toast.LENGTH_LONG).show();
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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        requestQueue = Volley.newRequestQueue(Property_specification_form.this);
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
                        cityspinner.setAdapter(myadapter);
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
        requestQueue = Volley.newRequestQueue(Property_specification_form.this);
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
                        statespinner.setAdapter(myadapter);
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


    public void back(View view) {
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
