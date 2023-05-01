package com.example.nk.grpapl;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.nfc.TagLostException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nk.grpapl.Adapter.Gridview_dashboard;
import com.example.nk.grpapl.SharedPreferences.SP;
import com.example.nk.grpapl.Url.ServerUrl;
import com.squareup.picasso.Picasso;

import java.util.zip.CRC32;

public class Dashboard_app extends AppCompatActivity {


    Gridview_dashboard gridview_dashboard;
    GridView gridView;

    TextView t_name;
    ImageView U_profile_img;

    SP sp;

    AppCompatActivity mActivity = Dashboard_app.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_app);

        sp = SP.getInstance();

        gridView = (GridView)findViewById(R.id.dashboardgrid);
        t_name = (TextView)findViewById(R.id.empnametextview);
        U_profile_img = (ImageView)findViewById(R.id.toolbarimages);


        String var =sp.getFirstname(Dashboard_app.this);
        String var1 =sp.getLastname(Dashboard_app.this);
        t_name.setText(var+" "+var1);

        String url = ServerUrl.imageprefix+""+sp.getProf_img(mActivity);
        Picasso.get().load(url).into(U_profile_img);
        gridview_dashboard=new Gridview_dashboard(Dashboard_app.this);
        gridView.setAdapter(gridview_dashboard);
    }
}
