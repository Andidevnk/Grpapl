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
import com.example.nk.grpapl.Models.Office_space_part_available;
import com.example.nk.grpapl.Models.Office_space_rented;
import com.example.nk.grpapl.Models.OfficespaceBroker;
import com.example.nk.grpapl.Models.OfficespaceManager;
import com.example.nk.grpapl.Models.OfficespaceOwner;
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

public class Office_space extends AppCompatActivity {


    private RequestQueue requestQueue;
    String result;
    ScrollView sv;


    private JSONObject jsonObject;
    ArrayList<Uri> imagesUriList;
    ArrayList<String> encodedImageList;
    String imageURI;


    public static String p_id;
    ServerUrl serverUrl;
    CommonDialog cd;
    SP sp;
    ProgressBar progressBar;
    ConnectionDetector connectionDetector;
    Databse_Handler databse_handler;
    AppCompatActivity mActivity = Office_space.this;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;


    EditText buildingname, address, city, state, pin, totalnumberfloor, totalnumberbasement, totalarea, typicalfloorarea, efficienacy, provisionforsin, proposedsolution,
            rentofsignature, unitnumber, floornumber, towernumber, totalleaarea, totalusablearea, efficincy1, floorplan, charges, houoperation, houresoperationchargesbeyond, freecarparking,
            additionalcarparkingcharges, airconditioning, electricitycharges, restroom, proposelbuget, timelineofhandover, lockin, additionalrentoffiyout, quatedrentforbareshell,
            sizeofworkstation, numberofcabin, numberofmeetingroom, numberofconferenceroom, numberofworkstation, breakarea, timelinehandoveer1, lockin1, quatedrentoffurnised, spaceavilabe,
            nameofcompany, contactpersonnname, designation, contactnumber, email, arerented, floor, spaceavailabe1, lockinexpiry, leaseexpiry, rent, rentescplation, rentescplationperiod,
            nameofcompany1, contactpersoanname1, designation1, conatctnumber1, email1, lockinexpiry1, leaseexpiry1, rent1, rentescplayion1, rentescaplationperiod1, quatedsalesprice,
            roi, powernackup;
    Button btnland, btnmanger, btnbroker, submit, uploadpicture, btnpartavalible, btnrentad;
    EditText owner_cn, owner_cpm, owner_num, owner_ea;
    EditText mngrcn, mngr_nm, mngr_num, mngr_email;
    EditText brok_cn, brok_nm, brok_num, brok_em, brok_fs, brok_afs;
    RadioGroup rg1, rg2, rg3, rg4, rg5, rg6, rg7, rg8;
    RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12, rb13, rb14, rb15, rb16;

    Spinner usagespinner;
    Spinner statusspinner;
    Spinner status2spinner;
    int war, war2;
    EditText latti, longt;
    LinearLayout uploadinterlayout;
    CardView uploadcardview;

    ArrayList<OfficespaceOwner> office_space_owner_details = new ArrayList<OfficespaceOwner>();
    ArrayList<OfficespaceManager> office_space_manager_details = new ArrayList<OfficespaceManager>();
    ArrayList<OfficespaceBroker> office_space_broker_details = new ArrayList<OfficespaceBroker>();
    ArrayList<Office_space_part_available> office_space_part_availables_deatails = new ArrayList<Office_space_part_available>();
    ArrayList<Office_space_rented> office_space_rented_deatails = new ArrayList<Office_space_rented>();
    private static final int PICK_FROM_GALLERY = 1;
    String imageEncoded;
    List<String> imagesEncodedList;
    Databse_Handler mdatabse_handler;

    GPSTracking gpsTracking;

