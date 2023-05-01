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
import com.example.nk.grpapl.Models.LandBroker;
import com.example.nk.grpapl.Models.LandManager;
import com.example.nk.grpapl.Models.LandOwner;
import com.example.nk.grpapl.Models.Landpartavailable;
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

public class Land extends AppCompatActivity {


    private RequestQueue requestQueue;
    String result;
    ScrollView sv;

    private JSONObject jsonObject;
    ArrayList<Uri> imagesUriList;
    ArrayList<String> encodedImageList;
    String imageURI;



 public  static String  p_id;
    ImageView imageView, imageView1, imageView2, imageView3, imageView4;
    Button btnland, btnmanger, btnbroker, btnlandpicture, submit,btnlndbuyer;
    EditText owner_cn, owner_cpm, owner_num, owner_ea;
    EditText mngr_cn, mngr_nm, mngr_num, mngr_email;
    EditText brok_cn, brok_nm, brok_num, brok_em, brok_fs, brok_afs;
    EditText fsi, addressland, villege, tehsil, district, state, pin, latitude, longtutute, khewatnumber, khasaranumber, jamabandi, landsize, frontage, quatsalesprize, spaceavailable,
            nameofcompany, contactperson, contactnumber, email, areasold, salesprice, nameofcomapany1, contactperson1, contactnumber1, email1, areasold1, salesprice1;
    Spinner Landspinner;
    Spinner CurrentLandUsagesLicense;
    Spinner LandUsagesCanbechagedto;
    RadioGroup llrg, grpapl1, grpapl2;
    Databse_Handler mdatabse_handler;
    private static final int PICK_FROM_GALLERY = 1;
    String imageEncoded;
    List<String> imagesEncodedList;


    ServerUrl serverUrl;
    GPSTracking gpsTracking;
    CommonDialog cd;

    private int REQUEST_FINE_LOCATION = 2000; /* 2 sec */

    SP sp;
    ProgressBar progressBar;
    int war;
    ConnectionDetector connectionDetector;

