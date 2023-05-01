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
import com.example.nk.grpapl.Models.warehouse_broker_detail;
import com.example.nk.grpapl.Models.warehouse_mngr_detail;
import com.example.nk.grpapl.Models.warehouse_owner_detail;
import com.example.nk.grpapl.Models.warehouse_part_avail;
import com.example.nk.grpapl.Models.warehouse_rent;
import com.example.nk.grpapl.Models.wrehouse_ready_move;
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

public class Warehouse extends AppCompatActivity {

        private RequestQueue requestQueue;
        String result;
        ScrollView sv;


        private JSONObject jsonObject;
        ArrayList<Uri> imagesUriList;
        ArrayList<String> encodedImageList;
        String imageURI;

        AppCompatActivity mActivity = Warehouse.this;
        ProgressBar progressBar;
        public static String state_nm , p_id="";
        SP sp;
        int var,var2;
        Databse_Handler mdatabase_handler;
        Databse_Handler db;
        ConnectionDetector connectionDetector;
        CommonDialog cd;
        GPSTracking gpsTracking;
        private int REQUEST_FINE_LOCATION = 2000;

        Spinner facilityspinner;
        Spinner statusofspacespineer,Cityspinner,Statespinner;
        Spinner Floorspinner;
        Spinner Statuswarehousespinner;
        ImageView wareoutsidepic1,wareoutsidepic2,wareoutsidepic3,wareoutsidepic4,wareoutsidepic5,wareoutsidepic6,wareoutsidepic7,wareoutsidepic8,wareoutside9,wareoutsidepic10;
        ImageView wareinsidepic1,wareinsidepic2,wareinsidepic3,wareinsidepic4,wareinsidepic5,wareinsidepic6,wareinsidepic7,wareinsidepic8,wareinsidepic9,wareinsidepic10;
        ImageView warelayoutpicture;


        CardView outsidecardview, insidecardview,layoutcardview;
        LinearLayout outsidelayout,insidelayout,layoutlayout;
         String imageEncoded;
        List<String> imagesEncodedList;
         private static final int PICK_FROM_GALLERY = 1;
         private static final int PICK_FROM_GALLERY_1 = 2;
         private static final int PICK_FROM_GALLERY_2 = 3;
        EditText warehouse_name,w_address,w_pin,w_latitude,w_longitute,w_landsize,t_of_hanover,w_rent,w_maintenance,w_quoted_pric;
        EditText m_name,m_num,m_email,o_name,o_num,o_email,brok_name,brok_num,brok_email,brok_fee,brok_agreefee;
        EditText road_with,num_of_cpark,num_of_tpark,elc_pow,pow_backup,leasable_area,cov_shel_dim,shed_height,clear_height,center_height,num_of_pill
                ,florring,num_of_exc_doc,num_ofshr_doc,lift,lock_in,year_of_cons,privious_tenant,space_avai,
                s_name_of_comp,s_con_per_name,s_desig,s_cont_num,s_email,s_area_rented,s_flore,s_lease_exp,s_rent,
                rent_name_of_comp,rent_contact_per_name,rent_cont_per_num,rent_desig,rent_email,rent_leaseexpire,rent_rent;

