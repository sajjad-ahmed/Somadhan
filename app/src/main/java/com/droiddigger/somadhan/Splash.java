package com.droiddigger.somadhan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Splash extends AppCompatActivity {

    protected static Activity splashActivityInstance;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    HashMap<String, HashMap> vendorHashMap;
    public boolean b1 = false;
    public static ArrayList<Vendor> vendor_Arraylist = new ArrayList<>();
    public static ArrayList<String> searchStringArrayList = new ArrayList<>();
    public static HashMap<String, String> searchableHashMap = new HashMap<>();


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        splashActivityInstance = this;

        start();


//        progressDialog = ProgressDialog.show(Splash.this, "Loading. Please wait.", "We are making sure your fast experience.");

        getSupportActionBar().hide();

        databaseSync(); //--------firebase data sync //TODO

    }


    private void databaseSync() {
        DatabaseReference ref = database.getReference("vendor");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.getValue().toString().equals("")) {

                    vendorHashMap = (HashMap) dataSnapshot.getValue();
                    int m = 0;
                    for (String key : vendorHashMap.keySet()) {
                        HashMap<String, String> eachVendorMap = (HashMap) vendorHashMap.get(key);
                        String name = eachVendorMap.get("name");
                        String id = eachVendorMap.get("id");
                        String address = eachVendorMap.get("address");
                        String image = eachVendorMap.get("image");
                        String category = eachVendorMap.get("category");
                        String contactno = eachVendorMap.get("contactno");
                        String lat = eachVendorMap.get("lat");
                        String lon = eachVendorMap.get("lon");
                        String tag = eachVendorMap.get("tag");
                        String ratedby = eachVendorMap.get("ratedby");
                        String rating = eachVendorMap.get("rating");
                        String verification = eachVendorMap.get("verification");

                        Vendor _v = new Vendor(name, id, address, image, category, contactno, lat, lon, tag, ratedby, rating, verification);

                        vendor_Arraylist.add(_v);

                        searchStringArrayList.add(name);

                        StringTokenizer stringTokenizer = new StringTokenizer(tag, ",");

                        while (stringTokenizer.hasMoreTokens()) {
                            String _eachTag = stringTokenizer.nextToken().trim();
                            if (m == 0) {
                                searchStringArrayList.add(_eachTag); //TODO remove thesre
                                searchableHashMap.put(_eachTag, "Tag");
                            } else {
                                boolean flag = true;
                                for (int k = 0; k < searchStringArrayList.size(); k++) {
                                    if (searchStringArrayList.get(k).toLowerCase().trim().equals(_eachTag.toLowerCase().trim())) {
                                        flag = false;
                                        break;
                                    }
                                }

                                if (flag)
                                {
                                    searchStringArrayList.add(_eachTag); //TODO remove thesre
                                    searchableHashMap.put(_eachTag, "Tag");
                                }
                            }
                        }

                        m++;
                    }
//                    for (String x : searchStringArrayList)
//                        System.out.println("----------" + x);
                }
                b1 = true;
//                if (progressDialog.isShowing()) {
//                    progressDialog.hide();
//                }

                Toast.makeText(Splash.this, "Data Synced Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Splash.this, Home.class);
                startActivity(intent);

                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void start() {
        if (!isOnline()) {
            showDialouge();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void showDialouge() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Splash.this);
        alertDialogBuilder.setTitle("No Internet Connection!");
        alertDialogBuilder
                .setMessage("Please activate WI-FI/Mobile data")
                .setCancelable(false)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
                    }
                });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
