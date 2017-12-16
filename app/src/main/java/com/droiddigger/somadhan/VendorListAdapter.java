package com.droiddigger.somadhan;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sajjad Ahmed on 5/14/2017.
 */

public class VendorListAdapter extends BaseAdapter {

    private ArrayList<Vendor> vendors;
    Context mContext;
    ArrayList<Vendor> arraylist;
    LayoutInflater inflater;

    public VendorListAdapter(Context context, List<Vendor> objects) {
//        super(context, resource, objects);

        mContext = context;
        vendors = new ArrayList<>();
        vendors.addAll(objects);

        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Vendor>();
        this.arraylist.addAll(Splash.vendor_Arraylist);
    }

    @Override
    public int getCount() {
        return vendors.size();
    }

    @Override
    public Object getItem(int i) {
        return vendors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).
//                    inflate(R.layout.list_item, parent, false);
            convertView = inflater.
                    inflate(R.layout.list_item, parent, false);
        }

        Vendor vendor = vendors.get(position);

        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(vendor.getName());


        TextView addressTexr = (TextView) convertView.findViewById(R.id.addressText);
        addressTexr.setText(vendor.getAddress());

        TextView noOfRating = (TextView) convertView.findViewById(R.id.noOfRatingText);
        noOfRating.setText("(" + vendor.getRatedby() + ")");

        TextView ratingText = (TextView) convertView.findViewById(R.id.ratingText);
        ratingText.setText(vendor.getRating());

        ImageView imageView_verification = (ImageView) convertView.findViewById(R.id.id_verificationStatus);


        if (vendor.getVerification().trim().toLowerCase().equals("true"))
            imageView_verification.setVisibility(View.VISIBLE);
        else if (vendor.getVerification().trim().toLowerCase().equals("ad")) {
            imageView_verification.setVisibility(View.VISIBLE);
            imageView_verification.setImageResource(R.drawable.ic_ad);
        } else if (vendor.getVerification().trim().toLowerCase().equals("false"))
            imageView_verification.setVisibility(View.INVISIBLE);

        return convertView;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        vendors.clear();
        if (charText.length() == 0) {
            vendors.addAll(arraylist);
        } else {
            for (Vendor wp : arraylist) {
                if (wp.tag.toLowerCase(Locale.getDefault()).contains(charText) ||
                        wp.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    vendors.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}