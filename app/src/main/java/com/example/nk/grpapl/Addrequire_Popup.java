package com.example.nk.grpapl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.nk.grpapl.SharedPreferences.SP;

public class Addrequire_Popup extends AppCompatActivity {

    Spinner myspinner;
    Button okbutton;
    int var;
    SP sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrequire__popup);
        sp = SP.getInstance();

        myspinner = (Spinner) findViewById(R.id.spinneraddrequirments);
        okbutton = (Button) findViewById(R.id.okbutton);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(Addrequire_Popup.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Propertytypes1));
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
                sp.setProperty_type(""+position,Addrequire_Popup.this);
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
                        j= new Intent(Addrequire_Popup.this,New_requirment_form.class);
                        startActivity(j);
                        finish();
                        break;
                    case 2:
                        Intent k;
                        k= new Intent(Addrequire_Popup.this,Property_specification_form.class);
                        startActivity(k);
                        finish();
                        break;
                    case 3:
                        Intent l;
                        l = new Intent(Addrequire_Popup.this,Serviced_office.class);
                        startActivity(l);
                        finish();
                        break;
                    case 4:
                        Intent m;
                        m = new Intent(Addrequire_Popup.this,Warehouse.class);
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
