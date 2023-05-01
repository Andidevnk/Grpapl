package com.example.nk.grpapl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.example.nk.grpapl.Models.addressss;
import com.example.nk.grpapl.Models.bank_det;
import com.example.nk.grpapl.Models.ci_user;
import com.example.nk.grpapl.SharedPreferences.SP;
import com.example.nk.grpapl.Url.ServerUrl;
import com.example.nk.grpapl.Utils.ConnectionDetector;
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

public class Edit_Profile extends AppCompatActivity {



    private JSONObject jsonObject;
    ArrayList<Uri> imagesUriList;
    ArrayList<String> encodedImageList;
    String imageURI;
    String imageEncoded;
    List<String> imagesEncodedList;
    Spinner state_sp,city_sp;
    Spinner state_perman,city_permanet;
    AppCompatActivity mActivity =Edit_Profile.this;
    ConnectionDetector connectionDetector;
    CommonDialog cd;
    ProgressBar progressBar;
    private RequestQueue requestQueue;
    String result;
    ScrollView sv;
    private static final int PICK_FROM_GALLERY = 1;
    public static String state_nm , p_id="";
    SP sp;
    ServerUrl serverUrl;
    Databse_Handler mdatabase_handler;
    Button submit;
    ImageView editprofile,profile;
    EditText firstname,lastname,email,number,addresscurrent,addresspermannet,landmarkcurrent,landmarkpermannet,

    pincurrent,pinpermannet,name_account ,number_account,ifsccode;

    public static ArrayList <ci_user> ci_user1 =new ArrayList<>();
    public static ArrayList <bank_det> bank_det1 =new ArrayList<>();
    public static ArrayList <addressss> address1 =new ArrayList<>();
    public static ArrayList<States> states = new ArrayList<>();
    public static ArrayList<String> states_nm = new ArrayList<>();
    public static ArrayList<Cities>  cities= new ArrayList<>();
    public static ArrayList<String> cities_nm = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);



        jsonObject = new JSONObject();
        encodedImageList = new ArrayList<>();

        mdatabase_handler=new Databse_Handler(this);
        connectionDetector = new ConnectionDetector(mActivity);
        cd=new CommonDialog(mActivity);
        sp = SP.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        progressBar = (ProgressBar)findViewById(R.id.progressBar_serviced);


        profile  = (ImageView)findViewById(R.id.Editpicture);
        editprofile = (ImageView)findViewById(R.id.edit);
        firstname = (EditText)findViewById(R.id.Editfirstname);
        lastname  = (EditText)findViewById(R.id.Editlastname);
        email = (EditText)findViewById(R.id.Editemail);
        number = (EditText)findViewById(R.id.Editnumber);
        addresscurrent = (EditText)findViewById(R.id.Editaddress);
        landmarkcurrent = (EditText)findViewById(R.id.Edirtlandmark);
        pincurrent = (EditText)findViewById(R.id.Editpin);
        addresspermannet = (EditText)findViewById(R.id.Editpermanaddress);
        landmarkpermannet = (EditText)findViewById(R.id.Edirtpermannetlandmark);
        pinpermannet = (EditText)findViewById(R.id.Editpermanentrpin);
        name_account = (EditText)findViewById(R.id.Editaccountname);
        number_account = (EditText)findViewById(R.id.Editsavingaccount);
        ifsccode = (EditText)findViewById(R.id.Editifsccode);

        submit  =(Button)findViewById(R.id.submiteditprofile);
        state_sp = (Spinner)findViewById(R.id.spinner_staes_serviceoffic);
        city_sp = (Spinner)findViewById(R.id.spinner_cities_serviceoffic);
        state_perman = (Spinner)findViewById(R.id.spinner_staes_permanet_serviceoffic);
        city_permanet = (Spinner)findViewById(R.id.spinner_cities_permanet_serviceoffic);



        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (ActivityCompat.checkSelfPermission(Edit_Profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Edit_Profile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailedit = email.getText().toString();
                final String numedit = number.getText().toString();


            /*    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (emailedit.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if(numedit.length()==10){

                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }*/
                Upload_image();
            }
        });

        gotoGetStates();

        state_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        state_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        requestQueue = Volley.newRequestQueue(Edit_Profile.this);
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
                        city_sp.setAdapter(myadapter);
                        city_permanet.setAdapter(myadapter);

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
        requestQueue = Volley.newRequestQueue(Edit_Profile.this);
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
                        state_sp.setAdapter(myadapter);
                        state_perman.setAdapter(myadapter);
                        progressBar.setVisibility(View.GONE);
                        editprofile();

                    }
                    else
                    {
                        //progress.setVisibility(View.GONE);
                        Toast.makeText(mActivity, ""+model.getMsg(), Toast.LENGTH_SHORT).show();
                        editprofile();

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

    public void editprofile(){
        if(!connectionDetector.isConnectingToInternet()){
            cd.showNetworkDialog();
        }
        else{

            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverEditPrfile;
            Map<String, String> jsonparam = new HashMap<String, String>();
            editprofile(url, jsonparam);
        }
    }


    public void editprofile(String url, Map<String ,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Edit_Profile.this);
        requestQueue.getCache().clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonparam),  new Response.Listener<JSONObject>() {
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
                        ci_user1 = model.ci_user;
                        bank_det1 = model.bank_det;
                        address1 = model.addressss;
                        p_id = model.getPid();
                        firstname.setText(""+ci_user1.get(0).getFirstname());
                        lastname.setText(""+ci_user1.get(0).getLastname());
                        email.setText(""+ci_user1.get(0).getEmail());
                        number.setText(""+ci_user1.get(0).getMobile_no());
                        addresscurrent.setText(""+address1.get(0).getAdd1());
                        landmarkcurrent.setText(""+address1.get(0).getLmark1());

                        int index = states_nm.indexOf(""+address1.get(0).getState1());
                        state_sp.setSelection(index);

                        int  cityindex = cities_nm.indexOf(""+address1.get(0).getCity1());
                        city_sp.setSelection(cityindex);

                        pincurrent.setText(""+address1.get(0).getPin1());
                        addresspermannet.setText(""+address1.get(0).getAdd2());
                        landmarkpermannet.setText(""+address1.get(0).getLmark2());

                        int index2 = states_nm.indexOf(""+address1.get(0).getState2());
                        state_perman.setSelection(index2);

                        int  cityindex2 = cities_nm.indexOf(""+address1.get(0).getCity2());
                        city_permanet.setSelection(cityindex2);

                        pinpermannet.setText(""+address1.get(0).getPin2());
                        name_account.setText(""+bank_det1.get(0).getBaccount());
                        number_account.setText(""+bank_det1.get(0).getSaccount());
                        ifsccode.setText(""+bank_det1.get(0).getIfsc());

                        Toast.makeText(mActivity,""+model.getMsg(),Toast.LENGTH_LONG);

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

                    profile.setImageURI(mImageUri);
                    profile.setVisibility(View.VISIBLE);

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