    private int REQUEST_FINE_LOCATION = 2000; /* 2 sec */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_office_space);

        sp = SP.getInstance();
        connectionDetector = new ConnectionDetector(mActivity);
        databse_handler = new Databse_Handler(this);
        progressBar = (ProgressBar) findViewById(R.id.ptogressbar);


        jsonObject = new JSONObject();
        encodedImageList = new ArrayList<>();


        buildingname = (EditText) findViewById(R.id.BuildingNameofficespace);
        address = (EditText) findViewById(R.id.Addressofficespace);
        city = (EditText) findViewById(R.id.cityofficespace);
        state = (EditText) findViewById(R.id.Stateofficespace);
        pin = (EditText) findViewById(R.id.pinofficespace);
        totalnumberfloor = (EditText) findViewById(R.id.totalnumberfloorofficespaceindex);
        totalnumberbasement = (EditText) findViewById(R.id.Totalnumberofbasementindex11);
        totalarea = (EditText) findViewById(R.id.Totalareaofficespaceindex);
        typicalfloorarea = (EditText) findViewById(R.id.typicalfloorareaofficespaceindex);
        efficienacy = (EditText) findViewById(R.id.efficiancyofficespaceindex);
        provisionforsin = (EditText) findViewById(R.id.ProvisionforSignageBoardindex);
        proposedsolution = (EditText) findViewById(R.id.ProposedSolutionindex);
        rentofsignature = (EditText) findViewById(R.id.RentforSignageBoardindex);
        unitnumber = (EditText) findViewById(R.id.UnitNumberofficespace);
        floornumber = (EditText) findViewById(R.id.FloorNumberofficespace);
        towernumber = (EditText) findViewById(R.id.TowerNumberofficespace);
        totalleaarea = (EditText) findViewById(R.id.TotalLeasableAreaSuperAreaofficespace);
        totalusablearea = (EditText) findViewById(R.id.TotalUsableAreaCarpetAreaofficespace);
        efficincy1 = (EditText) findViewById(R.id.Efficiencyofficespace);
        floorplan = (EditText) findViewById(R.id.FloorPlanofficespace);
        charges = (EditText) findViewById(R.id.Chargesofficespace);
        houoperation = (EditText) findViewById(R.id.HoursofOperationofficespace);
        houresoperationchargesbeyond = (EditText) findViewById(R.id.HoursofOperationofficespace);
        freecarparking = (EditText) findViewById(R.id.FreeCarParkingofficespace);
        additionalcarparkingcharges = (EditText) findViewById(R.id.AdditionalCarParkingChargesofficespace);
        airconditioning = (EditText) findViewById(R.id.AirConditioningofficespace);
        electricitycharges = (EditText) findViewById(R.id.ElectricityChargesofficespace);
        powernackup = (EditText) findViewById(R.id.PowerBackupofficespace);
        restroom = (EditText) findViewById(R.id.Restroomofficespace);
        proposelbuget = (EditText) findViewById(R.id.ProposedBudgetofficespace);
        timelineofhandover = (EditText) findViewById(R.id.TimelineofHandoverofficespace);
        lockin = (EditText) findViewById(R.id.Lockinofficespace);
        additionalrentoffiyout = (EditText) findViewById(R.id.AdditionalRentforFitOutofficespace);
        quatedrentforbareshell = (EditText) findViewById(R.id.QuotedRentforBareShellWarmShellofficespace);
        sizeofworkstation = (EditText) findViewById(R.id.SizeofWorkstationofficespace);
        numberofcabin = (EditText) findViewById(R.id.NumberofCabinsofficespace);
        numberofmeetingroom = (EditText) findViewById(R.id.NumberofMeetingRoomofficespace);
        numberofconferenceroom = (EditText) findViewById(R.id.NumberofConferenceRoomofficespace);
        numberofworkstation = (EditText) findViewById(R.id.NumberofWorkstationsofficespace);
        breakarea = (EditText) findViewById(R.id.BreakoutAreaofficespace);
        timelinehandoveer1 = (EditText) findViewById(R.id.Timelineofandoverofficespace);
        lockin1 = (EditText) findViewById(R.id.Lockinofficespace1);
        quatedrentoffurnised = (EditText) findViewById(R.id.QuotedRentforFurnishedofficespace);
        spaceavilabe = (EditText) findViewById(R.id.SpaceAvailableofficespace);
        nameofcompany = (EditText) findViewById(R.id.NameoftheCompanyofficespacepart);
        contactpersonnname = (EditText) findViewById(R.id.ContactPersonNameofficespacepart);
        designation = (EditText) findViewById(R.id.Designationofficespacepart);
        contactnumber = (EditText) findViewById(R.id.ContactNumberofficespace);
        email = (EditText) findViewById(R.id.Emailofficespace);
        arerented = (EditText) findViewById(R.id.AreaRentedofficespacepart);
        floor = (EditText) findViewById(R.id.Floorofficespacepart);
        spaceavailabe1 = (EditText) findViewById(R.id.Spaceavailableofficespace);
        lockinexpiry = (EditText) findViewById(R.id.LockINExpiryofficespacepart);
        leaseexpiry = (EditText) findViewById(R.id.LeaseExpiryofficespacepart);
        rent = (EditText) findViewById(R.id.Rentofficespacepart);
        rentescplation = (EditText) findViewById(R.id.RentEscalationofficespacepart);
        rentescplationperiod = (EditText) findViewById(R.id.RentEscalationPeriodofficespacepart);
        nameofcompany1 = (EditText) findViewById(R.id.NameoftheCompanyofficespacerentedto);
        contactpersoanname1 = (EditText) findViewById(R.id.ContactPersonNameofficespacerentad);
        designation1 = (EditText) findViewById(R.id.Designationofficespacerentadto);
        conatctnumber1 = (EditText) findViewById(R.id.ContactNumberofficespacerentad);
        email1 = (EditText) findViewById(R.id.Emailofficespacerentad);
        lockinexpiry1 = (EditText) findViewById(R.id.LockINExpiryofficespacerentad);
        leaseexpiry1 = (EditText) findViewById(R.id.LeaseExpiryofficespacerentadto);
        rent1 = (EditText) findViewById(R.id.Rentofficespacerentad);
        rentescplayion1 = (EditText) findViewById(R.id.RentEscalationofficespacerentad);
        rentescaplationperiod1 = (EditText) findViewById(R.id.RentEscalationPeriodofficespacerentad);
        quatedsalesprice = (EditText) findViewById(R.id.QuotedSalesPriceofficespace);
        roi = (EditText) findViewById(R.id.ROIofficespace);

        submit = (Button) findViewById(R.id.submit);
        uploadpicture = (Button) findViewById(R.id.interiorofficespace);
        btnpartavalible = (Button) findViewById(R.id.occupantofficespace);
        btnrentad = (Button) findViewById(R.id.occupantofficespac);


        rg1 = (RadioGroup) findViewById(R.id.group1);
        rg2 = (RadioGroup) findViewById(R.id.group2);
        rg3 = (RadioGroup) findViewById(R.id.group3);
        rg4 = (RadioGroup) findViewById(R.id.group4);
        rg5 = (RadioGroup) findViewById(R.id.group5);
        rg6 = (RadioGroup) findViewById(R.id.group6);
        rg7 = (RadioGroup) findViewById(R.id.group7);
        rg8 = (RadioGroup) findViewById(R.id.group8);


        rb1 = (RadioButton) findViewById(R.id.llyes1);
        rb2 = (RadioButton) findViewById(R.id.llno1);
        rb3 = (RadioButton) findViewById(R.id.statelandyes);
        rb4 = (RadioButton) findViewById(R.id.statelandno);
        rb5 = (RadioButton) findViewById(R.id.gramyes);
        rb6 = (RadioButton) findViewById(R.id.gramno);
        rb7 = (RadioButton) findViewById(R.id.muncipalyes);
        rb8 = (RadioButton) findViewById(R.id.muncipalno);
        rb9 = (RadioButton) findViewById(R.id.occupacyyes);
        rb10 = (RadioButton) findViewById(R.id.occupacyno);
        rb11 = (RadioButton) findViewById(R.id.nocyes);
        rb12 = (RadioButton) findViewById(R.id.nocno);
        rb13 = (RadioButton) findViewById(R.id.grapalyes);
        rb14 = (RadioButton) findViewById(R.id.grapaplno);
        rb15 = (RadioButton) findViewById(R.id.grpaplyes);
        rb16 = (RadioButton) findViewById(R.id.grpaplno);


        img1 = (ImageView) findViewById(R.id.interpic1);
        img2 = (ImageView) findViewById(R.id.interpic2);
        img3 = (ImageView) findViewById(R.id.interpic3);
        img4 = (ImageView) findViewById(R.id.interpic4);
        img5 = (ImageView) findViewById(R.id.interpic5);
        img6 = (ImageView) findViewById(R.id.interpic6);
        img7 = (ImageView) findViewById(R.id.interpic7);
        img8 = (ImageView) findViewById(R.id.interpic8);
        img9 = (ImageView) findViewById(R.id.interpic9);


        uploadcardview = (CardView) findViewById(R.id.cardviewinter);
        uploadinterlayout = (LinearLayout) findViewById(R.id.interiorpics);


        latti = (EditText) findViewById(R.id.latitude);
        longt = (EditText) findViewById(R.id.longitute);


        owner_cn = (EditText) findViewById(R.id.ownernameofficespace);
        owner_cpm = (EditText) findViewById(R.id.companynameofficespace);
        owner_num = (EditText) findViewById(R.id.Numberofficespace1);
        owner_ea = (EditText) findViewById(R.id.EmailAddressofficespace);
        btnland = (Button) findViewById(R.id.owner3officespace);

        mngrcn = (EditText) findViewById(R.id.managercompanynameofficespace);
        mngr_nm = (EditText) findViewById(R.id.managernameofficespace);
        mngr_num = (EditText) findViewById(R.id.numbermanagerofficespace);
        mngr_email = (EditText) findViewById(R.id.emailmanagerofficespace);
        btnmanger = (Button) findViewById(R.id.manager3officespace);


        brok_cn = (EditText) findViewById(R.id.brokercompanynameofficespace);
        brok_nm = (EditText) findViewById(R.id.brokernameofficespace);
        brok_num = (EditText) findViewById(R.id.numberbrokerofficespace);
        brok_em = (EditText) findViewById(R.id.emailbrokerofficespace);
        brok_fs = (EditText) findViewById(R.id.feesbrokerofficespace);
        brok_afs = (EditText) findViewById(R.id.agredfeesbrokerofficespace);
        btnbroker = (Button) findViewById(R.id.broker3officespace);


        uploadpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Office_space.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Office_space.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
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


        mdatabse_handler = new Databse_Handler(this);


        gpsTracking = new GPSTracking(this, Office_space.this);


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


                mdatabse_handler.addownerofficespace(ownercn, ownercpn, ownernum, owneremail);
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();

            }
        });

        btnmanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mngrcnn = mngrcn.getText().toString().trim();
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


                mdatabse_handler.addmanagerofficespace(mngrcnn, mngrnm, mngrnum, mngremail);
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();


            }
        });

        btnbroker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String brokcn = brok_cn.getText().toString().trim();
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


                mdatabse_handler.addbrokerofficespace(brokcn, broknm, broknum, brokem, brokfees, brokafees);
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();


            }
        });

        btnpartavalible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ospartavaspaceavailble = spaceavilabe.getText().toString().trim();
                final String ospartavacn = nameofcompany.getText().toString().trim();
                final String ospartavacpn = contactpersonnname.getText().toString().trim();
                final String ospartavades = designation.getText().toString().trim();
                final String ospartavacnum = contactnumber.getText().toString().trim();
                final String ospartavaem = email.getText().toString().trim();
                final String ospartavaarearentad = arerented.getText().toString().trim();
                final String ospartavafloor = floor.getText().toString().trim();
                final String ospartavaspaceava = spaceavailabe1.getText().toString().trim();
                final String ospartavalocinex = lockinexpiry.getText().toString().trim();
                final String ospartavaleasex = leaseexpiry.getText().toString().trim();
                final String ospartavarent = rent.getText().toString().trim();
                final String ospartavarentesc = rentescplation.getText().toString().trim();
                final String ospartavarentescperiod = rentescplationperiod.getText().toString().trim();
                String soldby1 = "";
                if (rg7.getCheckedRadioButtonId() == R.id.grapalyes) {
                    soldby1 = "1";
                    Toast.makeText(mActivity, "" + soldby1, Toast.LENGTH_SHORT).show();
                } else {
                    soldby1 = "0";
                    Toast.makeText(mActivity, "" + soldby1, Toast.LENGTH_SHORT).show();
                }


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (ospartavaem.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if (ospartavacnum.length() == 10) {

                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }


                if (!TextUtils.isEmpty(ospartavaspaceavailble) || !TextUtils.isEmpty(ospartavacn) || !TextUtils.isEmpty(ospartavacpn) || !TextUtils.isEmpty(ospartavades) || !TextUtils.isEmpty(ospartavacnum) || !TextUtils.isEmpty(ospartavaem)
                        || !TextUtils.isEmpty(ospartavaarearentad) || !TextUtils.isEmpty(ospartavafloor) || !TextUtils.isEmpty(ospartavaspaceava) || !TextUtils.isEmpty(ospartavalocinex) || !TextUtils.isEmpty(ospartavaleasex) || !TextUtils.isEmpty(ospartavarent)
                        || !TextUtils.isEmpty(ospartavarentesc) || !TextUtils.isEmpty(ospartavarentescperiod) || !TextUtils.isEmpty(soldby1)) {
                    mdatabse_handler.addofficespacepartava(ospartavaspaceavailble, ospartavacn, ospartavacpn, ospartavades, ospartavacnum, ospartavaem, ospartavaarearentad,
                            ospartavafloor, ospartavaspaceava, ospartavalocinex, ospartavaleasex, ospartavarent, ospartavarentesc, ospartavarentescperiod, soldby1);
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), " Data Values not saved,Retry", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnrentad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ospartavacn = nameofcompany1.getText().toString().trim();
                final String ospartavacpn = contactpersoanname1.getText().toString().trim();
                final String ospartavades = designation1.getText().toString().trim();
                final String ospartavacnum = conatctnumber1.getText().toString().trim();
                final String ospartavaem = email1.getText().toString().trim();
                final String ospartavalocinex = lockinexpiry1.getText().toString().trim();
                final String ospartavaleasex = leaseexpiry1.getText().toString().trim();
                final String ospartavarent = rent1.getText().toString().trim();
                final String ospartavarentesc = rentescplayion1.getText().toString().trim();
                final String ospartavarentescperiod = rentescaplationperiod1.getText().toString().trim();
                String soldby2 = "";
                if (rg8.getCheckedRadioButtonId() == R.id.grpaplyes) {
                    soldby2 = "1";
                    Toast.makeText(mActivity, "" + soldby2, Toast.LENGTH_SHORT).show();
                } else {
                    soldby2 = "0";
                    Toast.makeText(mActivity, "" + soldby2, Toast.LENGTH_SHORT).show();
                }


                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (ospartavaem.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if (ospartavacnum.length() == 10) {

                } else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }


               /* if (!TextUtils.isEmpty(ospartavacn) || !TextUtils.isEmpty(ospartavacpn) || !TextUtils.isEmpty(ospartavades) || !TextUtils.isEmpty(ospartavacnum) || !TextUtils.isEmpty(ospartavaem)
                        || !TextUtils.isEmpty(ospartavalocinex) || !TextUtils.isEmpty(ospartavaleasex) || !TextUtils.isEmpty(ospartavarent)
                        || !TextUtils.isEmpty(ospartavarentesc) || !TextUtils.isEmpty(ospartavarentescperiod) || !TextUtils.isEmpty(soldby2)) {
                    */mdatabse_handler.addofficespacerented(ospartavacn, ospartavacpn, ospartavades, ospartavacnum, ospartavaem,
                            ospartavalocinex, ospartavaleasex, ospartavarent, ospartavarentesc, ospartavarentescperiod, soldby2);
                    Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();

/*                } else {
                    Toast.makeText(getApplicationContext(), " Data Values not saved,Retry", Toast.LENGTH_LONG).show();
                }*/
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_part_available = email.getText().toString();
                final String num_part_ava = contactnumber.getText().toString();
                final String email_sold = email1.getText().toString();
                final String num_sold = conatctnumber1.getText().toString();
                final String ownernum = owner_num.getText().toString().trim();
                final String owneremail = owner_ea.getText().toString().trim();
                final String mngrnum = mngr_num.getText().toString().trim();
                final String mngremail = mngr_email.getText().toString().trim();
                final String broknum = brok_num.getText().toString().trim();
                final String brokem = brok_em.getText().toString().trim();


              /*  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (email_part_available.matches(emailPattern)||email_sold.matches(emailPattern)||owneremail.matches(emailPattern)||mngremail.matches(emailPattern)|| brokem.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if(num_part_ava.length()==10||num_sold.length()==10||ownernum.length()==10||mngrnum.length()==10||broknum.length()==10){

                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }

                */
                Upload_image();
                submitdata();

            }
        });

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.bareshellorwarmshell);
        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.iffurnished);

        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.partavailable);
        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.ifrentad);


        checkPermissions();


        usagespinner = (Spinner) findViewById(R.id.officespaceusage);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Office_space.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.officespaceusage));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usagespinner.setAdapter(myadapter);


        statusspinner = (Spinner) findViewById(R.id.Status);
        ArrayAdapter<String> mmyadapter = new ArrayAdapter<String>(Office_space.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Status));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusspinner.setAdapter(mmyadapter);


        status2spinner = (Spinner) findViewById(R.id.Status2);
        ArrayAdapter<String> mmmyadapter = new ArrayAdapter<String>(Office_space.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Status2));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status2spinner.setAdapter(mmmyadapter);


        status2spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                war2 = position;

                if (war2 == 2) {
                    linearLayout2.setVisibility(view.VISIBLE);
                    linearLayout3.setVisibility(view.GONE);
                } else if (war2 == 3) {
                    linearLayout3.setVisibility(view.VISIBLE);
                    linearLayout2.setVisibility(view.GONE);
                } else {
                    linearLayout2.setVisibility(view.GONE);
                    linearLayout3.setVisibility(view.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        statusspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                war = position;
                if (war == 1 || war == 2) {
                    linearLayout1.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                } else if (war == 3) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
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

            String llradiogroup = "";
            if (rg1.getCheckedRadioButtonId() == R.id.llyes1)

            {
                llradiogroup = "1";
                Toast.makeText(mActivity, "" + llradiogroup, Toast.LENGTH_SHORT).show();

            } else {
                llradiogroup = "0";
                Toast.makeText(mActivity, "" + llradiogroup, Toast.LENGTH_SHORT).show();

            }

            String stateradiogroup = "";
            if (rg2.getCheckedRadioButtonId() == R.id.statelandyes) {
                stateradiogroup = "1";
                Toast.makeText(mActivity, "" + stateradiogroup, Toast.LENGTH_SHORT).show();

            } else {
                stateradiogroup = "0";
                Toast.makeText(mActivity, "" + stateradiogroup, Toast.LENGTH_SHORT).show();

            }
            String grampanchradiogroup = "";
            if (rg3.getCheckedRadioButtonId() == R.id.gramyes) {
                grampanchradiogroup = "1";
                Toast.makeText(mActivity, "" + grampanchradiogroup, Toast.LENGTH_SHORT).show();

            } else {
                grampanchradiogroup = "0";
                Toast.makeText(mActivity, "" + grampanchradiogroup, Toast.LENGTH_SHORT).show();

            }
            String muncipalradiogroup = "";
            if (rg3.getCheckedRadioButtonId() == R.id.muncipalyes) {
                muncipalradiogroup = "1";
                Toast.makeText(mActivity, "" + muncipalradiogroup, Toast.LENGTH_SHORT).show();

            } else {
                muncipalradiogroup = "0";
                Toast.makeText(mActivity, "" + muncipalradiogroup, Toast.LENGTH_SHORT).show();

            }
            String occupanradiogroup = "";
            if (rg4.getCheckedRadioButtonId() == R.id.occupacyyes) {
                occupanradiogroup = "1";
                Toast.makeText(mActivity, "" + occupanradiogroup, Toast.LENGTH_SHORT).show();

            } else {
                occupanradiogroup = "0";
                Toast.makeText(mActivity, "" + occupanradiogroup, Toast.LENGTH_SHORT).show();

            }

            String nocradiogroup = "";
            if (rg4.getCheckedRadioButtonId() == R.id.noc_fire_yes) {
                nocradiogroup = "1";
                Toast.makeText(mActivity, "" + nocradiogroup, Toast.LENGTH_SHORT).show();

            } else {
                nocradiogroup = "0";
                Toast.makeText(mActivity, "" + nocradiogroup, Toast.LENGTH_SHORT).show();

            }


            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverOfficespace;
            Map<String, String> jsonparam = new HashMap<String, String>();
            jsonparam.put("use_spinner", "" + usagespinner.getSelectedItem().toString());
            jsonparam.put("buil_name", "" + buildingname.getText().toString());
            jsonparam.put("address", "" + address.getText().toString());
            jsonparam.put("city", "" + city.getText().toString());
            jsonparam.put("state", "" + state.getText().toString());
            jsonparam.put("pin", "" + pin.getText().toString());
            jsonparam.put("latitude", "" + latti.getText().toString());
            jsonparam.put("longitute", "" + longt.getText().toString());
            jsonparam.put("totl_num_floor", "" + totalnumberfloor.getText().toString());
            jsonparam.put("totl_num_base", "" + totalnumberbasement.getText().toString());
            jsonparam.put("totl_area", "" + totalarea.getText().toString());
            jsonparam.put("typical_floor_area", "" + typicalfloorarea.getText().toString());
            jsonparam.put("efficiency", "" + efficienacy.getText().toString());
            jsonparam.put("provi_sign_board", "" + provisionforsin.getText().toString());
            jsonparam.put("props_solut", "" + proposedsolution.getText().toString());
            jsonparam.put("rent_sign_board", "" + rentofsignature.getText().toString());
            jsonparam.put("unit_num", "" + unitnumber.getText().toString());
            jsonparam.put("floor_num", "" + floornumber.getText().toString());
            jsonparam.put("tower_num", "" + towernumber.getText().toString());
            jsonparam.put("totl_leas_area", "" + totalleaarea.getText().toString());
            jsonparam.put("totl_usable_area", "" + totalusablearea.getText().toString());
            jsonparam.put("effici", "" + efficincy1.getText().toString());
            jsonparam.put("floor_plan", "" + floorplan.getText().toString());
            jsonparam.put("charges", "" + charges.getText().toString());
            jsonparam.put("hou_operation", "" + houoperation.getText().toString());
            jsonparam.put("charg_beyond_oper", "" + houresoperationchargesbeyond.getText().toString());
            jsonparam.put("free_car_par", "" + freecarparking.getText().toString());
            jsonparam.put("addi_car_par_cha", "" + additionalcarparkingcharges.getText().toString());
            jsonparam.put("type", "" + statusspinner.getSelectedItem().toString());
            jsonparam.put("air_condi", "" + airconditioning.getText().toString());
            jsonparam.put("elect_chare", "" + electricitycharges.getText().toString());
            jsonparam.put("power_back", "" + powernackup.getText().toString());
            jsonparam.put("rest_room", "" + restroom.getText().toString());
            jsonparam.put("ll_fit_out", "" + llradiogroup);
            jsonparam.put("prop_budget", "" + proposelbuget.getText().toString());
            jsonparam.put("time_handover", "" + timelineofhandover.getText().toString());
            jsonparam.put("lok_in", "" + lockin.getText().toString());
            jsonparam.put("addi_rent_fit_out", "" + additionalrentoffiyout.getText().toString());
            jsonparam.put("quated_rent_for_b_w_shell", "" + quatedrentforbareshell.getText().toString());
            jsonparam.put("size_workstation", "" + sizeofworkstation.getText().toString());
            jsonparam.put("num_cabins", "" + numberofcabin.getText().toString());
            jsonparam.put("num_meet_room", "" + numberofmeetingroom.getText().toString());
            jsonparam.put("num_conf_room", "" + numberofconferenceroom.getText().toString());
            jsonparam.put("num_workstation", "" + numberofworkstation.getText().toString());
            jsonparam.put("brek_o_area", "" + arerented.getText().toString());
            jsonparam.put("time_hand", "" + timelinehandoveer1.getText().toString());
            jsonparam.put("lo_in", "" + lockin1.getText().toString());
            jsonparam.put("quated_rent_furnished", "" + quatedrentoffurnised.getText().toString());
            jsonparam.put("state_lnd_dov_aut", "" + stateradiogroup);
            jsonparam.put("gram_panch", "" + grampanchradiogroup);
            jsonparam.put("munci_auth", "" + muncipalradiogroup);
            jsonparam.put("occ_certifi", "" + occupanradiogroup);
            jsonparam.put("noc_fire", "" + nocradiogroup);
            jsonparam.put("quated_sales_prce", "" + quatedsalesprice.getText().toString());
            jsonparam.put("roi", "" + roi.getText().toString());
            submitdata(url, jsonparam);

        }
    }

    public void submitdata(String url, Map<String, String> jsonparam) {
        requestQueue = Volley.newRequestQueue(Office_space.this);
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
                        submitowner();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        submitowner();
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

    public void submitowner() {
        if (!connectionDetector.isConnectingToInternet()) {
            cd.showNetworkDialog();
        } else {
            office_space_owner_details = databse_handler.getofficespaceowner();

           if (office_space_owner_details.size()==0)
            {
                submitmanager();
            }
            else {
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 0; i < office_space_owner_details.size(); i++) {
                String url = ServerUrl.serverOfficespace_owner;
                Map<String, String> jsonparam = new HashMap<String, String>();
                jsonparam.put("pid", "" + p_id);
                jsonparam.put("name", "" + office_space_owner_details.get(i).getOs_own_comp_name());
                jsonparam.put("comname", "" + office_space_owner_details.get(i).getOs_own_cont_nm());
                jsonparam.put("num", "" + office_space_owner_details.get(i).getOs_owner_num());
                jsonparam.put("email", "" + office_space_owner_details.get(i).getOs_own_email());
                if(i== office_space_owner_details.size()-1){
                    submitmanager();
                }else{}
                submitowner(url, jsonparam);
                }
            }
        }
    }

    public void submitowner(String url, Map<String, String> jsonparam) {
        requestQueue = Volley.newRequestQueue(Office_space.this);
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
                        submitmanager();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        submitmanager();

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

   public  void submitmanager(){
        if(!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else{
            office_space_manager_details = databse_handler.getofficespacemanager();

            if(office_space_manager_details.size()==0)
            {
                submitbroker();
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < office_space_manager_details.size(); i++) {
                    String url = ServerUrl.serverOfficespace_manager;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("pid", "" + p_id);
                    jsonparam.put("mngr_cn", "" + office_space_manager_details.get(i).getOs_mng_cn());
                    jsonparam.put("name", "" + office_space_manager_details.get(i).getOs_mng_nm());
                    jsonparam.put("num", "" + office_space_manager_details.get(i).getOs_mng_num());
                    jsonparam.put("e_mail", "" + office_space_manager_details.get(i).getOs_mng_email());
                    if(i== office_space_manager_details.size()-1){
                        submitbroker();
                    }else{}
                    submitmanager(url, jsonparam);
                }
            }
        }
    }

    public void submitmanager(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Office_space.this);
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
            office_space_broker_details = databse_handler.getofficespacebroker();

            if(office_space_broker_details.size()==0)
            {
                submitpartavailable();
            }
            else {


                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < office_space_broker_details.size(); i++) {
                    String url = ServerUrl.serverOfficespace_broker;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("pid", "" + p_id);
                    jsonparam.put("com_name", "" + office_space_broker_details.get(i).getOs_brok_cn());
                    jsonparam.put("name", "" + office_space_broker_details.get(i).getOs_brok_nm());
                    jsonparam.put("num", "" + office_space_broker_details.get(i).getOs_brok_num());
                    jsonparam.put("email", "" + office_space_broker_details.get(i).getOs_brok_email());
                    jsonparam.put("fee", "" + office_space_broker_details.get(i).getOs_brrrrok_fees());
                    jsonparam.put("efee", "" + office_space_broker_details.get(i).getOs_brok_aagfees());
                    if(i== office_space_broker_details.size()-1){
                        submitpartavailable();
                    }else{}
                    submitbroker(url, jsonparam);
                }
            }
        }
    }

   public void submitbroker(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Office_space.this);
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


            office_space_part_availables_deatails = databse_handler.getofficespacepartava();
            if(office_space_part_availables_deatails.size()==0)
            {
                submitrented();
            }
            else
            {
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 0; i < office_space_part_availables_deatails.size(); i++) {
                String url = ServerUrl.serverOfficespace_part_ava;
                Map<String, String> jsonparam = new HashMap<String, String>();
                jsonparam.put("pid", "" + p_id);
                jsonparam.put("space_ava", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_space_ava());
                jsonparam.put("cn_name", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_com_nm());
                jsonparam.put("con_per_name", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_con_per_nm());
                jsonparam.put("desgin", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_desig());
                jsonparam.put("con_num", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_con_num());
                jsonparam.put("email", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_em());
                jsonparam.put("area_rentad", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_area_rentad());
                jsonparam.put("flor", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_floor());
                jsonparam.put("spac_avalab", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_spac_ava());
                jsonparam.put("lock_in_expiry", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_loc_in_expiry());
                jsonparam.put("lease_expiry", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_lease_expiry());
                jsonparam.put("rent", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_rent());
                jsonparam.put("rent_esc", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_rent_esc());
                jsonparam.put("rent_esc_period", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_rent_esc_period());
                jsonparam.put("rent_by_grpapl", "" + office_space_part_availables_deatails.get(i).getOs_part_ava_rented_by_grpapl());
                if(i== office_space_part_availables_deatails.size()-1){
                    submitrented();
                }else{}
                submitpartavailable(url, jsonparam);


            }
            }
        }
    }

    public void submitpartavailable(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Office_space.this);
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
                        submitrented();

                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        submitrented();

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

    public  void submitrented() {
        if (!connectionDetector.isConnectingToInternet()) {
            cd.showNetworkDialog();
        } else {


            office_space_rented_deatails = databse_handler.getofficespacerented();
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 0; i < office_space_rented_deatails.size(); i++) {
                String url = ServerUrl.serverOfficespace_rented;
                Map<String, String> jsonparam = new HashMap<String, String>();
                jsonparam.put("pid",""+p_id);
                jsonparam.put("cm_name",""+office_space_rented_deatails.get(i).getOs_rented_com_nm());
                jsonparam.put("con_per_name",""+office_space_rented_deatails.get(i).getOs_rented_con_per_nm());
                jsonparam.put("desig",""+office_space_rented_deatails.get(i).getOs_rented_desig());
                jsonparam.put("con_num",""+office_space_rented_deatails.get(i).getOs_rented_con_num());
                jsonparam.put("email",""+office_space_rented_deatails.get(i).getOs_rented_em());
                jsonparam.put("lock_inexpiry",""+office_space_rented_deatails.get(i).getOs_rented_in_expiry());
                jsonparam.put("lease_expiry",""+office_space_rented_deatails.get(i).getOs_rented_lease_expiry());
                jsonparam.put("rent",""+office_space_rented_deatails.get(i).getOs_rented_rent());
                jsonparam.put("rent_esc",""+office_space_rented_deatails.get(i).getOs_rented_rent_esc());
                jsonparam.put("rent_esc_period",""+office_space_rented_deatails.get(i).getOs_rented_esc_period());
                jsonparam.put("rent_by_grpapl",""+office_space_rented_deatails.get(i).getOs_rented_by_grpapl());
                submitrented(url,jsonparam);


            }
        }
    }

    public void submitrented(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Office_space.this);
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


                    img1.setImageURI(mImageUri);
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.GONE);
                    img3.setVisibility(View.GONE);
                    img4.setVisibility(View.GONE);
                    img5.setVisibility(View.GONE);
                    img6.setVisibility(View.GONE);
                    img7.setVisibility(View.GONE);
                    img8.setVisibility(View.GONE);
                    img9.setVisibility(View.GONE);
                    uploadcardview.setVisibility(View.VISIBLE);
                    uploadinterlayout.setVisibility(View.VISIBLE);


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
                            uploadinterlayout.setVisibility(View.VISIBLE);
                            uploadcardview.setVisibility(View.VISIBLE);

                            if(mArrayUri.size()==1)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.GONE);
                                img3.setVisibility(View.GONE);
                                img4.setVisibility(View.GONE);
                                img5.setVisibility(View.GONE);
                                img6.setVisibility(View.GONE);
                                img7.setVisibility(View.GONE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.GONE);
                                img4.setVisibility(View.GONE);
                                img5.setVisibility(View.GONE);
                                img6.setVisibility(View.GONE);
                                img7.setVisibility(View.GONE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img3.setImageURI(mArrayUri.get(2));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.VISIBLE);
                                img4.setVisibility(View.GONE);
                                img5.setVisibility(View.GONE);
                                img6.setVisibility(View.GONE);
                                img7.setVisibility(View.GONE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==4)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img3.setImageURI(mArrayUri.get(2));
                                img4.setImageURI(mArrayUri.get(3));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.VISIBLE);
                                img4.setVisibility(View.VISIBLE);
                                img5.setVisibility(View.GONE);
                                img6.setVisibility(View.GONE);
                                img7.setVisibility(View.GONE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==5)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img3.setImageURI(mArrayUri.get(2));
                                img4.setImageURI(mArrayUri.get(3));
                                img5.setImageURI(mArrayUri.get(4));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.VISIBLE);
                                img4.setVisibility(View.VISIBLE);
                                img5.setVisibility(View.VISIBLE);
                                img6.setVisibility(View.GONE);
                                img7.setVisibility(View.GONE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);
                            }

                            else if(mArrayUri.size()==6)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img3.setImageURI(mArrayUri.get(2));
                                img4.setImageURI(mArrayUri.get(3));
                                img5.setImageURI(mArrayUri.get(4));
                                img6.setImageURI(mArrayUri.get(5));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.VISIBLE);
                                img4.setVisibility(View.VISIBLE);
                                img5.setVisibility(View.VISIBLE);
                                img6.setVisibility(View.VISIBLE);
                                img7.setVisibility(View.GONE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);
                            }

                            else if(mArrayUri.size()==7)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img3.setImageURI(mArrayUri.get(2));
                                img4.setImageURI(mArrayUri.get(3));
                                img5.setImageURI(mArrayUri.get(4));
                                img6.setImageURI(mArrayUri.get(5));
                                img7.setImageURI(mArrayUri.get(6));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.VISIBLE);
                                img4.setVisibility(View.VISIBLE);
                                img5.setVisibility(View.VISIBLE);
                                img6.setVisibility(View.VISIBLE);
                                img7.setVisibility(View.VISIBLE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);
                            }

                            else if(mArrayUri.size()==8)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img3.setImageURI(mArrayUri.get(2));
                                img4.setImageURI(mArrayUri.get(3));
                                img5.setImageURI(mArrayUri.get(4));
                                img6.setImageURI(mArrayUri.get(5));
                                img7.setImageURI(mArrayUri.get(6));
                                img8.setImageURI(mArrayUri.get(7));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.VISIBLE);
                                img4.setVisibility(View.VISIBLE);
                                img5.setVisibility(View.VISIBLE);
                                img6.setVisibility(View.VISIBLE);
                                img7.setVisibility(View.VISIBLE);
                                img8.setVisibility(View.VISIBLE);
                                img9.setVisibility(View.GONE);
                            }

                            else if(mArrayUri.size()==9)
                            {
                                img1.setImageURI(mArrayUri.get(0));
                                img2.setImageURI(mArrayUri.get(1));
                                img3.setImageURI(mArrayUri.get(2));
                                img4.setImageURI(mArrayUri.get(3));
                                img5.setImageURI(mArrayUri.get(4));
                                img6.setImageURI(mArrayUri.get(5));
                                img7.setImageURI(mArrayUri.get(6));
                                img8.setImageURI(mArrayUri.get(7));
                                img9.setImageURI(mArrayUri.get(8));
                                img1.setVisibility(View.VISIBLE);
                                img2.setVisibility(View.VISIBLE);
                                img3.setVisibility(View.VISIBLE);
                                img4.setVisibility(View.VISIBLE);
                                img5.setVisibility(View.VISIBLE);
                                img6.setVisibility(View.VISIBLE);
                                img7.setVisibility(View.VISIBLE);
                                img8.setVisibility(View.VISIBLE);
                                img9.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                img1.setVisibility(View.GONE);
                                img2.setVisibility(View.GONE);
                                img3.setVisibility(View.GONE);
                                img4.setVisibility(View.GONE);
                                img5.setVisibility(View.GONE);
                                img6.setVisibility(View.GONE);
                                img7.setVisibility(View.GONE);
                                img8.setVisibility(View.GONE);
                                img9.setVisibility(View.GONE);
                                uploadinterlayout.setVisibility(View.GONE);
                                uploadcardview.setVisibility(View.GONE);
                                Toast.makeText(this," You can  select only 9 images",Toast.LENGTH_LONG).show();
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
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void back(View view) {
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gpsTracking.getLastLocation();
            double var= gpsTracking.getLatitude();
            double var1= gpsTracking.getLongitude();
          //  Toast.makeText(this,""+var +" "+var1,Toast.LENGTH_LONG).show();

            latti.setText(""+var);
            longt.setText(""+var1);

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
