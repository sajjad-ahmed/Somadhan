package com.droiddigger.somadhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Gigabyte on 9/24/2017.
 */

public class SearchDetailsAdapter extends ArrayAdapter<Search> {

    private Vendor vendor;
    public SearchDetailsAdapter(Context context, int resource,Vendor object) {
        super(context, resource);
        vendor = object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.seatch_suggestions, parent, false);
        }

//        Vendor _V=Splash.vendor_Arraylist.get(position);
//        TextView se = (TextView) convertView.findViewById(R.id.txt_name_tb);
//        se.setText(Search.searchText);
//        TextView set = (TextView) convertView.findViewById(R.id.txt_details);
//        set.setText(search.searchCategory);


        return convertView;
    }
}