package com.example.nk.grpapl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nk.grpapl.Addrequire_Popup;
import com.example.nk.grpapl.Edit_Profile;
import com.example.nk.grpapl.Log_in;
import com.example.nk.grpapl.Notification_customlistview;
import com.example.nk.grpapl.Popup;
import com.example.nk.grpapl.R;
import com.example.nk.grpapl.dialogs.CommonDialog;

/**
 * Created by Nk on 7/30/2018.
 */

public class Gridview_dashboard extends ArrayAdapter {


    CommonDialog cd;

    int images[] = {
            R.drawable.ic_add_circle_outline_black_24dp,
            R.drawable.ic_playlist_add_black_24dp,
            R.drawable.ic_plus_one_black_24dp,
            R.drawable.ic_control_point_duplicate_black_24dp,
            R.drawable.ic_edit_black_24dp,
            R.drawable.ic_notifications_active_black_24dp,
            R.drawable.ic_exit_to_app_black_24dp

    };

    String Title_name[] = {
            "Add Supply", "List Supply", "Add Requirments", "List Requirments", "Edit Profile", "Notification", "Exit" };


    String Discription[] = {
            "Add details you want", "List all supply", "Add new requirments", "List new requirments", "Edit your profile", "all alerts", "Out from application"
    };

    int  color[] = {

            Color.parseColor("#7c4dff"),
            Color.parseColor("#fe104d"),
            Color.parseColor("#00bfa5"),
            Color.parseColor("#ae2f99"),
            Color.parseColor("#ffb300"),
            Color.parseColor("#f3d90303"),
            Color.parseColor("#f304f1f1")


    };

    Context context;
    LayoutInflater layoutInflater;


    public Gridview_dashboard(@NonNull Context context) {
        super(context, R.layout.dashboard_list_adapter);


        cd = new CommonDialog(context);


        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        return images.length;
    }


    @Nullable
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        ImageView image;
        TextView titl_name, discri;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = layoutInflater.inflate(R.layout.dashboard_list_adapter, null);
            holder.titl_name = (TextView) convertView.findViewById(R.id.txtnametitleaddsupply);
            holder.discri = (TextView) convertView.findViewById(R.id.txtdiscaddsupply);
            holder.image = (ImageView) convertView.findViewById(R.id.cardviewaddsupply);
            convertView.setTag(holder);
        } else {

            holder = (Holder) convertView.getTag();
        }
        holder.image.setImageResource(images[position]);
        holder.image.setBackgroundColor(color[position]);
        holder.titl_name.setText(Title_name[position]);
        holder.discri.setText(Discription[position]);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position==0)
                {
                    Intent i =new Intent(context,Popup.class);
                    context.startActivity(i);
                }
              else if(position==1)
                {

                }
                else if(position ==2)
                {
                    Intent i = new Intent(context, Addrequire_Popup.class);
                    context.startActivity(i);
                }
                else if(position == 3)
                {

                }
                else if(position == 4)
                {
                    Intent i = new Intent(context, Edit_Profile.class);
                    context.startActivity(i);
                }

                else if (position == 5)
                {
                    Intent i = new Intent(context, Notification_customlistview.class);
                    context.startActivity(i);
                }

                else if(position==6)
                {


                    cd.showDialogExit();
                }
                else
                {
                    Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
                }

            }
        });

        return convertView;
    }
}