    ArrayList<LandOwner>owner_details = new ArrayList<LandOwner>();
    ArrayList<LandManager>manager_details = new ArrayList<LandManager>();
    ArrayList<LandBroker>broker_details = new ArrayList<LandBroker>();
    ArrayList<Landpartavailable>part_ava_details = new ArrayList<Landpartavailable>();
    Databse_Handler databse_handler;
    AppCompatActivity mActivity = Land.this;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_land);


        jsonObject = new JSONObject();
        encodedImageList = new ArrayList<>();


        sp = SP.getInstance();
        connectionDetector = new ConnectionDetector(mActivity);
        databse_handler = new Databse_Handler(this);

        CurrentLandUsagesLicense = (Spinner) findViewById(R.id.spinnerCurrentLandUsagesLicense);
        LandUsagesCanbechagedto = (Spinner) findViewById(R.id.spinnerLandUsagesCanbechagedto);
        Landspinner = (Spinner) findViewById(R.id.land);
        progressBar = (ProgressBar) findViewById(R.id.ptogressbar);


        fsi = (EditText) findViewById(R.id.FSIFloorSpaceIndex);
        addressland = (EditText) findViewById(R.id.Addressland);
        villege = (EditText) findViewById(R.id.Village);
        tehsil = (EditText) findViewById(R.id.Tehsil);
        district = (EditText) findViewById(R.id.District);
        state = (EditText) findViewById(R.id.State);
        pin = (EditText) findViewById(R.id.PINland);
        latitude = (EditText) findViewById(R.id.latitude);
        longtutute = (EditText) findViewById(R.id.longitute);
        khewatnumber = (EditText) findViewById(R.id.KhewatNumber);
        khasaranumber = (EditText) findViewById(R.id.KhasraNumber);
        jamabandi = (EditText) findViewById(R.id.JamabandiYear);
        landsize = (EditText) findViewById(R.id.LandSize);
        frontage = (EditText) findViewById(R.id.Frontage);
        quatsalesprize = (EditText) findViewById(R.id.QuotedSalesPrice);
        spaceavailable = (EditText) findViewById(R.id.SpaceAvailable);
        nameofcompany = (EditText) findViewById(R.id.NameoftheCompany);
        contactperson = (EditText) findViewById(R.id.ContactPersonNameland);
        contactnumber = (EditText) findViewById(R.id.ContactNumber);
        email = (EditText) findViewById(R.id.Emailsold);
        areasold = (EditText) findViewById(R.id.AreaSold);
        salesprice = (EditText) findViewById(R.id.SalesPrice);
        nameofcomapany1 = (EditText) findViewById(R.id.NameoftheCompanysoldto);
        contactperson1 = (EditText) findViewById(R.id.ContactPersonNamesoldto);
        contactnumber1 = (EditText) findViewById(R.id.ContactNumbersoldto);
        email1 = (EditText) findViewById(R.id.Emailsoldto);
        areasold1 = (EditText) findViewById(R.id.AreaSoldsoldto);
        salesprice1 = (EditText) findViewById(R.id.SalesPricesoldto);

        btnlndbuyer = (Button)findViewById(R.id.buyer3);
        btnlandpicture = (Button) findViewById(R.id.PictureoftheLand);
        submit = (Button) findViewById(R.id.submitland);






        llrg = (RadioGroup) findViewById(R.id.llgroup);
        grpapl1 = (RadioGroup) findViewById(R.id.grpapl1group);
        grpapl2 = (RadioGroup) findViewById(R.id.grpapl2group);

        mdatabse_handler = new Databse_Handler(this);


        gpsTracking = new GPSTracking(this, Land.this);

        owner_cn = (EditText) findViewById(R.id.CompanyName);
        owner_cpm = (EditText) findViewById(R.id.ContactPersonName);
        owner_num = (EditText) findViewById(R.id.Numberownerland);
        owner_ea = (EditText) findViewById(R.id.EmailAddresslandowner);
        btnland = (Button) findViewById(R.id.ownerland3);

        mngr_cn = (EditText) findViewById(R.id.CompanyNamemanger);
        mngr_nm = (EditText) findViewById(R.id.managernameland);
        mngr_num = (EditText) findViewById(R.id.numbermanagerland);
        mngr_email = (EditText) findViewById(R.id.emailmanagerland);
        btnmanger = (Button) findViewById(R.id.manager3land);


        brok_cn = (EditText) findViewById(R.id.CompanyNamebroker);
        brok_nm = (EditText) findViewById(R.id.brokernameland);
        brok_num = (EditText) findViewById(R.id.numberbrokerland);
        brok_em = (EditText) findViewById(R.id.emailbrokerland);
        brok_fs = (EditText) findViewById(R.id.feesbrokerland);
        brok_afs = (EditText) findViewById(R.id.feesbrokerlandagred);
        btnbroker = (Button) findViewById(R.id.broker3land);


        imageView = (ImageView) findViewById(R.id.selectimage1);
        imageView1 = (ImageView) findViewById(R.id.selectimage2);
        imageView2 = (ImageView) findViewById(R.id.selectimage3);
        imageView3 = (ImageView) findViewById(R.id.selectimage4);
        imageView4 = (ImageView) findViewById(R.id.selectimage5);

        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Land.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currentLanUsagesLicense));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrentLandUsagesLicense.setAdapter(myadapter);

        ArrayAdapter<String> myadapter1 = new ArrayAdapter<String>(Land.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.landUsagesCanbechagedto));
        myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LandUsagesCanbechagedto.setAdapter(myadapter1);


        btnlandpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Land.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Land.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
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


        btnland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ownercn = owner_cn.getText().toString().trim();
                final String ownercpn = owner_cpm.getText().toString().trim();
                final String ownernum = owner_num.getText().toString().trim();
                final String owneremail = owner_ea.getText().toString().trim();


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (owneremail.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if (ownernum.length() == 10) {

                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }

                if (!TextUtils.isEmpty(ownercn) || !TextUtils.isEmpty(ownercpn) || !TextUtils.isEmpty(ownernum) || !TextUtils.isEmpty(owneremail)) {


                    mdatabse_handler.addownerland(ownercn, ownercpn, ownernum, owneremail);
                    owner_cn.setText("");
                    owner_cpm.setText("");
                    owner_num.setText("");
                    owner_ea.setText("");
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), " Data Values not saved,Retry", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnmanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mngrcn = mngr_cn.getText().toString().trim();
                final String mngrnm = mngr_nm.getText().toString().trim();
                final String mngrnum = mngr_num.getText().toString().trim();
                final String mngremail = mngr_email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (mngremail.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if (mngrnum.length() == 10) {

                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }

                if (!TextUtils.isEmpty(mngrnm) || !TextUtils.isEmpty(mngrnum) || !TextUtils.isEmpty(mngremail)) {
                    mdatabse_handler.addmanagerland(mngrcn,mngrnm, mngrnum, mngremail);
                    mngr_cn.setText("");
                    mngr_nm.setText("");
                    mngr_num.setText("");
                    mngr_email.setText("");
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), " Data Values not saved,Retry", Toast.LENGTH_LONG).show();
                }

            }
        });


        btnbroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String brocn = brok_cn.getText().toString().trim();
                final String broknm = brok_nm.getText().toString().trim();
                final String broknum = brok_num.getText().toString().trim();
                final String brokem = brok_em.getText().toString().trim();
                final String brokfees = brok_fs.getText().toString().trim();
                final String brokafees = brok_afs.getText().toString().trim();


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (brokem.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if (broknum.length() == 10) {

                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }

                if (!TextUtils.isEmpty(broknm) || !TextUtils.isEmpty(broknum) || !TextUtils.isEmpty(brokem) || !TextUtils.isEmpty(brokfees) || !TextUtils.isEmpty(brokafees)) {

                    mdatabse_handler.addbrokerland(brocn,broknm, broknum, brokem, brokfees, brokafees);
                    brok_cn.setText("");
                    brok_nm.setText("");
                    brok_num.setText("");
                    brok_em.setText("");
                    brok_fs.setText("");
                    brok_afs.setText("");
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Data value not saved,Retry", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnlndbuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String lndavacn = nameofcompany.getText().toString().trim();
                final String lndavacpn = contactperson.getText().toString().trim();
                final String lndavanum = contactnumber.getText().toString().trim();
                final String lndavaemail = email.getText().toString().trim();
                final String lndavaareasold = areasold.getText().toString().trim();
                final String lndavasalesprice = salesprice.getText().toString().trim();
                 String soldby1="";
                if (llrg.getCheckedRadioButtonId() == R.id.soldyes){
                    soldby1 = "1";
                    Toast.makeText(mActivity, ""+soldby1, Toast.LENGTH_SHORT).show();
                }else{
                    soldby1 = "0";
                    Toast.makeText(mActivity, ""+soldby1, Toast.LENGTH_SHORT).show();
                }
                if (!TextUtils.isEmpty(lndavacn) || !TextUtils.isEmpty(lndavacpn) || !TextUtils.isEmpty(lndavanum) || !TextUtils.isEmpty(lndavaemail) || !TextUtils.isEmpty(lndavaareasold) || !TextUtils.isEmpty(lndavasalesprice)
                        || !TextUtils.isEmpty(soldby1)) {


                    mdatabse_handler.addlandpartava(lndavacn, lndavacpn, lndavanum, lndavaemail,lndavaareasold,lndavasalesprice,soldby1);
                    nameofcompany.setText("");
                    contactperson.setText("");
                    contactnumber.setText("");
                    email.setText("");
                    areasold.setText("");
                    salesprice.setText("");
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), " Data Values not saved,Retry", Toast.LENGTH_LONG).show();
                }

            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  final String email_partsold = contactnumber.getText().toString();
                final String num_partsold = email.getText().toString();
                final String email_soldto = contactnumber1.getText().toString();
                final String num_soldto = email1.getText().toString();
                final String ownernum = owner_num.getText().toString().trim();
                final String owneremail = owner_ea.getText().toString().trim();
                final String mngrnum = mngr_num.getText().toString().trim();
                final String mngremail = mngr_email.getText().toString().trim();
                final String broknum = brok_num.getText().toString().trim();
                final String brokem = brok_em.getText().toString().trim();


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (email_partsold.matches(emailPattern) || email_soldto.matches(emailPattern) || owneremail.matches(emailPattern) || mngremail.matches(emailPattern) || brokem.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if (num_partsold.length() == 10 || num_soldto.length() == 10 || ownernum.length() == 10 || mngrnum.length() == 10 || broknum.length() == 10) {

                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }
*/

                Upload_image();
                submitdata();
               // llselected = (RadioButton)findViewById(llrg.getCheckedRadioButtonId());
                //soldselected = (RadioButton)findViewById(grpapl1.getCheckedRadioButtonId());
                //soldelected2 = (RadioButton)findViewById(grpapl2.getCheckedRadioButtonId());


            }
        });

    checkPermissions();

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.landpartavailable);
        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.landsold);
        Landspinner = (Spinner) findViewById(R.id.land);
        ArrayAdapter<String> myadapter2 = new ArrayAdapter<String>(Land.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.land));
        myadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Landspinner.setAdapter(myadapter2);

        CurrentLandUsagesLicense = (Spinner) findViewById(R.id.spinnerCurrentLandUsagesLicense);
        ArrayAdapter<String> myadapter3 = new ArrayAdapter<String>(Land.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currentLanUsagesLicense));
        myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrentLandUsagesLicense.setAdapter(myadapter1);


        LandUsagesCanbechagedto = (Spinner) findViewById(R.id.spinnerLandUsagesCanbechagedto);
        ArrayAdapter<String> myadapter4 = new ArrayAdapter<String>(Land.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.landUsagesCanbechagedto));
        myadapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LandUsagesCanbechagedto.setAdapter(myadapter4);


        Landspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                war = position;
                if (war == 2) {
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                } else if (war == 3) {
                    linearLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                    linearLayout1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void submitdata() {
        if (!connectionDetector.isConnectingToInternet()) {
            cd.showNetworkDialog();

        } else {
            String llradiogroup="";
            if (llrg.getCheckedRadioButtonId() == R.id.llyes){
                llradiogroup = "1";
                Toast.makeText(mActivity, ""+llradiogroup, Toast.LENGTH_SHORT).show();
            }else{
                llradiogroup = "0";
                Toast.makeText(mActivity, ""+llradiogroup, Toast.LENGTH_SHORT).show();
            }
            String soldradiogroup = "";
            if(grpapl2.getCheckedRadioButtonId() == R.id.soldgryes)
            {
                soldradiogroup = "1";
                Toast.makeText(mActivity, ""+soldradiogroup, Toast.LENGTH_SHORT).show();
            }
            else
            {
                soldradiogroup = "0";
                Toast.makeText(mActivity, ""+soldradiogroup, Toast.LENGTH_SHORT).show();
            }

            String CLuLicense = CurrentLandUsagesLicense.getSelectedItem().toString();
            String LandUsCachagedto = LandUsagesCanbechagedto.getSelectedItem().toString();
           // llselected = (RadioButton)findViewById(llrg.getCheckedRadioButtonId());
            String fsiii = fsi.getText().toString();
            String addrland = addressland.getText().toString();
            String villegess = villege.getText().toString();
            String tehsl = tehsil.getText().toString();
            String distrct = district.getText().toString();
            String stte = state.getText().toString();
            String pinn = pin.getText().toString();
            String latie = latitude.getText().toString();
            String longte = longtutute.getText().toString();
            String btnlanture = btnlandpicture.getText().toString();
            String khewumber = khewatnumber.getText().toString();
            String khasumber = khasaranumber.getText().toString();
            String jamandi = jamabandi.getText().toString();
            String lanize = landsize.getText().toString();
            String fronage = frontage.getText().toString();
            String quatsesprize = quatsalesprize.getText().toString();
            String spaceavlable = spaceavailable.getText().toString();
            String Landpinner = Landspinner.getSelectedItem().toString();

            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverLand;
            Map<String, String> jsonparam = new HashMap<String, String>();
            jsonparam.put("CLU_license",""+CLuLicense);
            jsonparam.put("l_user_chngto",""+LandUsCachagedto);
            jsonparam.put("LL_opnto_CLU",""+llradiogroup);
            jsonparam.put("FSI",""+fsiii);
            jsonparam.put("address",""+addrland);
            jsonparam.put("village",""+villegess);
            jsonparam.put("tehsil",""+tehsl);
            jsonparam.put("district",""+distrct);
            jsonparam.put("state",""+stte);
            jsonparam.put("pin",""+pinn);
            jsonparam.put("latitude",""+latie);
            jsonparam.put("longitute",""+longte);
            jsonparam.put("land_pic",""+btnlanture);
            jsonparam.put("khewat_num",""+khewumber);
            jsonparam.put("khasra_num",""+khasumber);
            jsonparam.put("jamabandi_num",""+jamandi);
            jsonparam.put("l_size",""+lanize);
            jsonparam.put("frontage",""+fronage);
            jsonparam.put("q_sal_price",""+quatsesprize);
            jsonparam.put("s_avlbl",""+spaceavlable);
            jsonparam.put("land_spinner",""+Landpinner);
            jsonparam.put("nm_of_comp",""+nameofcomapany1.getText().toString());
            jsonparam.put("c_p_name",""+contactperson1.getText().toString());
            jsonparam.put("con_num",""+contactnumber1.getText().toString());
            jsonparam.put("email",""+email1.getText().toString());
            jsonparam.put("area_sold",""+areasold1.getText().toString());
            jsonparam.put("sl_price",""+salesprice1.getText().toString());
            jsonparam.put("s_by_grpapl",""+soldradiogroup);
            submitdata(url,jsonparam);

        }
    }

    public void submitdata(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Land.this);
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
                        submitowner();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        submitowner();
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



    public  void submitowner(){
     if(!connectionDetector.isConnectingToInternet())
     {
         cd.showNetworkDialog();
     }
     else{
         owner_details = databse_handler.getlandowner();
         if(owner_details.size()==0)
         {
             submitmanager();
         }
         else {
             progressBar.setVisibility(View.VISIBLE);
             for (int i = 0; i < owner_details.size(); i++) {
                 String url = ServerUrl.serverLand_owner;
                 Map<String, String> jsonparam = new HashMap<String, String>();
                 jsonparam.put("p_id", "" + p_id);
                 jsonparam.put("c_name", "" + owner_details.get(i).getlnd_own_comp_name().toString());
                 jsonparam.put("pers_name", "" + owner_details.get(i).getland_own_cont_nm().toString());
                 jsonparam.put("num", "" + owner_details.get(i).getland_owner_num().toString());
                 jsonparam.put("e_mail", "" + owner_details.get(i).getland_own_email().toString());
                 if(i== owner_details.size()-1){
                     submitmanager();
                 }else{}
                 submitowner(url, jsonparam);
             }
         }
     }
    }

    public void submitowner(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Land.this);
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
                        submitmanager();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
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


    public  void submitmanager(){
        if(!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else{
            manager_details = databse_handler.getlandmanager();
            if(manager_details.size()==0)
            {
                submitbroker();
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < manager_details.size(); i++) {
                    String url = ServerUrl.serverLand_manager;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("mngr_cn", "" + manager_details.get(i).getLnd_mng_cn().toString());
                    jsonparam.put("name", "" + manager_details.get(i).getLnd_mng_nm().toString());
                    jsonparam.put("num", "" + manager_details.get(i).getLnd_mng_num().toString());
                    jsonparam.put("e_mail", "" + manager_details.get(i).getLnd_mng_email().toString());
                    if(i== manager_details.size()-1){
                        submitbroker();
                    }else{}
                    submitmanager(url, jsonparam);
                }
            }
        }
    }

    public void submitmanager(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Land.this);
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
                        submitbroker();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        submitbroker();
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



    public  void submitbroker(){
        if(!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else{
            broker_details = databse_handler.getlandbroker();
            if(broker_details.size()==0)
            {
                submitpartavailable();
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < broker_details.size(); i++) {
                    String url = ServerUrl.serverLand_broker;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("broker_cn", "" + broker_details.get(i).getLnd_brok_cn().toString());
                    jsonparam.put("name", "" + broker_details.get(i).getLnd_brok_nm().toString());
                    jsonparam.put("num", "" + broker_details.get(i).getLnd_brok_num().toString());
                    jsonparam.put("email", "" + broker_details.get(i).getLnd_brok_email().toString());
                    jsonparam.put("fees", "" + broker_details.get(i).getLnd_brok_fees().toString());
                    jsonparam.put("agree_fee", "" + broker_details.get(i).getLnd_brok_aagfees().toString());
                    if(i== broker_details.size()-1){
                        submitbroker();
                    }else{}
                    submitbroker(url, jsonparam);
                }
            }
        }
    }

    public void submitbroker(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Land.this);
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
                        submitpartavailable();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        submitpartavailable();
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



    public  void submitpartavailable() {
        if (!connectionDetector.isConnectingToInternet()) {
            cd.showNetworkDialog();
        } else {


            progressBar.setVisibility(View.VISIBLE);
            part_ava_details = databse_handler.getlandpartavailable();
            for (int i = 0; i < part_ava_details.size(); i++) {
                String url = ServerUrl.serverLand_partavailable;
                Map<String, String> jsonparam = new HashMap<String, String>();
                jsonparam.put("p_id",""+p_id);
                jsonparam.put("nm_of_comp",""+part_ava_details.get(i).getLnd_part_ava_cn().toString());
                jsonparam.put("c_persn_nm",""+part_ava_details.get(i).getLnd_part_ava_cpn().toString());
                jsonparam.put("cont_num",""+part_ava_details.get(i).getLnd_part_ava_num().toString());
                jsonparam.put("s_email",""+part_ava_details.get(i).getLnd_part_ava_email().toString());
                jsonparam.put("s_area_sold",""+part_ava_details.get(i).getLnd_part_ava_area_sold().toString());
                jsonparam.put("s_sale_price",""+part_ava_details.get(i).getLnd_part_ava_salesprice().toString());
                jsonparam.put("s_sold_by",""+part_ava_details.get(i).getLnd_part_ava_sold_by_grpapl().toString());
                submitpartavailable(url,jsonparam);


            }
        }
    }

    public void submitpartavailable(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Land.this);
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

                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);

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


                    imageView.setImageURI(mImageUri);
                        imageView.setVisibility(View.VISIBLE);
                        imageView1.setVisibility(View.GONE);
                        imageView2.setVisibility(View.GONE);
                        imageView3.setVisibility(View.GONE);
                        imageView4.setVisibility(View.GONE);

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
                                imageView.setImageURI(mArrayUri.get(0));
                                imageView.setVisibility(View.VISIBLE);
                                imageView1.setVisibility(View.GONE);
                                imageView2.setVisibility(View.GONE);
                                imageView3.setVisibility(View.GONE);
                                imageView4.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                imageView.setImageURI(mArrayUri.get(0));
                                imageView1.setImageURI(mArrayUri.get(1));
                                imageView.setVisibility(View.VISIBLE);
                                imageView1.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.GONE);
                                imageView3.setVisibility(View.GONE);
                                imageView4.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                imageView.setImageURI(mArrayUri.get(0));
                                imageView1.setImageURI(mArrayUri.get(1));
                                imageView2.setImageURI(mArrayUri.get(2));
                                imageView.setVisibility(View.VISIBLE);
                                imageView1.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.VISIBLE);
                                imageView3.setVisibility(View.GONE);
                                imageView4.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==4)
                            {
                                imageView.setImageURI(mArrayUri.get(0));
                                imageView1.setImageURI(mArrayUri.get(1));
                                imageView2.setImageURI(mArrayUri.get(2));
                                imageView3.setImageURI(mArrayUri.get(3));
                                imageView.setVisibility(View.VISIBLE);
                                imageView1.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.VISIBLE);
                                imageView3.setVisibility(View.VISIBLE);
                                imageView4.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==5)
                            {
                                imageView.setImageURI(mArrayUri.get(0));
                                imageView1.setImageURI(mArrayUri.get(1));
                                imageView2.setImageURI(mArrayUri.get(2));
                                imageView3.setImageURI(mArrayUri.get(3));
                                imageView4.setImageURI(mArrayUri.get(4));
                                imageView.setVisibility(View.VISIBLE);
                                imageView1.setVisibility(View.VISIBLE);
                                imageView2.setVisibility(View.VISIBLE);
                                imageView3.setVisibility(View.VISIBLE);
                                imageView4.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                imageView.setVisibility(View.GONE);
                                imageView1.setVisibility(View.GONE);
                                imageView2.setVisibility(View.GONE);
                                imageView3.setVisibility(View.GONE);
                                imageView4.setVisibility(View.GONE);
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
            } else {
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void submitland(View view) {
    }

    public void back(View view) {
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracking.getLastLocation();
            double var= gpsTracking.getLatitude();
            double var1= gpsTracking.getLongitude();
         //   Toast.makeText(this,""+var +" "+var1,Toast.LENGTH_LONG).show();

            latitude.setText(""+var);
            longtutute.setText(""+var1);

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

