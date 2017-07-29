package com.droiddigger.somadhan;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchDetails extends AppCompatActivity implements OnMapReadyCallback {
    Vendor detailsVendor;
    ListView listView;
    MapFragment mapFragment;
    ImageView vendorLogo;
    TextView vendorName;
    RatingBar ratingBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();

        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        listView = (ListView) findViewById(R.id.details_listView);
        detailsVendor = SearchResults.selectedVenor;
        vendorLogo = (ImageView) findViewById(R.id.imageView);
        vendorName = (TextView) findViewById(R.id.nameText);
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);


        vendorName.setText(detailsVendor.name);
        ArrayList<Search> arrayList = new ArrayList<>();
        arrayList.add(new Search("Category", detailsVendor.category));
        arrayList.add(new Search("Address", detailsVendor.address));
        arrayList.add(new Search("Contact No", detailsVendor.contactno));
        arrayList.add(new Search("Products", detailsVendor.tag.replaceAll(",", "\n")));

        ratingBar2.setRating(Float.parseFloat(detailsVendor.rating));
        SearchListAdapter adapter = new SearchListAdapter(
                this, R.layout.seatch_suggestions, arrayList);
        listView.setAdapter(adapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng latLng = new LatLng(Float.parseFloat(detailsVendor.lat), Float.parseFloat(detailsVendor.lon));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder.setTitle("Help us improve your experience");
//            alertDialogBuilder
//                    .setMessage("Please turn on GPS")
//                    .setCancelable(true)
//                    .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
//                        }
//                    });
//
//            AlertDialog alert = alertDialogBuilder.create();
//            alert.show();

        }
//        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

        map.addMarker(new MarkerOptions()
                .title(detailsVendor.name)
//                .snippet("") //todo vendor detail
                .position(latLng));
    }

    public void on_rated_clicked(View view) {


        final Dialog rankDialog = new Dialog(SearchDetails.this, R.style.FullHeightDialog);
        rankDialog.setContentView(R.layout.rank_dialoge);
        rankDialog.setCancelable(true);
        final RatingBar ratingBar = (RatingBar) rankDialog.findViewById(R.id.dialog_ratingbar);
        ratingBar.setRating(2.5f);
        rankDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE);
        rankDialog.show();

        final Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                float oldrating = Float.parseFloat(detailsVendor.rating);
//                int oldratedby = Integer.parseInt(detailsVendor.ratedby);
//                ++oldratedby;
//
//                float newValue = ((oldratedby * oldrating) + ratingBar.getRating()) / (oldratedby);
//                detailsVendor.ratedby=""+oldratedby;
//                detailsVendor.rating=""+newValue;
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference ref = database.getReference("vendor" + detailsVendor.id);
//
//
//                ref.child(detailsVendor.id).child("rating").setValue(""+newValue);
//                ref.child(detailsVendor.id).child("ratedby").setValue(""+oldratedby);
//
//                System.out.println("---id----" + detailsVendor.id);
//                System.out.println("oldvalue--------" + oldrating + "----by-----" + oldratedby);
//                System.out.println("newvalue--------" + newValue + "----by-----" + oldratedby);
                rankDialog.dismiss();
            }
        });


    }
}