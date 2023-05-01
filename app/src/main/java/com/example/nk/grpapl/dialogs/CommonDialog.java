package com.example.nk.grpapl.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.nk.grpapl.R;
import com.example.nk.grpapl.SharedPreferences.SP;
import com.example.nk.grpapl.splash_screen;


public class CommonDialog{

    Context context;
    SP sp;

    public static EditText ed_change_pass_old,ed_change_pass_new,ed_change_pass_confirm;



	public CommonDialog(Context context)
    {
		this.context=context;
	}


    public void showDialogExit()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Guideo");
        builder.setMessage("Do you want to exit.");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sp = SP.getInstance();
                        sp.setUsername("",context);
                        ((AppCompatActivity) context).finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ((AppCompatActivity) context).startActivity(intent);
                        android.os.Process.killProcess(android.os.Process.myPid());

                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showNetworkDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setMessage("Please Check Connection. Click Enable to enable Internet.");
        builder.setPositiveButton("Enable Internet",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

    }

    public void showGPSDisabledAlertToUser(){
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Enable it?")
                .setCancelable(false)
                .setPositiveButton("ENABLE GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                               context.startActivity(callGPSSettingIntent);
                            }
                        });
        android.app.AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    public void showChangePasswordDialog(int val)
    {
        final Dialog dialog = new Dialog(context, R.style.AppCompatAlertDialogStyle);
        dialog.setContentView(R.layout.change_password_lay);

        if(val ==0)
        {
            ed_change_pass_old = (EditText)dialog.findViewById(R.id.Currentpssword);
            ed_change_pass_new = (EditText)dialog.findViewById(R.id.newpassword);
            ed_change_pass_confirm = (EditText)dialog.findViewById(R.id.confirmnewpassword);

            Button bt_chng_pass = (Button)dialog.findViewById(R.id.bt_chng_pass) ;

            dialog.show();

        }
        else
        {
            dialog.dismiss();
        }
        dialog.setCanceledOnTouchOutside(false);
    }

    public boolean validatePassword(String password,String confirmpassword) {
        if (!password.equals(confirmpassword)){
            ed_change_pass_confirm.requestFocus();
            return false;
        }else {

        }
        return true;
    }


}