package com.example.nk.grpapl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nk.grpapl.SharedPreferences.SP;

/**
 * Created by Nk on 7/9/2018.
 */

public class Popup extends Activity {
    Spinner myspinner;
    Button okbutton;
    int var;
    SP sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        sp = SP.getInstance();

        myspinner = (Spinner) findViewById(R.id.spinner);
        okbutton = (Button) findViewById(R.id.okbutton);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Popup.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Propertytypes));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner.setAdapter(myadapter);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp.setProperty_type(""+position,Popup.this);
                var = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (var)
                {




                    case 1:
                        Intent j;
                        j= new Intent(Popup.this,Land.class);
                        startActivity(j);
                        finish();
                        break;
                    case 2:
                        Intent k;
                        k= new Intent(Popup.this,Property_specification_form.class);
                        startActivity(k);
                        finish();
                        break;
                    case 3:
                        Intent l;
                        l = new Intent(Popup.this,Serviced_office.class);
                        startActivity(l);
                        finish();
                        break;
                    case 4:
                        Intent m;
                        m = new Intent(Popup.this,Warehouse.class);
                        startActivity(m);
                        finish();
                        break;


                }
            }
        });


    }

    public void Cancel(View view) {
        finish();
    }
}
