package com.example.firstacm;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity implements View .OnClickListener{

    private CardView aboutCard,gallaryCard,connectCard,workshopCard,eventCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(!isConnected()){
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Connection Error")
                    .setMessage("Please check your Internet connection")
                    .setPositiveButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
        else{
            Toast.makeText(HomeActivity.this,"Welcome" ,Toast.LENGTH_LONG).show();
        }

        aboutCard=(CardView) findViewById(R.id.about_id);
        gallaryCard=(CardView) findViewById(R.id.gallary_id);
        connectCard=(CardView) findViewById(R.id.connect_id);
        workshopCard=(CardView) findViewById(R.id.workshop_id);
        eventCard=(CardView) findViewById(R.id.events_id);

        aboutCard.setOnClickListener(this);
        gallaryCard.setOnClickListener(this);
        connectCard.setOnClickListener(this);
        workshopCard.setOnClickListener(this);
        eventCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.about_id: i =new Intent(this,About.class); startActivity(i);   break;
            case R.id.gallary_id : i =new Intent(this,Gallary.class); startActivity(i); break;
            case R.id.connect_id : i =new Intent(this,ConnectWithUS.class); startActivity(i); break;
            case R.id.workshop_id : i = new Intent(this,Workshop.class); startActivity(i); break;
            case R.id.events_id : i = new Intent(this,Event.class) ; startActivity(i); break;

            default: break;
        }


    }

    private boolean isConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return  networkInfo != null && networkInfo.isConnected();
    }
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HomeActivity.super.onBackPressed();
                        quit();
                    }
                }).create().show();
    }


    public void quit() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(start);
    }

}
