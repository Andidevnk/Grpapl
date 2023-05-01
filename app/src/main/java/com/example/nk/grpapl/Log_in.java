package com.example.nk.grpapl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
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
import com.example.nk.grpapl.Models.log_det;
import com.example.nk.grpapl.SharedPreferences.SP;
import com.example.nk.grpapl.Url.ServerUrl;
import com.example.nk.grpapl.Utils.ConnectionDetector;
import com.example.nk.grpapl.dialogs.CommonDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Log_in extends AppCompatActivity {

private RequestQueue requestQueue;
    String result;
    ScrollView sv;

    final Context context = this;
    private Button button1;
    private Button button2;
    EditText empcode,passeod;
    Button loginbtn;
    SP sp;

    public static ArrayList <log_det> log_det1 =new ArrayList<>();
    public static ArrayList <String> email =new ArrayList<>();
    public static ArrayList <String> pass =new ArrayList<>();

    ProgressBar progressBar;
    CommonDialog cd;
    ConnectionDetector connectionDetector;
    ServerUrl serverUrl;

    AppCompatActivity mActivity = Log_in.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        empcode = (EditText)findViewById(R.id.empcode);
        passeod = (EditText)findViewById(R.id.password);
        loginbtn = (Button)findViewById(R.id.button);

        connectionDetector =new ConnectionDetector(mActivity);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }

        });
        cd = new CommonDialog(this);
        sp = SP.getInstance();

        progressBar = (ProgressBar)findViewById(R.id.ptogressbar);

        TextView  forgottext = (TextView)findViewById(R.id.textforgot);
        forgottext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cd.showChangePasswordDialog(0);
            }
        });



        empcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(empcode.getText().toString()))
                {
                    empcode.setError("Input username");
                    return;
                }
                else{

                }
            }
        });
        passeod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(TextUtils.isEmpty(passeod.getText().toString()))
                {
                    passeod.setError("Input password");
                }
                else{

                }
            }
        });
    }

    public void gotoLogin(){
        if(!connectionDetector.isConnectingToInternet()){
            cd.showNetworkDialog();
        }
        else{
            String email= empcode.getText().toString().trim();
            String pass= passeod.getText().toString().trim();
            if((empcode.getText().toString()).equalsIgnoreCase("") &&(passeod.getText().toString()).equalsIgnoreCase(""))
            {
                Toast.makeText(mActivity, "Please Fill Details.", Toast.LENGTH_SHORT).show();
            }
            else if((empcode.getText().toString()).equalsIgnoreCase(""))
            {
                Toast.makeText(mActivity, "Please Enter Username.", Toast.LENGTH_SHORT).show();
            }
            else if((passeod.getText().toString()).equalsIgnoreCase(""))
            {
                Toast.makeText(mActivity, "Please Enter Password.", Toast.LENGTH_SHORT).show();
            }

                progressBar.setVisibility(View.VISIBLE);
                String url = serverUrl.serveruser_permission;
                Map<String, String> jsonparam = new HashMap<String, String>();
                jsonparam.put("email", "" + email);
                jsonparam.put("pass", "" + pass);
                gotoLoginCall(url, jsonparam);
            }
        }


    public void gotoLoginCall(String url, Map<String ,String> jsonparam){
        requestQueue = Volley.newRequestQueue(Log_in.this);
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
                        log_det1 = model.log_det;
                        sp.setUserid(""+log_det1.get(0).getId(),mActivity);
                        sp.setUsername(""+log_det1.get(0).getUsername(),mActivity);
                        sp.setPassword(""+log_det1.get(0).getPassword(),mActivity);
                        sp.setFirstname(""+log_det1.get(0).getFirstname(),mActivity);
                        sp.setLastname(""+log_det1.get(0).getLastname(),mActivity);
                        sp.setProf_img(""+log_det1.get(0).getProfile_pic(),mActivity);
                        Toast.makeText(mActivity,""+model.getMsg(),Toast.LENGTH_LONG);
                        Intent i = new Intent(Log_in.this,Dashboard_app.class);
                        i.putParcelableArrayListExtra("log_det",log_det1);
                        startActivity(i);
                        finish();
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

    public void log(View view) {
        Intent intent = new Intent(getApplicationContext(),Dashboard_app.class);
        startActivity(intent);
        finish();
    }
}
