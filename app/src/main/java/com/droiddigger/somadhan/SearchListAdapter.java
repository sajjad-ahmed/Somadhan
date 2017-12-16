package com.droiddigger.somadhan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sajjad Ahmed on 5/14/2017.
 */

public class SearchListAdapter extends ArrayAdapter<Search> {

    private List<Search> searches;

    public SearchListAdapter(Context context, int resource, List<Search> objects) {
        super(context, resource, objects);
        searches = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.seatch_suggestions, parent, false);
        }

        Search search = searches.get(position);
        TextView se= (TextView) convertView.findViewById(R.id.id_searchTag);
        se.setText(search.searchText);
        TextView set= (TextView) convertView.findViewById(R.id.txt_details);
        set.setText(search.searchCategory);

        return convertView;
    }
}