        RadioButton licns_yes,licns_no,noc_fire_yes,noc_fire_no,rented_grpapl_yes,getRented_grpapl_no;
        Button m_button,o_button ,b_button,submit,upload_picture,add_more_floor,ware_picture,ware_layout,part_avai_accupant,warehouse_rentd;
        RadioGroup more_ocup,rented,radio_licnce,radio_noc,radio_available;
        public static ArrayList<States> states = new ArrayList<>();
        public static ArrayList<String> states_nm = new ArrayList<>();
        public static ArrayList<Cities>  cities= new ArrayList<>();
        public static ArrayList<String> cities_nm = new ArrayList<>();
        ArrayList<warehouse_owner_detail> owner_detail = new ArrayList<warehouse_owner_detail>();
        ArrayList<warehouse_mngr_detail> managerdetail= new ArrayList<warehouse_mngr_detail>();
        ArrayList<warehouse_broker_detail> brokerdetail =new ArrayList<warehouse_broker_detail>();
        ArrayList<warehouse_rent>warehouse_rents=new ArrayList<warehouse_rent>();
        ArrayList<warehouse_part_avail>warehouse_part_avail=new ArrayList<warehouse_part_avail>();
        ArrayList<wrehouse_ready_move> warehouse_ready2=new ArrayList<wrehouse_ready_move>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_warehouse);
        sp = SP.getInstance();
        connectionDetector = new ConnectionDetector(mActivity);
        cd=new CommonDialog(mActivity);
        jsonObject = new JSONObject();
        encodedImageList = new ArrayList<>();
        gpsTracking = new GPSTracking(this, Warehouse.this);
        db = new Databse_Handler(this);
        sv=(ScrollView)findViewById(R.id.scrallview_warehouse);
        progressBar =(ProgressBar)findViewById(R.id.prog_bar_warehouse);
        mdatabase_handler=new Databse_Handler(this);
        statusofspacespineer=(Spinner)findViewById(R.id.Statusofspacespinner);
        Statuswarehousespinner = (Spinner)findViewById(R.id.statuswarehousespinner);
        Floorspinner = (Spinner)findViewById(R.id.Floorspinner);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutstatus);
        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.statuspartavail);
        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.staus_ranted);
        //============================================== id of sqlite ====================
        licns_yes=(RadioButton)findViewById(R.id.linace_info_yes);
        licns_no=(RadioButton)findViewById(R.id.linace_info_no);
        radio_licnce = (RadioGroup)findViewById(R.id.radio_licnce_info);
        noc_fire_no=(RadioButton)findViewById(R.id.noc_fire_no);
        noc_fire_yes=(RadioButton)findViewById(R.id.noc_fire_yes);
        rented_grpapl_yes=(RadioButton)findViewById(R.id.rented_grpaple_yes);
        getRented_grpapl_no=(RadioButton)findViewById(R.id.rented_grpaple_no);
        radio_noc =(RadioGroup) findViewById(R.id.radio_noc);
        radio_available =(RadioGroup) findViewById(R.id.radio_available);

        Cityspinner=(Spinner)findViewById(R.id.cityspinner);
        Statespinner=(Spinner)findViewById(R.id.statespinner);
        //================================================================================
        m_name=(EditText)findViewById(R.id.managernamewarehouse);
        m_num=(EditText)findViewById(R.id.numbermanagerwarehouse);
        m_email=(EditText)findViewById(R.id.emailmanagerwarehouse);
        m_button=(Button)findViewById(R.id.manager3warehouse);
        o_button=(Button)findViewById(R.id.owner1warehouse);
        b_button=(Button)findViewById(R.id.broker3warehouse);
        o_name=(EditText)findViewById(R.id.ownernamewarehouse);
        o_num=(EditText)findViewById(R.id.Numberwarehouse);
        o_email=(EditText)findViewById(R.id.EmailAddresswarehouse);
        brok_name=(EditText)findViewById(R.id.brokernamewarehouse);
        brok_num=(EditText)findViewById(R.id.numberbrokerwarehouse);
        brok_email=(EditText)findViewById(R.id.emailbrokerwarehouse);
        brok_fee=(EditText)findViewById(R.id.feesbrokerwarehouse);
        brok_agreefee=(EditText)findViewById(R.id.agredfeesbrokerwarehouse);
        //--------------------------------------------------------------------other id
        warehouse_name=(EditText)findViewById(R.id.WarehouseName);
        w_address=(EditText)findViewById(R.id.Addresswarehouse);
        w_pin=(EditText)findViewById(R.id.PINwarehouse);
        w_latitude=(EditText)findViewById(R.id.latitude);
        w_longitute=(EditText)findViewById(R.id.longitute);
        w_landsize=(EditText)findViewById(R.id.LandSizeinacreindex);
        t_of_hanover=(EditText)findViewById(R.id.TimelineofHandoverinDays);
        w_rent=(EditText)findViewById(R.id.Rent);
        w_maintenance=(EditText)findViewById(R.id.Maintenance);
        w_quoted_pric=(EditText)findViewById(R.id.QuotedSalesPricewarehouserent);
        //==========================================================
        road_with=(EditText)findViewById(R.id.RoadWidthinmeter);
        num_of_cpark=(EditText)findViewById(R.id.NumberofCarParks);
        num_of_tpark=(EditText)findViewById(R.id.NumberofTruckParks);
        elc_pow=(EditText)findViewById(R.id.ElectricityProvisioninKW);
        pow_backup=(EditText)findViewById(R.id.PowerBackup);
        leasable_area=(EditText)findViewById(R.id.LeasableAreainsqft);
        cov_shel_dim=(EditText)findViewById(R.id.CoveredShedDimension);
        shed_height=(EditText)findViewById(R.id.ShedHeightinmeter);
        clear_height=(EditText)findViewById(R.id.ClearHeightinmeter);
        center_height=(EditText)findViewById(R.id.CentreHeightinmeter);
        num_of_pill=(EditText)findViewById(R.id.NumberofPillars);
        florring=(EditText)findViewById(R.id.FlooringConcreteEpoxyPolyurethane);
        num_of_exc_doc=(EditText)findViewById(R.id.NumberofExclusiveDocks);
        num_ofshr_doc=(EditText)findViewById(R.id.NumberofSharedDocks);
        lift=(EditText)findViewById(R.id.Liftonlyifmultiplefloors);
        lock_in=(EditText)findViewById(R.id.LockininYears);
        year_of_cons=(EditText)findViewById(R.id.YearofConstruction);
        privious_tenant=(EditText)findViewById(R.id.PreviousTenant);
        //=======================================
        space_avai=(EditText)findViewById(R.id.SpaceAvailablewarehouse);
        s_name_of_comp=(EditText)findViewById(R.id.NameoftheCompanywarehousepartto);
        s_con_per_name=(EditText)findViewById(R.id.ContactPersonNamewarehouserentto);
        s_desig=(EditText)findViewById(R.id.Designationwarehouserentto);
        s_cont_num=(EditText)findViewById(R.id.ContactNumberwarehouserentto);
        s_email=(EditText)findViewById(R.id.Emailwarehouserentto);
        s_area_rented=(EditText)findViewById(R.id.AreaRentedrentto);
        s_flore=(EditText)findViewById(R.id.Floorrenttowarehouse);
        s_lease_exp=(EditText)findViewById(R.id.LeaseExpirywarehouserentto);
        s_rent=(EditText)findViewById(R.id.Rentwarehouserentto);
        //======================================
        rent_name_of_comp=(EditText)findViewById(R.id.NameoftheCompanywarehouserent);
        rent_contact_per_name=(EditText)findViewById(R.id.ContactPersonNamewarehouserentto);
        rent_cont_per_num=(EditText)findViewById(R.id.ContactNumberwarehouserent);
        rent_desig=(EditText)findViewById(R.id.Designationwarehouserent);
        rent_email=(EditText)findViewById(R.id.Emailwarehouserent);
        rent_leaseexpire=(EditText)findViewById(R.id.LeaseExpirywarehouserent);
        rent_rent=(EditText)findViewById(R.id.Rentwarehouserent);
        //-----------------------------------------------------------------------buttons
        more_ocup =(RadioGroup)findViewById(R.id.radio_more_occupant);
        rented =(RadioGroup)findViewById(R.id.radio_rented);
        //---------------------------------------
        submit=(Button)findViewById(R.id.warehouse_submit);
        upload_picture=(Button)findViewById(R.id.w_upload_picture);
        add_more_floor=(Button)findViewById(R.id.add_more_flore_button);
        ware_picture=(Button)findViewById(R.id.warehouse_pic);
        ware_layout=(Button)findViewById(R.id.warehouse_layout);
        part_avai_accupant=(Button)findViewById(R.id.add_mor_occupant_button);
        warehouse_rentd=(Button)findViewById(R.id.warehouse_rented);


        wareoutsidepic1 = (ImageView)findViewById(R.id.warepic1);
        wareoutsidepic2 = (ImageView)findViewById(R.id.warepic2);
        wareoutsidepic3 = (ImageView)findViewById(R.id.warepic3);
        wareoutsidepic4 = (ImageView)findViewById(R.id.warepic4);
        wareoutsidepic5 = (ImageView)findViewById(R.id.warepic5);
        wareoutsidepic6 = (ImageView)findViewById(R.id.warepic6);
        wareoutsidepic7 = (ImageView)findViewById(R.id.warepic7);
        wareoutsidepic8 = (ImageView)findViewById(R.id.warepic8);
        wareoutside9 = (ImageView)findViewById(R.id.warepic9);
        wareoutsidepic10 = (ImageView)findViewById(R.id.warepic10);

        outsidecardview = (CardView)findViewById(R.id.cardviewwarehouseoutside);
        outsidelayout = (LinearLayout)findViewById(R.id.warehousepictureoutside);

        wareinsidepic1 = (ImageView)findViewById(R.id.warepic11);
        wareinsidepic2 = (ImageView)findViewById(R.id.warepic22);
        wareinsidepic3 = (ImageView)findViewById(R.id.warepic33);
        wareinsidepic4 = (ImageView)findViewById(R.id.warepic44);
        wareinsidepic5 = (ImageView)findViewById(R.id.warepic55);
        wareinsidepic6 = (ImageView)findViewById(R.id.warepic66);
        wareinsidepic7 = (ImageView)findViewById(R.id.warepic77);
        wareinsidepic8 = (ImageView)findViewById(R.id.warepic88);
        wareinsidepic9 = (ImageView)findViewById(R.id.warepic99);
        wareinsidepic10 = (ImageView)findViewById(R.id.warepic1010);

        insidecardview = (CardView)findViewById(R.id.cardviewwarehouseinside);
        insidelayout = (LinearLayout)findViewById(R.id.warehousepictureinside);

        warelayoutpicture = (ImageView)findViewById(R.id.warepic111);
        layoutcardview = (CardView)findViewById(R.id.cardviewwarehouselayout);
        layoutlayout = (LinearLayout)findViewById(R.id.layoutsss);
        //--------------------------------------------------------------manager detail validation and sqlite data submit


        upload_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Warehouse.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Warehouse.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
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

        ware_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Warehouse.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Warehouse.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
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

        ware_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Warehouse.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Warehouse.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
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
                       m_num.setError("Please check the number");
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


            m_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final   String name=m_name.getText().toString().trim();
                    final   String number=m_num.getText().toString().trim();
                    final   String email =m_email.getText().toString().trim();

                    mdatabase_handler.addMngr(name,number,email);
                    m_name.setText("");
                    m_num.setText("");
                    m_email.setText("");

                }
            });
        //-------------------------------------------------------------owner detail validation and sqlite data submit
            o_num.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (o_num.getText().toString().length() == 10)
                    {
                    }
                    else
                    {
                        o_num.setError("Please check the number");
                    }

                }
            });
            o_email.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {



                }

                @Override
                public void afterTextChanged(Editable s) {
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (o_email.getText().toString().matches(emailPattern))
                    {
                    }
                    else
                    {
                        o_email.setError("Invalid E-mail");
                    }
                }
            });
            o_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final   String oname=o_name.getText().toString().trim();
                  final   String onumber=o_num.getText().toString().trim();
                  final  String oemail =o_email.getText().toString().trim();

                  mdatabase_handler.addowner(oname,onumber,oemail);
                  o_name.setText("");
                  o_num.setText("");
                  o_email.setText("");
              }
              });

            //----------------------------------------------------------broker detail validation and sqlite data submit code
               brok_num.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {


                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (brok_num.getText().toString().length() == 10)
                        {
                        }
                        else
                        {
                            brok_num.setError("Please check the number");
                        }

                    }
                });
                brok_email.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    if (brok_email.getText().toString().matches(emailPattern))
                    {
                    }
                    else
                    {
                        brok_email.setError("Invalid E-mail");
                    }

                }
            });

              b_button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final   String bname= brok_name.getText().toString().trim();
                  final   String bnum= brok_num.getText().toString().trim();
                  final  String bemail= brok_email.getText().toString().trim();
                  final  String bfee= brok_fee.getText().toString().trim();
                  final  String bagreefee= brok_agreefee.getText().toString().trim();

                  mdatabase_handler.addbroker(bname,bnum,bemail,bfee,bagreefee);
                  brok_name.setText("");
                  brok_num.setText("");
                  brok_email.setText("");
                  brok_fee.setText("");
                  brok_agreefee.setText("");
                  }
                 });

              warehouse_rentd.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      String radio="";
                      if(rented.getCheckedRadioButtonId()==R.id.radio_rented_yes)
                      {
                          radio="1";
                      }
                      else
                      {
                          radio="0";
                      }

                      String var1  =rent_name_of_comp.getText().toString();
                      String var2  =rent_contact_per_name.getText().toString();
                      String var3 = rent_desig.getText().toString();
                      String var4 = rent_cont_per_num.getText().toString();
                      String var5 = rent_email.getText().toString();
                      String var6 = rent_leaseexpire.getText().toString();
                      String var7= rent_rent.getText().toString();
                      String var8= radio;// cahnge with radio butuon

                      mdatabase_handler.addwarehouserent(var1,var2,var3,var4,var5,var6,var7,var8);
                      rent_name_of_comp.setText("");
                      rent_contact_per_name.setText("");
                      rent_cont_per_num.setText("");
                      rent_desig.setText("");
                      rent_email.setText("");
                      rent_leaseexpire.setText("");
                      rent_rent.setText("");
                  }
              });

        part_avai_accupant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String radio="";
                if(more_ocup.getCheckedRadioButtonId()==R.id.rented_grpaple_yes)
                {

                    radio="1";
                }
                else
                {
                    radio="0";
                }

                String var  =space_avai.getText().toString();
                String var1  =s_name_of_comp.getText().toString();
                String var2  =s_con_per_name.getText().toString();
                String var3 = s_desig.getText().toString();
                String var4 = s_cont_num.getText().toString();
                String var5 = s_email.getText().toString();
                String var6 = s_area_rented.getText().toString();
                String var7 = s_flore.getText().toString();
                String var8 = s_lease_exp.getText().toString();
                String var9= s_rent.getText().toString();
                String var10= radio;// cahnge with radio butuon

                mdatabase_handler.addwarehouse_partavail(var,var1,var2,var3,var4,var5,var6,var7,var8,var9,var10);
                space_avai.setText("");
                s_name_of_comp.setText("");
                s_con_per_name.setText("");
                s_desig.setText("");
                s_cont_num.setText("");
                s_email.setText("");
                s_area_rented.setText("");
                s_flore.setText("");
                s_lease_exp.setText("");
                s_rent.setText("");

            }
        });

        add_more_floor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String var  =road_with.getText().toString();// image code
                String var1  = road_with.getText().toString();
                String var2  = num_of_cpark.getText().toString();
                String var3  = num_of_tpark.getText().toString();
                String var4  = elc_pow.getText().toString();
                String var5  =pow_backup.getText().toString();
                String var6  =Floorspinner.getSelectedItem().toString();
                String var7  = leasable_area.getText().toString();
                String var8  = cov_shel_dim.getText().toString();
                String var9  = shed_height.getText().toString();
                String var10  = clear_height.getText().toString();
                String var11  = center_height.getText().toString();
                String var12  = num_of_pill.getText().toString();
                String var13  = florring.getText().toString();
                String var14  = num_of_exc_doc.getText().toString();
                String var15  = num_ofshr_doc.getText().toString();
                String var16  =lift.getText().toString();
                String var17  = lift.getText().toString();//image
                String var18  = lift.getText().toString();//image
                String var19  = lock_in.getText().toString();
                String var20  =year_of_cons.getText().toString();
                String var21  = privious_tenant.getText().toString();

                mdatabase_handler.addwarehouse_ready2_move(var,var1,var2,var3,var4,var5,var6,var7,var8,var9,var10,
                        var11,var12,var13,var14,var15,var16,var17,var18,var19,var20,var21);
                road_with.setText("");
                num_of_cpark.setText("");
                num_of_tpark.setText("");
                elc_pow.setText("");
                pow_backup.setText("");
                cov_shel_dim.setText("");
                shed_height.setText("");
                clear_height.setText("");
                center_height.setText("");
                num_of_pill.setText("");
                florring.setText("");
                num_of_exc_doc.setText("");
                num_ofshr_doc.setText("");
                lock_in.setText("");
                year_of_cons.setText("");
                privious_tenant.setText("");
            }
        });


              //---------------------------------------------------------------------------------------end

           statusofspacespineer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                var = position;
                if(var ==2)
                {
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    linearLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
           });


            ArrayList <String> tab = new ArrayList <String>();
            mdatabase_handler.getmanager_detail();

            Statuswarehousespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                   var2=position;
                    Toast.makeText(Warehouse.this, ""+var2, Toast.LENGTH_SHORT).show();

                    if(var2 ==2)
                    {
                        linearLayout2.setVisibility(View.VISIBLE);
                        linearLayout3.setVisibility(View.GONE);
                    }
                    else if(var2 ==3)
                    {
                        linearLayout3.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);
                    }
                    else{

                        linearLayout2.setVisibility(View.GONE);
                        linearLayout3.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        checkPermissions();
                facilityspinner = (Spinner)findViewById(R.id.facilitytypespinner);
                ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Warehouse.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.facilitytype));
                myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                facilityspinner.setAdapter(myadapter);

                statusofspacespineer = (Spinner)findViewById(R.id.Statusofspacespinner);
                ArrayAdapter<String> mmyadapter = new ArrayAdapter<String>(Warehouse.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.statusofspace));
                myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                statusofspacespineer.setAdapter(mmyadapter);


                ArrayAdapter<String> mmmyadapter = new ArrayAdapter<String>(Warehouse.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Floor));
                myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Floorspinner.setAdapter(mmmyadapter);

                ArrayAdapter<String> mmmmyadapter = new ArrayAdapter<String>(Warehouse.this,
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.statuswarehouse));
                myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Statuswarehousespinner.setAdapter(mmmmyadapter);

        gotoGetStates();

        Statespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Upload_image();
                submitdata();
            }
        });

    }


    public void submitdata() {
        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
            String War_name =warehouse_name.getText().toString();
            String address =w_address.getText().toString();
            String staes =Statespinner.getSelectedItem().toString();
            String city =Cityspinner.getSelectedItem().toString();
            String pin =w_pin.getText().toString();
            String lati =w_latitude.getText().toString();
            String longtitude =w_longitute.getText().toString();
            String facility =facilityspinner.getSelectedItem().toString();
            String landsize =w_landsize.getText().toString();
            String status =statusofspacespineer.getSelectedItem().toString();
            String t_hanover =t_of_hanover.getText().toString();
            String rent =w_rent.getText().toString();
            String maint =w_maintenance.getText().toString();
             String lic="";
             String noc="";
             String avail="";
             if(radio_licnce.getCheckedRadioButtonId()==R.id.linace_info_yes)
             {
                 lic="1";
             }
             else
                 {
                  lic="0";
                 }
            if(radio_noc.getCheckedRadioButtonId()==R.id.noc_fire_yes)
            {
                noc="1";
            }
            else
            {
                noc="0";
            }

            if(radio_available.getCheckedRadioButtonId()==R.id.radio_avail_yes)
            {
                avail="1";
            }
            else
            {
                avail="0";
            }
            String  statuswarehouse =Statuswarehousespinner.getSelectedItem().toString();
            //String War_name =Statuswarehousespinner.getText().toString();
            String quoted =w_quoted_pric.getText().toString();

            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverWarehouse;
            Map<String, String> jsonparam = new HashMap<String, String>();

            jsonparam.put("wname",""+War_name);
            jsonparam.put("address",""+address);
            jsonparam.put("state",""+staes);
            jsonparam.put("city",""+ city);
            jsonparam.put("pin",""+pin);
            jsonparam.put("latitude",""+lati);
            jsonparam.put("lgtute",""+longtitude);
            jsonparam.put("fac_type",""+facility);
            jsonparam.put("land_size",""+landsize);
            jsonparam.put("sta_of_spac",""+status);
            jsonparam.put("tm_of_hdovr",""+t_hanover);
            jsonparam.put("rent",""+rent);
            jsonparam.put("maintnanc",""+maint);
            //radio button  licns_info and noc_fire
            jsonparam.put("licns_info",""+lic);
            jsonparam.put("noc_fire",""+noc);
            jsonparam.put("status",""+statuswarehouse);
            jsonparam.put("invstnt_sale",""+avail);
            jsonparam.put("quotes_sales",""+quoted);
            submitdata(url, jsonparam);
        }
    }
    public void submitdata(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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

    public void submitowner() {
        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
            owner_detail = db.getowner_detail();

            if(owner_detail.size()==0)
            {
               submitmanager();
            }
            else {

                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < owner_detail.size(); i++) {
                    String url = ServerUrl.warehouse_owner;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("nameo", "" + owner_detail.get(i).getName());
                    jsonparam.put("numo", "" + owner_detail.get(i).getNumber());
                    jsonparam.put("emailo", "" + owner_detail.get(i).getEmail());
                    if(i== owner_detail.size()-1){
                        submitmanager();
                    }else{}
                    submitowner(url, jsonparam);

                }
            }

        }
    }
    public void submitowner(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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
                        Toast.makeText(mActivity, ""+model.getMsg(), Toast.LENGTH_SHORT).show();

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

    public void submitmanager() {
        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
            managerdetail = db.getmanager_detail();
            if(managerdetail.size()==0)
            {
                submitbroker();
            }
            else {


                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < managerdetail.size(); i++) {
                    String url = ServerUrl.warehouse_manager;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("namem", "" + managerdetail.get(i).getName());
                    jsonparam.put("numm", "" + managerdetail.get(i).getNumber());
                    jsonparam.put("emailm", "" + managerdetail.get(i).getEmail());
                    if(i== managerdetail.size()-1){
                        submitbroker();
                    }else{}
                    submitmanager(url, jsonparam);
                }
            }
        }
    }
    public void submitmanager(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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

    public void submitbroker() {
        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();

        }
        else
        {
            brokerdetail = db.get_broker_detail();

            if(brokerdetail.size()==0)
            {
                submit_warehouse_rent();
            }
            else
            {

                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < brokerdetail.size(); i++)
                {
                    String url = ServerUrl.warehouse_broker;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("nameb", "" + brokerdetail.get(i).getName());
                    jsonparam.put("numb", "" + brokerdetail.get(i).getNumber());
                    jsonparam.put("emailb", "" + brokerdetail.get(i).getEmail());
                    jsonparam.put("fee", "" + brokerdetail.get(i).getFees());
                    jsonparam.put("agfee", "" + brokerdetail.get(i).getAgree_fees());
                    if(i== brokerdetail.size()-1){
                        submit_warehouse_rent();
                    }else{}
                    submitbroker(url, jsonparam);
                }
            }
        }
    }
    public void submitbroker(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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
                        submit_warehouse_rent();
                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        submit_warehouse_rent();
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

    public void submit_warehouse_rent() {

        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
                warehouse_rents = db.get_warehouse_rent_detail();
            if(warehouse_rents.size()==0)
            {
                submit_warehouse_part();
            }
            else
            {

                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < warehouse_rents.size(); i++) {
                    String url = ServerUrl.warehouse_rent;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("c_name", "" + warehouse_rents.get(i).getCompany_name());
                    jsonparam.put("con_p_name", "" + warehouse_rents.get(i).getContact_person_name());
                    jsonparam.put("d_esig", "" + warehouse_rents.get(i).getDesignation());
                    jsonparam.put("num", "" + warehouse_rents.get(i).getNumber());
                    jsonparam.put("email", "" + warehouse_rents.get(i).getEmail());
                    jsonparam.put("leas_exp", "" + warehouse_rents.get(i).getLeas_expire());
                    jsonparam.put("rent", "" + warehouse_rents.get(i).getRent());
                    jsonparam.put("Rnt_by_grpapl", "" + warehouse_rents.get(i).getRent_by_grpapl());
                    if(i== warehouse_rents.size()-1){
                        submit_warehouse_part();
                    }else{}
                    submit_warehouse_rent(url, jsonparam);
                }
            }
        }
    }
    public void submit_warehouse_rent(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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
                        submit_warehouse_part();

                    }
                    else
                    {
                        progressBar.setVisibility(View.GONE);
                        submit_warehouse_part();
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

    public void submit_warehouse_part() {

        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
            warehouse_part_avail = db.get_warehouse_part_detail();
            if(warehouse_rents.size()==0)
            {
                submit_warehouse_redy2();
            }
            else {
                progressBar.setVisibility(View.VISIBLE);
                for (int i = 0; i < warehouse_part_avail.size(); i++) {
                    String url = ServerUrl.warehouse_part_avail;
                    Map<String, String> jsonparam = new HashMap<String, String>();
                    jsonparam.put("p_id", "" + p_id);
                    jsonparam.put("space_avai", "" + warehouse_part_avail.get(i).getSpace_available());
                    jsonparam.put("c_name", "" + warehouse_part_avail.get(i).getCompany_name());
                    jsonparam.put("con_p_name", "" + warehouse_part_avail.get(i).getContact_person_name());
                    jsonparam.put("d_esig", "" + warehouse_part_avail.get(i).getDesignation());
                    jsonparam.put("num", "" + warehouse_part_avail.get(i).getNumber());
                    jsonparam.put("email", "" + warehouse_part_avail.get(i).getEmail());
                    jsonparam.put("area_rent", "" + warehouse_part_avail.get(i).getArea_rent());
                    jsonparam.put("floore", "" + warehouse_part_avail.get(i).getFloor());
                    jsonparam.put("leas_exp", "" + warehouse_part_avail.get(i).getLeas_expire());
                    jsonparam.put("rent", "" + warehouse_part_avail.get(i).getRent().toString());
                    jsonparam.put("Rnt_by_grpapl", "" + warehouse_part_avail.get(i).getRent_by_grpapl());
                    submit_warehouse_part(url, jsonparam);
                    if(i== warehouse_part_avail.size()-1){
                        submit_warehouse_redy2();
                    }else{}
                }
            }
        }
    }
    public void submit_warehouse_part(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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

    public void submit_warehouse_redy2() {

        if (!connectionDetector.isConnectingToInternet())
        {
            cd.showNetworkDialog();
        }
        else
        {
            warehouse_ready2 = db.get_warehouse_ready2_detail();
            progressBar.setVisibility(View.VISIBLE);
            for (int i = 0; i < warehouse_ready2.size(); i++) {
                String url = ServerUrl.warehouse_readyto;
                Map<String, String> jsonparam = new HashMap<String, String>();

                jsonparam.put("p_id", "" + p_id);
                jsonparam.put("ware_pic", "" + warehouse_ready2.get(i).getWarehouse_Pic());
                jsonparam.put("road_wdth", "" + warehouse_ready2.get(i).getRoad_Width());
                jsonparam.put("car_pking", "" + warehouse_ready2.get(i).getNumber_Car_Parks());
                jsonparam.put("truck_pking", "" + warehouse_ready2.get(i).getNumber_Truck_Parks());
                jsonparam.put("elec_pow", "" + warehouse_ready2.get(i).getElectricity_Provision());
                jsonparam.put("pow_bckup", "" + warehouse_ready2.get(i).getPower_Backup());
                jsonparam.put("floore", "" + warehouse_ready2.get(i).getFloor());
                jsonparam.put("lesbl_area", "" + warehouse_ready2.get(i).getLeasable_Area());
                jsonparam.put("cov_shed", "" + warehouse_ready2.get(i).getCovered_Shed_dim());
                jsonparam.put("shed_height", "" + warehouse_ready2.get(i).getShed_Height());
                jsonparam.put("clear_height", "" + warehouse_ready2.get(i).getClear_Height());
                jsonparam.put("cntr_height", "" + warehouse_ready2.get(i).getCentre_Height());
                jsonparam.put("num_pillor", "" + warehouse_ready2.get(i).getNumber_Pillars());
                jsonparam.put("flooring", "" + warehouse_ready2.get(i).getFlooring());
                jsonparam.put("num_exclu_doc", "" + warehouse_ready2.get(i).getNumber_Exclusive_Docks());
                jsonparam.put("num_share_doc", "" + warehouse_ready2.get(i).getNumber_Shared_Dock());
                jsonparam.put("warehouse_pic", "" + warehouse_ready2.get(i).getWarehouse_Pic());
                jsonparam.put("lift", "" + warehouse_ready2.get(i).getLift());
                jsonparam.put("warehouse_layout", "" + warehouse_ready2.get(i).getWarehouse_Layout());
                jsonparam.put("lock_in", "" + warehouse_ready2.get(i).getLockin());
                jsonparam.put("year_const", "" + warehouse_ready2.get(i).getYear_Construction());
                jsonparam.put("prev_tanent", "" + warehouse_ready2.get(i).getPrevious_Tenant());

                Log.e("jsonparam", ""+jsonparam);
                submit_warehouse_redy2(url, jsonparam);


            }

        }
    }
    public void submit_warehouse_redy2(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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
                        Cityspinner.setAdapter(myadapter);
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
        requestQueue = Volley.newRequestQueue(Warehouse.this);
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
                        Statespinner.setAdapter(myadapter);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
            //   Toast.makeText(this,""+var +" "+var1,Toast.LENGTH_LONG).show();

            w_latitude.setText(""+var);
            w_longitute.setText(""+var1);

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

                    wareoutsidepic1.setImageURI(mImageUri);
                    wareoutsidepic1.setVisibility(View.VISIBLE);
                    wareoutsidepic2.setVisibility(View.GONE);
                    wareoutsidepic3.setVisibility(View.GONE);
                    wareoutsidepic4.setVisibility(View.GONE);
                    wareoutsidepic5.setVisibility(View.GONE);
                    wareoutsidepic6.setVisibility(View.GONE);
                    wareoutsidepic7.setVisibility(View.GONE);
                    wareoutsidepic8.setVisibility(View.GONE);
                    wareoutside9.setVisibility(View.GONE);
                    wareoutsidepic10.setVisibility(View.GONE);
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
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.GONE);
                                wareoutsidepic3.setVisibility(View.GONE);
                                wareoutsidepic4.setVisibility(View.GONE);
                                wareoutsidepic5.setVisibility(View.GONE);
                                wareoutsidepic6.setVisibility(View.GONE);
                                wareoutsidepic7.setVisibility(View.GONE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.GONE);
                                wareoutsidepic4.setVisibility(View.GONE);
                                wareoutsidepic5.setVisibility(View.GONE);
                                wareoutsidepic6.setVisibility(View.GONE);
                                wareoutsidepic7.setVisibility(View.GONE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.GONE);
                                wareoutsidepic5.setVisibility(View.GONE);
                                wareoutsidepic6.setVisibility(View.GONE);
                                wareoutsidepic7.setVisibility(View.GONE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==4)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic4.setImageURI(mArrayUri.get(3));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.VISIBLE);
                                wareoutsidepic5.setVisibility(View.GONE);
                                wareoutsidepic6.setVisibility(View.GONE);
                                wareoutsidepic7.setVisibility(View.GONE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==5)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic4.setImageURI(mArrayUri.get(3));
                                wareoutsidepic5.setImageURI(mArrayUri.get(4));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.VISIBLE);
                                wareoutsidepic5.setVisibility(View.VISIBLE);
                                wareoutsidepic6.setVisibility(View.GONE);
                                wareoutsidepic7.setVisibility(View.GONE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==6)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic4.setImageURI(mArrayUri.get(3));
                                wareoutsidepic5.setImageURI(mArrayUri.get(4));
                                wareoutsidepic6.setImageURI(mArrayUri.get(5));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.VISIBLE);
                                wareoutsidepic5.setVisibility(View.VISIBLE);
                                wareoutsidepic6.setVisibility(View.VISIBLE);
                                wareoutsidepic7.setVisibility(View.GONE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==7)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic4.setImageURI(mArrayUri.get(3));
                                wareoutsidepic5.setImageURI(mArrayUri.get(4));
                                wareoutsidepic6.setImageURI(mArrayUri.get(5));
                                wareoutsidepic7.setImageURI(mArrayUri.get(6));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.VISIBLE);
                                wareoutsidepic5.setVisibility(View.VISIBLE);
                                wareoutsidepic6.setVisibility(View.VISIBLE);
                                wareoutsidepic7.setVisibility(View.VISIBLE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==8)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic4.setImageURI(mArrayUri.get(3));
                                wareoutsidepic5.setImageURI(mArrayUri.get(4));
                                wareoutsidepic6.setImageURI(mArrayUri.get(5));
                                wareoutsidepic7.setImageURI(mArrayUri.get(6));
                                wareoutsidepic8.setImageURI(mArrayUri.get(7));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.VISIBLE);
                                wareoutsidepic5.setVisibility(View.VISIBLE);
                                wareoutsidepic6.setVisibility(View.VISIBLE);
                                wareoutsidepic7.setVisibility(View.VISIBLE);
                                wareoutsidepic8.setVisibility(View.VISIBLE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==9)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic4.setImageURI(mArrayUri.get(3));
                                wareoutsidepic5.setImageURI(mArrayUri.get(4));
                                wareoutsidepic6.setImageURI(mArrayUri.get(5));
                                wareoutsidepic7.setImageURI(mArrayUri.get(6));
                                wareoutsidepic8.setImageURI(mArrayUri.get(7));
                                wareoutside9.setImageURI(mArrayUri.get(8));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.VISIBLE);
                                wareoutsidepic5.setVisibility(View.VISIBLE);
                                wareoutsidepic6.setVisibility(View.VISIBLE);
                                wareoutsidepic7.setVisibility(View.VISIBLE);
                                wareoutsidepic8.setVisibility(View.VISIBLE);
                                wareoutside9.setVisibility(View.VISIBLE);
                                wareoutsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==10)
                            {
                                wareoutsidepic1.setImageURI(mArrayUri.get(0));
                                wareoutsidepic2.setImageURI(mArrayUri.get(1));
                                wareoutsidepic3.setImageURI(mArrayUri.get(2));
                                wareoutsidepic4.setImageURI(mArrayUri.get(3));
                                wareoutsidepic5.setImageURI(mArrayUri.get(4));
                                wareoutsidepic6.setImageURI(mArrayUri.get(5));
                                wareoutsidepic7.setImageURI(mArrayUri.get(6));
                                wareoutsidepic8.setImageURI(mArrayUri.get(7));
                                wareoutside9.setImageURI(mArrayUri.get(8));
                                wareoutsidepic10.setImageURI(mArrayUri.get(9));
                                wareoutsidepic1.setVisibility(View.VISIBLE);
                                wareoutsidepic2.setVisibility(View.VISIBLE);
                                wareoutsidepic3.setVisibility(View.VISIBLE);
                                wareoutsidepic4.setVisibility(View.VISIBLE);
                                wareoutsidepic5.setVisibility(View.VISIBLE);
                                wareoutsidepic6.setVisibility(View.VISIBLE);
                                wareoutsidepic7.setVisibility(View.VISIBLE);
                                wareoutsidepic8.setVisibility(View.VISIBLE);
                                wareoutside9.setVisibility(View.VISIBLE);
                                wareoutsidepic10.setVisibility(View.VISIBLE);

                            }

                            else
                            {
                                wareoutsidepic1.setVisibility(View.GONE);
                                wareoutsidepic2.setVisibility(View.GONE);
                                wareoutsidepic3.setVisibility(View.GONE);
                                wareoutsidepic4.setVisibility(View.GONE);
                                wareoutsidepic5.setVisibility(View.GONE);
                                wareoutsidepic6.setVisibility(View.GONE);
                                wareoutsidepic7.setVisibility(View.GONE);
                                wareoutsidepic8.setVisibility(View.GONE);
                                wareoutside9.setVisibility(View.GONE);
                                wareoutsidepic10.setVisibility(View.GONE);
                                outsidecardview.setVisibility(View.GONE);
                                outsidelayout.setVisibility(View.GONE);

                                Toast.makeText(this," You can  select only 10 images",Toast.LENGTH_LONG).show();
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


                    wareinsidepic1.setImageURI(mImageUri);
                    wareinsidepic1.setVisibility(View.VISIBLE);
                    wareinsidepic2.setVisibility(View.GONE);
                    wareinsidepic3.setVisibility(View.GONE);
                    wareinsidepic4.setVisibility(View.GONE);
                    wareinsidepic5.setVisibility(View.GONE);
                    wareinsidepic6.setVisibility(View.GONE);
                    wareinsidepic7.setVisibility(View.GONE);
                    wareinsidepic8.setVisibility(View.GONE);
                    wareinsidepic9.setVisibility(View.GONE);
                    wareinsidepic10.setVisibility(View.GONE);
                    insidecardview.setVisibility(View.VISIBLE);
                    insidelayout.setVisibility(View.VISIBLE);


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
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.GONE);
                                wareinsidepic3.setVisibility(View.GONE);
                                wareinsidepic4.setVisibility(View.GONE);
                                wareinsidepic5.setVisibility(View.GONE);
                                wareinsidepic6.setVisibility(View.GONE);
                                wareinsidepic7.setVisibility(View.GONE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==2)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.GONE);
                                wareinsidepic4.setVisibility(View.GONE);
                                wareinsidepic5.setVisibility(View.GONE);
                                wareinsidepic6.setVisibility(View.GONE);
                                wareinsidepic7.setVisibility(View.GONE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);
                            }
                            else if(mArrayUri.size()==3)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.GONE);
                                wareinsidepic5.setVisibility(View.GONE);
                                wareinsidepic6.setVisibility(View.GONE);
                                wareinsidepic7.setVisibility(View.GONE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==4)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic4.setImageURI(mArrayUri.get(3));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.VISIBLE);
                                wareinsidepic5.setVisibility(View.GONE);
                                wareinsidepic6.setVisibility(View.GONE);
                                wareinsidepic7.setVisibility(View.GONE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==5)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic4.setImageURI(mArrayUri.get(3));
                                wareinsidepic5.setImageURI(mArrayUri.get(4));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.VISIBLE);
                                wareinsidepic5.setVisibility(View.VISIBLE);
                                wareinsidepic6.setVisibility(View.GONE);
                                wareinsidepic7.setVisibility(View.GONE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==6)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic4.setImageURI(mArrayUri.get(3));
                                wareinsidepic5.setImageURI(mArrayUri.get(4));
                                wareinsidepic6.setImageURI(mArrayUri.get(5));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.VISIBLE);
                                wareinsidepic5.setVisibility(View.VISIBLE);
                                wareinsidepic6.setVisibility(View.VISIBLE);
                                wareinsidepic7.setVisibility(View.GONE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==7)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic4.setImageURI(mArrayUri.get(3));
                                wareinsidepic5.setImageURI(mArrayUri.get(4));
                                wareinsidepic6.setImageURI(mArrayUri.get(5));
                                wareinsidepic7.setImageURI(mArrayUri.get(6));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.VISIBLE);
                                wareinsidepic5.setVisibility(View.VISIBLE);
                                wareinsidepic6.setVisibility(View.VISIBLE);
                                wareinsidepic7.setVisibility(View.VISIBLE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==8)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic4.setImageURI(mArrayUri.get(3));
                                wareinsidepic5.setImageURI(mArrayUri.get(4));
                                wareinsidepic6.setImageURI(mArrayUri.get(5));
                                wareinsidepic7.setImageURI(mArrayUri.get(6));
                                wareinsidepic8.setImageURI(mArrayUri.get(7));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.VISIBLE);
                                wareinsidepic5.setVisibility(View.VISIBLE);
                                wareinsidepic6.setVisibility(View.VISIBLE);
                                wareinsidepic7.setVisibility(View.VISIBLE);
                                wareinsidepic8.setVisibility(View.VISIBLE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==9)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic4.setImageURI(mArrayUri.get(3));
                                wareinsidepic5.setImageURI(mArrayUri.get(4));
                                wareinsidepic6.setImageURI(mArrayUri.get(5));
                                wareinsidepic7.setImageURI(mArrayUri.get(6));
                                wareinsidepic8.setImageURI(mArrayUri.get(7));
                                wareinsidepic9.setImageURI(mArrayUri.get(8));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.VISIBLE);
                                wareinsidepic5.setVisibility(View.VISIBLE);
                                wareinsidepic6.setVisibility(View.VISIBLE);
                                wareinsidepic7.setVisibility(View.VISIBLE);
                                wareinsidepic8.setVisibility(View.VISIBLE);
                                wareinsidepic9.setVisibility(View.VISIBLE);
                                wareinsidepic10.setVisibility(View.GONE);

                            }
                            else if(mArrayUri.size()==10)
                            {
                                wareinsidepic1.setImageURI(mArrayUri.get(0));
                                wareinsidepic2.setImageURI(mArrayUri.get(1));
                                wareinsidepic3.setImageURI(mArrayUri.get(2));
                                wareinsidepic4.setImageURI(mArrayUri.get(3));
                                wareinsidepic5.setImageURI(mArrayUri.get(4));
                                wareinsidepic6.setImageURI(mArrayUri.get(5));
                                wareinsidepic7.setImageURI(mArrayUri.get(6));
                                wareinsidepic8.setImageURI(mArrayUri.get(7));
                                wareinsidepic9.setImageURI(mArrayUri.get(8));
                                wareinsidepic10.setImageURI(mArrayUri.get(9));
                                wareinsidepic1.setVisibility(View.VISIBLE);
                                wareinsidepic2.setVisibility(View.VISIBLE);
                                wareinsidepic3.setVisibility(View.VISIBLE);
                                wareinsidepic4.setVisibility(View.VISIBLE);
                                wareinsidepic5.setVisibility(View.VISIBLE);
                                wareinsidepic6.setVisibility(View.VISIBLE);
                                wareinsidepic7.setVisibility(View.VISIBLE);
                                wareinsidepic8.setVisibility(View.VISIBLE);
                                wareinsidepic9.setVisibility(View.VISIBLE);
                                wareinsidepic10.setVisibility(View.VISIBLE);

                            }

                            else
                            {
                                wareinsidepic1.setVisibility(View.GONE);
                                wareinsidepic2.setVisibility(View.GONE);
                                wareinsidepic3.setVisibility(View.GONE);
                                wareinsidepic4.setVisibility(View.GONE);
                                wareinsidepic5.setVisibility(View.GONE);
                                wareinsidepic6.setVisibility(View.GONE);
                                wareinsidepic7.setVisibility(View.GONE);
                                wareinsidepic8.setVisibility(View.GONE);
                                wareinsidepic9.setVisibility(View.GONE);
                                wareinsidepic10.setVisibility(View.GONE);
                                insidecardview.setVisibility(View.GONE);
                                insidelayout.setVisibility(View.GONE);
                                Toast.makeText(this," You can  select only 10 images",Toast.LENGTH_LONG).show();
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

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    imagesEncodedList.add(imageEncoded);

                    imagesUriList = new ArrayList<Uri>();
                    encodedImageList.clear();


                    warelayoutpicture.setImageURI(mImageUri);
                    warelayoutpicture.setVisibility(View.VISIBLE);
                    layoutcardview.setVisibility(View.VISIBLE);
                    layoutlayout.setVisibility(View.VISIBLE);
                    Toast.makeText(this," You can  select only 10 images",Toast.LENGTH_LONG).show();
                    imageURI = cursor.getString(columnIndex);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                    String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    encodedImageList.add(encodedImage);
                    cursor.close();


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
