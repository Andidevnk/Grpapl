package com.example.nk.grpapl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.nk.grpapl.SharedPreferences.SP;
import com.example.nk.grpapl.Url.ServerUrl;
import com.example.nk.grpapl.Utils.ConnectionDetector;
import com.example.nk.grpapl.dialogs.CommonDialog;
import com.example.nk.grpapl.sqlite.Databse_Handler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class New_requirment_form extends AppCompatActivity {


    private RequestQueue requestQueue;
    String result;
    ScrollView sv;
    CommonDialog cd;
    ProgressBar progressBar;
    SP sp;
    ConnectionDetector connectionDetector;
    Databse_Handler databse_handler;
    AppCompatActivity mActivity = New_requirment_form.this;
    public  static String  p_id;
    EditText comname,contpername,num,email,fsifrom,fsito,jamabandifrom,jamabandito,landsizefrom,landsizeto,fronatgefrom,frontageto;
    Button submit;
    Spinner spinnercurrent,spinneruser;
    RadioGroup rg1;
    RadioButton llyes,llno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requirment_form);



        sp = SP.getInstance();
        connectionDetector = new ConnectionDetector(mActivity);
        databse_handler = new Databse_Handler(this);

        comname = (EditText)findViewById(R.id.comname);
        contpername = (EditText)findViewById(R.id.cotpersoanname);
        num =(EditText)findViewById(R.id.number);
        email = (EditText)findViewById(R.id.emailperson);
        fsifrom = (EditText)findViewById(R.id.fromfsi);
        fsito = (EditText)findViewById(R.id.tofsi);
        jamabandifrom = (EditText)findViewById(R.id.fromjamabandi);
        jamabandito = (EditText)findViewById(R.id.tojamabandi);
        landsizefrom = (EditText)findViewById(R.id.fromlandsize);
        landsizeto = (EditText)findViewById(R.id.tolandsize);
        fronatgefrom = (EditText)findViewById(R.id.fromfrontage);
        frontageto = (EditText)findViewById(R.id.tofrontage);
        rg1 = (RadioGroup)findViewById(R.id.llgroupreqnew);
        llyes =(RadioButton)findViewById(R.id.llyesnew);
        llno =(RadioButton)findViewById(R.id.llnonew);
        progressBar =(ProgressBar)findViewById(R.id.ptogressbarproperty);

        submit = (Button)findViewById(R.id.submittion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnercurrent = (Spinner) findViewById(R.id.spinnercurrentland);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(New_requirment_form.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currentLanUsagesLicense));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercurrent.setAdapter(myadapter);



        spinneruser = (Spinner) findViewById(R.id.spinnerlanduser);
        ArrayAdapter<String> myadapter1 = new ArrayAdapter<String>(New_requirment_form.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.landUsagesCanbechagedto));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneruser.setAdapter(myadapter1);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newnum = num.getText().toString().trim();
                final String newemail = email.getText().toString().trim();

              /*  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (newemail.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }


                if(newnum.length()==10){

                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_LONG).show();
                }

*/
                submitdata();
            }
        });

    }

    public void submitdata() {
        if (!connectionDetector.isConnectingToInternet()) {
            cd.showNetworkDialog();

        } else {
            String llradiogroup="";
            if (rg1.getCheckedRadioButtonId() == R.id.llyesnew){
                llradiogroup = "1";
                Toast.makeText(mActivity, ""+llradiogroup, Toast.LENGTH_SHORT).show();
            }else{
                llradiogroup = "0";
                Toast.makeText(mActivity, ""+llradiogroup, Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.VISIBLE);
            String url = ServerUrl.serverNew_requirement_form;
            Map<String, String> jsonparam = new HashMap<String, String>();
            jsonparam.put("current_lnd_spinner",""+spinnercurrent.getSelectedItem().toString());
            jsonparam.put("lnd_user_spinner",""+spinneruser.getSelectedItem().toString());
            jsonparam.put("ll_group",""+llradiogroup);
            jsonparam.put("fsi_from",""+fsifrom.getText().toString());
            jsonparam.put("fsi_to",""+fsito.getText().toString());
            jsonparam.put("jamabandi_from",""+jamabandifrom.getText().toString());
            jsonparam.put("jamabandi_to",""+jamabandito.getText().toString());
            jsonparam.put("land_size_from",""+landsizefrom.getText().toString());
            jsonparam.put("land_size_to",""+landsizeto.getText().toString());
            jsonparam.put("frontage_from",""+fronatgefrom.getText().toString());
            jsonparam.put("frontage_to",""+frontageto.getText().toString());
            jsonparam.put("com_name",""+comname.getText().toString());
            jsonparam.put("cpn_name",""+contpername.getText().toString());
            jsonparam.put("num",""+num.getText().toString());
            jsonparam.put("email",""+email.getText().toString());
            submitdata(url,jsonparam);

        }
    }

    public void submitdata(String url, Map<String,String> jsonparam){
        requestQueue = Volley.newRequestQueue(New_requirment_form.this);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
